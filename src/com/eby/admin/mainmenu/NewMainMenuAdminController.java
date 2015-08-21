/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.admin.mainmenu;

import com.eby.animations.FadeInLeftTransition;
import com.eby.animations.FadeOutLeftTransition;
import com.eby.frameworkConfig.Config;
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
import javafx.scene.input.MouseEvent;
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
public class NewMainMenuAdminController implements Initializable, ControlledScreen {

    @FXML
    private Button btBukuMenu;
    @FXML
    private Button btAnggota;
    @FXML
    private Button btAdmin;
    @FXML
    private AnchorPane paneSubMenu;
    @FXML
    private Button btPeminjaman;
    @FXML
    private Button btPengembalian;

    @FXML
    private Button brFullScreen;
    private Button btMinimize;
    @FXML
    private Button btMaximize;
    @FXML
    private Button btClose;

    private Config con;
    private Stage stage;
    Double w, h;
    Rectangle2D rec2;
    private ScreensController screensController;
    @FXML
    private FontAwesomeIconView bukuIcon;
    @FXML
    private FontAwesomeIconView AnggotaIcon;
    @FXML
    private FontAwesomeIconView userIcon;
    @FXML
    private Text txtHeader;
    @FXML
    private FontAwesomeIconView peminjamanIcon;
    @FXML
    private FontAwesomeIconView pengembalianIcon;
    @FXML
    private HBox hBox;
    @FXML
    private Text txtMain;
    @FXML
    private Button btLogOut;
    @FXML
    private FontAwesomeIconView logOutIcon;
    private AnchorPane paneFollow;
    @FXML
    private FontAwesomeIconView showIcon;
    @FXML
    private FontAwesomeIconView hideIcon;
    @FXML
    private Button btGithub;
    @FXML
    private Button btFacebook;
    @FXML
    private FontAwesomeIconView fIcon;
    @FXML
    private FontAwesomeIconView gIcon;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = new Config();
        rec2 = Screen.getPrimary().getVisualBounds();
        w = 0.1;
        h = 0.1;
        showIcon.setVisible(false);
    }

    @FXML
    private void bukuMenuAction(ActionEvent event) {
        screensController.setScreen(Main.MenuBukuID);
    }

    @FXML
    private void anggotaAction(ActionEvent event) {
        screensController.setScreen(Main.menuAnggotaID);
    }

    @FXML
    private void adminAction(ActionEvent event) {
        screensController.setScreen(Main.menuAdminID);
    }

    @FXML
    private void peminjamanAction(ActionEvent event) {
        con.loadAnchorPane(paneSubMenu, "/peminjaman/Peminjaman.fxml");
    }

    @FXML
    private void pengembalianAction(ActionEvent event) {
//        paneSubMenu.getChildren().clear();
        con.loadAnchorPane(paneSubMenu, "/pengembalian/Pengembalian.fxml");
    }

    @FXML
    private void fullScreenAction(ActionEvent event) {
        stage = (Stage) brFullScreen.getScene().getWindow();
        if (stage.isFullScreen()) {
            stage.setFullScreen(false);
        } else {
            stage.setFullScreen(true);
        }
    }

    private void minimizeAction(ActionEvent event) {
        stage = (Stage) btMinimize.getScene().getWindow();
        if (stage.isMaximized()) {
            w = rec2.getWidth();
            h = rec2.getHeight();
            stage.setMaximized(false);
            stage.setHeight(h);
            stage.setWidth(w);
            stage.centerOnScreen();
            Platform.runLater(() -> {
                stage.setIconified(true);
            });
        } else {
            stage.setIconified(true);
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

    @Override
    public void setScreenParent(ScreensController screenPage) {
        screensController = screenPage;
    }

    @FXML
    private void logOutAction(ActionEvent event) {
        screensController.setScreen(Main.loginID);
    }

    @FXML
    private void hideFollow(MouseEvent event) {
        new FadeOutLeftTransition(btFacebook).play();
        new FadeOutLeftTransition(fIcon).play();
        new FadeOutLeftTransition(gIcon).play();
        new FadeOutLeftTransition(btGithub).play();
        new FadeOutLeftTransition(hideIcon).play();
        showIcon.setVisible(true);
        new FadeInLeftTransition(showIcon).play();
    }

    @FXML
    private void githubAction(ActionEvent event) {
        this.browse("https://github.com/eby73");
    }

    @FXML
    private void facebookAction(ActionEvent event) {
        this.browse("https://www.facebook.com/eby.j.barcelonista.bee.vhaa");
    }

    @FXML
    private void showFollow(MouseEvent event) {
        new FadeInLeftTransition(btFacebook).play();
        new FadeInLeftTransition(fIcon).play();
        new FadeInLeftTransition(gIcon).play();
        new FadeInLeftTransition(btGithub).play();
        new FadeInLeftTransition(hideIcon).play();
        new FadeOutLeftTransition(showIcon).play();
    }

    public void browse(String url) {
        //source dari mkyoung.com
        String os = System.getProperty("os.name").toLowerCase();
        Runtime rt = Runtime.getRuntime();

        try {

            if (os.indexOf("win") >= 0) {
                rt.exec("rundll32 url.dll,FileProtocolHandler " + url);
            } else if (os.indexOf("mac") >= 0) {
                rt.exec("open " + url);
            } else if (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0) {
                String[] browsers = {"epiphany", "chrome", "chromium", "google chrome", "firefox", "mozilla", "konqueror",
                    "netscape", "opera", "links", "lynx"};
                StringBuffer cmd = new StringBuffer();
                for (int i = 0; i < browsers.length; i++) {
                    cmd.append((i == 0 ? "" : " || ") + browsers[i] + " \"" + url + "\" ");
                }
                rt.exec(new String[]{"sh", "-c", cmd.toString()});
            } else {
                return;
            }
        } catch (Exception e) {
            return;
        }
        return;
    }
}
