package dao;

import entity.OccupiedBedsEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

public abstract class CrudDao <ID, T> {
    protected SessionFactory sessionFactory;


    void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public CrudDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void create(T o) {
        Session session;
        session = sessionFactory.openSession();

        Transaction transaction = null;

        transaction = session.beginTransaction();

        session.save(o);
        transaction.commit();
        session.clear();
    }

    public T read(ID id, Class classToRead) {
        Session session;

        session = sessionFactory.openSession();

        Transaction transaction = null;

        transaction = session.beginTransaction();

        T occupiedBedsEntity = (T) session.load(classToRead, Integer.parseInt(id.toString()));
        transaction.commit();
        session.clear();

        return occupiedBedsEntity;
    }

    public void update(T o) {
        Session session;

        session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction = session.beginTransaction();

        session.update(o);

        transaction.commit();
        session.clear();

    }

    public abstract void delete(ID id);

    public abstract List list();
}
