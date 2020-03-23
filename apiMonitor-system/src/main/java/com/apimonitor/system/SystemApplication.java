package com.apimonitor.system;

import com.apimonitor.system.utils.JwtUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
@MapperScan("com.apimonitor.system.mapper")
public class SystemApplication {
    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }

    @Bean
    public JwtUtils jwtUtils(){
        return  new JwtUtils();
    }

    @Bean
    public BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    //解决 no session问题
//    @Bean
//    public OpenEntityManagerInViewFilter openEntityManagerInViewFilter(){
//        return  new OpenEntityManagerInViewFilter();
//    }
}
