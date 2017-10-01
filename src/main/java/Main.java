import dao.daoImpl.StaffDao;
import entity.StaffEntity;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;

import java.util.Map;

public class Main {
    private static final SessionFactory ourSessionFactory;
    private static  StaffDao staffDao;
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

            staffDao = new StaffDao();
            staffDao.setSessionFactory(ourSessionFactory);
            staffDao.create(new StaffEntity("Lapkovsky"));
            staffDao.create(new StaffEntity("Win"));
            System.out.println("asdfa");
//            StaffEntity staffEntity= staffDao.read(1);
//            System.out.println("Staff entity:" + staffEntity.toString());
            ourSessionFactory.close();

    }
}