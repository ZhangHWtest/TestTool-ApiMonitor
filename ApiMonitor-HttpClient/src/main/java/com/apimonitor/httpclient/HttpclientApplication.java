package com.apimonitor.httpclient;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@MapperScan("com.apimonitor.common.mapper")
public class HttpclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommonApplication.class, args);
		//ThreadManager.getExecutorInstance().execute(new EthereumListenerRunnable());
	}
}
