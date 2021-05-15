package com.liaoxilin.demo.dto;

import com.liaoxilin.demo.model.User;
import lombok.Data;

@Data
public class NotificationDto {
    private Long id;
    private Long gmtCreate;
    private Integer status;
    private String notifierName;
    private String outerTitle;
    private String typeName;
    private Long outerid;
    private Integer type;
}
