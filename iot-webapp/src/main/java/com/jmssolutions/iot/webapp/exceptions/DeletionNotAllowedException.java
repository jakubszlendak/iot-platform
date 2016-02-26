package com.jmssolutions.iot.webapp.exceptions;

/**
 * Created by jakub on 26.02.16.
 */
public class DeletionNotAllowedException extends RuntimeException {
    public DeletionNotAllowedException(String message) {
        super(message);
    }
}
