package com.fatmadelenn.recommendation.ecommerce.repository;

import com.fatmadelenn.recommendation.ecommerce.dto.Properties;
import com.fatmadelenn.recommendation.ecommerce.entity.ProductViews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ProductViewsRepository extends JpaRepository<ProductViews, UUID> {

    Set<ProductViews> findTop10ByUseridOrderByTimestampDesc(String userid);

    List<ProductViews> findByUseridAndProperties(String userid, Properties properties);

    List<ProductViews> findByUserid(String userid);
}
