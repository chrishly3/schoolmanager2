package com.schoolmanager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class AppController {
		
		//测试代码提交
    @RequestMapping(value = "/caseTest",method = RequestMethod.GET)
    public String caseTest(){
        System.err.println("1111111111111");
        return  "111";
    }
}
