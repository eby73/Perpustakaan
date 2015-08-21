/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.menu.category;

import com.eby.animations.FadeInLeftTransition;
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
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author eby
 */
public class CategoryMenuController implements Initializable, ControlledScreen {
    @FXML
    private Button btBack;
    @FXML
    private Text txtHeader;
    @FXML
    private Button btTambahCat;
    @FXML
    private Button btDaftarCat;
    @FXML
    private AnchorPane paneView;
    private ScreensController screensController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fadeIn();
    }    

    @FXML
    private void backAction(ActionEvent event) {
        screensController.setScreen(Main.MenuBukuID);
    }

    @FXML
    private void tambahAction(ActionEvent event) {
    }

    @FXML
    private void daftarAction(ActionEvent event) {
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        screensController = screenPage;
    }
    
    public void fadeIn(){
        new FadeInLeftTransition(btBack).play();
        new FadeInLeftTransition(btDaftarCat).play();
        new FadeInLeftTransition(btTambahCat).play();
        new FadeInLeftTransition(txtHeader).play();
    }
    
}
