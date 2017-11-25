package FX;

import dao.CrudDao;
import dao.daoImpl.StaffDao;
import entity.AnalysisEntity;
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
import javafx.scene.control.cell.PropertyValueFactory;
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


    // Ссылка на главное приложение.
    private CrudDao dao;
    private Main mainApp;

    private static ObservableList<Object> staff = FXCollections.observableArrayList();



    /**
     * Инициализация класса-контроллера. Этот метод вызывается автоматически
     * после того, как fxml-файл будет загружен.
     */
    @FXML
    private void initialize() {
        // Инициализация таблицы адресатов с двумя столбцами.
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("fio"));


        showStaffDetails(null);
        personTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showStaffDetails((StaffEntity) newValue));
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

        dao  = new StaffDao(mainApp.getOurSessionFactory());
        staff.addAll(dao.list());
        personTable.setItems(staff);
    }

    @FXML
    private void deleteButtonHandler(){
        int selectedIndex = personTable.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0) {
            StaffEntity staffEntity = (StaffEntity) personTable.getItems().remove(selectedIndex);
            dao.delete(staffEntity.getId());
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
        tempPerson.setFio("FirstName LastName ThirdName");
        boolean okClicked = showPersonEditDialog(tempPerson);
        if (okClicked) {
           staff.add(tempPerson);
            dao.create(tempPerson);
        }
    }

    /**
     * Вызывается, когда пользователь кликает по кнопка Edit...
     * Открывает диалоговое окно для изменения выбранного адресата.
     */
    @FXML
    private void handleEditStaff() {
        StaffEntity selectedStaff = (StaffEntity) personTable.getSelectionModel().getSelectedItem();
        if (selectedStaff != null) {
            boolean okClicked = showPersonEditDialog(selectedStaff);
            if (okClicked) {
                showStaffDetails(selectedStaff);
                dao.update(selectedStaff);
            }

        } else {
            // Ничего не выбрано.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Staff Selected");
            alert.setContentText("Please select a object in the table.");

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
            dialogStage.initOwner(mainApp.getPrimaryStage());
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

    private void showStaffDetails(StaffEntity staffEntity){
        if(staffEntity!= null){
            setLabeksText(staffEntity.getFio().split(" ")[0],staffEntity.getFio().split(" ")[1],staffEntity.getFio().split(" ")[2],"","","");

        }else{
            setLabeksText("","","","","","");
        }
    }
    private void showAnalusisDetails(AnalysisEntity analysisEntity){
        if(analysisEntity!= null){
            setLabeksText(analysisEntity.getTypeOfAnalys().getName(),String.valueOf(analysisEntity.getId()),"","","","");

        }else{
            setLabeksText("","","","","","");
        }
    }



    public void setAnalysisColumns(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("typeOfAnalys"));
        personTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showAnalusisDetails((AnalysisEntity) newValue));

        setStaticLabeksText("id","name","","","","");
    }
    public void setStaffColumns(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("fio"));
        personTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showStaffDetails((StaffEntity) newValue));

        setStaticLabeksText("id","First name","Second name","Patronym","","");
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
