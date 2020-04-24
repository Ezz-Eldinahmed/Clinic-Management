package clinic;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class UsersCreationController implements Initializable {

    Methods m = new Methods();
    @FXML
    private Color x1;
    @FXML
    private PasswordField password;
    @FXML
    private TextField name;
    @FXML
    private PasswordField passwordagain;
    @FXML
    private RadioButton doc;
    @FXML
    private RadioButton rec;
    @FXML
    private Button close;
    @FXML
    private ToggleGroup type;
    @FXML
    private CheckBox admin;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            CreateUsersDb();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsersCreationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void loginAction(ActionEvent event) {
        DateTimeFormatter d = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        try {
            Connection conn = m.Connet();
            Users u = new Users(name.getText(), password.getText(), ((RadioButton) type.getSelectedToggle()).getText(), d.format(now), admin.isSelected());
            String sqlelect = "select * from UsersClinic where name = ?";
            PreparedStatement psa = conn.prepareStatement(sqlelect);
            psa.setString(1, u.getName());
            psa.execute();
            ResultSet rs = psa.executeQuery();
            if (rs.next()) {
                m.alerting(Alert.AlertType.WARNING, "Insert",
                        "this User name have been registerd before");
                return;
            }
            if (passwordagain.getText().equals(password.getText())) {
                String sql = "INSERT INTO UsersClinic (name,password,type,date,admin)values (?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, u.getName());
                ps.setString(2, u.getPassword());
                ps.setString(3, u.getType());
                ps.setString(4, u.getDate());
                ps.setBoolean(5, u.getAdmin());
                ps.execute();
                m.alerting(Alert.AlertType.CONFIRMATION, "Insert", "successfully Insert User");
            } else {
                m.alerting(Alert.AlertType.ERROR, "Error", "Error to Create User");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UsermangerController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void CreateUsersDb() throws ClassNotFoundException, SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS UsersClinic(name text PRIMARY KEY ,password text,type text ,date text,admin text)";
        Connection conn = Methods.Connet();
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
    }

    @FXML
    private void closeing(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        m.switching("Usermanger.fxml", event);
    }
}
