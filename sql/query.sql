/* 1 start */
SELECT DISTINCT staff.id, staff.FIO
FROM place_of_work INNER JOIN staff ON place_of_work.id_staff = staff.id 
                        INNER JOIN staff_specialization ON staff_specialization.id_staff = staff.id 
                        INNER JOIN specialty ON specialty.id= staff_specialization.id_specialty
WHERE staff_specialization.id_specialty = [id_specialty] and place_of_work.id_medical_facility = [medical_facility_id] and specialty.is_Doctor = true

SELECT DISTINCT staff.id, staff.FIO
FROM place_of_work INNER JOIN staff ON place_of_work.id_staff = staff.id 
                        INNER JOIN staff_specialization ON staff_specialization.id_staff = staff.id 
                        INNER JOIN specialty ON specialty.id= staff_specialization.id_specialty
WHERE staff_specialization.id_specialty = [id_specialty] and specialty.is_Doctor = true
/* 1 end */

/* 2 start */
SELECT DISTINCT staff.id, staff.FIO
FROM place_of_work INNER JOIN staff ON place_of_work.id_staff = staff.id 
                        INNER JOIN staff_specialization ON staff_specialization.id_staff = staff.id 
                        INNER JOIN specialty ON specialty.id= staff_specialization.id_specialty
WHERE staff_specialization.id_specialty = [id_specialty] and place_of_work.id_medical_facility = [medical_facility_id] and specialty.is_Doctor = false

SELECT DISTINCT staff.id, staff.FIO
FROM place_of_work INNER JOIN staff ON place_of_work.id_staff = staff.id 
                        INNER JOIN staff_specialization ON staff_specialization.id_staff = staff.id 
                        INNER JOIN specialty ON specialty.id= staff_specialization.id_specialty
WHERE staff_specialization.id_specialty = [id_specialty] and specialty.is_Doctor = false
/* 2 end */

/* 3 start */
SELECT staff.id, staff.FIO, COUNT(operations.since_)
FROM operations INNER JOIN staff ON staff.id =  operations.id_staff
                INNER JOIN staff_specialization ON staff.id = staff_specialization.id_staff
where operations.id_medical_facility = [medical_facility_id] and staff_specialization.id_specialty = [id_specialty]
GROUP BY operations.id_staff
HAVING COUNT(operations.since_)>= [count of operations]

SELECT staff.id, staff.FIO, COUNT(operations.since_)
FROM operations INNER JOIN staff ON staff.id =  operations.id_staff
                INNER JOIN staff_specialization ON staff.id = staff_specialization.id_staff
where staff_specialization.id_specialty = [id_specialty]
GROUP BY operations.id_staff
HAVING COUNT(operations.since_)>= [count of operations]
/* 3 end */

/* 4 start */
SELECT place_of_work.id_staff, SUM(YEAR(place_of_work.to_)) -SUM(YEAR(place_of_work.since_)) as experience
FROM place_of_work INNER JOIN staff ON staff.id =place_of_work.id_staff
                INNER JOIN staff_specialization on staff.id = staff_specialization.id_staff
WHERE place_of_work.id_medical_facility = [medical_facility_id] and staff_specialization.id_specialty = [id_specialty]
GROUP BY place_of_work.id_staff

SELECT place_of_work.id_staff, SUM(YEAR(place_of_work.to_)) -SUM(YEAR(place_of_work.since_)) as experience
FROM place_of_work INNER JOIN staff ON staff.id =place_of_work.id_staff
                INNER JOIN staff_specialization on staff.id = staff_specialization.id_staff
WHERE staff_specialization.id_specialty = [id_specialty]
GROUP BY place_of_work.id_staff
/* 4 end */

/* 5 start */
SELECT staff.id, staff.FIO, specialty.degree,specialty.grade
FROM staff INNER JOIN staff_specialization ON staff.id = staff_specialization.id_staff
        INNER JOIN specialty ON specialty.id  = staff_specialization.id_specialty
        INNER JOIN place_of_work ON place_of_work.id_staff = staff.id
WHERE place_of_work.id_medical_facility = [medical_facility_id] and  specialty.id = [id_specialty] 

SELECT staff.id, staff.FIO, specialty.degree,specialty.grade
FROM staff INNER JOIN staff_specialization ON staff.id = staff_specialization.id_staff
        INNER JOIN specialty ON specialty.id  = staff_specialization.id_specialty
        INNER JOIN place_of_work ON place_of_work.id_staff = staff.id
WHERE  specialty.id = [id_specialty] 
/* 5 end */

/* 6 start */
SELECT DISTINCT patiente_in_hospital.id_patience, patience.FIO, patiente_in_hospital.since_,patiente_in_hospital.id_doctor,patiente_in_hospital.complaints_at_admission
FROM patiente_in_hospital INNER JOIN patience ON patience.id = patiente_in_hospital.id_patience

