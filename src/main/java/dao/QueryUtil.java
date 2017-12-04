package dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.engine.query.spi.sql.NativeSQLQueryReturn;
import org.hibernate.jdbc.Work;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by lapko on 28.11.2017.
 */
public class QueryUtil {
    SessionFactory sessionFactory;
    public QueryUtil(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public ResultSet createQuery(String SQLQuery){
        final ResultSet[] result = new ResultSet[1];
        Session sesssion = sessionFactory.openSession();
        Transaction transaction = sesssion.beginTransaction();

        sesssion.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                result[0] = connection.createStatement().executeQuery(SQLQuery);

            }
        });
        transaction.commit();
        return result[0];
    }

    public ResultSet createQueryWithParam(String SQLQuery,int param){
        final ResultSet[] result = new ResultSet[1];
        Session sesssion = sessionFactory.openSession();
        Transaction transaction = sesssion.beginTransaction();

        sesssion.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {

                result[0] = connection.createStatement().executeQuery(SQLQuery.replace("?",String.valueOf(param)));
            }
        });
        transaction.commit();
        return result[0];
    }
}
