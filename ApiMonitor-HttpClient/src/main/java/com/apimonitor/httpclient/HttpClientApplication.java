package com.apimonitor.httpclient;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author xuxueli 2018-10-28 00:38:13
 */
@SpringBootApplication
@MapperScan("com.apimonitor.httpclient.mapper")
public class HttpClientApplication{

	public static void main(String[] args) {
        SpringApplication.run(HttpClientApplication.class, args);
	}

}