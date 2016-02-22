package com.jmssolutions.iot.webapp.exceptions;

import com.jmssolutions.iot.domain.Device;

/**
 * Created by jakub on 22.02.16.
 */
public class SensorNotFoundException extends RuntimeException {
    private long sensorId;
    private Device owningDevice;

    public SensorNotFoundException(long sensorId, Device dev) {
        this.sensorId = sensorId;
        this.owningDevice = dev;
    }

    public long getSensorId() {
        return sensorId;
    }

    public Device getOwningDevice() {
        return owningDevice;
    }
}
