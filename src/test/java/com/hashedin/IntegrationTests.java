package com.hashedin;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.hashedin.integration.AuthorizationTests;

@RunWith(Suite.class)
@SuiteClasses({ 
	AuthorizationTests.class,
})
public class IntegrationTests {

}
