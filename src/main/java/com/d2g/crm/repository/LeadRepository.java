package com.d2g.crm.repository;

import com.d2g.crm.domain.Lead;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Lead entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LeadRepository extends MongoRepository<Lead, String> {

}
