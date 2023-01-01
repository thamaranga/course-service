package com.hasithat.courseservice.controller;

import com.hasithat.courseservice.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class TestController {

    @Autowired
    private CourseService courseService;

    Logger log= LoggerFactory.getLogger(TestController.class);

    @GetMapping("/logTest2")
    public String logTest(){
        log.trace("Log message from trace");
        log.debug("Log message from debug");
        log.info("Log message from info");
        log.warn("Log message from warn");
        log.error("Log message from error");
        courseService.logTest();
        return "Hello.....";
    }
}
