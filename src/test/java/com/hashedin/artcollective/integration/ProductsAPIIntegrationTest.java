package com.hashedin.artcollective.integration;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.Test;

import com.hashedin.artcollective.BaseIntegrationTest;
import com.jayway.restassured.authentication.FormAuthConfig;
import com.jayway.restassured.filter.session.SessionFilter;
import com.jayway.restassured.specification.RequestSpecification;

public class ProductsAPIIntegrationTest extends BaseIntegrationTest {
	
	RequestSpecification withShopper;
	
	@Before
	public void setup() {
		SessionFilter sessionFilter = new SessionFilter();
		given()
			.contentType("application/json")
			.auth().form("shopper", "shopper", new FormAuthConfig("/login", "username", "password"))
			.filter(sessionFilter)
		.when()
			.get("/login")
		.then()
			.statusCode(200);
		
		withShopper = given().filter(sessionFilter);
	}
	@Test
	public void test() {
		withShopper
		.when()
			.get("/api/products")
		.then()
			.statusCode(200)
			.header("content-type", printToConsole)
			.body(printToConsole)
			.root("data")
			.body("title", hasItems("iPhone", "Nexus"));
		
		
	}

}
