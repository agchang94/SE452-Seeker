/*DROP TABLE IF EXISTS students;
DROP TABLE IF EXISTs student_academics;
DROP TABLE IF EXISTS student_certs;

CREATE TABLE students (
    id INT (8), firstName varchar (50),
    lastName varchar (50), email varchar (50),  state varchar (15), city varchar (50),
    street_address varchar (50), zip int (5), phone varchar (500),
    schoolID int (8), dob DATE,
    primary key (id)
);

CREATE TABLE students_academics (
    id INT (8), accomplishments varchar (50),
    major varchar (50), gpa DECIMAL (20,2),  universityName varchar (50), 
    foreign key (id) references students(id) 
    
);

create table student_certs(
    id INT (8), certificates varchar (500)
); 
*/

/* insert into ST (ST_ID, ST_fname, ST_lname, ST_email, ST_state, ST_city, ST_address, ST_zip, ST_phone, ST_sid, ST_dob) 
values (123, 'Mike', 'Adams', 'madams@gmail.com', 'NY', 'Bronx', '145 Flavor Street', 60659, 5555555555, 155, '1970-05-10');

insert into ST (ST_ID, ST_fname, ST_lname, ST_email, ST_state, ST_city, ST_address, ST_zip, ST_phone, ST_sid, ST_dob) 
values (124, 'Kyrie', 'Irving', 'irving@gmail.com', 'NY', 'Harlem', '364 Florence Blvd.', 60659, 7734653822, 156, '1971-06-16');

insert into ST (ST_ID, ST_fname, ST_lname, ST_email, ST_state, ST_city, ST_address, ST_zip, ST_phone, ST_sid, ST_dob) 
values (125, 'Victor', 'Lopez', 'vlopez@gmail.com', 'IL', 'Chicago', '367 Fairfield Street', 60645 , 6305555555 , 156, '1977-06-08');

insert into ST (ST_ID, ST_fname, ST_lname, ST_email, ST_state, ST_city, ST_address, ST_zip, ST_phone, ST_sid, ST_dob) 
values (126, 'Winnie', 'Yang', 'yang@gmail.com', 'CA', 'Long Beach', '745 Beachside Road', 60607 , 3125555555 , 157, '1971-02-03');

insert into ST (ST_ID, ST_fname, ST_lname, ST_email, ST_state, ST_city, ST_address, ST_zip, ST_phone, ST_sid, ST_dob)
values (127, 'Henry', 'Wilson', 'hwilson@gmail.com', 'ON', 'Toronto', '224 Seeley Parkway', 60607 , 7735678901 , 156, '1986-10-24');

insert into SA (STUDENT_ID, SA_ID, SA_ACC, SA_MAJOR, SA_GPA, SA_UNI) values (123, 1000, 'none', 'Computer Science', 1.85, 'DePaul University');

insert into SA (SA_ID, SA_ACC, SA_MAJOR, SA_GPA, SA_UNI, student_id) values (2, 'valedictorian, rhodes scholar, student of the month', 'Business', 3.95, 'Harvard University', 124);

insert into SA (SA_ID, SA_ACC, SA_MAJOR, SA_GPA, SA_UNI, student_id) values (3, 'none', 'Computer Science', 2.84, 'Truman College', 125);

insert into SA (SA_ID, SA_ACC, SA_MAJOR, SA_GPA, SA_UNI, student_id) values (4, 'Student Leader and Captain of Basketball Team', 'History', 3.45, 'Yale University', 126);

insert into SA (SA_ID, SA_ACC, SA_MAJOR, SA_GPA, SA_UNI, student_id) values (5, 'Graduated with Distinction', 'Software Engineering', 3.85, 'Loyola University', 127); 

insert into SC (SC_ID, SC_CT) values (123, 'Microsoft Technolgy Associate'); 

insert into SC (SC_ID, SC_CT) values (124, 'Google Developer'); 

insert into SC (SC_ID, SC_CT) values (125, 'Oracle Certified Professions'); 

insert into SC (SC_ID, SC_CT) values (126, 'None'); 

insert into SC (SC_ID, SC_CT) values (127, 'Cisco Certified Technician');  */ 