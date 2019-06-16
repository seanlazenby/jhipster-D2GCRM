package com.d2g.crm.web.rest;

import com.d2g.crm.D2GcrmApp;
import com.d2g.crm.config.TestSecurityConfiguration;
import com.d2g.crm.domain.Lead;
import com.d2g.crm.repository.LeadRepository;
import com.d2g.crm.service.LeadService;
import com.d2g.crm.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.Validator;


import java.util.List;

import static com.d2g.crm.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link LeadResource} REST controller.
 */
@SpringBootTest(classes = {D2GcrmApp.class, TestSecurityConfiguration.class})
public class LeadResourceIT {

    private static final String DEFAULT_LEAD_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LEAD_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LEAD_PHONE = "AAAAAAAAAA";
    private static final String UPDATED_LEAD_PHONE = "BBBBBBBBBB";

    private static final String DEFAULT_LEADID = "AAAAAAAAAA";
    private static final String UPDATED_LEADID = "BBBBBBBBBB";

    private static final String DEFAULT_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_ACTIVE = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVE = "BBBBBBBBBB";

    private static final String DEFAULT_HOTEL = "AAAAAAAAAA";
    private static final String UPDATED_HOTEL = "BBBBBBBBBB";

    private static final String DEFAULT_ROOMS = "AAAAAAAAAA";
    private static final String UPDATED_ROOMS = "BBBBBBBBBB";

    private static final String DEFAULT_CONTACT = "AAAAAAAAAA";
    private static final String UPDATED_CONTACT = "BBBBBBBBBB";

    private static final String DEFAULT_OFFICETEL = "AAAAAAAAAA";
    private static final String UPDATED_OFFICETEL = "BBBBBBBBBB";

    private static final String DEFAULT_CELL = "AAAAAAAAAA";
    private static final String UPDATED_CELL = "BBBBBBBBBB";

    private static final String DEFAULT_STREET_1 = "AAAAAAAAAA";
    private static final String UPDATED_STREET_1 = "BBBBBBBBBB";

    private static final String DEFAULT_STREET_2 = "AAAAAAAAAA";
    private static final String UPDATED_STREET_2 = "BBBBBBBBBB";

    private static final String DEFAULT_CITY = "AAAAAAAAAA";
    private static final String UPDATED_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_POSTALCODE = "AAAAAAAAAA";
    private static final String UPDATED_POSTALCODE = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_WEBSITE = "AAAAAAAAAA";
    private static final String UPDATED_WEBSITE = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_FOLLOWUP = "AAAAAAAAAA";
    private static final String UPDATED_FOLLOWUP = "BBBBBBBBBB";

    private static final String DEFAULT_COMMENT = "AAAAAAAAAA";
    private static final String UPDATED_COMMENT = "BBBBBBBBBB";

    @Autowired
    private LeadRepository leadRepository;

    @Autowired
    private LeadService leadService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restLeadMockMvc;

