package com.hasithat.courseservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//Below annotaion is for enabling swagger doc
@OpenAPIDefinition(info = @Info(title="COURSE SERVICE", version = "1.0" , description = "Course Service Operations"))
public class CourseServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourseServiceApplication.class, args);
    }

}
