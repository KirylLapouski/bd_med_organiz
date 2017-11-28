package dao.daoImpl;

import dao.CrudDao;
import entity.AnalysisEntity;
import org.hibernate.*;

import java.util.List;
import java.util.Queue;

/**
 * Created by lapko on 02.10.2017.
 */
//DONE
public class AnalysisDao extends CrudDao<Integer, AnalysisEntity>{


    public AnalysisDao( SessionFactory sessionFactory){
        super(sessionFactory);
    }

    public void delete(Integer id) {
        Session session;

            session = sessionFactory.openSession();

        Transaction transaction = null;

        transaction= session.beginTransaction();
           SQLQuery query = session.createSQLQuery("DELETE FROM analysis WHERE id = :analys_id");
            query.addEntity(AnalysisEntity.class);
            query.setParameter("analys_id",id);
            query.executeUpdate();

        transaction.commit();
        session.clear();

    }

    public List list(){
        Session session;

            session = sessionFactory.openSession();

        Transaction transaction = null;

        transaction= session.beginTransaction();

        List staffEntity =  session.createQuery("FROM AnalysisEntity").list();
        transaction.commit();
        session.clear();

        return staffEntity;
    }
}
