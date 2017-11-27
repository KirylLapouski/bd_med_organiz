package dao.daoImpl;

import dao.CrudDao;
import entity.PositionEntity;
import entity.SpecialtyEntity;
import org.hibernate.*;

import java.util.List;

/**
 * Created by lapko on 13.11.2017.
 */
public class SpecialtyDao extends CrudDao<Integer, SpecialtyEntity> {

    public SpecialtyDao(SessionFactory sessionFactory) {
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
        SQLQuery query = session.createSQLQuery("DELETE FROM specialty WHERE id = :id");
        query.addEntity(SpecialtyEntity.class);
        query.setParameter("id",integer);
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

        List staffEntity =  session.createQuery("FROM SpecialtyEntity ").list();
        transaction.commit();

        return staffEntity;
    }
}
