package se452.group9.seeker.model;

// import javax.persistence.CascadeType;
// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.OneToOne;
// import javax.persistence.Table;
// import javax.validation.constraints.NotBlank;
// import java.util.Date;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;


@Document(collection = "jobTypes")
@Data
public class JobType {

    private long id;
    private String type;

    public void setId(long id){
        this.id = id;
    }
    public Long getId() {
        return this.id;
    }

    public void setType(String type){
        this.type = type;
    }
    public String getType() {
        return this.type;
    }
}