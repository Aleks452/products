package com.products.products.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.products.products.Dtos.ProductDTO;
import com.products.products.Exceptions.CriterialNotFoundException;
import com.products.products.Repositories.ProductRepository;
import com.products.products.Services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/products")

public class ProductsController {


	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductRepository productRepository;
	
	
	@Operation(
			summary = "Get all products.",
			description =
			"This endpoint allows you to obtain all products.")
	@GetMapping()
	public ResponseEntity<List<ProductDTO>> getAllProducts() {
		
		List<ProductDTO> productDTO = productService.getProductsByCriteria(productRepository.findAll());
		if (productDTO.isEmpty()) {
			throw new CriterialNotFoundException("No exist any product");
		}
		
		return  ResponseEntity.ok(productDTO);
	}
	
	
	@Operation(
			summary = "Get product.",
			description =
			"This endpoint allows you to obtain product using specific id")
	@GetMapping("/{productId}")
	public ResponseEntity<Optional<ProductDTO>> getProductById(@PathVariable Long productId) {
		return  ResponseEntity.ok(productService.getProductById(productId));

	}

	@Operation(
			summary = "Get list of products.",
			description =
			"This endpoint allows you to obtain product using categories")
	@GetMapping("/category")
	public ResponseEntity<List<ProductDTO>> getProductById(@RequestParam("productCategory") @NotNull @NotBlank String productCategory) {
		return  ResponseEntity.ok(productService.getProductsByCategory(productCategory));

	}

	@Operation(
			summary = "Get list of products.",
			description =
			"This endpoint allows you to obtain product using ranges")
	@GetMapping("/rangePrice")
	public ResponseEntity<List<ProductDTO>> getProductsByRangePrice(@RequestParam("productPriceMin") @NotNull double productPriceMin, 
			@RequestParam("productPriceMax") @NotNull double productPriceMax) {
		return  ResponseEntity.ok(productService.getProductsByRangePrice(productPriceMin, productPriceMax));

	}


	@Operation(
			summary = "Get list of products.",
			description =
			"This endpoint allows you to obtain product using availabilitie")
	@GetMapping("/Availability")
	public ResponseEntity<List<ProductDTO>> getProductsByAvailability(@RequestParam("isAvailable") @NotNull boolean isAvailable) {
		return  ResponseEntity.ok(productService.getProductsByAvailability(isAvailable));

	}
	
	@Operation(
			summary = "Delete product.",
			description =
			"This endpoint allows you to delete product using specific id")
	@DeleteMapping("/{productId}")
	public ResponseEntity<String> deleteProductById(@PathVariable Long productId) {
		
		productService.getProductById(productId)
		.ifPresent(product -> productService.deleteProductById(productId));
		
		return ResponseEntity.status(HttpStatus.OK)
				.body("Product id: " + productId + " was deleted successfully.");

	}

}
