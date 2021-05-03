package se452.group9.seeker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "STUDENT_ATTRIBUTES")
public class StudentAttributes {

    @Id
    @GeneratedValue
    private Long id;

    private int studentID;
    private String skills;
    private String languages;
}