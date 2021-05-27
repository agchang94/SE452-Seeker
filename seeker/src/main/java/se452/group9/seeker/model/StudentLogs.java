package se452.group9.seeker.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
//import javax.persistence.JoinColumn;
//import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

/*
     Add to student class 
    @OneToMany(mappedBy="student", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<StudentResume> studentResume;

    @OneToOne (mappedBy="student", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private StudentLogs studentLog;

    @OneToOne(mappedBy="student", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private StudentAttributes studentAttributes;
*/
 

@Data
@Entity
@Table(name="STUDENT_LOGS")
public class StudentLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(name="lastLoginDate")
    private Date lastLogin;

    @Column(name="lastApplicationDate")
    private Date lastApplication;

    
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "st_id", nullable = false)
    private Student student;

}