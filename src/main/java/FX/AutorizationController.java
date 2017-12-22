package FX;

import dao.QueryUtil;
import dbUsers.XMLUsers;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import main.Main;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 * Created by lapko on 18.12.2017.
 */
public class AutorizationController {
    @FXML
    TextField login;
    @FXML
    PasswordField password;
    @FXML
    Button signIn;
    @FXML
    Button cancel;
    @FXML
    RadioButton admin,labarant, doctor;

    ToggleGroup toggleGroup;
    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void initialize(){
        toggleGroup = new ToggleGroup();
        admin.setToggleGroup(toggleGroup);
        labarant.setToggleGroup(toggleGroup);
        doctor.setToggleGroup(toggleGroup);
    }

    @FXML
    private void signInHandler(){
        try {
            Configuration configuration = new Configuration()
                                                    .setProperty("hibernate.connection.username",login.getText())
                                                    .setProperty("hibernate.connection.password",password.getText());
            configuration.configure();
            SessionFactory ourSessionFactory = configuration.buildSessionFactory();
            Main.setOurSessionFactory(ourSessionFactory);

            if(!XMLUsers.read(login.getText(),password.getText()))
                throw new Exception();

            Main.loggedUserLogin = login.getText();
            Main.loggedUserPassword = password.getText();

            if (((RadioButton)toggleGroup.getSelectedToggle()).getText().equals("Admin")) {
                Main.windowToShow =  1;
            }else if(((RadioButton)toggleGroup.getSelectedToggle()).getText().equals("Labarant")) {
                Main.windowToShow = 2;
            }else if(((RadioButton)toggleGroup.getSelectedToggle()).getText().equals("Doctor")) {
                Main.windowToShow = 3;
            }

            cancelHandler();
        } catch (Throwable ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Authentication error");
            alert.setHeaderText("Login or password is incorrect");

            alert.showAndWait();
        }
    }

    @FXML
    private void cancelHandler(){
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }
}
