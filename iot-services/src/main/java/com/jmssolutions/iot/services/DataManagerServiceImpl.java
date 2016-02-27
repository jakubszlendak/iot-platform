package com.jmssolutions.iot.services;

import com.jmssolutions.iot.dao.DataDAO;
import com.jmssolutions.iot.dao.SensorDAO;
import com.jmssolutions.iot.dao.UserDAO;
import com.jmssolutions.iot.domain.Sensor;
import com.jmssolutions.iot.domain.SensorData;
import com.jmssolutions.iot.domain.SensorDataReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jakub on 27.02.16.
 */
@Service
public class DataManagerServiceImpl implements DataManagerService {
    @Autowired
    DataDAO dataDAO;

    @Autowired
    SensorDAO sensorDAO;

    @Autowired
    UserDAO userDAO;

    @Override
    public List<SensorData> findDataInTimeRange(Sensor sensor, Date from, Date to) {
        return dataDAO.findInTimeRange(sensor, from, to);
    }

    @Override
    public SensorDataReport getDataReport(Sensor sensor) {
        SensorDataReport report = new SensorDataReport();
        report.setSensor(sensor);
        report.setEntryCount(dataDAO.getDataCount(sensor));
        report.setNewestEntryDate(dataDAO.getNewestEntry(sensor));
        report.setOldestEntryDate(dataDAO.getOldestEntry(sensor));
        return report;
    }
}
