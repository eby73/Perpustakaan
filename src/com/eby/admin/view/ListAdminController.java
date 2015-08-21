/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.admin.view;

import com.eby.main.ControlledScreen;
import com.eby.main.ScreensController;
import com.eby.main.Main;
import com.eby.helper.ControllerHelper;

import com.eby.frameworkConfig.Config;
import com.eby.orm.entity.User;

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
public class ListAdminController implements Initializable, ControlledScreen {

    @FXML
    private TableView<User> tableAdmin;
    @FXML
    private Button btEdit;
    @FXML
    private Button btDelete;

    private ListAdminTableModel tableModel;
    private ListAdminModel model;
    private Config con;
    private ScreensController screensController;
    @FXML
    private TextField txtCari;
    @FXML
    private Button btRefresh;

    /**
     * Initializes the controller class.
     *
     * @param url
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initModel();
        initTable();
        con = new Config();
    }

    @FXML
    private void deleteAction(ActionEvent event) {

        int index = tableAdmin.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            User user = tableModel.getItem().get(index);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Yakin ingin menghapus data?");
            alert.setHeaderText(null);
            alert.getButtonTypes().addAll(ButtonType.CANCEL);
            Optional<ButtonType> choose = alert.showAndWait();
            if (choose.get() == ButtonType.OK) {
                model.delete(user);
                con.dialog(Alert.AlertType.INFORMATION, "Data berhasil di hapus !", null);
                loadData();
            } else {

            }
        } else {
            con.dialog(Alert.AlertType.WARNING, "Pilih data terlebih dahulu !", null);
        }

    }

    public void initTable() {
        try {
            tableModel = new ListAdminTableModel();
            tableAdmin.setItems(tableModel.getItem());
            tableAdmin.getColumns().addAll(tableModel.getAllColumn());
            tableModel.getItem().addAll(model.list());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void initModel() {
        model = new ListAdminModel();
        model.setController(this);
    }

    public void loadData() {
        tableModel.getItem().remove(0, tableModel.getItem().size());
        tableModel.getItem().addAll(model.list());
        tableAdmin.setItems(tableModel.getItem());
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        screensController = screenPage;
    }

    @FXML
    private void mouseAction(ActionEvent event) {
        int index = tableAdmin.getSelectionModel().getSelectedIndex();

        if (index != -1) {
            User user = tableModel.getItem().get(index);
            ControllerHelper.getEditAdminController(screensController).setDataAdmin(user);
            screensController.setScreen(Main.editAdminId);
        } else {
            con.dialog(Alert.AlertType.WARNING, "Pilih data terlebih dahulu !", null);
        }

    }

    @FXML
    private void backAction(ActionEvent event) {
        screensController.setScreen(Main.MenuUtamaAdminID);
    }

    @FXML
    private void cariAction(KeyEvent event) {
        String keyword = txtCari.getText();
        if (keyword.equals("")) {
            loadData();
        } else {
            try {
                tableModel.getItem().remove(0, tableModel.getItem().size());
                tableModel.getItem().addAll(model.findData(keyword));
                tableAdmin.setItems(tableModel.getItem());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @FXML
    private void refreshAction(ActionEvent event) {
        loadData();
    }

}
