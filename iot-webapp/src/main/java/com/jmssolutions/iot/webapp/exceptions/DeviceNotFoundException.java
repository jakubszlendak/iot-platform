package com.jmssolutions.iot.webapp.exceptions;

import com.jmssolutions.iot.domain.User;

/**
 * Created by jakub on 22.02.16.
 */
public class DeviceNotFoundException extends RuntimeException {
    private User owner;
    private long id;

    public DeviceNotFoundException(User owner, long id) {
        this.owner = owner;
        this.id = id;
    }

    public DeviceNotFoundException(long id) {
        this.id = id;
        this.owner = null;
    }

    public User getOwner() {
        return owner;
    }

    public long getId() {
        return id;
    }
}
