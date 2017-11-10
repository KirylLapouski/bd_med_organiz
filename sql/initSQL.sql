DROP DATABASE IF EXISTS medicine;
CREATE DATABASE medicine;

use medicine;
CREATE TABLE staff (
    id int unsigned not null auto_increment,
    FIO VARCHAR(100) not null,
    /*-- учёная степень --*/

    CONSTRAINT pk_staff PRIMARY KEY (id)
);
CREATE TABLE staff_shedule(
    staff_id int unsigned not null,
    since_ DATETIME not null,
    to_ DATETIME not null,

    CONSTRAINT pk_staff_shedule PRIMARY KEY(staff_id, since_,to_),
    CONSTRAINT fk_staff_shedule_staff_id FOREIGN KEY(staff_id) REFERENCES staff(id)
);
CREATE TABLE medical_facility(
    id Int unsigned not null auto_increment,
    name varchar(100) not null,
    address varchar(200) not null,
    superior_medical_facility Int unsigned  DEFAULT  NULL,
    id_order_doctor int unsigned not null,
    medical_facility_type ENUM('Поликлиника','Больница') NOT NULL,

    constraint pk_medical_facility primary key (id),
    CONSTRAINT fk_id_doctor_department FOREIGN KEY(id_order_doctor) REFERENCES staff(id),
    constraint fk_superior_medical_facility foreign key (superior_medical_facility) references medical_facility(id)
);
CREATE TABLE housing(
    id int unsigned not null auto_increment,
    name varchar(100) not null,
    address VARCHAR(200) not null,
    medical_facility_id Int unsigned not null,
	
    CONSTRAINT pk_housing PRIMARY KEY(id),
    CONSTRAINT fk_hospital_id FOREIGN KEY(medical_facility_id) REFERENCES medical_facility(id)
);

CREATE TABLE department (
    id int unsigned not null auto_increment,
    name VARCHAR(100) not null,
    housing_id Int unsigned not null,


    CONSTRAINT pk_department PRIMARY KEY(id),
    CONSTRAINT fk_housing_id FOREIGN KEY(housing_id) REFERENCES housing(id)
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
	
	CONSTRAINT fk_department FOREIGN KEY(id_department) REFERENCES department(id),
	CONSTRAINT fk_disease FOREIGN KEY(id_disease) REFERENCES disease(id),
    CONSTRAINT pk_dep_spec PRIMARY KEY(id_department,id_disease)
);

CREATE TABLE specialty (
    id  INT unsigned not null auto_increment,
    name VARCHAR(100) not null,

    CONSTRAINT pk_specialty PRIMARY KEY (id)
);

CREATE TABLE staff_specialization (
    id_staff int unsigned not null,
    id_specialty INT unsigned not null,
    is_Doctor BOOLEAN not null DEFAULT false,
    salary FLOAT not null,
    degree ENUM('Кандидат медицинских наук','Доктор медицинских наук') DEFAULT null,
    grade ENUM('Доцент','Профессор') DEFAULT null,

	CONSTRAINT fk_staff FOREIGN KEY(id_staff) REFERENCES staff(id),
	CONSTRAINT fk_specialty FOREIGN KEY(id_specialty) REFERENCES specialty(id),
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
    to_ datetime not null,

	CONSTRAINT fk_place_of_work_staff FOREIGN KEY(id_staff) REFERENCES staff(id),
	CONSTRAINT fk_place_of_work_medical_facility FOREIGN KEY(id_medical_facility) REFERENCES medical_facility(id),
	CONSTRAINT fk_place_of_work_position FOREIGN KEY(id_position) REFERENCES position_(id),
    CONSTRAINT fk_place_of_work_department FOREIGN KEY(id_department) references department(id),
    CONSTRAINT pk_place_of_work PRIMARY KEY ( id_staff,id_medical_facility,id_position, id_department)
);

CREATE TABLE room (
	id int unsigned not null auto_increment,
	room_number int unsigned not null,
	number_of_beds int unsigned,
	id_department INT unsigned not null,
    id_responsible_doctor INT unsigned not null,
	
    FOREIGN KEY (id_department) REFERENCES department(id),
	CONSTRAINT pk_room PRIMARY KEY (id)
);
CREATE TABLE office (
    id int unsigned not null auto_increment,
    id_department int unsigned not null,
    id_responsible_doctor int unsigned not null,

    FOREIGN KEY(id_department) REFERENCES department(id),
    FOREIGN KEY(id_responsible_doctor) REFERENCES staff(id),
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
	since_ datetime not null,
	to_ datetime not null,
	
	CONSTRAINT fk_room_id FOREIGN KEY(id_room) REFERENCES room(id),
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
		id_spec int unsigned not null,
	
	CONSTRAINT pk_laboratory PRIMARY KEY(id),
	CONSTRAINT fk_spec_id FOREIGN KEY (id_spec) REFERENCES laboratory_spec(id)
);



