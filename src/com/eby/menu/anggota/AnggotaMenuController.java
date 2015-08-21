/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.menu.anggota;

import com.eby.animations.FadeInLeftTransition;
import com.eby.animations.FadeOutUpTransition;
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
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author eby
 */
public class AnggotaMenuController implements Initializable, ControlledScreen {

    @FXML
    private Text txtMenuCategory;
    @FXML
    private Button btTambahAnggota;
    @FXML
    private Button btList;
    @FXML
    private FontAwesomeIconView iconUsers;
    @FXML
    private FontAwesomeIconView iconTambah;
    @FXML
    private FontAwesomeIconView iconList;
    @FXML
    private AnchorPane paneParent;
    @FXML
    private AnchorPane closeIcon;

    private ScreensController screensController;
    @FXML
    private Button btBack;
    @FXML
    private AnchorPane paneView;
    private Config con;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new FadeInLeftTransition(btTambahAnggota).play();
        new FadeInLeftTransition(iconUsers).play();
        new FadeInLeftTransition(iconTambah).play();
        new FadeInLeftTransition(btList).play();
        new FadeInLeftTransition(iconList).play();
        new FadeInLeftTransition(txtMenuCategory).play();
        new FadeInLeftTransition(closeIcon).play();

        con = new Config();
    }

    @FXML
    private void tambahAction(ActionEvent event) {
        paneView.getChildren().clear();
        con.loadAnchorPane(paneView, "/admin/register/RegisterForAdmin.fxml");
    }

    @FXML
    private void daftarAction(ActionEvent event) {
        screensController.setScreen(Main.listAnggotaAdminID);
        ControllerHelper.getListAnggotaController(screensController).loadData();
    }

    private void closeAction(MouseEvent event) {
        new FadeOutUpTransition(btTambahAnggota).play();
        new FadeOutUpTransition(iconUsers).play();
        new FadeOutUpTransition(iconTambah).play();
        new FadeOutUpTransition(btList).play();
        new FadeOutUpTransition(iconList).play();
        new FadeOutUpTransition(txtMenuCategory).play();
        new FadeOutUpTransition(closeIcon).play();
        new FadeOutUpTransition(paneParent).play();
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        screensController = screenPage;
    }

    @FXML
    private void backAction(ActionEvent event) {
        screensController.setScreen(Main.MenuUtamaAdminID);
    }

}
