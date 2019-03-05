package com.zobel.springbootdemo.common.db.annotation;

import com.zobel.springbootdemo.common.MybatisConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhaoZhongbo
 * @date 2019/3/4 22:00
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SwitchDataSource {
    public String[] value() default {MybatisConfig.MASTERDATASOURCE};
}
