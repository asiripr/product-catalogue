package com.asiripr.product_catalogue.models;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name="products")
public class MyProduct {
	
//	our primary key
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)// to auto increment
	private int id;
	
//	our other variables 
	
	private String name;
	private String brand;
	private String category;
	private double price;

	@Column(columnDefinition = "TEXT")
	private String description;
	private Date createdAt;
	private String imageFileName;
	public int getId() {
		return id;
	}
	
//	------ GETTERS AND SETTERS ------

	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	
//	------ ------ ------
	
}
