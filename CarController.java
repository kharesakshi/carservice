package com.carservice.carExample.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.carservice.carExample.dto.CarRequestDto;
import com.carservice.carExample.dto.CarResponseDto;
import com.carservice.carExample.entity.Car;
import com.carservice.carExample.exception.ResourceNotFoundException;
import com.carservice.carExample.service.CarService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class CarController {
	
	private final CarService carService;
	
	CarController(CarService carService){
		this.carService = carService;
	}
	
	/**
	 * Endpoint to save a car
	 * @param carRequestDto
	 * @return saved car {@link CarResponseDto}
	 */
	@PostMapping(value="/car",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CarResponseDto> createCar(@RequestBody CarRequestDto carRequestDto){
		log.info("Create Car");
		CarResponseDto response = carService.createCar(carRequestDto);
		log.info("Car created successfully");
		return ResponseEntity.ok(response);		
	}
	
	/**
	 * Endpoint to get all cars present in the database.
	 * @return 
	 */
	@GetMapping(value="/car", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Car>> getAllCars(){
		log.info("Get list of all cars");
		List<Car> listOfCars = carService.getAllCars();
		log.info("Retrieved list of all cars");
		return ResponseEntity.ok(listOfCars);		
	}
	
	/**
	 * 
	 * @param carId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@GetMapping(value="car/{carId}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Optional<Car>> getCarById(@PathVariable Long carId) throws ResourceNotFoundException{
		log.info("Get car by id");
		Optional<Car> car = carService.getCarById(carId);
		log.info("Retrieved car for id passed.");
		return ResponseEntity.ok(car);
	}
	
	/**
	 * 
	 * @param request
	 * @param carId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	@PutMapping(value="car/{carId}",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CarResponseDto> updateCar(@RequestBody CarRequestDto request,@PathVariable Long carId) throws ResourceNotFoundException{
		log.info("Updating car");
		CarResponseDto updateResponse = carService.updateCar(request,carId);
		log.info("Car updated successfully.");
		return ResponseEntity.ok(updateResponse);
	}
	
	/**
	 * 
	 * @param carId
	 * @throws ResourceNotFoundException
	 */
	@DeleteMapping(value="car/{carId}",produces=MediaType.APPLICATION_JSON_VALUE)
	public void deleteCar(@PathVariable Long carId) throws ResourceNotFoundException {
		log.info("Deleting Car");
		carService.deleteCar(carId);
		log.info("Car Deleted successfully.");
	}
}
