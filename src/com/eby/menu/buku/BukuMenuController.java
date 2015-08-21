/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.menu.buku;

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
public class BukuMenuController implements Initializable, ControlledScreen {

    @FXML
    private Button btTambah;
    @FXML
    private AnchorPane paneView;

    Config config;
    private ScreensController screensController;
    @FXML
    private Button btCategory;
    @FXML
    private Button btPenerbit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        config = new Config();
    }

    @FXML
    private void TambahAction(ActionEvent event) {
        config.loadAnchorPane(paneView, "/book/add/BukuAdd.fxml");
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        screensController = screenPage;
    }

    @FXML
    private void categoryAction(ActionEvent event) {
        config.loadAnchorPane(paneView, "/book/category/Category.fxml");
    }

    @FXML
    private void penerbitAction(ActionEvent event) {
        config.loadAnchorPane(paneView, "/book/penerbit/Penerbit.fxml");
    }

}
