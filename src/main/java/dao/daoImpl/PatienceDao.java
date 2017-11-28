package dao.daoImpl;

import dao.CrudDao;
import entity.OfficeEntity;
import entity.PatienceEntity;
import org.hibernate.*;

import java.util.List;

/**
 * Created by lapko on 26.11.2017.
 */
public class PatienceDao extends CrudDao<Integer, PatienceEntity> {
    public PatienceDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void delete(Integer integer) {
        Session session;

            session = sessionFactory.openSession();

        Transaction transaction = null;

        transaction= session.beginTransaction();
        SQLQuery query = session.createSQLQuery("DELETE FROM patience WHERE id = :id");
        query.addEntity(PatienceEntity.class);
        query.setParameter("id",integer);
        query.executeUpdate();

        transaction.commit();
        session.clear();

    }

    @Override
    public List list() {
        Session session;

            session = sessionFactory.openSession();

        Transaction transaction = null;

        transaction= session.beginTransaction();

        List staffEntity =  session.createQuery("FROM PatienceEntity ").list();
        transaction.commit();
        session.clear();

        return staffEntity;
    }
}
