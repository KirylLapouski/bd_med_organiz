package dao.daoImpl;

import dao.CrudDao;
import entity.DiseaseEntity;
import entity.OccupiedBedsEntity;
import entity.PositionEntity;
import javafx.geometry.Pos;
import org.hibernate.*;

import java.util.List;

/**
 * Created by lapko on 13.11.2017.
 */
public class PositionDao extends CrudDao<Integer, PositionEntity> {

    public PositionDao(SessionFactory sessionFactory)
    {
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
        SQLQuery query = session.createSQLQuery("DELETE FROM position_ WHERE id = :position_id");
        query.addEntity(PositionEntity.class);
        query.setParameter("position_id",integer);
        query.executeUpdate();

        transaction.commit();
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

        List staffEntity =  session.createQuery("FROM PositionEntity ").list();
        transaction.commit();

        return staffEntity;
    }
}
