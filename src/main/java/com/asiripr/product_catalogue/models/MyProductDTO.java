package com.asiripr.product_catalogue.models;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.*;

// Data Transfer Object (DTO). 
// Used to transfer data between different layers of an application.

public class MyProductDTO {
	@NotEmpty(message="The name is required")
	private String name;
	
	@NotEmpty(message="The brand is required")
	private String brand;
	
	@NotEmpty(message="The price is required")
	private String category;
	
	@Min(0)
	private double price;
	
	@Size(min = 10, message="The description should be at least 10 characters")
	@Size(max = 2000, message="The description cannot exceed 2000 characters")
	private String discription;
	
	private MultipartFile imageFile;
	

}
