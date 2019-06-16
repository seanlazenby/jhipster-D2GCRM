package com.d2g.crm.service;

import com.d2g.crm.domain.Hotel;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Hotel}.
 */
public interface HotelService {

    /**
     * Save a hotel.
     *
     * @param hotel the entity to save.
     * @return the persisted entity.
     */
    Hotel save(Hotel hotel);

    /**
     * Get all the hotels.
     *
     * @return the list of entities.
     */
    List<Hotel> findAll();


    /**
     * Get the "id" hotel.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Hotel> findOne(String id);

    /**
     * Delete the "id" hotel.
     *
     * @param id the id of the entity.
     */
    void delete(String id);
}
