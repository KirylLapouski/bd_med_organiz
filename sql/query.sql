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
having COUNT(room.id) and sum(room.number_of_beds);

Select department.id, COUNT(room.id), sum(room.number_of_beds)
FROM medicine.medical_facility INNER JOIN housing ON medical_facility.id = housing.medical_facility_id
								INNER JOIN department ON department.housing_id = housing.id
                                INNER JOIN room ON room.id_department = department.id
where medical_facility.id = 1 and getFreeBeds(room.id) = room.number_of_beds
/* 9 end */


/* 11 start */
SELECT tabl.id, tabl.fio, avg(tabl.coun)
FROM(
        SELECT appointment.id_doctor as id,staff.FIO as fio, count(id_patience) as coun
        FROM appointment INNER JOIN staff ON staff.id = appointment.id_doctor
        WHERE  appointment.id_doctor=[id_doctor] and (Select DISTINCT DATE(appointment.to_)
                                                        FROM appointment
                                                        WHERE appointment.to_ BEtWEEN [first data] and [second date]) BETWEEN appointment.since_ AND appointment.to_
        GROUP BY appointment.id_doctor) as tabl
GROUP BY tabl.id

SELECT tabl.id, tabl.fio, avg(tabl.coun)
FROM(
        SELECT appointment.id_doctor as id,staff.FIO as fio, count(id_patience) as coun
        FROM appointment INNER JOIN staff ON staff.id = appointment.id_doctor
        WHERE  appointment.id_medical_facility=[id_medical_facility] and (Select DISTINCT DATE(appointment.to_)
                                                        FROM appointment
                                                        WHERE appointment.to_ BEtWEEN [first data] and [second date]) BETWEEN appointment.since_ AND appointment.to_
        GROUP BY appointment.id_doctor) as tabl
GROUP BY tabl.id

SELECT tabl.id, tabl.fio, avg(tabl.coun)
FROM(
        SELECT appointment.id_doctor as id,staff.FIO as fio, count(id_patience) as coun
        FROM appointment INNER JOIN staff ON staff.id = appointment.id_doctor
                        INNER JOIN staff_specialization ON staff.id = staff_specialization.id_staff
        WHERE  staff_specialization.id_specialty=[id_specialty] and (Select DISTINCT DATE(appointment.to_)
                                                        FROM appointment
                                                        WHERE appointment.to_ BEtWEEN [first data] and [second date]) BETWEEN appointment.since_ AND appointment.to_
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
