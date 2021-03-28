package com.fatmadelenn.recommendation.ecommerce.repository;


import com.fatmadelenn.recommendation.ecommerce.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductsRepository extends JpaRepository<Products, String> {
    Optional<Products> findByProductId(String productId);
}
