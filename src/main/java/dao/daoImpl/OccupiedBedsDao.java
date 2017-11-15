package dao.daoImpl;

import dao.CrudDao;
import entity.DiseaseEntity;
import entity.OccupiedBedsEntity;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Created by lapko on 13.11.2017.
 */
public class OccupiedBedsDao extends CrudDao<Integer, OccupiedBedsEntity> {
    public OccupiedBedsDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    @Override
    public void delete(Integer integer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction= session.beginTransaction();
        SQLQuery query = session.createSQLQuery("DELETE FROM occupied_beds WHERE id = :occupied_beds_id");
        query.addEntity(DiseaseEntity.class);
        query.setParameter("occupied_beds_id",integer);
        query.executeUpdate();

        transaction.commit();
    }
}
