package com.jmssolutions.iot.dao;

import com.jmssolutions.iot.domain.Device;
import com.jmssolutions.iot.domain.DeviceClass;
import com.jmssolutions.iot.domain.User;

import java.util.List;

/**
 * Created by jakub on 01.02.16.
 */
public interface DeviceDAO extends DataAccessObject<Long, Device>{
    DeviceClass findDeviceClass(String name);
    List<Device> findByOwner(User owner);
}
