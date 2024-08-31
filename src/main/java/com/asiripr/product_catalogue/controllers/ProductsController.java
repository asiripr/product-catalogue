package com.asiripr.product_catalogue.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.asiripr.product_catalogue.models.MyProduct;
import com.asiripr.product_catalogue.models.MyProductDTO;
import com.asiripr.product_catalogue.services.ProductRepository;


@Controller
@RequestMapping("/products")
public class ProductsController {
	
	@Autowired
	private ProductRepository repo;
	
	@GetMapping({"","/"})
	public String showProductList(Model model) {
		List<MyProduct> products = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
		model.addAttribute("products", products);
		return "products/index";
	}
	
	@GetMapping("/create")
	public String showCreatePage (Model model) {
		MyProductDTO productDTO = new MyProductDTO();
		model.addAttribute("productDTO", productDTO);
		return "products/mytest";
	}
}
