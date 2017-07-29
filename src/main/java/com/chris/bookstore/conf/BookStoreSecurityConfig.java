/**
 * Chris Book Store.
 */

package com.chris.bookstore.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.chris.bookstore.security.AuthFailureHandler;
import com.chris.bookstore.security.AuthSuccessHandler;
import com.chris.bookstore.security.CustomUserDetailsService;


/**
 * @author chris
 *
 */

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = CustomUserDetailsService.class)
public class BookStoreSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
	}

	@Autowired
	AuthFailureHandler authFailureHandler;

	@Autowired
	AuthSuccessHandler authSuccessHandler;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		   http.authorizeRequests()
//		   .antMatchers("/hello").access("hasRole('ROLE_ADMIN')")
		   .antMatchers("/admin/books/**").access("hasRole('ROLE_ADMIN')")
		   .antMatchers("/user/books/**").access("hasRole('ROLE_USER')")
		   .antMatchers("/api/admin/books/**").access("hasRole('ROLE_ADMIN')")
		   .antMatchers("/api/user/books/**").access("hasRole('ROLE_USER')")
		   .anyRequest().permitAll()
		   .and()
		     .formLogin().loginPage("/login")
		     .usernameParameter("username").passwordParameter("password")
		     
             .failureHandler(authFailureHandler)
             .successHandler(authSuccessHandler)

		   .and()
		     .logout().logoutSuccessUrl("/login?logout") 
		    .and()
		    .exceptionHandling().accessDeniedPage("/403")
		   .and()
		     .csrf().disable(); //disable csrf.  make it easy to login by Curl.
	}

	@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder();
	}
}




