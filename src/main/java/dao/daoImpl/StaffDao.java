package dao.daoImpl;

import dao.CrudDao;
import entity.StaffEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class StaffDao implements CrudDao<Integer, StaffEntity> {

    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void create(StaffEntity o) {
            Session session = sessionFactory.openSession();
            Transaction transaction = null;

            transaction= session.beginTransaction();

            session.save(o);
            transaction.commit();
            session.close();
    }

    public StaffEntity read(Integer id) {
            Session session = sessionFactory.getCurrentSession();
            StaffEntity staffEntity=session.load(StaffEntity.class,id);

            return staffEntity;
    }

    public void update(StaffEntity o) {
            Session session = sessionFactory.getCurrentSession();
            session.update(o);
    }

    public StaffEntity delete(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        StaffEntity staffEntity = session.load(StaffEntity.class,id);

        if(staffEntity!= null){
            session.delete(id);
        }
        return null;
    }
}
