package clinic;

import static clinic.ChangeUserDataController.name;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class SearchToUpdateUserController implements Initializable {

    Methods m = new Methods();
    @FXML
    private TextField un;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void search(ActionEvent event) throws IOException {
        try {
            ChangeUserDataController.name = un.getText();
            Connection conn = m.Connet();
            String sqlSelect = "SELECT * FROM UsersClinic where name =?";
            PreparedStatement psa = conn.prepareStatement(sqlSelect);
            psa.setString(1, name);
            psa.execute();
            ResultSet rs = psa.executeQuery();
            if (rs.next()) {
                m.alerting(Alert.AlertType.CONFIRMATION, "Update", "Found");
                m.switching("ChangeUserData.fxml", event);
            } else {
                m.alerting(Alert.AlertType.ERROR, "Update", "Not Found");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SearchToUpdateUserController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void Back(ActionEvent event) throws IOException {
        m.switching("Usermanger.fxml", event);
    }
}
