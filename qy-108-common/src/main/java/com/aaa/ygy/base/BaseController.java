package com.aaa.ygy.base;

import static com.aaa.ygy.status.LoginStatus.*;

/**
 * @program: qy-108
 * @description: contorlle
 * 通用controller实现consumer和providier的统一返回值
 * @author: ygy
 * @create: 2020-05-12-2020/5/12 17:40
 */
public class BaseController {
    
    /** 
     * @Description:  登录成功，使用系统消息
     * @Param: 
     * @return:  
     * @Author: ygy
     * @Date: 2020/5/12 17:55 
     */
    protected ResultData loginSuccess(){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(LOGIN_SUCCESS.getMsg());
        return resultData;
    }

    /** 
     * @Description:  登录成功，使用自定义消息
     * @Param: 
     * @return:  
     * @Author: ygy
     * @Date: 2020/5/12 18:00 
     */
    protected ResultData loginSuccess(String msg){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(msg);
        return resultData;
    }

    /** 
     * @Description:  登录成功，使用系统消息，自定义返回值
     * @Param: 
     * @return:  
     * @Author: ygy
     * @Date: 2020/5/12 18:01 
     */
    protected ResultData loginSuccess(Object data){
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(LOGIN_SUCCESS.getMsg());
        resultData.setData(data);
        return resultData;
    }

    /** 
     * @Description:   登录成功，自定义消息，自定义返回值
     * @Param: 
     * @return:  
     * @Author: ygy
     * @Date: 2020/5/12 18:01
     */
    protected ResultData loginSuccess(String msg, Object data) {
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_SUCCESS.getCode());
        resultData.setMsg(msg);
        resultData.setData(data);
        return resultData;
    }

    /** 
     * @Description:  登录失败，使用系统消息
     * @Param: 
     * @return:  
     * @Author: ygy
     * @Date: 2020/5/12 18:03 
     */
    protected ResultData loginFailed() {
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_FAILED.getCode());
        resultData.setMsg(LOGIN_FAILED.getMsg());
        return resultData;
    }

    /**
     * @Description: 登录失败，使用自定义消息
     * @Param:
     * @return:
     * @Author: ygy
     * @Date: 2020/5/13 9:33
     */
    protected ResultData loginFailed(String msg) {
        ResultData resultData = new ResultData();
        resultData.setCode(LOGIN_FAILED.getCode());
        resultData.setMsg(msg);
        return resultData;
    }


}
