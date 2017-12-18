delimiter $$
use medicine$$
/* 1 start */
CREATE procedure query1(IN idspecialty int, IN medicalFacility int)
BEGIN
SELECT DISTINCT staff.id, staff.FIO
FROM place_of_work INNER JOIN staff ON place_of_work.id_staff = staff.id 
                        INNER JOIN staff_specialization ON staff_specialization.id_staff = staff.id 
                        INNER JOIN specialty ON specialty.id= staff_specialization.id_specialty
WHERE staff_specialization.id_specialty = idspecialty and place_of_work.id_medical_facility = medicalFacility and specialty.is_Doctor = true;
END;
$$
delimiter ;

delimiter $$
CREATE procedure query1WithoutMedicalFacility(IN idspecialty int)
BEGIN
SELECT DISTINCT staff.id, staff.FIO
FROM place_of_work INNER JOIN staff ON place_of_work.id_staff = staff.id 
                        INNER JOIN staff_specialization ON staff_specialization.id_staff = staff.id 
                        INNER JOIN specialty ON specialty.id= staff_specialization.id_specialty
WHERE staff_specialization.id_specialty = idspecialty and specialty.is_Doctor = true;
END;
$$
delimiter ;
/* 1 end */

/* 2 start */
delimiter $$
CREATE procedure query2(IN idspecialty int,IN medicalFacility int)
BEGIN
SELECT DISTINCT staff.id, staff.FIO
FROM place_of_work INNER JOIN staff ON place_of_work.id_staff = staff.id 
                        INNER JOIN staff_specialization ON staff_specialization.id_staff = staff.id 
                        INNER JOIN specialty ON specialty.id= staff_specialization.id_specialty
WHERE staff_specialization.id_specialty = idspecialty and place_of_work.id_medical_facility = medicalFacility and specialty.is_Doctor = false;
END;
$$
delimiter ;


delimiter $$
CREATE procedure query2WithoutMedicalfacility(IN idspecialty int)
BEGIN
SELECT DISTINCT staff.id, staff.FIO
FROM place_of_work INNER JOIN staff ON place_of_work.id_staff = staff.id 
                        INNER JOIN staff_specialization ON staff_specialization.id_staff = staff.id 
                        INNER JOIN specialty ON specialty.id= staff_specialization.id_specialty
WHERE staff_specialization.id_specialty = idspecialty and specialty.is_Doctor = false;
END;
$$
delimiter ;
/* 2 end */

/* 3 start */
delimiter $$
CREATE procedure query3(IN idspecialty int,IN medicalFacility int,IN countOfOperations int)
BEGIN
SELECT staff.id, staff.FIO, COUNT(operations.since_)
FROM operations INNER JOIN staff ON staff.id =  operations.id_staff
                INNER JOIN staff_specialization ON staff.id = staff_specialization.id_staff
where operations.id_medical_facility = medicalFacility and staff_specialization.id_specialty = idspecialty
GROUP BY operations.id_staff
HAVING COUNT(operations.since_)>= countOfOperations;
END;
$$
delimiter ;

delimiter $$
CREATE procedure query3WithoutMedicalFAcility(IN idspecialty int,IN countOfOperations int)
BEGIN
SELECT staff.id, staff.FIO, COUNT(operations.since_)
FROM operations INNER JOIN staff ON staff.id =  operations.id_staff
                INNER JOIN staff_specialization ON staff.id = staff_specialization.id_staff
where staff_specialization.id_specialty = idspecialty
GROUP BY operations.id_staff
HAVING COUNT(operations.since_)>= countOfOperations;
END;
$$
delimiter ;

/* 3 end */

/* 4 start */
delimiter $$
CREATE procedure query4(IN idspecialty int,IN medicalFacility int)
BEGIN
SELECT place_of_work.id_staff, SUM(YEAR(place_of_work.to_)) -SUM(YEAR(place_of_work.since_)) as experience
FROM place_of_work INNER JOIN staff ON staff.id =place_of_work.id_staff
                INNER JOIN staff_specialization on staff.id = staff_specialization.id_staff
