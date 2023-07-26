package com.novo;

import com.novo.model.role.Role;
import com.novo.model.role.service.RoleService;
import com.novo.model.user.User;
import com.novo.model.user.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@SpringBootTest
public class UserControllerTest {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @Test
    public void test() {
        Role roleAdmin = Role.builder().id(0).role("ROLE_ADMIN").build();
        Role roleUser = Role.builder().id(0).role("ROLE_USER").build();

        Role savedRoleAdmin = roleService.save(roleAdmin);
        Role savedRoleUser = roleService.save(roleUser);

        Assertions.assertNotNull(savedRoleAdmin);
        Assertions.assertNotNull(savedRoleUser);


        User user = User.builder()
                .id(0)
                .username("user")
                .password(new BCryptPasswordEncoder().encode("user"))
                .firstName("Алексей")
                .middleName("Иванович")
                .lastName("Матвеев")
                .position("Инженер связи")
                .roleSet(Set.of(savedRoleUser))
                .isEnabled(true)
                .isCredentialsNonExpired(true)
                .isAccountNonLocked(true)
                .isAccountNonExpired(true)
                .build();


        User superAdmin = User.builder()
                .id(0)
                .username("admin")
                .password(new BCryptPasswordEncoder().encode("admin"))
                .firstName("Михаил")
                .middleName("Сергеевич")
                .lastName("Дедюхин")
                .position("Web developer")
                .roleSet(Set.of(savedRoleUser, savedRoleAdmin))
                .isEnabled(true)
                .isCredentialsNonExpired(true)
                .isAccountNonLocked(true)
                .isAccountNonExpired(true)
                .build();


        User simpleAdmin = User.builder()
                .id(0)
                .username("simple")
                .password(new BCryptPasswordEncoder().encode("simple"))
                .firstName("Игорь")
                .middleName("Владимирович")
                .lastName("Рюмкин")
                .position("Простой админ")
                .roleSet(Set.of(savedRoleAdmin))
                .isEnabled(true)
                .isCredentialsNonExpired(true)
                .isAccountNonLocked(true)
                .isAccountNonExpired(true)
                .build();


        userService.save(user);
        userService.save(simpleAdmin);
        userService.save(superAdmin);
    }
}
