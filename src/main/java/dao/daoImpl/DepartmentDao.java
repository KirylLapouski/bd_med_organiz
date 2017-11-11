package dao.daoImpl;

import dao.CrudDao;
import entity.DepartmentEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Created by lapko on 02.10.2017.
 */
public class DepartmentDao implements CrudDao<Integer, DepartmentEntity> {
    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(DepartmentEntity o) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction= session.beginTransaction();

        session.save(o);
        transaction.commit();
        session.close();
    }

    @Override
    public DepartmentEntity read(Integer integer) {
        return null;
    }

    @Override
    public void update(DepartmentEntity o) {

    }

    @Override
    public void delete(Integer integer) {

    }
}
