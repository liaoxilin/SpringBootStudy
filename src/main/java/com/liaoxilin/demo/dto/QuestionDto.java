package com.liaoxilin.demo.dto;

import com.liaoxilin.demo.model.User;
import lombok.Data;

@Data
public class QuestionDto {

    private Long id;
    private String title;
    private String description;
    private String tags;
    private Long gmtCreate;
    private Long gmtModified;
    private Long creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private Long comment;
    private User user;
}
