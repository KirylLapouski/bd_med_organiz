package FX;

import dao.daoImpl.TypeOfAnalysisDao;
import entity.AnalysisEntity;
import entity.StaffEntity;
import entity.TypeOfAnalysisEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;

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
    private Label firstLabel;
    @FXML
    private Label secondLabel;
    @FXML
    private Label thirdLabel;


    private Stage dialogStage;
    private Object entity;
    private boolean okClicked = false;

    /**
     * �������������� �����-����������. ���� ����� ���������� �������������
     * ����� ����, ��� fxml-���� ����� ��������.
     */
    @FXML
    private void initialize() {
       /* firstField= new TextField("FirstField");
        lastField = new TextField("SecondField");
        thirdField = new TextField("ThirdField");*/
    }

    /**
     * ������������� ����� ��� ����� ����.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    public void setStaff(Object staff) {
        Class  classs= staff.getClass();
        this.entity = staff;

        if(entity instanceof StaffEntity){
            /*firstField.setText(entity.getFio().split(" ")[0]);
            lastField.setText(entity.getFio().split(" ")[1]);
            thirdField.setText(entity.getFio().split(" ")[2]);*/

            setLabeksText("First name","Last name","Patronymic");
        }else if(entity instanceof AnalysisEntity){
            setLabeksText("ID type of analysis","","");
        }



    }

    /**
     * Returns true, ���� ������������ ������� OK, � ������ ������ false.
     *
     * @return
     */
    public boolean isOkClicked() {
        return okClicked;
    }

    /**
     * ����������, ����� ������������ ������� �� ������ OK.
     */
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
                String str = typeOfAnalysisEntity.getId()+"" + typeOfAnalysisEntity.getName();
                analysisEntity.setTypeOfAnalys(typeOfAnalysisEntity);
            }

            okClicked = true;
            dialogStage.close();
        }
    }

    /**
     * ����������, ����� ������������ ������� �� ������ Cancel.
     */
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    /**
     * ��������� ���������������� ���� � ��������� �����.
     *
     * @return true, ���� ���������������� ���� ���������
     */
    private boolean isInputValid() {
        String errorMessage = "";

        String str  = firstField.getText();
        if (firstField.disableProperty().getValue()==false && (firstField.getText() == null || firstField.getText().length() == 0))
            errorMessage += "No valid first field!\n";

        if (lastField.disableProperty().getValue()==false && (lastField.getText() == null || lastField.getText().length() == 0))
            errorMessage += "No valid second field!\n";

        if (thirdField.disableProperty().getValue()==false && (thirdField.getText() == null || thirdField.getText().length() == 0))
            errorMessage += "No valid third field!\n";


        if (errorMessage.length() == 0) {
            return true;
        } else {
            // ���������� ��������� �� ������.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid Fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }

    private void setLabeksText(String first,String second,String third) {
        firstLabel.setText(first);
        secondLabel.setText(second);
        thirdLabel.setText(third);
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
    }
}
