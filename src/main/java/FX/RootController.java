package FX;

import entity.util.Tables;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import main.Main;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lapko on 25.11.2017.
 */
public class RootController {

    public RootController() {
    }
    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    private TableView<Tables> tablesTable = new TableView<>();

    @FXML
    private TableColumn<Tables,String> name;

    private static ObservableList<Tables> list =  FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        list.add(new Tables("staff"));
        list.add(new Tables("analysis"));
        list.add(new Tables("something"));

        name.setCellValueFactory(new PropertyValueFactory<Tables,String>("name"));

        tablesTable.setItems(list);

        tablesTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> chooseHadnler(newValue));
   }

    private void chooseHadnler(Tables table){
        switch (table.getName())
        {
            case "staff": break;
            case "analysis": main.getRootLayout().setCenter( new AnchorPane()); break;
        }
    }
}
