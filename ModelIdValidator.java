package com.carservice.carExample.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.carservice.carExample.repository.ModelRepository;

public class ModelIdValidator implements ConstraintValidator<ModelId,Long> {

	@Autowired
	private ModelRepository modelRepository;
	
	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
	   return modelRepository.existsById(value);
	}

}
