/* ERROR */
delimiter $$
use medicine$$
CREATE TRIGGER before_insert_data_check
BEFORE INSERT ON occupied_beds FOR each row
BEGIN 
IF (NEW.since_ > NEW.to_) THEN
	 insert into error_because_of_the_trigger_check_since_later_then_to values ();
END IF;
END;
$$
delimiter ;

delimiter $$
use medicine$$
CREATE TRIGGER before_insert_data_check_orderly_doc
BEFORE INSERT ON orderly_for_hospital_doctor FOR each row
BEGIN 
IF (NEW.since_ > NEW.to_) THEN
	 insert into error_because_of_the_trigger_check_since_later_then_to values ();
END IF;
END;
$$
delimiter ;

delimiter $$
use medicine$$
CREATE TRIGGER before_insert_data_check_occupied_beds
BEFORE INSERT ON before_insert_data_check_occupied_beds FOR each row
BEGIN 
IF (NEW.since_ > NEW.to_) THEN
	 insert into error_because_of_the_trigger_check_since_later_then_to values ();
END IF;
END;
$$
delimiter ;

delimiter $$
use medicine$$
CREATE TRIGGER before_insert_data_check_hospital_lab
BEFORE INSERT ON hospital_laboratory FOR each row
BEGIN 
IF (NEW.since_ > NEW.to_) THEN
	 insert into error_because_of_the_trigger_check_since_later_then_to values ();
END IF;
END;
$$
delimiter ;

delimiter $$
use medicine$$
CREATE TRIGGER before_insert_data_check_patience_in_hosp
BEFORE INSERT ON patiente_in_hospital FOR each row
BEGIN 
IF (NEW.since_ > NEW.to_) THEN
	 insert into error_because_of_the_trigger_check_since_later_then_to values ();
END IF;
END;
$$
delimiter ;

/* CHECH FULL ROOM*/
delimiter $$
use medicine$$
CREATE trigger before_insert_beds_count
BEFORE INSERT ON occupied_beds for each row
BEGIN
	declare occup_beds int;
    SELECT COUNT(occupied_beds.id)
    INTO occup_beds
    FROM room INNER JOIN occupied_beds ON room.id = occupied_beds.id_room
    where room.id = NEW.id_room;
    
    IF(occup_beds >= (SELECT room.number_of_beds
					FROM room
					WHERE room.id = NEW.id_room)) THEN
			insert into error_room_is_full() values();	
	END IF;
END;
$$

/* CHECH SUPERIOR HOSPITAL IN HOSPITAL */
delimiter $$
use medicine$$
drop trigger check_superior_on_medical_facility$$
create trigger check_superior_on_medical_facility
BEFORE INSERT ON medical_facility
FOR EACH ROW
begin
	if(NEW.medical_facility_type = 2 ) THEN
		if(!isnull(NEW.superior_medical_facility)) THEN
			insert into ERROR_hospital_cannot_have_superior values();
		END IF;
	END IF;
END
$$
delimiter ;

/*CHECK NUMBER OF JOBS*/
delimiter $$
use medicine$$
drop trigger check_count_place_of_work$$
create trigger check_count_place_of_work
BEFORE INSERT ON place_of_work
FOR EACH ROW
begin
	IF(2<=(SELECT COUNT(place_of_work.id_staff)
			FROM place_of_work
			where place_of_work.id_staff  =  NEW.id_staff)) THEN
		insert into ERROR_staff_already_has_two_jobs values();
	ELSE IF((SELECT medical_facility.medical_facility_type FROM medical_facility WHERE medical_facility.id = NEW.id_medical_facility) = (SELECT medical_facility.medical_facility_type
										FROM medical_facility INNER JOIN  place_of_work 
											ON medical_facility.id = place_of_work.id_medical_facility
										WHERE place_of_work.id_staff = NEW.id_staff)) THEN
			insert into ERROR_staff_already_have_ajob_in_this_type_of_medical_facility values();
            END IF;
	END IF;
