import dao.daoImpl.*;
import entity.StaffEntity;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    private static final SessionFactory ourSessionFactory;

    private static MedicalFacilityDao medicalFacilityDao;
    private static AnalysisDao analysisDao;
    private static DiseaseDao diseaseDao;
    private static OccupiedBedsDao occupiedBedsDao;
    private static PositionDao positionDao;
    private static SpecialtyDao specialtyDao;
    private static RoomDao roomDao;
    private static TypeOfAnalysisDao typeOfAnalysisDao;

    private Stage stage;
    private BorderPane rootLayout;

    private ObservableList<StaffEntity> staff = FXCollections.observableArrayList();

    public ObservableList<StaffEntity> getStaff() {
        return staff;
    }

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

    public Main() {
    }

    public static void main(final String[] args) throws Exception {
            //JAVAFX
            launch(args);
            //JAVAFX ends

            medicalFacilityDao = new MedicalFacilityDao(ourSessionFactory);

            analysisDao  = new AnalysisDao(ourSessionFactory);

            occupiedBedsDao = new OccupiedBedsDao(ourSessionFactory);

            positionDao = new PositionDao(ourSessionFactory);

            diseaseDao = new DiseaseDao(ourSessionFactory);
            specialtyDao = new SpecialtyDao(ourSessionFactory);

            roomDao = new RoomDao(ourSessionFactory);
            typeOfAnalysisDao  = new TypeOfAnalysisDao(ourSessionFactory);


        ourSessionFactory.close();

    }

    //JAVAFX start
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        this.stage.setTitle("Staff");

        initRootLayout();
        showStaffOverview();
    }

    public  void initRootLayout(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("view/rootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showStaffOverview(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/view/staff.fxml"));

            AnchorPane anchorPane = (AnchorPane)loader.load();
            anchorPane.setMaxHeight(Double.MAX_VALUE);
            anchorPane.setMaxWidth(Double.MAX_VALUE);
            rootLayout.setCenter(anchorPane);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Stage getPrimaryStage() {
        return stage;
    }


}