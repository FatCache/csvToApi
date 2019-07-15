package com.example.demo.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Uses Spring Security to perform user authentication on RESTFul end-points
 * Two roles currently -> ADMIN && USER
 * @author aahmed
 *
 */
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("admin").password("{noop}password").roles("USER", "ADMIN");

    }
    
    // Secure the end-points with HTTP Basic authentication
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/init/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "blogpost/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/blogpost").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/blogpost/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/blogpost/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }

}
