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
public class DashBoard1Controller implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private TextField titleField;
    @FXML
    private TextField nameField;
    @FXML
    private TableView<Plantation> plantsTable;
    @FXML
    private TableColumn<Plantation, String> id;
    @FXML
    private TableColumn<Plantation, String> name;
    @FXML
    private TableColumn<Plantation, String> id_owner;
    @FXML
    private TableColumn<Plantation, String> address;
    @FXML
    private TableColumn<Plantation, String> city;
    @FXML
    private TableColumn<Plantation, String> state;
    
    ObservableList<User> oblist = FXCollections.observableArrayList();

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
    
    public void readTable(int u) {
        plantsTable.getItems().clear();
        PlantationDAO pdao = new PlantationDAO();
        for (Plantation p : pdao.read(u)) {
            this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            id_owner.setCellValueFactory(new PropertyValueFactory<>("id_owner"));
            address.setCellValueFactory(new PropertyValueFactory<>("address"));
            city.setCellValueFactory(new PropertyValueFactory<>("city"));
            state.setCellValueFactory(new PropertyValueFactory<>("state"));
            
            plantsTable.getItems().add(p);
        }
        nameField.setText(owner);
       titleField.setText(title);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //  System.out.println("ID DashBoard: " + ud.getUser().getId());

        readTable(idValue);
    }
    
}
