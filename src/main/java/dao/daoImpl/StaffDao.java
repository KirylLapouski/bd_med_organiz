package dao.daoImpl;

import dao.CrudDao;
import entity.StaffEntity;
import org.hibernate.*;

import java.util.List;


/**
 * Created by lapko on 20.11.2017.
 */
public class StaffDao extends CrudDao<Integer, StaffEntity> {
    public StaffDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void delete(Integer integer) {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction= session.beginTransaction();

        Query query = session.createQuery("DELETE from StaffEntity where id = :staff_id ");
        query.setParameter("staff_id", integer);
        query.executeUpdate();
/*
        SQLQuery query = session.createSQLQuery(" DELETE FROM medicine.staff WHERE id = :id");
        query.setParameter("id",integer);
        query.executeUpdate();
*/

        transaction.commit();
    }

    public List list(){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction= session.beginTransaction();

        List staffEntity =  session.createQuery("FROM StaffEntity").list();
        transaction.commit();

        return staffEntity;
    }
}
