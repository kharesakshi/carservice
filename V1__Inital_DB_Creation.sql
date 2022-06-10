CREATE TABLE carservicepart2.car 
(
	car_id BIGINT NOT NULL AUTO_INCREMENT,
	models VARCHAR(40) ,
	year INT NOT NULL,
	color VARCHAR(20) NOT NULL,
	vin VARCHAR(40) NOT NULL UNIQUE,
	miles BIGINT NOT NULL,
	PRIMARY KEY (car_id)
);

CREATE TABLE carservicepart2.manufacturer
(
	manufacturer_id BIGINT AUTO_INCREMENT,
	manufacturer_name VARCHAR(40) ,
	car_id BIGINT ,
	PRIMARY KEY(manufacturer_id),
	FOREIGN KEY (car_id) REFERENCES carservicepart2.car(car_Id)
);

CREATE TABLE carservicepart2.model
(
	model_id BIGINT NOT NULL AUTO_INCREMENT,
	model_name VARCHAR(30) ,
	manufacturer_id BIGINT ,
	car_id BIGINT ,
	PRIMARY KEY(model_id),
	FOREIGN KEY (manufacturer_id) REFERENCES carservicepart2.manufacturer(manufacturer_id),
	FOREIGN KEY (car_id) REFERENCES carservicepart2.car(car_id)
);






 