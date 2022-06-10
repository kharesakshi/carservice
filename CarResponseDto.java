package com.carservice.carExample.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarResponseDto {
	
	private String manufacturer;
	
	private String model;
	
	private int year;
	
	private String color;
	
	private String vin;
	
	private Long miles;

}
