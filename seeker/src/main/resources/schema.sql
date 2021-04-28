DROP TABLE IF EXISTS dummy_jobs;

DROP TABLE IF EXISTS companies;
DROP TABLE IF EXISTS company_jobs;
DROP TABLE IF EXISTS recruiters;

CREATE SEQUENCE hibernate_sequence START WITH 100 INCREMENT BY 1;

CREATE TABLE dummy_jobs (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    jobID           INT(8),
    jobTitle        VARCHAR(50),
    jobInfo         VARCHAR(250),
    PRIMARY KEY(id)
);

CREATE TABLE companies (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    companyID       INT(8),    
    companyName     VARCHAR(50),
    companyAddress  VARCHAR(250),
    companyInfo     VARCHAR(250),
    PRIMARY KEY(id)
);

CREATE TABLE company_jobs (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    company_ID      INT(8),
    job_ID          INT(8),
    PRIMARY KEY(id)
);

CREATE TABLE recruiters (
    id              INT AUTO_INCREMENT PRIMARY KEY,
    recruiterID     INT(8),    
    companyID       INT(8),
    fname           VARCHAR(50),
    lname           VARCHAR(50),
    email           VARCHAR(50),
    PRIMARY KEY(id)
);

