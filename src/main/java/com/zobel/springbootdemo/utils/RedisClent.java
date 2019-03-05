
package com.zobel.springbootdemo.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class RedisClent {

    @Autowired
    private StringRedisTemplate redisTemplate;


    /**
     * 保存字符串，并设置有效时间
     */
    public boolean setValue(String key,String value,int seconds){
        try{
            redisTemplate.opsForValue().set(key,value,seconds, TimeUnit.SECONDS);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 保存字符串
     */
    public boolean setValue(String key,String value){
        try{
            redisTemplate.opsForValue().set("key",value);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * 获取字符串
     */
    public String getString(String key){
        return redisTemplate.opsForValue().get("key");
    }

    public boolean setList(String key, List list){

        return true;
    }


}
