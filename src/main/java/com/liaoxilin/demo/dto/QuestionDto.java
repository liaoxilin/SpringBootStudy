package com.liaoxilin.demo.dto;

import com.liaoxilin.demo.model.User;
import lombok.Data;

@Data
public class QuestionDto {

    private Integer id;
    private String title;
    private String description;
    private String tags;
    private Long gmtCreate;
    private Long gmtModified;
    private Integer creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private Integer comment;
    private User user;
}
