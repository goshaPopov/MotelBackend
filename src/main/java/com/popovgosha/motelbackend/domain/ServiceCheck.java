package com.popovgosha.motelbackend.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by Georgiy Popov on 21.04.2016.
 */
@Entity
@Table(name = "service_check")
public class ServiceCheck implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_service")
    private Service service;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mark")
    private Long idMark;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_check")
    private Check check;

    public ServiceCheck() {
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Long getIdMark() {
        return idMark;
    }

    public void setIdMark(Long idMark) {
        this.idMark = idMark;
    }

    public Check getCheck() {
        return check;
    }

    public void setCheck(Check check) {
        this.check = check;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof ServiceCheck)) return false;

        ServiceCheck that = (ServiceCheck) o;

        return new EqualsBuilder()
                .append(getService(), that.getService())
                .append(getIdMark(), that.getIdMark())
                .append(getCheck(), that.getCheck())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getService())
                .append(getIdMark())
                .append(getCheck())
                .toHashCode();
    }
}
