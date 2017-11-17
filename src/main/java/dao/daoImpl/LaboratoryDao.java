package dao.daoImpl;

import dao.CrudDao;
import entity.LaboratoryEntity;
import entity.TypeOfAnalysisEntity;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Created by lapko on 16.11.2017.
 */
public class LaboratoryDao extends CrudDao<Integer, LaboratoryEntity> {
    public LaboratoryDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void delete(Integer integer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction= session.beginTransaction();
        SQLQuery query = session.createSQLQuery("DELETE FROM laboratory WHERE id = :id");
        query.addEntity(LaboratoryEntity.class);
        query.setParameter("id",integer);
        query.executeUpdate();

        transaction.commit();
    }
}