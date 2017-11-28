package dao.daoImpl;

import dao.CrudDao;
import entity.DepartmentEntity;
import entity.MedicalFacilityEntity;
import org.hibernate.*;

import java.util.List;

/**
 * Created by lapko on 03.10.2017.
 */
public class MedicalFacilityDao extends CrudDao<Integer, MedicalFacilityEntity> {


    public MedicalFacilityDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void delete(Integer integer) {
        Session session;

            session = sessionFactory.openSession();

        Transaction transaction = null;

        transaction= session.beginTransaction();
        SQLQuery query = session.createSQLQuery("DELETE FROM medical_facility WHERE id = :id");
        query.addEntity(MedicalFacilityEntity.class);
        query.setParameter("id",integer);
        query.executeUpdate();

        transaction.commit();
        session.clear();

    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List list() {
        Session session;

            session = sessionFactory.openSession();

        Transaction transaction = session.beginTransaction();

        List staffEntity =  session.createQuery("FROM MedicalFacilityEntity ").list();
        transaction.commit();
        session.clear();

        return staffEntity;
    }
}
