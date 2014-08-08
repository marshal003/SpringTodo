package com.hashedin.artcollective.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hashedin.artcollective.Main;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
public class ShopifyServiceTest {

	@Autowired
	private ShopifyService service;
	
	@Test
	public void testFetchProducts() {
		List<ShopifyProduct> products = service.getProductsSinceLastModified(null);
		ShopifyProduct product = products.get(0);
		assertEquals(product.getId(), 331204149);
		assertNotNull(product.getCreatedAt());
		assertNotNull(product.getUpdatedAt());
		assertNotNull(product.getPublishedAt());
		
	}
}
