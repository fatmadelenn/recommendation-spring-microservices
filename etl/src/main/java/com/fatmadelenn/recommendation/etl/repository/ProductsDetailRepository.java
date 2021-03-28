package com.fatmadelenn.recommendation.etl.repository;

import com.fatmadelenn.recommendation.etl.entity.ProductsDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsDetailRepository extends JpaRepository<ProductsDetail, Long> {
    List<ProductsDetail> findByProductId(String productId);
}
