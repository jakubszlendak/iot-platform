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
        User owner = loadPrincipal(principal);
        List<Device> devices = deviceManagerService.findDevicesByUser(owner);
        if(!devices.isEmpty()){
            return new ResponseEntity<>(devices, HttpStatus.OK);
        }
        else throw new NoDevicesFoundException(owner);
    }

    @RequestMapping(value = "/device/{id}", method = RequestMethod.GET)
    public ResponseEntity<Device> deviceByID(@PathVariable("id") long deviceId, Principal principal){
        logger.info("Entering /device/{id} controller");
        User owner = loadPrincipal(principal);
        Device dev = deviceManagerService.findDeviceById(deviceId, owner);
        if(dev != null)
            return new ResponseEntity<>(dev, HttpStatus.OK);
        else throw new DeviceNotFoundException(owner, deviceId);
    }

    @RequestMapping(value = "/device/{device_id}/sensor", method = RequestMethod.GET)
    public ResponseEntity<List<Sensor>> sensorByDevice(@PathVariable("device_id") long deviceId, Principal principal){
        User owner = loadPrincipal(principal);
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
        User owner = loadPrincipal(principal);
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
    public ResponseEntity<Device> createDevice(@RequestBody Device device, UriComponentsBuilder uriComponentsBuilder, Principal principal){
        Device savedDevice = null;
        User owner = loadPrincipal(principal);
        device.setOwner(owner);
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

    @RequestMapping(value = "/device/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Device> deleteDevice(@PathVariable("id") long deviceId, Principal principal){
        User owner = loadPrincipal(principal);
        Device device = deviceManagerService.findDeviceById(deviceId, owner);
        if(device == null)
            throw new DeviceNotFoundException(owner, deviceId);

        if(device.getSensors().isEmpty()){
            deviceManagerService.removeDevice(device);
        }
        else throw new DeletionNotAllowedException("Device has sensors. Deletion not allowed.");

        return new ResponseEntity<Device>(device, HttpStatus.OK);
    }

    @RequestMapping(value = "/device/{device_id}/sensor", method = RequestMethod.POST)
    public ResponseEntity<Sensor> createSensor(@PathVariable("device_id") long deviceId, @RequestBody Sensor sensor, UriComponentsBuilder uriComponentsBuilder, Principal principal){
        User owner = loadPrincipal(principal);
        Device owningDevice = deviceManagerService.findDeviceById(deviceId, owner);
        if(owningDevice == null) throw new DeviceNotFoundException(owner, deviceId);

        sensor.setOwningDevice(owningDevice);
        try {
            sensor = deviceManagerService.addSensorToDevice(owningDevice, sensor);
        } catch (DeviceManagerException e) {
            throw new InvalidSensorDataException(sensor);
        }
        HttpHeaders headers = new HttpHeaders();
        URI location = uriComponentsBuilder.path("/device/").path(String.valueOf(sensor.getID())).build().toUri();
        headers.setLocation(location);
        return new ResponseEntity<Sensor>(sensor, headers, HttpStatus.CREATED);
    }





    @ExceptionHandler(NoDevicesFoundException.class)
    public ResponseEntity<String> noDevicesFound(NoDevicesFoundException e){
        String username = e.getOwner().getUsername();
        Error error = new Error("User "+username+" has no devices.");
        return new ResponseEntity<>(error.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DeviceNotFoundException.class)
    public ResponseEntity<String> deviceNotFound(DeviceNotFoundException e){
        Error error = new Error("Device with id: "+e.getId() + " does not exist");
        return new ResponseEntity<String>(error.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSensorsFoundException.class)
    public ResponseEntity<String> noSensorsFound(NoSensorsFoundException e){
        Error error = new Error("Device of id " + e.getDevice().getID() + "has no sensor. We also found it strange.");
        return new ResponseEntity<String>(error.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SensorNotFoundException.class)
    public ResponseEntity<String> sensorNotFound(SensorNotFoundException e){
        Error error = new Error("Device with id: "+e.getSensorId() + " does not exist");
        return new ResponseEntity<String>(error.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidDeviceDataException.class)
    public ResponseEntity<String> invalidDevice(InvalidDeviceDataException e){
        Error error = new Error("Device with uuid: "+e.getDevice().getUuid() + " already exists");
        return new ResponseEntity<String>(error.getMessage(), HttpStatus.CONFLICT);
    }

//    @ExceptionHandler(InvalidSensorDataException.class)
//    public ResponseEntity<String> invalidSensor(InvalidSensorDataException e){
//        Error error = new Error("Device with uuid: "+e.getSensor().getUuid() + " already exists");
//        return new ResponseEntity<String>(error.getMessage(), HttpStatus.CONFLICT);
//    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> runtimeError(RuntimeException e){
        Error error = new Error("Runtime error: "+ e.getMessage());
        return new ResponseEntity<String>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DeletionNotAllowedException.class)
    public ResponseEntity<String> deletionNotAllowed(DeletionNotAllowedException e){
        return new ResponseEntity<String>(e.getMessage(), HttpStatus.FORBIDDEN);
    }

    private User loadPrincipal(Principal principal){
        User user = userService.getUserByUsername(principal.getName());
        if(user == null)
            throw new RuntimeException("Principal user not found. This is internal error. Call 911.");
        else return user;
    }
}
