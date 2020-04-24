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
import javafx.scene.paint.Color;

public class ReciptionHomeController implements Initializable {

    Methods m = new Methods();
    @FXML
    private Button view;
    @FXML
    private Button ADD;
    @FXML
    private Button remove;
    @FXML
    private Button edit;
    @FXML
    private Color x1;
    private Button menu;
    @FXML
    private Button manage;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void tableOfRecitptionView(ActionEvent event) throws IOException {
        m.switching("TablePatientReciption.fxml", event);
    }

    @FXML
    private void TableRecitptionADD(ActionEvent event) throws IOException {
        m.switching("resciption.fxml", event);
    }

    @FXML
    private void RemoveRecitptiontable(ActionEvent event) throws IOException {
        SearchPatientController.backToPatient = "R";
        m.switching("searchPatient.fxml", event);
    }

    @FXML
    private void TableRecitptionEdit(ActionEvent event) throws IOException {
        SearchTogetToupdateController.backTOpatient = "R";
        m.switching("searchTogetToupdate.fxml", event);
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        m.switching("FXMLDocument.fxml", event);
    }

    @FXML
    private void manageing(ActionEvent event) throws IOException, ClassNotFoundException, SQLException {
        UsermangerController.backto = "R";
        boolean a = m.IsAdmin();
        if (a == true) {
            m.switching("Usermanger.fxml", event);
        } else {
            m.alerting(Alert.AlertType.WARNING, "Login", "You Couldn't Access Here");
        }
    }
}
