package com.novo.model.role.service;

import com.novo.model.role.Role;
import com.novo.model.service.Service;

/**
 * The interface extends the {@link Service} interface and creates an additional layer for working with models in the database.
 * Allows you to create additional methods for working with a specific model.
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
public interface RoleService extends Service<Role> {

    /**
     * Finds a role by the role name
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
