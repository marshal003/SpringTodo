package com.hashedin.integration;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import com.hashedin.BaseIntegrationTest;


/* This Integration test is depends on - Google's RestAssured library
 * Please refer the link for more details - 
 * {@link https://code.google.com/p/rest-assured/wiki/Usage#Example_1_-_JSON}
 * {@link org.hamcrest}
 */
public class AuthorizationTests extends BaseIntegrationTest {
	
	
	@Test
	public void testGetUserResource(){
		given()
			.sessionId(login("user", "user123"))
			.when().get("/api/users")
			.then()
				.body("$", hasSize(0));
	}
	
	@Test
	public void testHomePageIsPublicallyAccessible() {
		given()
		.when().get("/")
		.then()
			.statusCode(200)
			.body(not(containsString("Please Login")));
	}
	
	@Test
	public void securePageMustNotBeAccessibleAnnonymously() {
		given()
		.when().get("/secure/dashboard")
		.then()
			.body(containsString("Please Login"));
	}
	
	@Test
	public void superAdminCanAccessManagementAPIs() {
		given()
			.sessionId(login("superadmin", "superadmin"))
		.when().get("/manage/mappings")
		.then()
			.statusCode(200)
			.body(not(containsString("Access is denied")));
		
	}
}
