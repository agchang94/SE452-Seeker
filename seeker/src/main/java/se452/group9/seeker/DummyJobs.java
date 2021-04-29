package se452.group9.seeker;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import lombok.Data;

@Entity
@Table(name = "dummy_jobs")
@Data
public class DummyJobs {
    
    @Id
    @GeneratedValue
    @Min(value = 1, message = "company ID must be greater than 0")
    @Max(value = 99999999, message = "company ID can be at most 8 digits")
    private int dummyJobID;

    @Column(name = "jobTitle")
    @Size(min = 1, max = 50, message = "limit for job title is 50 chars")
    private String jobTitle;

    @Column(name = "jobInfo")
    @Size(max = 250, message = "limit for job info is 250 chars.")
    private String jobMsg;
    
}
