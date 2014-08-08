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
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Main {

	public static void main(String args[]) {
		SpringApplication.run(Main.class, args);
	}
	
	//TODO - Remove hard-coded credentials
	@Bean
	public RestTemplate getRestTemplate() {
		BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(new AuthScope("art-vista.myshopify.com", -1), 
				new UsernamePasswordCredentials("6a0973890681db359599327965fd6ebf", "470d46d724c8c722be5d09273c3bdadf"));
		
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		HttpClient httpClient = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).build();
		factory.setHttpClient(httpClient);
		
		return new RestTemplate(factory);
	}
}
