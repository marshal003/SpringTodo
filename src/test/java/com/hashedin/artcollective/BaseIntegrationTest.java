package com.hashedin.artcollective;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.jayway.restassured.RestAssured;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@IntegrationTest("server.port=0") 
@WebAppConfiguration
public class BaseIntegrationTest {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseIntegrationTest.class);
	
	private static final String baseURI = "http://localhost";
	protected Printer printToConsole = new Printer();
	
	protected TestRestTemplate rest = new TestRestTemplate();
	
    @Value("${local.server.port}")   // 6
    protected int port;
	
	@Before
	public void _setUp() {
		RestAssured.baseURI = baseURI;
		RestAssured.port = port;
	}
	
	String getAbsoluteUrl(String relativeUrl) {
		return String.format("%s:%d%s", baseURI, port, relativeUrl);
	}
	
	protected String login(String username, String password) {
		/*
		 * Step 1: Fetch the login page, and extract csrf token
		 */
		String loginUrl = getAbsoluteUrl("/login");
		ResponseEntity<String> loginHtml = 
				rest.getForEntity(loginUrl, String.class);
		String csrfToken = extractCsrf(loginHtml.getBody());

		/*
		 * Step 2: POST credentials + csrf token to login
		 */
		MultiValueMap<String, String> loginParams = getLoginParameters(username, password, csrfToken);
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<?> entity = new HttpEntity<Object>(loginParams, headers);
		ResponseEntity<String> postResponse = 
				rest.exchange(loginUrl, HttpMethod.POST, entity, String.class);
		
		/*
		 * Step 3 : Extract Session ID and return
		 */
		Pattern sessionIDRegex = Pattern.compile(".*JSESSIONID=(\\w+).*");
		List<String> cookies = postResponse.getHeaders().get("Set-Cookie");
		for(String cookie : cookies) {
			Matcher matcher = sessionIDRegex.matcher(cookie);
			if(matcher.find()) {
				return matcher.group(1);
			}
		}
		
		throw new IllegalStateException("Could not find JSESSIONID even though login was successful");
	}

	protected MultiValueMap<String, String> getLoginParameters(String username, String password, String csrfToken) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("username", username);
		params.add("password", password);
		params.add("_csrf", csrfToken);
		return params;
	}
	
	private String extractCsrf(String loginHtml) {
		String regex = ".*input.*csrf.*value=\"([A-Za-z0-9-]+)\".*";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(loginHtml);
		if(matcher.find()) {
			return matcher.group(1);
		}
		else {
			String error = String.format("Could not extract CSRF token from login page HTML %s", loginHtml);
			LOGGER.error(error);
			throw new RuntimeException(error);
		}
	}
}


class Printer extends BaseMatcher<Object> {

	@Override
	public boolean matches(Object item) {
		System.out.println("PRINTER: " + item);
		return true;
	}

	@Override
	public void describeTo(Description description) {
		
	}
	
}