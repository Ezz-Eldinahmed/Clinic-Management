package clinic;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ChangeUserDataController implements Initializable {

    Methods m = new Methods();
    static String name;
    @FXML
    private Button update;
    @FXML
    private Color x1;
    @FXML
    private PasswordField p;
    @FXML
    private TextField na;
    @FXML
    private Button close;
    @FXML
    private PasswordField pa;
    @FXML
    private RadioButton docto;
    @FXML
    private ToggleGroup type;
    @FXML
    private RadioButton re;
    @FXML
    private Button back;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection conn = m.Connet();
            String sqlSelect = "SELECT * FROM UsersClinic where name =?";
            PreparedStatement psa = conn.prepareStatement(sqlSelect);
            psa.setString(1, name);
            psa.execute();
            ResultSet rs = psa.executeQuery();
            while (rs.next()) {
                na.setText(rs.getString("name"));
                p.setText(rs.getString("password"));
                String s = rs.getString("type");
                if (s.equals("Doctor")) {
                    docto.setSelected(true);
                } else if (s.equals("Recitpion")) {
                    re.setSelected(true);
                }
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SearchForUpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void updateing(ActionEvent event) {
        if (p.getText().equals(pa.getText())) {
            try {
                ArrayList<Patient> a = new ArrayList<>();
                Connection conn = m.Connet();
                String sqlUpdate = "Update UsersClinic set name = ? ,password =?, type=? where name = ?";
                PreparedStatement ps = conn.prepareStatement(sqlUpdate);
                ps.setString(1, na.getText());
                ps.setString(2, p.getText());
                ps.setString(3, ((RadioButton) type.getSelectedToggle()).getText());
                ps.setString(4, na.getText());
                ps.executeUpdate();
                m.alerting(Alert.AlertType.CONFIRMATION, "Update", "Updated");
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(SearchForUpdateController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            m.alerting(Alert.AlertType.ERROR, "Password", "Password and password verification are not matching");
        }
    }

    @FXML
    private void closeing(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void backing(ActionEvent event) throws IOException {
        m.switching("Usermanger.fxml", event);
    }
}
