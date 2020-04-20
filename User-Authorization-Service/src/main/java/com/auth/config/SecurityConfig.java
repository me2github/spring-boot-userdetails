package com.auth.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	@Qualifier("datasource")
	private DataSource dataSource;

	//@Autowired
	//public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	//	auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");

//        auth.jdbcAuthentication().dataSource(dataSource)
//        .authoritiesByUsernameQuery("select username, userrole FROM users where username= ?")
//        .usersByUsernameQuery("select username, password,1 FROM users where username = ?");    

	//}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests().anyRequest().authenticated();

		http.csrf().disable();

	}
}
