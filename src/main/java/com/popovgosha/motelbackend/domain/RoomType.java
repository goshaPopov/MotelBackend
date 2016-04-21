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
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Georgiy Popov on 20.04.2016.
 */
@Entity
@Table(name = "room_type")
public class RoomType implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 15)
    private String name;

    @Column(name = "description", length = 140)
    private String description;

    @Column(name = "place_quantity", nullable = false)
    private Short placeQuantity;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "price_by1", nullable = false)
    private BigDecimal priceByOne;

    @JsonIgnore
    @OneToMany(mappedBy = "roomType",fetch = FetchType.LAZY)
    private List<Room> rooms;

    public RoomType() {
    }

    public RoomType(String name, String description, Short placeQuantity,
                    BigDecimal price, BigDecimal priceByOne) {
        this.name = name;
        this.description = description;
        this.placeQuantity = placeQuantity;
        this.price = price;
        this.priceByOne = priceByOne;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Short getPlaceQuantity() {
        return placeQuantity;
    }

    public void setPlaceQuantity(Short placeQuantity) {
        this.placeQuantity = placeQuantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPriceByOne() {
        return priceByOne;
    }

    public void setPriceByOne(BigDecimal priceByOne) {
        this.priceByOne = priceByOne;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof RoomType)) return false;

        RoomType roomType = (RoomType) o;

        return new EqualsBuilder()
                .append(id, roomType.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }
}
