package com.adrianlui.letsplay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class LetsPlayApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(LetsPlayApplication.class, args);
    }
}
