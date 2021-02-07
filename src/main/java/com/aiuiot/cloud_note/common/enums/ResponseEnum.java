package com.aiuiot.cloud_note.common.enums;

/**
 * ResponseEnum响应枚举类
 * @author o0u0o
 * @date 2021/2/7 9:44 下午
 */
public enum ResponseEnum {

    ERROR(-1, "服务器错误"),

    SUCCESS(0, "成功"),

    PASSWORD_ERROR(1, "密码错误"),

    OLD_PASSWORD_ERROR(1, "原密码错误"),

    USERNAME_EXIST(2, "用户名已存在"),

    USERNAME_NOT_EXIST(3, "用户名不存在")
    ;

    /**
     * 响应码
     */
    Integer code;

    /**
     * 描述
     */
    String desc;

    ResponseEnum(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }


    public String getDesc() {
        return desc;
    }

}
