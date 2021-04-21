package com.liaoxilin.demo.model;


import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private String accountId;
    private  String token;
    private Long getCreate;
    private Long getModified;
    private String avatarUrl;


}
