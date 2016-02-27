package com.jmssolutions.iot.dao;

import com.jmssolutions.iot.domain.Sensor;
import com.jmssolutions.iot.domain.SensorData;

import java.util.Date;
import java.util.List;

/**
 * Created by jakub on 27.02.16.
 */
public interface DataDAO extends DataAccessObject<Long, SensorData>{
    List<SensorData> findInTimeRange(Sensor sensor, Date from, Date to);
    List<SensorData> findOlderThan(Sensor sensor, Date date);
    List<SensorData> findNewerThan(Sensor sensor, Date date);
    long getDataCount(Sensor sensor);
    Date getOldestEntry(Sensor sensor);
    Date getNewestEntry(Sensor sensor);
}
