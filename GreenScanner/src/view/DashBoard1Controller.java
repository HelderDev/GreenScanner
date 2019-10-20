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
        for (Plantation p : pdao.read(u)) {
            this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
          //  id_owner.setCellValueFactory(new PropertyValueFactory<>("id_owner"));
            address.setCellValueFactory(new PropertyValueFactory<>("address"));
            city.setCellValueFactory(new PropertyValueFactory<>("city"));
            state.setCellValueFactory(new PropertyValueFactory<>("state"));
            
            plantsTable.getItems().add(p);
        }
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //  System.out.println("ID DashBoard: " + ud.getUser().getId());
        welcomeLabel.setText("Bem vindo " + titleName + " " + userName);
        readTable(idValue);
    }
    
}
