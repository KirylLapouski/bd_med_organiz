package FX;

import dao.daoImpl.*;
import entity.AnalysisEntity;
import entity.StaffEntity;
import entity.util.Tables;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import main.Main;

import java.io.File;
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

    private static ObservableList<Tables> list =  FXCollections.observableArrayList();

    public StaffController getStaffController() {
        return staffController;
    }

    public static void setStaffController(StaffController staffController) {
        RootController.staffController = staffController;
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
}
