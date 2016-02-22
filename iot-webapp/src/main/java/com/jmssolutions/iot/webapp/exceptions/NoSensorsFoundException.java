package com.jmssolutions.iot.webapp.exceptions;

import com.jmssolutions.iot.domain.Device;

/**
 * Created by jakub on 22.02.16.
 */
public class NoSensorsFoundException extends RuntimeException {
    private Device device;

    public NoSensorsFoundException(Device dev) {
        this.device = dev;
    }

    public Device getDevice() {
        return device;
    }
}
