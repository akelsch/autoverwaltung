package de.htwsaar.prog3.carrental.dao;

import java.io.Serializable;

/**
 * Interface for Data Access Objects. Defines basic CRUD methods.
 *
 * @param <T>  the type of the entity
 * @param <ID> the type of the primary key
 * @author Arthur Kelsch
 * @see <a href="https://bit.ly/2vlJaS0">EntityManager</a>
 * @see <a href="https://bit.ly/2vnoGs2">CrudRepository</a>
 */
public interface GenericDao<T, ID extends Serializable> {
    /**
     * Persists a given entity (Create).
     *
     * @param entity entity instance
     */
    void persist(T entity);

    /**
     * Finds a single entity by its ID (Read).
     *
     * @param id primary key
     * @return the found entity instance or null if the entity does not exist
     */
    T findById(ID id);
    // List<T> findAll();

    /**
     * Updates a given entity (Update).
     *
     * @param entity entity instance
     */
    void update(T entity);

    /**
     * Removes a given entity (Delete).
     *
     * @param entity entity instance
     */
    void remove(T entity);
}
