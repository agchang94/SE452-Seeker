INSERT INTO dummyJobs (jobID, blah) VALUES 
    (100, 'test0'),
    (101, 'test1'),
    (102, 'test2'),
    (12345678, 'test3');

INSERT INTO company (companyID,companyName,companyAddress,companyInfo) VALUES
    (20,'C1','123 N com1', 'info for C1'),
    (21,'C2','456 E com2', 'info for C2'),
    (22,'C3','789 S com3', 'info for C3');

INSERT INTO companyJobs (companyID,jobID) VALUES
    (20,100),
    (20,101),
    (21,102),
    (22,12345678);

INSERT INTO recruiter (recruiterID, companyID, fname, lname, email) VALUES
    (800,20,'rec1','rec1','sample1@depaul.edu'),
    (801,21,'rec2','rec2','sample2@depaul.edu'),
    (802,22,'rec3','rec3','sample3@depaul.edu');
