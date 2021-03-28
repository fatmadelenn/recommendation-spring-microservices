package com.fatmadelenn.recommendation.streamreader.repository;

import com.fatmadelenn.recommendation.streamreader.entity.ProductViews;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductViewsRepository extends JpaRepository<ProductViews, Long> {
}
