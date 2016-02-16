package com.jmssolutions.iot.domain;

import javax.persistence.*;

/**
 * Created by jakub on 03.02.16.
 */
@Entity
@Table(name = "sensor")
public class Sensor {
    @Id
    @GeneratedValue
    @Column(name = "sensor_id")
    private long ID;

    @ManyToOne(targetEntity = Device.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "device_id", nullable = false)
    private DeviceClass owningDevice;

    @Column(name = "measurand_unit")
    private String unit;

    @OneToOne(targetEntity = Measurand.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "measurand_name", nullable = false)
    private Measurand measurand;

    public Sensor() {
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public DeviceClass getOwningDevice() {
        return owningDevice;
    }

    public void setOwningDevice(DeviceClass owningDevice) {
        this.owningDevice = owningDevice;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Measurand getMeasurand() {
        return measurand;
    }

    public void setMeasurand(Measurand measurand) {
        this.measurand = measurand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sensor sensor = (Sensor) o;

        if (ID != sensor.ID) return false;
        if (owningDevice != null ? !owningDevice.equals(sensor.owningDevice) : sensor.owningDevice != null)
            return false;
        if (unit != null ? !unit.equals(sensor.unit) : sensor.unit != null) return false;
        return !(measurand != null ? !measurand.equals(sensor.measurand) : sensor.measurand != null);

    }

    @Override
    public int hashCode() {
        int result = (int) (ID ^ (ID >>> 32));
        result = 31 * result + (owningDevice != null ? owningDevice.hashCode() : 0);
        result = 31 * result + (unit != null ? unit.hashCode() : 0);
        result = 31 * result + (measurand != null ? measurand.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "ID=" + ID +
                ", owningDevice=" + owningDevice +
                ", unit='" + unit + '\'' +
                ", measurand=" + measurand +
                '}';
    }
}
