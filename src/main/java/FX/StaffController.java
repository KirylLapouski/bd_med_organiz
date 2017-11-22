package FX;

import dao.daoImpl.StaffDao;
import entity.StaffEntity;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import main.Main;

import java.io.IOException;

/**
 * Created by lapko on 17.11.2017.
 */
public class StaffController {
    public StaffController() {
    }

    StaffDao staffDao;

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
    private Label ThirdNameLabel;

    // Ссылка на главное приложение.
    private Main mainApp;

    private static ObservableList<StaffEntity> staff = FXCollections.observableArrayList();



    /**
     * Инициализация класса-контроллера. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
        // Инициализация таблицы адресатов с двумя столбцами.
        idColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().id_fxProperty().getValue()));
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().fio_fxProperty());

        showStaffDetails(null);

        personTable.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> showStaffDetails(newValue)));

    }

    private void showStaffDetails(StaffEntity staffEntity){
        if(staffEntity!= null){
            firstNameLabel.setText(staffEntity.getFio().split(" ")[0]);
            lastNameLabel.setText( staffEntity.getFio().split(" ")[1]);
            ThirdNameLabel.setText(staffEntity.getFio().split(" ")[2]);
        }else{
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            ThirdNameLabel.setText("");
        }
    }

    /**
     * Вызывается главным приложением, которое даёт на себя ссылку.
     *
     * @param mainApp
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;

        // Добавление в таблицу данных из наблюдаемого списка
        personTable.setItems(staff);

       staffDao  = new StaffDao(mainApp.getOurSessionFactory());
       staff.addAll(staffDao.list());
    }

    @FXML
    private void deleteButtonHandler(){
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0) {
            StaffEntity staffEntity =  personTable.getItems().remove(selectedIndex);
            staffDao.delete(staffEntity.getId());
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No selection");
            alert.setHeaderText("No person selected");
            alert.setContentText("Please select a person in the table");

            alert.showAndWait();
        }
    }
    /**
     * Вызывается, когда пользователь кликает по кнопке New...
     * Открывает диалоговое окно с дополнительной информацией нового адресата.
     */
    @FXML
    private void handleNewStaff() {
        StaffEntity tempPerson = new StaffEntity();
        tempPerson.setId(0);
        tempPerson.setFio("FirstName LastName ThirdName");
        boolean okClicked = showPersonEditDialog(tempPerson);
        if (okClicked) {
           staff.add(tempPerson);
            staffDao.create(tempPerson);
        }
    }

    /**
     * Вызывается, когда пользователь кликает по кнопка Edit...
     * Открывает диалоговое окно для изменения выбранного адресата.
     */
    @FXML
    private void handleEditStaff() {
        StaffEntity selectedStaff = personTable.getSelectionModel().getSelectedItem();
        if (selectedStaff != null) {
            boolean okClicked = showPersonEditDialog(selectedStaff);
            if (okClicked) {
                showStaffDetails(selectedStaff);
                staffDao.update(selectedStaff);
            }

        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Staff Selected");
            alert.setContentText("Please select a staff in the table.");

            alert.showAndWait();
        }
    }

    /**
     * Открывает диалоговое окно для изменения деталей указанного адресата.
     * Если пользователь кликнул OK, то изменения сохраняются в предоставленном
     * объекте адресата и возвращается значение true.
     *
     * @return true, если пользователь кликнул OK, в противном случае false.
     */
    public boolean showPersonEditDialog(StaffEntity staffEntity) {
        try {
            // Загружаем fxml-файл и создаём новую сцену
            // для всплывающего диалогового окна.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/staffDialogDetails.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Создаём диалоговое окно Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainApp.getStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Передаём адресата в контроллер.
            StaffEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setStaff(staffEntity);

            // Отображаем диалоговое окно и ждём, пока пользователь его не закроет
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
