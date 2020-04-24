package clinic;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DrTableViewController implements Initializable {

    Methods m = new Methods();
    @FXML
    private TableView DataTable;
    @FXML
    private TableColumn<Patient, Integer> id;
    @FXML
    private TableColumn<Patient, String> Name;
    @FXML
    private TableColumn<Patient, String> kind;
    @FXML
    private TableColumn<Patient, String> Date;
    @FXML
    private TableColumn<Patient, Double> weight;
    @FXML
    private TableColumn<Patient, String> Dr;
    @FXML
    private TableColumn<Patient, Double> Age;
    @FXML
    private TableColumn<Patient, String> phone;
    @FXML
    private TableColumn<Patient, String> R;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            selectAll();
        } catch (ClassNotFoundException | SQLException ex) {
        }
    }

    public void selectAll() throws ClassNotFoundException, SQLException {
        ObservableList<Patient> p = TableOfPatient();
        id.setCellValueFactory(new PropertyValueFactory("id"));
        Name.setCellValueFactory(new PropertyValueFactory("name"));
        kind.setCellValueFactory(new PropertyValueFactory("kind"));
        Date.setCellValueFactory(new PropertyValueFactory("Todayvisit"));
        weight.setCellValueFactory(new PropertyValueFactory("weight"));
        Dr.setCellValueFactory(new PropertyValueFactory("dr"));
        Age.setCellValueFactory(new PropertyValueFactory("age"));
        phone.setCellValueFactory(new PropertyValueFactory("phone"));
        R.setCellValueFactory(new PropertyValueFactory("r"));
        DataTable.getItems().addAll(p);
    }

    public ObservableList<Patient> TableOfPatient() {
        try {
            String sql = "SELECT * FROM paitent";
            Connection conn = m.Connet();
            ObservableList<Patient> p = FXCollections.observableArrayList();
            ResultSet rs = conn.prepareStatement(sql).executeQuery();
            while (rs.next()) {
                int idd = rs.getInt("id");
                String Na = rs.getString("name");
                String knd = rs.getString("kind");
                String dateofvisit = rs.getString("dateOfvisit");
                Double weighta = rs.getDouble("weight");
                String drr = rs.getString("dr");
                Double aage = rs.getDouble("age");
                String phonee = rs.getString("phone");
                String r = rs.getString("r");
                p.add(new Patient(idd, Na, knd, dateofvisit, weighta, drr, aage, phonee, r));
            }
            return p;
        } catch (ClassNotFoundException | SQLException ex) {
            m.alerting(Alert.AlertType.ERROR, "Error", "Table Is Empty");
        }
        return null;
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        m.switching("homeDr.fxml", event);
    }
}
