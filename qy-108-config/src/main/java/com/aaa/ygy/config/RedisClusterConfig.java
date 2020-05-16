package com.aaa.ygy.config;

import com.aaa.ygy.properties.RedisClusterProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;

/**
 * @program: qy-108
 * @description: RedisClusterConfig
 * @author: ygy
 * @create: 2020-05-13-2020/5/13 20:51
 */
@Configuration
public class RedisClusterConfig {

    @Autowired
    private RedisClusterProperties redisClusterProperties;

    /**
     * @Description:  注入redis为java所提供的API(jedisCluster)
     * @Param:
     * @return:
     * @Author: ygy
     * @Date: 2020/5/13 20:53
     */

    @Bean
    public JedisCluster getJedisCluster(){

        //获取Redis的IP地址及端口号信息
        String nodes = redisClusterProperties.getNodes();
        //分割nodes，分割成一个一个的IP：端口的格式
        String[] nodeArr = nodes.split(",");
        HashSet<HostAndPort> hostAndPortSet  = new HashSet<HostAndPort>();
        //循环分割后的数据
        for (String node : nodeArr){
            //再次分割，把IP地址和端口号分割
            String[] hosPort = node.split(":");
            //创建Redis所需要的HostAndPort对象
            HostAndPort hostAndPort = new HostAndPort(hosPort[0], Integer.parseInt(hosPort[1]));
            //把HostAndPort对象存入到hostAndPortSet集合中
            hostAndPortSet.add(hostAndPort);
        }
        //创建JedisCluster对象
        JedisCluster jedisCluster = new JedisCluster(hostAndPortSet,redisClusterProperties.getCommmandTimeout(),redisClusterProperties.getMaxAttempts());
        //返回JedisCluster对象
        return jedisCluster;
    }

}
