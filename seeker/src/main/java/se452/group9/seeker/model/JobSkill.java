package se452.group9.seeker.model;


import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "jobSkills")
@Data
@Getter 
@Setter 
public class JobSkill {
    @Id 
    private long id;
    private String skills;
}