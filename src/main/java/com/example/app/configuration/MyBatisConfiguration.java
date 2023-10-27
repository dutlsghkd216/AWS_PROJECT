package com.example.app.configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
public class MyBatisConfiguration {
    private final ApplicationContext applicationContext;

    public MyBatisConfiguration(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    @Bean
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }
    @Bean
    public DataSource dataSource(){
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws IOException{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath*:/mapper/*.xml"));
        sqlSessionFactoryBean.setConfigLocation(applicationContext.getResource("classpath:/config/config.xml"));

        try {
            SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBean.getObject();
//          DBML의 '_' 표기법을 카멜표기법으로 바낀다.
            sqlSessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
            return sqlSessionFactory;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