WHERE place_of_work.id_medical_facility = medicalFacility and staff_specialization.id_specialty = idspecialty
GROUP BY place_of_work.id_staff;
END;
$$
delimiter ;

delimiter $$
CREATE procedure query4WithoutMedicalFacility(IN idspecialty int)
BEGIN
SELECT place_of_work.id_staff, SUM(YEAR(place_of_work.to_)) -SUM(YEAR(place_of_work.since_)) as experience
FROM place_of_work INNER JOIN staff ON staff.id =place_of_work.id_staff
                INNER JOIN staff_specialization on staff.id = staff_specialization.id_staff
WHERE staff_specialization.id_specialty = idspecialty
GROUP BY place_of_work.id_staff;
END;
$$
delimiter ;
/* 4 end */

/* 5 start */
delimiter $$
CREATE procedure query5(IN idspecialty int,IN medicalFacility int)
BEGIN
SELECT staff.id, staff.FIO, specialty.degree,specialty.grade
FROM staff INNER JOIN staff_specialization ON staff.id = staff_specialization.id_staff
        INNER JOIN specialty ON specialty.id  = staff_specialization.id_specialty
        INNER JOIN place_of_work ON place_of_work.id_staff = staff.id
WHERE place_of_work.id_medical_facility = medicalFacility and  specialty.id = idspecialty;
END;
$$
delimiter ;

delimiter $$
CREATE procedure query5WithoutMedicalFacility(IN idspecialty int)
BEGIN
SELECT staff.id, staff.FIO, specialty.degree,specialty.grade
FROM staff INNER JOIN staff_specialization ON staff.id = staff_specialization.id_staff
        INNER JOIN specialty ON specialty.id  = staff_specialization.id_specialty
        INNER JOIN place_of_work ON place_of_work.id_staff = staff.id
WHERE  specialty.id = idspecialty;
END;
$$
/* 5 end */

/* 6 start */
delimiter $$
CREATE procedure query6()
BEGIN
SELECT DISTINCT patiente_in_hospital.id_patience, patience.FIO, patiente_in_hospital.since_,patiente_in_hospital.id_doctor,patiente_in_hospital.complaints_at_admission
FROM patiente_in_hospital INNER JOIN patience ON patience.id = patiente_in_hospital.id_patience;
END;
$$
delimiter ;

delimiter $$
CREATE procedure query6MedicalFacility(IN medicalFacility int)
BEGIN
SELECT DISTINCT patiente_in_hospital.id_patience, patience.FIO, patiente_in_hospital.since_,patiente_in_hospital.id_doctor,patiente_in_hospital.complaints_at_admission
FROM patiente_in_hospital INNER JOIN patience ON patience.id = patiente_in_hospital.id_patience
WHERE patiente_in_hospital.id_medical_facility = medicalFacility;
END;
$$
delimiter ;

delimiter $$
CREATE procedure query6Department(IN department int)
BEGIN
SELECT DISTINCT patiente_in_hospital.id_patience, patience.FIO, patiente_in_hospital.since_,patiente_in_hospital.id_doctor,patiente_in_hospital.complaints_at_admission
FROM patiente_in_hospital INNER JOIN patience ON patience.id = patiente_in_hospital.id_patience
                        INNER JOIN medical_facility ON medical_facility.id = patiente_in_hospital.id_medical_facility
                        INNER JOIN housing ON housing.medical_facility_id = medical_facility.id 
                        INNER JOIN department ON department.housing_id = housing.id 
WHERE department.id = department;
END;
$$
delimiter ;

