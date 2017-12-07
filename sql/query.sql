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



