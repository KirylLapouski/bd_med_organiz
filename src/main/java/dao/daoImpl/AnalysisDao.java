package dao.daoImpl;

import dao.CrudDao;
import entity.AnalysisEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Created by lapko on 02.10.2017.
 */
public class AnalysisDao implements CrudDao<Integer, AnalysisEntity>{
    SessionFactory sessionFactory;


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public AnalysisDao() {

    }

    public void create(AnalysisEntity o) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction= session.beginTransaction();

        session.save(o);
        transaction.commit();
        session.close();
    }

    public AnalysisEntity read(Integer id) {
        Session session = sessionFactory.openSession();
        AnalysisEntity analysisEntity=session.load(AnalysisEntity.class,id);

        return analysisEntity;
    }

    public void update(AnalysisEntity o) {
        Session session = sessionFactory.getCurrentSession();
        session.update(o);
    }

    public AnalysisEntity delete(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        AnalysisEntity analysisEntity = session.load(AnalysisEntity.class,id);

        if(analysisEntity!= null){
            session.delete(id);
        }
        return null;
    }
}
