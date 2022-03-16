package com.practice.javatask.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.practice.javatask.exception.CustomAccessDeniedException;
import com.practice.javatask.filter.AuthorizationFilter;
import com.practice.javatask.filter.CustomAuthenticationFilter;
import com.practice.javatask.service.JavaTaskServiceImpl;

@Configuration
@EnableWebSecurity
public class ConfigSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JavaTaskServiceImpl javaTaskServiceImpl;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("configure auth");

		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("configure http");
		CustomAuthenticationFilter authenticattionFilter = new CustomAuthenticationFilter(authenticationManagerBean(),
				new CustomAccessDeniedException());
		AuthorizationFilter authorizationFilter = new AuthorizationFilter(javaTaskServiceImpl,
				new CustomAccessDeniedException());
		authenticattionFilter.setFilterProcessesUrl("/api/v1/login");
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("/api/v1/login/**", "/api/v1//token/refresh/**").permitAll();
		http.authorizeRequests().antMatchers("/api/v1/client/**").hasAnyAuthority("USER");
		http.authorizeRequests().antMatchers("/api/v1/agency/**").hasAnyAuthority("ADMIN");
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(authenticattionFilter);
		http.addFilterBefore(authorizationFilter, CustomAuthenticationFilter.class);
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public JWTConfig config() {
		return new JWTConfig();
	}

}
