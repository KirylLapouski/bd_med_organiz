package FX;

import dao.daoImpl.*;
import entity.*;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;

import javax.xml.soap.Text;
import java.util.Date;

/**
 * Created by lapko on 22.11.2017.
 */
public class StaffEditDialogController {
    @FXML
    private TextField firstField;
    @FXML
    private TextField lastField;
    @FXML
    private TextField thirdField;
    @FXML
    private TextField fouthField;
    @FXML
    private TextField fifthField;
    @FXML
    private Label firstLabel;
    @FXML
    private Label secondLabel;
    @FXML
    private Label thirdLabel;
    @FXML
    private Label fouthLabel;
    @FXML
    private Label fifthLabel;
    @FXML
    private ComboBox<String> firstcomboBox;
    @FXML
    private ComboBox<String> secondComboBox;
    @FXML
    private ComboBox<String> thirdCombobox;

    private Stage dialogStage;
    private Object entity;
    private boolean okClicked = false;


    @FXML
    private void initialize() {


    }


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    public void setStaff(Object staff) {
        this.entity = staff;

        if(entity instanceof StaffEntity){
            setLabeksText("First name","Last name","Patronymic","","");
        }else if(entity instanceof AnalysisEntity){
            setLabeksText("Id type of analysis","Id patience","","","");
        }else if(entity instanceof DepartmentEntity){
            setLabeksText("Name", "Housing id", "","","");
        }else if(entity instanceof DiseaseEntity){
             setLabeksText("Name", "", "","","");
        }else if(entity instanceof HousingEntity){
             setLabeksText("Name", "Address", "Medical facility id","","");
        }else if(entity instanceof LaboratoryEntity){
            setLabeksText("Name", "Address", "","","");
        }else if(entity instanceof LaboratorySpecEntity){
            setLabeksText("Name", "", "","","");
        }else if(entity instanceof MedicalFacilityEntity) {
            setLabeksText("Name", "Address", "Type", "Order doctor","");

            showFirstComboBox();

        }else if(entity instanceof OccupiedBedsEntity){
            setLabeksText("Id room", "Since", "To","","");
        }else if(entity instanceof OfficeEntity){
            setLabeksText("Id department", "Id doctor", "","","");
        }else if(entity instanceof PatienceEntity){
            setLabeksText("First name", "Last name", "Patronym","","");
        }else if(entity instanceof PositionEntity){
            setLabeksText("Name", "", "","","");
        }else if(entity instanceof RoomEntity){
            setLabeksText("Room number", "Number of beds", "Id department", "Id doctor","");
        }else if(entity instanceof SpecialtyEntity){
            setLabeksText("Name", "Doctor", "Salary", "Degree", "Grade");

            showSecondComboBox();
            showThirdComboBox();
        }else if(entity instanceof TypeOfAnalysisEntity){
            setLabeksText("Name", "", "","","");
        }



    }

    public boolean isOkClicked() {
        return okClicked;
    }


