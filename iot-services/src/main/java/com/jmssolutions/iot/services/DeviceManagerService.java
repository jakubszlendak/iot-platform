package com.jmssolutions.iot.services;

import com.jmssolutions.iot.domain.Device;
import com.jmssolutions.iot.domain.Sensor;
import com.jmssolutions.iot.domain.User;
import com.jmssolutions.iot.exceptions.DeviceManagerException;

import java.util.List;

/**
 * Created by jakub on 15.02.16.
 */
public interface DeviceManagerService {

    List<Device> findAllDevices();

    List<Device> findDevicesByUser(User owner);

    Device findDeviceById(long id, User owner);

    Sensor findSensorById(long sensorId, Device dev);

    List<Sensor> findSensorsByDevice(Device device);

    //    Device createDeviceWithSensors(Device device, List<Sensor> sensors);
    Device createDevice(Device device) throws DeviceManagerException;

    void addSensorToDevice(Device device, Sensor sensor);


}