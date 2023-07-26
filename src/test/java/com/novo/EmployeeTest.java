package com.novo;

import com.novo.model.employee.Employee;
import com.novo.model.employee.service.EmployeeService;
import com.novo.model.user.User;
import com.novo.model.user.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class EmployeeTest {
    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private UserService userService;



    @Test
    @RepeatedTest(10)
    public void EmployeeTest() {
       Employee employee = Employee.builder().id(0).firstName("Employee").middleName("Employee").lastName("Employee").build();
       Employee savedEmployee = employeeService.save(employee);

       User user = null;

        try {
            user = userService.findByUsername("admin");
            List<Employee> list = user.getEmployeeList();
            list.add(savedEmployee);
            user.setEmployeeList(list);
            userService.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        try {
            user = userService.findByUsername("admin");
            Assertions.assertNotEquals(0, user.getEmployeeList());
            List<Employee> list = user.getEmployeeList();
            list.remove(savedEmployee);
            user.setEmployeeList(list);
            userService.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        employeeService.deleteById(savedEmployee.getId());
    }


}
