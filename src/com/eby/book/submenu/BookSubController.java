/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.book.submenu;

import com.eby.frameworkConfig.Config;
import com.eby.main.ControlledScreen;
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
public class BookSubController implements Initializable, ControlledScreen {

    @FXML
    private AnchorPane paneView;

    private Config con;
    @FXML
    private Button btCategory;
    @FXML
    private Button btPenerbit;
    @FXML
    private Button btPengarang;

    private ScreensController screen;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        con = new Config();
    }

    @FXML
    private void categoryAction(ActionEvent event) {
        
    }

    @FXML
    private void penerbitAction(ActionEvent event) {
        
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        screen = screenPage;
    }

}
