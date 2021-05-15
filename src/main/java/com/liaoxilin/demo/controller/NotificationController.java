package com.liaoxilin.demo.controller;

import com.liaoxilin.demo.dto.NotificationDto;
import com.liaoxilin.demo.dto.PaginationDto;
import com.liaoxilin.demo.enums.NotificationTypeEnums;
import com.liaoxilin.demo.model.User;
import com.liaoxilin.demo.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/notification/{id}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "id")Long id){



        User user=(User) request.getSession().getAttribute("user");
        if(user==null){
            return "redirect:/";
        }

        NotificationDto notificationDto =notificationService.read(id,user);
        if(NotificationTypeEnums.REPLY_COMMENT.getType() == notificationDto.getType()
        ||NotificationTypeEnums.REPLY_QUESTION.getType()== notificationDto.getType()) {
            return "redirect:/question/" + notificationDto.getOuterid();
        }else{
            return "redirect:/";
        }
    }
}
