/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.admin.anggota.view;

import com.eby.frameworkConfig.Config;
import com.eby.helper.ControllerHelper;
import com.eby.orm.entity.Anggota;
import com.eby.main.ControlledScreen;
import com.eby.main.Main;
import com.eby.main.ScreensController;
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
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author eby
 */
public class ListAnggotaAdminController implements Initializable, ControlledScreen {

    @FXML
    private TableView<Anggota> tableAnggota;
    @FXML
    private Button btEdit;
    @FXML
    private Button btDelete;
    @FXML
    private Button btBack;
    @FXML
    private TextField txtCari;

    private ListAnggotaAdminTableModel tableModel;
    private Config config;
    private ListAnggotaAdminModel model;
    private ScreensController screenController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initModel();
        initTabel();
        config = new Config();
    }

    @FXML
    private void EditAction(ActionEvent event) {
    }

    @FXML
    private void deleteAction(ActionEvent event) {
        //Mengambil index pada table ketika cell table di klik
        int index = tableAnggota.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            //Memasukkan nilai index ke dalam object 
            Anggota a = tableModel.getItem().get(index);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Yakin ingin menghapus data?");
            alert.setHeaderText(null);
            //menambahkan opsi tombol cancel
            alert.getButtonTypes().addAll(ButtonType.CANCEL);
            Optional<ButtonType> choose = alert.showAndWait();
            if (choose.get() == ButtonType.OK) {
                //menghapus object yang terdapat pada index yang telah didapat
                model.delete(a);
                config.dialog(Alert.AlertType.INFORMATION, "Data berhasil di hapus !", null);
                loadData();
            } else {

            }
        } else {
            config.dialog(Alert.AlertType.WARNING, "Pilih data terlebih dahulu !", null);
        }

    }

    private void initTabel() {
        //inisialisasi isi dari object
        tableModel = new ListAnggotaAdminTableModel();
        //Menambah tableColum yang telah dibuat pada tableModel ke tableAnggota 
        tableAnggota.getColumns().addAll(tableModel.getAllColumn());
        //tableModel mengambil data pada database untuk ditampung sementara sebelum ditampilkan di tableAnggota
        tableModel.getItem().addAll(model.list());
        //mengeset isi yang terdapat pada table model kedalam tableAnggota
        tableAnggota.setItems(tableModel.getItem());
    }

    public void loadData() {
        //Melakukan refresh cell pada table, dengan menghapus dari mulai index 0 - size dari cell table
        tableModel.getItem().remove(0, tableModel.getItem().size());
        //tableModel mengambil data pada database untuk ditampung sementara sebelum ditampilkan di tableAnggota
        tableModel.getItem().addAll(model.list());
        //mengeset isi yang terdapat pada table model kedalam tableAnggota
        tableAnggota.setItems(tableModel.getItem());

    }

    private void initModel() {
        //inisialisasi nilai dari object model
        model = new ListAnggotaAdminModel();
        //mengeset controller yang akan dipakai oleh model
        model.setController(this);
    }

    @FXML
    private void editMouseClicked(MouseEvent event) {
        //Mengambil index pada table ketika cell table di klik
        int index = tableAnggota.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            //Memasukkan nilai index ke dalam object
            Anggota anggota = tableModel.getItem().get(index);
            //Mengambil controller yang sedang aktif dan melakukan set data ke form EditAdmin
            ControllerHelper.getEditAdminController(screenController).setDataAnggota(anggota);
            //Melakukan pergantian form ke EditAdmin form
            screenController.setScreen(Main.editAdminId);
        } else {
            config.dialog(Alert.AlertType.WARNING, "Pilih data terlebih dahulu !", null);
        }

    }

    //implementasi dari interface ControlledScreen
    @Override
    public void setScreenParent(ScreensController screenPage) {
        //inisialisasi nilai dari object screenController dengan nilai screenPage
        screenController = screenPage;
    }

    @FXML
    private void backAction(ActionEvent event) {
        screenController.setScreen(Main.menuAnggotaID);
    }

    @FXML
    private void cariAction(KeyEvent event) {
        String keyword = txtCari.getText();
        if (keyword.equals("")) {
            //Melakukan loadData ketika teks pada textField cari tidak ada/null/""
            loadData();
        } else {
            //melakukan pencarian ketika textField cari tidak kosong/null/""
            tableModel.getItem().remove(0, tableModel.getItem().size());
            tableModel.getItem().addAll(model.findByName(keyword));
            tableAnggota.setItems(tableModel.getItem());
        }

    }
}
