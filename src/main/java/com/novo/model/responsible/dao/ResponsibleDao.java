package com.novo.model.responsible.dao;

import com.novo.model.entry.Entry;
import com.novo.model.responsible.Responsible;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface extends the {@link CrudRepository} interface and allows you to work with {@link Responsible} objects in the database
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
@Repository
public interface ResponsibleDao extends CrudRepository<Responsible, Integer> {
}
