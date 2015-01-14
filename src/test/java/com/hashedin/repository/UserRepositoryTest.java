package com.hashedin.repository;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hashedin.BaseUnitTest;
import com.hashedin.entity.User;

public class UserRepositoryTest extends BaseUnitTest {

	@Autowired
	private UserRepository repository;

	@Before
	public void setUpUsersTable() {
		// Create some user
		List<User> newUsers = new ArrayList<User>();
		newUsers.add(new User("vinit", "rai", "vinit@hashedin.com"));
		newUsers.add(new User("vinit", "rai", "vinit.1989@hashedin.com"));
		repository.save(newUsers);
	}

	@After
	public void cleanUpUsersTable() {
		repository.deleteAll();
	}

	@Test
	public void testGetAllUsers() {
		List<User> users = (List<User>) repository.findAll();
		assertEquals("Got two users", 2, users.size());
	}

}
