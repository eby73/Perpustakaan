/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.orm.dao;

import com.eby.orm.utils.HibernateUtils;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author eby
 */
public class GenericDAO {

    @Resource(name = "sessionFactory")
    private final SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

    public <T> T save(final T o) {
       Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        return o;
    }

    public void delete(final Object object) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Object object1 = session.merge(object);
        session.delete(object1);
        session.getTransaction().commit();
    }

    /**
     *
     */
    public <T> T get(final Class<T> type, final int id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        T t = (T) session.get(type, id);
        session.getTransaction().commit();
        return t;
    }

    /**
     *
     */
    public void update(final Object o) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Object obj = session.merge(o);
        session.update(obj);
        session.getTransaction().commit();
    }

    /**
     *
     */
    public void saveOrUpdate(final Object o) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Object object = session.merge(o);
        session.saveOrUpdate(object);
        session.getTransaction().commit();
    }

    public <T> List<T> getAll(final Class<T> type) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria crit = session.createCriteria(type);
        session.getTransaction().commit();
        return crit.list();
    }

    public <T> List<T> findData(String entiyAtt, String like, Class<T> type) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria cr = session.createCriteria(type);
        cr.add(Restrictions.ilike(entiyAtt, like, MatchMode.START));
        T t = (T) cr.list();
        session.getTransaction().commit();
        return cr.list();
    }
}