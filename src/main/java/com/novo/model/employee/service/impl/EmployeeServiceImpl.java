package com.novo.model.employee.service.impl;

import com.novo.model.employee.Employee;
import com.novo.model.employee.dao.EmployeeDao;
import com.novo.model.employee.service.EmployeeService;
import com.novo.model.role.Role;
import com.novo.model.role.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The class extends the {@link RoleService} interface and allows you to operate on models in the database.
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;

    @Override
    public Employee save(Employee employee) {
        Employee savedEmployee = null;
        try {
            savedEmployee = employeeDao.save(employee);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (OptimisticEntityLockException e) {
            log.error(e.getMessage());
        }

        log.debug("The employee has been saved by the id: {}", savedEmployee.getId());
        return savedEmployee;
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> optionalEmployee = null;
        try {
            optionalEmployee = employeeDao.findById(id);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (OptimisticEntityLockException e) {
            log.error(e.getMessage());
        }

        if (optionalEmployee.isPresent()) {
            log.debug("The employee by id: {} was found", optionalEmployee.get().getId());
            return optionalEmployee.get();
        }

        log.debug("The employee by id: {} was not found");
        Employee employee = Employee.builder().id(-1).firstName("").middleName("").lastName("").build();
        return employee;
    }

    @Override
    public void delete(Employee employee) {
        try {
            employeeDao.delete(employee);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (OptimisticEntityLockException e) {
            log.error(e.getMessage());
        }

        log.debug("The employee by the id: {} has been removed", employee.getId());
    }

    @Override
    public void deleteById(int id) {
        try {
            employeeDao.deleteById(id);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (OptimisticEntityLockException e) {
            log.error(e.getMessage());
        }

        log.debug("The employee by the id: {} has been removed", id);
    }

    @Override
    public void deleteAll() {
        try {
            employeeDao.deleteAll();
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (OptimisticEntityLockException e) {
            log.error(e.getMessage());
        }

        log.debug("All employees have been removed");
    }

    @Override
    public List<Employee> findAll() {
        Iterable<Employee> iterable = employeeDao.findAll();
        List<Employee> employees = new ArrayList<>();
        iterable.forEach(employees::add);
        return employees;
    }

    @Override
    public boolean exists(String firstName, String middleName, String lastName) {
        boolean exists = employeeDao.existsByFirstNameAndMiddleNameAndLastName(firstName, middleName, lastName);
        log.debug("The employee {} {} {} exists: {}", firstName, middleName, lastName, exists);
        return exists;
    }

}
