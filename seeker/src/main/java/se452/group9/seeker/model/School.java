package se452.group9.seeker.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;


import lombok.Data;

@Data
@Entity
@Table(name = "schools")
public class School {

    // @Size(min = 1, max = 4, message = "school ID must be between 1 and 4 characters")
    @Max(value=4, message="school ID must be between 1 and 4 characters")
    @Min(value=1, message="school ID must be between 1 and 4 characters")
    @Id
    private long schoolID;
    
    private String schoolName;


}
