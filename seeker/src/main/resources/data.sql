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

DROP TABLE IF EXISTS schools;
DROP TABLE IF EXISTS locations;
DROP TABLE IF EXISTS applications;


CREATE TABLE schools (
    schoolID NUMBER(8) PRIMARY KEY,
    schoolName VARCHAR(50) NOT NULL

);

CREATE TABLE locations (
    cityID NUMBER(8) PRIMARY KEY,
    cityName VARCHAR(50),
    stateName VARCHAR(50),
    companyName VARCHAR(50),
    companyID NUMBER(8)

);

CREATE TABLE applications (
    applicationID NUMBER(8) PRIMARY KEY,
    studentID NUMBER(8),
    companyID NUMBER(8),
    applicationDate DATE,
    applicationStatus VARCHAR(10)

);

INSERT INTO schools (schoolID, schoolName)VALUES
(1, 'Illinois'),
(2, 'Michigan'),
(3, 'Indiana');

INSERT INTO locations (cityID, cityName, stateName, companyName, companyID)VALUES
(1, 'Chicago', 'Illinois', 'CME Group', 111),
(2, 'Detroit', 'Michigan', 'Ford Motor Company', 222),
(3, 'Cupertino', 'California', 'Apple', 333);

INSERT INTO Applications (applicationID, studentID, companyID, applicationDate, applicationStatus)VALUES
(123, 111, 111, '2021-04-28', 'active'),
(456, 111, 333, '2021-04-28', 'inactive'),
(789, 111, 222, '2021-04-28', 'active');
INSERT INTO dummy_jobs (jobID,jobTitle,jobInfo) VALUES 
    (600,'Junior Software Developer','Skills with relational databases and moderately advanced SQL is a must'),
    (601,'Python Software Engineer','Work on backend applications to support new features and capabilities'),
    (602,'Front End Developer','Build front-end architectures that integrate easily with other systems and technologies'),
    (603,'Software Developer','Develops and codes software programs, algorithms and automated processes');

INSERT INTO companies (companyID,companyName,companyAddress,companyInfo) VALUES
    (500,'Capitol One','Chicago, IL 60695', 'A bank holding company'),
    (501,'AllState','2775 Sanders Rd, NORTHBROOK, IL 60062-6110', 'An insurance company'),
    (502,'Motorola Solutions','500 W. Monroe St, Chicago, IL', 'data communications and telecommunications equipment provider');

INSERT INTO company_jobs (company_ID, job_ID) VALUES
    (500,600),
    (500,601),
    (501,602),
    (502,603);

INSERT INTO recruiters (recruiterID,companyID, fname, lname, email) VALUES
    (400,500,'John','Smith','jSmith1@depaul.edu'),
    (401,501,'David','Lee','dLee2@depaul.edu'),
    (402,502,'Jane','Doe','jDoe3@depaul.edu');


INSERT INTO STUDENT_ATTRIBUTES (studentID, skills, languages) VALUES (9999, 'TEST SKILLS 9', 'TEST LANGUAGES 9');
INSERT INTO STUDENT_ATTRIBUTES (studentID, skills, languages) VALUES (0000, 'TEST SKILLS 0', 'TEST LANGUAGES 0');
INSERT INTO STUDENT_ATTRIBUTES (studentID, skills, languages) VALUES (1111, 'TEST SKILLS 1', 'TEST LANGUAGES 1');

INSERT INTO STUDENT_LOGS (studentID, lastLoginDate, lastApplicationDate) VALUES (9999, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO STUDENT_LOGS (studentID, lastLoginDate, lastApplicationDate) VALUES (1111, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO STUDENT_LOGS (studentID, lastLoginDate, lastApplicationDate) VALUES (0000, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);



INSERT INTO STUDENT_RESUME(studentID, isCurrentJob, startDate, endDate, jobTitle, companyName, 
                            jobCity, jobState, jobCountry, jobDescription) 
                    VALUES (9999, 'N', '2014-12-03', '2019-04-19', 'Test Job Title', 'Test Company', 
                            'Test Job City', 'Test Job State', 'Test Job Country', 'Test Job Description');

