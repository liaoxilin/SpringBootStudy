package com.liaoxilin.demo.dto;


import lombok.Data;

@Data
public class AccessTokenDto {
        private String cline_id;
        private String cline_secret;
        private String code;
        private String redirect_uri;
        private String state;


}

