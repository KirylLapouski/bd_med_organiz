package dao.daoImpl;

import dao.CrudDao;
import entity.SpecialtyEntity;
import entity.TypeOfAnalysisEntity;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Created by lapko on 16.11.2017.
 */
public class TypeOfAnalysisDao extends CrudDao<Integer, TypeOfAnalysisEntity> {

    public TypeOfAnalysisDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void delete(Integer integer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction= session.beginTransaction();
        SQLQuery query = session.createSQLQuery("DELETE FROM type_of_analysis WHERE id = :id");
        query.addEntity(TypeOfAnalysisEntity.class);
        query.setParameter("id",integer);
        query.executeUpdate();

        transaction.commit();
    }
}
