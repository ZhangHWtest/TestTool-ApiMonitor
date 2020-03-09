package com.apimonitor.common.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;

@Configuration
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class ScheduleFactoryConfig {

    @Value("${scheduler.auto.startup}")
    private boolean schedulerAutoStartup;


    @Bean
//    @Lazy
    public SchedulerFactoryBean scheduler(DataSource dataSource) throws Exception {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();

        factoryBean.setDataSource(dataSource);
        factoryBean.setAutoStartup(schedulerAutoStartup);

        return factoryBean;
    }




}
