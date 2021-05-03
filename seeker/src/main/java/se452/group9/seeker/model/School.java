package se452.group9.seeker.model;

import java.io.Serializable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "school")
public class School implements Serializable{

    @Max(value=4, message="school ID must be between 1 and 4 characters")
    @Min(value=1, message="school ID must be between 1 and 4 characters")
    @Id
    private long schoolID;
    
    private String schoolName;


}
