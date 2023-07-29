package com.novo.model.jobtypes.service.impl;

import com.novo.model.employee.Employee;
import com.novo.model.jobtypes.JobType;
import com.novo.model.jobtypes.dao.JobTypeDao;
import com.novo.model.jobtypes.service.JobTypeService;
import com.novo.model.user.User;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.dialect.lock.OptimisticEntityLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The class extends the {@link JobTypeService} interface and allows you to operate on models in the database.
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */

@Slf4j
@Service
public class JobTypeServiceImpl implements JobTypeService {

    @Autowired
    private JobTypeDao jobTypeDao;

    @Override
    public JobType save(JobType jobType) {
        JobType savedJobType = null;
        try {
            savedJobType = jobTypeDao.save(jobType);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (OptimisticEntityLockException e) {
            log.error(e.getMessage());
        }
        log.debug("The JobType has been saved under the id: {}", savedJobType.getId());
        return savedJobType;
    }

    @Override
    public JobType findById(int id) {
        Optional<JobType> optionalJobType = null;
        try {
            optionalJobType = jobTypeDao.findById(id);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (OptimisticEntityLockException e) {
            log.error(e.getMessage());
        }

        if (optionalJobType.isPresent()) {
            log.debug("the JobType by the id: {} was found", id);
            return optionalJobType.get();
        }
        return null;
    }

    @Override
    public void delete(JobType jobType) {
        try {
            jobTypeDao.delete(jobType);
            log.debug("The jobtype by id: {} has been deleted", jobType.getId());
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (OptimisticEntityLockException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void deleteById(int id) {
        try {
            jobTypeDao.deleteById(id);
            log.debug("The jobtype by id: {} has been deleted", id);
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (OptimisticEntityLockException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void deleteAll() {
        try {
          jobTypeDao.deleteAll();
          log.debug("The all job types deleted");
        } catch (IllegalArgumentException e) {
            log.error(e.getMessage());
        } catch (OptimisticEntityLockException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public List<JobType> findAll() {
        Iterable<JobType> jobTypes = jobTypeDao.findAll();
        List<JobType> list = new ArrayList<>();
        jobTypes.forEach(list::add);
        return list;
    }


    @Override
    public boolean existsByName(String name) {
        boolean exist = false;
        exist = jobTypeDao.existsByName(name);
        log.debug("The job type: {} exists: {}", name, exist);
        return exist;
    }
}
