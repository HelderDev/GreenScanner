/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import model.bean.PlantationPesticide;
import static model.bean.PlantationPesticide.blockedUser;
import static model.bean.PlantationPesticide.idPestUser;
import static model.bean.PlantationPesticide.id_plantation;
import model.dao.PlantationPesticideDAO;
import static model.dao.UserDAO.permission;
import static model.bean.PlantationPesticide.*;
import static model.dao.PlantationPesticideDAO.*;

/**
 *
 * @author hdrhe
 */
public class PesticidesController implements Initializable {

    @FXML
    private TableView<PlantationPesticide> pestTable;
    @FXML
    private TableColumn<PlantationPesticide, String> pesticida;
    @FXML
    private TableColumn<PlantationPesticide, String> permissao;
    @FXML
    private CheckBox checkbox;
    // blockedUser = false;

    public void readTable(int pest) {
        pestTable.getItems().clear();
        PlantationPesticideDAO pdao = new PlantationPesticideDAO();
        for (PlantationPesticide p : pdao.pesticides(pest)) {

            pesticida.setCellValueFactory(new PropertyValueFactory<>("pestName"));

            permissao.setCellValueFactory(new PropertyValueFactory<>("allowed"));

            pestTable.getItems().add(p);

        }

    }

    public void notifyUser() {
        if (warning) {
            if (checkbox.isSelected()) {
                checkbox.setText("Usuário notificado");
                checkbox.setTooltip(new Tooltip("Usuário será notificado e bloqueado pelo uso indevido de pesticidas"));
                PlantationPesticideDAO pdao = new PlantationPesticideDAO();
                pdao.notifyUser(idPestUser);
                checkbox.setDisable(true);

            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        warning = false;

        readTable(id_plantation);

        checkbox.setTooltip(new Tooltip("Notificar usuário sobre uso indevido de pesticidas"));

        if (permission != 3) {
            checkbox.setVisible(false);
        }
        if (blockedUser) {
            checkbox.setDisable(true);
            checkbox.setText("Usuário notificado");
            checkbox.setTooltip(new Tooltip("Usuário notificado e bloqueado pelo uso indevido de pesticidas"));
        } else if (!warning) {
            checkbox.setVisible(false);

        }

        pestTable.setPlaceholder(new Label("Plantação não possui pesticidas."));

        permissao.setStyle("-fx-alignment: CENTER;");

    }

}
