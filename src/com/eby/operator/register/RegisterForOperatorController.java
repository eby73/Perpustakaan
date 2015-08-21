/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.operator.register;


import com.eby.animations.FadeInLeftTransition;
import com.eby.animations.FadeOutRightTransition;
import com.eby.frameworkConfig.Config;
import com.eby.orm.entity.Anggota;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author eby
 */
public class RegisterForOperatorController implements Initializable {
    @FXML
    private TextField txtNIS;
    @FXML
    private TextField txtNama;
    @FXML
    private TextArea txtAlamat;
    @FXML
    private TextField txtKelas;
    @FXML
    private Button btReg;
    
    private RegOpModel regModel;
    private Config con;
    @FXML
    private GridPane grid1;
    @FXML
    private FontAwesomeIconView closeIcon;
    @FXML
    private FontAwesomeIconView userPlusIcon;
    @FXML
    private Text txtHeader;
    @FXML
    private AnchorPane paneParent;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initModel();
        fadeIn();
        con = new Config();
    }    

    @FXML
    private void registerAction(ActionEvent event) {
        this.saveAnggota();
    }
    
    public void initModel(){
     regModel = new RegOpModel();
     regModel.setOperatorCOntroller(this);
     
    }
    
    public void saveAnggota() {

        String NIS = txtNIS.getText();
        String nama = txtNama.getText();
        String alamat = txtAlamat.getText();
        String kelas = txtKelas.getText();
        if (NIS.equals("") || nama.equals("") || alamat.equals("") || kelas.equals("")) {
            
            con.dialog(Alert.AlertType.WARNING, "Isi data dengan lengkap !", null);

        } else {

            Anggota anggota = new Anggota();
            anggota.setId(Integer.valueOf(NIS));
            anggota.setNama(nama);
            anggota.setAlamat(alamat);
            anggota.setKelas(kelas);
            regModel.save(anggota);

            clearAnggota();
            con.dialog(Alert.AlertType.INFORMATION, "Register Berhasil !", null);
        }
    }
    
    
    public void clearAnggota() {
        txtNIS.setText("");
        txtNama.setText("");
        txtAlamat.setText("");
        txtKelas.setText("");
    }

    @FXML
    private void closeAction(MouseEvent event) {
        fadeOut();
    }
    
    public void fadeIn(){
        new FadeInLeftTransition(grid1).play();
        new FadeInLeftTransition(txtHeader).play();
        new FadeInLeftTransition(btReg).play();
        new FadeInLeftTransition(userPlusIcon).play();
        new FadeInLeftTransition(closeIcon).play();
    }
    
    void fadeOut(){
        new FadeOutRightTransition(grid1).play();
        new FadeOutRightTransition(txtHeader).play();
        new FadeOutRightTransition(btReg).play();
        new FadeOutRightTransition(userPlusIcon).play();
        new FadeOutRightTransition(closeIcon).play();
        new FadeOutRightTransition(paneParent).play();
    }
    
    
}
