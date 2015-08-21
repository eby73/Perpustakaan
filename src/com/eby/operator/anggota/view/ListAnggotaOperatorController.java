/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.operator.anggota.view;

import com.eby.frameworkConfig.Config;
import com.eby.helper.ControllerHelper;
import com.eby.main.ControlledScreen;
import com.eby.main.Main;
import com.eby.main.ScreensController;
import com.eby.orm.entity.Anggota;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

/**
 * FXML Controller class
 *
 * @author eby
 */
public class ListAnggotaOperatorController implements Initializable, ControlledScreen {

    @FXML
    private Button btBack;
    @FXML
    private Button btEdit;
    @FXML
    private Button btDelete;
    @FXML
    private TableView<Anggota> tableAnggota;
    @FXML
    private TextField txtCari;
    private ScreensController screensController;
    private ListAnggotaOpModel model;
    private ListAnggotaOpTableModel tableModel;
    private Config con;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initModel();
        initTable();
    }

    @FXML
    private void backAction(ActionEvent event) {
        screensController.setScreen(Main.menuAnggotaOpID);
    }

    @FXML
    private void editAction(ActionEvent event) {
        this.edit();
    }

    @FXML
    private void deleteAction(ActionEvent event) {
        this.delete();
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        screensController = screenPage;
    }

    public void initModel() {
        model = new ListAnggotaOpModel();
        model.setController(this);
    }

    public void initTable() {
        tableModel = new ListAnggotaOpTableModel();
        tableAnggota.getColumns().addAll(tableModel.getAllColumn());
        tableAnggota.setItems(tableModel.getItem());
        tableModel.getItem().addAll(model.list());
    }

    public void loadData() {
        tableModel.getItem().remove(0, tableModel.getItem().size());
        tableModel.getItem().addAll(model.list());
        tableAnggota.setItems(tableModel.getItem());
    }

    public void delete() {
        int index = tableAnggota.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            Anggota a = tableModel.getItem().get(index);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Yakin ingin menghapus data?");
            alert.setHeaderText(null);
            alert.getButtonTypes().addAll(ButtonType.CANCEL);
            Optional<ButtonType> choose = alert.showAndWait();
            if (choose.get() == ButtonType.OK) {
                model.delete(a);
                loadData();
                con.dialog(Alert.AlertType.INFORMATION, "Data berhasil dihapus !", null);
            } else {

            }

        } else {
            con.dialog(Alert.AlertType.WARNING, "Pilih data terlebih dahulu !", null);
        }
    }

    private void edit() {
        int index = tableAnggota.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            Anggota a = tableModel.getItem().get(index);
            ControllerHelper.getEditAnggotaOpCon(screensController).setData(a);
            screensController.setScreen(Main.anggotaEditOpID);
        } else {
            con.dialog(Alert.AlertType.WARNING, "Pilih data terlebih dahulu !", null);
        }
    }

    @FXML
    private void cariAction(KeyEvent event) {
        String keyword = txtCari.getText();
        if(keyword.equals("")){
            loadData();
        }else{
            tableModel.getItem().remove(0, tableModel.getItem().size());
        tableModel.getItem().addAll(model.findData(keyword));
        tableAnggota.setItems(tableModel.getItem());
        }
        
    }
}
