package com.carservice.carExample.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import com.carservice.carExample.dto.CarRequestDto;
import com.carservice.carExample.dto.CarResponseDto;
import com.carservice.carExample.entity.Car;


@Mapper(componentModel="spring")
public interface CarMapper {
	
	Car fromCarRequestDtoToCarEntity(CarRequestDto request,@MappingTarget Car car);
	
	CarResponseDto fromCarEntityToCarRequestDto(Car car, @MappingTarget CarResponseDto response);

}
