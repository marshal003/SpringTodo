package com.hashedin;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

class ApplicationSecurity extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/api/**").permitAll();

		http.authorizeRequests().antMatchers("/", "/home").permitAll()
				.antMatchers("/secure/dashboard")
				.hasAuthority("PERM_READ_DASHBOARD").antMatchers("/assets/**")
				.permitAll().antMatchers("/manage/**").hasRole("SUPERADMIN")
				.anyRequest().fullyAuthenticated();

		http.formLogin().loginPage("/login").failureUrl("/login?error")
				.permitAll().and().logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.logoutSuccessUrl("/login");

		http.csrf().requireCsrfProtectionMatcher(
				new CsrfSecurityRequestMatcher());
	}
}

class CsrfSecurityRequestMatcher implements RequestMatcher {

	private Pattern allowedMethods = Pattern
			.compile("^(GET|HEAD|TRACE|OPTIONS)$");
	private RegexRequestMatcher unprotectedMatcher = new RegexRequestMatcher(
			"/api/.*", null, true);

	@Override
	public boolean matches(HttpServletRequest request) {
		if (allowedMethods.matcher(request.getMethod()).matches()) {
			return false;
		}
		return !(unprotectedMatcher.matches(request));
	}

}