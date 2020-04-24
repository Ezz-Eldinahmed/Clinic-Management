package clinic;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Methods {

    public void switching(String s, ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(s));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Clinic");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("baby.png")));
        stage.show();
        Node node = (Node) event.getSource();
        stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    public static Connection Connet() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        return conn;
    }

    public void alerting(Alert.AlertType type, String title, String Content) {
        Alert alert = new Alert(type);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setTitle(title);
        alert.setContentText(Content);
        alert.showAndWait();
    }

    public boolean IsAdmin() throws ClassNotFoundException, SQLException {
        Connection conn = Methods.Connet();
        String sql = "SELECT * FROM UsersClinic where name=?";
        Users u = new Users();
        PreparedStatement psa = conn.prepareStatement(sql);
        psa.setString(1, FXMLDocumentController.x);
        psa.execute();
        ResultSet rs = psa.executeQuery();
        if (rs.next()) {
            String admin = rs.getString("admin");
            if (admin.equals("TRUE")) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
