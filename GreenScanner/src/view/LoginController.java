/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.machinezoo.sourceafis.*;
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
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import model.bean.FingerPrint;
import model.dao.FingerPrintDAO;
import model.dao.UserDAO;

/**
 *
 * @author hdrhe
 */
public class LoginController implements Initializable {

    @FXML
    private void fingerPrint(ActionEvent event) {
        try {
            int cfp = 0;
            JFileChooser jfc;
            jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            jfc.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter filter = new FileNameExtensionFilter(".tif", "tif");
            jfc.addChoosableFileFilter(filter);

            jfc.setDialogTitle("Selecione o arquivo para entrar no GreenScanner");
            int returnValue = jfc.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                try {
                    checkFingerprint(Files.readAllBytes(Paths.get(jfc.getSelectedFile().getAbsolutePath())));
                    cfp = checkFingerprint(Files.readAllBytes(Paths.get(jfc.getSelectedFile().getAbsolutePath())));
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao buscar arquivo, tente novamente mais tarde");
                }
            }

            if (cfp != 0) {
                Stage stage = new Stage();
                stage.getIcons().add(new Image(getClass().getResourceAsStream("images/sprout.png")));
                stage.titleProperty().setValue("DashBoard");
                // stage.setResizable(false);

                UserDAO dao = new UserDAO();
                switch (dao.checkPermission(cfp)) {
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
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Ocorreu um erro, tente novamente: " + e);

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    private int checkFingerprint(byte[] candidateByte) {

        FingerprintTemplate candidate = new FingerprintTemplate(
                new FingerprintImage()
                        .dpi(500)
                        .decode(candidateByte));

        FingerPrintDAO fpDAO = new FingerPrintDAO();

        for (FingerPrint finger : fpDAO.readAllFingers()) {
            FingerprintTemplate probe = new FingerprintTemplate();
            probe.deserialize(finger.getFinger_detail());
            

            double score = new FingerprintMatcher()
                    .index(probe)
                    .match(candidate);
                double threshold = 280;
            boolean matches = score >= threshold;
            if (matches) {
                return finger.getId_user();
            }

        }
        return 0;
    }

}
