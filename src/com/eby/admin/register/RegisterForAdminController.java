/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.admin.register;

import com.eby.animations.FadeInLeftTransition;
import com.eby.animations.FadeOutUpTransition;
import com.eby.frameworkConfig.Config;
import com.eby.orm.entity.Anggota;
import com.eby.orm.entity.User;
import com.eby.main.ControlledScreen;
import com.eby.main.ScreensController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.apache.commons.validator.routines.EmailValidator;

/**
 * FXML Controller class
 *
 * @author eby
 */
public class RegisterForAdminController implements Initializable, ControlledScreen {

    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField txtPasswd;
    private ComboBox<String> cbMail;
    @FXML
    private ComboBox<String> cbLvl;
    @FXML
    private Button btRegisterAdmin;
    @FXML
    private TextField txtNIS;
    @FXML
    private TextArea txtAlamat;
    @FXML
    private TextField txtKelas;
    @FXML
    private Button btRegAnggota;
    @FXML
    private Label labelValid;

    @FXML
    private TextField txtNamaAdmin;
    @FXML
    private TextField txtNamaAnggota;

    @FXML
    private TextField txtEmail;
    private EmailValidator validdator;
    private ScreensController screensController;
    private Config con;
    private RegAdminModel regModel;
    @FXML
    private GridPane grid1;
    @FXML
    private GridPane grid2;
    @FXML
    private FontAwesomeIconView usPlusIcon;
    @FXML
    private FontAwesomeIconView iconClose;
    @FXML
    private FontAwesomeIconView usPlusIcon1;
    @FXML
    private AnchorPane paneView;
    @FXML
    private Text txtNotif;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initModel();
        addcbLvl();
        con = new Config();
        fadeIn();
        Platform.runLater(() -> {
            txtEmail.setOnKeyReleased((KeyEvent event) -> {
                validdator = EmailValidator.getInstance(true);
                if (validdator.isValid(txtEmail.getText())) {
                    labelValid.getStyleClass().remove("invalid-label");
                    labelValid.getStyleClass().add("valid-label");
                } else {
                    labelValid.getStyleClass().remove("valid-label");
                    labelValid.getStyleClass().add("invalid-label");
                }
            });
        });
        txtNotif.setVisible(false);

    }

    @FXML

    private void RegisterAdminAction(ActionEvent event) {
        this.saveAdmin();
    }

    @FXML
    private void RegAnggotaAction(ActionEvent event) {
        this.saveAnggota();
    }

    public void addcbLvl() {
        ObservableList list = FXCollections.observableArrayList();
        list.addAll("admin", "operator");
        cbLvl.setItems(list);
    }

    public void initModel() {
        regModel = new RegAdminModel();
        regModel.setAdminCOntroller(this);
    }

    public void saveAdmin() {
        User user = new User();
        String nama = txtNamaAdmin.getText();
        String email = txtEmail.getText();
        String username = txtUserName.getText();
        String pass = txtPasswd.getText();
        String level = cbLvl.getSelectionModel().getSelectedItem();
        try {

            if (nama.equals("") || email.equals("") || username.equals("") || pass.equals("") || level.equals("")) {
                con.dialog(Alert.AlertType.WARNING, "Isi data dengan lengkap !", null);
            } else {
                validdator = EmailValidator.getInstance();
                if (validdator.isValid(email)) {
                    if (pass.toCharArray().length < 6) {
                        con.dialog(Alert.AlertType.WARNING, "Password tidak memenuhi!", null);
                    } else {
                        user.setNama(nama);
                        user.setEmail(email);
                        user.setUsername(username);
                        user.setPasswd(pass);
                        user.setLevel(level);

                        regModel.saveAdmin(user);

                        clearAdmin();
                        con.dialog(Alert.AlertType.INFORMATION, "Register Berhasil !", null);
                    }
                } else {
                    con.dialog(Alert.AlertType.WARNING, "Email Tidak Valid !", "");
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void clearAdmin() {
        txtNamaAdmin.setText("");
        txtUserName.setText("");
        txtPasswd.setText("");
        cbLvl.getSelectionModel().select(-1);
        txtEmail.setText("");

    }

    public void clearAnggota() {
        txtNIS.setText("");
        txtNamaAnggota.setText("");
        txtAlamat.setText("");
        txtKelas.setText("");
    }

    public void saveAnggota() {

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
            regModel.saveAnggota(anggota);
            clearAnggota();
            con.dialog(Alert.AlertType.INFORMATION, "Register Berhasil !", null);
        }
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        screensController = screenPage;
    }

    @FXML
    private void closeAction(MouseEvent event) {
        fadeOut();
    }

    void fadeIn() {
        new FadeInLeftTransition(grid1).play();
        new FadeInLeftTransition(labelValid).play();
        new FadeInLeftTransition(btRegisterAdmin).play();
        new FadeInLeftTransition(usPlusIcon).play();
        new FadeInLeftTransition(usPlusIcon1).play();
        new FadeInLeftTransition(btRegAnggota).play();
        new FadeInLeftTransition(grid2).play();
        new FadeInLeftTransition(iconClose).play();
    }

    void fadeOut() {
        new FadeOutUpTransition(grid1).play();
        new FadeOutUpTransition(labelValid).play();
        new FadeOutUpTransition(btRegisterAdmin).play();
        new FadeOutUpTransition(usPlusIcon).play();
        new FadeOutUpTransition(usPlusIcon1).play();
        new FadeOutUpTransition(btRegAnggota).play();
        new FadeOutUpTransition(grid2).play();
        new FadeOutUpTransition(iconClose).play();
        new FadeOutUpTransition(paneView).play();
    }

    @FXML
    private void passKeyAction(KeyEvent event) {
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
