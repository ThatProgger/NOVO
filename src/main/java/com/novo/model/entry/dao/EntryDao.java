package com.novo.model.entry.dao;

import com.novo.model.employee.Employee;
import com.novo.model.entry.Entry;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface extends the {@link CrudRepository} interface and allows you to work with {@link Entry} objects in the database
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
@Repository
public interface EntryDao extends CrudRepository<Entry, Integer> {
}
