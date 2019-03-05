package com.zobel.springbootdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExcptionController {


    @RequestMapping("errorexception")
    public Object exctption() {
        int a = 1/0;
        return "123";
    }

}
