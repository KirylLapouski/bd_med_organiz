package dao.daoImpl;

import dao.CrudDao;
import entity.RoomEntity;
import entity.SpecialtyEntity;
import org.hibernate.*;

import java.util.List;

/**
 * Created by lapko on 15.11.2017.
 */
public class RoomDao extends CrudDao<Integer, RoomEntity> {
    public RoomDao(SessionFactory sessionFactory) {
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
        SQLQuery query = session.createSQLQuery("DELETE FROM room WHERE id = :id");
        query.addEntity(SpecialtyEntity.class);
        query.setParameter("id",integer);
        query.executeUpdate();

        transaction.commit();
    }

    @Override
    public List list() {
        return null;
    }
}