delimiter $$
CREATE procedure query6Room(IN room int)
BEGIN
SELECT DISTINCT patiente_in_hospital.id_patience, patience.FIO, patiente_in_hospital.since_,patiente_in_hospital.id_doctor,patiente_in_hospital.complaints_at_admission
FROM patiente_in_hospital INNER JOIN patience ON patience.id = patiente_in_hospital.id_patience
                        INNER JOIN medical_facility ON medical_facility.id = patiente_in_hospital.id_medical_facility
                        INNER JOIN housing ON housing.medical_facility_id = medical_facility.id 
                        INNER JOIN department ON department.housing_id = housing.id 
                        INNER JOIN room ON room.id_department = department.id 
WHERE room.id = room;
END;
$$
delimiter ;
/* 6 end */

/* 7 start */
delimiter $$
CREATE procedure query7(IN medicalFacility int,IN firstTime Date,IN secondFirst Date)
BEGIN
SELECT patiente_in_hospital.id_patience, patience.FIO 
FROM patiente_in_hospital INNER JOIN patience ON patience.id = patiente_in_hospital.id_patience
WHERE patiente_in_hospital.id_medical_facility = medicalFacility and patiente_in_hospital.type ="Cтационарное лечение" and patiente_in_hospital.to_ BETWEEN firstTime and secondFirst;
END;
$$
delimiter ;

delimiter $$
CREATE procedure query7Doctor(IN doctor int,IN firstTime Date,IN secondFirst Date)
BEGIN
SELECT patiente_in_hospital.id_patience, patience.FIO 
FROM patiente_in_hospital INNER JOIN patience ON patience.id = patiente_in_hospital.id_patience
WHERE patiente_in_hospital.id_doctor = doctor and patiente_in_hospital.type ="Cтационарное лечение" and patiente_in_hospital.to_ BETWEEN firstTime and secondFirst;
END;
$$
delimiter ;
/* 7 end */

/* 8 start */
delimiter $$
CREATE procedure query8(IN idspecialty int,IN medicalFacility int)
BEGIN
SELECT DISTINCT staff.id, staff.FIO, appointment.id_patience
FROM appointment INNER JOIN office ON office.id = appointment.id_office 
                INNER JOIN department ON office.id_department = department.id 
                INNER JOIN housing ON housing.id = department.housing_id
                INNER JOIN medical_facility ON housing.medical_facility_id = medical_facility.id 
                INNER JOIN staff ON staff.id = appointment.id_doctor
                INNER JOIN staff_specialization ON staff.id = staff_specialization.id_staff
WHERE staff_specialization.id_specialty = idspecialty and medical_facility.id = medicalFacility AND medical_facility.medical_facility_type = "Поликлиника";
END;
$$
delimiter ;
/* 8 end */

/* 9 start */
delimiter $$
CREATE procedure query9(IN medicalFacility int)
BEGIN
Select COUNT(room.id), sum(room.number_of_beds)
FROM medicine.medical_facility INNER JOIN housing ON medical_facility.id = housing.medical_facility_id
								INNER JOIN department ON department.housing_id = housing.id
                                INNER JOIN room ON room.id_department = department.id
where medical_facility.id = medicalFacility;
END;
$$
delimiter ;

delimiter $$
CREATE procedure query9GroupByDepartment(IN medicalFacility int)
BEGIN
Select department.id, COUNT(room.id), sum(room.number_of_beds)
FROM medicine.medical_facility INNER JOIN housing ON medical_facility.id = housing.medical_facility_id
								INNER JOIN department ON department.housing_id = housing.id
                                INNER JOIN room ON room.id_department = department.id
where medical_facility.id = medicalFacility
group by department.id;
END;
$$
delimiter ;

delimiter $$
CREATE procedure query9GroupByDepartmentAndFree(IN medicalFacility int)
BEGIN
Select department.id, COUNT(room.id), sum(room.number_of_beds)
FROM medicine.medical_facility INNER JOIN housing ON medical_facility.id = housing.medical_facility_id
								INNER JOIN department ON department.housing_id = housing.id
                                INNER JOIN room ON room.id_department = department.id
