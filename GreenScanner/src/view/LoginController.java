/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.machinezoo.sourceafis.FingerprintImage;
import com.machinezoo.sourceafis.FingerprintTemplate;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.dao.UserDAO;

/**
 *
 * @author hdrhe
 */
public class LoginController implements Initializable {

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;

    @FXML
    private void login(ActionEvent event) {

        Stage stage = new Stage();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("images/sprout.png")));
        stage.titleProperty().setValue("DashBoard");
        // stage.setResizable(false);

        UserDAO dao = new UserDAO();

        if (dao.checkLogin(nameField.getText(), idField.getText())) {

            switch (dao.checkPermission(nameField.getText(), idField.getText())) {
                case 1:
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("DashBoard1.fxml"));

                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                        ((Node) (event.getSource())).getScene().getWindow().hide();
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                    break;
                case 2:
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("DashBoard2.fxml"));

                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                        ((Node) (event.getSource())).getScene().getWindow().hide();
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, "deu erro bixao");
                    }
                    break;
                case 3:
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("DashBoard3.fxml"));

                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();

                        ((Node) (event.getSource())).getScene().getWindow().hide();
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(null, e);
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Usuário inválido!");
                    ;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Usuário inválido!");
        }
    }

    @FXML
    private void fingerPrint() {
        byte[] probeImage;
        try {
            probeImage = Files.readAllBytes(Paths.get("C:\\Users\\amorimhe\\Documents\\NetBeansProjects\\Versioned GreenScanner\\GreenScanner\\src\\view\\probe.png"));
            byte[] candidateImage = Files.readAllBytes(Paths.get("C:\\Users\\amorimhe\\Documents\\NetBeansProjects\\Versioned GreenScanner\\GreenScanner\\src\\view\\probe.png"));

            FingerprintTemplate probe = new FingerprintTemplate(
                    new FingerprintImage()
                            .dpi(500)
                            .decode(probeImage));

            FingerprintTemplate candidate = new FingerprintTemplate(
                    new FingerprintImage()
                            .dpi(500)
                            .decode(candidateImage));

            System.out.println("Probe: " + probe);
            System.out.println("Candidate: " + candidate);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

}
