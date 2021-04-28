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

