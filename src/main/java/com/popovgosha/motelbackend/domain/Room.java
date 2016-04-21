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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Georgiy Popov on 20.04.2016.
 */
@Entity
@Table(name = "room")
public class Room implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "number", unique = true)
    private Integer number;

    @Column(name = "floor", nullable = false)
    private Short floor;

    @Column(name = "view", length = 45)
    private String view;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_room_type", nullable = false)
    private RoomType roomType;

    @JsonIgnore
    @OneToMany( mappedBy = "room", fetch = FetchType.LAZY)
    private List<RoomAccounting> roomAccountings;

    public Room() {
    }

    public Room(Integer number, Short floor, String view, RoomType roomType) {
        this.number = number;
        this.floor = floor;
        this.view = view;
        this.roomType = roomType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Short getFloor() {
        return floor;
    }

    public void setFloor(Short floor) {
        this.floor = floor;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
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

        if (!(o instanceof Room)) return false;

        Room room = (Room) o;

        return new EqualsBuilder()
                .append(id, room.id)
                .append(number, room.number)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(number)
                .toHashCode();
    }
}
