package com.novo.model.role.dao;

import com.novo.model.role.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface extends the {@link CrudRepository} interface and allows you to work with {@link Role} objects in the database
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */

@Repository
public interface RoleDao extends CrudRepository<Role, Integer> {

    /**
     * Finds a role by the role name
     *
     * @param rolename must not be null
     * @return saved the role instance or null
     */
    public Role findByRole(String rolename);


    /**
     * Checks if an entry exists in a table by role name
     *
     * @param roleName must not be null
     * @return true if the entry by the role name exists or false if not.
     */
    public boolean existsByRole(String roleName);
}
