package clinic;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Font;

public class ViewUsersController implements Initializable {

    Methods m = new Methods();
    @FXML
    private TableColumn<Users, String> UN;
    @FXML
    private TableColumn<Users, String> P;
    @FXML
    private TableColumn<Users, String> D;
    @FXML
    private Button back;
    @FXML
    private TableView TableUser;
    @FXML
    private TableColumn<Users, String> type;
    @FXML
    private TableColumn<Users, String> admin;
    @FXML
    private Font x1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            selectAll();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ViewUsersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void selectAll() throws ClassNotFoundException, SQLException {
        ObservableList<Users> p = TableOfUsers();
        UN.setCellValueFactory(new PropertyValueFactory("name"));
        P.setCellValueFactory(new PropertyValueFactory("password"));
        type.setCellValueFactory(new PropertyValueFactory("type"));
        D.setCellValueFactory(new PropertyValueFactory("Date"));

        admin.setCellValueFactory(cellData -> {
            boolean admin = cellData.getValue().getAdmin();
            String a;
            if (admin == true) {
                a = "Admin";
            } else {
                a = "Ordianry";
            }
            return new ReadOnlyStringWrapper(a);
        });
        TableUser.getItems().addAll(p);
    }

    public ObservableList<Users> TableOfUsers() throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM UsersClinic";
        Connection conn = m.Connet();
        ObservableList<Users> ob = FXCollections.observableArrayList();
        ResultSet rs = conn.prepareStatement(sql).executeQuery();
        while (rs.next()) {
            String name = rs.getString("name");
            String pass = rs.getString("password");
            String type = rs.getString("type");
            String date = rs.getString("date");
            Boolean admin = rs.getBoolean("admin");
            ob.add(new Users(name, pass, type, date, admin));
        }
        return ob;
    }

    @FXML
    private void backing(ActionEvent event) throws IOException {
        m.switching("Usermanger.fxml", event);
    }
}
