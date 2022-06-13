package com.carservice.carExample.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import com.carservice.carExample.dto.CarRequestDto;
import com.carservice.carExample.dto.CarResponseDto;
import com.carservice.carExample.entity.Car;
import com.carservice.carExample.repository.ManufacturerRepository;
import com.carservice.carExample.repository.ModelRepository;

@Mapper(componentModel="spring")
public abstract class CarMapper {
	
	@Autowired
	ModelRepository modelRepository;
	
	@Autowired
	ManufacturerRepository manufacturerRepository;
	
	@Mapping(source = "request.manufacturerId", target="manufacturer.manufacturerId")
	@Mapping(source = "request.modelId",target = "models.modelId")
	@Mapping(target="car.modelName" ,expression ="java(modelRepository.findById(request.modelId).get().getModelName())")
	@Mapping(target="car.manufacturerName",expression="java(manufacturerRepository.findById(request.manufacturerId).get().getManufacturerName())")
	public abstract
	Car fromDtoToEntity(CarRequestDto request,@MappingTarget Car car);
	
	@Mapping(source ="car.manufacturer.manufacturerId",target="manufacturerId")
	@Mapping(source ="car.models.modelId", target="modelId")
	public abstract
	CarResponseDto fromEntityToDto(Car car, @MappingTarget CarResponseDto response);
	
}
