DROP DATABASE IF EXISTS medicine;
CREATE DATABASE medicine;
/* beds instead of occupited_beds ??*/
/* patiente after bed*/
/*Добавить анализ*/
use medicine;
CREATE TABLE staff (
    id int unsigned not null auto_increment,
    FIO VARCHAR(100) not null,

    CONSTRAINT pk_staff PRIMARY KEY (id)
);
CREATE TABLE staff_shedule(
    staff_id int unsigned not null,
    since_ DATETIME not null,
    to_ DATETIME not null,

    CONSTRAINT pk_staff_shedule PRIMARY KEY(staff_id, since_,to_),
    CONSTRAINT fk_staff_shedule_staff_id FOREIGN KEY(staff_id) REFERENCES staff(id) ON DELETE CASCADE
);
CREATE TABLE medical_facility(
    id Int unsigned not null auto_increment,
    name varchar(100) not null,
    address varchar(200) not null,
    superior_medical_facility Int unsigned  DEFAULT  NULL,
    id_order_doctor int unsigned not null,
    medical_facility_type ENUM('Поликлиника','Больница') NOT NULL,

    constraint pk_medical_facility primary key (id),
    CONSTRAINT fk_id_doctor_department FOREIGN KEY(id_order_doctor) REFERENCES staff(id) ON DELETE CASCADE,
    constraint fk_superior_medical_facility foreign key (superior_medical_facility) references medical_facility(id) ON DELETE CASCADE
);
CREATE TABLE housing(
    id int unsigned not null auto_increment,
    name varchar(100) not null,
    address VARCHAR(200) not null,
    medical_facility_id Int unsigned not null,
	
    CONSTRAINT pk_housing PRIMARY KEY(id),
    CONSTRAINT fk_hospital_id FOREIGN KEY(medical_facility_id) REFERENCES medical_facility(id) ON DELETE CASCADE
);

CREATE TABLE department (
    id int unsigned not null auto_increment,
    name VARCHAR(100) not null,
    housing_id Int unsigned not null,


    CONSTRAINT pk_department PRIMARY KEY(id),
    CONSTRAINT fk_housing_id FOREIGN KEY(housing_id) REFERENCES housing(id) ON DELETE CASCADE
);
/* null - healthy*/ 
CREATE TABLE disease (
    id int unsigned auto_increment,
    name VARCHAR(100) not null,
	
    CONSTRAINT pk_disease PRIMARY KEY(id)
)
auto_increment = 1;

CREATE TABLE department_specialization (
    id_department int unsigned not null,
    id_disease int unsigned not NULL,
	
	CONSTRAINT fk_department FOREIGN KEY(id_department) REFERENCES department(id) ON DELETE CASCADE,
	CONSTRAINT fk_disease FOREIGN KEY(id_disease) REFERENCES disease(id) ON DELETE CASCADE,
    CONSTRAINT pk_dep_spec PRIMARY KEY(id_department,id_disease)
);

CREATE TABLE specialty (
    id  INT unsigned not null auto_increment,
    name VARCHAR(100) not null,
    is_Doctor BOOLEAN not null DEFAULT false,
    salary FLOAT not null,
    degree ENUM('Кандидат медицинских наук','Доктор медицинских наук') DEFAULT null,
    grade ENUM('Доцент','Профессор') DEFAULT null,
    
    CONSTRAINT pk_specialty PRIMARY KEY (id)
);

CREATE TABLE staff_specialization (
    id_staff int unsigned not null,
    id_specialty INT unsigned not null,
   

	CONSTRAINT fk_staff FOREIGN KEY(id_staff) REFERENCES staff(id) ON DELETE CASCADE,
	CONSTRAINT fk_specialty FOREIGN KEY(id_specialty) REFERENCES specialty(id) ON DELETE CASCADE,
    CONSTRAINT pk_staff_specialization PRIMARY KEY(id_staff,id_specialty)
);

CREATE TABLE position_ (
    id INT unsigned not null auto_increment,
    name VARCHAR(100) not null,

    CONSTRAINT pk_position PRIMARY KEY (id)
);

