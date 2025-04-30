package com.myshopping.My.Shopping.REPOSITORY;

import com.myshopping.My.Shopping.MODEL.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {}