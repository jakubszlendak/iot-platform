package com.jmssolutions.iot.dao;

import com.jmssolutions.iot.domain.Device;
import com.jmssolutions.iot.domain.Measurand;
import com.jmssolutions.iot.domain.Sensor;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by jakub on 03.02.16.
 */
@Repository
public class SensorDAOImpl extends AbstractJpaDAO<Long, Sensor> implements SensorDAO {

    @Override
    public void remove(Sensor sensor){
        super.remove(entityManager.find(Sensor.class, sensor.getID()));
    }

    @Override
    public Measurand findMeasurandByName(String name) {
        return entityManager.find(Measurand.class, name);
    }

    @Override
    public List<Sensor> findByDevice(Device device) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Sensor> q = cb.createQuery(Sensor.class);
        Root<Sensor> u = q.from(Sensor.class);
        q.select(u);
        q.where(cb.equal(u.get("owningDevice"), device));

        TypedQuery<Sensor> tq = entityManager.createQuery(q);
        return tq.getResultList();
    }
}
