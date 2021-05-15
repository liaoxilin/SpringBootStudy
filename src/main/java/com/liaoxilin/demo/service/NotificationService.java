package com.liaoxilin.demo.service;

import com.liaoxilin.demo.dto.NotificationDto;
import com.liaoxilin.demo.dto.PaginationDto;
import com.liaoxilin.demo.dto.QuestionDto;
import com.liaoxilin.demo.enums.NotificationStatusEnum;
import com.liaoxilin.demo.enums.NotificationTypeEnums;
import com.liaoxilin.demo.exception.CustomizeErrorCode;
import com.liaoxilin.demo.exception.CustomizeException;
import com.liaoxilin.demo.mapper.NotificationMapper;
import com.liaoxilin.demo.mapper.UserMapper;
import com.liaoxilin.demo.model.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    private NotificationMapper notificationMapper;
    @Autowired
    private UserMapper userMapper;


    public PaginationDto list(Long userId, Integer page, Integer size) {


        PaginationDto<NotificationDto> paginationDto = new PaginationDto<>();
        Integer totalPage;
        NotificationExample notificationExample=new NotificationExample();
        notificationExample.createCriteria().andReceiverEqualTo(userId);
        Integer totalCount=(int)notificationMapper.countByExample(notificationExample);

        if(totalCount%size==0){
            totalPage=totalCount/size;
        }else{
            totalPage=totalCount/size+1;
        }

        if(page<1){
            page=1;
        }
        if(page>totalPage){
            page=totalPage;
        }
        paginationDto.setPagination(totalPage,page);

        Integer offset=size*(page-1);
        NotificationExample example=new NotificationExample();
        example.createCriteria().andReceiverEqualTo(userId);
        example.setOrderByClause("gmt_create desc");

        List<Notification> notifications =notificationMapper.selectByExampleWithRowbounds(example,new RowBounds(offset,size));

        if(notifications.size()==0){
            return paginationDto;
        }

       //?


        List<NotificationDto> notificationDtos=new ArrayList<>();

        for(Notification notification:notifications){
            NotificationDto notificationDto = new NotificationDto();
            BeanUtils.copyProperties(notification,notificationDto);
            notificationDto.setTypeName(NotificationTypeEnums.nameOfType(notification.getType()));
            notificationDtos.add(notificationDto);
        }

        paginationDto.setData(notificationDtos);
        return paginationDto;
    }

    public Long unreadCount(Long userId) {

        NotificationExample notificationExample = new NotificationExample();
        notificationExample.createCriteria()
                .andReceiverEqualTo(userId)
                .andStatusEqualTo(NotificationStatusEnum.UNREAD.getStaus());
        return notificationMapper.countByExample(notificationExample);
    }

    public NotificationDto read(Long id, User user) {

        Notification notification = notificationMapper.selectByPrimaryKey(id);
        if(notification.getReceiver()!=user.getId()){
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }
        if(!Objects.equals(notification.getReceiver(),user.getId())){
            throw new CustomizeException(CustomizeErrorCode.READ_NOTIFICATION_FAIL);
        }

        notification.setStatus(NotificationStatusEnum.READ.getStaus());
        notificationMapper.updateByPrimaryKey(notification);

        NotificationDto notificationDto = new NotificationDto();
        BeanUtils.copyProperties(notification,notificationDto);
        notificationDto.setTypeName(NotificationTypeEnums.nameOfType(notification.getType()));
        return notificationDto;
    }
}
