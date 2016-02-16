package com.jmssolutions.iot.services;

import com.jmssolutions.iot.domain.Device;
import com.jmssolutions.iot.domain.Sensor;
import com.jmssolutions.iot.domain.User;

import java.util.List;

/**
 * Created by jakub on 15.02.16.
 */
public interface DeviceManagerService {

    List<Device> findAllDevices();
    List<Device> findDevicesByUser(User owner);
    List<Sensor> findSensorByDevice(Device device);
    Device createDeviceWithSensors(Device device, List<Sensor> sensors);
    void addSensorToDevice(Device device, Sensor sensor);

}
