package com.example.clouddisk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;



@ServletComponentScan
@SpringBootApplication
@MapperScan("com.example.clouddisk.mapper")
public class CloudDiskApplication {

    public static void main(String[] args) {
        SpringApplication.run(
                CloudDiskApplication.class, args);
    }

}