    @FXML
    private void handleOk() {
        if (isInputValid()) {
            if(entity instanceof StaffEntity) {

                StaffEntity staffEntity = StaffEntity.class.cast(entity);
                staffEntity.setFio(firstField.getText() + " " + lastField.getText() + " " + thirdField.getText());

            }else if(entity instanceof AnalysisEntity){

                AnalysisEntity analysisEntity = AnalysisEntity.class.cast(entity);
                TypeOfAnalysisDao typeOfAnalysisDao = new TypeOfAnalysisDao(Main.getOurSessionFactory());

                TypeOfAnalysisEntity typeOfAnalysisEntity =  typeOfAnalysisDao.read(Integer.parseInt(firstField.getText()), TypeOfAnalysisEntity.class);
                analysisEntity.setTypeOfAnalys(typeOfAnalysisEntity);

                PatienceDao patienceDao  =new PatienceDao(Main.getOurSessionFactory());
                analysisEntity.setPatience(patienceDao.read(Integer.valueOf(lastField.getText()), PatienceEntity.class));

            }else if(entity instanceof DepartmentEntity){

                DepartmentEntity departmentEntity = DepartmentEntity.class.cast(entity);
                departmentEntity.setName(firstField.getText());

                HousingDao housingDao = new HousingDao(Main.getOurSessionFactory());
                departmentEntity.setHousing( housingDao.read(Integer.valueOf(lastField.getText()),HousingEntity.class));
            }else if(entity instanceof DiseaseEntity){
                DiseaseEntity diseaseEntity = DiseaseEntity.class.cast(entity);
                diseaseEntity.setName(firstField.getText());
            }else if(entity instanceof HousingEntity){
                HousingEntity housingEntity = HousingEntity.class.cast(entity);
                housingEntity.setName(firstField.getText());
                housingEntity.setAddress(lastField.getText());

                MedicalFacilityDao dao = new MedicalFacilityDao(Main.getOurSessionFactory());
                housingEntity.setMedicalFacility(dao.read(Integer.valueOf(thirdField.getText()),MedicalFacilityEntity.class));
            }else if(entity instanceof LaboratoryEntity){
               LaboratoryEntity laboratoryEntity = LaboratoryEntity.class.cast(entity);
                laboratoryEntity.setName(firstField.getText());
                laboratoryEntity.setAddress(lastField.getText());
            }else if(entity instanceof LaboratorySpecEntity){
                LaboratorySpecEntity laboratorySpecEntity = LaboratorySpecEntity.class.cast(entity);
                laboratorySpecEntity.setName(firstField.getText());
            }else if(entity instanceof MedicalFacilityEntity) {
                MedicalFacilityEntity medicalFacilityEntity  = MedicalFacilityEntity.class.cast(entity);
                medicalFacilityEntity.setName(firstField.getText());
                medicalFacilityEntity.setAddress(lastField.getText());
                medicalFacilityEntity.setMedicalFacilityType(thirdField.getText());

                StaffDao dao = new StaffDao(Main.getOurSessionFactory());
                medicalFacilityEntity.setOrderDoctor(dao.read(Integer.valueOf(fouthField.getText()),StaffEntity.class));
            }else if(entity instanceof OccupiedBedsEntity){
                OccupiedBedsEntity occupiedBedsEntity = OccupiedBedsEntity.class.cast(entity);

                RoomDao dao = new RoomDao(Main.getOurSessionFactory());
                occupiedBedsEntity.setRoom(dao.read(Integer.valueOf(firstField.getText()),RoomEntity.class));

                occupiedBedsEntity.setSince(new Date(lastField.getText()));
                occupiedBedsEntity.setTo(new Date(thirdField.getText()));
            }else if(entity instanceof OfficeEntity){
               OfficeEntity officeEntity = OfficeEntity.class.cast(entity);

                DepartmentDao  dao = new DepartmentDao(Main.getOurSessionFactory());
                officeEntity.setDepartment(dao.read(Integer.valueOf(firstField.getText()),DepartmentEntity.class));

                StaffDao staffDao = new StaffDao(Main.getOurSessionFactory());
                officeEntity.setDoctor(staffDao.read(Integer.valueOf(lastField.getText()),StaffEntity.class));
            }else if(entity instanceof PatienceEntity){
                PatienceEntity patienceEntity = PatienceEntity.class.cast(entity);

                patienceEntity.setFio(firstField.getText()+" "+lastField.getText()+" "+ thirdField.getText());
            }else if(entity instanceof PositionEntity){
                PositionEntity positionEntity = PositionEntity.class.cast(entity);

                positionEntity.setName(firstField.getText());
            }else if(entity instanceof RoomEntity){
              RoomEntity roomEntity = RoomEntity.class.cast(entity);
                roomEntity.setRoomNumber(Integer.parseInt(firstField.getText()));
                roomEntity.setNumberOfBeds(Integer.valueOf(lastField.getText()));

                DepartmentDao departmentDao = new DepartmentDao(Main.getOurSessionFactory());
                roomEntity.setDepartment(departmentDao.read(Integer.valueOf(thirdField.getText()), DepartmentEntity.class));

                StaffDao staffDao = new StaffDao(Main.getOurSessionFactory());
                roomEntity.setResponsibleDoctor(staffDao.read(Integer.valueOf(fouthField.getText()),StaffEntity.class));
            }else if(entity instanceof SpecialtyEntity){
                SpecialtyEntity specialtyEntity = SpecialtyEntity.class.cast(entity);

                specialtyEntity.setName(firstField.getText());
                specialtyEntity.setIsDoctor(Boolean.valueOf(lastField.getText()));
                specialtyEntity.setSalary(Float.parseFloat(thirdField.getText()));
                specialtyEntity.setDegree(fouthField.getText());
                specialtyEntity.setGrade(fifthField.getText());

            }else if(entity instanceof TypeOfAnalysisEntity){
                TypeOfAnalysisEntity typeOfAnalysisEntity = TypeOfAnalysisEntity.class.cast(entity);
                typeOfAnalysisEntity.setName(firstField.getText());
            }

            okClicked = true;
            dialogStage.close();
        }
    }


