/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.menu.buku.op;

import com.eby.frameworkConfig.Config;
import com.eby.helper.ControllerHelper;
import com.eby.main.ControlledScreen;
import com.eby.main.Main;
import com.eby.main.ScreensController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author eby
 */
public class MenuBukuOpController implements Initializable, ControlledScreen {

    @FXML
    private Button btBack;
    @FXML
    private Button btTambahMenu;
    @FXML
    private Button btPenerbit;
    @FXML
    private Button btDaftar;
    @FXML
    private Button btCategory;
    @FXML
    private AnchorPane paneView;

    private ScreensController screensController;
    private Config con;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = new Config();
    }

    @FXML
    private void backAction(ActionEvent event) {
        screensController.setScreen(Main.menuUtamaOperatorID);
    }

    @FXML
    private void tambahMenuAction(ActionEvent event) {
        paneView.getChildren().clear();
        con.loadAnchorPane(paneView, "/book/add/BukuAdd.fxml");
    }

    @FXML
    private void penerbitAction(ActionEvent event) {
        paneView.getChildren().clear();
        con.loadAnchorPane(paneView, "/book/penerbit/Penerbit.fxml");
    }

    @FXML
    private void daftarAction(ActionEvent event) {
        screensController.setScreen(Main.listBukuOperatorID);
        ControllerHelper.getListBukuOpCon(screensController).loadData();
    }

    @FXML
    private void categoryAction(ActionEvent event) {
        paneView.getChildren().clear();
        con.loadAnchorPane(paneView, "/book/category/Category.fxml");
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        screensController = screenPage;
    }

}
