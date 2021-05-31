package se452.group9.seeker.model;

import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.*;


@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="STUDENT_RESUME")
public class StudentResume {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    
    private String isCurrentJob;
    private Date startDate;
    private Date endDate;
   
    @Column(name="jobTitle")
    private String title;

    @Column(name="companyName")
    private String company;

    @Column(name="jobCity")
    private String city;

    @Column(name="jobState")
    private String state;

    @Column(name="jobCountry")
    private String country;

    @Column(name="jobDescription")
    private String description;  

    public Long getId() {
		return id;
	} 
    
    
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "st_id", nullable = false)
    private Student student;
    
}