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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by lapko on 22.11.2017.
 */
public class StaffEditDialogController {
    @FXML
    private TextField firstField;
    @FXML
    private TextField secondField;
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
    private ComboBox<String> thirdComboBox;
    @FXML
    private ComboBox<String> fouthComboBox;
    @FXML
    private ComboBox<String> fifthCombobox;

    private Stage dialogStage;
    private Object entity;
    private boolean okClicked = false;
    private List<Boolean> required= new ArrayList<>();
    private String STYLE_FOR_REQUIRED_FIELD = "-fx-border-color: blue";

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
            setRequired(new boolean[]{true, true, true});
        }else if(entity instanceof AnalysisEntity){
            setLabeksText("Id type of analysis","Id patience","","","");
            setRequired(new boolean[]{true,true,true});
        }else if(entity instanceof DepartmentEntity){
            setLabeksText("Name", "Housing id", "","","");
            setRequired(new boolean[]{true,true});
        }else if(entity instanceof DiseaseEntity){
             setLabeksText("Name", "", "","","");
            setRequired(new boolean[]{true});
        }else if(entity instanceof HousingEntity){
             setLabeksText("Name", "Address", "Medical facility id","","");
            setRequired(new boolean[]{true,true,true});
        }else if(entity instanceof LaboratoryEntity){
            setLabeksText("Name", "Address", "","","");
            setRequired(new boolean[]{true,true});
        }else if(entity instanceof LaboratorySpecEntity){
            setLabeksText("Name", "", "","","");
            setRequired(new boolean[]{true});
        }else if(entity instanceof MedicalFacilityEntity) {
            setLabeksText("Name", "Address", "Type", "Order doctor","");
            setRequired(new boolean[]{true,true,true,true});
            showFirstComboBox();

        }else if(entity instanceof OccupiedBedsEntity){
            setLabeksText("Id room", "Since", "To","","");
            setRequired(new boolean[]{true,true,false});
        }else if(entity instanceof OfficeEntity){
            setLabeksText("Id department", "Id doctor", "","","");
            setRequired(new boolean[]{true,true});
        }else if(entity instanceof PatienceEntity){
            setLabeksText("First name", "Last name", "Patronym","","");
            setRequired(new boolean[]{true,true,true});
        }else if(entity instanceof PositionEntity){
            setLabeksText("Name", "", "","","");
            setRequired(new boolean[]{true});
        }else if(entity instanceof RoomEntity){
            setLabeksText("Room number", "Number of beds", "Id department", "Id doctor","");
            setRequired(new boolean[]{true,false,true,true});
        }else if(entity instanceof SpecialtyEntity){
            setLabeksText("Name", "Doctor", "Salary", "Degree", "Grade");
            setRequired(new boolean[]{true,true,true,false,false});
            showSecondComboBox();
            showThirdComboBox();
        }else if(entity instanceof TypeOfAnalysisEntity){
            setLabeksText("Name", "", "","","");
            setRequired(new boolean[]{true});
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
                staffEntity.setFio(firstField.getText() + " " + secondField.getText() + " " + thirdField.getText());

            }else if(entity instanceof AnalysisEntity){

                AnalysisEntity analysisEntity = AnalysisEntity.class.cast(entity);
                TypeOfAnalysisDao typeOfAnalysisDao = new TypeOfAnalysisDao(Main.getOurSessionFactory());

                TypeOfAnalysisEntity typeOfAnalysisEntity =  typeOfAnalysisDao.read(Integer.parseInt(firstField.getText()), TypeOfAnalysisEntity.class);
                analysisEntity.setTypeOfAnalys(typeOfAnalysisEntity);

                PatienceDao patienceDao  =new PatienceDao(Main.getOurSessionFactory());
                analysisEntity.setPatience(patienceDao.read(Integer.valueOf(secondField.getText()), PatienceEntity.class));

            }else if(entity instanceof DepartmentEntity){

                DepartmentEntity departmentEntity = DepartmentEntity.class.cast(entity);
                departmentEntity.setName(firstField.getText());

                HousingDao housingDao = new HousingDao(Main.getOurSessionFactory());
                departmentEntity.setHousing( housingDao.read(Integer.valueOf(secondField.getText()),HousingEntity.class));
            }else if(entity instanceof DiseaseEntity){
                DiseaseEntity diseaseEntity = DiseaseEntity.class.cast(entity);
                diseaseEntity.setName(firstField.getText());
            }else if(entity instanceof HousingEntity){
                HousingEntity housingEntity = HousingEntity.class.cast(entity);
                housingEntity.setName(firstField.getText());
                housingEntity.setAddress(secondField.getText());

                MedicalFacilityDao dao = new MedicalFacilityDao(Main.getOurSessionFactory());
                housingEntity.setMedicalFacility(dao.read(Integer.valueOf(thirdField.getText()),MedicalFacilityEntity.class));
            }else if(entity instanceof LaboratoryEntity){
               LaboratoryEntity laboratoryEntity = LaboratoryEntity.class.cast(entity);
                laboratoryEntity.setName(firstField.getText());
                laboratoryEntity.setAddress(secondField.getText());
            }else if(entity instanceof LaboratorySpecEntity){
                LaboratorySpecEntity laboratorySpecEntity = LaboratorySpecEntity.class.cast(entity);
                laboratorySpecEntity.setName(firstField.getText());
            }else if(entity instanceof MedicalFacilityEntity) {
                MedicalFacilityEntity medicalFacilityEntity  = MedicalFacilityEntity.class.cast(entity);
                medicalFacilityEntity.setName(firstField.getText());
                medicalFacilityEntity.setAddress(secondField.getText());
                medicalFacilityEntity.setMedicalFacilityType(thirdField.getText());

                StaffDao dao = new StaffDao(Main.getOurSessionFactory());
                medicalFacilityEntity.setOrderDoctor(dao.read(Integer.valueOf(fouthField.getText()),StaffEntity.class));
            }else if(entity instanceof OccupiedBedsEntity){
                OccupiedBedsEntity occupiedBedsEntity = OccupiedBedsEntity.class.cast(entity);

                RoomDao dao = new RoomDao(Main.getOurSessionFactory());
                occupiedBedsEntity.setRoom(dao.read(Integer.valueOf(firstField.getText()),RoomEntity.class));

                occupiedBedsEntity.setSince(new Date(secondField.getText()));
                occupiedBedsEntity.setTo(new Date(thirdField.getText()));
            }else if(entity instanceof OfficeEntity){
               OfficeEntity officeEntity = OfficeEntity.class.cast(entity);

                DepartmentDao  dao = new DepartmentDao(Main.getOurSessionFactory());
                officeEntity.setDepartment(dao.read(Integer.valueOf(firstField.getText()),DepartmentEntity.class));

                StaffDao staffDao = new StaffDao(Main.getOurSessionFactory());
                officeEntity.setDoctor(staffDao.read(Integer.valueOf(secondField.getText()),StaffEntity.class));
            }else if(entity instanceof PatienceEntity){
                PatienceEntity patienceEntity = PatienceEntity.class.cast(entity);

                patienceEntity.setFio(firstField.getText()+" "+ secondField.getText()+" "+ thirdField.getText());
            }else if(entity instanceof PositionEntity){
                PositionEntity positionEntity = PositionEntity.class.cast(entity);

                positionEntity.setName(firstField.getText());
            }else if(entity instanceof RoomEntity){
              RoomEntity roomEntity = RoomEntity.class.cast(entity);
                roomEntity.setRoomNumber(Integer.parseInt(firstField.getText()));
                roomEntity.setNumberOfBeds(Integer.valueOf(secondField.getText()));

                DepartmentDao departmentDao = new DepartmentDao(Main.getOurSessionFactory());
                roomEntity.setDepartment(departmentDao.read(Integer.valueOf(thirdField.getText()), DepartmentEntity.class));

                StaffDao staffDao = new StaffDao(Main.getOurSessionFactory());
                roomEntity.setResponsibleDoctor(staffDao.read(Integer.valueOf(fouthField.getText()),StaffEntity.class));
            }else if(entity instanceof SpecialtyEntity){
                SpecialtyEntity specialtyEntity = SpecialtyEntity.class.cast(entity);

                specialtyEntity.setName(firstField.getText());
                specialtyEntity.setIsDoctor(Boolean.valueOf(secondField.getText()));
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
        if (required.get(0)==true && (firstField.getText() == null || firstField.getText().length() == 0))
            errorMessage += "No valid first field!\n";

        if (required.get(1)==true && (secondField.getText() == null || secondField.getText().length() == 0))
            errorMessage += "No valid second field!\n";

        if (required.get(2)==true && ((thirdField.getText() == null || thirdField.getText().length() == 0) && (thirdComboBox.getValue() == null || thirdComboBox.getValue().isEmpty())))
            errorMessage += "No valid third field!\n";
        if(required.get(3)==true && ((fouthField.getText() == null || fouthField.getText().length() == 0) && (fouthComboBox.getValue() == null || fouthComboBox.getValue().isEmpty())))
            errorMessage += "No valid fouth field!\n";
        if(required.get(4)==true && ((fifthField.getText() == null || fifthField.getText().length() == 0) && (fifthCombobox.getValue() == null || fifthCombobox.getValue().isEmpty())))
            errorMessage += "No valid fifth field!\n";

        if (errorMessage.length() == 0 && chechFields()) {
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
            secondField.setVisible(false);
            secondField.disableProperty().setValue(true);
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

    private void setRequired(boolean[] fields){
        for(int i=0;i< 5;i++){
            if(fields.length <= i)
            {
                required.add(false);
            }else {
                required.add(fields[i]);
            }
        }
        drawRequiredFields();
    }
    private void drawRequiredFields(){
        for(int i=0;i<required.size();i++){
            switch (i){
                case 0:
                    if(required.get(i))
                        firstField.setStyle(STYLE_FOR_REQUIRED_FIELD);
                    break;
                case 1:
                    if(required.get(i))
                        secondField.setStyle(STYLE_FOR_REQUIRED_FIELD);
                    break;
                case 2:
                    if(required.get(i)) {
                        thirdField.setStyle(STYLE_FOR_REQUIRED_FIELD);
                        thirdComboBox.setStyle(STYLE_FOR_REQUIRED_FIELD);
                    }
                    break;
                case 3:
                    if(required.get(i)) {
                        fouthField.setStyle(STYLE_FOR_REQUIRED_FIELD);
                        fouthComboBox.setStyle(STYLE_FOR_REQUIRED_FIELD);
                    }
                    break;
                case 4:
                    if(required.get(i)) {
                        fifthField.setStyle(STYLE_FOR_REQUIRED_FIELD);
                        fifthCombobox.setStyle(STYLE_FOR_REQUIRED_FIELD);
                    }
                    break;
            }
        }
    }

    private void showFirstComboBox(){
        thirdComboBox.getItems().clear();
        thirdComboBox.getItems().add(new String("Поликлиника"));
        thirdComboBox.getItems().add(new String("Больница"));

        thirdField.visibleProperty().setValue(false);
        thirdField.disableProperty().setValue(true);

        thirdComboBox.disableProperty().setValue(false);
        thirdComboBox.visibleProperty().setValue(true);

    }

    private void showSecondComboBox(){
        fouthComboBox.getItems().clear();
        fouthComboBox.getItems().add(new String("Кандидат медицинских наук"));
        fouthComboBox.getItems().add(new String("Доктор медицинских наук"));

        fouthField.visibleProperty().setValue(false);
        fouthField.disableProperty().setValue(true);

        fouthComboBox.disableProperty().setValue(false);
        fouthComboBox.visibleProperty().setValue(true);

    }
    private void showThirdComboBox(){
        fifthCombobox.getItems().clear();
        fifthCombobox.getItems().add(new String("Доцент"));
        fifthCombobox.getItems().add(new String("Профессор"));

        fifthField.visibleProperty().setValue(false);
        fifthField.disableProperty().setValue(true);

        fifthCombobox.disableProperty().setValue(false);
        fifthCombobox.visibleProperty().setValue(true);

    }

    private void setComboBoxInvisible(){
        thirdComboBox.setVisible(false);
        thirdComboBox.disableProperty().setValue(false);

        fouthComboBox.setVisible(false);
        fouthComboBox.disableProperty().setValue(false);

        fifthCombobox.setVisible(false);
        fifthCombobox.disableProperty().setValue(false);
    }


    public void setTextFieldValues(String[] values){
        if(values.length>=1)
        {
            firstField.setText(values[0]);
            if(values.length>=2) {
                secondField.setText(values[1]);
                if(values.length>=3){
                    thirdField.setText(values[2]);
                    thirdComboBox.setValue(values[2]);
                    if(values.length>=4) {
                        fouthField.setText(values[3]);
                        fouthComboBox.setValue(values[3]);
                        if(values.length>=5) {
                            fifthField.setText(values[4]);
                            fifthCombobox.setValue(values[4]);
                        }
                    }

                }
            }

        }
    }

    public boolean chechFields(){
        for(int i=0;i< required.size();i++){
            if(i==0){
                if(required.get(i))
                    if(firstField.getText().isEmpty())
                       return false;
            }
            if(i==1){
                if(required.get(i))
                    if(secondField.getText().isEmpty())
                        return false;
            }
            if(i==2){
                if(required.get(i)) {
                    if(thirdComboBox.disableProperty().getValue()==false){
                        if(thirdComboBox.getValue().isEmpty())
                            return false;
                    }
                    if (thirdField.disabledProperty().getValue()==false){
                        if(thirdField.getText().isEmpty())
                            return false;
                    }
                }
            }
            if(i==3){
                if(required.get(i)) {
                    if(fouthComboBox.disableProperty().getValue()==false){
                        if(fouthComboBox.getValue().isEmpty())
                            return false;
                    }
                    if (fouthField.disabledProperty().getValue()==false){
                        if(fouthField.getText().isEmpty())
                            return false;
                    }
                }
            }
            if(i==4){
                if(required.get(i)) {
                    if(fifthCombobox.disableProperty().getValue()==false){
                        if(fifthCombobox.getValue().isEmpty())
                            return false;
                    }
                    if (fifthField.disabledProperty().getValue()==false){
                        if(fifthField.getText().isEmpty())
                            return false;
                    }
                }
            }
        }
        return true;
    }
}