CREATE TABLE place_of_work (
	/*--свой ID? --*/
    id_staff int unsigned not null,
    id_medical_facility  int unsigned not null,
    id_position INT unsigned not NULL,
    id_department INT unsigned not null,
    rate ENUM('1','0.25','0.5','0.75') not null, 
    type_of_work ENUM('Работа','Консультация') not null DEFAULT 'Работа',
    since_ datetime not null,
    to_ datetime not null DEFAULT NOW(),

	CONSTRAINT fk_place_of_work_staff FOREIGN KEY(id_staff) REFERENCES staff(id) ON DELETE CASCADE,
	CONSTRAINT fk_place_of_work_medical_facility FOREIGN KEY(id_medical_facility) REFERENCES medical_facility(id) ON DELETE CASCADE,
	CONSTRAINT fk_place_of_work_position FOREIGN KEY(id_position) REFERENCES position_(id) ON DELETE CASCADE,
    CONSTRAINT fk_place_of_work_department FOREIGN KEY(id_department) references department(id) ON DELETE CASCADE,
    CONSTRAINT pk_place_of_work PRIMARY KEY ( id_staff,id_medical_facility,id_position, id_department)
);

CREATE TABLE room (
	id int unsigned not null auto_increment,
	room_number int unsigned not null,
	number_of_beds int unsigned,
	id_department INT unsigned not null,
    id_responsible_doctor INT unsigned not null,
	
    FOREIGN KEY (id_department) REFERENCES department(id) ON DELETE CASCADE,
	CONSTRAINT pk_room PRIMARY KEY (id)
);
CREATE TABLE office (
    id int unsigned not null auto_increment,
    id_department int unsigned not null,
    id_responsible_doctor int unsigned not null,

    FOREIGN KEY(id_department) REFERENCES department(id) ON DELETE CASCADE,
    FOREIGN KEY(id_responsible_doctor) REFERENCES staff(id) ON DELETE CASCADE,
    PRIMARY KEY(id)
);


/*CREATE TABLE room_department (
	/*--свой ID? --
	id_department int unsigned not null,
	id_room int unsigned not null,
	since datetime not null,
	to_ datetime not null,
		
	CONSTRAINT fk_room_department_room FOREIGN KEY(id_room) REFERENCES room(id),
	CONSTRAINT fk_room_department_department FOREIGN KEY(id_department) REFERENCES department(id),
	CONSTRAINT pk_room_department PRIMARY KEY (id_department,id_room,since,to_)
);*/
/* INSERT AFTER PATIENTE COME*/
CREATE TABLE occupied_beds (
    id int unsigned not null auto_increment,
	id_room int unsigned not null,
	since_ datetime not null DEFAULT NOW(),
	to_ datetime not null DEFAULT NOW(),
	
	CONSTRAINT fk_room_id FOREIGN KEY(id_room) REFERENCES room(id) ON DELETE CASCADE,
    CONSTRAINT pk_occupied_beds PRIMARY KEY(id)
);

/*--CREATE TABLE service_doctor (
--	id_department int unsigned not null,
--	id_room int unsigned not null,
--	since datetime not null,
--	to_ datetime not null,
--)*/
CREATE TABLE laboratory_spec (
	id INT unsigned not null auto_increment,
	name VARCHAR(100) not null,
	
	CONSTRAINT pk_laboratory PRIMARY KEY(id)
);

CREATE TABLE laboratory (
	id INT unsigned not null auto_increment,
	name VARCHAR(100) not null,
	address VARCHAR(200) not null,
	
	CONSTRAINT pk_laboratory PRIMARY KEY(id)
);

CREATE TABLE laboratory_laboratory_spec(
    id_laboratory INT unsigned not null,
    id_laboratory_spec INT unsigned not null,

    PRIMARY KEY(id_laboratory,id_laboratory_spec),
    FOREIGN KEY(id_laboratory) REFERENCES laboratory(id) ON DELETE CASCADE,
    FOREIGN KEY(id_laboratory_spec) REFERENCES laboratory_spec(id) ON DELETE CASCADE
);


CREATE TABLE type_of_analysis(
    id int unsigned not null auto_increment,
    name VARCHAR(100) not null,

    PRIMARY KEY(id)
);
CREATE TABLE patience (
    id INT unsigned NOT NULL auto_increment,
	FIO varchar(100) not null,
    
    primary key(id)
);
CREATE TABLE analysis (
	id INT unsigned not null auto_increment,
    id_type_of_analysis INT unsigned not null,
    id_patience int unsigned not null,

    CONSTRAINT fk_analysis_id_type_of_analysis FOREIGN KEY(id_type_of_analysis) REFERENCES type_of_analysis(id) ON DELETE CASCADE,
    CONSTRAINT fk_analysis_id_patience FOREIGN KEY(id_patience) REFERENCES patience(id) ON DELETE CASCADE,
	CONSTRAINT pk_analysis PRIMARY KEY(id)
);
CREATE TABLE laboratory_analysis_type (
	id_laboratory INT unsigned not null,
	id_analysis_type INT unsigned not null,
	
	CONSTRAINT fk_analysis FOREIGN KEY(id_analysis_type) REFERENCES type_of_analysis(id) ON DELETE CASCADE,
	CONSTRAINT fk_laboratory FOREIGN KEY(id_laboratory) REFERENCES laboratory(id) ON DELETE CASCADE,
	CONSTRAINT pk_laboratory_analysis PRIMARY KEY(id_laboratory, id_analysis_type)
);

