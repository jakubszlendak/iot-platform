package com.jmssolutions.iot.domain;

import java.util.Date;

/**
 * Created by jakub on 27.02.16.
 */
public class SensorDataReport {
    private long entryCount;
    private Date oldestEntryDate;
    private Date newestEntryDate;
    private Sensor sensor;

    public SensorDataReport(long entryCount, Date oldestEntryDate, Date newestEntryDate, Sensor sensor) {
        this.entryCount = entryCount;
        this.oldestEntryDate = oldestEntryDate;
        this.newestEntryDate = newestEntryDate;
        this.sensor = sensor;
    }

    public SensorDataReport() {}

    public long getEntryCount() {
        return entryCount;
    }

    public void setEntryCount(long entryCount) {
        this.entryCount = entryCount;
    }

    public Date getOldestEntryDate() {
        return oldestEntryDate;
    }

    public void setOldestEntryDate(Date oldestEntryDate) {
        this.oldestEntryDate = oldestEntryDate;
    }

    public Date getNewestEntryDate() {
        return newestEntryDate;
    }

    public void setNewestEntryDate(Date newestEntryDate) {
        this.newestEntryDate = newestEntryDate;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
