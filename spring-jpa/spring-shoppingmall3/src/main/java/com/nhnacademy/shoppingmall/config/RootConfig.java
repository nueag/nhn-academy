package com.nhnacademy.shoppingmall.config;

import com.nhnacademy.shoppingmall.Base;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackageClasses = Base.class,
        excludeFilters = @ComponentScan.Filter(Controller.class))
public class RootConfig {
    @Bean
    public DataSource dataSource() {
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName("org.h2.Driver");
//        dataSource.setUrl("jdbc:h2:~/spring-jpa;DATABASE_TO_UPPER=false;"
//                + "INIT=RUNSCRIPT FROM 'classpath:/script/shopping_mall.sql'");
//        dataSource.setUsername("sa");
//        dataSource.setPassword("");
//
//        dataSource.setInitialSize(10);
//        dataSource.setMaxTotal(10);
//        dataSource.setMinIdle(10);
//        dataSource.setMaxIdle(10);
//
//        dataSource.setMaxWaitMillis(1000);
//
//        dataSource.setTestOnBorrow(true);
//        dataSource.setTestOnReturn(true);
//        dataSource.setTestWhileIdle(true);
        BasicDataSource basicDataSource = new BasicDataSource();

        try {
            basicDataSource.setDriver(new com.mysql.cj.jdbc.Driver());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        basicDataSource.setUrl("jdbc:mysql://133.186.241.167:3306/nhn_academy_31");
        basicDataSource.setUsername("nhn_academy_31");
        basicDataSource.setPassword("3u.SF)xeXWIfzB[Y");

        basicDataSource.setInitialSize(5);
        basicDataSource.setMaxTotal(5);
        basicDataSource.setMaxIdle(5);
        basicDataSource.setMinIdle(5);

        basicDataSource.setMaxWaitMillis(1000);
        basicDataSource.setValidationQuery("select 1");
        basicDataSource.setTestOnBorrow(true);

        return basicDataSource;
    }

}
