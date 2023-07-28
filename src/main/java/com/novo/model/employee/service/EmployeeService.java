package com.novo.model.employee.service;

import com.novo.model.employee.Employee;
import com.novo.model.service.Service;
import org.springframework.security.core.Authentication;

/**
 * The interface extends the {@link Service} interface and creates an additional layer for working with models in the database.
 * Allows you to create additional methods for working with a specific model.
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
public interface EmployeeService extends Service<Employee> {



}
