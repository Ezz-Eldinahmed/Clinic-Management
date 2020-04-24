package clinic;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class UsermangerController implements Initializable {

    Methods m = new Methods();
    static String backto;
    @FXML
    private Font x1;
    @FXML
    private Button Back;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void create(ActionEvent event) throws IOException {
        m.switching("UsersCreation.fxml", event);
    }

    @FXML
    private void Delete(ActionEvent event) throws IOException {
        m.switching("DeleteUser.fxml", event);
    }

    @FXML
    private void change(ActionEvent event) throws IOException {
        m.switching("searchToUpdateUser.fxml", event);
    }

    @FXML
    private void View(ActionEvent event) throws IOException {
        m.switching("ViewUsers.fxml", event);
    }

    @FXML
    private void Backing(ActionEvent event) throws IOException {
        if (backto.equals("R")) {
            m.switching("ReciptionHome.fxml", event);
        } else if (backto.equals("D")) {
            m.switching("homeDr.fxml", event);
        }
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        m.switching("FXMLDocument.fxml", event);
    }
}
