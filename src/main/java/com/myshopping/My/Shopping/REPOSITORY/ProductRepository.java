package com.myshopping.My.Shopping.REPOSITORY;

import com.myshopping.My.Shopping.MODEL.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {}
