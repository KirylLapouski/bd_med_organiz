package FX;

import dao.QueryUtil;
import dao.daoImpl.StaffDao;
import dbUsers.XMLUsers;
import entity.StaffEntity;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Callback;
import main.Main;

import java.io.IOException;
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
    Label label2,label3,label4;
    @FXML
    TextField textField;
    @FXML
    TextField textField2, textField3,textField4;
    @FXML
    RadioButton laborantRadioButton,doctorRadioButton,adminRadioButton;
    @FXML
    Button button;
    ToggleGroup toggleGroup;

    private String sql;

    @FXML
    private void initialize(){
        queryUtil = new QueryUtil(Main.getOurSessionFactory());
        toggleGroup = new ToggleGroup();
        laborantRadioButton.setToggleGroup(toggleGroup);
        doctorRadioButton.setToggleGroup(toggleGroup);
        adminRadioButton.setToggleGroup(toggleGroup);
    }

    @FXML
    private void queryButtonHandler(){
        ResultSetMetaData meta;
        ResultSet resultSet;

        writeXML();
        if(textField.getText().isEmpty())
            resultSet =  queryUtil.createQuery(sql);
        else {
            queryUtil.clearParams();
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
                alert.setHeaderText("Error when add user");
                alert.setContentText(e.getMessage());

                alert.showAndWait();
            }
        }
        stage.close();
    }

    private void writeXML(){
        String param = textField.getText() + ";"+ textField2.getText()+";";
        if(laborantRadioButton.isSelected())
            param += "laboratory:"+textField3.getText();
        else if (doctorRadioButton.isSelected()) {
            StaffDao staffDao = new StaffDao(Main.getOurSessionFactory());
            StaffEntity staffEntity = staffDao.getByFIO(textField3.getText());

            if(staffEntity!=null) {
                param += "doctor:"+staffEntity.getId()+";";
            }
            param += "office:" + textField4.getText();
        }

        try {
            XMLUsers.addUser(param);
        } catch (IOException e) {
            e.printStackTrace();

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(stage);
            alert.setTitle("Error");
            alert.setHeaderText("Error when add user in XML");
            alert.setContentText(e.getMessage());

            alert.showAndWait();
        }
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
                if(str.length>=3) {
                    label3.setText(str[3]);
                    if (str.length >= 4) {
                        label4.setText(str[4]);
                    }
                }
            }
        }
    }
    @FXML
    private void labarantRadioButtonHandler() {
        label3.setText("laboratory");
        setVisibleThirdParam();
    }

    @FXML
    private void doctorRadioButtonHandler() {
        label3.setText("Doctor");
        label4.setText("Office");
        setVisibleThirdParam();
        setVisibleFouthParam();
    }

    @FXML
    private void adminRadioButtonHandler() {
        setInvisibleThirdParam();
        setInvisibleFouthParam();
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

        setInvisibleThirdParam();
        setInvisibleFouthParam();
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

    private void setVisibleThirdParam(){
        textField3.setVisible(true);
        textField3.disableProperty().setValue(false);
    }

    private void setInvisibleThirdParam(){
        label3.setText("");
        textField3.setVisible(false);
        textField3.disableProperty().setValue(true);
    }

    private void setVisibleFouthParam(){
        textField4.setVisible(true);
        textField4.disableProperty().setValue(false);
    }

    private void setInvisibleFouthParam(){
        label4.setText("");
        textField4.setVisible(false);
        textField4.disableProperty().setValue(true);
    }


    public void setStage(Stage stage){
        this.stage = stage;
    }
}
