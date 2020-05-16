package com.aaa.ygy.base;

import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @program: qy-108
 * @description: controller
 * @author: ygy
 * @create: 2020-05-12-2020/5/12 18:58
 */
public abstract class CommonController<T> extends BaseController {


    /** 
     * @Description:  钩子函数，在新增之前所执行的内容
     * @Param: 
     * @return:  
     * @Author: ygy
     * @Date: 2020/5/12 20:29 
     */
    protected void beforeAdd(Map map){
        
    }

    /**
     * @Description:  钩子函数，在新增之前所执行的内容
     * @Param:
     * @return:
     * @Author: ygy
     * @Date: 2020/5/12 20:30
     */
    protected void afterAdd(Map map) {

    }


    public abstract BaseService<T> getBaseService();

    /**
     * @Description: 新增数据
     * @Param:
     * @return:
     * @Author: ygy
     * @Date: 2020/5/12 20:33
     */
    public ResultData add(@RequestBody Map map){
        ResultData resultData = new ResultData();
        beforeAdd(map);
        T instance = getBaseService().newInstance(map);
        try {
            Integer insertResult = getBaseService().add(instance);
            if (insertResult > 0){
                //保存成功
                afterAdd(map);
                return loginSuccess();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return loginFailed();
    }

    /**
     * @Description: 通过主键删除数据
     * @Param:
     * @return:
     * @Author: ygy
     * @Date: 2020/5/12 20:48
     */
    public ResultData delete (Map map) {
        ResultData resultData = new ResultData();
        beforeAdd(map);
        T instance = getBaseService().newInstance(map);
        try {
            Integer delete = getBaseService().delete(instance);
            if (delete > 0){
                afterAdd(map);
                return loginSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginFailed();
    }

    /**
     * @Description:  通过主键批量删除
     * @Param:
     * @return:
     * @Author: ygy
     * @Date: 2020/5/12 21:04
     */
    public ResultData batchDelete (Map map){
        ResultData resultData = new ResultData();
        beforeAdd(map);
        List instance = (List) getBaseService().newInstance(map);
        try {
            Integer integer = getBaseService().bactchDelete(instance);
            if (integer > 0){
                afterAdd(map);
                return loginSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginFailed();
    }

    /**
     * @Description: 更新数据
     * @Param:
     * @return:
     * @Author: ygy
     * @Date: 2020/5/12 21:11
     */
    public ResultData update(@RequestBody Map map){
        ResultData resultData = new ResultData();
        beforeAdd(map);
        T instance = getBaseService().newInstance(map);
        try {
            Integer update = getBaseService().update(instance);
            if (update > 0 ){
                afterAdd(map);
                return loginSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginFailed();
    }

    /**
     * @Description: 批量更新数据
     * @Param:
     * @return:
     * @Author: ygy
     * @Date: 2020/5/12 21:33
     */
    public ResultData batchUpdate(@RequestBody Map map,Object[] ids){
        ResultData resultData = new ResultData();
        beforeAdd(map);
        T instance = getBaseService().newInstance(map);
        try {
            Integer integer = getBaseService().batchUpdate(instance, ids);
            if (integer > 0){
                afterAdd(map);
                return loginSuccess();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginFailed();
    }

    /** 
     * @Description:查询一条数据
     * @Param: 
     * @return:  
     * @Author: ygy
     * @Date: 2020/5/12 22:25 
     */
    public ResultData queryOne(Map map){
        ResultData resultData = new ResultData();
        beforeAdd(map);
        T instance = getBaseService().newInstance(map);
        try {
            T queryOne = getBaseService().queryOne(instance);
            if (null == queryOne || "".equals(queryOne)){
                afterAdd(map);
                return loginSuccess(queryOne);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginFailed();
    }

    /**
     * @Description:  条件查询
     * @Param:
     * @return:
     * @Author: ygy
     * @Date: 2020/5/12 22:36
     */
    public ResultData qureyList(Map map){
        ResultData resultData = new ResultData();
        beforeAdd(map);
        T instance = getBaseService().newInstance(map);
        try {
            List<T> queryList = getBaseService().queryList(instance);
            if (null == queryList || "".equals(queryList)){
                afterAdd(map);
                return loginSuccess(queryList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginFailed();
    }

    /**
     * @Description: 分页查询
     * @Param:
     * @return:
     * @Author: ygy
     * @Date: 2020/5/12 22:41
     */
    public ResultData queryListByPage(Map map,Integer pageNo, Integer pageSize){
        ResultData resultData = new ResultData();
        beforeAdd(map);
        T instance = getBaseService().newInstance(map);
        try {
            PageInfo<T> queryListByPage = getBaseService().queryListByPage(instance, pageNo, pageSize);
            if (null == queryListByPage || "".equals(queryListByPage)){
                afterAdd(map);
                return loginSuccess(queryListByPage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return loginFailed();
    }



    /**
     * @Description: 防止数据不安全，所以不能直接在controller某个方法中直接接收HttpServletRequest对象
     *      *      必须要从本地当前线程中获取对象
     * @Param:
     * @return:
     * @Author: ygy
     * @Date: 2020/5/12 20:46
     */
    public HttpServletRequest getServletRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes;
        if(requestAttributes instanceof ServletRequestAttributes) {
            servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
            return servletRequestAttributes.getRequest();
        }
        return null;
    }

    /**
     * @Description: 获取当前客户端的session对象(如果不存在，则会重新创建一个)
     * @Param:
     * @return:
     * @Author: ygy
     * @Date: 2020/5/12 20:47
     */
    public HttpSession getSession() {
        return getServletRequest().getSession();
    }

    /**
     * @Description: 获取当前客户端的session对象(如果不存在，则直接返回为null)
     * @Param:
     * @return:
     * @Author: ygy
     * @Date: 2020/5/12 20:47
     */
    public HttpSession getExistSession() {
        return getServletRequest().getSession(false);

    }

}
