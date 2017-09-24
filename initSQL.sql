DROP DATABASE IF EXISTS medicine;
CREATE DATABASE medicine;

use medicine;

CREATE TABLE medical_facility(
    id Int unsigned not null auto_increment,
    name varchar(100) not null,
    address varchar(200) not null,
    superior_medical_facility Int unsigned  DEFAULT  NULL,
    constraint pk_medical_facility primary key (id),
    constraint fk_superior_medical_facility foreign key (superior_medical_facility) references medical_facility(id)
);

CREATE TABLE housing(
    id int unsigned not null auto_increment,
    name varchar(100) not null,
    address VARCHAR(200) not null,
    hospital_id Int unsigned not null,
    CONSTRAINT pk_housing PRIMARY KEY(id),
    CONSTRAINT fk_hospital_id FOREIGN KEY(hospital_id) REFERENCES medical_facility(id)
);

CREATE TABLE department (
    id int unsigned not null auto_increment,
    name VARCHAR(100) not null,
    housing_id Int unsigned not null,
    CONSTRAINT pk_department PRIMARY KEY(id),
    CONSTRAINT fk_housing_id FOREIGN KEY(housing_id) REFERENCES housing(id)
);

CREATE TABLE disease (
    id int unsigned not null auto_increment,
    name VARCHAR(100) not null,
    CONSTRAINT pk_disease PRIMARY KEY(id)
);

CREATE TABLE department_specialization (
    id_department int unsigned not null,
    id_desease int unsigned not NULL,
    CONSTRAINT pk_dep_spec PRIMARY KEY(id_department,id_desease)
);

CREATE TABLE staff (
    id int unsigned not null auto_increment,
    FIO VARCHAR(100) not null,
    -- учёная степень --

    CONSTRAINT pk_staff PRIMARY KEY (id)
);

CREATE TABLE specialty (
    id  SMALLINT unsigned not null auto_increment,
    name VARCHAR(100) not null,

    CONSTRAINT pk_specialty PRIMARY KEY (id)
);

CREATE TABLE staff_specialization (
    id_staff int unsigned not null,
    id_specialty SMALLINT unsigned not null,

    CONSTRAINT pk_staff_specialization PRIMARY KEY(id_staff,id_specialty)
);

CREATE TABLE position (
    id SMALLINT unsigned not null,
    name VARCHAR(100) not null,

    CONSTRAINT pk_position PRIMARY KEY (id)
);

CREATE TABLE place_of_work (
    id_staff int unsigned not null,
    id_medical_facility  int unsigned not null,
    id_position SMALLINT unsigned not NULL,
    rate ENUM('1','0.25','0.5','0.75'), 

    CONSTRAINT pk_place_of_work PRIMARY KEY ( id_staff,id_medical_facility,id_position)
);