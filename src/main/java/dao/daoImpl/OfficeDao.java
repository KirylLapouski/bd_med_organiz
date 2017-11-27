package dao.daoImpl;

import dao.CrudDao;
import entity.OfficeEntity;
import entity.PositionEntity;
import org.hibernate.*;

import java.util.List;

/**
 * Created by lapko on 26.11.2017.
 */
public class OfficeDao extends CrudDao<Integer, OfficeEntity> {
    public OfficeDao(SessionFactory sessionFactory) {
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
        SQLQuery query = session.createSQLQuery("DELETE FROM office WHERE id = :id");
        query.addEntity(OfficeEntity.class);
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

        List staffEntity =  session.createQuery("FROM OfficeEntity").list();
        transaction.commit();

        return staffEntity;
    }
}
