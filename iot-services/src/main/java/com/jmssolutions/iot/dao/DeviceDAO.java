package com.jmssolutions.iot.dao;

import com.jmssolutions.iot.domain.Device;
import com.jmssolutions.iot.domain.DeviceClass;
import com.jmssolutions.iot.domain.User;

import java.util.List;

/**
 * Created by jakub on 01.02.16.
 */
public interface DeviceDAO {
    DeviceClass findDeviceClass(String name);
    Device createDevice(Device device);
    Device findDeviceById(long id);
    List<Device> findDevicesByOwner(User user);
    Device updateDevice(Device device) throws IllegalArgumentException;
    void deleteDevice(Device device);
}
