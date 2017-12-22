package FX;

import dao.CrudDao;
import dao.QueryUtil;
import dao.daoImpl.DiseaseDao;
import dbUsers.XMLUsers;
import entity.DiseaseEntity;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import main.Main;

import java.sql.SQLException;

/**
 * Created by lapko on 22.12.2017.
 */
public class DoctorFinishAppointmentController {
    QueryUtil queryUtil = new QueryUtil(Main.getOurSessionFactory());
    @FXML
    TextField textField;
    private int idPatience;
    private String since;
    private Stage stage;


    @FXML
    private void confirmButtonHandler(){
        try {
            DiseaseDao dao = new DiseaseDao(Main.getOurSessionFactory());
            DiseaseEntity disease = dao.getDisesaeByName(textField.getText());
            if (!textField.getText().isEmpty() && (disease == null))
                throw new Exception(textField.getText());
            queryUtil.createUpdate("Update appointment SET appointment.id_disease = " + disease.getId() + " , appointment.to_ = NOW() WHERE appointment.id_doctor = " + XMLUsers.getDoctorByUser(Main.loggedUserLogin, Main.loggedUserPassword) + " and appointment.id_patience = " + idPatience + " and appointment.id_office=" + XMLUsers.getOfficeByUser(Main.loggedUserLogin, Main.loggedUserPassword) + " and appointment.since_='" + since + "'");
            stage.close();
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Disease error");
            alert.setContentText("Disease "+e.getMessage()+" does not exists");
            alert.showAndWait();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setIdPatience(int id){
        idPatience = id;
    }

    public void setSince(String since) {
        this.since = since;
    }

}
