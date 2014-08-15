package com.hashedin.artcollective;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@EnableWebSecurity
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Main extends WebMvcConfigurerAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);
	
	public static void main(String args[]) {
		//System.setProperty("spring.profiles.active", "prod");
		SpringApplication.run(Main.class, args);
		LOGGER.info("Started Application Art Collective");
	}
	
	//TODO - Remove hard-coded credentials
	@Bean
	public RestTemplate getRestTemplate() {
		BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(
				"6a0973890681db359599327965fd6ebf", 
				"470d46d724c8c722be5d09273c3bdadf");
		
		AuthScope authScope = new AuthScope("art-vista.myshopify.com", -1);
		credentialsProvider.setCredentials(authScope, credentials);
		
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		HttpClient httpClient = HttpClientBuilder
								.create()
								.setDefaultCredentialsProvider(credentialsProvider)
								.build();
		factory.setHttpClient(httpClient);
		
		return new RestTemplate(factory);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("login");
	}

	@Bean
	public ApplicationSecurity getApplicationSecurity() {
		return new ApplicationSecurity();
	}
	
	@Bean
	public AuthenticationSecurity authenticationSecurity() {
		return new AuthenticationSecurity();
	}

	protected static class ApplicationSecurity extends WebSecurityConfigurerAdapter {

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
				.antMatchers("/", "/home").permitAll()
				.antMatchers("/secure/artist").hasAuthority("PERM_READ_ARTIST_DASHBOARD")
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
	
	protected static class AuthenticationSecurity extends
			GlobalAuthenticationConfigurerAdapter {

		@Override
		public void init(AuthenticationManagerBuilder auth) throws Exception {
			
			/* 
			 * Always assign "permissions" to users
			 * See http://springinpractice.com/2010/10/27
			 * /quick-tip-spring-security-role-based-authorization-and-permissions/
			 */
			auth.inMemoryAuthentication()
					.withUser("superadmin").password("superadmin").roles("SUPERADMIN").and()
					.withUser("artist").password("artist")
						.authorities("PERM_READ_ARTIST_DASHBOARD").and()
					.withUser("shopper").password("shopper")
						.authorities("PERM_READ_CUSTOMER_ORDERS").and();
		}
	}

}
