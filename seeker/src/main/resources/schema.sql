DROP TABLE IF EXISTS dummyJobs;

DROP TABLE IF EXISTS company;
DROP TABLE IF EXISTS companyJobs;
DROP TABLE IF EXISTS recruiter;


CREATE SEQUENCE hibernate_sequence START WITH 100 INCREMENT BY 1;


CREATE TABLE dummyJobs (
    jobID           INT AUTO_INCREMENT PRIMARY KEY,
    blah            VARCHAR(10)
);

CREATE TABLE company (
    companyID       INT(8) AUTO_INCREMENT  PRIMARY KEY,    
    companyName     VARCHAR(50),
    companyAddress  VARCHAR(250),
    companyInfo     VARCHAR(250) 
);

CREATE TABLE companyJobs (
    companyID       VARCHAR(50),
    jobID           INT(8),
    PRIMARY KEY     (companyID, jobID),
    FOREIGN KEY     (companyID) REFERENCES company(companyID),
    FOREIGN KEY     (jobID)     REFERENCES DummyJobs(jobID)
);

CREATE TABLE recruiter (
    recruiterID     INT(8) AUTO_INCREMENT  PRIMARY KEY,    
    companyID       INT(8),
    fname           VARCHAR(50),
    lname           VARCHAR(50),
    email           VARCHAR(50),
    FOREIGN KEY     (companyID) REFERENCES company(companyID)
);

