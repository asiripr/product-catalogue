package com.asiripr.product_catalogue.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asiripr.product_catalogue.models.MyProduct;
// we can use this interface to read and write product from the db
public interface ProductRepository extends JpaRepository<MyProduct, Integer>{

}
