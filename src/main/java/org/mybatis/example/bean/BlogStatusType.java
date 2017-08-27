package org.mybatis.example.bean;

import org.mybatis.example.type_handler.BaseCodeEnum;

public enum BlogStatusType implements BaseCodeEnum {
    NORMAL("normal", "正常"),
    DELETE("delete", "已删除"),
    CANCEL("cancel", "已取消");

    private String code;
    private String description;

    BlogStatusType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String toString() {
        return this.code;
    }

    @Override
    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
