package com.jmssolutions.iot.dao;

import com.jmssolutions.iot.domain.Device;
import com.jmssolutions.iot.domain.Measurand;
import com.jmssolutions.iot.domain.Sensor;

import java.util.List;

/**
 * Created by jakub on 03.02.16.
 */
public interface SensorDAO  extends DataAccessObject<Long, Sensor>{

    Measurand findMeasurandByName(String name);
    List<Sensor> findByDevice(Device device);

}