SELECT DISTINCT patiente_in_hospital.id_patience, patience.FIO, patiente_in_hospital.since_,patiente_in_hospital.id_doctor,patiente_in_hospital.complaints_at_admission
FROM patiente_in_hospital INNER JOIN patience ON patience.id = patiente_in_hospital.id_patience
WHERE patiente_in_hospital.id_medical_facility = [medical_facility_id]

SELECT DISTINCT patiente_in_hospital.id_patience, patience.FIO, patiente_in_hospital.since_,patiente_in_hospital.id_doctor,patiente_in_hospital.complaints_at_admission
FROM patiente_in_hospital INNER JOIN patience ON patience.id = patiente_in_hospital.id_patience
                        INNER JOIN medical_facility ON medical_facility.id = patiente_in_hospital.id_medical_facility
                        INNER JOIN housing ON housing.medical_facility_id = medical_facility.id 
                        INNER JOIN department ON department.housing_id = housing.id 
WHERE department.id = [id_department]

SELECT DISTINCT patiente_in_hospital.id_patience, patience.FIO, patiente_in_hospital.since_,patiente_in_hospital.id_doctor,patiente_in_hospital.complaints_at_admission
FROM patiente_in_hospital INNER JOIN patience ON patience.id = patiente_in_hospital.id_patience
                        INNER JOIN medical_facility ON medical_facility.id = patiente_in_hospital.id_medical_facility
                        INNER JOIN housing ON housing.medical_facility_id = medical_facility.id 
                        INNER JOIN department ON department.housing_id = housing.id 
                        INNER JOIN room ON room.id_department = department.id 
WHERE room.id = [room_id]
/* 6 end */

/* 7 start */
SELECT patiente_in_hospital.id_patience, patience.FIO 
FROM patiente_in_hospital INNER JOIN patience ON patience.id = patiente_in_hospital.id_patience
WHERE patiente_in_hospital.id_medical_facility = [medical_facility_id] and patiente_in_hospital.type ="Cтационарное лечение" and patiente_in_hospital.to_ BETWEEN [first time] and [second time]

SELECT patiente_in_hospital.id_patience, patience.FIO 
FROM patiente_in_hospital INNER JOIN patience ON patience.id = patiente_in_hospital.id_patience
WHERE patiente_in_hospital.id_doctor = [doctor_id] and patiente_in_hospital.type ="Cтационарное лечение" and patiente_in_hospital.to_ BETWEEN [first time] and [second time]
/* 7 end */

/* 8 start */
SELECT DISTINCT staff.id, staff.FIO, appointment.id_patience
FROM appointment INNER JOIN office ON office.id = appointment.id_office 
                INNER JOIN department ON office.id_department = department.id 
                INNER JOIN housing ON housing.id = department.housing_id
                INNER JOIN medical_facility ON housing.medical_facility_id = medical_facility.id 
                INNER JOIN staff ON staff.id = appointment.id_doctor
                INNER JOIN staff_specialization ON staff.id = staff_specialization.id_staff
WHERE staff_specialization.id_specialty = [id_specialty] and medical_facility.id = [id_medical_facility] AND medical_facility.medical_facility_type = "Поликлиника"
/* 8 end */

/* 9 start */
use medicine;
Select COUNT(room.id), sum(room.number_of_beds)
FROM medicine.medical_facility INNER JOIN housing ON medical_facility.id = housing.medical_facility_id
								INNER JOIN department ON department.housing_id = housing.id
                                INNER JOIN room ON room.id_department = department.id
where medical_facility.id = 1

use medicine;
Select department.id, COUNT(room.id), sum(room.number_of_beds)
FROM medicine.medical_facility INNER JOIN housing ON medical_facility.id = housing.medical_facility_id
								INNER JOIN department ON department.housing_id = housing.id
                                INNER JOIN room ON room.id_department = department.id
where medical_facility.id = 1
group by department.id 


Select department.id, COUNT(room.id), sum(room.number_of_beds)
FROM medicine.medical_facility INNER JOIN housing ON medical_facility.id = housing.medical_facility_id
								INNER JOIN department ON department.housing_id = housing.id
                                INNER JOIN room ON room.id_department = department.id
where medical_facility.id = 1 and getFreeBeds(room.id) = room.number_of_beds
/* 9 end */
/* 10 start */
SELECT COUNT(office.id)
FROM medical_facility INNER JOIN housing ON medical_facility.id = housing.medical_facility_id
                        INNER JOIN department ON department.housing_id = housing.id 
                        INNER JOIN office ON office.id_department = department.id 
WHERE medical_facility.id = [id_medical_facility]

SELECT appointment.id_office, COUNT(appointment.id_patience)
FROM appointment
WHERE appointment.to_ BETWEEN [first data] AND [second data]
GROUP BY appointment.id_office
/* 10 end*/

/* 11 start */
SELECT tabl.id, tabl.fio, avg(tabl.coun)
FROM(
        SELECT appointment.id_doctor as id,staff.FIO as fio, count(id_patience) as coun
        FROM appointment INNER JOIN staff ON staff.id = appointment.id_doctor
        WHERE  appointment.id_doctor=[id_doctor] and (Select DISTINCT DATE(appointment.to_)
                                                        FROM appointment
                                                        WHERE appointment.to_ BEtWEEN [first data] and [second date]) BETWEEN DATE(appointment.since_) AND DATE(appointment.to_)
        GROUP BY appointment.id_doctor) as tabl
