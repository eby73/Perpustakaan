/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.admin.mainmenu;

import com.eby.frameworkConfig.Config;
import com.eby.helper.ControllerHelper;
import com.eby.main.ControlledScreen;
import com.eby.main.ScreensController;
import com.eby.main.Main;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author eby
 */
public class MainMenuAdminController implements Initializable, ControlledScreen {

    @FXML
    private Button maximize;
    @FXML
    private Button minimize;
    @FXML
    private Button close;

    Stage stage;
    Rectangle2D rec2;
    Double w, h;
    @FXML
    private Button fullscreen;
    @FXML
    private ListView<String> listView;

    private Config con;
    @FXML
    private AnchorPane paneView;

    ScreensController screenController;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        con = new Config();

        rec2 = Screen.getPrimary().getVisualBounds();
        w = 0.1;
        h = 0.1;
        listMenuContent();
        runLater();

    }

    public AnchorPane getPaneView() {
        return paneView;
    }

    public void setPaneView(AnchorPane paneView) {
        this.paneView = paneView;
    }

    @FXML
    private void maximizeAction(ActionEvent event) {
        stage = (Stage) maximize.getScene().getWindow();
        if (stage.isMaximized()) {
            if (w == rec2.getWidth() && h == rec2.getHeight()) {
                stage.setMaximized(false);
                stage.setHeight(600);
                stage.setWidth(800);
                stage.centerOnScreen();
                maximize.getStyleClass().remove("decoration-button-restore");

            } else {
                stage.setMaximized(false);
                maximize.getStyleClass().remove("decoration-button-restore");

            }

        } else {
            stage.setMaximized(true);
            stage.setHeight(rec2.getHeight());
            maximize.getStyleClass().add("decoration-button-restore");

        }
    }

    @FXML
    private void minimizeAction(ActionEvent event) {

        stage = (Stage) minimize.getScene().getWindow();
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
        }else{
            stage.setIconified(true);
        }        
    }

    @FXML
    private void closeAction(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    private void fullscreenAction(ActionEvent event) {
        stage = (Stage) fullscreen.getScene().getWindow();
        if (stage.isFullScreen()) {
            stage.setFullScreen(false);
        } else {
            stage.setFullScreen(true);
        }
    }

    public void listMenuContent() {
        ObservableList list = FXCollections.observableArrayList();
        list.addAll("","Register", "Buku", "Peminjaman", "Pengembalian", "Daftar Anggota", "Daftar Buku", "Daftar User", "", "",
                "", "", "", "", "Log Out");
        listView.setItems(list);
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        screenController = screenPage;
    }

    @FXML
    private void listViewAction(MouseEvent event) {
        switch (listView.getSelectionModel().getSelectedIndex()) {

            case 1: {
                con.loadAnchorPane(paneView, "/admin/register/RegisterForAdmin.fxml");
            }
            break;

            case 2: {
                con.loadAnchorPane(paneView, "/book/menu/BukuMenu.fxml");
            }
            break;

            case 3: {
                con.loadAnchorPane(paneView, "/peminjaman/Peminjaman.fxml");
            }
            break;

            case 4: {
                con.loadAnchorPane(paneView, "/pengembalian/Pengembalian.fxml");
            }
            break;

            case 5: {
                screenController.setScreen(Main.listAnggotaAdminID);
                ControllerHelper.getListAnggotaController(screenController).loadData();
            }
            break;
            case 6: {
                screenController.setScreen(Main.listBukuAdminID);
                ControllerHelper.getListBukuController(screenController).loadData();
            }
            break;

            case 7: {
                screenController.setScreen(Main.listAdminID);
                ControllerHelper.getListAdminController(screenController).loadData();
            }
            break;

            case 14: {
                screenController.setScreen(Main.loginID);
            }
            break;

        }
    }

    public void resize() {
        Rectangle2D screen = Screen.getPrimary().getVisualBounds();
        screen.getHeight();
        screen.getWidth();

        paneView.autosize();
    }

    public void runLater() {
        Platform.runLater(() -> {
            con.loadAnchorPane(paneView, "/admin/register/RegisterForAdmin.fxml");
            listView.requestFocus();
        });
    }
}
