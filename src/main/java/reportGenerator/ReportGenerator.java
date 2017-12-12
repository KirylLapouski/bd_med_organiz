package reportGenerator;

import java.io.File;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Date;
import java.util.HashMap;
import java.util.ArrayList;

import entity.MedicalFacilityEntity;
import main.Main;
import net.sf.jasperreports.engine.*;

import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jdbc.Work;

public class ReportGenerator
{
    private String REPORT_pdf     = "src/main/resources/report.html";
    private String REPORT_pattern = "src/main/resources/jrxml/medicalFacility.jrxml";
    private Map<String, Object>         parameters;


    private JasperDesign jasperDesign;
    private JasperReport jasperReport;
    private JasperPrint  jasperPrint ;

    public void create() throws JRException
    {


        parameters = new HashMap<String, Object>();

        jasperReport  = JasperCompileManager.compileReport(REPORT_pattern);


        Session sesssion = Main.getOurSessionFactory().openSession();
        Transaction transaction = sesssion.beginTransaction();
        sesssion.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                try {
                    jasperPrint   = JasperFillManager.fillReport(jasperReport, parameters,connection);
                    JasperExportManager.exportReportToHtmlFile(jasperPrint,REPORT_pdf);
                } catch (JRException e) {
                    e.printStackTrace();
                }
            }
        });
        transaction.commit();

    }

    public void setREPORT_pattern(String pattern){
        REPORT_pattern = pattern;
    }

}