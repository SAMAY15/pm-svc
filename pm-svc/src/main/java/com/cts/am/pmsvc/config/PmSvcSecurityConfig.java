package com.cts.am.pmsvc.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import com.cts.am.pmsvc.service.PmSvcUserDetailsService;

@Configuration
@EnableWebSecurity
public class PmSvcSecurityConfig  extends WebSecurityConfigurerAdapter{

	private final String projectweeklyQuery = "select * from project_weekly_table where Project_ID=?";
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private PmSvcUserDetailsService pmsvcUserDetailsService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(pmsvcUserDetailsService);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//set your jdbc configuration using the auth object
		auth.jdbcAuthentication().usersByUsernameQuery(projectweeklyQuery).dataSource(dataSource);
	}
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
		
		@Override
	    protected void configure(HttpSecurity http) throws Exception{
	        http.cors().and().csrf().disable();
	    }
		
		@Bean
	    public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl db = new JdbcTokenRepositoryImpl();
		db.setDataSource(dataSource);
		
		return db;

	}
}
