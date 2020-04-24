package clinic;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class TablePatientReciptionController implements Initializable {

    Methods m = new Methods();
    @FXML
    private Button back;
    @FXML
    private TableView rectav;
    @FXML
    private TableColumn<Patient, Integer> id;
    @FXML
    private TableColumn<Patient, String> name;
    @FXML
    private TableColumn<Patient, String> kind;
    @FXML
    private TableColumn<Patient, String> date;
    @FXML
    private TableColumn<Patient, Double> weight;
    @FXML
    private TableColumn<Patient, String> dr;
    @FXML
    private TableColumn<Patient, Double> age;
    @FXML
    private TableColumn<Patient, String> phone;
    @FXML
    private TableColumn<Patient, String> r;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            selectAll();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TablePatientReciptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        m.switching("ReciptionHome.fxml", event);
    }

    public void selectAll() throws ClassNotFoundException, SQLException {
        ObservableList<Patient> p = TableOfPatient();
        id.setCellValueFactory(new PropertyValueFactory("id"));
        name.setCellValueFactory(new PropertyValueFactory("name"));
        kind.setCellValueFactory(new PropertyValueFactory("kind"));
        date.setCellValueFactory(new PropertyValueFactory("Todayvisit"));
        weight.setCellValueFactory(new PropertyValueFactory("weight"));
        dr.setCellValueFactory(new PropertyValueFactory("dr"));
        age.setCellValueFactory(new PropertyValueFactory("age"));
        phone.setCellValueFactory(new PropertyValueFactory("phone"));
        r.setCellValueFactory(new PropertyValueFactory("r"));
        rectav.getItems().addAll(p);
    }

    public ObservableList<Patient> TableOfPatient() throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM paitent";
        Connection conn=m.Connet();
        ObservableList<Patient> p = FXCollections.observableArrayList();
        ResultSet rs = conn.prepareStatement(sql).executeQuery();
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
            p.add(new Patient(idd, Na, knd, dateofvisit, weightt, drra, aage, phonee, rrr));
        }
        return p;
    }
}
