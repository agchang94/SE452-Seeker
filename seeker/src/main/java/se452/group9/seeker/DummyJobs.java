package se452.group9.seeker;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class DummyJobs {

    private int id;
    
    @Min(value = 1, message = "company ID must be greater than 0")
    @Max(value = 99999999, message = "company ID can be at most 8 digits")
    private int dummyJobID;

    @Size(min = 1, max = 50, message = "limit for job title is 50 chars")
    private String jobTitle;

    @Size(max = 250, message = "limit for job info is 250 chars.")
    private String jobMsg;

    public DummyJobs (String jobDesc) {
        this.jobMsg = jobDesc;
    }
}
