package com.example.hogwartsmybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import tk.mybatis.spring.annotation.MapperScan;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
//@MapperScan(basePackages = "generator/mapper")
public class HogwartsMybatisApplication {
    public static void main(String[] args) {
        SpringApplication.run(HogwartsMybatisApplication.class, args);
    }
}