CREATE TABLE analysis (
	id INT unsigned not null auto_increment,
	name VARCHAR(100) not null,
	
	CONSTRAINT pk_analysis PRIMARY KEY(id)
);

CREATE TABLE laboratory_analysis (
	id_laboratory INT unsigned not null,
	id_analysis INT unsigned not null,
	
	CONSTRAINT fk_analysis FOREIGN KEY(id_analysis) REFERENCES analysis(id),
	CONSTRAINT fk_laboratory FOREIGN KEY(id_laboratory) REFERENCES laboratory(id),
	CONSTRAINT pk_laboratory_analysis PRIMARY KEY(id_laboratory, id_analysis)
);

CREATE TABLE hospital_laboratory (
	id_hospital INT unsigned not null,
	id_laboratory INT unsigned not null,
	contract_number INT unsigned not null,
	since_ DATE not null,
	to_ DATE not null,
	
	CONSTRAINT fk_hospital_laboratory_hospital FOREIGN KEY(id_hospital) REFERENCES medical_facility(id),
	CONSTRAINT fk_hospital_laboratory_laboratory FOREIGN KEY(id_laboratory) REFERENCES laboratory(id),
	CONSTRAINT pk_hospital_laboratory PRIMARY KEY(id_hospital, id_laboratory)
);
	
CREATE TABLE orderly_for_hospital_doctor (
    id_doctor INT unsigned NOT NULL,
    since_ datetime NOT NULL,
    to_ datetime NOT NULL,

    FOREIGN KEY(id_doctor) REFERENCES staff(id),
    CONSTRAINT pk_order_for_hospital_doctor PRIMARY KEY(id_doctor,since_,to_)
);

CREATE TABLE patience (
    id INT unsigned NOT NULL,
	FIO varchar(100) not null,
    
    primary key(id)
);
CREATE TABLE patiente_in_hospital (
    id_patience INT unsigned NOT NULL,
    id_medical_facility INT unsigned NOT NULL,
    id_room INT unsigned DEFAULT null,
    id_disease INT unsigned not null,
    since_ DATETIME NOT NULL,
    to_ DATETIME NOT NULL,
    
    
    FOREIGN KEY(id_patience)
        REFERENCES patience(id),
    FOREIGN KEY( id_room)
        REFERENCES room( id),
	foreign key(id_disease) references disease(id),
    FOREIGN KEY(id_medical_facility) references medical_facility(id),

    primary key(id_patience,id_disease,since_,to_)
);
CREATE TABLE appointment(
    id_doctor int unsigned not null,
    id_patience int unsigned not null,
    complaints VARCHAR(100) DEFAULT null,
    id_disease int unsigned DEFAULT null,
    since_ DATETIME not null,
    to_ DATETIME not null,

    FOREIGN KEY(id_doctor) REFERENCES staff(id),
    FOREIGN KEY(id_patience) REFERENCES patience(id),
    FOREIGN KEY(id_disease) REFERENCES disease(id),
    PRIMARY KEY(id_doctor,id_patience,since_,to_)
);
/* CHECk IN HOSPITAL ONLY ONE doctor*/
/* CHECk DOCTOR and working in this medical facility*/
/* checK patience is exuding now*/ 
CREATE TABLE attending_doctor(
    id_staff int unsigned not null,
    id_patience int unsigned not null,
    id_disease int unsigned not null,
    since_ datetime not null,
    to_ datetime not null,

    FOREIGN KEY(id_staff) REFERENCES staff(id),
    FOREIGN KEY(id_patience) REFERENCES patience(id),
    FOREIGN KEY(id_disease) REFERENCES disease(id),
    PRIMARY KEY(id_staff,id_patience,id_disease,since_)
);
CREATE TABLE patience_analysis(
    id_patience int unsigned not null,
    id_analysis int unsigned not null,

    FOREIGN KEY(id_patience) references patience(id),
    FOREIGN KEY(id_analysis) references analysis(id),
    PRIMARY KEY(id_patience,id_analysis)
);
/* CHECH STAFF SPECIALIZATION PERMISSION*/
CREATE TABLE operations(
    id_staff int unsigned not null,
    id_patience int unsigned not null,
    id_disease int unsigned not null,
    since_ datetime not null,
    to_ datetime not null,

    FOREIGN KEY(id_staff) REFERENCES staff(id),
    FOREIGN KEY(id_patience) REFERENCES patience(id),
    FOREIGN KEY(id_disease) references disease(id),
    PRIMARY KEY(id_staff,id_patience,id_disease,since_)
);
/* Кабинеты в поликлинике*/
INSERT INTO staff(FIO)
                        VALUES("Титова Галина Вячеславовна"),
                            ("Ефимов Дмитрий Демьянович"),
                            ("Боброва Тамара Глебовна"),
                            ("Некрасов Александр Мэлсович"),
                            ("Евдокимова Регина Кондратовна");
