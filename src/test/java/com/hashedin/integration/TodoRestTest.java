package com.hashedin.integration;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import groovy.time.BaseDuration.From;

import org.junit.Test;

import com.hashedin.BaseIntegrationTest;
import com.hashedin.entity.User;
import com.hashedin.service.model.TaskRequestData;
import com.hashedin.service.model.UserData;
import com.jayway.restassured.path.json.JsonPath;

import static com.jayway.restassured.RestAssured.*;


public class TodoRestTest extends BaseIntegrationTest{

	
	@Test
	public void testGetTaskResource(){
		given()
		.when().get("/api/tasks")
			.then()
			.body("$", hasSize(0));  // ""/"$" for root element
	}
	
	@Test
	public void testCreateUser(){
		UserData user = new UserData("vinit", "rai", "marshal@gmail.com");
	
		given()
			.when().get("/api/users").then()
			.statusCode(200)
			.body("$", hasSize(0));
		
		given()
			.contentType("application/json")
			.when()
			.body(user)
			.post("/api/users")
			.then()
			.statusCode(200);
	}

	@Test
	public void testCreateTask(){
		UserData user = new UserData("vinit", "rai", "marshalll@gmail.com");
		
		String response = addUser(user);
		JsonPath path = new JsonPath(response);
		Long userId = path.getLong("userId");
		
		TaskRequestData task = new TaskRequestData("Sample Task", userId);
		
		String taskResponse = given()
								.contentType("application/json")
								.when()
								.body(task)
								.post("/api/tasks")
								.then().statusCode(200)
								.extract()
								.asString();
		
		path = new JsonPath(taskResponse);
		Long taskId =  path.getLong("taskId");
		
		removeTask(taskId);
		removeUser(userId);
	}

	private void removeUser(Long userId){
		given().delete("/api/users/" + userId)
			.then()
			.statusCode(200);
	}
	
	private void removeTask(Long taskId){
		given().delete("/api/tasks/" + taskId)
		.then()
		.statusCode(200);
	}

	private String addUser(UserData data){
		return given()
				.contentType("application/json")
				.body(data)
				.post("/api/users")
				.andReturn()
				.body().asString();
	}
}
