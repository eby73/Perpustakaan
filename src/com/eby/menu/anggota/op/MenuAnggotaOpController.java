/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.menu.anggota.op;

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
public class MenuAnggotaOpController implements Initializable, ControlledScreen {
    @FXML
    private Button btBack;
    @FXML
    private AnchorPane paneView;
    @FXML
    private Button btTambah;
    @FXML
    private Button btDaftar;
    
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
    private void tambahAction(ActionEvent event) {
        paneView.getChildren().clear();
        con.loadAnchorPane(paneView, "/operator/register/OperatorRegister.fxml");
    }

    @FXML
    private void daftarAction(ActionEvent event) {
        screensController.setScreen(Main.listAnggotaOpID);
        ControllerHelper.getListAnggotaOp(screensController).loadData();
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        screensController = screenPage;
    }
    
}
