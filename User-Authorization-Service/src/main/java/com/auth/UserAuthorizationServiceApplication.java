package com.auth;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

//@EnableFeignClients
@ComponentScan("com.auth")
@SpringBootApplication
public class UserAuthorizationServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAuthorizationServiceApplication.class, args);
	}

//	@Bean
//	@Primary
//	@ConfigurationProperties("app.datasource")
//	public DataSourceProperties dataSourceProperties() {
//		return new DataSourceProperties();
//	}
//
//	@Bean(value = "datasource")
//	@ConfigurationProperties("app.datasource")
//	public DataSource dataSource(DataSourceProperties properties) {
//		return properties.initializeDataSourceBuilder().build();
//	}

    @Bean(value = "datasource")
    @ConfigurationProperties("app.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
    

}
