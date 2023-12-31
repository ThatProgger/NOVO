package com.novo;

import com.novo.model.jobtypes.JobType;
import com.novo.model.jobtypes.service.JobTypeService;
import com.novo.model.role.Role;
import com.novo.model.role.service.RoleService;
import com.novo.model.user.User;
import com.novo.model.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

/**
 * @see <a href='https://docs.spring.io/spring-security/reference/servlet/authorization/method-security.html'>Method Security</a>
 */
@SpringBootApplication
@EnableJpaAuditing
public class App implements CommandLineRunner {

    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;

    @Autowired
    private JobTypeService jobTypeService;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        if (!roleService.existsByRole("ROLE_ADMIN")) {
            Role role = Role.builder().id(0).role("ROLE_ADMIN").build();
            roleService.save(role);
        }

        if(!roleService.existsByRole("ROLE_USER")){
            Role role = Role.builder().id(0).role("ROLE_USER").build();
            roleService.save(role);
        }

        Role roleAdmin = roleService.findByRole("ROLE_ADMIN");
        Role roleUser = roleService.findByRole("ROLE_USER");

        if(!userService.existsByUsername("admin")){
            User user = User
                    .builder().id(0)
                    .username("admin")
                    .password(new BCryptPasswordEncoder().encode("admin"))
                    .firstName("Михиал")
                    .middleName("Сергеевич")
                    .lastName("Дедюхин")
                    .position("Web разработчик")
                    .isAccountNonExpired(true)
                    .isAccountNonLocked(true)
                    .isEnabled(true)
                    .isCredentialsNonExpired(true)
                    .roleSet(Set.of(roleAdmin, roleUser))
                    .build();

            userService.save(user);
        }



        if(!jobTypeService.existsByName("Телефония")){
            JobType jobType = JobType.builder().id(0).name("Телефония").build();
            jobTypeService.save(jobType);

        }

        if(!jobTypeService.existsByName("Сеть и интернет")){
            JobType jobType = JobType.builder().id(0).name("Сеть и интернет").build();
            jobTypeService.save(jobType);
        }
        if(!jobTypeService.existsByName("АСУПОГ")){
            JobType jobType = JobType.builder().id(0).name("АСУПОГ").build();
            jobTypeService.save(jobType);
        }

        if(!jobTypeService.existsByName("Прочее")){
            JobType jobType = JobType.builder().id(0).name("Прочее").build();
            jobTypeService.save(jobType);
        }

    }
}
