package com.hashedin.sample;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.hashedin.sample.integration.AuthorizationTests;

@RunWith(Suite.class)
@SuiteClasses({ 
	AuthorizationTests.class,
})
public class IntegrationTests {

}
