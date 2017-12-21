use medicine;
/* Кабинеты в поликлинике
добавить пациентов  в кровати */
INSERT INTO staff(FIO)
                        VALUES("Титова Галина Вячеславовна"),
                            ("Ефимов Дмитрий Демьянович"),
                            ("Боброва Тамара Глебовна"),
                            ("Некрасов Александр Мэлсович"),
                            ("Евдокимова Регина Кондратовна"),
                            ("Анисимов Иван Вадимович"),
                            ("Матвиенко Лариса Вадимовна");
INSERT INTO medical_facility(name,address,superior_medical_facility,id_order_doctor,medical_facility_type)
                        VALUES("Hospital 1","Kolasa st. 89 ",null,1,2 ),
                            ("polyclinic 1","Molodegnaya st. 12", 1,1,1),
                            ("hospital 2","Kostromskaya Ul., bld. 88, appt. 65",null,1,2),
                            ("hospital 3","YAgodnyy Per., bld. 2, appt. 48",null,1,2),
                            ("policlinic 2","Pr Leningradskiy, bld. 43, appt. 160",3,1,1);

INSERT INTO housing(name, address,medical_facility_id)
                        VALUES("housing 1","Kolasa st. 89, housing 1", 1),
                            ("housing 2","Kolasa st. 89, housing 2", 1),
                            ("housing 1","Molodegnaya st. 12, housing 1",2),
                            ("housing 1","SHevchenko Ul., bld. 86, appt. 45",3),
                            ("housing 1","D. Davydovo 2-Y Mikrorayon, bld. 28, appt. 19",4);

INSERT INTO department(name, housing_id) 
                        VALUES("infectious disease department",1),
                            ("cardiology department",2),
                            ("pulmonology department",2),
                            ("surgery",3),
                            ("neurology",1);

INSERT INTO room(room_number,number_of_beds,id_department,id_responsible_doctor) 
                        VALUES(100,5,1,1),
                            (101,6,1,1),
                            (102,7,1,1),
                            (100,6,2,1),
                            (101,7,2,1),
                            (102,7,2,1),
                            (201,6,3,1),
                            (202,4,3,1),
                            (203,5,3,2);
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

INSERT INTO specialty(name,is_Doctor,salary,degree,grade)
                        VALUES("the pulmonologist",true,99.99,'Кандидат медицинских наук','Доцент'),
                            ("the cardiologist",true,99.99,'Доктор медицинских наук','Профессор'),
                            ("the endocrinologist",true,99.99,null,null),
                            ("the pediatrician",true,99.99,null,null),
                            ("the allergist",true,99.99,null,null),
                            ("the nurse",false,99.99,null,null);
INSERT INTO staff_specialization(id_staff,id_specialty) 
                        VALUES(1,1),
                            (1,2),
                            (2,1),
                            (3,3),
                            (4,2),
                            (5,2),
                            (1,3),
                            (1,4),
                            (1,5),
                            (7,6);
/*ВРАЧ ДЕЖУРИТ ПО ВСЕЙ БОЛЬНИЦЕ*/
INSERT INTO orderly_for_hospital_doctor(id_doctor,since_,to_)
                        VALUES(1,"2017-11-03 08:00:00","2017-11-03 16:00:00"),
                            (4,"2017-11-03 16:00:00","2017-11-04 00:00:00"),
                            (5,"2017-11-04 00:00:00","2017-11-04 08:00:00"),
                            (1,"2017-11-04 08:00:00","2017-11-04 16:00:00"),
                            (2,"2017-11-04 16:00:00","2017-11-05 00:00:00");
INSERT INTO position_(name)
                        VALUES("глав. врач"),
                            ("врач-ординатор"),
                            ("зав. отделением");
INSERT INTO place_of_work(id_staff,id_medical_facility,id_position,id_department,rate,type_of_work,since_)
                        VALUES(1, 1, 1, 2,1,'Работа',"2017-11-03 08:00:00"),
                            (2,1,2,3,1,'Работа',"2017-11-03 08:00:00"),
                            (4,1,2,2,1,'Работа',"2017-11-03 08:00:00"),
                            (5,1,2,2,1,'Работа',"2017-11-03 08:00:00"),
                            (7,1,1,1,1,'Работа',"2011-12-30 12:00:00");
INSERT INTO staff_shedule(staff_id, since_,to_) 
                        VALUES (1,"2017-01-01 12:00:00","2018-01-01 12:00:00"),
                                (2,"2017-01-01 12:00:00","2018-01-01 12:00:00");
INSERT INTO type_of_analysis(name)
                        VALUES ("biochemical analysis of blood"),
                                ("Clinical analysis of blood"),
                                ("blood test for thyroid hormones"),
                                ("blood coagulation analysis"),
                                ("General blood analysis");
INSERT INTO patience(FIO) values("Петухов Иван Агафонович"),
								("Михеева Екатерина Игнатьева"),
                                ("Молчанов Лукий Лукьянович"),
                                ("Носов Илья Денисович"),
                                ("Панфилов Лаврентий  Иринеевич");  
