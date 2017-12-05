package FX;

import dao.daoImpl.*;
import entity.AnalysisEntity;
import entity.MedicalFacilityEntity;
import entity.StaffEntity;
import entity.util.Tables;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.MenuItem;
import main.Main;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lapko on 25.11.2017.
 */
public class RootController {

    public RootController() {
    }
    private Main main;
    private static StaffController staffController;

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private TableView<Tables> tablesTable = new TableView<>();

    @FXML
    private TableColumn<Tables,String> name;

    @FXML
    private MenuItem firstMenuItem;
    @FXML
    private MenuItem secondMenuItem;
    @FXML
    private MenuItem thirdMenuItem;
    @FXML
    private MenuItem fouthMenuItem;
    @FXML
    private MenuItem deleteUser;
    @FXML
    private MenuItem addUser;
    @FXML
    private MenuItem addPermission;
    @FXML
    private MenuItem deletePermission;

    private static ObservableList<Tables> list =  FXCollections.observableArrayList();

    public StaffController getStaffController() {
        return staffController;
    }

    public static void setStaffController(StaffController staffController) {
        RootController.staffController = staffController;
    }

    @FXML
    private void queryHandler(){
        openQueryWindow();
    }

    @FXML
    private void initialize() {

        tableListInit();
        name.setCellValueFactory(new PropertyValueFactory<Tables,String>("name"));

        tablesTable.setItems(list);

        tablesTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> chooseHadnler(newValue));
   }

    private void chooseHadnler(Tables table){
        switch (table.getName())
        {
            case "staff": StaffDao staffDao = new StaffDao(Main.getOurSessionFactory());
                            StaffController.setStaff(staffDao.list().toArray());
                            staffController.setStaffColumns();
                            staffController.setDao(staffDao);
                            break;
            case "analysis":
                AnalysisDao analysisDao = new AnalysisDao(Main.getOurSessionFactory());
                    StaffController.setStaff(analysisDao.list().toArray());
                    staffController.setAnalysisColumns();
                    staffController.setDao(analysisDao);
                    break;
            case "department":
                DepartmentDao departmentDao = new DepartmentDao(Main.getOurSessionFactory());
                StaffController.setStaff(departmentDao.list().toArray());
                staffController.setDepartmentColumn();
                staffController.setDao(departmentDao);
                break;
            case "disease":
                DiseaseDao diseaseDao = new DiseaseDao(Main.getOurSessionFactory());
                StaffController.setStaff(diseaseDao.list().toArray());
                staffController.setDiseaseColumn();
                staffController.setDao(diseaseDao);
                break;
            case "housing":
                HousingDao housingDao = new HousingDao(Main.getOurSessionFactory());
                StaffController.setStaff(housingDao.list().toArray());
                staffController.setHousingColumn();
                staffController.setDao(housingDao);
                break;
            case "laboratory":
                LaboratoryDao laboratoryDao = new LaboratoryDao(Main.getOurSessionFactory());
                StaffController.setStaff(laboratoryDao.list().toArray());
                staffController.setLaboratoryColumn();
                staffController.setDao(laboratoryDao);
                break;
            case "laboratorySpec":
                 LaboratorySpecDao laboratorySpecDao = new LaboratorySpecDao(Main.getOurSessionFactory());
                StaffController.setStaff(laboratorySpecDao.list().toArray());
                staffController.setLaboratorySpecColumn();
                staffController.setDao(laboratorySpecDao);
                break;
            case "medicalFacility":
                MedicalFacilityDao medicalFacilityDao = new MedicalFacilityDao(Main.getOurSessionFactory());
                StaffController.setStaff(medicalFacilityDao.list().toArray());
                staffController.setMedicalFacilityColumn();
                staffController.setDao(medicalFacilityDao);
                break;
            case "occupieBeds":
                OccupiedBedsDao occupiedBedsDao = new OccupiedBedsDao(Main.getOurSessionFactory());
                StaffController.setStaff(occupiedBedsDao.list().toArray());
                staffController.setOccupiedBedsColumn();
                staffController.setDao(occupiedBedsDao);
                break;
            case "office":
                OfficeDao officeDao = new OfficeDao(Main.getOurSessionFactory());
                StaffController.setStaff(officeDao.list().toArray());
                staffController.setOfficeColumn();
                staffController.setDao(officeDao);
                break;
            case "patience":
                PatienceDao patienceDao = new PatienceDao(Main.getOurSessionFactory());
                StaffController.setStaff(patienceDao.list().toArray());
                staffController.setPatienceColumn();
                staffController.setDao(patienceDao);
                break;
            case "position":
                PositionDao positionDao = new PositionDao(Main.getOurSessionFactory());
                StaffController.setStaff(positionDao.list().toArray());
                staffController.setPositionColumn();
                staffController.setDao(positionDao);
                break;
            case "room":
                RoomDao roomDao = new RoomDao(Main.getOurSessionFactory());
                StaffController.setStaff(roomDao.list().toArray());
                staffController.setRoomColumn();
                staffController.setDao(roomDao);
                break;
            case "specialty":
                SpecialtyDao specialtyDao = new SpecialtyDao(Main.getOurSessionFactory());
                StaffController.setStaff(specialtyDao.list().toArray());
                staffController.setSpecialtyColumn();
                staffController.setDao(specialtyDao);
                break;
            case "typeOfAnalysis":
                TypeOfAnalysisDao typeOfAnalysisDao = new TypeOfAnalysisDao(Main.getOurSessionFactory());
                StaffController.setStaff(typeOfAnalysisDao.list().toArray());
                staffController.setTypeOfAnalysisColumn();
                staffController.setDao(typeOfAnalysisDao);
                break;
        }
    }

    private void tableListInit(){
        list.add(new Tables("staff"));
        list.add(new Tables("analysis"));
        list.add(new Tables("department"));
        list.add(new Tables("disease"));
        list.add(new Tables("housing"));
        list.add(new Tables("laboratory"));
        list.add(new Tables("laboratorySpec"));
        list.add(new Tables("medicalFacility"));
        list.add(new Tables("occupieBeds"));
        list.add(new Tables("office"));
        list.add(new Tables("patience"));
        list.add(new Tables("position"));
        list.add(new Tables("room"));
        list.add(new Tables("specialty"));
        list.add(new Tables("typeOfAnalysis"));

    }

    @FXML
    private void menuItemHandler(Event event){
        if(event.getTarget() == firstMenuItem) {
            showCallFunctionWindow("Select getOrderleDoctors();");
        }else if(event.getTarget() == secondMenuItem){
            showCallFunctionWindow("Select getFreeBeds(?);",new String[]{"Room id"});
        }else if(event.getTarget()== thirdMenuItem){
            showCallFunctionWindow("Select checkDoctorById(?);", new String[]{"Staff id"});
        }else if(event.getTarget() == fouthMenuItem){
            showCallFunctionWindow("call workingStaff();");
        }
    }

    @FXML
    private void administrationHandler(Event event){
        if(event.getTarget() == deleteUser){
            showAdministrationWindow("call deleteUser(?)", new String[]{"User name"});
        }else if(event.getTarget() == addUser){
            showAdministrationWindow("call createUser(?,?)", new String[]{"User name", "Password"});
        }else if(event.getTarget() == addPermission){
            showAdministrationWindow("call addPermissionForUserOnTable(?,?)", new String[]{"User name", "Table name"});
        }else if(event.getTarget() == deletePermission){
            showAdministrationWindow("call deletePermissionForUserOnTable(?,?)", new String[]{"User name", "Table name"});
        }
    }

    private void showCallFunctionWindow(String sql){
        AnchorPane pane;
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Main.class.getResource("/view/callFunction.fxml"));
        try {
            pane = (AnchorPane)loader.load();
            Stage stage= new Stage();
            stage.setTitle("Query");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(main.getPrimaryStage());

            Scene scene = new Scene(pane);
            stage.setScene(scene);

            FunctionWindowController controller = loader.getController();
            controller.setSql(sql);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void showCallFunctionWindow(String sql, String[] param){
        AnchorPane pane;
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Main.class.getResource("/view/callFunction.fxml"));
        try {
            pane = (AnchorPane)loader.load();
            Stage stage= new Stage();
            stage.setTitle("Query");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(main.getPrimaryStage());

            Scene scene = new Scene(pane);
            stage.setScene(scene);

            FunctionWindowController controller = loader.getController();
            controller.setSql(sql);
            controller.setParamesNames(param);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAdministrationWindow(String sql){
        AnchorPane pane;
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Main.class.getResource("/view/administrationLayout.fxml"));
        try {
            pane = (AnchorPane)loader.load();
            Stage stage= new Stage();
            stage.setTitle("Query");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(main.getPrimaryStage());

            Scene scene = new Scene(pane);
            stage.setScene(scene);

            AdministrationController controller = loader.getController();
            controller.setSql(sql);
            controller.setStage(stage);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void showAdministrationWindow(String sql, String[] param){
        AnchorPane pane;
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Main.class.getResource("/view/administrationLayout.fxml"));
        try {
            pane = (AnchorPane)loader.load();
            Stage stage= new Stage();
            stage.setTitle("Query");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(main.getPrimaryStage());

            Scene scene = new Scene(pane);
            stage.setScene(scene);

            AdministrationController controller = loader.getController();
            controller.setSql(sql);
            controller.setParamesNames(param);
            controller.setStage(stage);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void openQueryWindow(){
        AnchorPane pane;
        FXMLLoader loader = new FXMLLoader();

        loader.setLocation(Main.class.getResource("/view/query.fxml"));
        try {
            pane = (AnchorPane)loader.load();
            Stage stage= new Stage();
            stage.setTitle("Query");
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(main.getPrimaryStage());

            Scene scene = new Scene(pane);
            stage.setScene(scene);

            QueryWindowController controller = loader.getController();

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
