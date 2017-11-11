package dao.daoImpl;

import dao.CrudDao;
import entity.AnalysisEntity;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Queue;

/**
 * Created by lapko on 02.10.2017.
 */
//DONE
public class AnalysisDao implements CrudDao<Integer, AnalysisEntity>{
    SessionFactory sessionFactory;


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public AnalysisDao() {

    }
    public AnalysisDao( SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    public void create(AnalysisEntity o) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction= session.beginTransaction();

        session.save(o);
        transaction.commit();
    }

    public AnalysisEntity read(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction= session.beginTransaction();

        AnalysisEntity analysisEntity=session.load(AnalysisEntity.class,id);
        transaction.commit();

        return analysisEntity;
    }

    public void update(AnalysisEntity o) {
        Session session = sessionFactory.openSession();
        Transaction transaction= null;

        transaction = session.beginTransaction();

        session.update(o);

        transaction.commit();
    }

    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction= session.beginTransaction();
           SQLQuery query = session.createSQLQuery("DELETE FROM analysis WHERE id = :analys_id");
            query.addEntity(AnalysisEntity.class);
            query.setParameter("analys_id",id);
            query.executeUpdate();

        transaction.commit();
    }
}
