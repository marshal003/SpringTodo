package com.hashedin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hashedin.entity.TaskStatus;
import com.hashedin.service.TodoService;
import com.hashedin.service.exception.ResourceAlreadyExistException;
import com.hashedin.service.exception.ResourceNotFoundException;
import com.hashedin.service.model.TaskRequestData;
import com.hashedin.service.model.TaskResponseData;
import com.hashedin.service.model.UserData;


@RestController
public class TodoAPIs {

	@Autowired
	private TodoService todoService;
	
	// Get All existing tasks
	@RequestMapping(value = "api/tasks", method = RequestMethod.GET)
	public Iterable<TaskResponseData> getAllTasks(){
		return todoService.getAllTasks();
	}
	
	// Create new Task
	@RequestMapping(value="api/tasks", method = RequestMethod.POST)
	public TaskResponseData createTask(@RequestBody TaskRequestData data) 
			throws ResourceNotFoundException{ // Return Resource Not Found
		return todoService.createTask(data); 
	}

	// Get a particular Task
	@RequestMapping(value = "api/tasks/{taskId:[0-9]+}", method = RequestMethod.GET)
	public TaskResponseData getTaskById(@PathVariable("taskId") Long taskId) 
			throws ResourceNotFoundException{
		return todoService.getTaskById(taskId);	
	}
	
	// Get a particular Task
	@RequestMapping(value = "api/tasks/{taskId:[0-9]+}", method = RequestMethod.DELETE)
	public void deleteTaskById(@PathVariable("taskId") Long taskId) 
			throws ResourceNotFoundException{
		todoService.removeTask(taskId);	
	}
	
	// Assign Task to User
	@RequestMapping(value = "api/tasks/{taskId:[0-9]+}/assign", method = RequestMethod.PATCH)
	public void assignTask(@PathVariable("taskId") Long taskId, 
			@RequestParam(value = "to") Long userId) 
			throws ResourceNotFoundException {
		todoService.assignedTask(taskId, userId);
	}
	
	// Update Task Status
	@RequestMapping(value = "api/tasks/{taskId:[0-9]+}/update-status", method = RequestMethod.PATCH)
	public void updateTaskStatus(@PathVariable("taskId") Long taskId, 
			@RequestParam(value = "status") String status) 
			throws ResourceNotFoundException {
		TaskStatus taskStatus = TaskStatus.get(status);
		todoService.updateTaskStatus(taskId, taskStatus);
	}
	
	// Explicitly close the task. Note this can also be done using update task status.
	@RequestMapping(value = "api/tasks/{taskId:[0-9]+}/close", method = RequestMethod.PATCH)
	public void closeTask(@PathVariable("taskId") Long taskId) 
			throws ResourceNotFoundException {
		todoService.closeTask(taskId);
	}
	
	// Create new User 
	@RequestMapping(value = "api/users", method = RequestMethod.POST)
	public UserData createUser(@RequestBody UserData userData) 
			throws ResourceAlreadyExistException{
		return todoService.addUser(userData);
	}

	// Get All Users
	@RequestMapping(value = "api/users", method = RequestMethod.GET)
	public Iterable<UserData> getAllUsers(){
		return todoService.getAllUser();
	}

	@RequestMapping(value = "api/users/{userId:[0-9]+}", method = RequestMethod.POST)
	public UserData createUser(@PathVariable("userId") Long userId) 
			throws ResourceAlreadyExistException, ResourceNotFoundException{
		return todoService.getUserById(userId);
	}
	
	@RequestMapping(value = "api/users/{userId:[0-9]+}", method = RequestMethod.DELETE)
	public void removeUserById(@PathVariable("userId") Long userId) 
			throws ResourceAlreadyExistException, ResourceNotFoundException{
		todoService.removeUser(userId);
	}
	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public String handleResourceNotExistsException(Exception e){
		return e.getMessage();
	}
	
	@ExceptionHandler(ResourceAlreadyExistException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public String handleResourceAlreadyExistsException(Exception e){
		return e.getMessage();
	}
	
}
