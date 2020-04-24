package clinic;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ResciptionController implements Initializable {

    Methods m = new Methods();
    @FXML
    private RadioButton drahmed;
    @FXML
    private ToggleGroup drs;
    @FXML
    private TextField name;
    @FXML
    private RadioButton drazza;
    @FXML
    private TextField age;
    @FXML
    private Button registery;
    @FXML
    private RadioButton consult;
    @FXML
    private ToggleGroup kind;
    @FXML
    private RadioButton reveal;
    @FXML
    private TextField phone;
    @FXML
    private TextField weight;
    @FXML
    private TextField r;
    @FXML
    private Button back;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void registerPatient(ActionEvent event) throws ClassNotFoundException, SQLException {
        createPatientRecititionviewTable();
        InsertPatientRecititionviewTable();
    }

    public static void createPatientRecititionviewTable() throws ClassNotFoundException, SQLException {
        Connection conn = Methods.Connet();
        String sql = "CREATE TABLE IF NOT EXISTS paitent(id integer PRIMARY KEY auto_increment ,name text ,kind text , dateOfvisit text ,weight real ,dr text,age real , phone text,r text)";
        Statement stmt = conn.createStatement();
        stmt.execute(sql);
    }

    public void InsertPatientRecititionviewTable() {
        try {
            ObservableList<Patient> ob = FXCollections.observableArrayList();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();
            Connection conn = m.Connet();
            Patient p = new Patient(name.getText(), ((RadioButton) kind.getSelectedToggle()).getText(),
                    dtf.format(now), Double.parseDouble(weight.getText()),
                    ((RadioButton) drs.getSelectedToggle()).getText(),
                    Double.parseDouble(age.getText()), phone.getText(), r.getText());
            String query = "SELECT * FROM paitent WHERE name = ?";
            PreparedStatement psa = conn.prepareStatement(query);
            psa.setString(1, p.getName());
            ResultSet rs = psa.executeQuery();
            if (rs.next()) {
                m.alerting(Alert.AlertType.INFORMATION, "Insert", "this patient have been registerd before");
                ExistpatientController.t.clear();
                while (rs.next()) {
                    int idd = rs.getInt("id");
                    String Na = rs.getString("name");
                    String knd = rs.getString("kind");
                    String dateofvisit = rs.getString("dateOfvisit");
                    Double weightt = rs.getDouble("weight");
                    String drra = rs.getString("dr");
                    Double aage = rs.getDouble("age");
                    String phonee = rs.getString("phone");
                    String rrr = rs.getString("r");
                    ExistpatientController.t.add(new Patient(idd, Na, knd, dateofvisit, weightt, drra, aage, phonee, rrr));
                }
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("existpatient.fxml"));
                Parent root1 = (Parent) fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Clinic");
                stage.getIcons().add(new Image(getClass().getResourceAsStream("baby.png")));
                stage.setScene(new Scene(root1));
                stage.show();
                m.alerting(Alert.AlertType.CONFIRMATION, "Insert", "successfully Insert data");
            }
            String sql = "INSERT INTO paitent (name,kind,dateOfvisit,weight,dr,age,phone,r)values (?,?,?,?,?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, p.getName());
            ps.setString(2, p.getkind());
            ps.setString(3, dtf.format(now));
            ps.setDouble(4, p.getWeight());
            ps.setString(5, p.getDr());
            ps.setDouble(6, p.getage());
            ps.setString(7, p.getphone());
            ps.setString(8, p.getR());
            ps.execute();
            m.alerting(Alert.AlertType.CONFIRMATION, "Insert", "successfully Insert data");
        } catch (SQLException | ClassNotFoundException | IOException exx) {
            Logger.getLogger(DrviewController.class.getName()).log(Level.SEVERE, null, exx);
        } catch (NullPointerException | NumberFormatException aa) {
            m.alerting(Alert.AlertType.ERROR, "Error", "Incorrect Entry "
                    + "\n"
                    + "Age and Weight are Only Numbers" + "\n"
                    + "Unchecked Doctor or visit type");
        }
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        m.switching("ReciptionHome.fxml", event);
    }
}
