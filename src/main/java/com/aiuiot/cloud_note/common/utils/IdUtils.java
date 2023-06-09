package com.aiuiot.cloud_note.common.utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * <h1>基于hutool工具类封装雪花算法实现的工具类</h1>
 * ClassName: IdUtils
 * Description:
 *
 * @Author o0u0o
 * @Date 2023/6/8 8:35 PM
 */
public class IdUtils {
    private static Snowflake snowflake = IdUtil.getSnowflake();

    /**
     * 生成long 类型的ID
     * @return
     */
    public static Long getId() {
        return snowflake.nextId();
    }

    /**
     * 生成String 类型的ID
     * @return
     */
    public static String getIdStr() {
        return snowflake.nextIdStr();
    }

    public static void main(String[] args) {
        System.out.println(getId());
        System.out.println(getIdStr());
    }
}
