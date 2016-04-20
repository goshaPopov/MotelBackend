package com.popovgosha.motelbackend.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Georgiy Popov on 20.04.2016.
 */
@Entity
@Table(name="guest")
public class Guest implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "second_name", nullable = false, length = 50)
    private String secondName;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "patronymic", nullable = false, length = 50)
    private String patronymic;

    @Temporal(TemporalType.DATE)
    @Column(name = "birth_day")
    private Date birthDay;

    @Column(name = "citizenship", nullable = false, length = 100)
    private String citizenship;

    @Column(name = "country_residence", nullable = false, length = 100)
    private String countryResidence;

    @Column(name = "state_residence", nullable = false, length = 100)
    private String stateResidence;

    @Column(name = "city_residence", nullable = false, length = 100)
    private String cityResidence;

    @Column(name = "street_residence", nullable = false, length = 50)
    private String streetResidence;

    @Column(name = "building_residence", nullable = false, length = 50)
    private String buildingResidence;

    @Column(name = "appartament_residence", nullable = false, length = 10)
    private String appartamentResidence;

    @Column(name = "country_birth", nullable = false, length = 100)
    private String countryBirth;

    @Column(name = "state_birth", nullable = false, length = 100)
    private String stateBirth;

    @Column(name = "city_birth", nullable = false, length = 100)
    private String cityBirth;

    @Column(name = "passport_series_number", nullable = false, length = 20, unique = true)
    private String passportData;

    @Temporal(TemporalType.DATE)
    @Column(name = "passport_date", nullable = false)
    private Date passportDate;

    @Column(name = "passport_authority", nullable = false, length = 100)
    private String passportAuthority;

    public Guest() {
    }

    public Guest(String secondName, String firstName, String patronymic,
                 Date birthDay, String citizenship, String countryResidence,
                 String stateResidence, String cityResidence, String streetResidence,
                 String buildingResidence, String appartamentResidence, String countryBirth,
                 String stateBirth, String cityBirth, String passportData, Date passportDate,
                 String passportAuthority) {
        this.secondName = secondName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.birthDay = birthDay;
        this.citizenship = citizenship;
        this.countryResidence = countryResidence;
        this.stateResidence = stateResidence;
        this.cityResidence = cityResidence;
        this.streetResidence = streetResidence;
        this.buildingResidence = buildingResidence;
        this.appartamentResidence = appartamentResidence;
        this.countryBirth = countryBirth;
        this.stateBirth = stateBirth;
        this.cityBirth = cityBirth;
        this.passportData = passportData;
        this.passportDate = passportDate;
        this.passportAuthority = passportAuthority;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getCountryResidence() {
        return countryResidence;
    }

    public void setCountryResidence(String countryResidence) {
        this.countryResidence = countryResidence;
    }

    public String getStateResidence() {
        return stateResidence;
    }

    public void setStateResidence(String stateResidence) {
        this.stateResidence = stateResidence;
    }

    public String getCityResidence() {
        return cityResidence;
    }

    public void setCityResidence(String cityResidence) {
        this.cityResidence = cityResidence;
    }

    public String getStreetResidence() {
        return streetResidence;
    }

    public void setStreetResidence(String streetResidence) {
        this.streetResidence = streetResidence;
    }

    public String getBuildingResidence() {
        return buildingResidence;
    }

    public void setBuildingResidence(String buildingResidence) {
        this.buildingResidence = buildingResidence;
    }

    public String getAppartamentResidence() {
        return appartamentResidence;
    }

    public void setAppartamentResidence(String appartamentResidence) {
        this.appartamentResidence = appartamentResidence;
    }

    public String getCountryBirth() {
        return countryBirth;
    }

    public void setCountryBirth(String countryBirth) {
        this.countryBirth = countryBirth;
    }

    public String getStateBirth() {
        return stateBirth;
    }

    public void setStateBirth(String stateBirth) {
        this.stateBirth = stateBirth;
    }

    public String getCityBirth() {
        return cityBirth;
    }

    public void setCityBirth(String cityBirth) {
        this.cityBirth = cityBirth;
    }

    public String getPassportData() {
        return passportData;
    }

    public void setPassportData(String passportData) {
        this.passportData = passportData;
    }

    public Date getPassportDate() {
        return passportDate;
    }

    public void setPassportDate(Date passportDate) {
        this.passportDate = passportDate;
    }

    public String getPassportAuthority() {
        return passportAuthority;
    }

    public void setPassportAuthority(String passportAuthority) {
        this.passportAuthority = passportAuthority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Guest)) return false;

        Guest guest = (Guest) o;

        return new EqualsBuilder()
                .append(id, guest.id)
                .append(passportData, guest.passportData)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(passportData)
                .toHashCode();
    }
}