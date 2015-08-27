/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.admin.edit.admin;

import com.eby.frameworkConfig.Config;
import com.eby.helper.ControllerHelper;
import com.eby.main.ControlledScreen;
import com.eby.main.Main;
import com.eby.main.ScreensController;
import com.eby.orm.entity.Anggota;
import com.eby.orm.entity.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * FXML Controller class
 *
 * @author eby
 */
public class EditAdminController implements Initializable, ControlledScreen {

    @FXML
    private TextField txtNIS;
    @FXML
    private TextField txtKelas;
    @FXML
    private TextArea txtAlamat;
    @FXML
    private Button btUpdateAnggota;
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtUsername;
    @FXML
    private PasswordField txtPasswd;
    @FXML
    private ComboBox<String> cbLevel;
    @FXML
    private Button btUpdateAdmin;
    @FXML
    private TextField txtNamaAnggota;
    @FXML
    private TextField txtNamaAdmin;
    @FXML
    private Button btBacktoMenu;
    @FXML
    private Label labelEmail;
    @FXML
    private Text txtNotif;

    private ScreensController screensController;
    private Config con;
    private EditAdminModel model;
    private EmailValidator validator;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initModel();
        addcbLvl();
        //diajalankan apabila hanya diperlukan
        Platform.runLater(() -> {
            txtEmail.setOnKeyReleased((KeyEvent event) -> {
                validator = EmailValidator.getInstance();
                if (validator.isValid(txtEmail.getText())) {
                    //mengubah icon menjadi valid-label jika email valid
                    labelEmail.getStyleClass().remove("invalid-label");
                    labelEmail.getStyleClass().add("valid-label");
                } else {
                    //menguah icon menjadi invalid-label jiak email invalid
                    labelEmail.getStyleClass().remove("valid-label");
                    labelEmail.getStyleClass().add("invalid-label");
                }
            });
        });
        //nonaktifkan txtNotif
        txtNotif.setVisible(false);
    }

    public void initModel() {
        //inisialisasi objek model
        model = new EditAdminModel();
        //set controller yang akan digunakan
        model.setController(this);
    }

    @FXML
    private void updateAnggota(ActionEvent event) {
        this.updateAnggota();
    }

    @FXML
    private void updateAdmin(ActionEvent event) {
        this.updateAdmin();
    }

    public void setDataAdmin(User user) {
        //setDataAdmin ketika proses editAdmin
        txtId.setText(String.valueOf(user.getId()));
        txtNamaAdmin.setText(user.getNama());
        txtEmail.setText(user.getEmail());
        txtUsername.setText(user.getUsername());
        txtPasswd.setText(user.getPasswd());
        cbLevel.getSelectionModel().select(user.getLevel());
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        screensController = screenPage;
    }

    public void addcbLvl() {
        //mengisi item untuk cbLevel
        ObservableList list = FXCollections.observableArrayList();
        list.addAll("admin", "operator");
        cbLevel.setItems(list);
    }

    @FXML
    private void backtoMenuAction(ActionEvent event) {
        screensController.setScreen(Main.MenuUtamaAdminID);
    }

    public void setDataAnggota(Anggota anggota) {
        //setDataAnggota untuk proses editAnggota
        txtNIS.setText(String.valueOf(anggota.getId()));
        txtNamaAnggota.setText(anggota.getNama());
        txtAlamat.setText(anggota.getAlamat());
        txtKelas.setText(anggota.getKelas());
    }

    public void updateAdmin() {
        String id = txtId.getText();
        String nama = txtNamaAdmin.getText();
        String email = txtEmail.getText();
        String username = txtUsername.getText();
        String pass = txtPasswd.getText();
        String level = cbLevel.getSelectionModel().getSelectedItem();

        if (nama.equals("") || email.equals("") || username.equals("") || pass.equals("") || level.equals("")) {
            con.dialog(Alert.AlertType.WARNING, "Isi data dengan lengkap !", null);
        } else {
            validator = EmailValidator.getInstance();
            //membuat kondisi untuk cek validitas email
            if (validator.isValid(email)) {
                //kondisi untuk cek jumlah karakter password
                if (pass.toCharArray().length < 6) {
                    con.dialog(Alert.AlertType.WARNING, "Password tidak memenuhi", null);
                } else {
                    //set isi untuk entity berdasarkan inputan dari node
                    User user = new User();
                    user.setId(Integer.valueOf(id));
                    user.setNama(nama);
                    user.setEmail(email);
                    user.setUsername(username);
                    user.setPasswd(pass);
                    user.setLevel(level);
                    
                    //eksekusi methode updateAdmin dari class EditAdminModel
                    model.updateAdmin(user);

                    clearAdmin();
                    con.dialog(Alert.AlertType.INFORMATION, "Update data Berhasil !", null);
                }
            } else {
                con.dialog(Alert.AlertType.WARNING, "Email tidak valid", null);
            }

        }
    }

    public void clearAdmin() {
        //set isi node ke default/kosong/""
        txtId.setText("");
        txtNamaAdmin.setText("");
        txtUsername.setText("");
        txtPasswd.setText("");
        cbLevel.getSelectionModel().clearSelection();
        txtEmail.setText("");

    }

    public void updateAnggota() {
        String NIS = txtNIS.getText();
        String nama = txtNamaAnggota.getText();
        String alamat = txtAlamat.getText();
        String kelas = txtKelas.getText();
        if (NIS.equals("") || nama.equals("") || alamat.equals("") || kelas.equals("")) {

            con.dialog(Alert.AlertType.WARNING, "Isi data dengan lengkap !", null);

        } else {
            //set isi entity berdasarkan inputan dari node
            Anggota anggota = new Anggota();
            anggota.setId(Integer.valueOf(NIS));
            anggota.setNama(nama);
            anggota.setAlamat(alamat);
            anggota.setKelas(kelas);
            //eksekusi methode updateAnggota dari class EditAdminModel
            model.updateAnggota(anggota);

            clearAnggota();
            con.dialog(Alert.AlertType.INFORMATION, "Update data Berhasil !", null);
        }
    }

    public void clearAnggota() {
        //set isi node ke default/kosong/""
        txtNIS.setText("");
        txtNamaAnggota.setText("");
        txtAlamat.setText("");
        txtKelas.setText("");
    }

    @FXML
    private void keyPassAction(KeyEvent event) {
        String pass = txtPasswd.getText();
        if (pass.equals("")) {
            //jika pass masih ""/null/kosong, maka txtNotif masih nonaktif
            txtNotif.setVisible(false);
        } else {
            if (pass.toCharArray().length < 6) {
                //jika karakter password <6 maka txtNotif aktif
                txtNotif.setVisible(true);
            } else {
                txtNotif.setVisible(false);
            }
        }

    }
}
