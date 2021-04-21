package com.liaoxilin.demo.mapper;

import com.liaoxilin.demo.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;



@Mapper
public interface UserMapper {
    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modify,avatar_url) values (#{name},#{accountId},#{token},#{getCreate},#{getModified},#{avatarUrl})")
    void insert(User user);


    @Select("select * from user where token = #{token}")
    User findByToken(@Param("token")String token);


    @Select("select * from user where id = #{id}")
    User findByID(@Param("id")Integer id);
}