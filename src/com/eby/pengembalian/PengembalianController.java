/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.pengembalian;

import com.eby.animations.FadeInLeftTransition;
import com.eby.animations.FadeOutRightTransition;
import com.eby.frameworkConfig.Config;
import com.eby.sql.connector.MySQLQuery;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
public class PengembalianController implements Initializable {

    @FXML
    private TextField txtIdPinjam;
    @FXML
    private TextField txtNIS;
    @FXML
    private TextField txtIdBuku;
    @FXML
    private TextField txtLamaPinjam;
    @FXML
    private TextField txtDenda;
    @FXML
    private Button btCari;
    @FXML
    private Button btKembaliBuku;
    private PengembalianModel model;
    private Config config;
    @FXML
    private GridPane grid;
    @FXML
    private Text textNIS;
    @FXML
    private Text txtHeader;
    @FXML
    private FontAwesomeIconView cariIcon;
    @FXML
    private FontAwesomeIconView closeIcon;
    @FXML
    private AnchorPane paneParent;
    @FXML
    private Text txtHari;
    @FXML
    private Button btRefresh;
    @FXML
    private FontAwesomeIconView kembaliIcon;
    @FXML
    private FontAwesomeIconView refreshIcon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initModel();
        fadeIn();
    }

    @FXML
    private void CariAction(ActionEvent event) {

        if (txtIdPinjam.equals("")) {
            config.dialog(Alert.AlertType.WARNING, "Masukkan id peminjaman terlebih dahulu !", null);
        } else {
            this.cariBuku();

            String denda = txtLamaPinjam.getText();
            String jmlhDenda = null;
            if (denda.equals("-7") || denda.equals("-6") || denda.equals("-5") || denda.equals("-4") || denda.equals("-3")
                    || denda.endsWith("-2") || denda.equals("-1") || denda.equals("0")) {
                jmlhDenda = "0";
            } else if (denda.equals("1")) {
                jmlhDenda = "500";
            } else if (denda.equals("2")) {
                jmlhDenda = "1000";
            } else if (denda.equals("3")) {
                jmlhDenda = "1500";
            } else if (denda.equals("4")) {
                jmlhDenda = "2000";
            } else if (denda.equals("5")) {
                jmlhDenda = "2500";
            } else if (denda.equals("6") || denda.equals("7") || denda.equals("8") || denda.equals("9") || denda.equals("10")) {
                jmlhDenda = "5000";
            }

            txtDenda.setText(jmlhDenda);
        }

    }

    @FXML
    private void KembaliBukuAction(ActionEvent event) {
        if (txtIdPinjam.getText().equals("")) {
            config.dialog(Alert.AlertType.WARNING, "Cari peminjaman terlebih dahulu !", null);
        } else {
            int id = Integer.valueOf(txtIdPinjam.getText());
            model.kembalikan(id);
            System.out.println(id);
            config.dialog(Alert.AlertType.INFORMATION, "Buku sudah di kembalikan !", null);
            clear();
        }

    }

    public void cariBuku() {
        String id = txtIdPinjam.getText();

        if (id.equals("")) {
            config.dialog(Alert.AlertType.WARNING, "Masukkan id peminjaman !", null);
        } else {
            MySQLQuery query = new MySQLQuery();
            String sql = "SELECT anggota_id, buku_id, (curdate()- tgl_kembali) from peminjaman where id = " + id;
            try {

                ResultSet rs = query.DMLQuery(sql);

                if (rs.next()) {
                    String v1 = rs.getString(1);
                    String v2 = rs.getString(2);
                    String v3 = rs.getString(3);

                    txtNIS.setText(v1);
                    txtIdBuku.setText(v2);
                    txtLamaPinjam.setText(v3);
                } else {
                    config.dialog(Alert.AlertType.WARNING, "Id peminjamana : " + id + " Tidak tersedia !", null);
                    clear();
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void clear() {
        txtIdPinjam.setText("");
        txtNIS.setText("");
        txtIdBuku.setText("");
        txtLamaPinjam.setText("");
        txtDenda.setText("");
    }

    public void initModel() {
        model = new PengembalianModel();
        model.setController(this);
    }

    @FXML
    private void closeAction(MouseEvent event) {
        fadeOut();
    }

    private void fadeIn() {
        new FadeInLeftTransition(grid).play();
        new FadeInLeftTransition(txtHeader).play();
        new FadeInLeftTransition(closeIcon).play();
        new FadeInLeftTransition(btCari).play();
        new FadeInLeftTransition(btKembaliBuku).play();
        new FadeInLeftTransition(cariIcon).play();
        new FadeInLeftTransition(txtHari).play();
        new FadeInLeftTransition(btRefresh).play();
        new FadeInLeftTransition(refreshIcon).play();
        new FadeInLeftTransition(kembaliIcon).play();
    }

    private void fadeOut() {
        new FadeOutRightTransition(grid).play();
        new FadeOutRightTransition(txtHeader).play();
        new FadeOutRightTransition(closeIcon).play();
        new FadeOutRightTransition(btCari).play();
        new FadeOutRightTransition(btKembaliBuku).play();
        new FadeOutRightTransition(cariIcon).play();
        new FadeOutRightTransition(txtHari).play();
        new FadeOutRightTransition(paneParent).play();
        new FadeOutRightTransition(btRefresh).play();
        new FadeOutRightTransition(refreshIcon).play();
        new FadeOutRightTransition(kembaliIcon).play();
    }

    @FXML
    private void refreshAction(ActionEvent event) {
        txtNIS.setText("");
        txtIdBuku.setText("");
        txtIdPinjam.setText("");
        txtLamaPinjam.setText("");
        txtDenda.setText("");
    }

}
