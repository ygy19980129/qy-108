package com.aaa.ygy.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @program: qy-108
 * @description:
 * @author: ygy
 * @create: 2020-05-15-2020/5/15 20:07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TokenVo implements Serializable {

    /**
     * 就是简单的token值
     */
    private String token;
    /**
     * 标识了方法是否执行成功
     */
    private Boolean ifSuccess;

    /**
     * 保存token的key值
     */
    private String redisKey;

}
