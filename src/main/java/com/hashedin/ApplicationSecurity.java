package com.hashedin;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

class ApplicationSecurity extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/", "/home").permitAll()
			.antMatchers("/secure/dashboard").hasAuthority("PERM_READ_DASHBOARD")
			.antMatchers("/assets/**").permitAll()
			.antMatchers("/manage/**").hasRole("SUPERADMIN")
			.anyRequest().fullyAuthenticated();
		
		http
			.formLogin()
				.loginPage("/login").failureUrl("/login?error").permitAll()
			.and()
				.logout().logoutRequestMatcher(
					new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login");
	}
}