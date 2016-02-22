package com.jmssolutions.iot.webapp.controllers.api;

import com.jmssolutions.iot.domain.Device;
import com.jmssolutions.iot.domain.User;
import com.jmssolutions.iot.services.DeviceManagerService;
import com.jmssolutions.iot.services.UserService;
import com.jmssolutions.iot.webapp.exceptions.DeviceNotFoundException;
import com.jmssolutions.iot.webapp.exceptions.NoDevicesFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

/**
 * Created by jakub on 16.02.16.
 */
@RestController
@RequestMapping("/api")
public class DeviceSensorApiController {
    private final static Logger logger = Logger.getLogger(DeviceSensorApiController.class);

    @Autowired
    DeviceManagerService deviceManagerService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/device", method = RequestMethod.GET)
    public ResponseEntity<List<Device>> devicesByUser(Principal principal){
        logger.info("Entering /device controller");
        User owner = userService.getUserByUsername(principal.getName());
        List<Device> devices = deviceManagerService.findDevicesByUser(owner);
        if(!devices.isEmpty()){
            return new ResponseEntity<>(devices, HttpStatus.OK);
        }
        else throw new NoDevicesFoundException(owner);
    }

    @RequestMapping(value = "/device/{id}")
    public ResponseEntity<Device> deviceByID(@PathVariable("id") long deviceId, Principal principal){
        logger.info("Entering /device/{id} controller");
        User owner = userService.getUserByUsername(principal.getName());
        Device dev = deviceManagerService.findById(deviceId, owner);
        if(dev != null)
            return new ResponseEntity<>(dev, HttpStatus.OK);
        else throw new DeviceNotFoundException(owner, deviceId);
    }

    @ExceptionHandler(NoDevicesFoundException.class)
    public ResponseEntity<Error> noDevicesFound(NoDevicesFoundException e){
        String username = e.getOwner().getUsername();
        Error error = new Error("User "+username+" has no devices.");
        return new ResponseEntity<>(error, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(DeviceNotFoundException.class)
    public ResponseEntity<Error> deviceNotFound(DeviceNotFoundException e){
        Error error = new Error("Device with id: "+e.getId() + " does not exist");
        return new ResponseEntity<Error>(error, HttpStatus.NO_CONTENT);
    }

}
