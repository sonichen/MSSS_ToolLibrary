package com.msss;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.msss.mapper")
@SpringBootApplication
public class MsssApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsssApplication.class, args);
	}

}