INSERT INTO analysis(id_type_of_analysis,id_patience)
                        VALUES (1,1),
                                (2,1),
                                (2,1);
INSERT INTO disease(name) VALUES("disease1"),
								("disease2"),
								("disease3"),
                                ("disease4"),
                                ("disease5");
INSERT INTO department_specialization(id_department,id_disease) VALUES(1,1),
																		(1,2),
                                                                        (2,1),
                                                                        (3,1),
                                                                        (4,1);
INSERT INTO laboratory(name,address) VALUES("laboratory1","Lenina Ul., bld. 287/3, appt. 28"),
							            ("laboratory2","Druzhby Ul., bld. 73, appt. 27"),
                                        ("laboratory3","YAshina Ul., bld. 33, appt. 59"),
                                        ("laboratory4","Mira Ul., bld. 59, appt. 13"),
                                        ("laboratory5","Gastello Ul., bld. 11, appt. 1");
INSERT INTO hospital_laboratory VALUES(1,1,1,"2016-11-03","2018-11-03"),
										(2,1,2,"2015-11-03","2019-11-03"),
                                        (2,4,3,"2015-11-03","2019-11-03"),
                                        (3,5,4,"2015-11-03","2020-11-03"),
                                        (4,2,5,"2015-11-03","2020-11-03");
INSERT INTO laboratory_analysis_type VALUES(1,1),
											(1,2),
                                            (2,1),
                                            (3,2),
                                            (4,1),
                                            (5,4);
INSERT INTO laboratory_spec(name) VALUES("Parasitological"),
									("Cytological"),
									("Forensic"),
                                    ("Pathoanatomical"),
                                    ("Sanitary"),
                                    ("Radioisotope");
INSERT INTO laboratory_laboratory_spec VALUES(1,1),
											(1,2),
                                            (1,3),
                                            (2,1);
INSERT INTO office(id_department,room_number,id_responsible_doctor) VALUES(1,101,1),
															(2,102,2),
                                                            (3,103,3),
                                                            (3,104,4),
                                                            (4,105,5),
                                                            (4,106,6);
/* CHECK room in the medical facility, staff in ,medical facility */
INSERT INTO patiente_in_hospital(id_patience,id_medical_facility,id_room,id_doctor,id_disease,type,complaints_at_admission,since_,to_) VALUES
                                        (1,1,1,1,1,'Амбулаторное лечение',"complaints 1","2016-11-03 12:00:00","2018-11-03 12:00:00"),
										(1,1,1,1,1,'Амбулаторное лечение',"complaints 2","2019-11-03 12:00:00","2020-11-03 12:00:00"),
                                        (2,2,null,2,2,'Амбулаторное лечение',"complaints 3","2016-11-03 12:00:00","2018-11-03 12:00:00"),
                                        (3,2,null,3,3,'Cтационарное лечение',"complaints 4","2016-11-03 12:00:00","2018-11-03 12:00:00"),
                                        (4,3,null,3,4,'Cтационарное лечение',"complaints 5","2016-11-03 12:00:00","2018-11-03 12:00:00");
INSERT INTO appointment(id_doctor,id_patience,complaints,id_disease,id_office,since_,to_) VALUES(1,1,"complaints 1",1,1,"2016-11-03 12:00:00","2018-11-03 12:00:00"),
                                                                                    (1,2,"complaints 2",1,1,"2016-11-03 12:00:00","2018-11-03 12:00:00"),
                                                                                    (2,3,"complaints 3",2,2,"2016-11-03 12:00:00","2018-11-03 12:00:00"),
                                                                                    (3,4,"complaints 4",3,2,"2016-11-03 12:00:00","2018-11-03 12:00:00"),
                                                                                    (4,5,"complaints 5",4,3,"2016-11-03 12:00:00","2018-11-03 12:00:00");
/* CHECK PERMISSION */
INSERT INTO operations(id_staff,id_patience,id_disease,id_medical_facility,since_,to_) VALUES(1,1,1,1,"2016-11-03 12:00:00","2016-11-03 19:00:00"),
                                                                        (1,2,1,1,"2016-12-03 12:00:00","2016-12-03 14:00:00"),
                                                                        (2,3,4,2,"2017-12-04 12:00:00","2017-12-04 12:30:00"),
                                                                        (3,4,5,2,"2017-12-05 11:00:00","2017-12-05 12:00:00"),
                                                                        (4,3,5,1,"2017-12-07 11:00:00","2017-12-07 11:20:00"),
                                                                        (1,1,3,1,"2017-01-01 20:00:00","2018-12-30 23:00:00");
INSERT INTO survey(id_laboratory,id_medical_facility,id_analysis,since_,to_) VALUES (1,1,1,"2017-12-08 12:00:00","2017-12-08 13:00:00"),
                                                                        (1,2,2,"2017-12-08 12:00:00","2017-12-08 13:00:00"),
                                                                        (1,2,3,"2017-12-08 12:00:00","2017-12-08 13:00:00");
                                    