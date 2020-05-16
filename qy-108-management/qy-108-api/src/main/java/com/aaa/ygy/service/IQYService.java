package com.aaa.ygy.service;

import com.aaa.ygy.model.User;
import com.aaa.ygy.vo.TokenVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @program: qy-108
 * @description:
 * @author: ygy
 * @create: 2020-05-15-2020/5/15 19:10
 */
@FeignClient(value = "system-interface")
public interface IQYService {

    @PostMapping("/doLogin")
    TokenVo doLogin(@RequestBody User user);

}
