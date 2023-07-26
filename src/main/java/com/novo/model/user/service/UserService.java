package com.novo.model.user.service;

import com.novo.model.employee.Employee;
import com.novo.model.service.Service;
import com.novo.model.user.User;
import org.springframework.security.core.Authentication;

/**
 * The interface extends the {@link Service} interface and creates an additional layer for working with models in the database.
 * Allows you to create additional methods for working with a specific model.
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
public interface UserService extends Service<User> {

    /**
     * Finds a user by the username
     * @param username must not be null, empty or blank
     * @return saved user instance
     */
    public User findByUsername (String username) throws Exception;


    /**
     * Saves a user with rights corresponding to the user ROLE_USER
     * @param user must not be null
     * @return saved user instance
     */
    public User saveOrdinaryUser(User user);




    /**
     * Checks if an entry exists in a table by the username
     * @param username must not be null
     * @return true if the entry exists or false if not
     */
    public boolean existsByUsername(String username);

    /**
     * Adds new employee to user employee list
     * @param employee must not be null
     * @param authentication contains username
     */
    public void addNewEmployee (Employee employee, Authentication authentication);


}
