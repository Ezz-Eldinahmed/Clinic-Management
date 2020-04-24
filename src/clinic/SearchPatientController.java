package clinic;

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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SearchPatientController implements Initializable {

    Methods m = new Methods();
    @FXML
    private Button search;
    @FXML
    private TextField id;
    @FXML
    private Button close;

    static String backToPatient;

    @FXML
    private void searchingFordelete(ActionEvent event) {
        try {
            Connection conn = m.Connet();
            String sqlSelect = "SELECT * FROM paitent where id =?";
            PreparedStatement psa = conn.prepareStatement(sqlSelect);
            psa.setString(1, id.getText());
            psa.execute();
            ResultSet rs = psa.executeQuery();
            if (rs.next()) {
                String sql = "delete from paitent where id =?;";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, id.getText());
                ps.execute();
                m.alerting(Alert.AlertType.CONFIRMATION, "Delete", "successfully Deleted!");
                m.switching("homeDr.fxml", event);
            } else {
                m.alerting(Alert.AlertType.ERROR, "Update", "Not Found Id");
            }
        } catch (ClassNotFoundException | SQLException | IOException ex) {
            Logger.getLogger(SearchPatientController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void Backing(ActionEvent event) throws IOException {
        if (backToPatient.equals("R")) {
            m.switching("ReciptionHome.fxml", event);
        } else if (backToPatient.equals("D")) {
            m.switching("homeDr.fxml", event);
        }
    }

    @FXML
    private void Closing(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }
}
