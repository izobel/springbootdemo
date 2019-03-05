package com.zobel.springbootdemo.controller;

import com.zobel.springbootdemo.domain.User2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class GetController {


    @Autowired
    private StringRedisTemplate redisTemplate;


    private Logger logger = LoggerFactory.getLogger(this.getClass());


    Map<String,Object> result = new HashMap<>();

    @GetMapping("log")
    public Object flagLog(){
        logger.info("info log");
        logger.warn("warn log");
        logger.error("error log");

        return "123";
    }


    @RequestMapping(path = "/{city_id}/{user_id}",method = RequestMethod.GET)
    public Object findUser(@PathVariable("city_id") String cityId,
                           @PathVariable("user_id") String userId){
        result.clear();
        result.put("cityId", cityId);
        result.put("userId", userId);
        return result;
    }

    @GetMapping(value = "/v1/page_user1")
    public Object getMapping(@RequestParam(defaultValue = "0", name = "page") int form, int size){
        result.clear();
        result.put("form",form);
        result.put("size",size);

        return result;
    }

    @GetMapping(value = "/testjson")
    public Object testJson(){
//        redisTemplate.opsForValue().set("university","sit");
        return new User2("星光 ","123456","12",new Date());
    }
}
