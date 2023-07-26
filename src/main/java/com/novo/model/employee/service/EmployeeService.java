package com.novo.model.employee.service;

import com.novo.model.employee.Employee;
import com.novo.model.service.Service;

/**
 * The interface extends the {@link Service} interface and creates an additional layer for working with models in the database.
 * Allows you to create additional methods for working with a specific model.
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
public interface EmployeeService extends Service<Employee> {

    /**
     * Checks for the existence of an entry by the user's full name
     * @param firstName must not be null
     * @param middleName must not be null
     * @param lastName must not be null
     * @return true if the entry exists or false if not.
     */
    public boolean exists(String firstName, String middleName, String lastName);



}
