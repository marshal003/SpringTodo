package com.hashedin;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.hashedin.repository.PersonRepositoryTest;
import com.hashedin.service.ProductServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ 
	PersonRepositoryTest.class,
	ProductServiceTest.class,
})
public class UnitTests {

}
