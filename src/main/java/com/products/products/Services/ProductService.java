package com.products.products.Services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.products.products.Dtos.ProductDTO;
import com.products.products.Entities.ProductEntity;
import com.products.products.Exceptions.CriterialNotFoundException;
import com.products.products.Repositories.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	
	public Optional<ProductDTO> getProductById(Long productId){
		return Optional.ofNullable(productRepository.findByProductId(productId)
													.orElseThrow(() -> new CriterialNotFoundException("Product" + productId + " was not found")))
													.map(this::convertDTO);
	}
	
	
	public List<ProductDTO> getProductsByCategory(String productCategory) {
		return getProductsByCriteria(productRepository.findByProductCategory(productCategory));
	}
	
	public List<ProductDTO> getProductsByRangePrice(double productPriceMin, double productPriceMax) {
		return getProductsByCriteria(productRepository.findByProductPriceBetween(productPriceMin, productPriceMax));
	}
	
	public List<ProductDTO> getProductsByAvailability(boolean isAvailable) {
		return getProductsByCriteria(productRepository.findByIsAvailable(isAvailable));
	}
	
	public List<ProductDTO> getProductsByCriteria(List<ProductEntity> productEntity) {
		
		if (productEntity.isEmpty()) {
			throw new CriterialNotFoundException("Product not found with that criteria");
		}
		
		return productEntity.stream()
					 .map(this::convertDTO)
					 .collect(Collectors.toList());	
	}
		
	
	private ProductDTO convertDTO(ProductEntity productEntity) {
		return new ProductDTO(productEntity.getProductId(), productEntity.getProductName(), productEntity.getProductCategory(), productEntity.isAvailable(), productEntity.getProductPrice());
	}
	
}
