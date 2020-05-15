package com.shree.intergration;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan({"com.shree.intergration.model.mapper"})
@SpringBootApplication
public class ShreeApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShreeApiApplication.class, args);
    }

}
