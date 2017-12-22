package FX;

import dao.QueryUtil;
import dbUsers.XMLUsers;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.Main;
import net.sf.jasperreports.engine.JRException;
import reportGenerator.ReportGenerator;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lapko on 21.12.2017.
 */
public class DoctorController {
    QueryUtil queryUtil;
    @FXML
    TableView<String> table;


    List<TableColumn<String,String>> columns;
    private  ObservableList<String> list =  FXCollections.observableArrayList();

    @FXML
    private void initialize(){
        queryUtil = new QueryUtil(Main.getOurSessionFactory());
        columns = new ArrayList<>();
        selectTasksFormDB();
    }


    private void selectTasksFormDB(){
        ResultSetMetaData meta;

        list.clear();
        columns.clear();
        table.getColumns().clear();


        try {
            queryUtil.clearParams();
            queryUtil.addParam(String.valueOf(XMLUsers.getDoctorByUser(Main.loggedUserLogin, Main.loggedUserPassword)));
            ResultSet resultSet =  queryUtil.createQueryWithParam("call selectDoctorTasks(?);");

            meta = resultSet.getMetaData();
            for(int i=1;i<=meta.getColumnCount();i++) {
                columns.add(new TableColumn(meta.getColumnName(i)));
            }
            while (resultSet.next()){
                String result = "";
                for(int i=1;i<=meta.getColumnCount();i++){
                    result+= resultSet.getString(i)+";";
                }
                list.add(result);
            }
            for(int i=1;i<=meta.getColumnCount();i++){
                final int finalI = i;

                columns.get(i-1).setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String, String>, ObservableValue<String>>() {
                    @Override
                    public ObservableValue<String> call(TableColumn.CellDataFeatures<String, String> param) {
                        return new ReadOnlyObjectWrapper<String>(param.getValue().split(";")[ finalI -1]);

                    }
                });

            }

            table.getColumns().addAll(columns);
            table.setItems(list);

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error when execute query");
            alert.setContentText("Close and try again");

            alert.showAndWait();
        }

    }

//TODO
    @FXML
    private void finishButtonHandler() {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0) {
            showConfirmDialog();
            /*queryUtil.createUpdate("Update appointment SET appointment.to_ = NOW() WHERE appointment.id_doctor = " + XMLUsers.getDoctorByUser(Main.loggedUserLogin, Main.loggedUserPassword) + " and appointment.id_patience = " + table.getSelectionModel().getSelectedItem().split(";")[0] + " and appointment.id_office=" + XMLUsers.getOfficeByUser(Main.loggedUserLogin, Main.loggedUserPassword) + " and appointment.since_='" + table.getSelectionModel().getSelectedItem().split(";")[3] + "'");
            */
            selectTasksFormDB();
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No selection");
            alert.setHeaderText("No item selected");
            alert.setContentText("Please select a item in the table");

            alert.showAndWait();
        }
    }
    public void showConfirmDialog() {
        try {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/view/doctorFinishAppointment.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Disease");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            DoctorFinishAppointmentController controller = loader.getController();
            controller.setIdPatience(Integer.parseInt(table.getSelectionModel().getSelectedItem().split(";")[0]));
            controller.setSince(table.getSelectionModel().getSelectedItem().split(";")[3]);
            controller.setStage(dialogStage);
            dialogStage.showAndWait();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//TODO
    @FXML
    private void todaysWorkButtonHander(){
        ReportGenerator reportGenerator = new ReportGenerator();

        reportGenerator.setREPORT_pattern("src/main/resources/jrxml/todayWorkMF.jrxml");
        try {
            reportGenerator.create();
        } catch (JRException e) {
            e.printStackTrace();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Report");
        alert.setHeaderText("Report successfully generated");

        alert.showAndWait();
    }

    @FXML
    private void tomorrowTaskHandler() {
        ReportGenerator reportGenerator = new ReportGenerator();

        reportGenerator.setREPORT_pattern("src/main/resources/jrxml/tomorrowTasksMF.jrxml");
        try {
            reportGenerator.create();
        } catch (JRException e) {
            e.printStackTrace();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Report");
        alert.setHeaderText("Report successfully generated");

        alert.showAndWait();
    }



}
