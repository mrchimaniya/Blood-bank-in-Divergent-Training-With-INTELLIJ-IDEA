package com.divergentsl.config;

import com.divergentsl.services.DonorUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	DonorUserDetailsService donorUserDetailsService;

	@Autowired
	private JwtAuthenticationEntryPoint entrypoint;
	
//	@Autowired
//	private AUTHORIZATION_FILTER jwtFilter;

	@Autowired
	private AuthenticationConfiguration auth;

		@Bean
		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			http
					.csrf().disable()
					.authorizeHttpRequests()

					.antMatchers("/donor/**").hasAnyAuthority("USER","ADMIN")
					.antMatchers("/admin/**").hasAuthority("ADMIN")

					.antMatchers("/").permitAll();

//					jwtFilter is Literal
			http.addFilter(new AUTHENTICATION_FILTER(authenticationManager(auth)))//once per req
				.addFilterBefore(new AUTHORIZATION_FILTER(), UsernamePasswordAuthenticationFilter.class)
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.exceptionHandling().authenticationEntryPoint(entrypoint);

			return http.build();
		}

		@Bean
		public AuthenticationManager authenticationManager(AuthenticationConfiguration auth) throws Exception {
			return auth.getAuthenticationManager();
		}

		@Bean
		public PasswordEncoder getPasswordEncoder()
		{
			return new BCryptPasswordEncoder();
		}
}

//old code

		/*
	//to tell where is our authentication objects or where is all details of tables
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService).passwordEncoder(getPasswordEncode());
	}

	//to control on all http requests
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http
		   .csrf().disable()
		   .cors().disable()
		   .authorizeRequests()
		   //.antMatchers("/admin/**").hasRole("ADMIN") // not working coz we are changing role into authority


				.antMatchers("/donor/**").hasAnyAuthority("USER","ADMIN")
		        .antMatchers("/admin/**").hasAuthority("ADMIN")

		   .antMatchers("/").permitAll()
		   .and()
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
	}  */
