/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.follow;

import com.eby.admin.mainmenu.NewMainMenuAdminController;
import com.eby.animations.FadeInLeftTransition;
import com.eby.animations.FadeOutLeftTransition;
import com.eby.frameworkConfig.Config;
import com.eby.helper.ControllerHelper;
import com.eby.main.ControlledScreen;
import com.eby.main.ScreensController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.applet.AppletContext;
import java.awt.Desktop;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.text.html.HTML;

/**
 * FXML Controller class
 *
 * @author eby
 */
public class FollowController implements Initializable, ControlledScreen {

    @FXML
    private AnchorPane paneParent;
    @FXML
    private Button btFacebook;
    @FXML
    private Button btGithub;
    @FXML
    private FontAwesomeIconView fIcon;
    @FXML
    private FontAwesomeIconView gIcon;
    @FXML
    private FontAwesomeIconView hideIcon;

    private ScreensController screensController;
    private Config con;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = new Config();
        fadeIn();
    }

    @FXML
    private void facebookAction(ActionEvent event) {
        this.browse("https://www.facebook.com/eby.j.barcelonista.bee.vhaa");
    }

    @FXML
    private void githubIcon(ActionEvent event) {
        this.browse("https://github.com/eby73");
    }

    @FXML
    private void hideAction(MouseEvent event) {
        new FadeOutLeftTransition(btFacebook).play();
        new FadeOutLeftTransition(fIcon).play();
        new FadeOutLeftTransition(gIcon).play();
        new FadeOutLeftTransition(gIcon).play();
        new FadeOutLeftTransition(paneParent).play();
        new FadeOutLeftTransition(hideIcon).play();
    }

    public void fadeIn() {
        new FadeInLeftTransition(btFacebook).play();
        new FadeInLeftTransition(fIcon).play();
        new FadeInLeftTransition(gIcon).play();
        new FadeInLeftTransition(gIcon).play();
        new FadeInLeftTransition(paneParent).play();
        new FadeInLeftTransition(hideIcon).play();
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        screensController = screenPage;
    }

    public void browse(String url) {
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
