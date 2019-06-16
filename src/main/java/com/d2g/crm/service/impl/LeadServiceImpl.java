package com.d2g.crm.service.impl;

import com.d2g.crm.service.LeadService;
import com.d2g.crm.domain.Lead;
import com.d2g.crm.repository.LeadRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link Lead}.
 */
@Service
public class LeadServiceImpl implements LeadService {

    private final Logger log = LoggerFactory.getLogger(LeadServiceImpl.class);

    private final LeadRepository leadRepository;

    public LeadServiceImpl(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    /**
     * Save a lead.
     *
     * @param lead the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Lead save(Lead lead) {
        log.debug("Request to save Lead : {}", lead);
        return leadRepository.save(lead);
    }

    /**
     * Get all the leads.
     *
     * @return the list of entities.
     */
    @Override
    public List<Lead> findAll() {
        log.debug("Request to get all Leads");
        return leadRepository.findAll();
    }


    /**
     * Get one lead by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    public Optional<Lead> findOne(String id) {
        log.debug("Request to get Lead : {}", id);
        return leadRepository.findById(id);
    }

    /**
     * Delete the lead by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(String id) {
        log.debug("Request to delete Lead : {}", id);
        leadRepository.deleteById(id);
    }
}
