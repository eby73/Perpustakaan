/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.login;

import com.eby.animations.FadeInLeftTransition;
import com.eby.frameworkConfig.Config;
import com.eby.main.ControlledScreen;
import com.eby.main.ScreensController;
import com.eby.main.Main;
import com.eby.sql.connector.MySQLQuery;
import com.mytdev.javafx.scene.control.AutoCompleteTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eby
 */
public class LoginController implements Initializable, ControlledScreen {

    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPass;
    @FXML
    private Button btLogin;
    @FXML
    private Button btClose;
    @FXML
    private Text loginHeader;
    @FXML
    private HBox hBox;
    @FXML
    private Button btFullscreen;
    @FXML
    private Button btMaximize;
    @FXML
    private Text txtHeader;
    @FXML
    private Label lblWrong;
    private FontAwesomeIconView userIcon;
    private FontAwesomeIconView passIcon;
    double w, h;
    Rectangle2D rec2;
    private Label lblUser;
    private Label lblPass;
    Stage stage;
    Config config;
    ScreensController screenController;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        stage = new Stage();
        config = new Config();
        rec2 = Screen.getPrimary().getVisualBounds();
        w = 0.1;
        h = 0.1;
        fadeIn();
    }

    @FXML
    private void LoginAction(ActionEvent event) throws InterruptedException {
        if (txtUser.getText().equals("") || txtPass.getText().equals("")) {
            lblWrong.setText("Masukkan Username dan Password");
        } else {
            Thread.sleep(1000);
            this.login();
        }

    }

    public void login() {

        String user = txtUser.getText();
        String pass = txtPass.getText();

        //Mendapatkan nilai user pass dan level pada database
        MySQLQuery query = new MySQLQuery();
        String sql = "select username, passwd, level"
                + " from user where username = '" + user + "'"
                + "and passwd = '" + pass + "'";
        try {
            ResultSet rs = query.DMLQuery(sql);
            if (rs.next()) {
                rs.getString("username");
                rs.getString("passwd");
//                config.dialog(Alert.AlertType.INFORMATION, "Login sebagai " + user + " Berhasil !!!", null);
                lblWrong.setText("Login Succes !");
                reset();
                if (rs.getString("level").equalsIgnoreCase("admin")) {
                    screenController.setScreen(Main.MenuUtamaAdminID);
                } else {
                    screenController.setScreen(Main.menuUtamaOperatorID);
                }
            } else {
//                config.dialog(Alert.AlertType.WARNING, "username or password salah !!!", null);
                lblWrong.setText("Username atau password salah !");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void reset() {
        txtUser.setText("");
        txtPass.setText("");
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        screenController = screenPage;
    }

    public TextField getTxtUser() {
        return txtUser;
    }

    public void setTxtUser(TextField txtUser) {
        this.txtUser = txtUser;
    }

    @FXML
    private void closeAction(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void fullScreenAction(ActionEvent event) {
        stage = (Stage) btFullscreen.getScene().getWindow();
        if (stage.isFullScreen()) {
            stage.setFullScreen(false);
        } else {
            stage.setFullScreen(true);
        }
    }

    @FXML
    private void maximizeAction(ActionEvent event) {
        stage = (Stage) btMaximize.getScene().getWindow();
        if (stage.isMaximized()) {
            if (w == rec2.getWidth() && h == rec2.getHeight()) {
                stage.setMaximized(false);
                stage.setHeight(720);
                stage.setWidth(1320);
                stage.centerOnScreen();
                btMaximize.getStyleClass().remove("decoration-button-restore");

            } else {
                stage.setMaximized(false);
                btMaximize.getStyleClass().remove("decoration-button-restore");

            }

        } else {
            stage.setMaximized(true);
            stage.setHeight(rec2.getHeight());
            btMaximize.getStyleClass().add("decoration-button-restore");

        }
    }

    void fadeIn() {
        new FadeInLeftTransition(txtHeader).play();
        new FadeInLeftTransition(loginHeader).play();
        new FadeInLeftTransition(hBox).play();
        new FadeInLeftTransition(btLogin).play();
    }

    public void set() {
        lblWrong.setText("");
    }

    @FXML
    private void keyPressedAction(KeyEvent event) throws InterruptedException {
        if (event.getCode() == KeyCode.ENTER) {
            if (txtUser.getText().equals("") || txtPass.getText().equals("")) {
                lblWrong.setText("Masukkan Username dan Password");
            } else {
                Thread.sleep(1000);
                this.login();
            }
        }
    }

}
