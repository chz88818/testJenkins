package com.chz.jenkins;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class JenkinsApplication {

    @Value("${spring.application.name}")
    private String applicationName;
    public static void main(String[] args) {
        SpringApplication.run(JenkinsApplication.class, args);
    }

    @GetMapping("/get")
    public String testJenkins(){
        return applicationName;
    }
}
