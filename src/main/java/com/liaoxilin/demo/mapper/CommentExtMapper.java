package com.liaoxilin.demo.mapper;

import com.liaoxilin.demo.model.Comment;
import com.liaoxilin.demo.model.CommentExample;
import com.liaoxilin.demo.model.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface CommentExtMapper {
    int incCommentCount(Comment comment);
}