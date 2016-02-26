package com.jmssolutions.iot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by jakub on 26.02.16.
 */
@Entity
@Table(name = "sensor_data")
public class SensorData {
    @Id
    @GeneratedValue
    @Column(name = "data_id")
    private long id;

    @JsonIgnore
    @ManyToOne(targetEntity = Sensor.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "sensor_id", nullable = false)
    private Sensor sensor;

    @Column(name = "timestamp")
    private long timestamp;

    @Column(name = "value")
    private double value;


    public SensorData() { }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SensorData that = (SensorData) o;

        if (id != that.id) return false;
        if (timestamp != that.timestamp) return false;
        if (Double.compare(that.value, value) != 0) return false;
        return sensor.equals(that.sensor);

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + sensor.hashCode();
        result = 31 * result + (int) (timestamp ^ (timestamp >>> 32));
        temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "SensorData{" +
                "id=" + id +
                ", sensor id=" + sensor.getID() +
                ", timestamp=" + timestamp +
                ", value=" + value +
                '}';
    }
}
