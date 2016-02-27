package com.jmssolutions.iot.services;

import com.jmssolutions.iot.domain.Sensor;
import com.jmssolutions.iot.domain.SensorData;
import com.jmssolutions.iot.domain.SensorDataReport;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jakub on 27.02.16.
 */
public interface DataManagerService {
    List<SensorData> findDataInTimeRange(Sensor sensor, Date from, Date to);
    SensorDataReport getDataReport(Sensor sensor);

}
