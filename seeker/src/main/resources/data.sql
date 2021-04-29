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