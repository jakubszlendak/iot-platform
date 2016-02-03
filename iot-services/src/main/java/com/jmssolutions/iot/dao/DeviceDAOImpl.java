package com.jmssolutions.iot.dao;

import com.jmssolutions.iot.domain.Device;
import com.jmssolutions.iot.domain.DeviceClass;
import com.jmssolutions.iot.domain.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.util.List;

/**
 * Created by jakub on 01.02.16.
 */
@Repository
public class DeviceDAOImpl implements DeviceDAO {

    @PersistenceContext
    EntityManager entityManager;

    private final static Logger logger = Logger.getLogger(DeviceDAOImpl.class);


    @Override
    public DeviceClass findDeviceClass(String name) {
        return entityManager.find(DeviceClass.class, name);
    }

    @Override
    @Transactional
    public Device createDevice(Device device) {
        logger.info("Trying to create device: " + device.toString());
        try{
            device = entityManager.merge(device);
            entityManager.flush();
            entityManager.detach(device);
            return device;
        } catch (PersistenceException e){
            logger.error("Persistence error: "+ e.getMessage());
            throw new PersistenceException(e.getCause().toString());
        }
    }

    @Override
    public Device findDeviceById(long id) {
        return entityManager.find(Device.class, id);
    }

    @Override
    public List<Device> findDevicesByOwner(User user) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Device> q = cb.createQuery(Device.class);
        Root<Device> u = q.from(Device.class);
        q.select(u);
        q.where(cb.equal(u.get("owner"), user));

        TypedQuery<Device> tq = entityManager.createQuery(q);
        return tq.getResultList();
    }

    @Override
    @Transactional
    public Device updateDevice(Device device) {
        Device d = entityManager.find(Device.class, device.getID());
        if(d==null)
            throw new IllegalArgumentException("Device not found");
        return entityManager.merge(device);
    }

    @Override
    @Transactional
    public void deleteDevice(Device device) {
        Device d = entityManager.find(Device.class, device.getID());
        entityManager.remove(d);
    }
}
