package com.liaoxilin.demo.model;

public class User {
    private Integer id;
    private String name;
    private String accountId;
    private  String token;
    private Long getCreate;
    private Long getModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getGetCreate() {
        return getCreate;
    }

    public void setGetCreate(Long getCreate) {
        this.getCreate = getCreate;
    }

    public Long getGetModified() {
        return getModified;
    }

    public void setGetModified(Long getModified) {
        this.getModified = getModified;
    }
}
