package com.d2g.crm.service.impl;

import com.d2g.crm.service.OfficeService;
import com.d2g.crm.domain.Office;
import com.d2g.crm.repository.OfficeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Office}.
 */
@Service
public class OfficeServiceImpl implements OfficeService {

    private final Logger log = LoggerFactory.getLogger(OfficeServiceImpl.class);

    private final OfficeRepository officeRepository;

    public OfficeServiceImpl(OfficeRepository officeRepository) {
        this.officeRepository = officeRepository;
    }

    /**
     * Save a office.
     *
     * @param office the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Office save(Office office) {
        log.debug("Request to save Office : {}", office);
        return officeRepository.save(office);
    }

    /**
     * Get all the offices.
     *
     * @return the list of entities.
     */
    @Override
    public List<Office> findAll() {
        log.debug("Request to get all Offices");
        return officeRepository.findAll();
    }


    /**
     * Get one office by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<Office> findOne(String id) {
        log.debug("Request to get Office : {}", id);
        return officeRepository.findById(id);
    }

    /**
     * Delete the office by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Office : {}", id);
        officeRepository.deleteById(id);
    }
}
