DROP TABLE IF EXISTS dummy_jobs;

CREATE SEQUENCE hibernate_sequence START WITH 100 INCREMENT BY 1;

CREATE TABLE dummy_jobs (
    jobID           INT AUTO_INCREMENT PRIMARY KEY,
    blah            VARCHAR(10)
);
