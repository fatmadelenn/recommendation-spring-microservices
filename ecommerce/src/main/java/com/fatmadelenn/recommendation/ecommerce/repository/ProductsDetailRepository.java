package com.fatmadelenn.recommendation.ecommerce.repository;

import com.fatmadelenn.recommendation.ecommerce.entity.ProductsDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsDetailRepository extends JpaRepository<ProductsDetail, Long> {
    @Query(value = "SELECT product_id FROM products_detail ORDER BY total_user DESC", nativeQuery = true)
    List<String> orderDescBestSeller();

    @Query(value = "SELECT detail.product_id FROM products_detail detail inner join products pro on detail.product_id = pro.product_id where pro.category_id = :categoryId ORDER BY total_user DESC", nativeQuery = true)
    List<String> orderDescestSellerWithCategory(String categoryId);
}
