package com.hashedin.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hashedin.BaseUnitTest;
import com.hashedin.entity.Task;
import com.hashedin.entity.TaskPriority;
import com.hashedin.entity.TaskStatus;
import com.hashedin.entity.User;

import static org.junit.Assert.*;

public class TaskRepositoryTest extends BaseUnitTest {

	@Autowired
	private TaskRepository repository;

	@Autowired
	private UserRepository userRepository;

	private User user;

	@Before
	public void setUp() {
		User newuser = new User("vinit", "rai", "vinitrai.marshal@gmail.com");
		user = userRepository.save(newuser);
	}

	@After
	public void cleanUp() {
		userRepository.delete(user.getUserId());
	}

	@Test
	public void testGetAll() {
		List<Task> tasks = (List<Task>) repository.findAll();
		assertEquals(tasks.size(), 0);
		List<Task> newtasks = new ArrayList<Task>();

		// Create new Tasks
		newtasks.add(new Task(user, "DummyTask1", TaskPriority.HIGH));
		newtasks.add(new Task(user, "DummyTask2", TaskPriority.MEDIUM));
		newtasks.add(new Task(user, "DummyTask3", TaskPriority.SHOWSTOPPER));

		// Save All tasks
		repository.save(newtasks);

		tasks = (List<Task>) repository.findAll();
		assertEquals(3, tasks.size());
		repository.delete(newtasks);
	}

	@Test
	public void testCreateBy() {

		assertNull(user.getTasksCreated());

		Task task = new Task(user, "DummyTask", TaskPriority.HIGH);
		Task newTask = repository.save(task);

		assertEquals(newTask.getTask(), "DummyTask");
		assertEquals(newTask.getCreatedBy(), user);

		user = userRepository.findOne(user.getUserId());
		assertNotNull(user.getTasksCreated());
		repository.delete(newTask.getTaskId());

	}

	@Test
	public void testSaveTask() {
		Task task = new Task(user, "DummyTask", TaskPriority.HIGH);
		Task newTask = repository.save(task);

		assertEquals(newTask.getTask(), "DummyTask");
		assertNotNull(newTask.getCreatedAt());
		assertNotNull(newTask.getUpdatedAt());
		assertEquals(newTask.getCreatedAt(), newTask.getUpdatedAt());
		assertEquals(TaskStatus.CREATED, newTask.getStatus());
		assertEquals(TaskPriority.HIGH, newTask.getPriority());

		repository.delete(newTask.getTaskId());
	}

}
