package FX;

import dao.QueryUtil;
import entity.util.Tables;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.util.Callback;
import main.Main;
import org.hibernate.engine.query.spi.sql.NativeSQLQueryReturn;

import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lapko on 28.11.2017.
 */
public class QueryWindowController {
    QueryUtil queryUtil;
    @FXML
    TextArea textArea;
    @FXML
    TableView<String> table;

    List<TableColumn<String,String>> columns;
    private  ObservableList<String> list =  FXCollections.observableArrayList();

    @FXML
    private void initialize(){
        queryUtil = new QueryUtil(Main.getOurSessionFactory());
        columns = new ArrayList<>();
    }

    @FXML
    private void queryButtonHandler(){
        ResultSetMetaData meta;

        list.clear();
        columns.clear();
        table.getColumns().clear();

        ResultSet resultSet =  queryUtil.createQuery(textArea.getText());

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
}
