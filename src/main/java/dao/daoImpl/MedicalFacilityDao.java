package dao.daoImpl;

import dao.CrudDao;
import entity.MedicalFacilityEntity;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by lapko on 03.10.2017.
 */
public class MedicalFacilityDao extends CrudDao<Integer, MedicalFacilityEntity> {

    SessionFactory sessionFactory;

    public MedicalFacilityDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public void delete(Integer integer) {

    }


    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }






    @Override
    public List list() {
        return null;
    }
}
