package com.divergentsl.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.divergentsl.services.AdminUserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {	

	@Autowired
	private JwtAuthenticationEntryPoint entrypoint;
	
	@Autowired
	private JwtAuthenticationFilter jwtFilter;
	
	@Autowired
	private AdminUserDetailsService customUserDetailsService;
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService);
	}
	 
	@Bean
	public PasswordEncoder getPasswordEncode()
	{
		return NoOpPasswordEncoder.getInstance();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {	    
	    http
		   .csrf().disable()
		   .cors().disable()
		   .authorizeRequests().antMatchers("/admin/adminlogin").permitAll()
		   .antMatchers("/token").permitAll()
		   //.antMatchers("/admin/**").hasRole("ADMIN") // not working coz we are changing role into authority
		   .antMatchers("/admin/**").hasAuthority("ADMIN")
		   .antMatchers("/").permitAll()
		   .and().formLogin();
	    
	    http
		   .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) 
		   .and()
		   .exceptionHandling().authenticationEntryPoint(entrypoint);
		
		//jwtFilter is Literal
		 http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationManagerBean();
	}
}