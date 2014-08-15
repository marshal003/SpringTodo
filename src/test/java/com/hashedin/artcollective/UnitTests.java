package com.hashedin.artcollective;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.hashedin.artcollective.repository.ArtistRepositoryTest;
import com.hashedin.artcollective.service.ShopifyServiceTest;

@RunWith(Suite.class)
@SuiteClasses({ 
	ArtistRepositoryTest.class,
	ShopifyServiceTest.class,
})
public class UnitTests {

}
