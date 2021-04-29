DROP TABLE IF EXISTS dummy_jobs;

DROP TABLE IF EXISTS companies;
DROP TABLE IF EXISTS company_jobs;
DROP TABLE IF EXISTS recruiters;

CREATE SEQUENCE hibernate_sequence START WITH 100 INCREMENT BY 1;

CREATE TABLE dummy_jobs (
    jobID           INT AUTO_INCREMENT PRIMARY KEY,
    jobTitle        VARCHAR(50),
    jobInfo         VARCHAR(250),
    PRIMARY KEY(jobID)
);

CREATE TABLE companies (
    companyID       INT AUTO_INCREMENT PRIMARY KEY,    
    companyName     VARCHAR(50),
    companyAddress  VARCHAR(250),
    companyInfo     VARCHAR(250),
    PRIMARY KEY(companyID)
);

CREATE TABLE company_jobs (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    company_ID      INT(8),
    job_ID          INT(8),
    PRIMARY KEY(id)
);

CREATE TABLE recruiters (
    recruiterID     INT AUTO_INCREMENT PRIMARY KEY,    
    companyID       INT(8),
    fname           VARCHAR(50),
    lname           VARCHAR(50),
    email           VARCHAR(50),
    PRIMARY KEY(recruiterID)
);

