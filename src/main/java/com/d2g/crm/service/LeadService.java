package com.d2g.crm.service;

import com.d2g.crm.domain.Lead;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Lead}.
 */
public interface LeadService {

    /**
     * Save a lead.
     *
     * @param lead the entity to save.
     * @return the persisted entity.
     */
    Lead save(Lead lead);

    /**
     * Get all the leads.
     *
     * @return the list of entities.
     */
    List<Lead> findAll();


    /**
     * Get the "id" lead.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Lead> findOne(String id);

    /**
     * Delete the "id" lead.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
