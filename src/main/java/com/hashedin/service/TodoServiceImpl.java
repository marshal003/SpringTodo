package com.hashedin.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hashedin.entity.Comment;
import com.hashedin.entity.Task;
import com.hashedin.entity.TaskStatus;
import com.hashedin.entity.User;
import com.hashedin.repository.CommentRepository;
import com.hashedin.repository.TaskRepository;
import com.hashedin.repository.UserRepository;
import com.hashedin.service.exception.ResourceAlreadyExistException;
import com.hashedin.service.exception.ResourceNotFoundException;
import com.hashedin.service.model.CommentData;
import com.hashedin.service.model.TaskRequestData;
import com.hashedin.service.model.TaskResponseData;
import com.hashedin.service.model.UserData;


@Service
public class TodoServiceImpl implements TodoService{

	@Autowired
	private TaskRepository taskRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CommentRepository commentRepo;
	
	@Override
	public TaskResponseData createTask(TaskRequestData taskData) throws ResourceNotFoundException {
		User user = getUserEntityById(taskData.getCreatedBy());
		Task task = new Task(taskData);
		task.setCreatedBy(user);
		return new TaskResponseData(taskRepo.save(task));
	}

	@Override
	public void updateTaskStatus(Long taskId, TaskStatus status) throws ResourceNotFoundException {
		Task existingTask = getTaskEntityById(taskId);
		existingTask.setStatus(status);
		taskRepo.save(existingTask);
	}

	@Override
	public void assignedTask(Long taskId, Long userId) throws ResourceNotFoundException {
		Task existingTask = getTaskEntityById(taskId);
		User existingUser = getUserEntityById(userId);
		existingTask.setAssignedTo(existingUser);
		taskRepo.save(existingTask);
	}

	@Override
	public void closeTask(Long taskId) throws ResourceNotFoundException {
		Task existingTask = getTaskEntityById(taskId);
		existingTask.setStatus(TaskStatus.CLOSED);
		taskRepo.save(existingTask);
	}

	@Override
	public UserData addUser(UserData userData) throws ResourceAlreadyExistException {
		User existingUserWithSameEmail = userRepo.findByEmail(userData.getEmail());
		if(existingUserWithSameEmail != null){
			throw new ResourceAlreadyExistException(String.format("User with email %s already exists", 
					userData.getEmail()));
		}
		
		// Create new User entity model
		User newUserEntity = new User(userData);
		
		// Save User entity into database
		User savedUserentity = userRepo.save(newUserEntity);
		
		return new UserData(savedUserentity);
	}

	@Override
	public List<TaskResponseData> getAllTasks() {
		List<Task> existingTasks = (List<Task>) taskRepo.findAll();
		List<TaskResponseData> responseData = new ArrayList<TaskResponseData>();
		for(Task task : existingTasks){
			responseData.add(new TaskResponseData(task));
		}
		return responseData;
	}

	@Override
	public TaskResponseData getTaskById(Long taskId) throws ResourceNotFoundException{
		Task existingTask = getTaskEntityById(taskId);
		return new TaskResponseData(existingTask);
	}

	@Override
	public void addComment(CommentData data) throws ResourceNotFoundException {
		User existingUser = getUserEntityById(data.getUserId());
		Task existingTask = getTaskEntityById(data.getTaskId());
		Comment comment = new Comment(data);
		comment.setCommentedBy(existingUser);
		comment.setCommentedOn(existingTask);
		
		// Save comment
		commentRepo.save(comment);
	}

	@Override
	public List<UserData> getAllUser() {
		List<User> existingUsers = (List<User>) userRepo.findAll();
		List<UserData> response = new ArrayList<UserData>();
		for(User user : existingUsers){
			response.add(new UserData(user));
		}
		return response;
	}

	@Override
	public UserData getUserById(Long userId) throws ResourceNotFoundException {
		User existingUser = getUserEntityById(userId);
		return new UserData(existingUser);
	}

	@Override
	public UserData getUserOfEmail(String email){
		User existingUser = userRepo.findByEmail(email);
		if(existingUser == null){
			return null;
		}
		return new UserData(existingUser);
	}

	// Helper method
	private User getUserEntityById(Long userId) throws ResourceNotFoundException{
		User user = userRepo.findOne(userId);
		if(user == null){
			throw new ResourceNotFoundException("Unable to find User with user_id " + 
						userId);
		}
		return user;
	}
	
	// Helper method
	private Task getTaskEntityById(Long taskId) throws ResourceNotFoundException{
		Task existingTask = taskRepo.findOne(taskId);
		if(existingTask == null){
			throw new ResourceNotFoundException("Unable to find Task with task_id " + 
					taskId);
		}
		return existingTask;
	}

	@Override
	public void removeTask(Long taskId) throws ResourceNotFoundException {
		Task existingTask = getTaskEntityById(taskId);
		taskRepo.delete(existingTask.getTaskId());
	}

	@Override
	public void removeUser(Long userId) throws ResourceNotFoundException {
		User user = getUserEntityById(userId);
		userRepo.delete(user.getUserId());
	}
}
