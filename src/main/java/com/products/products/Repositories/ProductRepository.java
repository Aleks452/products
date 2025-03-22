package com.products.products.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.products.products.Entities.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	
	Optional<ProductEntity> findByProductId(Long productId);
	List<ProductEntity> findByProductCategory(String productCategory);
	List<ProductEntity> findByIsAvailable(boolean isAvailable);
	List<ProductEntity> findByProductPriceBetween(double productPriceMin, double productPriceMax);
	
	
}
