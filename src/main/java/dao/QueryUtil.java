package dao;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.engine.query.spi.sql.NativeSQLQueryReturn;
import org.hibernate.jdbc.Work;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lapko on 28.11.2017.
 */
public class QueryUtil {
    SessionFactory sessionFactory;
    List<String> param =new ArrayList<>();

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

   /* public ResultSet createQueryWithParam(String SQLQuery,String param){
        final ResultSet[] result = new ResultSet[1];
        Session sesssion = sessionFactory.openSession();
        Transaction transaction = sesssion.beginTransaction();

        sesssion.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {

                result[0] = connection.createStatement().executeQuery(SQLQuery.replace("?",param));
            }
        });
        transaction.commit();
        return result[0];
    }*/

    public ResultSet createQueryWithParam(String SQLQuery){
        final ResultSet[] result = new ResultSet[1];
        Session sesssion = sessionFactory.openSession();
        Transaction transaction = sesssion.beginTransaction();

        sesssion.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                List<String> str =new ArrayList<String>();
                int begin=0;
                int end = 0;

                while((end = SQLQuery.indexOf('?',begin+1))!=-1){

                    if(begin!=0){
                        str.add(SQLQuery.substring(begin+1, end));
                    }else {
                        str.add(SQLQuery.substring(begin, end));
                    }

                    begin = end;
                }

                str.add(");");

                String resultQuery ="";

                for(int i=0;i<str.size()-1;i++) {
                    resultQuery += str.get(i).concat(param.get(i));
                }
                resultQuery +=str.get(str.size()-1);

                result[0] = connection.createStatement().executeQuery(resultQuery);
            }
        });
        transaction.commit();
        return result[0];
    }

    public void addParam(String str){
        param.add("'" + str+"'");
    }
    public void clearParams(){
        param.clear();
    }
}