END;
$$

delimiter ; 

delimiter $$
use medicine$$
drop trigger insert_check_degree$$
create trigger insert_check_degree
BEFORE INSERT ON  specialty FOR EACH ROW
BEGIN 
IF(NEW.is_Doctor = false AND (!isnull(NEW.degree) OR !isnull(NEW.grade)))THEN
	insert into ERROR_only_doctor_can_have_a_degree_or_grade VALUES();
END IF;
IF(NEW.degree = 1 AND NEW.grade = 2 AND NEW.is_Doctor = true) THEN
	insert into ERROR_this_doctor_cannot_be_a_professor VALUES();
END IF;
IF(!isnull(NEW.grade)	AND isnull(NEW.degree) AND NEW.is_Doctor = true) THEN
	insert into ERROR_this_doctor_cannot_has_a_grade VALUES();
END IF;
END;
$$
	
delimiter $$
use medicine$$
drop trigger  update_check_degree$$
create trigger update_check_degree
BEFORE UPDATE ON  specialty FOR EACH ROW
BEGIN 
IF(NEW.is_Doctor = false AND (!isnull(NEW.degree) OR !isnull(NEW.grade)))THEN
	insert into ERROR_only_doctor_can_have_a_degree_or_grade VALUES();
END IF;
IF(NEW.degree = 1 AND NEW.grade = 2 AND NEW.is_Doctor = true) THEN
	insert into ERROR_this_doctor_cannot_be_a_professor VALUES();
END IF;
IF(!isnull(NEW.grade)	AND isnull(NEW.degree) AND NEW.is_Doctor = true) THEN
	insert into ERROR_this_doctor_cannot_has_a_grade VALUES();
END IF;
END;
$$
	

delimiter $$
use medicine$$
drop trigger  check_full_room$$
create trigger check_full_room
BEFORE INSERT ON  occupied_beds FOR EACH ROW
BEGIN 
IF(getFreeBeds( NEW.id_room)=0)THEN
	insert into ERROR_room_is_full VALUES();
END IF;
END;
$$

delimiter $$
use medicine$$
drop trigger  check_workplace$$
create trigger check_workplace
BEFORE INSERT ON  office FOR EACH ROW
BEGIN 
IF(isnull((SELECT DISTINCT place_of_work.id_staff
							FROM place_of_work
							WHERE place_of_work.id_staff = NEW.id_responsible_doctor AND  place_of_work.id_department = NEW.id_department AND NOW() BETWEEN place_of_work.since_ AND place_of_work.to_ )))THEN
	insert into ERROR_Doctor_does_not_work_there VALUES();
END IF;
END;

$$
delimiter $$
use medicine$$
drop trigger  appointment_workplace$$
create trigger appointment_workplace
BEFORE INSERT ON  appointment FOR EACH ROW
BEGIN 
IF(isnull((SELECT DISTINCT office.id
							FROM office
							WHERE office.id = NEW.id_office AND  office.id_responsible_doctor = NEW.id_doctor)))THEN
	insert into ERROR_Doctor_does_not_work_in_this_office VALUES();
END IF;
END;
$$

$$
delimiter $$
use medicine$$
drop trigger  check_hospital_lab_contract$$
create trigger check_hospital_lab_contract
BEFORE INSERT ON  survey FOR EACH ROW
BEGIN 
IF(isnull((SELECT DISTINCT hospital_laboratory.id_hospital
							FROM hospital_laboratory
							WHERE hospital_laboratory.id_hospital = NEW.id_medical_facility AND  hospital_laboratory.id_laboratry = NEW.id_laboratory AND NOW() BETWEEN hospital_laboratory.since_ AND hospital_laboratory.to_)))THEN
	insert into ERROR_Med_Facility_and_laboratory_doesnot_have_a_contract VALUES();
END IF;
END;
$$
/* триггер на место работы  провека rate*/