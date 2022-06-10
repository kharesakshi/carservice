package com.carservice.carExample.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "car")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "car_id")
	Long carId;
	
	int year;
	
	String color;
	
	String vin;
	
	Long miles;
	
	@ManyToOne(cascade = CascadeType.ALL,targetEntity = Model.class)
	Model models;
	 
	/*
	 * @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name ="car_id", referencedColumnName="car_id") String
	 * manufacturer;
	 */
	
	
}
