package com.jmssolutions.iot.services;

import com.jmssolutions.iot.dao.DeviceDAO;
import com.jmssolutions.iot.dao.SensorDAO;
import com.jmssolutions.iot.domain.Device;
import com.jmssolutions.iot.domain.Sensor;
import com.jmssolutions.iot.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jakub on 16.02.16.
 */
@Service
public class DeviceManagerServiceImpl implements DeviceManagerService {

    @Autowired
    DeviceDAO deviceDAO;

    @Autowired
    SensorDAO sensorDAO;


    @Override
    public List<Device> findAllDevices() {
        return deviceDAO.findAll();
    }

    @Override
    public List<Device> findDevicesByUser(User owner) {

        return deviceDAO.findByOwner(owner);
    }

    @Override
    public List<Sensor> findSensorsByDevice(Device device) {
        return sensorDAO.findByDevice(device);
    }

    @Override
    public Device createDeviceWithSensors(Device device, List<Sensor> sensors) {
        return null;
    }

    @Override
    public void addSensorToDevice(Device device, Sensor sensor) {

    }
}
