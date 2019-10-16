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
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.bean.Plantation;
import model.bean.User;
import model.dao.PlantationDAO;
import static model.dao.PlantationDAO.owner;
import static model.dao.PlantationDAO.title;
import model.dao.UserDAO;
import static model.dao.UserDAO.idValue;

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
    private TableColumn<User, String> pID;
    @FXML
    private TableColumn<User, String> pName;
    @FXML
    private TableColumn<User, String> pOwner;
    @FXML
    private TableColumn<User, String> pAddress;
    @FXML
    private TableColumn<User, String> pCity;
    @FXML
    private TableColumn<User, String> pState;
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

    //ObservableList<Plantation> oblist = FXCollections.observableArrayList();
    //  UserDAO ud = new UserDAO();
    @FXML
    private void handleButtonAction(ActionEvent event) {
//
//        Date date = new Date();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//
//        //User u = new User();
//        Plantation p = new Plantation();
//        UserDAO dao = new UserDAO();
//        u.setTitle(titleField.getText());
//        u.setName(nameField.getText());
//
//        u.setPermission(1);
//        u.setCreation(formatter.format(date));
//        dao.create(u);
//        readTable();
    }

    public void readTable() {
        usersTable.getItems().clear();
        UserDAO udao = new UserDAO();
        for (User u : udao.readAll()) {
//            pID.setCellValueFactory(new PropertyValueFactory<>("id"));
//            pName.setCellValueFactory(new PropertyValueFactory<>("name"));
//            pOwner.setCellValueFactory(new PropertyValueFactory<>("id_owner"));
//            pAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
//            pCity.setCellValueFactory(new PropertyValueFactory<>("city"));
//            pState.setCellValueFactory(new PropertyValueFactory<>("state"));

            uID.setCellValueFactory(new PropertyValueFactory<>("id"));
            uName.setCellValueFactory(new PropertyValueFactory<>("name"));
            uTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
            uPermission.setCellValueFactory(new PropertyValueFactory<>("permission"));
            uCreation.setCellValueFactory(new PropertyValueFactory<>("creation"));
            usersTable.getItems().add(u);
            
            
        }
        
       
        nameField.setText(owner);
        titleField.setText(title);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //  System.out.println("ID DashBoard: " + ud.getUser().getId());

        readTable();
    }

}
