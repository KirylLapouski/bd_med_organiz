package dao.daoImpl;

import dao.CrudDao;
import entity.AnalysisEntity;
import entity.DiseaseEntity;
import org.hibernate.*;

import java.util.List;

/**
 * Created by lapko on 13.11.2017.
 */
public class DiseaseDao extends CrudDao<Integer, DiseaseEntity> {


    public DiseaseDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    public void delete(Integer integer){
        Session session;

            session = sessionFactory.openSession();

        Transaction transaction = null;

        transaction= session.beginTransaction();
        SQLQuery query = session.createSQLQuery("DELETE FROM disease WHERE id = :disease_id");
        query.addEntity(DiseaseEntity.class);
        query.setParameter("disease_id",integer);
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

        List staffEntity =  session.createQuery("FROM DiseaseEntity ").list();
        transaction.commit();
        session.clear();

        return staffEntity;
    }

}
