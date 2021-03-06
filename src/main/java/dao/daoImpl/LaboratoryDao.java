package dao.daoImpl;

import dao.CrudDao;
import entity.LaboratoryEntity;
import entity.TypeOfAnalysisEntity;
import org.hibernate.*;

import java.util.List;

/**
 * Created by lapko on 16.11.2017.
 */
public class LaboratoryDao extends CrudDao<Integer, LaboratoryEntity> {
    public LaboratoryDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void delete(Integer integer) {
        Session session;

            session = sessionFactory.openSession();

        Transaction transaction = null;

        transaction= session.beginTransaction();
        SQLQuery query = session.createSQLQuery("DELETE FROM laboratory WHERE id = :id");
        query.addEntity(LaboratoryEntity.class);
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

        List staffEntity =  session.createQuery("FROM LaboratoryEntity ").list();
        transaction.commit();
        session.clear();

        return staffEntity;
    }
}
