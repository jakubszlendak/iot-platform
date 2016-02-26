package com.jmssolutions.iot.exceptions;

/**
 * Created by jakub on 24.02.16.
 */
public abstract class DeviceManagerException extends RuntimeException {
    public DeviceManagerException(String message) {
        super(message);
    }
}
