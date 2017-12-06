package FX;

import dao.CrudDao;
import dao.daoImpl.StaffDao;
import entity.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lapko on 17.11.2017.
 */
public class StaffController {
    public StaffController() {
    }

    @FXML
    private TableView personTable = new TableView();
    @FXML
    private TableColumn idColumn;
    @FXML
    private TableColumn lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label ThirdNameLabel;
    @FXML
    private Label FouthLabel;
    @FXML
    private Label FifthLabel;
    @FXML
    private Label sixLabel;

    @FXML
    private Label FirstStaticLabel;
    @FXML
    private Label SecondStaticLabel;
    @FXML
    private Label ThirdStaticLabel;
    @FXML
    private Label FouthStaticLabel;
    @FXML
    private Label FifthStaticLabel;
    @FXML
    private Label SixStaticLabel;


    private CrudDao dao;
    private Main mainApp;
    private static ObservableList<Object> staff = FXCollections.observableArrayList();




    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("fio"));


        showStaffDetails(null);
        personTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showStaffDetails(newValue));
    }



    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;

        personTable.setItems(staff);

        dao  = new StaffDao(mainApp.getOurSessionFactory());
        staff.addAll(dao.list());
        personTable.setItems(staff);
    }

    @FXML
    private void deleteButtonHandler(){
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0) {


            if(staff.get(0) instanceof StaffEntity) {
                StaffEntity staffEntity = (StaffEntity) personTable.getItems().remove(selectedIndex);
                dao.delete(staffEntity.getId());
            }else if(staff.get(0) instanceof AnalysisEntity){
                AnalysisEntity analysisEntity = (AnalysisEntity) personTable.getItems().remove(selectedIndex);
                dao.delete(analysisEntity.getId());
            }else if(staff.get(0) instanceof DepartmentEntity){
                DepartmentEntity departmentEntity = (DepartmentEntity) personTable.getItems().remove(selectedIndex);
                dao.delete(departmentEntity.getId());
            }else if(staff.get(0) instanceof DiseaseEntity){
                DiseaseEntity diseaseEntity = (DiseaseEntity) personTable.getItems().remove(selectedIndex);
                dao.delete(diseaseEntity.getId());
            }else if(staff.get(0) instanceof HousingEntity){
                HousingEntity housingEntity = (HousingEntity) personTable.getItems().remove(selectedIndex);
                dao.delete(housingEntity.getId());
            }else if(staff.get(0) instanceof LaboratoryEntity){
                LaboratoryEntity laboratoryEntity = (LaboratoryEntity) personTable.getItems().remove(selectedIndex);
                dao.delete(laboratoryEntity.getId());
            }else if(staff.get(0) instanceof LaboratorySpecEntity){
                LaboratorySpecEntity laboratorySpecEntity = (LaboratorySpecEntity) personTable.getItems().remove(selectedIndex);
                dao.delete(laboratorySpecEntity.getId());
            }else if(staff.get(0) instanceof MedicalFacilityEntity) {
                MedicalFacilityEntity medicalFacilityEntity = (MedicalFacilityEntity) personTable.getItems().remove(selectedIndex);
                dao.delete(medicalFacilityEntity.getId());
            }else if(staff.get(0) instanceof OccupiedBedsEntity){
                OccupiedBedsEntity occupiedBedsEntity = (OccupiedBedsEntity) personTable.getItems().remove(selectedIndex);
                dao.delete(occupiedBedsEntity.getId());
            }else if(staff.get(0) instanceof OfficeEntity){
                OfficeEntity officeEntity = (OfficeEntity) personTable.getItems().remove(selectedIndex);
                dao.delete(officeEntity.getId());
            }else if(staff.get(0) instanceof PatienceEntity){
                PatienceEntity patienceEntity = (PatienceEntity) personTable.getItems().remove(selectedIndex);
                dao.delete(patienceEntity.getId());
            }else if(staff.get(0) instanceof PositionEntity){
                PositionEntity positionEntity = (PositionEntity) personTable.getItems().remove(selectedIndex);
                dao.delete(positionEntity.getId());
            }else if(staff.get(0) instanceof RoomEntity){
                RoomEntity roomEntity = (RoomEntity) personTable.getItems().remove(selectedIndex);
                dao.delete(roomEntity.getId());
            }else if(staff.get(0) instanceof SpecialtyEntity){
                SpecialtyEntity specialtyEntity = (SpecialtyEntity) personTable.getItems().remove(selectedIndex);
                dao.delete(specialtyEntity.getId());
            }else if(staff.get(0) instanceof TypeOfAnalysisEntity){
                TypeOfAnalysisEntity typeOfAnalysisEntity = (TypeOfAnalysisEntity) personTable.getItems().remove(selectedIndex);
                dao.delete(typeOfAnalysisEntity.getId());
            }


            staff.clear();
            staff.addAll(dao.list());
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No selection");
            alert.setHeaderText("No item selected");
            alert.setContentText("Please select a item in the table");

            alert.showAndWait();
        }
    }

    @FXML
    private void handleNewStaff() {
        try {
            Class classs = staff.get(0).getClass();
            Object newEntity = classs.newInstance();

            if(newEntity instanceof StaffEntity) {
                ((StaffEntity)newEntity).setId(0);
                ((StaffEntity)newEntity).setFio("FirstName LastName ThirdName");
            }else if(newEntity instanceof AnalysisEntity){
                ((AnalysisEntity)newEntity).setId(0);
            }else if(newEntity instanceof DepartmentEntity){
                ((DepartmentEntity)newEntity).setId(0);
            }else if(newEntity instanceof DiseaseEntity){
                ((DiseaseEntity)newEntity).setId(0);
            }else if(newEntity instanceof HousingEntity){
                ((HousingEntity)newEntity).setId(0);
            }else if(newEntity instanceof LaboratoryEntity){
                ((LaboratoryEntity)newEntity).setId(0);
            }else if(newEntity instanceof LaboratorySpecEntity){
                ((LaboratorySpecEntity)newEntity).setId(0);
            }else if(newEntity instanceof MedicalFacilityEntity) {
                ((MedicalFacilityEntity)newEntity).setId(0);
            }else if(newEntity instanceof OccupiedBedsEntity){
                ((OccupiedBedsEntity)newEntity).setId(0);
            }else if(newEntity instanceof OfficeEntity){
                ((OfficeEntity)newEntity).setId(0);
            }else if(newEntity instanceof PatienceEntity){
                ((PatienceEntity)newEntity).setId(0);
            }else if(newEntity instanceof PositionEntity){
                ((PositionEntity)newEntity).setId(0);
            }else if(newEntity instanceof RoomEntity){
                ((RoomEntity)newEntity).setId(0);
            }else if(newEntity instanceof SpecialtyEntity){
                ((SpecialtyEntity)newEntity).setId(0);
            }else if(newEntity instanceof TypeOfAnalysisEntity){
                ((TypeOfAnalysisEntity)newEntity).setId(0);
            }


            boolean okClicked = showPersonNewDialog(newEntity);
            if (okClicked) {
                staff.clear();
                dao.create(newEntity);
                staff.addAll(dao.list());
            }

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }


    @FXML
    private void handleEditStaff() {
        Class classs = staff.get(1).getClass();
        Object selectedEntity = null;
        selectedEntity = classs.cast(personTable.getSelectionModel().getSelectedItem());
        if (selectedEntity != null) {
            List<String> str = new ArrayList<>();


            if(selectedEntity instanceof StaffEntity) {
                str.add(((StaffEntity) selectedEntity).getFio().split(" ")[0]);
                str.add(((StaffEntity) selectedEntity).getFio().split(" ")[1]);
                str.add(((StaffEntity) selectedEntity).getFio().split(" ")[2]);
            }else if(selectedEntity instanceof AnalysisEntity){
                str.add(String.valueOf(((AnalysisEntity) selectedEntity).getTypeOfAnalys().getId()));
                str.add(String.valueOf(((AnalysisEntity) selectedEntity).getPatience().getId()));
            }else if(selectedEntity instanceof DepartmentEntity){
                str.add(((DepartmentEntity) selectedEntity).getName());
                str.add(String.valueOf(((DepartmentEntity) selectedEntity).getHousing().getId()));
            }else if(selectedEntity instanceof DiseaseEntity){
                str.add(((DiseaseEntity) selectedEntity).getName());
            }else if(selectedEntity instanceof HousingEntity){
                str.add(((HousingEntity) selectedEntity).getName());
                str.add(((HousingEntity) selectedEntity).getAddress());
                str.add(String.valueOf(((HousingEntity) selectedEntity).getMedicalFacility().getId()));
            }else if(selectedEntity instanceof LaboratoryEntity){
                str.add(((LaboratoryEntity) selectedEntity).getName());
                str.add(((LaboratoryEntity) selectedEntity).getAddress());
            }else if(selectedEntity instanceof LaboratorySpecEntity){
               str.add(((LaboratorySpecEntity) selectedEntity).getName());
            }else if(selectedEntity instanceof MedicalFacilityEntity) {
                str.add(((MedicalFacilityEntity) selectedEntity).getName());
                str.add(((MedicalFacilityEntity) selectedEntity).getAddress());
                str.add(((MedicalFacilityEntity) selectedEntity).getMedicalFacilityType());
                str.add(String.valueOf(((MedicalFacilityEntity) selectedEntity).getOrderDoctor().getId()));
            }else if(selectedEntity instanceof OccupiedBedsEntity){
                str.add(String.valueOf(((OccupiedBedsEntity) selectedEntity).getRoom().getId()));
                str.add(String.valueOf(((OccupiedBedsEntity) selectedEntity).getSince()));
                str.add(String.valueOf(((OccupiedBedsEntity) selectedEntity).getTo()));
            }else if(selectedEntity instanceof OfficeEntity) {
                str.add(String.valueOf(((OfficeEntity) selectedEntity).getDepartment().getId()));
                str.add(String.valueOf(((OfficeEntity) selectedEntity).getDoctor().getId()));
            }else if(selectedEntity instanceof PatienceEntity){
                str.add(((PatienceEntity) selectedEntity).getFio().split(" ")[0]);
                str.add(((PatienceEntity) selectedEntity).getFio().split(" ")[1]);
                str.add(((PatienceEntity) selectedEntity).getFio().split(" ")[2]);
            }else if(selectedEntity instanceof PositionEntity){
                str.add(((PositionEntity) selectedEntity).getName());
            }else if(selectedEntity instanceof RoomEntity) {
                str.add(String.valueOf(((RoomEntity) selectedEntity).getRoomNumber()));
                str.add(((RoomEntity) selectedEntity).getNumberOfBeds().toString());
                str.add(String.valueOf(((RoomEntity) selectedEntity).getDepartment().getId()));
                str.add(String.valueOf(((RoomEntity) selectedEntity).getResponsibleDoctor().getId()));
            }else if(selectedEntity instanceof SpecialtyEntity) {
                str.add(((SpecialtyEntity) selectedEntity).getName());
                str.add(String.valueOf(((SpecialtyEntity) selectedEntity).getIsDoctor()));
                str.add(String.valueOf(((SpecialtyEntity) selectedEntity).getSalary()));
                str.add(((SpecialtyEntity) selectedEntity).getDegree());
                str.add(((SpecialtyEntity) selectedEntity).getGrade());
            }else if(selectedEntity instanceof TypeOfAnalysisEntity){
               str.add(((TypeOfAnalysisEntity) selectedEntity).getName());
            }

            String[] values=  new String[str.size()];
            for(int i=0;i< str.size();i++)
                values[i] =  str.get(i);


            boolean okClicked = showPersonEditDialog(selectedEntity,values);

            if (okClicked) {
                showStaffDetails(selectedEntity);

                if(selectedEntity instanceof StaffEntity) {
                    dao.update((StaffEntity)selectedEntity);

                    staff.clear();
                    staff.addAll(dao.list());
                }else if(selectedEntity instanceof AnalysisEntity){
                    dao.update((AnalysisEntity) selectedEntity);

                    staff.clear();
                    staff.addAll(dao.list());
                }else if(selectedEntity instanceof DepartmentEntity){
                    dao.update((DepartmentEntity) selectedEntity);

                    staff.clear();
                    staff.addAll(dao.list());
                }else if(selectedEntity instanceof DiseaseEntity){
                    dao.update((DiseaseEntity) selectedEntity);

                    staff.clear();
                    staff.addAll(dao.list());
                }else if(selectedEntity instanceof HousingEntity){
                    dao.update((HousingEntity) selectedEntity);

                    staff.clear();
                    staff.addAll(dao.list());
                }else if(selectedEntity instanceof LaboratoryEntity){
                    dao.update((LaboratoryEntity) selectedEntity);

                    staff.clear();
                    staff.addAll(dao.list());
                }else if(selectedEntity instanceof LaboratorySpecEntity){
                    dao.update((LaboratorySpecEntity) selectedEntity);

                    staff.clear();
                    staff.addAll(dao.list());
                }else if(selectedEntity instanceof MedicalFacilityEntity) {
                    dao.update((MedicalFacilityEntity) selectedEntity);

                    staff.clear();
                    staff.addAll(dao.list());
                }else if(selectedEntity instanceof OccupiedBedsEntity){
                    dao.update((OccupiedBedsEntity) selectedEntity);

                    staff.clear();
                    staff.addAll(dao.list());
                }else if(selectedEntity instanceof OfficeEntity){
                    dao.update((OfficeEntity) selectedEntity);

                    staff.clear();
                    staff.addAll(dao.list());
                }else if(selectedEntity instanceof PatienceEntity){
                    dao.update((PatienceEntity) selectedEntity);

                    staff.clear();
                    staff.addAll(dao.list());
                }else if(selectedEntity instanceof PositionEntity){
                    dao.update((PositionEntity) selectedEntity);

                    staff.clear();
                    staff.addAll(dao.list());
                }else if(selectedEntity instanceof RoomEntity){
                     dao.update((RoomEntity) selectedEntity);

                    staff.clear();
                    staff.addAll(dao.list());
                }else if(selectedEntity instanceof SpecialtyEntity){
                    dao.update((SpecialtyEntity) selectedEntity);

                    staff.clear();
                    staff.addAll(dao.list());
                }else if(selectedEntity instanceof TypeOfAnalysisEntity){
                    dao.update((TypeOfAnalysisEntity) selectedEntity);

                    staff.clear();
                    staff.addAll(dao.list());
                }
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Staff Selected");
            alert.setContentText("Please select a object in the table.");

            alert.showAndWait();
        }
    }


    public boolean showPersonEditDialog(Object staffEntity, String[] values) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/staffDialogDetails.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainApp.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            StaffEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setStaff(staffEntity);
            controller.setTextFieldValues(values);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showPersonNewDialog(Object staffEntity) {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/staffDialogDetails.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainApp.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            StaffEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setStaff(staffEntity);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
    private void showStaffDetails(Object entity){

        if(entity  == null){
            setLabeksText("", "", "", "", "", "");
        } else if(entity instanceof StaffEntity) {
            StaffEntity staffEntity = StaffEntity.class.cast(entity);
            setLabeksText(staffEntity.getId().toString(),staffEntity.getFio().split(" ")[0], staffEntity.getFio().split(" ")[1], staffEntity.getFio().split(" ")[2], "", "");
        }else if(entity instanceof AnalysisEntity) {
            AnalysisEntity analysisEntity = AnalysisEntity.class.cast(entity);
            setLabeksText(String.valueOf(analysisEntity.getId()),analysisEntity.getTypeOfAnalys().getName() , String.valueOf(analysisEntity.getPatience().getId()), "", "", "");
        }else if(staff.get(0) instanceof DepartmentEntity){
            DepartmentEntity departmentEntity = DepartmentEntity.class.cast(entity);
            setLabeksText(String.valueOf(departmentEntity.getId()), departmentEntity.getName(), String.valueOf(departmentEntity.getHousing().getId()), "", "", "");
        }else if(staff.get(0) instanceof DiseaseEntity){
            DiseaseEntity diseaseEntity = DiseaseEntity.class.cast(entity);
            setLabeksText(String.valueOf(diseaseEntity.getId()), diseaseEntity.getName(),"", "", "", "");
        }else if(staff.get(0) instanceof HousingEntity){
            HousingEntity housingEntity = HousingEntity.class.cast(entity);
            setLabeksText(String.valueOf(housingEntity.getId()), housingEntity.getName(), housingEntity.getAddress(), String.valueOf(housingEntity.getMedicalFacility().getId()), "", "");
        }else if(staff.get(0) instanceof LaboratoryEntity){
            LaboratoryEntity laboratoryEntity = LaboratoryEntity.class.cast(entity);
            setLabeksText(String.valueOf(laboratoryEntity.getId()), laboratoryEntity.getName(), laboratoryEntity.getAddress(), "", "", "");
        }else if(staff.get(0) instanceof LaboratorySpecEntity){
            LaboratorySpecEntity laboratorySpecEntity = LaboratorySpecEntity.class.cast(entity);
            setLabeksText(String.valueOf(laboratorySpecEntity.getId()), laboratorySpecEntity.getName(), "", "", "", "");
        }else if(staff.get(0) instanceof MedicalFacilityEntity) {
            MedicalFacilityEntity medicalFacilityEntity = MedicalFacilityEntity.class.cast(entity);
            setLabeksText(String.valueOf(medicalFacilityEntity.getId()), medicalFacilityEntity.getName(), medicalFacilityEntity.getAddress(), medicalFacilityEntity.getMedicalFacilityType(), String.valueOf(medicalFacilityEntity.getOrderDoctor().getId()), "");
        }else if(staff.get(0) instanceof OccupiedBedsEntity){
            OccupiedBedsEntity occupiedBedsEntity = OccupiedBedsEntity.class.cast(entity);
            setLabeksText(String.valueOf(occupiedBedsEntity.getId()), String.valueOf(occupiedBedsEntity.getRoom().getId()), String.valueOf(occupiedBedsEntity.getSince())
                    , String.valueOf(occupiedBedsEntity.getTo()), "", "");
        }else if(staff.get(0) instanceof OfficeEntity){
            OfficeEntity officeEntity = OfficeEntity.class.cast(entity);
            setLabeksText(String.valueOf(officeEntity.getId()), String.valueOf(officeEntity.getDepartment().getId()), String.valueOf(officeEntity.getDoctor().getId()), "", "", "");
        }else if(staff.get(0) instanceof PatienceEntity){
            PatienceEntity patienceEntity = PatienceEntity.class.cast(entity);
            setLabeksText(String.valueOf(patienceEntity.getId()), patienceEntity.getFio().split(" ")[0], patienceEntity.getFio().split(" ")[1], patienceEntity.getFio().split(" ")[2], "", "");
        }else if(staff.get(0) instanceof PositionEntity){
            PositionEntity positionEntity = PositionEntity.class.cast(entity);
            setLabeksText(String.valueOf(positionEntity.getId()), positionEntity.getName(), "", "", "", "");
        }else if(staff.get(0) instanceof RoomEntity){
            RoomEntity roomEntity = RoomEntity.class.cast(entity);
            setLabeksText(String.valueOf(roomEntity.getId()), String.valueOf(roomEntity.getRoomNumber()), String.valueOf(roomEntity.getNumberOfBeds()),
                    String.valueOf(roomEntity.getDepartment().getId()), String.valueOf(roomEntity.getResponsibleDoctor().getId()), "");
        }else if(staff.get(0) instanceof SpecialtyEntity){
            SpecialtyEntity specialtyEntity = SpecialtyEntity.class.cast(entity);
            setLabeksText(String.valueOf(specialtyEntity.getId()), specialtyEntity.getName(), String.valueOf(specialtyEntity.getIsDoctor()), String.valueOf(specialtyEntity.getSalary()), specialtyEntity.getDegree(), specialtyEntity.getGrade());
        }else if(staff.get(0) instanceof TypeOfAnalysisEntity){
            TypeOfAnalysisEntity typeOfAnalysisEntity = TypeOfAnalysisEntity.class.cast(entity);
            setLabeksText(String.valueOf(typeOfAnalysisEntity.getId()), typeOfAnalysisEntity.getName(), "", "", "", "");
        }

    }
/*    private void showAnalusisDetails(AnalysisEntity analysisEntity){
        if(analysisEntity!= null){
            setLabeksText(analysisEntity.getTypeOfAnalys().getName(),String.valueOf(analysisEntity.getId()),"","","","");

        }else{
            setLabeksText("","","","","","");
        }
    }*/



    public void setAnalysisColumns(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        lastNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Object, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures<Object, String> param) {
                return new ReadOnlyObjectWrapper(((AnalysisEntity)param.getValue()).getTypeOfAnalys().getId());
            }
        });
        idColumn.setText("Id");
        lastNameColumn.setText("Id analysis type");

        setStaticLabeksText("id", "Id analysis type", "id patience", "", "", "");
    }
    public void setStaffColumns(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("fio"));
        idColumn.setText("Id");
        lastNameColumn.setText("Fio");

        setStaticLabeksText("id","First name","Second name","Patronym","","");
    }
    public void setDepartmentColumn(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setText("Id");
        lastNameColumn.setText("Name");

        setStaticLabeksText("id","name","housing id","","","");
    }
    public void setDiseaseColumn(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setText("Id");
        lastNameColumn.setText("Name");

        setStaticLabeksText("id","name","","","","");
    }
    public void setHousingColumn(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setText("Id");
        lastNameColumn.setText("Name");

        setStaticLabeksText("id","name","address","medical facility id","","");
    }
    public void setLaboratoryColumn(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setText("Id");
        lastNameColumn.setText("Name");

        setStaticLabeksText("id","name","address","","","");
    }
    public void setLaboratorySpecColumn(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setText("Id");
        lastNameColumn.setText("Name");

        setStaticLabeksText("id","name","","","","");
    }
    public void setMedicalFacilityColumn(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setText("Id");
        lastNameColumn.setText("Name");

        setStaticLabeksText("id","name","address","type","order doctor","");
    }
    public void setOccupiedBedsColumn(){//TODO
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        lastNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures param) {
                return new ReadOnlyObjectWrapper (String.valueOf(((OccupiedBedsEntity) param.getValue()).getRoom().getId()));
            }
        });
        idColumn.setText("Id");
        lastNameColumn.setText("Id room");

        setStaticLabeksText("id","id room","since","to","","");
    }
    public void setOfficeColumn(){//TODO
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        lastNameColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures, ObservableValue>() {
            @Override
            public ObservableValue call(TableColumn.CellDataFeatures param) {
                return  new ReadOnlyObjectWrapper (((OfficeEntity)param.getValue()).getDepartment().getId());
            }
        });
        idColumn.setText("Id");
        lastNameColumn.setText("Id department");

        setStaticLabeksText("id","id department","id doctor","","","");
    }
    public void setPatienceColumn(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("fio"));
        idColumn.setText("Id");
        lastNameColumn.setText("Fio");

        setStaticLabeksText("id","first name","last name","Patronym","","");
    }
    public void setPositionColumn(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setText("Id");
        lastNameColumn.setText("Name");

        setStaticLabeksText("id","name","","","","");
    }
    public void setRoomColumn(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        idColumn.setText("Id");
        lastNameColumn.setText("Room number");

        setStaticLabeksText("id","roomNumber","number of beds","id department","id doctor","");
    }
    public void setSpecialtyColumn(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setText("Id");
        lastNameColumn.setText("Name");

        setStaticLabeksText("id","name","Doctor","salary","degree","grade");
    }
    public void setTypeOfAnalysisColumn(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumn.setText("Id");
        lastNameColumn.setText("Name");

        setStaticLabeksText("id","name","","","","");
    }


    private void setStaticLabeksText(String first,String second,String third, String fouth,String fifth, String six){
        FirstStaticLabel.setText(first);
        SecondStaticLabel.setText(second);
        ThirdStaticLabel.setText(third);
        FouthStaticLabel.setText(fouth);
        FifthStaticLabel.setText(fifth);
        SixStaticLabel.setText(six);
    }

    private void setLabeksText(String first,String second,String third, String fouth,String fifth, String six) {
        firstNameLabel.setText(first);
        lastNameLabel.setText(second);
        ThirdNameLabel.setText(third);
        FouthLabel.setText(fouth);
        FifthLabel.setText(fifth);
        sixLabel.setText(six);
    }

    public static ObservableList<Object> getStaff() {
        return staff;
    }

    public void setDao(CrudDao dao) {
        this.dao = dao;
    }

    public static void setStaff(Object[] staff) {
        StaffController.getStaff().clear();
        StaffController.staff.addAll(staff);
    }
}
