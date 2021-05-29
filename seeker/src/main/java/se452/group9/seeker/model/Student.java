package se452.group9.seeker.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Data;


@Entity
@Table(name = "ST")
@Data
public class Student {
   
    // @GeneratedValue(strategy = GenerationType.AUTO) -> Would use this to auto generate values for the primary key 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "ST_fname")
    private String fname;
    @Column(name = "ST_lname")
    private String lname;
    @Column(name="ST_pword")
    private String password;
    @Column(name = "ST_email")
    private String email; 
   
    @Column(name = "ST_state")
    private String state;
    @Column(name = "ST_city")
    private String city;
    @Column(name = "ST_address")
    private String address;
    @Column(name = "ST_zip")
    private int zip;
    @Column(name = "ST_phone")
    private String phone;
    @Column(name = "ST_dob")
    private String dob; 

    @OneToOne(mappedBy = "student", fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    private StudentAcademics studentAcademics;

    @OneToOne(mappedBy = "student", fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    private StudentLogs studentLogs;
    
    @OneToMany (mappedBy = "student", fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    private List<StudentResume> studentResumes;

    public Long getId() {
		return id;
	}

    public void setId(Long id) {
		this.id = id;
	}


}
