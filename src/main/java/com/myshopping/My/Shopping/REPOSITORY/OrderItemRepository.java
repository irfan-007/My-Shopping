package com.myshopping.My.Shopping.REPOSITORY;

import com.myshopping.My.Shopping.MODEL.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {}
