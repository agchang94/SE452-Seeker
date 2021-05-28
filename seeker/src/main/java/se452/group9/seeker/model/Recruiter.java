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

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Entity
//@Table(name = "recruiters")
@Data
@Getter
@Setter
@Document(collection = "recruiterProfiles")
public class Recruiter {

    @Id
    @GeneratedValue
    private long recruiterID;

    private String recruiterCompany;

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
