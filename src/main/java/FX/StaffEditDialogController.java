package FX;

import entity.StaffEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Created by lapko on 22.11.2017.
 */
public class StaffEditDialogController {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField thirdNameField;



    private Stage dialogStage;
    private StaffEntity staffEntity;
    private boolean okClicked = false;

    /**
     * �������������� �����-����������. ���� ����� ���������� �������������
     * ����� ����, ��� fxml-���� ����� ��������.
     */
    @FXML
    private void initialize() {
    }

    /**
     * ������������� ����� ��� ����� ����.
     *
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }


    public void setStaff(StaffEntity staff) {
        this.staffEntity = staff;

            firstNameField.setText(staffEntity.getFio().split(" ")[0]);
            lastNameField.setText(staffEntity.getFio().split(" ")[1]);
            thirdNameField.setText(staffEntity.getFio().split(" ")[2]);

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
            staffEntity.setFio(firstNameField.getText() + " " + lastNameField.getText() + " " + thirdNameField.getText());

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

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (thirdNameField.getText() == null || thirdNameField.getText().length() == 0) {
            errorMessage += "No valid street!\n";
        }



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
}