    private Lead lead;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final LeadResource leadResource = new LeadResource(leadService);
        this.restLeadMockMvc = MockMvcBuilders.standaloneSetup(leadResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Lead createEntity() {
        Lead lead = new Lead()
            .leadName(DEFAULT_LEAD_NAME)
            .leadPhone(DEFAULT_LEAD_PHONE)
            .leadid(DEFAULT_LEADID)
            .title(DEFAULT_TITLE)
            .status(DEFAULT_STATUS)
            .active(DEFAULT_ACTIVE)
            .hotel(DEFAULT_HOTEL)
            .rooms(DEFAULT_ROOMS)
            .contact(DEFAULT_CONTACT)
            .officetel(DEFAULT_OFFICETEL)
            .cell(DEFAULT_CELL)
            .street1(DEFAULT_STREET_1)
            .street2(DEFAULT_STREET_2)
            .city(DEFAULT_CITY)
            .state(DEFAULT_STATE)
            .postalcode(DEFAULT_POSTALCODE)
            .country(DEFAULT_COUNTRY)
            .website(DEFAULT_WEBSITE)
            .email(DEFAULT_EMAIL)
            .followup(DEFAULT_FOLLOWUP)
            .comment(DEFAULT_COMMENT);
        return lead;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Lead createUpdatedEntity() {
        Lead lead = new Lead()
            .leadName(UPDATED_LEAD_NAME)
            .leadPhone(UPDATED_LEAD_PHONE)
            .leadid(UPDATED_LEADID)
            .title(UPDATED_TITLE)
            .status(UPDATED_STATUS)
            .active(UPDATED_ACTIVE)
            .hotel(UPDATED_HOTEL)
            .rooms(UPDATED_ROOMS)
            .contact(UPDATED_CONTACT)
            .officetel(UPDATED_OFFICETEL)
            .cell(UPDATED_CELL)
            .street1(UPDATED_STREET_1)
            .street2(UPDATED_STREET_2)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .postalcode(UPDATED_POSTALCODE)
            .country(UPDATED_COUNTRY)
            .website(UPDATED_WEBSITE)
            .email(UPDATED_EMAIL)
            .followup(UPDATED_FOLLOWUP)
            .comment(UPDATED_COMMENT);
        return lead;
    }

    @BeforeEach
    public void initTest() {
        leadRepository.deleteAll();
        lead = createEntity();
    }

    @Test
    public void createLead() throws Exception {
        int databaseSizeBeforeCreate = leadRepository.findAll().size();

        // Create the Lead
        restLeadMockMvc.perform(post("/api/leads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lead)))
            .andExpect(status().isCreated());

        // Validate the Lead in the database
        List<Lead> leadList = leadRepository.findAll();
        assertThat(leadList).hasSize(databaseSizeBeforeCreate + 1);
        Lead testLead = leadList.get(leadList.size() - 1);
        assertThat(testLead.getLeadName()).isEqualTo(DEFAULT_LEAD_NAME);
        assertThat(testLead.getLeadPhone()).isEqualTo(DEFAULT_LEAD_PHONE);
        assertThat(testLead.getLeadid()).isEqualTo(DEFAULT_LEADID);
        assertThat(testLead.getTitle()).isEqualTo(DEFAULT_TITLE);
        assertThat(testLead.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testLead.getActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testLead.getHotel()).isEqualTo(DEFAULT_HOTEL);
        assertThat(testLead.getRooms()).isEqualTo(DEFAULT_ROOMS);
        assertThat(testLead.getContact()).isEqualTo(DEFAULT_CONTACT);
        assertThat(testLead.getOfficetel()).isEqualTo(DEFAULT_OFFICETEL);
        assertThat(testLead.getCell()).isEqualTo(DEFAULT_CELL);
        assertThat(testLead.getStreet1()).isEqualTo(DEFAULT_STREET_1);
        assertThat(testLead.getStreet2()).isEqualTo(DEFAULT_STREET_2);
        assertThat(testLead.getCity()).isEqualTo(DEFAULT_CITY);
        assertThat(testLead.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testLead.getPostalcode()).isEqualTo(DEFAULT_POSTALCODE);
        assertThat(testLead.getCountry()).isEqualTo(DEFAULT_COUNTRY);
        assertThat(testLead.getWebsite()).isEqualTo(DEFAULT_WEBSITE);
        assertThat(testLead.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testLead.getFollowup()).isEqualTo(DEFAULT_FOLLOWUP);
        assertThat(testLead.getComment()).isEqualTo(DEFAULT_COMMENT);
    }

    @Test
    public void createLeadWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = leadRepository.findAll().size();

        // Create the Lead with an existing ID
        lead.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restLeadMockMvc.perform(post("/api/leads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lead)))
            .andExpect(status().isBadRequest());

        // Validate the Lead in the database
        List<Lead> leadList = leadRepository.findAll();
        assertThat(leadList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllLeads() throws Exception {
        // Initialize the database
        leadRepository.save(lead);

        // Get all the leadList
        restLeadMockMvc.perform(get("/api/leads?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(lead.getId())))
            .andExpect(jsonPath("$.[*].leadName").value(hasItem(DEFAULT_LEAD_NAME.toString())))
            .andExpect(jsonPath("$.[*].leadPhone").value(hasItem(DEFAULT_LEAD_PHONE.toString())))
            .andExpect(jsonPath("$.[*].leadid").value(hasItem(DEFAULT_LEADID.toString())))
            .andExpect(jsonPath("$.[*].title").value(hasItem(DEFAULT_TITLE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.toString())))
            .andExpect(jsonPath("$.[*].hotel").value(hasItem(DEFAULT_HOTEL.toString())))
            .andExpect(jsonPath("$.[*].rooms").value(hasItem(DEFAULT_ROOMS.toString())))
            .andExpect(jsonPath("$.[*].contact").value(hasItem(DEFAULT_CONTACT.toString())))
            .andExpect(jsonPath("$.[*].officetel").value(hasItem(DEFAULT_OFFICETEL.toString())))
            .andExpect(jsonPath("$.[*].cell").value(hasItem(DEFAULT_CELL.toString())))
            .andExpect(jsonPath("$.[*].street1").value(hasItem(DEFAULT_STREET_1.toString())))
            .andExpect(jsonPath("$.[*].street2").value(hasItem(DEFAULT_STREET_2.toString())))
            .andExpect(jsonPath("$.[*].city").value(hasItem(DEFAULT_CITY.toString())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE.toString())))
            .andExpect(jsonPath("$.[*].postalcode").value(hasItem(DEFAULT_POSTALCODE.toString())))
            .andExpect(jsonPath("$.[*].country").value(hasItem(DEFAULT_COUNTRY.toString())))
            .andExpect(jsonPath("$.[*].website").value(hasItem(DEFAULT_WEBSITE.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].followup").value(hasItem(DEFAULT_FOLLOWUP.toString())))
            .andExpect(jsonPath("$.[*].comment").value(hasItem(DEFAULT_COMMENT.toString())));
    }
    
    @Test
    public void getLead() throws Exception {
        // Initialize the database
        leadRepository.save(lead);

        // Get the lead
        restLeadMockMvc.perform(get("/api/leads/{id}", lead.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(lead.getId()))
            .andExpect(jsonPath("$.leadName").value(DEFAULT_LEAD_NAME.toString()))
            .andExpect(jsonPath("$.leadPhone").value(DEFAULT_LEAD_PHONE.toString()))
            .andExpect(jsonPath("$.leadid").value(DEFAULT_LEADID.toString()))
            .andExpect(jsonPath("$.title").value(DEFAULT_TITLE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.toString()))
            .andExpect(jsonPath("$.hotel").value(DEFAULT_HOTEL.toString()))
            .andExpect(jsonPath("$.rooms").value(DEFAULT_ROOMS.toString()))
            .andExpect(jsonPath("$.contact").value(DEFAULT_CONTACT.toString()))
            .andExpect(jsonPath("$.officetel").value(DEFAULT_OFFICETEL.toString()))
            .andExpect(jsonPath("$.cell").value(DEFAULT_CELL.toString()))
            .andExpect(jsonPath("$.street1").value(DEFAULT_STREET_1.toString()))
            .andExpect(jsonPath("$.street2").value(DEFAULT_STREET_2.toString()))
            .andExpect(jsonPath("$.city").value(DEFAULT_CITY.toString()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE.toString()))
            .andExpect(jsonPath("$.postalcode").value(DEFAULT_POSTALCODE.toString()))
            .andExpect(jsonPath("$.country").value(DEFAULT_COUNTRY.toString()))
            .andExpect(jsonPath("$.website").value(DEFAULT_WEBSITE.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.followup").value(DEFAULT_FOLLOWUP.toString()))
            .andExpect(jsonPath("$.comment").value(DEFAULT_COMMENT.toString()));
    }

    @Test
    public void getNonExistingLead() throws Exception {
        // Get the lead
        restLeadMockMvc.perform(get("/api/leads/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateLead() throws Exception {
        // Initialize the database
        leadService.save(lead);

        int databaseSizeBeforeUpdate = leadRepository.findAll().size();

        // Update the lead
        Lead updatedLead = leadRepository.findById(lead.getId()).get();
        updatedLead
            .leadName(UPDATED_LEAD_NAME)
            .leadPhone(UPDATED_LEAD_PHONE)
            .leadid(UPDATED_LEADID)
            .title(UPDATED_TITLE)
            .status(UPDATED_STATUS)
            .active(UPDATED_ACTIVE)
            .hotel(UPDATED_HOTEL)
            .rooms(UPDATED_ROOMS)
            .contact(UPDATED_CONTACT)
            .officetel(UPDATED_OFFICETEL)
            .cell(UPDATED_CELL)
            .street1(UPDATED_STREET_1)
            .street2(UPDATED_STREET_2)
            .city(UPDATED_CITY)
            .state(UPDATED_STATE)
            .postalcode(UPDATED_POSTALCODE)
            .country(UPDATED_COUNTRY)
            .website(UPDATED_WEBSITE)
            .email(UPDATED_EMAIL)
            .followup(UPDATED_FOLLOWUP)
            .comment(UPDATED_COMMENT);

        restLeadMockMvc.perform(put("/api/leads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedLead)))
            .andExpect(status().isOk());

        // Validate the Lead in the database
        List<Lead> leadList = leadRepository.findAll();
        assertThat(leadList).hasSize(databaseSizeBeforeUpdate);
        Lead testLead = leadList.get(leadList.size() - 1);
        assertThat(testLead.getLeadName()).isEqualTo(UPDATED_LEAD_NAME);
        assertThat(testLead.getLeadPhone()).isEqualTo(UPDATED_LEAD_PHONE);
        assertThat(testLead.getLeadid()).isEqualTo(UPDATED_LEADID);
        assertThat(testLead.getTitle()).isEqualTo(UPDATED_TITLE);
        assertThat(testLead.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testLead.getActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testLead.getHotel()).isEqualTo(UPDATED_HOTEL);
        assertThat(testLead.getRooms()).isEqualTo(UPDATED_ROOMS);
        assertThat(testLead.getContact()).isEqualTo(UPDATED_CONTACT);
        assertThat(testLead.getOfficetel()).isEqualTo(UPDATED_OFFICETEL);
        assertThat(testLead.getCell()).isEqualTo(UPDATED_CELL);
        assertThat(testLead.getStreet1()).isEqualTo(UPDATED_STREET_1);
        assertThat(testLead.getStreet2()).isEqualTo(UPDATED_STREET_2);
        assertThat(testLead.getCity()).isEqualTo(UPDATED_CITY);
        assertThat(testLead.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testLead.getPostalcode()).isEqualTo(UPDATED_POSTALCODE);
        assertThat(testLead.getCountry()).isEqualTo(UPDATED_COUNTRY);
        assertThat(testLead.getWebsite()).isEqualTo(UPDATED_WEBSITE);
        assertThat(testLead.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testLead.getFollowup()).isEqualTo(UPDATED_FOLLOWUP);
        assertThat(testLead.getComment()).isEqualTo(UPDATED_COMMENT);
    }

    @Test
    public void updateNonExistingLead() throws Exception {
        int databaseSizeBeforeUpdate = leadRepository.findAll().size();

        // Create the Lead

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLeadMockMvc.perform(put("/api/leads")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(lead)))
            .andExpect(status().isBadRequest());

        // Validate the Lead in the database
        List<Lead> leadList = leadRepository.findAll();
        assertThat(leadList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteLead() throws Exception {
        // Initialize the database
        leadService.save(lead);

        int databaseSizeBeforeDelete = leadRepository.findAll().size();

        // Delete the lead
        restLeadMockMvc.perform(delete("/api/leads/{id}", lead.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Lead> leadList = leadRepository.findAll();
        assertThat(leadList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Lead.class);
        Lead lead1 = new Lead();
        lead1.setId("id1");
        Lead lead2 = new Lead();
        lead2.setId(lead1.getId());
        assertThat(lead1).isEqualTo(lead2);
        lead2.setId("id2");
        assertThat(lead1).isNotEqualTo(lead2);
        lead1.setId(null);
        assertThat(lead1).isNotEqualTo(lead2);
    }
}
