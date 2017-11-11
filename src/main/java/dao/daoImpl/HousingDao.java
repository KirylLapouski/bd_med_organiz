package dao.daoImpl;

import dao.CrudDao;
import entity.HousingEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Created by lapko on 03.10.2017.
 */
public class HousingDao implements CrudDao<Integer, HousingEntity>{
    SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(HousingEntity o) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction= session.beginTransaction();

        session.save(o);
        transaction.commit();
        session.close();
    }

    @Override
    public HousingEntity read(Integer integer) {
        return null;
    }

    @Override
    public void update(HousingEntity o) {

    }

    @Override
    public void delete(Integer integer) {

    }
}