INSERT INTO medical_facility(name,address,superior_medical_facility,id_doctor,medical_facility_type)
                        VALUES("Hospital 1","Kolasa st. 89 ",null,1,2 ),
                            ("polyclinic 1","Molodegnaya st. 12", 1,1,1);

INSERT INTO housing(name, address,medical_facility_id)
                        VALUES("housing 1","Kolasa st. 89, housing 1", 1),
                            ("housing 2","Kolasa st. 89, housing 2", 1),
                            ("housing 1","Molodegnaya st. 12, housing 1",2);
INSERT INTO department(name, housing_id) 
                        VALUES("infectious disease department",1),
                            ("cardiology department",2),
                            ("pulmonology department",2);
INSERT INTO room(room_number,number_of_beds,id_department) 
                        VALUES(100,5,1),
                            (101,6,1),
                            (102,7,1),
                            (100,6,2),
                            (101,7,2),
                            (102,7,2),
                            (201,6,3),
                            (202,4,3),
                            (203,5,3);
/* ПРОВЕРИТЬ ЧТОБЫ НЕЛЬЗЯ БЫЛО ВСТАВИТЬ КОЛИЧЕСВТО КРОВАТЕЙ БОЛЬШЕ ЧЕМ В ПАЛАТЕ */
/* ПРОВЕРИТЬ ЧТОБЫ ДАТА TO_ БЫЛА ПОЗЖА ЧЕМ ДАТА since_ */
/* ПО ID staff ПРОВЕРИТЬ ВРАЧ ЛИ ЭТО */
INSERT INTO occupied_beds(id_room,since_,to_) 
                         VALUES(1,"2017-01-01 12:00:00","2017-01-10 12:00:00"),
                            (1,"2017-11-03 12:00:00","2017-11-10 12:00:00"),
                            (1,"2017-11-03 12:00:00","2017-11-10 12:00:00"),
                            (1,"2017-11-03 12:00:00","2017-11-10 12:00:00"),
                            (1,"2017-11-03 12:00:00","2017-11-10 12:00:00"),
                            (2,"2017-11-01 12:00:00","2017-11-30 12:00:00"),
                            (2,"2017-11-01 12:00:00","2017-11-30 12:00:00"),
                            (2,"2017-11-01 12:00:00","2017-11-30 12:00:00"),
                            (2,"2017-11-01 12:00:00","2017-11-30 12:00:00"),
                            (2,"2017-11-01 12:00:00","2017-11-30 12:00:00"),
                            (3,"2017-12-01 12:00:00","2017-12-31 12:00:00"),
                            (3,"2017-12-01 12:00:00","2017-12-31 12:00:00"),
                            (3,"2017-12-01 12:00:00","2017-12-31 12:00:00"),
                            (3,"2017-12-01 12:00:00","2017-12-31 12:00:00"); 

INSERT INTO specialty(name)
                        VALUES("врач-пульмонолог"),
                            ("врач-кардиолог"),
                            ("врач-эндокринолог");
INSERT INTO staff_specialization(id_staff,id_specialty) 
                        VALUES(1,1),
                            (1,2),
                            (2,1),
                            (3,3),
                            (4,2),
                            (5,2);
/*ВРАЧ ДЕЖУРИТ ПО ВСЕЙ БОЛЬНИЦЕ*/
INSERT INTO orderly_doctor(id_doctor,since_,to_)
                        VALUES(1,"2017-11-03 08:00:00","2017-11-03 16:00:00"),
                            (4,"2017-11-03 16:00:00","2017-11-04 00:00:00"),
                            (5,"2017-11-04 00:00:00","2017-11-04 08:00:00"),
                            (1,"2017-11-04 08:00:00","2017-11-04 16:00:00"),
                            (2,"2017-11-04 16:00:00","2017-11-05 00:00:00");
INSERT INTO position_(name)
                        VALUES("глав. врач"),
                            ("врач-ординатор"),
                            ("зав. отделением");
INSERT INTO place_of_work(id_staff,id_medical_facility,id_position,id_department,rate)
                         VALUES(1, 1, 1, 2,1),
                            (2,1,2,3,1),
                            (4,1,2,2,1),
                            (5,1,2,2,1);