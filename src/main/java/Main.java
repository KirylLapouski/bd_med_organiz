import dao.daoImpl.*;
import entity.*;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    private static final SessionFactory ourSessionFactory;
    private static  StaffDao staffDao;
    private static DepartmentDao departmentDao;
    private static HousingDao housingDao;
    private static MedicalFacilityDao medicalFacilityDao;
    private static AnalysisDao analysisDao;
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public static void main(final String[] args) throws Exception {

            departmentDao = new DepartmentDao();
            departmentDao.setSessionFactory(ourSessionFactory);

            housingDao = new HousingDao();
            housingDao.setSessionFactory(ourSessionFactory);

            medicalFacilityDao = new MedicalFacilityDao();
            medicalFacilityDao.setSessionFactory(ourSessionFactory);

            analysisDao  = new AnalysisDao();
            analysisDao.setSessionFactory(ourSessionFactory);

            analysisDao.create(new AnalysisEntity("analyzzzz"));
            AnalysisEntity analysisEntity  =  analysisDao.read(1);

            System.out.println("asdfa");
            System.out.println("Staff entity:" + analysisEntity.toString());
            ourSessionFactory.close();

    }
}