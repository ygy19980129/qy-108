package com.aaa.ygy.service;

import com.aaa.ygy.base.BaseService;
import com.aaa.ygy.mapper.UserMapper;
import com.aaa.ygy.model.User;
import com.aaa.ygy.redis.RedisService;
import com.aaa.ygy.utils.IDUtils;
import com.aaa.ygy.vo.TokenVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.aaa.ygy.staticstatus.RedisProperties.*;

/**
 * @program: qy-108
 * @description:
 * @author: ygy
 * @create: 2020-05-15-2020/5/15 20:35
 */
@Service
public class LoginService extends BaseService<User> {

    @Autowired
    private UserMapper userMapper;

    /**
     * @Description: 执行登录操作
     * @Param: [user, redisService]
     * @return: com.aaa.ygy.vo.TokenVo
     * @Author: ygy
     * @Date: 2020/5/15 20:41
     */
    public TokenVo doLongin(User user, RedisService redisService){
        TokenVo tokenVo = new TokenVo();
        tokenVo.setIfSuccess(false);
        //1.判断(目前实现的是登录功能，也就是说用户在执行登录操作--->肯定没有token)
        if (null != user){
            // 可以继续往下去执行
            // 2.验证用户名和密码是否正确
            User u = userMapper.selectOne(user);
            // 3.判断如果从数据库中查询的user对象是否为null
            if (null !=u){
                //说明用户登录成功
                String token = IDUtils.getUUid();
                u.setToken(token);
                int updateResult = userMapper.updateByPrimaryKey(u);
                // 4.判断token是否更新成功
                if (updateResult > 0){
                    // 说明token更新成功(需要返回token)
                    // 需要给token设置一个失效时间(因为以后每一个方法都需要去查询token，也就是说必须要查询数据库)
                    // 就会大量影响效率(所以说直接存缓存)
                    String setResult = redisService.set(String.valueOf(u.getDeptId()), token, XX, EX, 1800);
                    if ("OK".equals(setResult.toUpperCase()) || "1".equals(setResult)){
                        return tokenVo.setIfSuccess(true).setToken(token).setRedisKey(String.valueOf(u.getDeptId()));
                    }
                }
            }
        }
        return tokenVo;
    }

}
