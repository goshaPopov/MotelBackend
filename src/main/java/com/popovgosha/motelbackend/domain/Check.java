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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Georgiy Popov on 21.04.2016.
 */
@Entity
@Table(name = "check")
public class Check implements Serializable {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "issued_l", length = 1)
    private Boolean issuedB;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "issued")
    private Date issuedDate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_room_acc")
    private RoomAccounting roomAcc;

    public Check() {
    }

    public Check(Boolean issuedB, Date issuedDate) {
        this.issuedB = issuedB;
        this.issuedDate = issuedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIssuedB() {
        return issuedB;
    }

    public void setIssuedB(Boolean issuedB) {
        this.issuedB = issuedB;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public RoomAccounting getRoomAcc() {
        return roomAcc;
    }

    public void setRoomAcc(RoomAccounting roomAcc) {
        this.roomAcc = roomAcc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Check)) return false;

        Check check = (Check) o;

        return new EqualsBuilder()
                .append(id, check.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }
}
