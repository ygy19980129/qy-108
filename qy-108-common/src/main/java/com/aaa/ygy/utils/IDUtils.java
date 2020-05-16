package com.aaa.ygy.utils;

import java.util.UUID;

/**
 * @program: qy-108
 * @description:
 * @author: ygy
 * @create: 2020-05-15-2020/5/15 18:19
 */
public class IDUtils {

    private IDUtils(){

    }

    /**
     * @Description: 获取uuid
     * @Param: []
     * @return: java.lang.String
     * @Author: ygy
     * @Date: 2020/5/15 18:21
     */
    public static String getUUid(){
        return UUID.randomUUID().toString().replace("_","");
    }

}
