package com.hashedin;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.hashedin.repository.TaskRepositoryTest;
import com.hashedin.repository.UserRepositoryTest;
import com.hashedin.service.TaskServiceTest;
import com.hashedin.task.TaskQueueTest;

@RunWith(Suite.class)
@SuiteClasses({ 
	TaskQueueTest.class,
	TaskRepositoryTest.class,
	UserRepositoryTest.class,
	TaskServiceTest.class
})
public class UnitTests {

}
