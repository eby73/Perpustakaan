/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.operator.anggota.edit;

import com.eby.frameworkConfig.Config;
import com.eby.main.ControlledScreen;
import com.eby.main.Main;
import com.eby.main.ScreensController;
import com.eby.orm.entity.Anggota;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author eby
 */
public class AnggotaOpEditController implements Initializable, ControlledScreen {

    @FXML
    private Button btBack;
    @FXML
    private TextField txtNIS;
    @FXML
    private TextField txtNama;
    @FXML
    private TextArea txtAlamat;
    @FXML
    private TextField txtKelas;
    @FXML
    private Button btUpdate;

    private ScreensController screensController;
    private AnggotaOpEditModel model;
    private Config con;
    @FXML
    private Button btBackTo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initModel();
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        screensController = screenPage;
    }

    public void setData(Anggota a) {
        txtNIS.setText(String.valueOf(a.getId()));
        txtNama.setText(a.getNama());
        txtAlamat.setText(a.getAlamat());
        txtKelas.setText(a.getKelas());
    }

    @FXML
    private void backAction(ActionEvent event) {
        screensController.setScreen(Main.menuUtamaOperatorID);
    }

    @FXML
    private void updateAction(ActionEvent event) {
        this.update();
    }

    public void initModel() {
        model = new AnggotaOpEditModel();
        model.setController(this);
    }

    public void update() {
        String NIS = txtNIS.getText();
        String nama = txtNama.getText();
        String alamat = txtAlamat.getText();
        String kelas = txtKelas.getText();
        if (NIS.equals("") || nama.equals("") || alamat.equals("") || kelas.equals("")) {

            con.dialog(Alert.AlertType.WARNING, "Isi data dengan lengkap !", null);

        } else {

            Anggota anggota = new Anggota();
            anggota.setId(Integer.valueOf(NIS));
            anggota.setNama(nama);
            anggota.setAlamat(alamat);
            anggota.setKelas(kelas);
            model.update(anggota);

            clearAnggota();
            con.dialog(Alert.AlertType.INFORMATION, "Update data berhasil !", null);
        }
    }

    public void clearAnggota() {
        txtNIS.setText("");
        txtNama.setText("");
        txtAlamat.setText("");
        txtKelas.setText("");
    }

    @FXML
    private void bactToAction(ActionEvent event) {
        screensController.setScreen(Main.menuAnggotaOpID);
    }

}
