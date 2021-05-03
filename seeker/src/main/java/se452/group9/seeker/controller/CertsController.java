/*** This is a class in the making that will implement a means of easily adding, deleting, and updating my Student Certs mongodb table. it utilizes concepts of mapping. 
 * Postman software will be needed to run this class  */

package se452.group9.seeker.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import se452.group9.seeker.model.StudentCerts;
import se452.group9.seeker.repo.StudentCertsRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class CertsController {
    @Autowired
    private StudentCertsRepository studentCertsRepository;

    public CertsController(StudentCertsRepository studentCertsRepository) {
        this.studentCertsRepository=studentCertsRepository;
    }

    @GetMapping("/all")
    public List<StudentCerts> getAll() {
        List<StudentCerts> certs = this.studentCertsRepository.findAll();
        return certs;
    }

    @PostMapping("/addCert")
    public void insert(@RequestBody StudentCerts studentCerts) {
        this.studentCertsRepository.insert(studentCerts);
    }

    public void update(@RequestBody StudentCerts studentCerts) {
        this.studentCertsRepository.save(studentCerts);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        this.studentCertsRepository.deleteById(id);
    }

    
}
