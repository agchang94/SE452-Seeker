package se452.group9.seeker.model;


//import javax.persistence.Column;
//import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.validation.constraints.Email;
//import javax.validation.constraints.Max;
//import javax.validation.constraints.Min;
//import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Entity
//@Table(name = "recruiters")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "recruiterProfiles")
public class Recruiter {

    @Id
    @GeneratedValue
    //@Min(value = 1, message = "recruiter ID must be greater than 0")
    //@Max(value = 99999999, message = "recruiter ID can be at most 8 digits")
    private int recruiterID;

    //@Column(name = "companyID")
    //@Min(value = 1, message = "company ID must be greater than 0")
    //@Max(value = 99999999, message = "company ID can be at most 8 digits")
    private int companyID;

    //@Column(name = "fname")
    //@Size(max = 50, message = "first name can be at most 50 chars")
    private String fname;

    //@Column(name = "lname")
    //@Size(max = 50, message = "last name can be at most 50 chars")
    private String lname;

    //@Column(name = "email")
    //@Email(message = "invalid email address format")
    private String email;

}
