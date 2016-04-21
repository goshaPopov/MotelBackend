package com.popovgosha.motelbackend.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Georgiy Popov on 20.04.2016.
 */
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "second_name", nullable = false, length = 45)
    private String secondName;

    @Column(name = "first_name", nullable = false, length = 45)
    private String firstName;

    @Column(name = "patronymic", nullable = false, length = 45)
    private String patronymic;

    @Column(name = "position", nullable = false, length = 45)
    private String position;

    @Column(name = "phone", nullable = false, length = 15, unique = true)
    private String phone;

    @Column(name = "password", nullable = false, length = 20)
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Service> services;

    @JsonIgnore
    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<RoomAccounting> roomAccountings;

    public Employee() {
    }

    public Employee(String secondName, String firstName, String patronymic,
                    String position, String phone, String password) {
        this.secondName = secondName;
        this.firstName = firstName;
        this.patronymic = patronymic;
        this.position = position;
        this.phone = phone;
        this.password = password;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public List<RoomAccounting> getRoomAccountings() {
        return roomAccountings;
    }

    public void setRoomAccountings(List<RoomAccounting> roomAccountings) {
        this.roomAccountings = roomAccountings;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Employee)) return false;

        Employee employee = (Employee) o;

        return new EqualsBuilder()
                .append(id, employee.id)
                .append(phone, employee.phone)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(phone)
                .toHashCode();
    }
}

