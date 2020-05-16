package com.aaa.ygy.base;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @program: qy-108
 * @description: data
 * @author: ygy
 * @create: 2020-05-12-2020/5/12 17:45
 */
@Data
@Accessors(chain = true)
public class ResultData<T> implements Serializable {

    private String code;
    private String msg;
    private String detail;
    private T data;

}
