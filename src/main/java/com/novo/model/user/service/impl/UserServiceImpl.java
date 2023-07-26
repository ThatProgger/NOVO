package com.novo.model.user.service.impl;

import com.novo.model.employee.Employee;
import com.novo.model.employee.dao.EmployeeDao;
import com.novo.model.employee.service.EmployeeService;
import com.novo.model.role.Role;
import com.novo.model.role.dao.RoleDao;
import com.novo.model.role.service.RoleService;
import com.novo.model.user.User;
import com.novo.model.user.dao.UserDao;
import com.novo.model.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * The class extends the {@link UserService} interface and allows you to operate on models in the database.
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleService roleService;

    @Autowired
    private EmployeeService employeeService;


    @Override
    public User save(User user) {
        User savedUser = null;
        try {
            savedUser = userDao.save(user);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (OptimisticEntityLockException e) {
            log.error(e.getMessage());
        }
        log.debug("The user has been saved under the id: {}", user.getId());
        return user;
    }


    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findUserByUsername(username);
        if (user == null)
            throw new UsernameNotFoundException(String.format("The user with username \"%s\" not found", username));
        return user;
    }


    @Override
    public User findById(int id) {
        Optional<User> optionalUser = null;
        try {
            optionalUser = userDao.findById(id);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (OptimisticEntityLockException e) {
            log.error(e.getMessage());
        }

        if (optionalUser.isPresent()) {
            log.debug("the user by the id: {} was found", id);
            return optionalUser.get();
        }
        User user = User.builder().id(-1).username("").build();
        return user;
    }

    @Override
    public void delete(User user) {

        if (!"admin".equals(user.getUsername())) {
            try {
                List<Employee> list = user.getEmployeeList();
                for (Employee employee : list) {
                    employeeService.delete(employee);
                    log.debug("The employee by the id: {} deleted", employee.getId());
                }
                userDao.delete(user);
            } catch (IllegalArgumentException e) {
                log.error(e.getMessage());
            } catch (OptimisticEntityLockException e) {
                log.error(e.getMessage());
            }
            log.debug("The user by the id: {} has been removed", user.getId());
        } else {
            log.debug("Attempt to remove the portal administrator");
        }


    }

    @Override
    public void deleteById(int id) {
        try {
            Optional<User> optionalUser = userDao.findById(id);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                if (!"admin".equals(user.getUsername())) {
                    List<Employee> employees = user.getEmployeeList();
                    for (Employee employee : employees)
                        employeeService.delete(employee);
                    userDao.delete(user);
                }
            }
        } catch (
                IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (
                OptimisticEntityLockException e) {
            log.error(e.getMessage());
        }


    }

    @Override
    public void deleteAll() {
        try {
            Iterable<User> iterable = userDao.findAll();
            List<User> users = new ArrayList<>();
            iterable.forEach(users::add);

            for (User user : users){
                if(!"admin".equals(user.getUsername())){
                    List<Employee> employees = user.getEmployeeList();
                    for (Employee employee : employees)
                        employeeService.delete(employee);
                    userDao.delete(user);
                }
            }
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (OptimisticEntityLockException e) {
            log.error(e.getMessage());
        }

        log.debug("All users have been deleted");
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findUserByUsername(username);

        if (user == null)
            throw new UsernameNotFoundException(String.format("The user not exist by %s", username));

        return user;
    }

    @Override
    public List<User> findAll() {
        Iterable<User> users = userDao.findAll();
        List<User> list = new ArrayList<>();
        users.forEach(list::add);
        return list;
    }

    @Override
    public User saveOrdinaryUser(User user) {
        Role userRole = roleService.findByRole("ROLE_USER");
        User savedUser = null;

        log.debug("The role {} found", userRole.toString());
        user.setRoleSet(Set.of(userRole));
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setEnabled(true);
        user.setCredentialsNonExpired(true);
        user.setAccountNonLocked(true);
        user.setAccountNonExpired(true);

        try {
            savedUser = userDao.save(user);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (OptimisticEntityLockException e) {
            log.error(e.getMessage());
        }

        log.debug("The user has been saved under the id: {}", savedUser.getId());
        return user;
    }

    @Override
    public boolean existsByUsername(String username) {
        boolean exist = false;

        try {
            exist = userDao.existsByUsername(username);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (OptimisticEntityLockException e) {
            log.error(e.getMessage());
        }

        log.debug("The user by the username: {} exists: {}", username, exist);
        return exist;
    }


    @Override
    public void addNewEmployee(Employee employee, Authentication authentication) {

        try {
            User user = userDao.findUserByUsername(authentication.getName());
            Employee savedEmployee = employeeService.save(employee);
            List<Employee> list = user.getEmployeeList();
            list.add(savedEmployee);
            user.setEmployeeList(list);

            log.debug("The employee list of the user \"{}\" has been updated", userDao.save(user).getUsername());
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (OptimisticEntityLockException e) {
            log.error(e.getMessage());
        }
    }

}
