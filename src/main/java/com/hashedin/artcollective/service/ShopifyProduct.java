package com.hashedin.artcollective.service;

import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ShopifyProduct {

	private static final DateTimeFormatter DATE_FORMAT = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ssZ");

	private String bodyHtml;
	private String handle;
	private long id;
	private String productType;
	private String publishedScope;
	private String templateSuffix;
	private String title;
	private String vendor;
	private String tags;

	private DateTime createdAt;
	private DateTime updatedAt;
	private DateTime publishedAt;
	
	private List<ShopifyVariant> variants;

	public String getBodyHtml() {
		return bodyHtml;
	}
	public void setBodyHtml(String bodyHtml) {
		this.bodyHtml = bodyHtml;
	}
	public String getHandle() {
		return handle;
	}
	public void setHandle(String handle) {
		this.handle = handle;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getPublishedScope() {
		return publishedScope;
	}
	public void setPublishedScope(String publishedScope) {
		this.publishedScope = publishedScope;
	}
	public String getTemplateSuffix() {
		return templateSuffix;
	}
	public void setTemplateSuffix(String templateSuffix) {
		this.templateSuffix = templateSuffix;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getVendor() {
		return vendor;
	}
	public void setVendor(String vendor) {
		this.vendor = vendor;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public DateTime getCreatedAt() {
		return createdAt;
	}
	@JsonSetter("created_at")
	public void setCreatedAt(String createdAt) {
		this.createdAt = DATE_FORMAT.parseDateTime(createdAt);
	}
	
	public DateTime getUpdatedAt() {
		return updatedAt;
	}
	@JsonSetter("updated_at")
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = DATE_FORMAT.parseDateTime(updatedAt);
	}
	public DateTime getPublishedAt() {
		return publishedAt;
	}
	@JsonSetter("published_at")
	public void setPublishedAt(String publishedAt) {
		this.publishedAt = DATE_FORMAT.parseDateTime(publishedAt);
	}
	public List<ShopifyVariant> getVariants() {
		return variants;
	}
	public void setVariants(List<ShopifyVariant> variants) {
		this.variants = variants;
	}

	
	
}
