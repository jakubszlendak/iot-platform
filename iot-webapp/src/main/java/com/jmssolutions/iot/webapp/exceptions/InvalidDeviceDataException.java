package com.jmssolutions.iot.webapp.exceptions;

import com.jmssolutions.iot.domain.Device;

/**
 * Created by jakub on 24.02.16.
 */
public class InvalidDeviceDataException extends IllegalArgumentException{
    private Device device;

    public InvalidDeviceDataException(Device device) {
        this.device = device;
    }

    public Device getDevice() {
        return device;
    }
}
