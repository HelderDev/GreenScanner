/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import model.bean.Plantation;
import model.bean.PlantationPesticide;
import static model.bean.PlantationPesticide.*;
import model.bean.User;
import model.dao.PlantationDAO;
import model.dao.PlantationPesticideDAO;
import model.dao.UserDAO;
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
    @FXML
    private TextField searchField;

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

    public void readTable() {
        usersTable.getItems().clear();
        UserDAO udao = new UserDAO();
        for (User u : udao.readAll()) {

            uID.setCellValueFactory(new PropertyValueFactory<>("id"));
            uName.setCellValueFactory(new PropertyValueFactory<>("name"));
            uTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            uPermission.setCellValueFactory(new PropertyValueFactory<>("permission"));
            uCreation.setCellValueFactory(new PropertyValueFactory<>("creation"));
            usersTable.getItems().add(u);

        }

    }

    public void readLike(String user) {
        usersTable.getItems().clear();
        UserDAO udao = new UserDAO();
        for (User u : udao.readLike(user)) {

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
        idPestUser = item.getId();
        blockedUser = item.getBlocked();
        PlantationDAO pdao = new PlantationDAO();

        for (Plantation p : pdao.readByUser(item.getId())) {

            idField.setCellValueFactory(new PropertyValueFactory<>("id"));
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        welcomeLabel.setText("Bem-vindo " + titleName + " " + userName + "!");
        readTable();
        uID.setStyle("-fx-alignment: CENTER;");
        uPermission.setStyle("-fx-alignment: CENTER;");
        idField.setStyle("-fx-alignment: CENTER;");

        plantsTable.setPlaceholder(new Label("Usuário não possui plantações."));
        usersTable.setPlaceholder(new Label("Usuário inexistente."));

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

        searchField.addEventFilter(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                readLike(searchField.getText());

            }
        });

    }

}
