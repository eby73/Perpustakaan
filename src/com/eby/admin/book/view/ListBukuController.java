/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.admin.book.view;

import com.eby.book.add.BukuAddController;
import com.eby.helper.ControllerHelper;
import com.eby.frameworkConfig.Config;
import com.eby.orm.entity.Buku;
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
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author eby
 */
public final class ListBukuController implements Initializable, ControlledScreen {

    private AnchorPane paneView;
    @FXML
    private Button btEdit;
    @FXML
    private Button btDelete;
    @FXML
    private TableView<Buku> tableBuku;
    @FXML
    private Button btBack;
    @FXML
    private TextField txtCari;

    private ListBukuTableModel tableModel;
    private BukuAddController bukuAdd;
    private ListBukumodel model;
    private ControllerHelper helper;
    private ScreensController screenController;
    private Config con;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initModel();
        initTable();
        con = new Config();
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        screenController = screenPage;
    }

    @FXML
    private void editAction(ActionEvent event) {
    }

    @FXML
    private void deleteAction(ActionEvent event) {
        //mengambil index dari tableBuku
        int index = tableBuku.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            //inisialisasi objek buku dengan index diatas
            Buku buku = tableModel.getItem().get(index);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Yakin ingin menghapus data?");
            alert.setHeaderText(null);
            //menambahkan opsi cancel pada dialog informasi
            alert.getButtonTypes().addAll(ButtonType.CANCEL);
            Optional<ButtonType> choose = alert.showAndWait();
            if (choose.get() == ButtonType.OK) {
                //jika memilih tombol OK maka eksekusi delete
                model.delete(buku);
                con.dialog(Alert.AlertType.INFORMATION, "Data berhasil di hapus !", null);
                loadData();
            } else {

            }
        } else {
            con.dialog(Alert.AlertType.WARNING, "Pilih data terlebih dahulu !", null);
        }

    }

    @FXML
    private void tableBukuAction(MouseEvent event) {

    }

    public void initTable() {
        //inisialisasi objek tableModel
        tableModel = new ListBukuTableModel();
        //mengeset isi data dari tableModel kedalam tableBuku
        tableBuku.setItems(tableModel.getItem());
        //membuat column pada tableBuku seperti pada tableModel
        tableBuku.getColumns().addAll(tableModel.getAllColumn());
        //set isi dari Buku kedalam tableModel
        tableModel.getItem().addAll(model.list());
    }

    public void initModel() {
        //inisisalisasi objek model
        model = new ListBukumodel();
        //set controller yang akan digunakan
        model.setController(this);
    }

    @FXML
    private void editMouseEvent(MouseEvent event) {
        //mengambil index dari tableBuku
        int index = tableBuku.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            //inisialisasi objek buku dengan index diatas
            Buku buku = tableModel.getItem().get(index);
            //mengaktifkan controller BukuEditAdmin untuk eksekusi methode setData
            ControllerHelper.getBukuEditAdmin(screenController).setData(buku);
            //setScreen ke bukuEditAdmin
            screenController.setScreen(Main.bukuEditAdminID);
        } else {
            con.dialog(Alert.AlertType.WARNING, "Pilih data terlebih dahulu !", null);
        }
    }

    @FXML
    private void backAction(ActionEvent event) {
        screenController.setScreen(Main.MenuBukuID);
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
            //eksekusi loadData jika txtCari kosong/null/""
            loadData();
        } else {
            //eksekusi methode pencrian jika != null
            tableModel.getItem().remove(0, tableModel.getItem().size());
            tableModel.getItem().addAll(model.findData(keyword));
            tableBuku.setItems(tableModel.getItem());
        }
    }
}