CREATE TABLE hospital_laboratory (
	id_hospital INT unsigned not null,
	id_laboratory INT unsigned not null,
	contract_number INT unsigned not null,
	since_ DATE not null,
	to_ DATE not null,
	
	CONSTRAINT fk_hospital_laboratory_hospital FOREIGN KEY(id_hospital) REFERENCES medical_facility(id) ON DELETE CASCADE,
	CONSTRAINT fk_hospital_laboratory_laboratory FOREIGN KEY(id_laboratory) REFERENCES laboratory(id) ON DELETE CASCADE,
	CONSTRAINT pk_hospital_laboratory PRIMARY KEY(id_hospital, id_laboratory)
);
	
CREATE TABLE orderly_for_hospital_doctor (
    id_doctor INT unsigned NOT NULL,
    since_ datetime NOT NULL,
    to_ datetime NOT NULL,

    FOREIGN KEY(id_doctor) REFERENCES staff(id) ON DELETE CASCADE,
    CONSTRAINT pk_order_for_hospital_doctor PRIMARY KEY(id_doctor,since_,to_)
);


CREATE TABLE patiente_in_hospital (
    id_patience INT unsigned NOT NULL,
    id_medical_facility INT unsigned NOT NULL,
    id_room INT unsigned DEFAULT null,
    id_doctor INT unsigned not null,
    id_disease INT unsigned not null,
    since_ DATETIME NOT NULL,
    to_ DATETIME NOT NULL,
    
    
    FOREIGN KEY(id_patience)
        REFERENCES patience(id) ON DELETE CASCADE,
    FOREIGN KEY( id_room)
        REFERENCES room( id) ON DELETE CASCADE,
	foreign key(id_disease) references disease(id) ON DELETE CASCADE,
    FOREIGN KEY(id_medical_facility) references medical_facility(id) ON DELETE CASCADE,
    FOREIGN KEY(id_doctor) references staff(id) ON DELETE CASCADE,
    primary key(id_patience,id_disease,since_,to_)
);
CREATE TABLE appointment(
    id_doctor int unsigned not null,
    id_patience int unsigned not null,
    complaints VARCHAR(100) DEFAULT null,
    id_disease int unsigned DEFAULT null,
    id_medical_facility int unsigned not null,
    since_ DATETIME not null,
    to_ DATETIME not null,

    FOREIGN KEY(id_doctor) REFERENCES staff(id) ON DELETE CASCADE,
    FOREIGN KEY(id_patience) REFERENCES patience(id) ON DELETE CASCADE,
    FOREIGN KEY(id_disease) REFERENCES disease(id) ON DELETE CASCADE,
    FOREIGN KEY(id_medical_facility) REFERENCES medical_facility(id) ON DELETE CASCADE,
    PRIMARY KEY(id_doctor,id_patience,since_,to_)
);
/* CHECk IN HOSPITAL ONLY ONE doctor*/
/* CHECk DOCTOR and working in this medical facility*/
/* checK patience is exuding now*/ 
/*CREATE TABLE attending_doctor(
    id_staff int unsigned not null,
    id_patience int unsigned not null,
    id_disease int unsigned not null,
    since_ datetime not null,
    to_ datetime not null,

    FOREIGN KEY(id_staff) REFERENCES staff(id) ON DELETE CASCADE,
    FOREIGN KEY(id_patience) REFERENCES patience(id) ON DELETE CASCADE,
    FOREIGN KEY(id_disease) REFERENCES disease(id) ON DELETE CASCADE,
    PRIMARY KEY(id_staff,id_patience,id_disease,since_)
);*/
/* CHECH STAFF SPECIALIZATION PERMISSION*/
CREATE TABLE operations(
    id_staff int unsigned not null,
    id_patience int unsigned not null,
    id_disease int unsigned not null,
    id_medical_facility int unsigned not null,
    since_ datetime not null,
    to_ datetime not null,

    FOREIGN KEY(id_staff) REFERENCES staff(id) ON DELETE CASCADE,
    FOREIGN KEY(id_patience) REFERENCES patience(id) ON DELETE CASCADE,
    FOREIGN KEY(id_disease) references disease(id) ON DELETE CASCADE,
    FOREIGN KEY(id_medical_facility) references medical_facility(id) ON DELETE CASCADE,
    PRIMARY KEY(id_staff,id_patience,id_disease,since_)
);
