package com.d2g.crm.service;

import com.d2g.crm.domain.Office;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Office}.
 */
public interface OfficeService {

    /**
     * Save a office.
     *
     * @param office the entity to save.
     * @return the persisted entity.
     */
    Office save(Office office);

    /**
     * Get all the offices.
     *
     * @return the list of entities.
     */
    List<Office> findAll();


    /**
     * Get the "id" office.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Office> findOne(String id);

    /**
     * Delete the "id" office.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
