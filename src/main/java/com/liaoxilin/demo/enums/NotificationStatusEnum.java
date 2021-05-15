package com.liaoxilin.demo.enums;

public enum NotificationStatusEnum {
    UNREAD(0),
    READ(1);


    private int staus;

    public int getStaus() {
        return staus;
    }

    NotificationStatusEnum(int staus) {
        this.staus = staus;
    }
}
