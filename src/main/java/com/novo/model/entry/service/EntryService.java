package com.novo.model.entry.service;

import com.novo.model.entry.Entry;
import com.novo.model.service.Service;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * The interface extends the {@link Service} interface and creates an additional layer for working with models in the database.
 * Allows you to create additional methods for working with a specific model.
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
public interface EntryService extends Service<Entry> {

    /**
     * Finds all entries
     * @return the inverted collection
     */
    @Query("from Entry order by id desc")
    public List<Entry> findAllinInvertedOrder();
}
