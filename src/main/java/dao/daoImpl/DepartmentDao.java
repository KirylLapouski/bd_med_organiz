package dao.daoImpl;

import dao.CrudDao;
import entity.DepartmentEntity;
import entity.SpecialtyEntity;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction= session.beginTransaction();
        SQLQuery query = session.createSQLQuery("DELETE FROM department WHERE id = :id");
        query.addEntity(DepartmentEntity.class);
        query.setParameter("id",integer);
        query.executeUpdate();

        transaction.commit();
    }

    @Override
    public List list() {
        return null;
    }
}
