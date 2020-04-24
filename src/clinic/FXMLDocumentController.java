package clinic;

import java.awt.BorderLayout;
import java.awt.SplashScreen;
import java.io.File;
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
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JWindow;

public class FXMLDocumentController implements Initializable {

    static String x;
    Methods m = new Methods();
    @FXML
    private AnchorPane loading;
    @FXML
    private Button login;
    @FXML
    private Label label;
    @FXML
    private PasswordField password;
    @FXML
    private TextField id;
    @FXML
    private Font x2;
    @FXML
    private Color x1;
    @FXML
    private Button close;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SplashScreen splash1 = SplashScreen.getSplashScreen();
        File file1 = new File("C:\\Users\\Ezz-Eldin\\Documents\\NetBeansProjects\\clinic\\src\\clinic\\baby.png");
        String imgfile1 = file1.getAbsolutePath();
        JWindow splash = new JWindow();
        JPanel content = (JPanel) splash.getContentPane();
        splash.setBounds(700, 300, 500, 400);
        JLabel t = new JLabel(new ImageIcon(imgfile1));
        content.add(t, BorderLayout.CENTER);
        splash.setVisible(true);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        splash.setVisible(false);
        splash.dispose();
    }

    @FXML
    private void loginAction(ActionEvent event) throws IOException {
        try {
            Connection conn = Methods.Connet();
            String sql = "SELECT * FROM UsersClinic where name=? and password =?";
            Users u = new Users(id.getText(), password.getText());
            PreparedStatement psa = conn.prepareStatement(sql);
            psa.setString(1, u.getName());
            psa.setString(2, u.getPassword());
            psa.execute();
            ResultSet rs = psa.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String pass = rs.getString("password");
                String type = rs.getString("type");
                x = name;
                if (pass.equals(password.getText()) && name.equals(id.getText())) {
                    if (type.equals("Recitpion")) {
                        m.alerting(AlertType.CONFIRMATION, "Login", "successfully Loged in as " + name + " R");
                        m.switching("ReciptionHome.fxml", event);
                    } else if (type.equals("Doctor")) {
                        m.alerting(AlertType.CONFIRMATION, "Login", "successfully Loged in as " + name + " D");
                        m.switching("homeDr.fxml", event);
                    }
                }
            } else {
                m.alerting(AlertType.ERROR, "Login", "Incorrect User name or password");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void closeing(ActionEvent event) {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }
}
