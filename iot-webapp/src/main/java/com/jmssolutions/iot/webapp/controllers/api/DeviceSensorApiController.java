package com.jmssolutions.iot.webapp.controllers.api;

import com.jmssolutions.iot.domain.Device;
import com.jmssolutions.iot.domain.Sensor;
import com.jmssolutions.iot.domain.User;
import com.jmssolutions.iot.exceptions.DeviceManagerException;
import com.jmssolutions.iot.services.DeviceManagerService;
import com.jmssolutions.iot.services.UserService;
import com.jmssolutions.iot.webapp.exceptions.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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

    @RequestMapping(value = "/device/{id}", method = RequestMethod.GET)
    public ResponseEntity<Device> deviceByID(@PathVariable("id") long deviceId, Principal principal){
        logger.info("Entering /device/{id} controller");
        User owner = userService.getUserByUsername(principal.getName());
        Device dev = deviceManagerService.findDeviceById(deviceId, owner);
        if(dev != null)
            return new ResponseEntity<>(dev, HttpStatus.OK);
        else throw new DeviceNotFoundException(owner, deviceId);
    }

    @RequestMapping(value = "/device/{device_id}/sensor", method = RequestMethod.GET)
    public ResponseEntity<List<Sensor>> sensorByDevice(@PathVariable("device_id") long deviceId, Principal principal){
        User owner = userService.getUserByUsername(principal.getName());
        Device dev = deviceManagerService.findDeviceById(deviceId, owner);
        if(dev != null){
            List<Sensor> sensors = deviceManagerService.findSensorsByDevice(dev);
            if(!sensors.isEmpty()){
                return new ResponseEntity<List<Sensor>>(sensors, HttpStatus.OK);
            }
            else throw new NoSensorsFoundException(dev);
        }
        else throw new DeviceNotFoundException(owner, deviceId);
    }

    @RequestMapping(value = "/device/{device_id}/sensor/{sensor_id}", method = RequestMethod.GET)
    public ResponseEntity<Sensor> sensorById(@PathVariable("device_id") long deviceId, @PathVariable("sensor_id") long sensorId, Principal principal){
        User owner = userService.getUserByUsername(principal.getName());
        Device dev = deviceManagerService.findDeviceById(deviceId, owner);
        if(dev != null){
            Sensor sensor = deviceManagerService.findSensorById(sensorId, dev);
            if(sensor != null)
                return new ResponseEntity<Sensor>(sensor, HttpStatus.OK);
            else throw new SensorNotFoundException(sensorId, dev);
        }
        else throw new DeviceNotFoundException(owner, deviceId);
    }

    @RequestMapping(value = "/device", method = RequestMethod.POST)
    public ResponseEntity<Device> createDevice(@RequestBody Device device, UriComponentsBuilder uriComponentsBuilder){
        Device savedDevice = null;
        try {
            savedDevice = deviceManagerService.createDevice(device);
        } catch (DeviceManagerException e) {
            throw new InvalidDeviceDataException(device);
        }
        HttpHeaders headers = new HttpHeaders();
        URI location = uriComponentsBuilder.path("/device/").path(String.valueOf(savedDevice.getID())).build().toUri();
        headers.setLocation(location);
        return new ResponseEntity<Device>(savedDevice, headers, HttpStatus.CREATED);
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

    @ExceptionHandler(NoSensorsFoundException.class)
    public ResponseEntity<Error> noSensorsFound(NoSensorsFoundException e){
        Error error = new Error("Device of id " + e.getDevice().getID() + "has no sensor. We also found it strange.");
        return new ResponseEntity<Error>(error, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(SensorNotFoundException.class)
    public ResponseEntity<Error> sensorNotFound(SensorNotFoundException e){
        Error error = new Error("Device with id: "+e.getSensorId() + " does not exist");
        return new ResponseEntity<Error>(error, HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(InvalidDeviceDataException.class)
    public ResponseEntity<Error> invalidDevice(InvalidDeviceDataException e){
        Error error = new Error("Device with uuid: "+e.getDevice().getUuid() + " already exists");
        return new ResponseEntity<Error>(error, HttpStatus.CONFLICT);
    }

}
