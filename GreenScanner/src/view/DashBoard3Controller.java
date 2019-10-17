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
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
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
    private TableView<Plantation> plantsTable;
    @FXML
    private TableColumn<Plantation, String> idField;
    @FXML
    private TableColumn<Plantation, String> id_ownerField;
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
//    @FXML
//    private TableColumn<User, String> pID;
//    @FXML
//    private TableColumn<User, String> pName;
//    @FXML
//    private TableColumn<User, String> pOwner;
//    @FXML
//    private TableColumn<User, String> pAddress;
//    @FXML
//    private TableColumn<User, String> pCity;
//    @FXML
//    private TableColumn<User, String> pState;
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
        ObservableList<User> oblistUser;

        oblistUser = usersTable.getSelectionModel().getSelectedItems();

        System.out.println(oblistUser.get(0).getId());
    }

    // usersTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
    //  @Override
    // public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
    //Check whether item is selected and set value of selected item to Label
//        if(usersTable.getSelectionModel().getSelectedItem() != null) 
//        {    
//           TableViewSelectionModel selectionModel = usersTable.getSelectionModel();
//           ObservableList selectedCells = selectionModel.getSelectedCells();
//           TablePosition tablePosition = (TablePosition) selectedCells.get(0);
//           Object val = tablePosition.getTableColumn().getCellData(newValue);
//           System.out.println("Selected Value" + val);
//         }
    // });
    @FXML
    private void editTable(ActionEvent event) {
        nameField.setText("A");
    }

    public void readTable() {
        usersTable.getItems().clear();
        UserDAO udao = new UserDAO();
        for (User u : udao.read()) {
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

    private void printRow(User item) {
        plantsTable.getItems().clear();

        PlantationDAO pdao = new PlantationDAO();

        System.out.println(item.getId() + item.getPermission());

        for (Plantation p : pdao.readAll(item.getId())) {
//            pID.setCellValueFactory(new PropertyValueFactory<>("id"));
//            pName.setCellValueFactory(new PropertyValueFactory<>("name"));
//            pOwner.setCellValueFactory(new PropertyValueFactory<>("id_owner"));
//            pAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
//            pCity.setCellValueFactory(new PropertyValueFactory<>("city"));
//            pState.setCellValueFactory(new PropertyValueFactory<>("state"));

            idField.setCellValueFactory(new PropertyValueFactory<>("id"));
            id_ownerField.setCellValueFactory(new PropertyValueFactory<>("id_owner"));
            pnameField.setCellValueFactory(new PropertyValueFactory<>("name"));
            addressField.setCellValueFactory(new PropertyValueFactory<>("address"));
            cityField.setCellValueFactory(new PropertyValueFactory<>("city"));
            stateField.setCellValueFactory(new PropertyValueFactory<>("state"));

            plantsTable.getItems().add(p);

        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        readTable();
        uID.setStyle("-fx-alignment: CENTER;");
        uPermission.setStyle("-fx-alignment: CENTER;");
        idField.setStyle("-fx-alignment: CENTER;");
        id_ownerField.setStyle("-fx-alignment: CENTER;");

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
        // ...

    }

}
