package se452.group9.seeker;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class Recruiter {
    @Min(value = 1, message = "recruiter ID must be greater than 0")
    @Max(value = 99999999, message = "recruiter ID can be at most 8 digits")
    private int recruiterID;

    @Min(value = 1, message = "company ID must be greater than 0")
    @Max(value = 99999999, message = "company ID can be at most 8 digits")
    private int companyID;

    @Size(max = 50, message = "first name can be at most 50 chars")
    private String fName;

    @Size(max = 50, message = "last name can be at most 50 chars")
    private String lName;

    @Email(message = "invalid email address format")
    private String email;


}
