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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.bean.PlantationPesticide;
import static model.bean.PlantationPesticide.id_plantation;
import model.dao.PlantationPesticideDAO;

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

 
    public void readTable(int pest) {
        pestTable.getItems().clear();
        String perm;
        PlantationPesticideDAO pdao = new PlantationPesticideDAO();
        for (PlantationPesticide p : pdao.pesticides(pest)) {
 
            pesticida.setCellValueFactory(new PropertyValueFactory<>("pestName"));

            permissao.setCellValueFactory(new PropertyValueFactory<>("allowed"));

 
            pestTable.getItems().add(p);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
        pestTable.setPlaceholder(new Label("Plantação não possui pesticidas."));

        readTable(id_plantation);
        permissao.setStyle("-fx-alignment: CENTER;");

    }

}
