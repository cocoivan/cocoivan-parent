package com.cocoivan.account;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@MapperScan("com.cocoivan.*.mapper")
public class CocoivanAccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(CocoivanAccountApplication.class, args);
	}

}
