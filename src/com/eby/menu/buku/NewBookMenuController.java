/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.menu.buku;

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
public class NewBookMenuController implements Initializable, ControlledScreen {

    @FXML
    private Button btTambahBuku;
    @FXML
    private FontAwesomeIconView bukuIcon;
    @FXML
    private FontAwesomeIconView tambahIcon;
    @FXML
    private Button btDaftarBuku;
    @FXML
    private FontAwesomeIconView listIcon;
    @FXML
    private Button btCategory;
    @FXML
    private Button btPenerbit;
    @FXML
    private FontAwesomeIconView categoryIcon;
    @FXML
    private FontAwesomeIconView penerbitIcon;
    @FXML
    private Text txtMainMenu;
    @FXML
    private AnchorPane paneParent;
    private ScreensController screensController;
    @FXML
    private AnchorPane paneView;
    @FXML
    private Button btBack;

    private Config con;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Platform.runLater(() -> {
            new FadeInLeftTransition(btTambahBuku).play();
            new FadeInLeftTransition(bukuIcon).play();
            new FadeInLeftTransition(tambahIcon).play();
            new FadeInLeftTransition(btDaftarBuku).play();
            new FadeInLeftTransition(listIcon).play();
            new FadeInLeftTransition(btCategory).play();
            new FadeInLeftTransition(categoryIcon).play();
            new FadeInLeftTransition(btPenerbit).play();
            new FadeInLeftTransition(penerbitIcon).play();
        });
        con = new Config();
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        screensController = screenPage;
    }

    @FXML
    private void tambahAction(ActionEvent event) {
        paneView.getChildren().clear();
        con.loadAnchorPane(paneView, "/book/add/BukuAdd.fxml");
    }

    @FXML
    private void daftarAction(ActionEvent event) {
        screensController.setScreen(Main.listBukuAdminID);
        ControllerHelper.getListBukuController(screensController).loadData();
    }

    @FXML
    private void categoryAction(ActionEvent event) {
        paneView.getChildren().clear();
        con.loadAnchorPane(paneView, "/book/category/Category.fxml");
    }

    @FXML
    private void penerbitAction(ActionEvent event) {
        paneView.getChildren().clear();
        con.loadAnchorPane(paneView, "/book/penerbit/Penerbit.fxml");
    }

    @FXML
    private void backAction(ActionEvent event) {
        screensController.setScreen(Main.MenuUtamaAdminID);
    }

}
