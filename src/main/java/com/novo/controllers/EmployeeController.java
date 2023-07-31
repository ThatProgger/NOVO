package com.novo.controllers;

import com.novo.model.employee.Employee;
import com.novo.model.employee.service.EmployeeService;
import com.novo.model.user.User;
import com.novo.model.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
@Slf4j
@Controller
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmployeeService employeeService;


    @GetMapping
    public String getEmployees (Model model, Authentication authentication){
        List<Employee> employees = null;
        try {
            employees = userService.findByUsername(authentication.getName()).getEmployeeList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("fpath", "fragments/employees");
        model.addAttribute("fname", "employees");
        model.addAttribute("employeeList", employees);
        return "index";
    }


    @GetMapping("add")
    public String getAddEmployee(Model model){
        model.addAttribute("fpath", "fragments/employee-add");
        model.addAttribute("fname", "add");
        model.addAttribute("employee", new Employee());
        return "index";
    }


    @PostMapping("add")
    public String postAddEmployee(@ModelAttribute Employee employee, Authentication authentication){
        log.debug(employee.toString());
        Employee savedEmployee = employeeService.save(employee);
        try {
            User user = userService.findByUsername(authentication.getName());
            List<Employee> list = user.getEmployeeList();
            list.add(savedEmployee);
            user.setEmployeeList(list);
            userService.save(user);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/employee";
    }

    @GetMapping("delete")
    public String deleteById(@RequestParam("id") int id){
        employeeService.deleteById(id);
        return "redirect:/employee";
    }
}
