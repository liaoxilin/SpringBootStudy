package com.liaoxilin.demo.service;

import com.liaoxilin.demo.dto.PaginationDto;
import com.liaoxilin.demo.dto.QuestionDto;
import com.liaoxilin.demo.mapper.QuestionMapper;
import com.liaoxilin.demo.mapper.UserMapper;
import com.liaoxilin.demo.model.Question;
import com.liaoxilin.demo.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDto list(Integer page, Integer size) {


        PaginationDto paginationDto = new PaginationDto();
        Integer totalCount=questionMapper.count();
        paginationDto.setPagination(totalCount,page,size);

        if(page<1){
            page=1;
        }
        if(page>paginationDto.getTotalPage()){
            page=paginationDto.getTotalPage();
        }

        Integer offset=size*(page-1);
        List<Question> questions = questionMapper.list(offset,size);
        List<QuestionDto> questionDtoList=new ArrayList<>();


        for(Question question:questions){
            User user =userMapper.findByID(question.getCreator());
            QuestionDto questionDto=new QuestionDto();
            BeanUtils.copyProperties(question,questionDto);
            questionDto.setUser(user);
            questionDtoList.add(questionDto);
        }
        paginationDto.setQuestions(questionDtoList);

        return paginationDto;
    }
}
