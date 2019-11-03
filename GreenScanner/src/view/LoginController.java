/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.machinezoo.sourceafis.*;
import java.io.File;
import java.io.FileReader;
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
import model.bean.FingerReader;
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
            String path = new File("src/view/images/fingerprints").getAbsolutePath();

            probeImage = Files.readAllBytes(Paths.get(path + "/101_1.tif"));
            byte[] candidateImage = Files.readAllBytes(Paths.get(path + "/teste.tif"));

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
            System.out.println("Serialize: " + candidate.serialize());
            double score = new FingerprintMatcher()
                    .index(probe)
                    .match(candidate);

            double threshold = 340;
            boolean matches = score >= threshold;
            System.out.println("Matches: " + matches);
            System.out.println("Score:" + score);

            Gson gson = new Gson();
            // 1. JSON file to Java object

            // 2. JSON string to Java object
            String json = "{'name' : 'mkyong'}";

            // 3. JSON file to JsonElement, later String
            String result = gson.toJson(json);

            System.out.println("Altura: " + loadUserFromJSONGson(candidate.serialize()).getHeight());
            System.out.println("Largura: " + loadUserFromJSONGson(candidate.serialize()).getWidth());
            System.out.println("Version: " + loadUserFromJSONGson(candidate.serialize()).getVersion());
            System.out.println("minutiae 1: " + loadUserFromJSONGson(candidate.serialize()).getMinutiae().get(0));
            System.out.println("minutiae 2: " + loadUserFromJSONGson(candidate.serialize()).getMinutiae().get(1));
            System.out.println("minutiae 3: " + loadUserFromJSONGson(candidate.serialize()).getMinutiae().get(2));
            System.out.println("minutiae 4: " + loadUserFromJSONGson(candidate.serialize()).getMinutiae().get(3));

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    public FingerReader loadUserFromJSONGson(String jsonString) {
        Gson gson = new Gson();
        FingerReader user = gson.fromJson(jsonString, FingerReader.class);

        return user;
    }

}
