/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.book.category;

import com.eby.animations.FadeInLeftTransition;
import com.eby.animations.FadeOutUpTransition;
import com.eby.frameworkConfig.Config;
import com.eby.main.ControlledScreen;
import com.eby.main.ScreensController;
import com.eby.orm.entity.Category;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author eby
 */
public class CategoryController implements Initializable, ControlledScreen {

    @FXML
    private TextField txtNama;
    @FXML
    private ListView<String> listParent;
    @FXML
    private Button btAdd;
    @FXML
    private TableView<Category> tableCategory;
    @FXML
    private Button btEdit;
    @FXML
    private Button btDelete;
    @FXML
    private Button btUpdate;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtCari;
    @FXML
    private FontAwesomeIconView iconClose;
    @FXML
    private GridPane grid1;
    @FXML
    private FontAwesomeIconView upIcon;
    @FXML
    private FontAwesomeIconView plusIcon;
    @FXML
    private FontAwesomeIconView editIcon;
    @FXML
    private FontAwesomeIconView deleteIcon;
    @FXML
    private AnchorPane paneView;
    @FXML
    private FontAwesomeIconView cariIcon;

    private CategoryTableModel tableModel;
    private CategoryModel model;
    private CategoryListModel listModel;
    private Config con;
    private ScreensController screensController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initModel();
        initTable();
        initListView();
        fadeIn();
    }

    @FXML
    private void AddAction(ActionEvent event) {

        String id = txtId.getText();
        String nama = txtNama.getText();

        if (nama.equals("")) {
            con.dialog(Alert.AlertType.WARNING, "isi data dengan lengkap", null);
        } else {

            Category cat = new Category();
            cat.setNama(nama);

            //mengambil index dari listParent
            int index = listParent.getSelectionModel().getSelectedIndex();
            if (index != -1) {
                //inisialisasi objek parent dengan isi index diatas
                Category parent = listModel.get(index);
                //set index diatas kedalam entity Category
                cat.setCategory(parent);
            }

            //eksekusi methode save dari class CategoryModel
            model.save(cat);

            clear();
            con.dialog(Alert.AlertType.INFORMATION, "Data berhasil disimpan", null);
            loadData();
            initListView();
        }
    }

    @FXML
    private void editAction(ActionEvent event) {
        //Mengambil index pada table ketika cell table di klik
        int index = tableCategory.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            //inisialisasi objek cat dengan index diatas
            Category cat = tableModel.getItem().get(index);

            //set isi textField dengan nilai Entity berdasarkan primary key
            txtId.setText(String.valueOf(cat.getId()));
            txtNama.setText(cat.getNama());
        } else {
            con.dialog(Alert.AlertType.WARNING, "Pilih data terlebih dahulu !", null);
        }

    }

    @FXML
    private void deleteAction(ActionEvent event) {
        //mengambil index dari tableCategory
        int index = tableCategory.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            //inisialisasi objek category dengan index diatas
            Category category = tableModel.getItem().get(index);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Yakin ingin menghapus data?");
            alert.setHeaderText(null);
            //menambahkan opsi cancel pada dialog informasi
            alert.getButtonTypes().addAll(ButtonType.CANCEL);
            Optional<ButtonType> choose = alert.showAndWait();
            if (choose.get() == ButtonType.OK) {
                //jika memilih tombol OK maka eksekusi delete
                model.delete(category);
                con.dialog(Alert.AlertType.INFORMATION, "Data berhasil di hapus !", null);
                loadData();
                initListView();
            } else {

            }

        } else {
            con.dialog(Alert.AlertType.WARNING, "Pilih data terlebih dahulu !", null);
        }
    }

    public void initTable() {
        //inisialisasi objek tableModel
        tableModel = new CategoryTableModel();
        //mengeset isi data dari tableModel kedalam tableCategory
        tableCategory.setItems(tableModel.getItem());
        //membuat column pada tableCategory seperti pada tableModel
        tableCategory.getColumns().addAll(tableModel.getAllColumn());
        //set isi dari Category kedalam tableModel
        tableModel.getItem().addAll(model.list());
    }

    @FXML
    private void updateAction(ActionEvent event) {
        String id = txtId.getText();
        String nama = txtNama.getText();

        if (nama.equals("")) {
            con.dialog(Alert.AlertType.WARNING, "isi data dengan lengkap", null);
        } else {

            Category cat = new Category();
            cat.setId(Integer.valueOf(id));
            cat.setNama(nama);

            //mengambil index dari listParent
            int index = listParent.getSelectionModel().getSelectedIndex();
            if (index != -1) {
                //inisialisasi objek parent dengan isi index diatas
                Category parent = listModel.get(index);
                //set index diatas kedalam entity Category
                cat.setCategory(parent);
            }

            //eksekusi methode save dari class CategoryModel
            model.update(cat);

            clear();
            con.dialog(Alert.AlertType.INFORMATION, "Update data berhasil !", null);
            loadData();
            initListView();
        }

    }

    public void initListView() {
        //inisialisasi objek listModel
        listModel = new CategoryListModel();
        //menambahkan isi data dari category ke listModel
        listModel.addList(model.list());
        //mengeset isi data dari list model ke listParent
        listParent.setItems(listModel.getItems());
    }

    public void initModel() {
        //inisialisasi objek model supaya tidak error null pointer
        model = new CategoryModel();
        //set COntroller yang digunakan
        model.setController(this);
    }

    public void clear() {
        //set semua inputan node ke default/kosong/""
        txtId.setText("");
        txtNama.setText("");
        listParent.getSelectionModel().clearSelection();
    }

    public void loadData() {
        //refresh isi dari tableModel mulai dari index 0 sampai index.size
        tableModel.getItem().remove(0, tableModel.getItem().size());
        //mendapatkan isi tableModel dari database
        tableModel.getItem().addAll(model.list());
        //mengeset isi tableModel kedalam tableAdmin
        tableCategory.setItems(tableModel.getItem());

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        screensController = screenPage;
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
            tableModel.getItem().addAll(model.findByNama(keyword));
            tableCategory.setItems(tableModel.getItem());
        }
    }

    @FXML
    private void closeAction(MouseEvent event) {
        fadeOut();
    }

    public void fadeIn() {
        //Animasi masuk melalui sisi kiri
        new FadeInLeftTransition(btAdd).play();
        new FadeInLeftTransition(grid1).play();
        new FadeInLeftTransition(cariIcon).play();
        new FadeInLeftTransition(iconClose).play();
        new FadeInLeftTransition(tableCategory).play();
        new FadeInLeftTransition(btDelete).play();
        new FadeInLeftTransition(btEdit).play();
        new FadeInLeftTransition(btUpdate).play();
        new FadeInLeftTransition(upIcon).play();
        new FadeInLeftTransition(editIcon).play();
        new FadeInLeftTransition(deleteIcon).play();
        new FadeInLeftTransition(plusIcon).play();
        new FadeInLeftTransition(txtCari).play();
        new FadeInLeftTransition(cariIcon).play();
    }

    public void fadeOut() {
        //Animasi keluar ke atas
        new FadeOutUpTransition(btAdd).play();
        new FadeOutUpTransition(grid1).play();
        new FadeOutUpTransition(cariIcon).play();
        new FadeOutUpTransition(iconClose).play();
        new FadeOutUpTransition(tableCategory).play();
        new FadeOutUpTransition(btDelete).play();
        new FadeOutUpTransition(btEdit).play();
        new FadeOutUpTransition(btUpdate).play();
        new FadeOutUpTransition(upIcon).play();
        new FadeOutUpTransition(editIcon).play();
        new FadeOutUpTransition(deleteIcon).play();
        new FadeOutUpTransition(plusIcon).play();
        new FadeOutUpTransition(txtCari).play();
        new FadeOutUpTransition(paneView).play();
        new FadeOutUpTransition(cariIcon).play();
    }

}
