package com.atguigu.jxc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.atguigu.jxc.dao")
public class JxcApplication {
	public static void main(String[] args) {
		System.out.println("IDEA测试");
		SpringApplication.run(JxcApplication.class, args);
	}

}
