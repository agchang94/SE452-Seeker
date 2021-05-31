package se452.group9.seeker.model;

import java.util.List;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;



@Data
@Document (collection = "certs")
public class StudentCerts {
    @Id
    private Long id;
    private List <String> certs;
    //private String certs;
}
