package com.d2g.crm.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * A Office.
 */
@Document(collection = "office")
public class Office implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("office_city")
    private String officeCity;

    @Field("office_country")
    private String officeCountry;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOfficeCity() {
        return officeCity;
    }

    public Office officeCity(String officeCity) {
        this.officeCity = officeCity;
        return this;
    }

    public void setOfficeCity(String officeCity) {
        this.officeCity = officeCity;
    }

    public String getOfficeCountry() {
        return officeCountry;
    }

    public Office officeCountry(String officeCountry) {
        this.officeCountry = officeCountry;
        return this;
    }

    public void setOfficeCountry(String officeCountry) {
        this.officeCountry = officeCountry;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Office)) {
            return false;
        }
        return id != null && id.equals(((Office) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Office{" +
            "id=" + getId() +
            ", officeCity='" + getOfficeCity() + "'" +
            ", officeCountry='" + getOfficeCountry() + "'" +
            "}";
    }
}
