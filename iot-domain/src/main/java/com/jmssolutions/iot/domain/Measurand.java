package com.jmssolutions.iot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jakub on 03.02.16.
 */
@Entity
@Table(name = "measurand")
public class Measurand {

    @Id
    @Column(name = "measurand_name")
    private String measurandName;

    public Measurand() {
    }

    public String getMeasurandName() {
        return measurandName;
    }

    public void setMeasurandName(String measurandName) {
        this.measurandName = measurandName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Measurand measurand = (Measurand) o;

        return measurandName.equals(measurand.measurandName);

    }

    @Override
    public int hashCode() {
        return measurandName.hashCode();
    }

    @Override
    public String toString() {
        return "Measurand{" +
                "measurandName='" + measurandName + '\'' +
                '}';
    }
}
