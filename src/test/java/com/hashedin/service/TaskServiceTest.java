package com.hashedin.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;

import com.hashedin.BaseUnitTest;
import com.hashedin.entity.TaskPriority;
import com.hashedin.entity.TaskStatus;
import com.hashedin.service.exception.ResourceAlreadyExistException;
import com.hashedin.service.exception.ResourceNotFoundException;
import com.hashedin.service.model.CommentData;
import com.hashedin.service.model.TaskData;
import com.hashedin.service.model.TaskDetailsData;
import com.hashedin.service.model.UserData;

public class TaskServiceTest extends BaseUnitTest {

	@Autowired
	private TodoService todoService;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void setUp() throws ResourceNotFoundException {
		UserData data = todoService.getUserOfEmail("marshal@gmail.com");
		if (data != null) {
			todoService.removeUser(data.getUserId());
		}
	}

	@After
	public void tearDown() {
		todoService.removeAlltasks();
		todoService.removeAllUsers();
	}

	public TaskData createTask(String task, Long userId) {
		TaskData data = new TaskData(task, userId);
		data.setDueDate(new DateTime());
		data.setNotes("This is a Sample Test Task");
		return data;
	}

	@Test
	public void testTwoUserCannotHaveSameEmail() throws Exception {
		todoService.addUser(new UserData("vinit", "rai", "marshal@gmail.com"));

		UserData anotheruser = new UserData("Anurag", "Jain",
				"marshal@gmail.com");

		exception.expect(ResourceAlreadyExistException.class);
		todoService.addUser(anotheruser);
	}

	@Test
	public void testTaskCreate() throws Exception {
		UserData user = todoService.addUser(new UserData("vinit", "rai",
				"marshal@gmail.com"));

		TaskData data = createTask("Sample task", user.getUserId());
		TaskDetailsData response = todoService.createTask(data);

		assertEquals("Matching Task Name", data.getTask(), response.getTask());
		assertEquals("Matching Task Status", TaskStatus.CREATED,
				response.getStatus());
		assertEquals("Matching Task Priority", TaskPriority.HIGH,
				response.getPriority());
		assertEquals("Matching Task Assigned To", null, response
				.getAssignedTo().getEmail());
		assertEquals("Matching Task Comments", null, response.getComments());
		assertNotNull(response.getCreatedBy());
	}

	@Test
	public void testTaskCreateFailure() throws Exception {
		TaskData data = createTask("Sample task", new Long(1234));
		exception.expect(ResourceNotFoundException.class);
		todoService.createTask(data);
	}

	@Test
	public void testCannotRemoveInvallidUser() throws ResourceNotFoundException {
		exception.expect(ResourceNotFoundException.class);
		todoService.removeUser(new Long(1234));
	}

	@Test
	public void testCannotRemoveInvallidTask() throws ResourceNotFoundException {
		exception.expect(ResourceNotFoundException.class);
		todoService.removeTask(new Long(1234));
	}

	@Test
	public void testAddComment() throws Exception {
		UserData user = todoService.addUser(new UserData("vinit", "rai",
				"marshal@gmail.com"));
		TaskData data = createTask("Sample task", user.getUserId());
		TaskDetailsData response = todoService.createTask(data);

		CommentData comment = new CommentData();
		comment.setCommentText("Sample Comment on Sapmle Task");
		comment.setTaskId(response.getTaskId());
		comment.setUserId(user.getUserId());

		todoService.addComment(comment);

		TaskDetailsData task = todoService.getTaskById(response.getTaskId());
		assertNotNull(task.getComments());
	}

	@Test
	public void testAddCommentWithInvalidUserId() throws Exception {
		UserData user = todoService.addUser(new UserData("vinit", "rai",
				"marshal@gmail.com"));
		TaskData data = createTask("Sample task", user.getUserId());
		TaskDetailsData task = todoService.createTask(data);

		CommentData comment = new CommentData();
		comment.setCommentText("Sample Comment on Sample Task");
		comment.setTaskId(task.getTaskId());
		comment.setUserId(new Long(3245));

		exception.expect(ResourceNotFoundException.class);
		todoService.addComment(comment);
	}

	@Test
	public void testAddCommentWithInvalidTaskId() throws Exception {
		UserData user = todoService.addUser(new UserData("vinit", "rai",
				"marshal@gmail.com"));
		TaskData data = createTask("Sample task", user.getUserId());
		TaskDetailsData task = todoService.createTask(data);

		CommentData comment = new CommentData();
		comment.setCommentText("Sample Comment on Sapmle Task");
		comment.setTaskId(new Long(324534234));
		comment.setUserId(user.getUserId());

		exception.expect(ResourceNotFoundException.class);
		todoService.addComment(comment);
	}
}
