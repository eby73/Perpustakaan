/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.menu.admin;

import com.eby.animations.FadeInLeftTransition;
import com.eby.frameworkConfig.Config;
import com.eby.helper.ControllerHelper;
import com.eby.main.ControlledScreen;
import com.eby.main.Main;
import com.eby.main.ScreensController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author eby
 */
public class AdminMenuController implements Initializable, ControlledScreen {

    @FXML
    private AnchorPane paneParent;
    @FXML
    private Text txtUser;
    @FXML
    private Button btTambahUser;
    @FXML
    private FontAwesomeIconView userIcon;
    @FXML
    private Button btDaftarUser;
    @FXML
    private FontAwesomeIconView daftarIcon;
    @FXML
    private Button btBack;
    @FXML
    private AnchorPane paneView;
    private ScreensController screensController;
    private Config con;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fadeIn();
        con = new Config();
    }

    @FXML
    private void tambahUserAction(ActionEvent event) {
        paneView.getChildren().clear();
        con.loadAnchorPane(paneView, "/admin/register/RegisterForAdmin.fxml");
    }

    @FXML
    private void daftarUserAction(ActionEvent event) {
        screensController.setScreen(Main.listAdminID);
        ControllerHelper.getListAdminController(screensController).loadData();
    }

    @FXML
    private void backAction(ActionEvent event) {
        screensController.setScreen(Main.MenuUtamaAdminID);
    }

    public void fadeIn() {
        new FadeInLeftTransition(txtUser).play();
        new FadeInLeftTransition(btTambahUser).play();
        new FadeInLeftTransition(userIcon).play();
        new FadeInLeftTransition(btDaftarUser).play();
        new FadeInLeftTransition(daftarIcon).play();
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        screensController = screenPage;
    }

}
