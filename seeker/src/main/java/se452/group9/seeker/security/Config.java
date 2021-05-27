package se452.group9.seeker.security;

/*import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity

public class Config extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {
    
        public void addViewControllers(ViewControllerRegistry registry) {
            registry.addViewController("/").setViewName("index");
        }    
            
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                .authorizeRequests()
                    .antMatchers("/", "/jobs/register", "/jobs/jobPosts").permitAll()
                //    .and()
                //.authorizeRequests()
                //    .antMatchers("/jobs/jobPosts").hasAnyRole("STUDENT")
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();

                    //http.authorizeRequests().antMatchers("jobs/jobPosts").hasRole("STUDENT");
                    //http.authorizeRequests().antMatchers("jobs/jobPosts").access("hasAnyRole('STUDENT')");
        }
    
        @Bean
        @Override
        public UserDetailsService userDetailsService() {
            UserDetails user =
                 User.withDefaultPasswordEncoder()
                    .username("demo-user")
                    .password("password")
                    .roles("STUDENT")
                    .build();
    
            return new InMemoryUserDetailsManager(user);
        }
    
    }*/
