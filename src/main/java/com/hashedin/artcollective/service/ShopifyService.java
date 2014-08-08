package com.hashedin.artcollective.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public interface ShopifyService {

	public List<ShopifyProduct> getProductsSinceLastModified(Date lastModified);
}
