package dao.daoImpl;

import dao.CrudDao;
import entity.DepartmentEntity;
import entity.SpecialtyEntity;
import org.hibernate.*;

import java.util.List;

/**
 * Created by lapko on 16.11.2017.
 */
public class DepartmentDao extends CrudDao<Integer, DepartmentEntity> {
    public DepartmentDao(SessionFactory sessionFactory) {
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
        SQLQuery query = session.createSQLQuery("DELETE FROM department WHERE id = :id");
        query.addEntity(DepartmentEntity.class);
        query.setParameter("id",integer);
        query.executeUpdate();

        transaction.commit();
        session.clear();

    }

    @Override
    public List list() {
        Session session;
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException ex) {
            session = sessionFactory.openSession();
        }
        Transaction transaction = null;

        transaction= session.beginTransaction();

        List staffEntity =  session.createQuery("FROM DepartmentEntity").list();
        transaction.commit();
        session.clear();

        return staffEntity;
    }
}
