package com.novo.model.entry.dao;

import com.novo.model.employee.Employee;
import com.novo.model.entry.Entry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface extends the {@link CrudRepository} interface and allows you to work with {@link Entry} objects in the database
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
@Repository
public interface EntryDao extends CrudRepository<Entry, Integer> {

    /**
     * Finds all entries
     * @return the inverted collection
     */
    @Query("from Entry order by id desc")
    public List<Entry> findAllinInvertedOrder();
}

