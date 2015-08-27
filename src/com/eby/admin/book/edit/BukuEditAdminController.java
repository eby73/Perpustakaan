/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.admin.book.edit;

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
public class BukuEditAdminController implements Initializable, ControlledScreen {

    @FXML
    private Button btUpdate;
    @FXML
    private TextField txtJmlhHalaman;
    @FXML
    private TextArea txtSynopsis;
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
    private Button btBackTo;
    @FXML
    private Button btBack;

    private ScreensController screensController;
    private CategoryListModel listModel;
    private PenerbitComboBoxModel penComboModel;
    private BukuEditAdminModel model;
    private Config con;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initModel();
        initCategoryListModel();
        initPenerbitComboModel();
        //inisialisasi objek con
        con = new Config();
    }

    @FXML
    private void updateAction(ActionEvent event) {
        this.update();
    }

    @FXML
    private void backtoMainAction(ActionEvent event) {
        //setScreen ke menuUtama admins
        screensController.setScreen(Main.MenuUtamaAdminID);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        screensController = screenPage;
    }

    public void setData(Buku buku) {
        //methode setData ketika proses edit
        txtIdBuku.setText(String.valueOf(buku.getId()));
        txtJudul.setText(buku.getJudul());
        listCategory.getSelectionModel().select(buku.getCategory().getNama());
        cbPenerbit.getSelectionModel().select(buku.getPenerbit().getNama());
        txtPengarang.setText(buku.getPengarang());
        txtTahunTerbit.setText(buku.getTahunTerbit());
        txtJmlhHalaman.setText(buku.getJumlahHalaman());
        txtSynopsis.setText(buku.getSynopsis());
    }

    public void initCategoryListModel() {
        //inisialisasi objek listModel
        listModel = new CategoryListModel();
        //menambahkan isi data dari category ke listModel
        listModel.addList(model.lisCat());
        //mengeset isi data dari list model ke listCategory
        listCategory.setItems(listModel.getItems());
        //bisa memilih lebih dari 1 category dengan menekan shift + click
        listCategory.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void initPenerbitComboModel() {
        //inisialisasi objek penComboModel
        penComboModel = new PenerbitComboBoxModel();
        //menambahkan isi data dari penerbit ke penComboModel
        penComboModel.add(model.listPen());
        //mengeset isi data dari penComboModel ke cbpenerbit
        cbPenerbit.setItems(penComboModel.getItems());
    }

    public void initModel() {
        //inisialisasi objek model
        model = new BukuEditAdminModel();
        //mengeset controller yang akan digunakan
        model.setController(this);
    }

    public void update() {
        Buku buku = new Buku();
        String id = txtIdBuku.getText();
        String judul = txtJudul.getText();
        int catId = listCategory.getSelectionModel().getSelectedIndex();
        int penId = cbPenerbit.getSelectionModel().getSelectedIndex();
        String pengarang = txtPengarang.getText();
        String tahun = txtTahunTerbit.getText();
        String jumlah = txtJmlhHalaman.getText();
        String synopsis = txtSynopsis.getText();

        if (id != null && judul != null && catId != -1 && penId != -1 && pengarang != null) {

            buku.setId(Integer.valueOf(id));
            buku.setJudul(judul);

            //getSelectedIndices artinya bisa memilih lebih dari 1 index dengan menekan shift + klik
            ObservableList<Integer> indices = listCategory.getSelectionModel().getSelectedIndices();
            //menggunakan for-loop yang sama artinya dengan for(int i=0; i<idices.size; i++) untuk penyimpanan lebih dari 1 index
            for (Integer indice : indices) {
                //inisialisasi objek cat dengan isi index diatas
                Category cat = listModel.get(indice);
                //set index kedalam entity category
                buku.setCategory(cat);
            }

            //mengambil index dari cbPenerbit
            int index = cbPenerbit.getSelectionModel().getSelectedIndex();
            if (index != -1) {
                //inisialisasi objek pen dengan isi index diatas
                Penerbit pen = penComboModel.get(index);
                //set index diatas kedalam entity penerbit
                buku.setPenerbit(pen);
            }

            //set isi semua entity dengan inputan dari masing2 node
            buku.setPengarang(pengarang);
            buku.setTahunTerbit(tahun);
            buku.setJumlahHalaman(jumlah);
            buku.setSynopsis(synopsis);

            //eksekusi methode update dari class BukuEditAdminModel
            model.update(buku);
            clear();

            con.dialog(Alert.AlertType.INFORMATION, "Data berhasil di update !", null);
        } else {
            con.dialog(Alert.AlertType.WARNING, "Isi data dengan lengkap !", null);
        }
    }

    private void clear() {
        //set semua inputan node ke default/kosong/""
        txtIdBuku.setText("");
        txtJudul.setText("");
        txtPengarang.setText("");
        txtJmlhHalaman.setText("");
        txtSynopsis.setText("");
        txtTahunTerbit.setText("");
        listCategory.getSelectionModel().clearSelection();
        cbPenerbit.getSelectionModel().clearSelection();
    }

    @FXML
    private void backAction(ActionEvent event) {
        //setScreen ke menuBuku
        screensController.setScreen(Main.MenuBukuID);
    }

}
