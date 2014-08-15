package com.hashedin.artcollective;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.RestAssured;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
@IntegrationTest("server.port=0") 
@WebAppConfiguration
public class BaseIntegrationTest {

	protected Printer printToConsole = new Printer();
	
    @Value("${local.server.port}")   // 6
    protected int port;
	
	@Before
	public void _setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
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