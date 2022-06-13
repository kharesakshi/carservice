CREATE TABLE carservicepart2.manufacturer
(
	manufacturer_id BIGINT NOT NULL AUTO_INCREMENT,
	manufacturer_name VARCHAR(40) ,
	PRIMARY KEY(manufacturer_id)
);

CREATE TABLE carservicepart2.model
(
	model_id BIGINT NOT NULL AUTO_INCREMENT,
	model_name VARCHAR(30) ,
	manufacturer_id BIGINT ,
	PRIMARY KEY(model_id),
	FOREIGN KEY (manufacturer_id) REFERENCES carservicepart2.manufacturer(manufacturer_id)
);

CREATE TABLE carservicepart2.car 
(
	car_id BIGINT NOT NULL AUTO_INCREMENT,
	year INT NOT NULL,
	color VARCHAR(20) NOT NULL,
	vin VARCHAR(40) NOT NULL UNIQUE,
	miles BIGINT NOT NULL,
	model_id BIGINT NOT NULL,
	manufacturer_id BIGINT NOT NULL,
	model_name VARCHAR(40),
	manufacturer_name VARCHAR(40),
	PRIMARY KEY (car_id),
	FOREIGN KEY (manufacturer_id) REFERENCES carservicepart2.manufacturer(manufacturer_id),
	FOREIGN KEY (model_id) REFERENCES carservicepart2.model(model_id)
);

INSERT INTO carservicepart2.manufacturer VALUES(1,"Tata");
INSERT INTO carservicepart2.manufacturer VALUES(2,"Maruti");

INSERT INTO carservicepart2.model VALUES(1,"Punch",1);
INSERT INTO carservicepart2.model VALUES(2,"Nexon",1);
INSERT INTO carservicepart2.model VALUES(3,"Baleno",2);





 