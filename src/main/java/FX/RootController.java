package FX;

import dao.daoImpl.*;
import entity.util.Tables;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.MenuItem;
import main.Main;
import net.sf.jasperreports.engine.JRException;
import reportGenerator.ReportGenerator;

import java.io.IOException;

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
    @FXML
    private MenuItem cityMedicalFacilityReport;
    @FXML
    private MenuItem laboratoryWorkloadReport;
    @FXML
    private MenuItem departmentsReport;

    @FXML
    private MenuItem firstTaskQuery,firstTaskQueryWithoutMF,secondTaskQuery,secondTaskQueryWithoutMF,thirdTaskQuery,thirdTaskQueryWithoutMF,fouthTaskQuery,fouthTaskQueryWithoutMF;
    @FXML
    private MenuItem fifthTaskQuery, fifthTaskQueryWithoutMF,sixTaskQuery,sixTaskQueryMF,sixTaskQueryDepartment,sixTaskQueryRoom,sevenTaskQuery,sevenTaskQueryDoctor;
    @FXML
    private MenuItem eightTask, nineTaskQuery,nineTaskQueryDepartment, nineTaskQueryFree,tenTaskQuery,tenTaskQueryDate,elevenTaskQuery,elevenTaskQueryMF,elevenTaskQuerySpecialrt;
    @FXML
    private MenuItem tvelweTaskQuery, tvelweTaskQueryMF,tvelweTaskQuerySpecialty,thirtynTaskQuerySpecialty,thirtynTaskQueryStaff,fouthinTaskQuery, fouthinTaskQueryLaboratory;


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
    private void taskQueryHandler(Event event){
        if(event.getTarget() ==firstTaskQuery){
            showCallFunctionWindow("call query1(?,?)", new String[]{"Specialty id","Medical Facility id"});
        }else if(event.getTarget() == firstTaskQueryWithoutMF){
            showCallFunctionWindow("call query1WithoutMedicalFacility(?)", new String[]{"Specialty id"});
        }else if(event.getTarget() == secondTaskQuery){
            showCallFunctionWindow("call query2(?,?)", new String[]{"Specialty id","Medicak Facility id"});
        }else if(event.getTarget()==secondTaskQueryWithoutMF){
            showCallFunctionWindow("call query2WithoutMedicalfacility(?)", new String[]{"Specialty id"});
        }else if(event.getTarget() == thirdTaskQuery){
            showCallFunctionWindow("call query3(?,?,?)", new String[]{"Specialty id", "Medical Facility id","Count of operations"});
        }else if(event.getTarget() == thirdTaskQueryWithoutMF){
            showCallFunctionWindow("call query3WithoutMedicalFAcility(?,?)", new String[]{"Specialty id", "Count of operations"});
        }else if(event.getTarget() == fouthTaskQuery){
            showCallFunctionWindow("call query4(?,?)", new String[]{"Specialty id","Medical Facility id"});
        }else if(event.getTarget() == fouthTaskQueryWithoutMF){
            showCallFunctionWindow("call query4WithoutMedicalFacility(?)", new String[]{"Specialty id"});
        }else if(event.getTarget()== fifthTaskQuery){
            showCallFunctionWindow("call query5(?,?)", new String[]{"Specialty id","Medical Facility id"});
        }else if(event.getTarget() == fifthTaskQueryWithoutMF){
            showCallFunctionWindow("call query5WithoutMedicalFacility(?)", new String[]{"Specialty id"});
        }else if(event.getTarget() == sixTaskQuery){
            showCallFunctionWindow("call query6()");
        }else if(event.getTarget()==sixTaskQueryMF){
            showCallFunctionWindow("call query6MedicalFacility(?)", new String[]{"Medical Facility id"});
        }else if(event.getTarget() == sixTaskQueryDepartment){
            showCallFunctionWindow("call query6Department(?)", new String[]{"Department id"});
        }else if(event.getTarget() == sixTaskQueryRoom){
            showCallFunctionWindow("call query6Room(?)", new String[]{"Room id"});
        }else if(event.getTarget() == sevenTaskQuery){
            showCallFunctionWindow("call query7(?,?,?)", new String[]{"Medical Facility id","First time","Second Time"});
        }else if(event.getTarget() == sevenTaskQueryDoctor){
            showCallFunctionWindow("call query7Doctor(?,?,?)", new String[]{"Doctor id","First Time","Second Time"});
        }else if(event.getTarget() == eightTask){
            showCallFunctionWindow("call query8(?,?)", new String[]{"Specialty id","Medical Facility id"});
        }else if(event.getTarget() == nineTaskQuery){
            showCallFunctionWindow("call query9(?)", new String[]{"Medical Facility id"});
        }else if(event.getTarget() == nineTaskQueryDepartment){
            showCallFunctionWindow("call query9GroupByDepartment(?)", new String[]{"Medical Facility id"});
        }else if(event.getTarget() == nineTaskQueryFree){
            showCallFunctionWindow("call query9GroupByDepartmentAndFree(?)", new String[]{"Medical Facility id"});
        }else if(event.getTarget() == tenTaskQuery){
            showCallFunctionWindow("call query10(?)", new String[]{"Medical Facility id"});
        }else if(event.getTarget() == tenTaskQueryDate){
            showCallFunctionWindow("call query10WithDate(?,?)", new String[]{"firstDate","secondDate"});
        }else if(event.getTarget() == elevenTaskQuery){
            showCallFunctionWindow("call query11(?,?,?)", new String[]{"Doctor id","First Date","Second Date"});
        }else if(event.getTarget() == elevenTaskQueryMF){
            showCallFunctionWindow("call query11MedicalFacility(?,?,?)", new String[]{"Medical Facility id","First Date","Second Date"});
        }else if(event.getTarget() == elevenTaskQuerySpecialrt){
            showCallFunctionWindow("call query11Specialty(?,?,?)", new String[]{"Specialty id","First Date","Second Date"});
        }else if(event.getTarget() == tvelweTaskQuery){
            showCallFunctionWindow("call query12(?)", new String[]{"Doctor id"});
        }else if(event.getTarget() == tvelweTaskQueryMF){
            showCallFunctionWindow("call query12MedicalFacility(?)", new String[]{"Medical facility id"});
        }else if(event.getTarget() == tvelweTaskQuerySpecialty){
            showCallFunctionWindow("call query12Specialty(?)", new String[]{"Specialty id"});
        }else if(event.getTarget() == thirtynTaskQuerySpecialty){
            showCallFunctionWindow("call query13Specialty(?,?)",new String[]{"Medical Facility id","Time"});
        }else if(event.getTarget() == thirtynTaskQueryStaff){
            showCallFunctionWindow("call query13Staff(?,?)",new String[]{"Staff id","Time"});
        }else if(event.getTarget() == fouthinTaskQuery){
            showCallFunctionWindow("call query14(?,?,?,?)", new String[]{"Medical Facility id","Laboratory id","First date","Second date"});
        }else if(event.getTarget() ==fouthinTaskQueryLaboratory){
            showCallFunctionWindow("call query14Laboratory(?,?,?)", new String[]{"Laboratory id","First date","Second date"});
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

    @FXML
    private void reportHandler(Event event){
        if(event.getTarget() == cityMedicalFacilityReport){
            try {
                ReportGenerator rg = new ReportGenerator();
                rg.setREPORT_pattern("src/main/resources/jrxml/medicalFacility.jrxml");
                rg.create();
            } catch (JRException e) {
                e.printStackTrace();
            }
        }else if(event.getTarget() == laboratoryWorkloadReport)
        {
            try {
               ReportGenerator rg = new ReportGenerator();
                rg.setREPORT_pattern("src/main/resources/jrxml/SecondReport.jrxml");
                rg.create();

            } catch (JRException e) {
                e.printStackTrace();
            }
        }else if(event.getTarget() == departmentsReport)
        {
            try {
                ReportGenerator rg = new ReportGenerator();
                rg.setREPORT_pattern("src/main/resources/jrxml/ThirdReport.jrxml");
                rg.create();

            } catch (JRException e) {
                e.printStackTrace();
            }
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
            stage.initOwner(main.getStage());

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
            stage.initOwner(main.getStage());

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
            stage.initOwner(main.getStage());

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
            stage.initOwner(main.getStage());

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
            stage.initOwner(main.getStage());

            Scene scene = new Scene(pane);
            stage.setScene(scene);

            QueryWindowController controller = loader.getController();

            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
