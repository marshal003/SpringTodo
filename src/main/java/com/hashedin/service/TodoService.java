package com.hashedin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hashedin.entity.Task;
import com.hashedin.entity.TaskStatus;
import com.hashedin.entity.User;
import com.hashedin.service.exception.ResourceAlreadyExistException;
import com.hashedin.service.exception.ResourceNotFoundException;
import com.hashedin.service.model.CommentData;
import com.hashedin.service.model.TaskRequestData;
import com.hashedin.service.model.TaskResponseData;
import com.hashedin.service.model.UserData;


@Service
public interface TodoService {

	// Task Services
	public TaskResponseData createTask(TaskRequestData task) throws ResourceNotFoundException;
	public void updateTaskStatus(Long taskId, TaskStatus status) throws ResourceNotFoundException;
	public void assignedTask(Long taskId, Long userId) throws ResourceNotFoundException;
	public void closeTask(Long taskId) throws ResourceNotFoundException;
	public List<TaskResponseData> getAllTasks();
	public TaskResponseData getTaskById(Long taskId) throws ResourceNotFoundException;
	public void removeTask(Long taskId)  throws ResourceNotFoundException;

	// Comment Services
	void addComment(CommentData data) throws ResourceNotFoundException;
	
	// User Services
	public UserData addUser(UserData user) throws ResourceAlreadyExistException;
	public List<UserData> getAllUser();
	public UserData getUserById(Long userId) throws ResourceNotFoundException;
	public UserData getUserOfEmail(String email);
	public void removeUser(Long userId)throws ResourceNotFoundException;
	
}
