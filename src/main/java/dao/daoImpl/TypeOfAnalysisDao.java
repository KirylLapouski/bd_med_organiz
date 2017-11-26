package dao.daoImpl;

import dao.CrudDao;
import entity.SpecialtyEntity;
import entity.TypeOfAnalysisEntity;
import org.hibernate.*;

import java.util.List;

/**
 * Created by lapko on 16.11.2017.
 */
public class TypeOfAnalysisDao extends CrudDao<Integer, TypeOfAnalysisEntity> {

    public TypeOfAnalysisDao(SessionFactory sessionFactory) {
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
        SQLQuery query = session.createSQLQuery("DELETE FROM type_of_analysis WHERE id = :id");
        query.addEntity(TypeOfAnalysisEntity.class);
        query.setParameter("id",integer);
        query.executeUpdate();

        transaction.commit();
    }

    @Override
    public List list() {
        return null;
    }
}
