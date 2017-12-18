package main;

import FX.AutorizationController;
import FX.RootController;
import FX.StaffController;
import dao.daoImpl.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;

public class Main extends Application {
    protected static SessionFactory ourSessionFactory;

    private static MedicalFacilityDao medicalFacilityDao;
    private static AnalysisDao analysisDao;
    private static DiseaseDao diseaseDao;
    private static OccupiedBedsDao occupiedBedsDao;
    private static PositionDao positionDao;
    private static SpecialtyDao specialtyDao;
    private static RoomDao roomDao;
    private static StaffDao staffDao;
    private static TypeOfAnalysisDao typeOfAnalysisDao;

    private Stage stage;
    private BorderPane rootLayout;

    public BorderPane getRootLayout() {
        return rootLayout;
    }
/*
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();
            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }*/

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    public Main() {
    }

    public static void main(final String[] args) throws Exception {
            //JAVAFX
            launch(args);
            //JAVAFX ends

        ourSessionFactory.close();
    }

    //JAVAFX start
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        this.stage.setTitle("Staff");

        setAuthorizationWindow();
        initRootLayout();
        showStaffOverview();
    }

    public void setAuthorizationWindow(){

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/autorization.fxml"));
            AnchorPane layout = (AnchorPane) loader.load();
            Scene scene = new Scene(layout);

            Stage newWindow = new Stage();
            newWindow.setTitle("Authentication");
            newWindow.setScene(scene);


            AutorizationController controller = loader.getController();
            controller.setStage(this.getStage());

            newWindow.showAndWait();
        } catch (IOException e) {

        }


    }
    public  void initRootLayout(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/rootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            stage.setScene(scene);
            stage.show();

            RootController rootController = loader.getController();
            rootController.setMain(this);

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

            StaffController staffController  = loader.getController();

            staffController.setMainApp(this);
            RootController.setStaffController(staffController);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Stage getStage() {
        return stage;
    }

    public static SessionFactory getOurSessionFactory() {
        return ourSessionFactory;
    }

    //TODO
    public static void setOurSessionFactory(SessionFactory sessionFactory){
        Main.ourSessionFactory = sessionFactory;
    }
}