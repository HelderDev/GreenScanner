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
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.bean.User;
import model.dao.UserDAO;

/**
 *
 * @author hdrhe
 */
public class LoginController implements Initializable {

    @FXML
    private TextField titleField;
    @FXML
    private TextField nameField;

    @FXML
    private void handleButtonAction(ActionEvent event) {
//        System.out.println();
//        label.setText("Hello World!");

//        byte[] probeImage;
//        try{
//            probeImage = Files.readAllBytes(Paths.get("C:\\Users\\amorimhe\\Documents\\NetBeansProjects\\GreenScanner\\src\\greenscanner\\probe.png"));
// 
//            byte[] candidateImage = Files.readAllBytes(Paths.get("C:\\Users\\amorimhe\\Documents\\NetBeansProjects\\GreenScanner\\src\\greenscanner\\probe.png"));
//
//            FingerprintTemplate probe = new FingerprintTemplate()
//                    .dpi(500)
//                    .create(probeImage);
//
//            FingerprintTemplate candidate = new FingerprintTemplate()
//                    .dpi(500)
//                    .create(candidateImage);
//        }
//         catch (IOException ex) {
//            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
//    }
//        System.out.println("teste");
//        Date date = new Date(); // this object contains the current date value
////SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        User u = new User();
//
//        UserDAO dao = new UserDAO();
//        u.setTitle(titleField.getText());
//        u.setName(nameField.getText());
//
//        u.setPermission(1);
//        u.setCreation(formatter.format(date));
//        dao.create(u);
//        readTable();
        Stage stage = new Stage();

        UserDAO dao = new UserDAO();

        if (dao.checkLogin(nameField.getText(), titleField.getText())) {

            try {
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

                // Hide this current window (if this is what you want)
                ((Node) (event.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Usuário inválido!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
