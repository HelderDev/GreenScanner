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
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.bean.User;
import model.dao.UserDAO;

/**
 *
 * @author hdrhe
 */
public class DashBoard3Controller implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField titleField;
    @FXML
    private TextField nameField;
    @FXML
    private TableView<User> usersTable;
    @FXML
    private TableColumn<User, String> id;
    @FXML
    private TableColumn<User, String> name;
    @FXML
    private TableColumn<User, String> title;
    @FXML
    private TableColumn<User, String> permission;
    @FXML
    private TableColumn<User, String> creation;

    ObservableList<User> oblist = FXCollections.observableArrayList();

    @FXML
    private void handleButtonAction(ActionEvent event) {

        Date date = new Date(); 
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        User u = new User();

        UserDAO dao = new UserDAO();
        u.setTitle(titleField.getText());
        u.setName(nameField.getText());

        u.setPermission(1);
        u.setCreation(formatter.format(date));
        dao.create(u);
        readTable();
    }

    public void readTable() {
        usersTable.getItems().clear();
        UserDAO udao = new UserDAO();
        for (User u : udao.read()) {
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            title.setCellValueFactory(new PropertyValueFactory<>("title"));
            permission.setCellValueFactory(new PropertyValueFactory<>("permission"));
            creation.setCellValueFactory(new PropertyValueFactory<>("creation"));

            usersTable.getItems().add(u);

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        readTable();
    }

}
