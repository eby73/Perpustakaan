/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.menu.peminjaman;

import com.eby.animations.FadeInLeftTransition;
import com.eby.animations.FadeOutUpTransition;
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
public class PeminjamanMenuController implements Initializable {

    @FXML
    private AnchorPane paneParent;
    @FXML
    private Text txtPeminjaman;
    @FXML
    private Button btTambahPinjam;
    @FXML
    private FontAwesomeIconView iconP;
    @FXML
    private FontAwesomeIconView iconPlus;
    @FXML
    private Button btDaftar;
    @FXML
    private FontAwesomeIconView listIcon;
    @FXML
    private FontAwesomeIconView closeIcon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        new FadeInLeftTransition(iconP).play();
        new FadeInLeftTransition(txtPeminjaman).play();
        new FadeInLeftTransition(btTambahPinjam).play();
        new FadeInLeftTransition(iconPlus).play();
        new FadeInLeftTransition(btDaftar).play();
        new FadeInLeftTransition(listIcon).play();
        new FadeInLeftTransition(closeIcon).play();
    }

    @FXML
    private void peminjamanAction(ActionEvent event) {
    }

    @FXML
    private void daftarPeminjaman(ActionEvent event) {
    }

    @FXML
    private void closeAction(MouseEvent event) {
        new FadeOutUpTransition(iconP).play();
        new FadeOutUpTransition(txtPeminjaman).play();
        new FadeOutUpTransition(btTambahPinjam).play();
        new FadeOutUpTransition(iconPlus).play();
        new FadeOutUpTransition(btDaftar).play();
        new FadeOutUpTransition(listIcon).play();
        new FadeOutUpTransition(closeIcon).play();
        new FadeOutUpTransition(paneParent).play();
    }

}
