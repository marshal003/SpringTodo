package com.hashedin.artcollective.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ShopifyServiceImpl implements ShopifyService {

	private final String baseUri;
	private final RestTemplate rest;
	
	@Autowired
	public ShopifyServiceImpl(RestTemplate rest, 
								@Value("${shopify.baseurl}") String baseUrl) {
		
		this.rest = rest;
		this.baseUri = baseUrl;
	}


	@Override
	public List<ShopifyProduct> getProductsSinceLastModified(Date lastModified) {
		ShopifyProducts products = rest.getForObject(baseUri + "products.json", ShopifyProducts.class);
		return products.getProducts();
	}

}
