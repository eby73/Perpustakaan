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
    @FXML
    private TextField txtCari;

    private ListAdminTableModel tableModel;
    private ListAdminModel model;
    private Config con;
    private ScreensController screensController;

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

        //mengambil index yang dipilih pada tableAdmin
        int index = tableAdmin.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            //inisialisasi objek user dengan isi index diatas dimasukkan ke tableModel
            User user = tableModel.getItem().get(index);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Yakin ingin menghapus data?");
            alert.setHeaderText(null);
            //menambahkan opsi tombol cancel
            alert.getButtonTypes().addAll(ButtonType.CANCEL);
            Optional<ButtonType> choose = alert.showAndWait();
            if (choose.get() == ButtonType.OK) {
                //jika tombol OK dipilih maka methode delete akan di eksekusi
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
        //inisialisasi objek tableModel
        tableModel = new ListAdminTableModel();
        //mengeset isi dati tableModel kedalam tableAdmin
        tableAdmin.setItems(tableModel.getItem());
        //Menambah Column pada tableAdmin seperti yang ada pada tableModel
        tableAdmin.getColumns().addAll(tableModel.getAllColumn());
        //menambah isi data dari database kedalam tableModel
        tableModel.getItem().addAll(model.list());
    }

    public void initModel() {
        //inisialisasi objek model
        model = new ListAdminModel();
        //setController yang digunakan
        model.setController(this);
    }

    public void loadData() {
        //refresh isi dari tableModel mulai dari index 0 sampai index.size
        tableModel.getItem().remove(0, tableModel.getItem().size());
        //mendapatkan isi tableModel dari database
        tableModel.getItem().addAll(model.list());
        //mengeset isi tableModel kedalam tableAdmin
        tableAdmin.setItems(tableModel.getItem());
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        screensController = screenPage;
    }

    @FXML
    private void mouseAction(ActionEvent event) {
        //mengambil index yang dipilih pada tableAdmin
        int index = tableAdmin.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            //inisialisasi onjek user dengan isi index diatas
            User user = tableModel.getItem().get(index);
            //mengaktifkan EditAdminController untuk eksekusi methode setDataAdmin
            ControllerHelper.getEditAdminController(screensController).setDataAdmin(user);
            //setScreen
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
            //eksekusi loadData jika inputan pencarian kosong/null/""
            loadData();
        } else {
            tableModel.getItem().remove(0, tableModel.getItem().size());
            //mengeset data dari database berdasarkan pencarian dengan query like
            tableModel.getItem().addAll(model.findData(keyword));
            tableAdmin.setItems(tableModel.getItem());
        }
    }

}
