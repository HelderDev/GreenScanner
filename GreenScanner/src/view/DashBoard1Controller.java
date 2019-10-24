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
import java.util.List;
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
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.bean.Plantation;
import model.bean.PlantationPesticide;
import static model.bean.PlantationPesticide.id_plantation;
import model.bean.User;
import model.dao.PlantationDAO;
import static model.dao.PlantationDAO.owner;
import static model.dao.PlantationDAO.title;
import model.dao.PlantationPesticideDAO;
import model.dao.UserDAO;
import static model.dao.UserDAO.idValue;
import static model.dao.UserDAO.titleName;
import static model.dao.UserDAO.userName;

/**
 *
 * @author hdrhe
 */
public class DashBoard1Controller implements Initializable {

    @FXML
    private Label welcomeLabel;
    @FXML
    private TableView<Plantation> plantsTable;
    @FXML
    private TableColumn<Plantation, String> id;
    @FXML
    private TableColumn<Plantation, String> name;

    @FXML
    private TableColumn<Plantation, String> address;
    @FXML
    private TableColumn<Plantation, String> city;
    @FXML
    private TableColumn<Plantation, String> state;

    ObservableList<User> oblist = FXCollections.observableArrayList();

    public void readTable(int u) {
        plantsTable.getItems().clear();
        PlantationDAO pdao = new PlantationDAO();
        for (Plantation p : pdao.readByUser(u)) {
            this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            address.setCellValueFactory(new PropertyValueFactory<>("address"));
            city.setCellValueFactory(new PropertyValueFactory<>("city"));
            state.setCellValueFactory(new PropertyValueFactory<>("state"));

            plantsTable.getItems().add(p);
        }

    }

    private void showPesticides(Plantation item) {
        id_plantation = item.getId();
        PlantationPesticideDAO pdao = new PlantationPesticideDAO();
        Stage stage = new Stage();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("images/fertilizer.png")));
        stage.titleProperty().setValue("Pesticidas");
        stage.setResizable(false);

        try {

            Parent root = FXMLLoader.load(getClass().getResource("Pesticides.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        for (PlantationPesticide p : pdao.pesticides(item.getId())) {
            PesticidesController pest = new PesticidesController();
            pest.readTable(item.getId());

        }

    }

    @FXML
    private void logout(ActionEvent event) {
        Stage stage = new Stage();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("images/sprout.png")));
        stage.titleProperty().setValue("Login");
        stage.setResizable(false);
        String[] options = new String[2];
        options[0] = "Sim";
        options[1] = "Não";
        int x = JOptionPane.showOptionDialog(null, "Tem certeza que quer desconectar?",
                "Selecione uma opção",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
        if (x == 0) {
            try {
                Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

                ((Node) (event.getSource())).getScene().getWindow().hide();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        welcomeLabel.setText("Bem vindo " + titleName + " " + userName);
        readTable(idValue);

        plantsTable.setRowFactory(tv -> {
            TableRow<Plantation> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 2) {

                    Plantation clickedRow = row.getItem();
                    showPesticides(clickedRow);
                }
            });
            return row;
        });

    }

}
