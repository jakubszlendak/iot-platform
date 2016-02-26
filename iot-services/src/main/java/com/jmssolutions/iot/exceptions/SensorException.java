package com.jmssolutions.iot.exceptions;

import com.jmssolutions.iot.domain.Sensor;

/**
 * Created by jakub on 26.02.16.
 */
public class SensorException extends DeviceManagerException {
    private Sensor sensor;
    public SensorException(Sensor sensor, String message) {

        super(message);
        this.sensor = sensor;
    }

    public Sensor getSensor() {
        return sensor;
    }
}
