package com.carservice.carExample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.carservice.carExample.entity.Model;

@Repository
public interface ModelRepository extends JpaRepository<Model,Long> {

}
