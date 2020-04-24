package clinic;

import static clinic.SearchForUpdateController.id;
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

public class SearchTogetToupdateController implements Initializable {

    @FXML
    private Button search;
    @FXML
    private TextField textgetid;

    static String backTOpatient;
    Methods m = new Methods();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void searchingg(ActionEvent event) throws IOException {

        try {
            SearchForUpdateController.id = textgetid.getText();
            Connection conn = m.Connet();
            String sqlSelect = "SELECT * FROM paitent where id =?";
            PreparedStatement psa = conn.prepareStatement(sqlSelect);
            psa.setInt(1, Integer.parseInt(id));
            psa.execute();
            ResultSet rs = psa.executeQuery();
            if (rs.next()) {
                m.switching("SearchForUpdate.fxml", event);
            } else {
                m.alerting(Alert.AlertType.ERROR, "Update", "Not Found Id");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(SearchTogetToupdateController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void backing(ActionEvent event) throws IOException {
        if (backTOpatient.equals("R")) {
            m.switching("ReciptionHome.fxml", event);
        } else if (backTOpatient.equals("D")) {
            m.switching("homeDr.fxml", event);
        }
    }
}
