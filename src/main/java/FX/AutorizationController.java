package FX;

import dao.QueryUtil;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
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

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void initialize(){
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
