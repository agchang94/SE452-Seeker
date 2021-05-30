package se452.group9.seeker.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import se452.group9.seeker.model.Student;
import se452.group9.seeker.repo.StudentRepository;

@Service
public class StudentServiceImpl implements UserDetailsService {

    @Autowired
    private StudentRepository studentRepository;




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepository.findbyEmail(username);
        if(student == null){
            throw new UsernameNotFoundException("User name does not exist");
        }
        
        return new CustomUserDetails(student);

        //return new org.springframework.security.core.userdetails.User(student.getEmail(), student.getPassword(), null);
    
    }

    /*private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

    }*/


    
    
}
