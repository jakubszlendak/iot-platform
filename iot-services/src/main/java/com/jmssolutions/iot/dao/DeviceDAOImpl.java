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
public class DeviceDAOImpl extends AbstractJpaDAO<Long, Device> implements DeviceDAO {

    private final static Logger logger = Logger.getLogger(DeviceDAOImpl.class);


    @Override
    public DeviceClass findDeviceClass(String name) {
        return entityManager.find(DeviceClass.class, name);
    }

    @Override
    public List<Device> findByOwner(User owner) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Device> q = cb.createQuery(Device.class);
        Root<Device> u = q.from(Device.class);
        q.select(u);
        q.where(cb.equal(u.get("owner"), owner));

        TypedQuery<Device> tq = entityManager.createQuery(q);
        return tq.getResultList();
    }

    @Override
    @Transactional
    public void remove(Device device){
        super.remove(entityManager.find(Device.class, device.getID()));
    }


}
