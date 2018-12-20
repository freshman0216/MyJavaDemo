package com.shizir.spring;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.shizir.spring.sentinel.RtDegradeDemo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @SentinelResource("abc0")
    public String hello() {
        return "Hello";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "Hello test";
    }

    @RequestMapping(value = "/test2", method = RequestMethod.GET)
    public String test2() {
        RtDegradeDemo.initDegradeRule();
        return "Hello test2";
    }
}
