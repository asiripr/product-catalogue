package com.asiripr.product_catalogue.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asiripr.product_catalogue.models.MyProduct;

public interface ProductRepository extends JpaRepository<MyProduct, Integer>{

}
