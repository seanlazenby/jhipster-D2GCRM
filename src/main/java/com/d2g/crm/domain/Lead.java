package com.d2g.crm.domain;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * A Lead.
 */
@Document(collection = "lead")
public class Lead implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("lead_name")
    private String leadName;

    @Field("lead_phone")
    private String leadPhone;

    @Field("leadid")
    private String leadid;

    @Field("title")
    private String title;

    @Field("status")
    private String status;

    @Field("active")
    private String active;

    @Field("hotel")
    private String hotel;

    @Field("rooms")
    private String rooms;

    @Field("contact")
    private String contact;

    @Field("officetel")
    private String officetel;

    @Field("cell")
    private String cell;

    @Field("street_1")
    private String street1;

    @Field("street_2")
    private String street2;

    @Field("city")
    private String city;

    @Field("state")
    private String state;

    @Field("postalcode")
    private String postalcode;

    @Field("country")
    private String country;

    @Field("website")
    private String website;

    @Field("email")
    private String email;

    @Field("followup")
    private String followup;

    @Field("comment")
    private String comment;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLeadName() {
        return leadName;
    }

    public Lead leadName(String leadName) {
        this.leadName = leadName;
        return this;
    }

    public void setLeadName(String leadName) {
        this.leadName = leadName;
    }

    public String getLeadPhone() {
        return leadPhone;
    }

    public Lead leadPhone(String leadPhone) {
        this.leadPhone = leadPhone;
        return this;
    }

    public void setLeadPhone(String leadPhone) {
        this.leadPhone = leadPhone;
    }

    public String getLeadid() {
        return leadid;
    }

    public Lead leadid(String leadid) {
        this.leadid = leadid;
        return this;
    }

    public void setLeadid(String leadid) {
        this.leadid = leadid;
    }

    public String getTitle() {
        return title;
    }

    public Lead title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public Lead status(String status) {
        this.status = status;
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActive() {
        return active;
    }

    public Lead active(String active) {
        this.active = active;
        return this;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getHotel() {
        return hotel;
    }

    public Lead hotel(String hotel) {
        this.hotel = hotel;
        return this;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getRooms() {
        return rooms;
    }

    public Lead rooms(String rooms) {
        this.rooms = rooms;
        return this;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public String getContact() {
        return contact;
    }

    public Lead contact(String contact) {
        this.contact = contact;
        return this;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getOfficetel() {
        return officetel;
    }

    public Lead officetel(String officetel) {
        this.officetel = officetel;
        return this;
    }

    public void setOfficetel(String officetel) {
        this.officetel = officetel;
    }

    public String getCell() {
        return cell;
    }

    public Lead cell(String cell) {
        this.cell = cell;
        return this;
    }

    public void setCell(String cell) {
        this.cell = cell;
    }

    public String getStreet1() {
        return street1;
    }

    public Lead street1(String street1) {
        this.street1 = street1;
        return this;
    }

    public void setStreet1(String street1) {
        this.street1 = street1;
    }

    public String getStreet2() {
        return street2;
    }

    public Lead street2(String street2) {
        this.street2 = street2;
        return this;
    }

    public void setStreet2(String street2) {
        this.street2 = street2;
    }

    public String getCity() {
        return city;
    }

    public Lead city(String city) {
        this.city = city;
        return this;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public Lead state(String state) {
        this.state = state;
        return this;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public Lead postalcode(String postalcode) {
        this.postalcode = postalcode;
        return this;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getCountry() {
        return country;
    }

    public Lead country(String country) {
        this.country = country;
        return this;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getWebsite() {
        return website;
    }

    public Lead website(String website) {
        this.website = website;
        return this;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public Lead email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFollowup() {
        return followup;
    }

    public Lead followup(String followup) {
        this.followup = followup;
        return this;
    }

    public void setFollowup(String followup) {
        this.followup = followup;
    }

    public String getComment() {
        return comment;
    }

    public Lead comment(String comment) {
        this.comment = comment;
        return this;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Lead)) {
            return false;
        }
        return id != null && id.equals(((Lead) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Lead{" +
            "id=" + getId() +
            ", leadName='" + getLeadName() + "'" +
            ", leadPhone='" + getLeadPhone() + "'" +
            ", leadid='" + getLeadid() + "'" +
            ", title='" + getTitle() + "'" +
            ", status='" + getStatus() + "'" +
            ", active='" + getActive() + "'" +
            ", hotel='" + getHotel() + "'" +
            ", rooms='" + getRooms() + "'" +
            ", contact='" + getContact() + "'" +
            ", officetel='" + getOfficetel() + "'" +
            ", cell='" + getCell() + "'" +
            ", street1='" + getStreet1() + "'" +
            ", street2='" + getStreet2() + "'" +
            ", city='" + getCity() + "'" +
            ", state='" + getState() + "'" +
            ", postalcode='" + getPostalcode() + "'" +
            ", country='" + getCountry() + "'" +
            ", website='" + getWebsite() + "'" +
            ", email='" + getEmail() + "'" +
            ", followup='" + getFollowup() + "'" +
            ", comment='" + getComment() + "'" +
            "}";
    }
}
