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

/**
 *
 * @author hdrhe
 */
public class PesticidesController implements Initializable {

    @FXML
    private TableView<PlantationPesticide> pestTable;
//    @FXML
//    private TableColumn<PlantationPesticide, String> pestID;
//    @FXML
//    private TableColumn<PlantationPesticide, String> plantation;
    @FXML
    private TableColumn<PlantationPesticide, String> pesticida;
    @FXML
    private TableColumn<PlantationPesticide, String> permissao;

    ObservableList<PlantationPesticide> oblist = FXCollections.observableArrayList();

    //  UserDAO ud = new UserDAO();
    public PesticidesController() {
    }

    public void readTable(int pest) {
        pestTable.getItems().clear();
        String perm;
        PlantationPesticideDAO pdao = new PlantationPesticideDAO();
        for (PlantationPesticide p : pdao.pesticides(pest)) {
            //   plantation.setCellValueFactory(new PropertyValueFactory<>("plantName"));

            pesticida.setCellValueFactory(new PropertyValueFactory<>("pestName"));

            permissao.setCellValueFactory(new PropertyValueFactory<>("allowed"));

//                      plantation.setCellValueFactory(new PropertyValueFactory<>("name"));
//            pestID.setCellValueFactory(new PropertyValueFactory<>("allowed"));
//             
            pestTable.getItems().add(p);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //  System.out.println("ID DashBoard: " + ud.getUser().getId());
        pestTable.setPlaceholder(new Label("Plantação não possui pesticidas."));

        readTable(id_plantation);
        permissao.setStyle("-fx-alignment: CENTER;");

    }

}
