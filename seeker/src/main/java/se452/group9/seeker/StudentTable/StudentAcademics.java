package se452.group9.seeker.StudentTable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table (name="SA")
@Data
public class StudentAcademics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SA_ID")
    private long id;
    @Column(name = "SA_ACC")
    private String accomplishments;
    @Column(name = "SA_MAJOR")
    private String major;
    @Column(name = "SA_GPA")
    private double gpa;
    @Column(name = "SA_UNI")
    private String universityName;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "st_id", nullable = false)
    private Student student;

}