where medical_facility.id = medicalFacility and getFreeBeds(room.id) = room.number_of_beds;
END;
$$
delimiter ;
/* 9 end */

/* 10 start */
delimiter $$
CREATE procedure query10(IN medicalFacility int)
BEGIN
SELECT COUNT(office.id)
FROM medical_facility INNER JOIN housing ON medical_facility.id = housing.medical_facility_id
                        INNER JOIN department ON department.housing_id = housing.id 
                        INNER JOIN office ON office.id_department = department.id 
WHERE medical_facility.id = medicalFacility;
END;
$$
delimiter ;

delimiter $$
CREATE procedure query10WithDate(IN firstDate Date, IN secondDate DATE)
BEGIN
SELECT appointment.id_office, COUNT(appointment.id_patience)
FROM appointment
WHERE appointment.to_ BETWEEN firstDate AND secondDate
GROUP BY appointment.id_office;
END;
$$
delimiter ;
/* 10 end*/

/* 11 start */
delimiter $$
CREATE procedure query11(IN doctor int, IN firstDate Date,IN secondDate Date)
BEGIN
SELECT tabl.id, tabl.fio, avg(tabl.coun)
FROM(
        SELECT appointment.id_doctor as id,staff.FIO as fio, count(id_patience) as coun
        FROM appointment INNER JOIN staff ON staff.id = appointment.id_doctor
        WHERE  appointment.id_doctor=doctor and (Select DISTINCT DATE(appointment.to_)
                                                        FROM appointment
                                                        WHERE appointment.to_ BEtWEEN firstDate and secondDate) BETWEEN DATE(appointment.since_) AND DATE(appointment.to_)
        GROUP BY appointment.id_doctor) as tabl
GROUP BY tabl.id;
END;
$$
delimiter ;

delimiter $$
CREATE procedure query11MedicalFacility(IN medicalFacility int, IN firstDate Date,IN secondDate Date)
BEGIN
SELECT tabl.id, tabl.fio, avg(tabl.coun)
FROM(
        SELECT appointment.id_doctor as id,staff.FIO as fio, count(id_patience) as coun
        FROM appointment INNER JOIN staff ON staff.id = appointment.id_doctor
                        INNER JOIN office ON office.id  = appointment.id_office
                        INNER JOIN department ON oficce.id_department = department.id 
                        INNER JOIN housing ON housing.id = department.housing_id
                        INNER JOIN medical_facility ON medical_facility.id = housing.medical_facility_id
        WHERE  medical_facility.id=medicalFacility and (Select DISTINCT DATE(appointment.to_)
                                                        FROM appointment
                                                        WHERE appointment.to_ BEtWEEN firstDate and secondDate) BETWEEN DATE(appointment.since_) AND DATE(appointment.to_)
        GROUP BY appointment.id_doctor) as tabl
GROUP BY tabl.id;
END;
$$
delimiter ;

delimiter $$
CREATE procedure query11Specialty(IN specialty int, IN firstDate Date,IN secondDate Date)
BEGIN
SELECT tabl.id, tabl.fio, avg(tabl.coun)
FROM(
        SELECT appointment.id_doctor as id,staff.FIO as fio, count(id_patience) as coun
        FROM appointment INNER JOIN staff ON staff.id = appointment.id_doctor
                        INNER JOIN staff_specialization ON staff.id = staff_specialization.id_staff
        WHERE  staff_specialization.id_specialty=specialty and (Select DISTINCT DATE(appointment.to_)
                                                        FROM appointment
                                                        WHERE appointment.to_ BEtWEEN firstDate and secondDate) BETWEEN DATE(appointment.since_) AND DATE(appointment.to_)
        GROUP BY appointment.id_doctor) as tabl
GROUP BY tabl.id;
END;
$$
delimiter ;
/* 11 end */


