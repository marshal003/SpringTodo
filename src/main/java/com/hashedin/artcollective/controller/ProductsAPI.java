package com.hashedin.artcollective.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductsAPI {

	@RequestMapping(value = "/api/products", method = RequestMethod.GET)
	public Map<String, Object> getProducts() {
		List<Product> products = new ArrayList<Product>();
		products.add(new Product("1", "iPhone", "499", "The dumbest phone in the world"));
		products.add(new Product("2", "Nexus", "501", "The smartest android machine"));
		return wrapResponse(products);
		
	}

	static Map<String, Object> wrapResponse(Object o) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", o);
		return map;
	}
}
