package com.liaoxilin.demo.service;


import com.liaoxilin.demo.mapper.UserMapper;
import com.liaoxilin.demo.model.User;
import com.liaoxilin.demo.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public void createOrUpdata(User user) {

        UserExample userExample=new UserExample();
        userExample.createCriteria().andAccountIdEqualTo(user.getAccountId());
        List<User> users= userMapper.selectByExample(userExample);
        if(users.size()==0){
            //插入
            user.setGmtCreate(String.valueOf(System.currentTimeMillis()));
            user.setGmtModify(user.getGmtCreate());
            userMapper.insert(user);
        }else{
            //更新
            User dbUser= users.get(0);
            User updateUser =new User();
            updateUser.setGmtCreate(String.valueOf(System.currentTimeMillis()));
            updateUser.setAvatarUrl(user.getAvatarUrl());
            updateUser.setName(user.getName());
            updateUser.setToken(user.getToken());
            UserExample example=new UserExample();
            example.createCriteria().andIdEqualTo(dbUser.getId());
            userMapper.updateByExampleSelective(updateUser,example);
        }
    }
}
