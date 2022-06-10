package com.carservice.carExample.dto;

import lombok.Data;

@Data
public class CarRequestDto {
	
	private String manufacturer;
	
	private String model;
	
	private int year;
	
	private String color;
	
	private String vin;
	
	private Long miles;
 
}
