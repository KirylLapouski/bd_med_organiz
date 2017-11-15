package dao.daoImpl;

import dao.CrudDao;
import entity.PositionEntity;
import entity.SpecialtyEntity;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Created by lapko on 13.11.2017.
 */
public class SpecialtyDao extends CrudDao<Integer, SpecialtyEntity> {

    public SpecialtyDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public SpecialtyEntity read(Integer id){
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction= session.beginTransaction();

        SpecialtyEntity specialtyEntity=  session.load(SpecialtyEntity.class, Integer.parseInt(id.toString()));
        transaction.commit();
        return specialtyEntity;
    }
    @Override
    public void delete(Integer integer) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction= session.beginTransaction();
        SQLQuery query = session.createSQLQuery("DELETE FROM specialty WHERE id = :id");
        query.addEntity(SpecialtyEntity.class);
        query.setParameter("id",integer);
        query.executeUpdate();

        transaction.commit();
    }
}
