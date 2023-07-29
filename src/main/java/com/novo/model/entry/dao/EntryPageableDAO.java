package com.novo.model.entry.dao;

import com.novo.model.entry.Entry;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * @author  Mikhail Dedyukhin
 * @since 1.0
 */

@Repository
public interface EntryPageableDAO extends PagingAndSortingRepository<Entry, Integer> {
}
