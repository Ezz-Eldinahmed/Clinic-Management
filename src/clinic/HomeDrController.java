package clinic;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class HomeDrController implements Initializable {

    Methods m = new Methods();
    @FXML
    private Button view;
    @FXML
    private Button add;
    @FXML
    private Button remove;
    @FXML
    private Button edit;
    @FXML
    private Font x1;
    @FXML
    private Button manage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void tableOfDoctorView(ActionEvent event) throws IOException {
        m.switching("DrTableView.fxml", event);
    }

    @FXML
    private void RemoveDrtable(ActionEvent event) throws IOException {
        SearchPatientController.backToPatient = "D";
        m.switching("searchPatient.fxml", event);
    }

    @FXML
    private void TableDREdit(ActionEvent event) throws IOException {
        SearchTogetToupdateController.backTOpatient = "D";
        m.switching("searchTogetToupdate.fxml", event);
    }

    @FXML
    private void Addpatient(ActionEvent event) throws IOException {
        m.switching("drview.fxml", event);
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        m.switching("FXMLDocument.fxml", event);
    }

    @FXML
    private void Manageing(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        UsermangerController.backto = "D";
        boolean a = m.IsAdmin();
        if (a == true) {
            m.switching("Usermanger.fxml", event);
        } else {
            m.alerting(Alert.AlertType.WARNING, "Login", "You Couldn't Access Here");
        }
    }
}
