package com.tyj.hello.spring.cloud.web.admin.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "hello-spring-cloud-service-admin" , fallback = AdminServiceImpl.class)
@Component
public interface AdminService {
    @RequestMapping(value = "hi" , method = RequestMethod.GET)
    public String sayHi(@RequestParam(value="message")String message);
}
