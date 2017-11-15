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

delimiter $$
use medicine$$
drop function if exists checkDoctorById$$
CREATE FUNCTION checkDoctorById(id int)
RETURNS bool
BEGIN
	DECLARE result bool;
		select staff_specialization.is_Doctor into result
        from staff Inner join staff_specialization ON staff.id= staff_specialization.id_staff
        where staff.id = id;
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
