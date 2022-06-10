package com.carservice.carExample.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.carservice.carExample.dto.CarRequestDto;
import com.carservice.carExample.dto.CarResponseDto;
import com.carservice.carExample.entity.Car;
import com.carservice.carExample.exception.ResourceNotFoundException;
import com.carservice.carExample.mapper.CarMapper;
import com.carservice.carExample.repository.CarRepository;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class CarService {

	private final CarRepository carRepository;
	private final CarMapper carMapper;
	
	public CarService(CarRepository carRepository, CarMapper carMapper) {
		this.carRepository = carRepository;
		this.carMapper = carMapper;
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public CarResponseDto createCar(CarRequestDto request) {
		log.info("Create car");
		Car car = carRepository.save(carMapper.fromCarRequestDtoToCarEntity(request, new Car()));
		return carMapper.fromCarEntityToCarRequestDto(car, new CarResponseDto());	
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Car> getAllCars(){
		log.info("get list of all cars");
		List<Car> listOfCars = carRepository.findAll();
		return listOfCars;
	}
	
	/**
	 * 
	 * @param carId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public Optional<Car> getCarById(Long carId) throws ResourceNotFoundException {
		log.info("Get car by id passed");
		boolean carExists = carRepository.existsById(carId);
		carRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Car Id passed does not exists."));
		Optional<Car> carResponse = null;
		if(carExists) {
			 carResponse = carRepository.findById(carId);
		}
		return carResponse;
		
	}
	
	/**
	 * 
	 * @param request
	 * @param carId
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public CarResponseDto updateCar(CarRequestDto request,Long carId) throws ResourceNotFoundException {
		log.info("Updating Car");
		boolean carExists = carRepository.existsById(carId);
		Car car = carRepository.findById(carId).get();
		carRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Car Id passed does not exists."));
		Car carUpdated = null;
		if(carExists) {
			carUpdated = carRepository.save(carMapper.fromCarRequestDtoToCarEntity(request,car));
		}
		
		return carMapper.fromCarEntityToCarRequestDto(carUpdated, new CarResponseDto());
	}
	
	/**
	 * 
	 * @param carId
	 * @throws ResourceNotFoundException
	 */
	public void deleteCar(Long carId) throws ResourceNotFoundException {
		log.info("Deleting Car");
		boolean carExists = carRepository.existsById(carId);
		carRepository.findById(carId).orElseThrow(() -> new ResourceNotFoundException("Car Id passed does not exists."));
		if (carExists) {
			carRepository.deleteById(carId);
		} 

	}
}
