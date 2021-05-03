package se452.group9.seeker.model;

//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;
//import javax.persistence.Table;
import javax.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Document(collection = "STUDENT_ATTRIBUTES")
@NoArgsConstructor
@AllArgsConstructor
public class StudentAttributes {

    @Id
    private int studentID;
    private String skills;
    private String languages;


    /** 
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "st_id", nullable = false)
    private Student student;
    */
}