/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.operator.mainmenu;

import com.eby.animations.FadeInLeftTransition;
import com.eby.frameworkConfig.Config;
import com.eby.helper.ControllerHelper;
import com.eby.main.ControlledScreen;
import com.eby.main.Main;
import com.eby.main.ScreensController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author eby
 */
public class NewMainMenuOperatorController implements Initializable, ControlledScreen {

    @FXML
    private HBox hBox;
    @FXML
    private Text txtHeader;
    @FXML
    private AnchorPane paneView;
    @FXML
    private Button btBukuMenu;
    @FXML
    private Button btAnggotaMenu;
    @FXML
    private Button btPeminjaman;
    @FXML
    private Button btPengembalian;
    @FXML
    private FontAwesomeIconView bukuIcon;
    @FXML
    private FontAwesomeIconView anggotaIcon;
    @FXML
    private FontAwesomeIconView peminjamanIcon;
    @FXML
    private FontAwesomeIconView pengembalianIcon;
    @FXML
    private Text txtMenuHeader;
    @FXML
    private Button btAdminMenu;
    @FXML
    private FontAwesomeIconView adminIcon;
    @FXML
    private Button btFullscreen;
    @FXML
    private Button btMaximize;
    @FXML
    private Button btClose;
    @FXML
    private Text txtDisable;

    double w, h;
    Rectangle2D rec2;
    private ScreensController screensController;
    private Config con;
    Stage stage;
    @FXML
    private Button btLogOut;
    @FXML
    private FontAwesomeIconView logOutIcon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fadeIn();
        con = new Config();
        rec2 = Screen.getPrimary().getVisualBounds();
        w = 0.1;
        h = 0.1;
    }

    @FXML
    private void fullscreenAction(ActionEvent event) {
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

    @FXML
    private void closeAction(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void bukuMenuAction(ActionEvent event) {
        screensController.setScreen(Main.menuBukuOpID);
    }

    @FXML
    private void anggotaMenuAction(ActionEvent event) {
        screensController.setScreen(Main.menuAnggotaOpID);
    }

    @FXML
    private void peminjamanAction(ActionEvent event) {
        con.loadAnchorPane(paneView, "/peminjaman/Peminjaman.fxml");
    }

    @FXML
    private void pengembalianAction(ActionEvent event) {
        con.loadAnchorPane(paneView, "/pengembalian/Pengembalian.fxml");
    }

    @FXML
    private void AdminMenuAction(ActionEvent event) {
    }

    public void fadeIn() {
        new FadeInLeftTransition(hBox).play();
        new FadeInLeftTransition(txtHeader).play();
        new FadeInLeftTransition(txtMenuHeader).play();
        new FadeInLeftTransition(btBukuMenu).play();
        new FadeInLeftTransition(bukuIcon).play();
        new FadeInLeftTransition(btAnggotaMenu).play();
        new FadeInLeftTransition(anggotaIcon).play();
        new FadeInLeftTransition(btPeminjaman).play();
        new FadeInLeftTransition(peminjamanIcon).play();
        new FadeInLeftTransition(btPengembalian).play();
        new FadeInLeftTransition(pengembalianIcon).play();
        new FadeInLeftTransition(btAdminMenu).play();
        new FadeInLeftTransition(adminIcon).play();
        new FadeInLeftTransition(txtDisable).play();
        new FadeInLeftTransition(btLogOut).play();
        new FadeInLeftTransition(logOutIcon).play();

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        screensController = screenPage;
    }

    @FXML
    private void logOutAction(ActionEvent event) {
        screensController.setScreen(Main.loginID);
        ControllerHelper.getLoginController(screensController).set();
    }
}
