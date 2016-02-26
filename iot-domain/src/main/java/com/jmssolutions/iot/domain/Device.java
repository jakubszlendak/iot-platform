package com.jmssolutions.iot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by jakub on 01.02.16.
 */
@Entity
@Table(name = "device")
public class Device {
    @Id
    @GeneratedValue
    @Column(name = "device_id")
    private long ID;

    @OneToOne(targetEntity = DeviceClass.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "class_name", nullable = false)
    private DeviceClass deviceClass;

    @Column(name = "name")
    private String name;

    @Column(name = "producer_name")
    private String producerName;

    @Column(name = "aes_key")
    private String aesKey;

    @Column(name = "device_uuid")
    private String uuid;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    @JsonIgnore
    @OneToMany(mappedBy = "owningDevice", fetch = FetchType.EAGER)
    private Collection<Sensor> sensors;

    public Device() {}

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public DeviceClass getDeviceClass() {
        return deviceClass;
    }

    public void setDeviceClass(DeviceClass deviceClass) {
        this.deviceClass = deviceClass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducerName() {
        return producerName;
    }

    public void setProducerName(String producerName) {
        this.producerName = producerName;
    }

    public String getAesKey() {
        return aesKey;
    }

    public void setAesKey(String aesKey) {
        this.aesKey = aesKey;
    }


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Collection<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(Collection<Sensor> sensors) {
        this.sensors = sensors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Device device = (Device) o;

        if (ID != device.ID) return false;
        if (!deviceClass.equals(device.deviceClass)) return false;
        if (!name.equals(device.name)) return false;
        if (!producerName.equals(device.producerName)) return false;
        if (!aesKey.equals(device.aesKey)) return false;
        return owner.equals(device.owner);

    }

    @Override
    public int hashCode() {
        int result = (int) (ID ^ (ID >>> 32));
        result = 31 * result + deviceClass.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + producerName.hashCode();
        result = 31 * result + aesKey.hashCode();
        result = 31 * result + owner.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Device{" +
                "ID=" + ID +
                ", deviceClass=" + deviceClass +
                ", name='" + name + '\'' +
                ", producerName='" + producerName + '\'' +
                ", aesKey='" + aesKey + '\'' +
                ", owner=" + owner +
                '}';
    }
}
