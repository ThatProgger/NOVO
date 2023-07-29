package com.novo.model.jobtypes.dao;

import com.novo.model.jobtypes.JobType;
import com.novo.model.user.dao.UserDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface extends the {@link CrudRepository} interface and allows you to work with {@link JobType} objects in the database
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */

@Repository
public interface JobTypeDao extends CrudRepository<JobType, Integer> {


    /**
     * checks for the existence of a record in the database
     * @param name must not be null, empty or blank
     * @return true if exists or false if not
     */
    public boolean existsByName(String name);
}
