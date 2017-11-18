package FX;

import entity.StaffEntity;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import main.Main;
/**
 * Created by lapko on 17.11.2017.
 */
public class StaffController {
    public StaffController() {
    }

    @FXML
    private TableView<StaffEntity> personTable;
    @FXML
    private TableColumn<StaffEntity, Integer> idColumn;
    @FXML
    private TableColumn<StaffEntity, String> lastNameColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetLabel;
    @FXML
    private Label postalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    // ������ �� ������� ����������.
    private Main mainApp;


    /**
     * ������������� ������-�����������. ���� ����� ���������� �������������
     * ����� ����, ��� fxml-���� ����� ��������.
     */
    @FXML
    private void initialize() {
        // ������������� ������� ��������� � ����� ���������.
        idColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().id_fxProperty().getValue()));
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().fio_fxProperty());
    }

    /**
     * ���������� ������� �����������, ������� ��� �� ���� ������.
     *
     * @param mainApp
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;

        // ���������� � ������� ������ �� ������������ ������
        personTable.setItems(mainApp.getStaff());
    }
}
