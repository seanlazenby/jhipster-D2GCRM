package com.d2g.crm.repository;

import com.d2g.crm.domain.Office;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


/**
 * Spring Data MongoDB repository for the Office entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OfficeRepository extends MongoRepository<Office, String> {

}
