package com.jmssolutions.iot.webapp.exceptions;

import com.jmssolutions.iot.domain.User;

/**
 * Created by jakub on 18.02.16.
 */
public class NoDevicesFoundException extends RuntimeException {
    private User owner;

    public NoDevicesFoundException(User owner) {
        this.owner = owner;
    }

    public User getOwner() {
        return owner;
    }
}
