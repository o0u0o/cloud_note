package com.aiuiot.cloud_note.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * <h1>用户视图对象</h1>
 * ClassName: UserVO
 * Description:
 *
 * @Author o0u0o
 * @Date 2023/6/12 9:31 AM
 */
@Getter
@Setter
public class UserVO {
    //用户ID
    private Long id;

    //用户名
    private String name;

    //密码
    private String password;

    //令牌
    private String token;

    //昵称
    private String nick;
}
