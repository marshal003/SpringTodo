package com.hashedin.artcollective.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.hashedin.artcollective.BaseUnitTest;

public class ShopifyServiceTest extends BaseUnitTest {

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
