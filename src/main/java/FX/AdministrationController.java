package FX;

import dao.QueryUtil;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.Main;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lapko on 05.12.2017.
 */
public class AdministrationController {

    QueryUtil queryUtil;
    Stage stage;
    @FXML
    Label label1;
    @FXML
    Label label2;
    @FXML
    TextField textField;
    @FXML
    TextField textField2;

    @FXML
    Button button;


    private String sql;

    @FXML
    private void initialize(){
        queryUtil = new QueryUtil(Main.getOurSessionFactory());
    }

    @FXML
    private void queryButtonHandler(){
        ResultSetMetaData meta;
        ResultSet resultSet;



        if(textField.getText().isEmpty())
            resultSet =  queryUtil.createQuery(sql);
        else {
            if(!textField.getText().isEmpty())
                queryUtil.addParam(textField.getText());

            if(!textField2.getText().isEmpty())
                queryUtil.addParam(textField2.getText());

            try {
                resultSet = queryUtil.createQueryWithParam(sql);
            }catch (Exception e){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(stage);
                alert.setTitle("Error");
                alert.setHeaderText("Error when execute query");
                alert.setContentText("Close and try again");

                alert.showAndWait();
            }



        }
        stage.close();


    }

    public void setSql(String SQL){
        this.sql = SQL;
        int pos;
        setParamInvisible();
        if(!sql.contains("?")){
            queryButtonHandler();

            setParamInvisible();

        }else if((pos = sql.indexOf('?'))!=-1){
            setVisibleFirstParam();
            if(sql.indexOf('?',pos+1)!=-1){
                setVisibleSecondParam();
            }
        }
    }

    public void setParamesNames(String[] str){
        if(str.length >=1){
            label1.setText(str[0]);
            if(str.length>=2){
                label2.setText(str[1]);
            }
        }
    }

    private void setParamInvisible(){
        button.setVisible(false);
        button.disableProperty().setValue(true);

        textField.setVisible(false);
        textField.disableProperty().setValue(true);

        textField2.setVisible(false);
        textField2.disableProperty().setValue(true);

        label1.setText("");
        label2.setText("");
    }

    private void setVisibleFirstParam(){
        button.setVisible(true);
        button.disableProperty().setValue(false);

        textField.setVisible(true);
        textField.disableProperty().setValue(false);


    }

    private void setVisibleSecondParam(){
        textField2.setVisible(true);
        textField2.disableProperty().setValue(false);
    }

    public void setStage(Stage stage){
        this.stage = stage;
    }
}
