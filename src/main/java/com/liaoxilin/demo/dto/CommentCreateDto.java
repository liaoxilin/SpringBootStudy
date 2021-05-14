package com.liaoxilin.demo.dto;


import lombok.Data;

@Data
public class CommentCreateDto {
    private Long parentId;
    private String content;
    private Integer type;
}
