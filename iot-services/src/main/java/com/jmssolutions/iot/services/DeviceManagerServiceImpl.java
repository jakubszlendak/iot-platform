package com.jmssolutions.iot.services;

import com.jmssolutions.iot.dao.DeviceDAO;
import com.jmssolutions.iot.dao.SensorDAO;
import com.jmssolutions.iot.domain.Device;
import com.jmssolutions.iot.domain.Sensor;
import com.jmssolutions.iot.domain.User;
import com.jmssolutions.iot.exceptions.DeviceException;
import com.jmssolutions.iot.exceptions.SensorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
    public Device findDeviceById(long id, User owner) {
        Device dev = deviceDAO.findById(id);
        if(dev == null)
            return null;
        List<Device> ownerDevices = deviceDAO.findByOwner(owner);
        if(ownerDevices.contains(dev))
            return dev;
        else return null;
    }

    @Override
    public Sensor findSensorById(long sensorId, Device dev) {
        Sensor sen = sensorDAO.findById(sensorId);
        if(sen == null)
            return null;
        List<Sensor> deviceSensors = sensorDAO.findByDevice(dev);
        if(deviceSensors.contains(sen))
            return sen;
        else return null;
    }

    @Override
    public List<Sensor> findSensorsByDevice(Device device) {
        return sensorDAO.findByDevice(device);
    }

    @Override
    public Device createDevice(Device device) {
        Device dev = null;
        try{
            dev = deviceDAO.create(device);
        } catch (DataIntegrityViolationException e){
            throw new DeviceException(device, e.getMessage());
        }
        return dev;
    }

    @Override
    public Sensor addSensorToDevice(Device device, Sensor sensor) {
        Sensor s = null;
        try{
            s = sensorDAO.create(sensor);
        } catch (DataIntegrityViolationException e){
            throw new SensorException(s, e.getMessage());
        }
        return s;
    }

    @Override
    public void removeDevice(Device device) {

        deviceDAO.remove(device.getID());
    }
}
