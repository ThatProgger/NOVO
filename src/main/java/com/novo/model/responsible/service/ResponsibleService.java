package com.novo.model.responsible.service;

import com.novo.model.responsible.Responsible;
import com.novo.model.service.Service;

import java.io.Serial;

/**
 * The interface extends the {@link Service} interface and creates an additional layer for working with models in the database.
 * Allows you to create additional methods for working with a specific model.
 *
 * @author Mikhail Dedyukhin
 * @since 1.0
 */
public interface ResponsibleService extends Service<Responsible> {
}
