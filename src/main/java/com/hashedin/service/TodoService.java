package com.hashedin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hashedin.entity.TaskStatus;
import com.hashedin.service.exception.ResourceAlreadyExistException;
import com.hashedin.service.exception.ResourceNotFoundException;
import com.hashedin.service.model.CommentData;
import com.hashedin.service.model.TaskData;
import com.hashedin.service.model.TaskDetailsData;
import com.hashedin.service.model.UserData;

/**
 * @author marshal
 *
 */
@Service
public interface TodoService {

	/**
	 * Creates a new task based on the given task bean and returns the created
	 * Task details.
	 * 
	 * @param task
	 *            Task to be created.
	 * @return Details of newly created Task.
	 * @throws ResourceNotFoundException
	 *             If creating User is not found.
	 */
	public TaskDetailsData createTask(TaskData task)
			throws ResourceNotFoundException;

	/**
	 * Update task status based on taskId.
	 * 
	 * @param taskId
	 *            Id of task to be updated.
	 * @param status
	 *            updated status
	 * @throws ResourceNotFoundException
	 *             If taskId is invalid.
	 */

	public void updateTaskStatus(Long taskId, TaskStatus status)
			throws ResourceNotFoundException;

	/**
	 * Assign task to the given user.
	 * 
	 * @param taskId
	 *            Id of task to be assigned.
	 * @param userId
	 *            Id of user whom to assign.
	 * @throws ResourceNotFoundException
	 *             If either taskId or userId is invalid.
	 */
	public void assignedTask(Long taskId, Long userId)
			throws ResourceNotFoundException;

	/**
	 * Mark the status of task as closed.
	 * 
	 * @param taskId
	 *            Id of task to be closed.
	 * @throws ResourceNotFoundException
	 *             If taskId is invalid.
	 */

	public void closeTask(Long taskId) throws ResourceNotFoundException;

	/**
	 * Return all existing tasks. Each task will be a full object i.e will also
	 * have its relationships details.
	 * 
	 * @return List of task details.
	 */
	public List<TaskDetailsData> getAllTasks();

	/**
	 * Return Task details by taskId.
	 * 
	 * @param taskId
	 *            Id of task.
	 * @return task detail for the given taskId.
	 * @throws ResourceNotFoundException
	 *             If taskId is invalid.
	 */
	public TaskDetailsData getTaskById(Long taskId)
			throws ResourceNotFoundException;

	/**
	 * Delete a task.
	 * 
	 * @param taskId
	 *            Id of task to be deleted.
	 * @throws ResourceNotFoundException
	 *             If taskId is invalid.
	 */
	public void removeTask(Long taskId) throws ResourceNotFoundException;

	/**
	 * Add a comment on task. CommentData should also contain taskId(where
	 * comment has been made) and userId(whose has made the comment)
	 * 
	 * @param data
	 *            Comment to be added.
	 * @throws ResourceNotFoundException
	 *             If any of userId or taskId attribute of data is invalid.
	 */
	void addComment(CommentData data) throws ResourceNotFoundException;

	/**
	 * Return UserData bean after creating a new user.
	 * 
	 * @param user
	 *            Detail of User to be created.
	 * @return Details of newly created User.
	 * @throws ResourceAlreadyExistException
	 *             If user with same email Id already exists.
	 */
	public UserData addUser(UserData user) throws ResourceAlreadyExistException;

	/**
	 * @return List of all the existing Users.
	 */
	public List<UserData> getAllUser();

	/**
	 * Return User for the requested userId.
	 * 
	 * @param userId
	 *            Id of user.
	 * @return User data of the requested User.
	 * @throws ResourceNotFoundException
	 *             If userId is invalid.
	 */
	public UserData getUserById(Long userId) throws ResourceNotFoundException;

	/**
	 * Return user of the given emailId.
	 * 
	 * @param email
	 *            emailId of user
	 * @return User data whose email matches with the given emailId.
	 */
	public UserData getUserOfEmail(String email);

	/**
	 * Remove User permanently for the given userId
	 * 
	 * @param userId
	 *            Id of user to be removed.
	 * @throws ResourceNotFoundException
	 *             If userId is invalid
	 */
	public void removeUser(Long userId) throws ResourceNotFoundException;

	/**
	 * Remove all Todo tasks from the database. Helpful in writing test cases.
	 */
	public void removeAlltasks();
	
	/**
	 * Remove all Users from the database. Helpful in writing test cases.
	 */
	public void removeAllUsers();
	
}
