package clinic;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ExistpatientController implements Initializable {

    public static ObservableList<Patient> t = FXCollections.observableArrayList();

    @FXML
    public TableColumn<Patient, String> identifer;
    @FXML
    public TableColumn<Patient, String> nameOf;
    @FXML
    public TableColumn<Patient, String> kindo;
    @FXML
    public TableColumn<Patient, String> dateo;
    @FXML
    public TableColumn<Patient, String> weight;
    @FXML
    public TableColumn<Patient, String> dr0;
    @FXML
    public TableColumn<Patient, String> age0;
    @FXML
    public TableColumn<Patient, String> phone0;
    @FXML
    public TableColumn<Patient, String> r0;
    @FXML
    private TableView viewexistens;
    @FXML
    private Button close;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        identifer.setCellValueFactory(new PropertyValueFactory("id"));
        nameOf.setCellValueFactory(new PropertyValueFactory("name"));
        kindo.setCellValueFactory(new PropertyValueFactory("kind"));
        dateo.setCellValueFactory(new PropertyValueFactory("Todayvisit"));
        weight.setCellValueFactory(new PropertyValueFactory("weight"));
        dr0.setCellValueFactory(new PropertyValueFactory("dr"));
        age0.setCellValueFactory(new PropertyValueFactory("age"));
        phone0.setCellValueFactory(new PropertyValueFactory("phone"));
        r0.setCellValueFactory(new PropertyValueFactory("r"));
        viewexistens.setItems(t);
    }

    @FXML
    private void closeing(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }
}
