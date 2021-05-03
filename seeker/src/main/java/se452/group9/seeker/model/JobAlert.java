package se452.group9.seeker.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "JobAlert")
public class JobAlert implements Serializable{
    @Id 
    private long id;
    private String searchTerm1;
    private String searchTerm2;
    private String searchTerm3;
}