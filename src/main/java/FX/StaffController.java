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


    // ������ �� ������� ����������.
    private CrudDao dao;
    private Main mainApp;
    private Class classs;
    private static ObservableList<Object> staff = FXCollections.observableArrayList();



    /**
     * ������������� ������-�����������. ���� ����� ���������� �������������
     * ����� ����, ��� fxml-���� ����� ��������.
     */
    @FXML
    private void initialize() {
        // ������������� ������� ��������� � ����� ���������.
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("fio"));


        showStaffDetails(null);
        personTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> showStaffDetails(newValue));
    }


    /**
     * ���������� ������� �����������, ������� ��� �� ���� ������.
     *
     * @param mainApp
     */
    public void setMainApp(Main mainApp) {
        this.mainApp = mainApp;

        // ���������� � ������� ������ �� ������������ ������
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
            staff.clear();
            staff.addAll(dao.list());
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
     * ����������, ����� ������������ ������� �� ������ New...
     * ��������� ���������� ���� � �������������� ����������� ������ ��������.
     */
    @FXML
    private void handleNewStaff() {
        try {
            Class classs = staff.get(1).getClass();
            Object newEntity = classs.newInstance();

            if(newEntity instanceof StaffEntity) {
                ((StaffEntity)newEntity).setId(0);
                ((StaffEntity)newEntity).setFio("FirstName LastName ThirdName");
            }else if(newEntity instanceof AnalysisEntity){
                ((AnalysisEntity)newEntity).setId(0);
            }
            boolean okClicked = showPersonEditDialog(newEntity);
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

    /**
     * ����������, ����� ������������ ������� �� ������ Edit...
     * ��������� ���������� ���� ��� ��������� ���������� ��������.
     */
    @FXML
    private void handleEditStaff() {
        Class classs = staff.get(1).getClass();
        Object selectedEntity = null;
        selectedEntity = classs.cast(personTable.getSelectionModel().getSelectedItem());
        if (selectedEntity != null) {
            boolean okClicked = showPersonEditDialog(selectedEntity);
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
                }
            }

        } else {
            // ������ �� �������.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(mainApp.getPrimaryStage());
            alert.setTitle("No Selection");
            alert.setHeaderText("No Staff Selected");
            alert.setContentText("Please select a object in the table.");

            alert.showAndWait();
        }
    }

    /**
     * ��������� ���������� ���� ��� ��������� ������� ���������� ��������.
     * ���� ������������ ������� OK, �� ��������� ����������� � ���������������
     * ������� �������� � ������������ �������� true.
     *
     * @return true, ���� ������������ ������� OK, � ��������� ������ false.
     */
    public boolean showPersonEditDialog(Object staffEntity) {
        try {
            // ��������� fxml-���� � ������ ����� �����
            // ��� ������������ ����������� ����.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/staffDialogDetails.fxml"));
            AnchorPane page = (AnchorPane) loader.load();


            // ������ ���������� ���� Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(mainApp.getPrimaryStage());
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // ������� �������� � ����������.
            StaffEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setStaff(staffEntity);

            // ���������� ���������� ���� � ���, ���� ������������ ��� �� �������
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
        }
        else if(entity instanceof StaffEntity) {
            StaffEntity staffEntity = StaffEntity.class.cast(entity);
            setLabeksText(staffEntity.getId().toString(),staffEntity.getFio().split(" ")[0], staffEntity.getFio().split(" ")[1], staffEntity.getFio().split(" ")[2], "", "");

        }
        else if(entity instanceof AnalysisEntity) {
            AnalysisEntity analysisEntity = AnalysisEntity.class.cast(entity);
            setLabeksText(String.valueOf(analysisEntity.getId()),analysisEntity.getTypeOfAnalys().getName() , "", "", "", "");
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
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("typeOfAnalys"));

        setStaticLabeksText("id","name","","","","");
    }
    public void setStaffColumns(){
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("fio"));

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

    public Class getClasss() {
        return classs;
    }

    public void setClasss(Class classs) {
        this.classs = classs;
    }
}