/* 12 start */
delimiter $$
CREATE procedure query12(IN doctor int)
BEGIN
SELECT id_doctor,staff.FIO, COUNT(id_patience)
FROM patiente_in_hospital INNER JOIN staff ON staff.id = patiente_in_hospital.id_doctor
WHERE patiente_in_hospital.id_doctor=doctor and NOW() BEtWEEN patiente_in_hospital.since_ AND patiente_in_hospital.to_ 
GROUP BY id_doctor;
END;
$$
delimiter ;

delimiter $$
CREATE procedure query12MedicalFacility(IN medicalFacility int)
BEGIN
SELECT id_doctor,staff.FIO, COUNT(id_patience)
FROM patiente_in_hospital INNER JOIN staff ON staff.id = patiente_in_hospital.id_doctor
WHERE patiente_in_hospital.id_medical_facility=medicalFacility and NOW() BEtWEEN patiente_in_hospital.since_ AND patiente_in_hospital.to_ 
GROUP BY id_doctor;
END;
$$
delimiter ;

delimiter $$
CREATE procedure query12Specialty(IN specialty int)
BEGIN
SELECT id_doctor,staff.FIO, COUNT(id_patience)
FROM patiente_in_hospital INNER JOIN staff ON staff.id = patiente_in_hospital.id_doctor
                            INNER JOIN staff_specialization ON staff.id = staff_specialization.id_staff
WHERE staff_specialization.id_specialty = specialty and NOW() BEtWEEN patiente_in_hospital.since_ AND patiente_in_hospital.to_ 
GROUP BY id_doctor;
END;
$$
delimiter ;
/*12 end */


/* 13 start */
delimiter $$
CREATE procedure query13Specialty(IN medicalFacility int,IN time1 int)
BEGIN
SELECT DISTINCT patience.id, patience.FIO
FROM operations INNER JOIN patience ON operations.id_patience = patience.id 
where operations.id_medical_facility=medicalFacility AND time1 between  operations.since_ AND operations.to_;
END;
$$
delimiter ;

delimiter $$
CREATE procedure query13Staff(IN staff int,IN time1 int)
BEGIN
SELECT DISTINCT patience.id, patience.FIO
FROM operations INNER JOIN patience ON operations.id_patience = patience.id 
where operations.id_staff=staff AND time1 between  operations.since_ AND operations.to_;
END;
$$
delimiter ;
/* 13 end */

/* 14 start */
delimiter $$
CREATE procedure query14(IN medicalFacility int,IN laboratory int,IN firstDate DATE, IN secondDate DATE)
BEGIN
SELECT tabl.id, tabl.lab_name, avg(tabl.coun)
FROM(
        SELECT laboratory.id as id,laboratory.name as lab_name, count(survey.id_analysis) as coun
        FROM survey INNER JOIN laboratory ON survey.id_laboratory = laboratory.id
        WHERE survey.id_medical_facility= medicalFacility and  survey.id_laboratory=laboratory and (Select DISTINCT DATE(survey.to_)
                                                        FROM survey
                                                        WHERE survey.to_ BEtWEEN firstDate and secondDate) BETWEEN DATE(survey.since_) AND DATE(survey.to_)
        ) as tabl
GROUP BY tabl.id;
END;
$$
delimiter ;

delimiter $$
CREATE procedure query14Laboratory(IN laboratory int,IN firstDate DATE, IN secondDate DATE)
BEGIN
SELECT tabl.id, tabl.lab_name, avg(tabl.coun)
FROM(
        SELECT laboratory.id as id,laboratory.name as lab_name, count(survey.id_analysis) as coun
        FROM survey INNER JOIN laboratory ON survey.id_laboratory = laboratory.id
        WHERE  survey.id_laboratory=laboratory and (Select DISTINCT DATE(survey.to_)
                                                        FROM survey
                                                        WHERE survey.to_ BEtWEEN firstDate and secondDate) BETWEEN DATE(survey.since_) AND DATE(survey.to_)
        ) as tabl
GROUP BY tabl.id;
END;
$$
delimiter ;
/* 14 end */
