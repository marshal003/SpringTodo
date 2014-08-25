package com.hashedin.sample;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.hashedin.sample.repository.PersonRepositoryTest;
import com.hashedin.sample.service.ProductServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ 
	PersonRepositoryTest.class,
	ProductServiceTest.class,
})
public class UnitTests {

}
