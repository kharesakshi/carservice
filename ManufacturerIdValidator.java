package com.carservice.carExample.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.carservice.carExample.repository.ManufacturerRepository;
import com.carservice.carExample.repository.ModelRepository;

public class ManufacturerIdValidator implements ConstraintValidator<ManufacturerId,Long> {
	
	@Autowired
	private ManufacturerRepository manufacturerRepository;
	
	@Override
	public boolean isValid(Long value, ConstraintValidatorContext context) {
	   return manufacturerRepository.existsById(value);
	}

}
