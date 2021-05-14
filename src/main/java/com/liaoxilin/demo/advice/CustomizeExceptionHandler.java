package com.liaoxilin.demo.advice;

import com.alibaba.fastjson.JSON;
import com.liaoxilin.demo.dto.ResultDto;
import com.liaoxilin.demo.exception.CustomizeErrorCode;
import com.liaoxilin.demo.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    Object handle(HttpServletRequest request, Throwable e, Model model, HttpServletResponse response){
        String contentType = request.getContentType();

        if("application/json".equals(contentType)){
            ResultDto resultDto=null;
            //返回json
            if(e instanceof CustomizeException){
                resultDto =  ResultDto.errorOf((CustomizeException)e);
            }else{
                resultDto = ResultDto.errorOf(CustomizeErrorCode.SYS_ERROR);
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDto));
                writer.close();
            } catch (IOException ioe) {
            }
            return null;
        }else {
            //返回页面错误
            if(e instanceof CustomizeException){
                model.addAttribute("message",e.getMessage());
            }else{
                model.addAttribute("message",CustomizeErrorCode.SYS_ERROR.getMessage());
            }

            return new ModelAndView(("error"));

        }

    }

}
