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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
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
public class DashBoard3Controller implements Initializable {

    @FXML
    private Label welcomeLabel;

    @FXML
    private TableView<Plantation> plantsTable;
    @FXML
    private TableColumn<Plantation, String> idField;

    @FXML
    private TableColumn<Plantation, String> pnameField;
    @FXML
    private TableColumn<Plantation, String> addressField;
    @FXML
    private TableColumn<Plantation, String> cityField;
    @FXML
    private TableColumn<Plantation, String> stateField;

    @FXML
    private TableView<User> usersTable;
 
    @FXML
    private TableColumn<User, String> uID;
    @FXML
    private TableColumn<User, String> uName;
    @FXML
    private TableColumn<User, String> uTitle;
    @FXML
    private TableColumn<User, String> uPermission;
    @FXML
    private TableColumn<User, String> uCreation;

    ObservableList<User> oblist = FXCollections.observableArrayList();

    //  UserDAO ud = new UserDAO();
    @FXML
    private void handleButtonAction(ActionEvent event) {

        ObservableList<User> oblistUser;

        oblistUser = usersTable.getSelectionModel().getSelectedItems();

        System.out.println(oblistUser.get(0).getId());
    }

    public void readTable() {
        usersTable.getItems().clear();
        UserDAO udao = new UserDAO();
        for (User u : udao.read()) {

            uID.setCellValueFactory(new PropertyValueFactory<>("id"));
            uName.setCellValueFactory(new PropertyValueFactory<>("name"));
            uTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            uPermission.setCellValueFactory(new PropertyValueFactory<>("permission"));
            uCreation.setCellValueFactory(new PropertyValueFactory<>("creation"));
            usersTable.getItems().add(u);

        }

    }

    private void printRow(User item) {
        plantsTable.getItems().clear();

        PlantationDAO pdao = new PlantationDAO();

 
        for (Plantation p : pdao.readAll(item.getId())) {

            idField.setCellValueFactory(new PropertyValueFactory<>("id"));
    //        id_ownerField.setCellValueFactory(new PropertyValueFactory<>("id_owner"));
            pnameField.setCellValueFactory(new PropertyValueFactory<>("name"));
            addressField.setCellValueFactory(new PropertyValueFactory<>("address"));
            cityField.setCellValueFactory(new PropertyValueFactory<>("city"));
            stateField.setCellValueFactory(new PropertyValueFactory<>("state"));

            plantsTable.getItems().add(p);

        }

    }

    private void showPesticides(Plantation item) {
        id_plantation = item.getId();
        PlantationPesticideDAO pdao = new PlantationPesticideDAO();
        //PlantationPesticide plantPest = new PlantationPesticide(item.getId());
        Stage stage = new Stage();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("images/fertilizer.png")));
        stage.titleProperty().setValue("Pesticidas");
        stage.setResizable(false);

        try {

            Parent root = FXMLLoader.load(getClass().getResource("Pesticides.fxml"));

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            // Hide this current window (if this is what you want)
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        for (PlantationPesticide p : pdao.pesticides(item.getId())) {
            PesticidesController pest = new PesticidesController();
            pest.readTable(item.getId());

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        welcomeLabel.setText("Bem-vindo " + titleName + " " + userName + "!");
        readTable();
        uID.setStyle("-fx-alignment: CENTER;");
        uPermission.setStyle("-fx-alignment: CENTER;");
        idField.setStyle("-fx-alignment: CENTER;");
     //   id_ownerField.setStyle("-fx-alignment: CENTER;");

        plantsTable.setPlaceholder(new Label("Usuário não possui plantações."));

        usersTable.setRowFactory(tv -> {
            TableRow<User> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY
                        && event.getClickCount() == 1) {

                    User clickedRow = row.getItem();
                    printRow(clickedRow);
                }
            });
            return row;
        });

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
        // ...

    }

}
