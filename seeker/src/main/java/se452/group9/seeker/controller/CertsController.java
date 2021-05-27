/*** This is a class in the making that will implement a means of easily adding, deleting, and updating my Student Certs mongodb table. it utilizes concepts of mapping. 
 * Postman software will be needed to run this class  */

/*package se452.group9.seeker.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import se452.group9.seeker.model.StudentCerts;
import se452.group9.seeker.repo.StudentCertsRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/")
public class CertsController {
    @Autowired
    private StudentCertsRepository studentCertsRepository;

    @RequestMapping("/product")
    public String product(Model model) {
        model.addAttribute("studentcerts", studentCertsRepository.findAll());
        return "certs";
    }

    
    @RequestMapping("/create")
    public String create(Model model) {
        return "create";
    }

    @RequestMapping("/save")
    public String save(@RequestParam String cert1, @RequestParam String cert2, @RequestParam String cert3, @RequestParam String cert4,
    @RequestParam String cert5){
        StudentCerts s = new StudentCerts();
        s.setCerts(Arrays.asList(cert1, cert2, cert3, cert4, cert5)); 
        studentCertsRepository.save(s);
        return "redirect:/show/" + s.getId();
    }

    @RequestMapping("/show/{id}")
    public String show(@PathVariable String id, Model model) {
        model.addAttribute("studentcerts", studentCertsRepository.findById(id).get());
        return "show";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam String id) {
        Optional<StudentCerts> cert = studentCertsRepository.findById(id);
        studentCertsRepository.delete(cert.get());

        return "redirect:/studentcerts";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable String id, Model model) {
        model.addAttribute("studentcerts", studentCertsRepository.findById(id).get());
        return "edit";
    }

    @RequestMapping("/update")
    public String update(@RequestParam String id, @RequestParam String cert1, @RequestParam String cert2, @RequestParam String cert3, @RequestParam String cert4,
    @RequestParam String cert5){
        Optional<StudentCerts> cert = studentCertsRepository.findById(id);
        cert.get().setCerts(Arrays.asList(cert1, cert2, cert3, cert4, cert5));
        studentCertsRepository.save(cert.get());
        return "redirect:/show/" + cert.get().getId();
    }



    

    
    
}
 */