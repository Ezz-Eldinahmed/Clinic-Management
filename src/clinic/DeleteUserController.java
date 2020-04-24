package clinic;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DeleteUserController implements Initializable {

    Methods m = new Methods();
    @FXML
    private PasswordField pass;
    @FXML
    private TextField user;
    @FXML
    private Font x1;
    @FXML
    private Button delete;
    @FXML
    private Font x2;
    @FXML
    private PasswordField pa;
    @FXML
    private Button Back;
    @FXML
    private Button close;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void deleting(ActionEvent event) {
        if (pass.getText().equals(pa.getText())) {
            try {
                Connection conn = m.Connet();
                String sql = "delete from UsersClinic where name =? and password=?;";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, user.getText());
                ps.setString(2, pass.getText());
                ps.execute();
                m.alerting(Alert.AlertType.CONFIRMATION, "Delete", "successfully Deleted!");
                m.switching("Usermanger.fxml", event);
            } catch (ClassNotFoundException | SQLException | IOException ex) {
                Logger.getLogger(SearchPatientController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            m.alerting(Alert.AlertType.ERROR, "Password",
                    "Password and password verification are not matching");
        }
    }

    @FXML
    private void Backing(ActionEvent event) throws IOException {
        m.switching("Usermanger.fxml", event);
    }

    @FXML
    private void closing(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }
}
