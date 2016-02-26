package com.jmssolutions.iot.exceptions;

import com.jmssolutions.iot.domain.Device;

/**
 * Created by jakub on 24.02.16.
 */
public class DeviceException extends DeviceManagerException {
    private Device device;

    public DeviceException(Device device, String message) {
        super(message);
        this.device = device;
    }

    public Device getDevice() {
        return device;
    }
}
