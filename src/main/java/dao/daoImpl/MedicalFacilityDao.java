package dao.daoImpl;

import dao.CrudDao;
import entity.MedicalFacilityEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Created by lapko on 03.10.2017.
 */
public class MedicalFacilityDao extends CrudDao<Integer, MedicalFacilityEntity> {

    SessionFactory sessionFactory;

    public MedicalFacilityDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(MedicalFacilityEntity o) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        transaction= session.beginTransaction();

        session.save(o);
        transaction.commit();
        session.close();
    }


    @Override
    public void update(MedicalFacilityEntity o) {

    }

    @Override
    public void delete(Integer integer) {

    }
}
