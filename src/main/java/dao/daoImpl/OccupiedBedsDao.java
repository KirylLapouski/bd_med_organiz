package dao.daoImpl;

import dao.CrudDao;
import entity.DiseaseEntity;
import entity.OccupiedBedsEntity;
import org.hibernate.*;

import java.util.List;

/**
 * Created by lapko on 13.11.2017.
 */
public class OccupiedBedsDao extends CrudDao<Integer, OccupiedBedsEntity> {
    public OccupiedBedsDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    @Override
    public void delete(Integer integer) {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException ex) {
            session = sessionFactory.openSession();
        }
        Transaction transaction = null;

        transaction= session.beginTransaction();
        SQLQuery query = session.createSQLQuery("DELETE FROM occupied_beds WHERE id = :occupied_beds_id");
        query.addEntity(DiseaseEntity.class);
        query.setParameter("occupied_beds_id",integer);
        query.executeUpdate();

        transaction.commit();
    }

    @Override
    public List list() {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException ex) {
            session = sessionFactory.openSession();
        }
        Transaction transaction = null;

        transaction= session.beginTransaction();

        List staffEntity =  session.createQuery("FROM OccupiedBedsEntity ").list();
        transaction.commit();

        return staffEntity;
    }
}
