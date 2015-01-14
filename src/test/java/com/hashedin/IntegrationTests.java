package com.hashedin;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.hashedin.integration.AuthorizationTests;
import com.hashedin.integration.DebugModeTests;
import com.hashedin.integration.TodoRestTest;

@RunWith(Suite.class)
@SuiteClasses({ AuthorizationTests.class, DebugModeTests.class,
		TodoRestTest.class })
public class IntegrationTests {

}
