package com.example.dataimport;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@MapperScan(basePackages = {"com.example.dataimport.mapper.*"})
@ServletComponentScan("com.example.dataimport.filter")
public class DataImportApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataImportApplication.class, args);
    }

}
