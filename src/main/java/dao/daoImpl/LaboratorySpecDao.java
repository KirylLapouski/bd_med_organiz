package dao.daoImpl;

import dao.CrudDao;
import entity.LaboratorySpecEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by lapko on 26.11.2017.
 */
public class LaboratorySpecDao extends CrudDao<Integer, LaboratorySpecEntity> {
    public LaboratorySpecDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public List list() {
        Session session;

            session = sessionFactory.openSession();

        Transaction transaction = null;

        transaction= session.beginTransaction();

        List staffEntity =  session.createQuery("FROM LaboratorySpecEntity ").list();
        transaction.commit();
        session.clear();

        return staffEntity;
    }
}
