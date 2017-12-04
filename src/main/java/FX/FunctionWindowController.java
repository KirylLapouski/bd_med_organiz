package FX;

import dao.QueryUtil;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;
import main.Main;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lapko on 29.11.2017.
 */
public class FunctionWindowController {
    QueryUtil queryUtil;
    @FXML
    TextField textField;
    @FXML
    TableView<String> table;
    @FXML
    Button button;

    List<TableColumn<String,String>> columns;
    private ObservableList<String> list =  FXCollections.observableArrayList();

    private String sql;

    @FXML
    private void initialize(){
        queryUtil = new QueryUtil(Main.getOurSessionFactory());
        columns = new ArrayList<>();
    }

    @FXML
    private void queryButtonHandler(){
        ResultSetMetaData meta;
        ResultSet resultSet;

        list.clear();
        columns.clear();
        table.getColumns().clear();

        if(textField.getText().isEmpty())
            resultSet =  queryUtil.createQuery(sql);
        else{
            resultSet = queryUtil.createQueryWithParam(sql, Integer.parseInt(textField.getText()));
        }

        try {
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setSql(String SQL){
        this.sql = SQL;
        if(!sql.contains("?")){
            queryButtonHandler();

            button.setVisible(false);
            button.disableProperty().setValue(true);

            textField.setVisible(false);
            textField.disableProperty().setValue(true);
        }
    }

}