    @FXML
    private void handleCancel() {
        dialogStage.close();
    }


    private boolean isInputValid() {
        String errorMessage = "";

        String str  = firstField.getText();
        if (firstField.disableProperty().getValue()==false && (firstField.getText() == null || firstField.getText().length() == 0))
            errorMessage += "No valid first field!\n";

        if (lastField.disableProperty().getValue()==false && (lastField.getText() == null || lastField.getText().length() == 0))
            errorMessage += "No valid second field!\n";

        if (thirdField.disableProperty().getValue()==false && (thirdField.getText() == null || thirdField.getText().length() == 0))
            errorMessage += "No valid third field!\n";
        if(fouthField.disableProperty().getValue()==false && (fouthField.getText() == null || fouthField.getText().length() == 0))
            errorMessage += "No valid fouth field!\n";
        if(fifthField.disableProperty().getValue()==false && (fifthField.getText() == null || fifthField.getText().length() == 0))
            errorMessage += "No valid fifth field!\n";

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    private void setLabeksText(String first,String second,String third,String fouth,String fifth) {

       setComboBoxInvisible();

        firstLabel.setText(first);
        secondLabel.setText(second);
        thirdLabel.setText(third);
        fouthLabel.setText(fouth);
        fifthLabel.setText(fifth);

        if(first.isEmpty()){
            firstField.setVisible(false);
            firstField.disableProperty().setValue(true);
        }
        if(second.isEmpty()){
            lastField.setVisible(false);
            lastField.disableProperty().setValue(true);
        }
        if(third.isEmpty()){
            thirdField.setVisible(false);
            thirdField.disableProperty().setValue(true);
        }
        if(fouth.isEmpty()){
            fouthField.setVisible(false);
            fouthField.disableProperty().setValue(true);
        }if(fifth.isEmpty()){
            fifthField.setVisible(false);
            fifthField.disableProperty().setValue(true);
        }
    }

    private void showFirstComboBox(){
        firstcomboBox.getItems().clear();
        firstcomboBox.getItems().add(new String("Поликлиника"));
        firstcomboBox.getItems().add(new String("Больница"));

        thirdField.visibleProperty().setValue(false);
        thirdField.disableProperty().setValue(true);

        firstcomboBox.disableProperty().setValue(false);
        firstcomboBox.visibleProperty().setValue(true);

    }

    private void showSecondComboBox(){
        secondComboBox.getItems().clear();
        secondComboBox.getItems().add(new String("Кандидат медицинских наук"));
        secondComboBox.getItems().add(new String("Доктор медицинских наук"));

        fouthField.visibleProperty().setValue(false);
        fouthField.disableProperty().setValue(true);

        secondComboBox.disableProperty().setValue(false);
        secondComboBox.visibleProperty().setValue(true);

    }
    private void showThirdComboBox(){
        thirdCombobox.getItems().clear();
        thirdCombobox.getItems().add(new String("Доцент"));
        thirdCombobox.getItems().add(new String("Профессор"));

        fifthField.visibleProperty().setValue(false);
        fifthField.disableProperty().setValue(true);

        thirdCombobox.disableProperty().setValue(false);
        thirdCombobox.visibleProperty().setValue(true);

    }

    private void setComboBoxInvisible(){
        firstcomboBox.setVisible(false);
        firstcomboBox.disableProperty().setValue(false);

        secondComboBox.setVisible(false);
        secondComboBox.disableProperty().setValue(false);

        thirdCombobox.setVisible(false);
        thirdCombobox.disableProperty().setValue(false);
    }

}
