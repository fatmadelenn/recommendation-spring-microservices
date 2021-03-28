package com.fatmadelenn.recommendation.ecommerce.repository;

import com.fatmadelenn.recommendation.ecommerce.entity.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItems, Integer> {
}
