package com.asiripr.product_catalogue.controllers;

import java.io.InputStream;
import java.nio.file.*;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.asiripr.product_catalogue.models.MyProduct;
import com.asiripr.product_catalogue.models.MyProductDTO;
import com.asiripr.product_catalogue.services.ProductRepository;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/products")
public class ProductsController {

	@Autowired
	private ProductRepository repo;

	@GetMapping({ "", "/" })
	public String showProductList(Model model) {
		List<MyProduct> products = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
		model.addAttribute("products", products);
		return "products/index";
	}

	@GetMapping("/create")
	public String showCreatePage(Model model) {
		MyProductDTO productDTO = new MyProductDTO();
		model.addAttribute("productDTO", productDTO);
		return "products/CreateProduct";
	}

	@PostMapping("/products")
	public String submitProduct(@Valid @ModelAttribute("productDTO") MyProductDTO productDTO,
	                            BindingResult result,
	                            RedirectAttributes redirectAttributes) {
		if (productDTO.getImageFile().isEmpty()) {
			result.addError(new FieldError("productDTO", "imageFile", "The image file is reqired!"));
		}
		if (result.hasErrors()) {
			return "products/CreateProduct";
		}
        redirectAttributes.addFlashAttribute("message", "Product submitted successfully!");
		return "redirect:/products";
	}
	@GetMapping("/edit")
	public String showEditPage(Model model, @RequestParam int id) {
		try {
			MyProduct product = repo.findById(id).get();
			model.addAttribute("product",product);
			
			MyProductDTO productDTO = new MyProductDTO();
			productDTO.setName(product.getName());
			productDTO.setBrand(product.getBrand());
			productDTO.setCategory(product.getCategory());
			productDTO.setPrice(product.getPrice());
			productDTO.setDescription(product.getDescription());
			
			model.addAttribute("productDTO", productDTO);
		} catch (Exception e) {
			System.out.println("Exception: "+e.getMessage());
			return "redirect:/products";
		}
		return "products/EditProduct";
	}
	
	@PostMapping("/edit")
	public String updateProduct(Model model, @RequestParam int id, @Valid @ModelAttribute MyProductDTO productDTO, BindingResult result) {
		try {
			MyProduct product = repo.findById(id).get();
			model.addAttribute("product", product);
			
			if (result.hasErrors()) {
				return "products/EditProducts";
			}
			
			if (!productDTO.getImageFile().isEmpty()) {
				//delete old image
				String uploadDir = "public/images/";
				Path oldImagePath = Paths.get(uploadDir + product.getImageFileName());
			
				try {
					Files.delete(oldImagePath);
				} catch (Exception e) {
					System.out.println("Exception: "+e.getMessage());
				}
				
				// save new image file
				MultipartFile image = productDTO.getImageFile();
				Date createdAt = new Date(id);
				String storageFileName = createdAt.getTime() + "_" + image.getOriginalFilename();
				
				try (InputStream inputStream = image.getInputStream()){
					Files.copy(inputStream, Paths.get(uploadDir + storageFileName),
						StandardCopyOption.REPLACE_EXISTING);
					}
					product.setImageFileName(storageFileName);}
				 
			
			
			
		} catch (Exception e) {
			System.out.println("Exception: "+e.getMessage());
		}
		
		return "redirect:/products";
	}
}
