delimiter $$
use medicine$$
drop function if exists getOrderleDoctors$$
CREATE FUNCTION getOrderleDoctors()
RETURNS INT
BEGIN

	DECLARE doctor_id INT;
    
    SELECT id_doctor into doctor_id
    FROM orderly_for_hospital_doctor as a
    WHERE NOW() BETWEEN a.since_ AND a.to_;
    
    RETURN doctor_id;

END;
$$
delimiter ;


delimiter $$
use medicine$$
drop function getFreeBeds$$
CREATE FUNCTION getFreeBeds(room_id int)
RETURNS INT
BEGIN
    DECLARE occupied_beds INT;
    DECLARE free_beds INT;
		SELECT 
    room.number_of_beds
INTO free_beds FROM
    room
WHERE
    room.id = room_id;
        
		SELECT 
    COUNT(occupied_beds.id)
INTO occupied_beds FROM
    room
        INNER JOIN
    occupied_beds ON room.id = occupied_beds.id_room
WHERE
    room.id = room_id;
 
    RETURN free_beds -occupied_beds;
END;
$$
delimiter ;
/*FIRST SPECIALIZATION*/ 
delimiter $$
use medicine$$
drop function if exists checkDoctorById$$
CREATE FUNCTION checkDoctorById(id int)
RETURNS bool
BEGIN
	DECLARE result bool;
    SET result = false;
		select specialty.is_Doctor  into result
        from staff Inner join staff_specialization ON staff.id= staff_specialization.id_staff
                    INNER JOIN specialty ON staff_specialization.id_specialty = specialty.id
        where staff.id = id and  specialty.is_Doctor = true;
    RETURN result;
END;
$$
delimiter ;

delimiter $$
use medicine$$
drop procedure if exists workingStaff$$
CREATE procedure workingStaff()
BEGIN

    select staff.id
    from staff inner join staff_shedule ON staff.id = staff_shedule.staff_id
    where NOW() between staff_shedule.since_ AND staff_shedule.to_;

END;
$$
delimiter ;

delimiter $$
use medicine$$
drop procedure if exists createUser$$
CREATE procedure createUser(IN userName varchar(100),IN pass varchar(100))
BEGIN
	declare template VARCHAR(100);
    
    SET @template = concat("CREATE USER ", userName,"@\'localhost\' IDENTIFIED BY \'",pass,"\'");
	prepare query1 from @template;
    EXECUTE query1;
    DEALLOCATE PREPARE query1;
END;
$$
delimiter ;

delimiter $$
use medicine$$
drop procedure if exists deleteUser$$
CREATE procedure deleteUser(IN userName varchar(100))
BEGIN
	declare template VARCHAR(100);
    
    SET @template = concat("drop USER ", userName,"@\'localhost\'");
	prepare query1 from @template;
    EXECUTE query1;
    DEALLOCATE PREPARE query1;
END;
$$
delimiter ;

delimiter $$
use medicine$$
drop procedure if exists addPermissionForUserOnTable$$
CREATE procedure addPermissionForUserOnTable(IN userName varchar(100), IN tableName varchar(100))
BEGIN
	declare template VARCHAR(100);
    
    SET @template = concat("GRANT ALL PRIVILEGES ON medicine.", tableName," TO ", userName,"@\'localhost\'; ");
	prepare query1 from @template;
    EXECUTE query1;
    DEALLOCATE PREPARE query1;
END;
$$
delimiter ;

delimiter $$
use medicine$$
drop procedure if exists deletePermissionForUserOnTable$$
CREATE procedure deletePermissionForUserOnTable(IN userName varchar(100), IN tableName varchar(100))
BEGIN
	declare template VARCHAR(100);
    
    SET @template = concat("REVOKE ALL PRIVILEGES ON medicine.", tableName," FROM ", userName,"@\'localhost\'; ");
	prepare query1 from @template;
    EXECUTE query1;
    DEALLOCATE PREPARE query1;
END;
$$
delimiter ;

delimiter $$
use medicine$$
drop procedure if exists selectLabarantTasks$$
CREATE procedure selectLabarantTasks(IN laboratory int)
BEGIN
Select distinct survey.id_analysis as Analysis, type_of_analysis.name as Type,survey.id_medical_facility as Medical_Facility, patience.FIO as Patience, survey.since_ as Since
 FROM survey INNER JOIN analysis ON survey.id_analysis = analysis.id
					INNER JOIN type_of_analysis ON type_of_analysis.id = analysis.id_type_of_analysis
					INNER JOIN patience ON analysis.id_patience = patience.id
			WHERE survey.id_laboratory = laboratory and isnull(survey.to_);
END;
$$
delimiter ;

delimiter $$
use medicine$$
drop procedure if exists selectDoctorTasks$$
CREATE procedure selectDoctorTasks(IN doctor int)
BEGIN
Select distinct patience.id as ID_patience, patience.FIO as Patience,appointment.complaints as Complaints , appointment.since_ as Since
 FROM appointment 
					INNER JOIN patience ON appointment.id_patience = patience.id
			WHERE appointment.id_doctor = doctor and isnull(appointment.to_);
END;
$$
delimiter ;