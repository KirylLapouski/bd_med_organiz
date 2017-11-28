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

            session = sessionFactory.openSession();

        Transaction transaction = null;

        transaction= session.beginTransaction();
        SQLQuery query = session.createSQLQuery("DELETE FROM type_of_analysis WHERE id = :id");
        query.addEntity(TypeOfAnalysisEntity.class);
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

        List staffEntity =  session.createQuery("FROM TypeOfAnalysisEntity").list();
        transaction.commit();
        session.clear();


        return staffEntity;
    }
}
