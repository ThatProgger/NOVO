package com.novo.model.entry.service;

import com.novo.model.entry.Entry;
import com.novo.model.service.Service;

/**
 * The interface extends the {@link Service} interface and creates an additional layer for working with models in the database.
 * Allows you to create additional methods for working with a specific model.
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
public interface EntryService extends Service<Entry> {
}
