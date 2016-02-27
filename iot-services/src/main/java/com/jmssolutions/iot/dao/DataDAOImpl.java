package com.jmssolutions.iot.dao;

import com.jmssolutions.iot.domain.Sensor;
import com.jmssolutions.iot.domain.SensorData;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;

/**
 * Created by jakub on 27.02.16.
 */
@Repository
public class DataDAOImpl extends AbstractJpaDAO<Long, SensorData> implements DataDAO {

    private final static Logger logger = Logger.getLogger(DataDAOImpl.class);



    @Override
    public List<SensorData> findInTimeRange(Sensor sensor, Date from, Date to) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<SensorData> q = cb.createQuery(SensorData.class);
        Root<SensorData> d = q.from(SensorData.class);
        q.select(d);
        q.where(
                cb.and(
                        cb.equal(d.get("sensor"), sensor),
                        cb.gt(d.get("timestamp"), from.getTime()),
                        cb.lt(d.get("timestamp"), to.getTime())
                ));
        TypedQuery<SensorData> typedQuery = entityManager.createQuery(q);
        return typedQuery.getResultList();
    }

    @Override
    public List<SensorData> findOlderThan(Sensor sensor, Date date) {
        throw new NotImplementedException();
    }

    @Override
    public List<SensorData> findNewerThan(Sensor sensor, Date date) {
        throw new NotImplementedException();
//        return null;
    }

    @Override
    public long getDataCount(Sensor sensor) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> q = cb.createQuery(Long.class);
        Root<SensorData> d = q.from(SensorData.class);
        q.select(cb.count(d));
        q.where(cb.equal(d.get("sensor"), sensor));
        TypedQuery<Long> typedQuery = entityManager.createQuery(q);
        return typedQuery.getSingleResult();
    }

    @Override
    public Date getOldestEntry(Sensor sensor) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> q = cb.createQuery(Long.class);
        Root<SensorData> d = q.from(SensorData.class);
        q.select(cb.min(d.get("timestamp")));
        q.where(cb.equal(d.get("sensor"), sensor));
        TypedQuery<Long> typedQuery = entityManager.createQuery(q);
        return new Date(typedQuery.getSingleResult());
    }

    @Override
    public Date getNewestEntry(Sensor sensor) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> q = cb.createQuery(Long.class);
        Root<SensorData> d = q.from(SensorData.class);
        q.select(cb.max(d.get("timestamp")));
        q.where(cb.equal(d.get("sensor"), sensor));
        TypedQuery<Long> typedQuery = entityManager.createQuery(q);
        return new Date(typedQuery.getSingleResult());
    }
}
