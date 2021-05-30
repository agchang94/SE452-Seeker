package se452.group9.seeker.model;

import javax.persistence.Id;

import java.io.Serializable;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@Document(collection = "JobAlert")
public class JobAlert implements Serializable{
    @Id 
    private long id;
    private String searchTerm1;
    private String searchTerm2;
    private String searchTerm3;
}