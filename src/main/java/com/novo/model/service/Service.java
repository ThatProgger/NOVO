package com.novo.model.service;

import java.util.List;

/**
 * The interface allows you to implement a layer with basic methods for working with objects in the database.
 * @param <MODEL> generic model type
 */
public interface Service <MODEL>{

    /**
     * Saves the model to the database
     * @param model must not be null
     * @return saved model instance
     */
    public MODEL save (MODEL model);

    /**
     * Finds a model by the id
     * @param id must not be null, zero or less than zero
     * @return saved model instance
     */
    public MODEL findById(int id);

    /**
     * Deletes the model from the database
     * @param model must not be null
     */
    public void delete (MODEL model);

    /**
     * Deletes a model by the id
     * @param id must not be null, zero or less than zero
     */
    public void deleteById (int id);


    /**
     * Deletes all models from the databse
     */
    public void deleteAll ();


    /**
     * Finds all entries in the database
     * @return the list of entries
     */
    public List<MODEL> findAll ();
}
