package com.liaoxilin.demo.controller;

import com.liaoxilin.demo.dto.PaginationDto;
import com.liaoxilin.demo.dto.QuestionDto;
import com.liaoxilin.demo.mapper.QuestionMapper;
import com.liaoxilin.demo.mapper.UserMapper;
import com.liaoxilin.demo.model.Question;
import com.liaoxilin.demo.model.User;
import com.liaoxilin.demo.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {



    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page",defaultValue ="1") Integer page,
                        @RequestParam(name = "size",defaultValue ="5") Integer size
                        ){


        PaginationDto pagination=questionService.list(page,size);
        model.addAttribute("pagination",pagination);

        return "index";
    }
}
