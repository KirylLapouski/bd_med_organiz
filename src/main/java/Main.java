import dao.daoImpl.*;
import entity.*;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Main {
    private static final SessionFactory ourSessionFactory;

    private static MedicalFacilityDao medicalFacilityDao;
    private static AnalysisDao analysisDao;
    private static DiseaseDao diseaseDao;
    private static OccupiedBedsDao occupiedBedsDao;
    private static PositionDao positionDao;
    private static SpecialtyDao specialtyDao;
    private static RoomDao roomDao;
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



            medicalFacilityDao = new MedicalFacilityDao(ourSessionFactory);

            analysisDao  = new AnalysisDao(ourSessionFactory);

            occupiedBedsDao = new OccupiedBedsDao(ourSessionFactory);

            positionDao = new PositionDao(ourSessionFactory);

            diseaseDao = new DiseaseDao(ourSessionFactory);
            specialtyDao = new SpecialtyDao(ourSessionFactory);

            roomDao = new RoomDao(ourSessionFactory);



        ourSessionFactory.close();

    }
}