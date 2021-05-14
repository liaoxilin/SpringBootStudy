package com.liaoxilin.demo.controller;


import com.liaoxilin.demo.dto.CommentDto;
import com.liaoxilin.demo.dto.QuestionDto;
import com.liaoxilin.demo.enums.CommentTypeEnum;
import com.liaoxilin.demo.service.CommentService;
import com.liaoxilin.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name="id")Long id,
                           Model model){
        QuestionDto questionDto=questionService.getById(id);
        List<QuestionDto> relatedQuestions = questionService.selectRelated(questionDto);
        List<CommentDto> comments = commentService.listByTargetId(id, CommentTypeEnum.QUESTION);
        questionService.incView(id);//累加阅读数
        model.addAttribute("question",questionDto);
        model.addAttribute("comments",comments);
        model.addAttribute("relatedQuestions",relatedQuestions);
        return "question";
    }
}
