package com.novo.model.employee.dao;


import com.novo.model.employee.Employee;
import com.novo.model.role.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface extends the {@link CrudRepository} interface and allows you to work with {@link Employee} objects in the database
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
@Repository
public interface EmployeeDao extends CrudRepository<Employee, Integer> {

    /**
     * Checks for the existence of an entry by the user's full name
     * @param firstName must not be null
     * @param middleName must not be null
     * @param lastName must not be null
     * @return true if the entry exists or false if not.
     */
    public boolean existsByFirstNameAndMiddleNameAndLastName(String firstName, String middleName, String lastName);



}
