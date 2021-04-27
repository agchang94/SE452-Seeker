INSERT INTO dummyJobs (jobID, blah) VALUES 
    (100, 'test0'),
    (101, 'test1'),
    (102, 'test2'),
    (12345678, 'test3');

INSERT INTO company (companyID,companyName,companyAddress,companyInfo) VALUES
    (1,'C1','123 N com1', 'info for C1'),
    (2,'C2','456 E com2', 'info for C2'),
    (3,'C3','789 S com3', 'info for C3');

INSERT INTO companyJobs (companyID,jobID) VALUES
    (1,100),
    (1,101),
    (2,102),
    (3,12345678);

INSERT INTO recruiter (recruiterID,companyID, fname, lname, email) VALUES
    (80,1,'rec1','rec1','sample1@depaul.edu'),
    (81,2,'rec2','rec2','sample2@depaul.edu'),
    (82,3,'rec3','rec3','sample3@depaul.edu');