GROUP BY tabl.id

SELECT tabl.id, tabl.fio, avg(tabl.coun)
FROM(
        SELECT appointment.id_doctor as id,staff.FIO as fio, count(id_patience) as coun
        FROM appointment INNER JOIN staff ON staff.id = appointment.id_doctor
                        INNER JOIN office ON office.id  = appointment.id_office
                        INNER JOIN department ON oficce.id_department = department.id 
                        INNER JOIN housing ON housing.id = department.housing_id
                        INNER JOIN medical_facility ON medical_facility.id = housing.medical_facility_id
        WHERE  medical_facility.id=[id_medical_facility] and (Select DISTINCT DATE(appointment.to_)
                                                        FROM appointment
                                                        WHERE appointment.to_ BEtWEEN [first data] and [second date]) BETWEEN DATE(appointment.since_) AND DATE(appointment.to_)
        GROUP BY appointment.id_doctor) as tabl
GROUP BY tabl.id

SELECT tabl.id, tabl.fio, avg(tabl.coun)
FROM(
        SELECT appointment.id_doctor as id,staff.FIO as fio, count(id_patience) as coun
        FROM appointment INNER JOIN staff ON staff.id = appointment.id_doctor
                        INNER JOIN staff_specialization ON staff.id = staff_specialization.id_staff
        WHERE  staff_specialization.id_specialty=[id_specialty] and (Select DISTINCT DATE(appointment.to_)
                                                        FROM appointment
                                                        WHERE appointment.to_ BEtWEEN [first data] and [second date]) BETWEEN DATE(appointment.since_) AND DATE(appointment.to_)
        GROUP BY appointment.id_doctor) as tabl
GROUP BY tabl.id
/* 11 end */


/* 12 start */
SELECT id_doctor,staff.FIO, COUNT(id_patience)
FROM patiente_in_hospital INNER JOIN staff ON staff.id = patiente_in_hospital.id_doctor
WHERE patiente_in_hospital.id_doctor=[id_doctor] and NOW() BEtWEEN patiente_in_hospital.since_ AND patiente_in_hospital.to_ 
GROUP BY id_doctor

SELECT id_doctor,staff.FIO, COUNT(id_patience)
FROM patiente_in_hospital INNER JOIN staff ON staff.id = patiente_in_hospital.id_doctor
WHERE patiente_in_hospital.id_medical_facility=[id_medical_facility] and NOW() BEtWEEN patiente_in_hospital.since_ AND patiente_in_hospital.to_ 
GROUP BY id_doctor

SELECT id_doctor,staff.FIO, COUNT(id_patience)
FROM patiente_in_hospital INNER JOIN staff ON staff.id = patiente_in_hospital.id_doctor
                            INNER JOIN staff_specialization ON staff.id = staff_specialization.id_staff
WHERE staff_specialization.id_specialty = [id_specialty] and NOW() BEtWEEN patiente_in_hospital.since_ AND patiente_in_hospital.to_ 
GROUP BY id_doctor
/*12 end */


/* 13 start */
SELECT DISTINCT patience.id, patience.FIO
FROM operations INNER JOIN patience ON operations.id_patience = patience.id 
where operations.id_medical_facility=[medical_facility_id] AND [time] between  operations.since_ AND operations.to_

SELECT DISTINCT patience.id, patience.FIO
FROM operations INNER JOIN patience ON operations.id_patience = patience.id 
where operations.id_staff=[staff_id] AND [time] between  operations.since_ AND operations.to_

/* 13 end */

/* 14 start */
SELECT tabl.id, tabl.lab_name, avg(tabl.coun)
FROM(
        SELECT laboratory.id as id,laboratory.name as lab_name, count(survey.id_analysis) as coun
        FROM survey INNER JOIN laboratory ON survey.id_laboratory = laboratory.id
        WHERE survey.id_medical_facility= [medical_facility_id] and  survey.id_laboratory=[id_laboratory] and (Select DISTINCT DATE(survey.to_)
                                                        FROM survey
                                                        WHERE survey.to_ BEtWEEN [first data] and [second date]) BETWEEN DATE(survey.since_) AND DATE(survey.to_)
        ) as tabl
GROUP BY tabl.id

SELECT tabl.id, tabl.lab_name, avg(tabl.coun)
FROM(
        SELECT laboratory.id as id,laboratory.name as lab_name, count(survey.id_analysis) as coun
        FROM survey INNER JOIN laboratory ON survey.id_laboratory = laboratory.id
        WHERE  survey.id_laboratory=[id_laboratory] and (Select DISTINCT DATE(survey.to_)
                                                        FROM survey
                                                        WHERE survey.to_ BEtWEEN [first data] and [second date]) BETWEEN DATE(survey.since_) AND DATE(survey.to_)
        ) as tabl
GROUP BY tabl.id
/* 14 end */