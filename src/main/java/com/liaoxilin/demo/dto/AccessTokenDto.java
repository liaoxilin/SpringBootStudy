package com.liaoxilin.demo.dto;

public class AccessTokenDto {
        private String cline_id;
        private String cline_secret;
        private String code;
        private String redirect_uri;
        private String state;

    public String getCline_id() {
        return cline_id;
    }

    public void setCline_id(String cline_id) {
        this.cline_id = cline_id;
    }

    public String getCline_secret() {
        return cline_secret;
    }

    public void setCline_secret(String cline_secret) {
        this.cline_secret = cline_secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}

