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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.bean.User;
import model.dao.UserDAO;

/**
 *
 * @author hdrhe
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label label;
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
        System.out.println("teste");
        Date date = new Date(); // this object contains the current date value
//SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        User u = new User();

        UserDAO dao = new UserDAO();
        u.setTitle(titleField.getText());
        u.setName(nameField.getText());

        u.setPermission(1);
        u.setCreation(formatter.format(date));
        dao.create(u);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
