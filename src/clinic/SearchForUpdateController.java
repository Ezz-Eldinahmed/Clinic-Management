package clinic;

import static clinic.SearchTogetToupdateController.backTOpatient;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class SearchForUpdateController implements Initializable {

    Methods m = new Methods();
    public static String id;
    @FXML
    private Button Update;
    @FXML
    private RadioButton drahmed;
    @FXML
    private ToggleGroup dr;
    @FXML
    private TextField name;
    @FXML
    private RadioButton drazza;
    @FXML
    private TextField age;
    @FXML
    private TextField date;
    @FXML
    private RadioButton conaa;
    @FXML
    private ToggleGroup kind;
    @FXML
    private RadioButton reve;
    @FXML
    private TextField phone;
    @FXML
    private TextField weight;
    @FXML
    private TextField r;

    static String backToPatient;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Connection conn = m.Connet();
            String sqlSelect = "SELECT * FROM paitent where id =?";
            PreparedStatement psa = conn.prepareStatement(sqlSelect);
            psa.setInt(1, Integer.parseInt(id));
            psa.execute();
            ResultSet rs = psa.executeQuery();
            while (rs.next()) {
                name.setText(rs.getString("name"));
                String x = rs.getString("kind");
                if (x.equals("consult")) {
                    conaa.setSelected(true);
                } else if (x.equals("reveal")) {
                    reve.setSelected(true);
                }
                date.setText(rs.getString("dateOfvisit"));
                weight.setText(rs.getDouble("weight") + "");

                String s = rs.getString("dr");
                if (s.equals("Dr/Azza Ezz-Eldin")) {
                    drazza.setSelected(true);
                } else if (s.equals("Dr/Ahmed mustafa")) {
                    drahmed.setSelected(true);
                }
                age.setText(rs.getDouble("age") + "");
                phone.setText(rs.getString("phone"));
                r.setText(rs.getString("r"));
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SearchForUpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void updating(ActionEvent event) {
        try {
            ArrayList<Patient> a = new ArrayList<>();
            Connection conn = m.Connet();
            String sqlUpdate = "Update paitent set name = ? ,kind =?, dateOfvisit=?, weight=? ,dr=?, age=? ,phone=? ,r=? where id = ?";
            PreparedStatement ps = conn.prepareStatement(sqlUpdate);
            ps.setString(1, name.getText());
            ps.setString(2, ((RadioButton) kind.getSelectedToggle()).getText());
            ps.setString(3, date.getText());
            ps.setDouble(4, Double.parseDouble(weight.getText()));
            ps.setString(5, ((RadioButton) dr.getSelectedToggle()).getText());
            ps.setDouble(6, Double.parseDouble(age.getText()));
            ps.setString(7, phone.getText());
            ps.setString(8, r.getText());
            ps.setInt(9, Integer.parseInt(id));
            ps.executeUpdate();
            m.alerting(Alert.AlertType.CONFIRMATION, "Update", "Update sucess For Paitent have Id = " + id + " where it's name is " + name.getText());
        } catch (NullPointerException | NumberFormatException e) {
            m.alerting(Alert.AlertType.ERROR, "Error", "Incorrect Entry "
                    + "\n"
                    + "Age and Weight are Only Numbers");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SearchForUpdateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        if (backTOpatient.equals("R")) {
            m.switching("ReciptionHome.fxml", event);
        } else if (backTOpatient.equals("D")) {
            m.switching("homeDr.fxml", event);
        }
    }
}
