package com.myshopping.My.Shopping.REPOSITORY;

import com.myshopping.My.Shopping.MODEL.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {}
