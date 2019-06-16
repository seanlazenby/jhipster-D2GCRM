package com.d2g.crm.web.rest;

import com.d2g.crm.D2GcrmApp;
import com.d2g.crm.config.TestSecurityConfiguration;
import com.d2g.crm.domain.Office;
import com.d2g.crm.repository.OfficeRepository;
import com.d2g.crm.service.OfficeService;
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
 * Integration tests for the {@Link OfficeResource} REST controller.
 */
@SpringBootTest(classes = {D2GcrmApp.class, TestSecurityConfiguration.class})
public class OfficeResourceIT {

    private static final String DEFAULT_OFFICE_CITY = "AAAAAAAAAA";
    private static final String UPDATED_OFFICE_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_OFFICE_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_OFFICE_COUNTRY = "BBBBBBBBBB";

    @Autowired
    private OfficeRepository officeRepository;

    @Autowired
    private OfficeService officeService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restOfficeMockMvc;

    private Office office;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final OfficeResource officeResource = new OfficeResource(officeService);
        this.restOfficeMockMvc = MockMvcBuilders.standaloneSetup(officeResource)
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
    public static Office createEntity() {
        Office office = new Office()
            .officeCity(DEFAULT_OFFICE_CITY)
            .officeCountry(DEFAULT_OFFICE_COUNTRY);
        return office;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Office createUpdatedEntity() {
        Office office = new Office()
            .officeCity(UPDATED_OFFICE_CITY)
            .officeCountry(UPDATED_OFFICE_COUNTRY);
        return office;
    }

    @BeforeEach
    public void initTest() {
        officeRepository.deleteAll();
        office = createEntity();
    }

    @Test
    public void createOffice() throws Exception {
        int databaseSizeBeforeCreate = officeRepository.findAll().size();

        // Create the Office
        restOfficeMockMvc.perform(post("/api/offices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(office)))
            .andExpect(status().isCreated());

        // Validate the Office in the database
        List<Office> officeList = officeRepository.findAll();
        assertThat(officeList).hasSize(databaseSizeBeforeCreate + 1);
        Office testOffice = officeList.get(officeList.size() - 1);
        assertThat(testOffice.getOfficeCity()).isEqualTo(DEFAULT_OFFICE_CITY);
        assertThat(testOffice.getOfficeCountry()).isEqualTo(DEFAULT_OFFICE_COUNTRY);
    }

    @Test
    public void createOfficeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = officeRepository.findAll().size();

        // Create the Office with an existing ID
        office.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restOfficeMockMvc.perform(post("/api/offices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(office)))
            .andExpect(status().isBadRequest());

        // Validate the Office in the database
        List<Office> officeList = officeRepository.findAll();
        assertThat(officeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllOffices() throws Exception {
        // Initialize the database
        officeRepository.save(office);

        // Get all the officeList
        restOfficeMockMvc.perform(get("/api/offices?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(office.getId())))
            .andExpect(jsonPath("$.[*].officeCity").value(hasItem(DEFAULT_OFFICE_CITY.toString())))
            .andExpect(jsonPath("$.[*].officeCountry").value(hasItem(DEFAULT_OFFICE_COUNTRY.toString())));
    }
    
    @Test
    public void getOffice() throws Exception {
        // Initialize the database
        officeRepository.save(office);

        // Get the office
        restOfficeMockMvc.perform(get("/api/offices/{id}", office.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(office.getId()))
            .andExpect(jsonPath("$.officeCity").value(DEFAULT_OFFICE_CITY.toString()))
            .andExpect(jsonPath("$.officeCountry").value(DEFAULT_OFFICE_COUNTRY.toString()));
    }

    @Test
    public void getNonExistingOffice() throws Exception {
        // Get the office
        restOfficeMockMvc.perform(get("/api/offices/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateOffice() throws Exception {
        // Initialize the database
        officeService.save(office);

        int databaseSizeBeforeUpdate = officeRepository.findAll().size();

        // Update the office
        Office updatedOffice = officeRepository.findById(office.getId()).get();
        updatedOffice
            .officeCity(UPDATED_OFFICE_CITY)
            .officeCountry(UPDATED_OFFICE_COUNTRY);

        restOfficeMockMvc.perform(put("/api/offices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedOffice)))
            .andExpect(status().isOk());

        // Validate the Office in the database
        List<Office> officeList = officeRepository.findAll();
        assertThat(officeList).hasSize(databaseSizeBeforeUpdate);
        Office testOffice = officeList.get(officeList.size() - 1);
        assertThat(testOffice.getOfficeCity()).isEqualTo(UPDATED_OFFICE_CITY);
        assertThat(testOffice.getOfficeCountry()).isEqualTo(UPDATED_OFFICE_COUNTRY);
    }

    @Test
    public void updateNonExistingOffice() throws Exception {
        int databaseSizeBeforeUpdate = officeRepository.findAll().size();

        // Create the Office

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOfficeMockMvc.perform(put("/api/offices")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(office)))
            .andExpect(status().isBadRequest());

        // Validate the Office in the database
        List<Office> officeList = officeRepository.findAll();
        assertThat(officeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteOffice() throws Exception {
        // Initialize the database
        officeService.save(office);

        int databaseSizeBeforeDelete = officeRepository.findAll().size();

        // Delete the office
        restOfficeMockMvc.perform(delete("/api/offices/{id}", office.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Office> officeList = officeRepository.findAll();
        assertThat(officeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Office.class);
        Office office1 = new Office();
        office1.setId("id1");
        Office office2 = new Office();
        office2.setId(office1.getId());
        assertThat(office1).isEqualTo(office2);
        office2.setId("id2");
        assertThat(office1).isNotEqualTo(office2);
        office1.setId(null);
        assertThat(office1).isNotEqualTo(office2);
    }
}
