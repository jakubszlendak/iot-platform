package com.jmssolutions.iot.webapp.exceptions;

import com.jmssolutions.iot.domain.Device;
import com.jmssolutions.iot.domain.Sensor;

/**
 * Created by jakub on 26.02.16.
 */
public class InvalidSensorDataException extends IllegalArgumentException {
    private Sensor sensor;
    public InvalidSensorDataException(Sensor sensor) {
        super();
    }

    public Sensor getSensor() {
        return sensor;
    }
}
