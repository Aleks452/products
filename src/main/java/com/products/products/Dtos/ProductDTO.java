package com.products.products.Dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.products.products.Validations.FirstMandatoryValidation;
import com.products.products.Validations.SecondMandatoryValidation;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	
	@NotNull (groups = {FirstMandatoryValidation.class})
	@Positive(groups = {FirstMandatoryValidation.class})
	private Long productId;
	
	@NotNull(groups = {FirstMandatoryValidation.class, SecondMandatoryValidation.class})
	@NotBlank(groups = {FirstMandatoryValidation.class, SecondMandatoryValidation.class})
	private String productName;
	
	@NotNull(groups = {FirstMandatoryValidation.class, SecondMandatoryValidation.class})
	@NotBlank(groups = {FirstMandatoryValidation.class, SecondMandatoryValidation.class})
	private String productCategory;
	
	@NotNull(groups = {FirstMandatoryValidation.class, SecondMandatoryValidation.class})
	@JsonProperty("isAvailable")
	private boolean isAvailable;
	
	@NotNull(groups = {FirstMandatoryValidation.class, SecondMandatoryValidation.class})
	@Positive(groups = {FirstMandatoryValidation.class, SecondMandatoryValidation.class})
	private double productPrice;
	
		
}
