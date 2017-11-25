package dao.daoImpl;

import dao.CrudDao;
import entity.HousingEntity;
import entity.TypeOfAnalysisEntity;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by lapko on 16.11.2017.
 */
public class HousingDao extends CrudDao<Integer, HousingEntity> {
    public HousingDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void delete(Integer integer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction= session.beginTransaction();
        SQLQuery query = session.createSQLQuery("DELETE FROM housing WHERE id = :id");
        query.addEntity(HousingEntity.class);
        query.setParameter("id",integer);
        query.executeUpdate();

        transaction.commit();
    }

    @Override
    public List list() {
        return null;
    }
}
