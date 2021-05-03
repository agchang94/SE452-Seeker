package se452.group9.seeker.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="STUDENT_LOGS")
public class StudentLogs {
    @Id
    @GeneratedValue
    private Long id;

    private int studentID;
    
    @Column(name="lastLoginDate")
    private Date lastLogin;

    @Column(name="lastApplicationDate")
    private Date lastApplication;
}