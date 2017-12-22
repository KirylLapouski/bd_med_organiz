package FX;

import dao.QueryUtil;
import dbUsers.XMLUsers;
import entity.*;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import main.Main;
import net.sf.jasperreports.engine.JRException;
import reportGenerator.ReportGenerator;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by lapko on 21.12.2017.
 */
public class LaborantController {
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
            queryUtil.addParam(String.valueOf(XMLUsers.getLaboratoryByUser(Main.loggedUserLogin,Main.loggedUserPassword)));
            ResultSet resultSet =  queryUtil.createQueryWithParam("call selectLabarantTasks(?);");

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


    @FXML
    private void finishButtonHandler() {
        int selectedIndex = table.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0) {
            queryUtil.createUpdate("Update survey SET survey.to_ = NOW() WHERE survey.id_laboratory = " + XMLUsers.getLaboratoryByUser(Main.loggedUserLogin, Main.loggedUserPassword) + " and survey.id_medical_facility = " + table.getSelectionModel().getSelectedItem().split(";")[2] + " and survey.id_analysis=" + table.getSelectionModel().getSelectedItem().split(";")[0] + " and survey.since_='" + table.getSelectionModel().getSelectedItem().split(";")[4] + "'");
            selectTasksFormDB();
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No selection");
            alert.setHeaderText("No item selected");
            alert.setContentText("Please select a item in the table");

            alert.showAndWait();
        }
    }

    @FXML
    private void todaysWorkButtonHander(){
        ReportGenerator reportGenerator = new ReportGenerator();

        reportGenerator.setREPORT_pattern("src/main/resources/jrxml/workfortoday.jrxml");
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

        reportGenerator.setREPORT_pattern("src/main/resources/jrxml/tomorrowTasks.jrxml");
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