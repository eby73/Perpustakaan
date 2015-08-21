/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.operator.book.view;

import com.eby.admin.book.view.ListBukuTableModel;
import com.eby.admin.book.view.ListBukumodel;
import com.eby.book.add.BukuAddController;
import com.eby.frameworkConfig.Config;
import com.eby.helper.ControllerHelper;
import com.eby.main.ControlledScreen;
import com.eby.main.Main;
import com.eby.main.ScreensController;
import com.eby.orm.entity.Buku;
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
public class ListBukuOperatorController implements Initializable, ControlledScreen {

    @FXML
    private Button btBack;
    @FXML
    private Button btEdit;
    @FXML
    private Button btDelete;

    private ListBukuOperatorTableModel tableModel;
    private BukuAddController bukuAdd;
    private ListBukuOperatorModel model;
    private Config con;
    private ScreensController screensController;
    @FXML
    private TableView<Buku> tableBuku;
    @FXML
    private TextField txtCari;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initModel();
        initTable();
        con = new Config();
    }

    @FXML
    private void backAction(ActionEvent event) {
        screensController.setScreen(Main.mainMenuOperator);
    }

    @FXML
    private void editAtion(ActionEvent event) {
        int index = tableBuku.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            Buku buku = tableModel.getItem().get(index);
            ControllerHelper.getBukuEditOperator(screensController).setData(buku);
            screensController.setScreen(Main.bukuEditOperatorID);
        } else {
            con.dialog(Alert.AlertType.WARNING, "Pilih data terlebih dahulu !", null);
        }
    }

    @FXML
    private void deleteAction(ActionEvent event) {
        int index = tableBuku.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            Buku buku = tableModel.getItem().get(index);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Yakin ingin menghapus data?");
            alert.setHeaderText(null);
            alert.getButtonTypes().addAll(ButtonType.CANCEL);
            Optional<ButtonType> choose = alert.showAndWait();
            if (choose.get() == ButtonType.OK) {
                model.delete(buku);
                con.dialog(Alert.AlertType.INFORMATION, "Data berhasil di hapus !", null);
                loadData();
            } else {

            }
        } else {
            con.dialog(Alert.AlertType.WARNING, "Pilih data terlebih dahulu !", null);
        }
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        screensController = screenPage;
    }

    public void initTable() {
        tableModel = new ListBukuOperatorTableModel();
        tableBuku.setItems(tableModel.getItem());
        tableBuku.getColumns().addAll(tableModel.getAllColumn());
        tableModel.getItem().addAll(model.list());
    }

    public void initModel() {
        model = new ListBukuOperatorModel();
        model.setController(this);
    }

    public void loadData() {
        tableModel.getItem().remove(0, tableModel.getItem().size());
        tableModel.getItem().addAll(model.list());
        tableBuku.setItems(tableModel.getItem());
    }

    @FXML
    private void cariAction(KeyEvent event) {
        String keyword = txtCari.getText();
        if (keyword.equals("")) {
            loadData();
        } else {
            tableModel.getItem().remove(0, tableModel.getItem().size());
            tableModel.getItem().addAll(model.findData(keyword));
            tableBuku.setItems(tableModel.getItem());
        }
    }

}
