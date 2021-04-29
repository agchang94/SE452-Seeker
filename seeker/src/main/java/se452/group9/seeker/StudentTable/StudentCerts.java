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
@Table(name="SC")
@Data
public class StudentCerts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SC_ID")
    private long id;
    @Column(name = "SC_CT")
    private String certificates;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "st_id", nullable = false)
    private Student student;
}
