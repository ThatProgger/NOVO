package com.novo.model.jobtypes.service;

import com.novo.model.jobtypes.JobType;
import com.novo.model.service.Service;

/**
 * The interface extends the {@link Service} interface and creates an additional layer for working with models in the database.
 * Allows you to create additional methods for working with a specific model.
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
public interface JobTypeService extends Service<JobType> {

    /**
     * checks for the existence of a record in the database
     * @param name must not be null, empty or blank
     * @return true if exists or false if not
     */
    public boolean existsByName(String name);
}
