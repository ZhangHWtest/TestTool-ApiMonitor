package com.apimonitor.httpclient.config;


import com.apimonitor.httpclient.quartz.DynamicSchedulerFactory;
import org.quartz.Scheduler;
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

    /**
     * SchedulerFactoryBean extends SchedulerAccessor implements FactoryBean<Scheduler>,BeanNameAware, ApplicationContextAware, InitializingBean, DisposableBean, SmartLifecycle {}
     * 实现FactoryBean接口：返回由FactoryBean创建的bean实例
     * 通过getObject方法可以获取的是它所生产的对象
     */
    @Bean
//    @Lazy
    public SchedulerFactoryBean scheduler(DataSource dataSource) throws Exception {
        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();

        factoryBean.setDataSource(dataSource);
        // 设置自动启动
        factoryBean.setAutoStartup(schedulerAutoStartup);

        return factoryBean;
    }


    /**
     * dynamic scheduler factory
     * 动态 定时任务 配置
     * DynamicSchedulerFactory实现：InitializingBean接口
     * 为bean提供了初始化方法的方式，它只包括afterPropertiesSet方法，凡是继承该接口的类，在初始化bean的时候都会执行该方法。
     */
    @Bean
    public DynamicSchedulerFactory dynamicSchedulerFactory(Scheduler scheduler) {
        DynamicSchedulerFactory schedulerFactory = new DynamicSchedulerFactory();
        schedulerFactory.setScheduler(scheduler);
        return schedulerFactory;
    }


}
