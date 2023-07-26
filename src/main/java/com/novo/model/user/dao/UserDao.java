package com.novo.model.user.dao;

import com.novo.model.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface extends the {@link CrudRepository} interface and allows you to work with {@link UserDao} objects in the database
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */

@Repository
public interface UserDao extends CrudRepository<User, Integer> {

    /**
     * Finds a user by the username
     * @param username must not be null, empty or blank
     * @return saved model instance
     */
    public User findUserByUsername(String username);


    /**
     * Checks if an entry exists in a table by the username
     * @param username must not be null
     * @return true if the entry exists or false if not
     */
    public boolean existsByUsername(String username);

}
