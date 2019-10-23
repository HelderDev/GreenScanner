/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.machinezoo.sourceafis.FingerprintTemplate;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.bean.User;
import model.dao.UserDAO;
import static model.dao.UserDAO.titleName;
import static model.dao.UserDAO.userName;

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
    private void handleButtonAction(ActionEvent event) {
//        System.out.println();
//        label.setText("Hello World!");

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

                        // Hide this current window (if this is what you want)
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

                        // Hide this current window (if this is what you want)
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

                        // Hide this current window (if this is what you want)
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

}
