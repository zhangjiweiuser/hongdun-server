package com.hongdun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hongdun.dao")
public class HongdunServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(HongdunServiceApplication.class, args);
    }

}
