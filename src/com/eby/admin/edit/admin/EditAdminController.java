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
    private ComboBox<String> cbMail;
    private TextField txtMail;
    @FXML
    private Button btUpdateAdmin;
    @FXML
    private TextField txtNamaAnggota;
    @FXML
    private TextField txtNamaAdmin;

    private ScreensController screensController;
    private Config con;
    @FXML
    private Button btBacktoMenu;
    private EditAdminModel model;
    private EmailValidator validator;
    @FXML
    private Label labelEmail;
    @FXML
    private Text txtNotif;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initModel();
        addcbLvl();
        Platform.runLater(() -> {
            txtEmail.setOnKeyReleased((KeyEvent event) -> {
                validator = EmailValidator.getInstance();
                if (validator.isValid(txtEmail.getText())) {
                    labelEmail.getStyleClass().remove("invalid-label");
                    labelEmail.getStyleClass().remove("invalid-label");
                    labelEmail.getStyleClass().remove("invalid-label");
                    labelEmail.getStyleClass().remove("invalid-label");
                    labelEmail.getStyleClass().add("valid-label");
                } else {
                    labelEmail.getStyleClass().remove("valid-label");
                    labelEmail.getStyleClass().add("invalid-label");
                }
            });
        });
        txtNotif.setVisible(false);
    }

    public void initModel() {
        model = new EditAdminModel();
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
        ObservableList list = FXCollections.observableArrayList();
        list.addAll("admin", "operator");
        cbLevel.setItems(list);
    }

    @FXML
    private void backtoMenuAction(ActionEvent event) {
        screensController.setScreen(Main.MenuUtamaAdminID);
    }

    public void setDataAnggota(Anggota anggota) {
        txtNIS.setText(String.valueOf(anggota.getId()));
        txtNamaAnggota.setText(anggota.getNama());
        txtAlamat.setText(anggota.getAlamat());
        txtKelas.setText(anggota.getKelas());
    }

    public void updateAdmin() {
        User user = new User();

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
            if (validator.isValid(email)) {
                if (pass.toCharArray().length < 8) {
                    con.dialog(Alert.AlertType.WARNING, "Password tidak memenuhi", null);
                } else {
                    user.setId(Integer.valueOf(id));
                    user.setNama(nama);
                    user.setEmail(email);
                    user.setUsername(username);
                    user.setPasswd(pass);
                    user.setLevel(level);

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

            Anggota anggota = new Anggota();
            anggota.setId(Integer.valueOf(NIS));
            anggota.setNama(nama);
            anggota.setAlamat(alamat);
            anggota.setKelas(kelas);
            model.updateAnggota(anggota);

            clearAnggota();
            con.dialog(Alert.AlertType.INFORMATION, "Update data Berhasil !", null);
        }
    }

    public void clearAnggota() {
        txtNIS.setText("");
        txtNamaAnggota.setText("");
        txtAlamat.setText("");
        txtKelas.setText("");
    }

    private void refreshAnggotaAction(ActionEvent event) {
        clearAnggota();
    }

    private void refreshAdminAction(ActionEvent event) {
        clearAdmin();
    }

    @FXML
    private void keyPassAction(KeyEvent event) {
        String pass = txtPasswd.getText();
        if (pass.equals("")) {
            txtNotif.setVisible(false);
        } else {
            if (pass.toCharArray().length < 8) {
                txtNotif.setVisible(true);
            } else {
                txtNotif.setVisible(false);
            }
        }

    }
}
