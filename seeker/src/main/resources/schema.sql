DROP TABLE IF EXISTS dummy_jobs;

DROP TABLE IF EXISTS companies;
DROP TABLE IF EXISTS company_jobs;
DROP TABLE IF EXISTS recruiters;

DROP TABLE IF EXISTS STUDENT_ATTRIBUTES;
DROP TABLE IF EXISTS STUDENT_LOGS;
DROP TABLE IF EXISTS STUDENT_RESUME;

CREATE SEQUENCE hibernate_sequence START WITH 100 INCREMENT BY 1;

/*CREATE TABLE dummy_jobs (
    jobID           INT AUTO_INCREMENT PRIMARY KEY,
    jobTitle        VARCHAR(50),
    jobInfo         VARCHAR(250),
    PRIMARY KEY(jobID)
);
*/

CREATE TABLE companies (
    companyID       NUMBER(8) AUTO_INCREMENT PRIMARY KEY,    
    companyName     VARCHAR(50),
    companyAddress  VARCHAR(250),
    companyInfo     VARCHAR(250),
    PRIMARY KEY(companyID)
);

CREATE TABLE company_jobs (
    id              NUMBER(8) AUTO_INCREMENT PRIMARY KEY,
    company_ID      NUMBER(8),
    job_ID          NUMBER(8),
    PRIMARY KEY(id)
);

/*CREATE TABLE STUDENT_LOGS (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    studentID INT,
    lastLoginDate DATE,
    lastApplicationDate DATE
);

CREATE TABLE STUDENT_RESUME (
    id INT AUTO_INCREMENT  PRIMARY KEY,
    studentID INT,
    isCurrentJob VARCHAR(1),
    startDate DATE,
    endDate DATE,
    jobTitle VARCHAR (50),
    companyName VARCHAR (50),
    jobCity	VARCHAR (50),
    jobState VARCHAR (50),
    jobCountry VARCHAR (50),
    jobDescription	 VARCHAR(999)

);
 */