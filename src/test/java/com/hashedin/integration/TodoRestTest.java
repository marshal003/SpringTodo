package com.hashedin.integration;

import static org.hamcrest.Matchers.hasSize;
import org.junit.Test;
import com.hashedin.BaseIntegrationTest;
import com.hashedin.service.model.TaskData;
import com.hashedin.service.model.UserData;
import com.jayway.restassured.path.json.JsonPath;

import static com.jayway.restassured.RestAssured.*;


public class TodoRestTest extends BaseIntegrationTest{

	
	@Test
	public void testGetTaskResource(){
		given()
			.with()
			.sessionId(login("user", "user123"))
			.when()
			.get("/api/tasks")
			.then()
			.statusCode(200)
			.body("$", hasSize(0));  // ""/"$" for root element
	}
	
	@Test
	public void testCreateUser(){
		UserData user = new UserData("vinit", "rai", "marshal@gmail.com");
	
		given()
			.sessionId(login("user", "user123"))
			.when().get("/api/users").then()
			.statusCode(200)
			.body("$", hasSize(0));
		
		given()
			.sessionId(login("user", "user123"))
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
		
		TaskData task = new TaskData("Sample Task", userId);
		
		String taskResponse = given()
								.sessionId(login("user", "user123"))
								.contentType("application/json")
								.body(task)
								.expect()
								.statusCode(200)
								.when()
								.post("/api/tasks")
								.andReturn()
								.body()
								.asString();
		
		path = new JsonPath(taskResponse);
		Long taskId =  path.getLong("taskId");
		
		removeTask(taskId);
		removeUser(userId);
	}

	private void removeUser(Long userId){
		given()
			.sessionId(login("user", "user123"))
			.delete("/api/users/" + userId)
			.then()
			.statusCode(200);
	}
	
	private void removeTask(Long taskId){
		given()
			.sessionId(login("user", "user123"))
			.delete("/api/tasks/" + taskId)
			.then()
			.statusCode(200);
	}

	private String addUser(UserData data){
		return given()
				.sessionId(login("user", "user123"))
				.contentType("application/json")
				.body(data)
				.expect()
				.statusCode(200)
				.when()
				.post("/api/users")
				.andReturn()
				.body().asString();
	}
}
