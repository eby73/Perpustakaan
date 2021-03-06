/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.operator.book.edit;

import com.eby.frameworkConfig.Config;
import com.eby.main.ControlledScreen;
import com.eby.main.Main;
import com.eby.main.ScreensController;
import com.eby.orm.entity.Buku;
import com.eby.orm.entity.Category;
import com.eby.orm.entity.Penerbit;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author eby
 */
public class BukuEditOperatorController implements Initializable, ControlledScreen {

    @FXML
    private TextField txtIdBuku;
    @FXML
    private TextField txtJudul;
    @FXML
    private TextField txtPengarang;
    @FXML
    private TextField txtTahunTerbit;
    @FXML
    private ComboBox<String> cbPenerbit;
    @FXML
    private ListView<String> listCategory;
    @FXML
    private TextField txtJumlahHalaman;
    @FXML
    private TextArea txtSynopsis;
    @FXML
    private Button btUpdate;

    private ScreensController screensController;
    private CategoryListModel listModel;
    private PenerbitComboBoxModel penComboModel;
    private Config con;
    private BukuEditOperatorModel model;
    @FXML
    private Button btBack;
    @FXML
    private Button btBackTo;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initModel();
        initCategoryListModel();
        initPenerbitComboModel();
        con = new Config();
    }

    public void setData(Buku buku) {
        txtIdBuku.setText(String.valueOf(buku.getId()));
        txtJudul.setText(buku.getJudul());
        listCategory.getSelectionModel().select(buku.getCategory().getNama());
        cbPenerbit.getSelectionModel().select(buku.getPenerbit().getNama());
        txtPengarang.setText(buku.getPengarang());
        txtTahunTerbit.setText(buku.getTahunTerbit());
        txtJumlahHalaman.setText(buku.getJumlahHalaman());
        txtSynopsis.setText(buku.getSynopsis());
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        screensController = screenPage;
    }

    @FXML
    private void updateAction(ActionEvent event) {
        Buku buku = new Buku();
        String id = txtIdBuku.getText();
        String judul = txtJudul.getText();
        int catId = listCategory.getSelectionModel().getSelectedIndex();
        int penId = cbPenerbit.getSelectionModel().getSelectedIndex();
        String pengarang = txtPengarang.getText();
        String tahun = txtTahunTerbit.getText();
        String jumlah = txtJumlahHalaman.getText();
        String synopsis = txtSynopsis.getText();

        if (id != null && judul != null && catId != -1 && penId != -1 && pengarang != null) {

            buku.setId(Integer.valueOf(id));
            buku.setJudul(judul);

            ObservableList<Integer> indices = listCategory.getSelectionModel().getSelectedIndices();
            for (int i = 0; i < indices.size(); i++) {
                Category cat = listModel.get(indices.get(i));
                buku.setCategory(cat);
            }

            int index = cbPenerbit.getSelectionModel().getSelectedIndex();
            if (index != -1) {
                Penerbit pen = penComboModel.get(index);
                buku.setPenerbit(pen);
            }

            buku.setPengarang(pengarang);
            buku.setTahunTerbit(tahun);
            buku.setJumlahHalaman(jumlah);
            buku.setSynopsis(synopsis);
            model.update(buku);
            clear();

            con.dialog(Alert.AlertType.INFORMATION, "Data berhasil di update !", null);
        } else {
            con.dialog(Alert.AlertType.WARNING, "Isi data dengan lengkap !", null);
        }
    }

    public void initCategoryListModel() {
        listModel = new CategoryListModel();
        listModel.addList(model.catList());
        listCategory.setItems(listModel.getItems());
        listCategory.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void initPenerbitComboModel() {
        penComboModel = new PenerbitComboBoxModel();
        penComboModel.add(model.penList());
        cbPenerbit.setItems(penComboModel.getItems());
    }

    public void initModel() {
        model = new BukuEditOperatorModel();
        model.setController(this);
    }

    private void clear() {
        txtIdBuku.setText("");
        txtJudul.setText("");
        txtPengarang.setText("");
        txtJumlahHalaman.setText("");
        txtSynopsis.setText("");
        txtTahunTerbit.setText("");
        listCategory.getSelectionModel().select(-1);
        cbPenerbit.getSelectionModel().select(-1);
    }

    @FXML
    private void backAction(ActionEvent event) {
        screensController.setScreen(Main.menuUtamaOperatorID);
    }

    @FXML
    private void backToAction(ActionEvent event) {
        screensController.setScreen(Main.menuBukuOpID);
    }

}
