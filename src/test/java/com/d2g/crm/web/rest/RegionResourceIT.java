package com.d2g.crm.web.rest;

import com.d2g.crm.D2GcrmApp;
import com.d2g.crm.config.TestSecurityConfiguration;
import com.d2g.crm.domain.Region;
import com.d2g.crm.repository.RegionRepository;
import com.d2g.crm.service.RegionService;
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
 * Integration tests for the {@Link RegionResource} REST controller.
 */
@SpringBootTest(classes = {D2GcrmApp.class, TestSecurityConfiguration.class})
public class RegionResourceIT {

    private static final String DEFAULT_REGION_NAME = "AAAAAAAAAA";
    private static final String UPDATED_REGION_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_REGION_CODE = "AAAAAAAAAA";
    private static final String UPDATED_REGION_CODE = "BBBBBBBBBB";

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private RegionService regionService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private Validator validator;

    private MockMvc restRegionMockMvc;

    private Region region;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final RegionResource regionResource = new RegionResource(regionService);
        this.restRegionMockMvc = MockMvcBuilders.standaloneSetup(regionResource)
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
    public static Region createEntity() {
        Region region = new Region()
            .regionName(DEFAULT_REGION_NAME)
            .regionCode(DEFAULT_REGION_CODE);
        return region;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Region createUpdatedEntity() {
        Region region = new Region()
            .regionName(UPDATED_REGION_NAME)
            .regionCode(UPDATED_REGION_CODE);
        return region;
    }

    @BeforeEach
    public void initTest() {
        regionRepository.deleteAll();
        region = createEntity();
    }

    @Test
    public void createRegion() throws Exception {
        int databaseSizeBeforeCreate = regionRepository.findAll().size();

        // Create the Region
        restRegionMockMvc.perform(post("/api/regions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(region)))
            .andExpect(status().isCreated());

        // Validate the Region in the database
        List<Region> regionList = regionRepository.findAll();
        assertThat(regionList).hasSize(databaseSizeBeforeCreate + 1);
        Region testRegion = regionList.get(regionList.size() - 1);
        assertThat(testRegion.getRegionName()).isEqualTo(DEFAULT_REGION_NAME);
        assertThat(testRegion.getRegionCode()).isEqualTo(DEFAULT_REGION_CODE);
    }

    @Test
    public void createRegionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = regionRepository.findAll().size();

        // Create the Region with an existing ID
        region.setId("existing_id");

        // An entity with an existing ID cannot be created, so this API call must fail
        restRegionMockMvc.perform(post("/api/regions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(region)))
            .andExpect(status().isBadRequest());

        // Validate the Region in the database
        List<Region> regionList = regionRepository.findAll();
        assertThat(regionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    public void getAllRegions() throws Exception {
        // Initialize the database
        regionRepository.save(region);

        // Get all the regionList
        restRegionMockMvc.perform(get("/api/regions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(region.getId())))
            .andExpect(jsonPath("$.[*].regionName").value(hasItem(DEFAULT_REGION_NAME.toString())))
            .andExpect(jsonPath("$.[*].regionCode").value(hasItem(DEFAULT_REGION_CODE.toString())));
    }
    
    @Test
    public void getRegion() throws Exception {
        // Initialize the database
        regionRepository.save(region);

        // Get the region
        restRegionMockMvc.perform(get("/api/regions/{id}", region.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(region.getId()))
            .andExpect(jsonPath("$.regionName").value(DEFAULT_REGION_NAME.toString()))
            .andExpect(jsonPath("$.regionCode").value(DEFAULT_REGION_CODE.toString()));
    }

    @Test
    public void getNonExistingRegion() throws Exception {
        // Get the region
        restRegionMockMvc.perform(get("/api/regions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    public void updateRegion() throws Exception {
        // Initialize the database
        regionService.save(region);

        int databaseSizeBeforeUpdate = regionRepository.findAll().size();

        // Update the region
        Region updatedRegion = regionRepository.findById(region.getId()).get();
        updatedRegion
            .regionName(UPDATED_REGION_NAME)
            .regionCode(UPDATED_REGION_CODE);

        restRegionMockMvc.perform(put("/api/regions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedRegion)))
            .andExpect(status().isOk());

        // Validate the Region in the database
        List<Region> regionList = regionRepository.findAll();
        assertThat(regionList).hasSize(databaseSizeBeforeUpdate);
        Region testRegion = regionList.get(regionList.size() - 1);
        assertThat(testRegion.getRegionName()).isEqualTo(UPDATED_REGION_NAME);
        assertThat(testRegion.getRegionCode()).isEqualTo(UPDATED_REGION_CODE);
    }

    @Test
    public void updateNonExistingRegion() throws Exception {
        int databaseSizeBeforeUpdate = regionRepository.findAll().size();

        // Create the Region

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRegionMockMvc.perform(put("/api/regions")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(region)))
            .andExpect(status().isBadRequest());

        // Validate the Region in the database
        List<Region> regionList = regionRepository.findAll();
        assertThat(regionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    public void deleteRegion() throws Exception {
        // Initialize the database
        regionService.save(region);

        int databaseSizeBeforeDelete = regionRepository.findAll().size();

        // Delete the region
        restRegionMockMvc.perform(delete("/api/regions/{id}", region.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database is empty
        List<Region> regionList = regionRepository.findAll();
        assertThat(regionList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Region.class);
        Region region1 = new Region();
        region1.setId("id1");
        Region region2 = new Region();
        region2.setId(region1.getId());
        assertThat(region1).isEqualTo(region2);
        region2.setId("id2");
        assertThat(region1).isNotEqualTo(region2);
        region1.setId(null);
        assertThat(region1).isNotEqualTo(region2);
    }
}
