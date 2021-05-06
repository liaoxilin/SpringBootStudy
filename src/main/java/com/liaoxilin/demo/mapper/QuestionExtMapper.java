package com.liaoxilin.demo.mapper;

import com.liaoxilin.demo.model.Question;
import com.liaoxilin.demo.model.QuestionExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

@Mapper
public interface QuestionExtMapper {

    int incView(Question record);
}