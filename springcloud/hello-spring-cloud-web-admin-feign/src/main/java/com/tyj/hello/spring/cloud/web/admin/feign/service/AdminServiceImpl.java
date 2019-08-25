package com.tyj.hello.spring.cloud.web.admin.feign.service;

import org.springframework.stereotype.Component;

@Component
public class AdminServiceImpl implements AdminService {
    @Override
    public String sayHi(String message) {
        return "your are message is :"+message+" but request bad";
    }
}
