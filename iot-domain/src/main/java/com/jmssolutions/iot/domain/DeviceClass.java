package com.jmssolutions.iot.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by jakub on 01.02.16.
 */
@Entity
@Table(name = "device_class")
public class DeviceClass {
    @Id
    @Column(name = "class_name")
    private String className;

    public DeviceClass() {
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeviceClass that = (DeviceClass) o;

        return className.equals(that.className);

    }

    @Override
    public int hashCode() {
        return className.hashCode();
    }
}
