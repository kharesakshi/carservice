package com.carservice.carExample.repository;

import org.springframework.stereotype.Repository;

import com.carservice.carExample.entity.Manufacturer;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer,Long>{

}
