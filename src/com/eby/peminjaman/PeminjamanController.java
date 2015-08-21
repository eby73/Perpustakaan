/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.peminjaman;

import com.eby.animations.FadeInLeftTransition;
import com.eby.animations.FadeOutRightTransition;
import com.eby.frameworkConfig.Config;
import com.eby.orm.entity.Anggota;
import com.eby.orm.entity.Buku;
import com.eby.orm.entity.Peminjaman;
import com.eby.report.JspReport;
import com.eby.report.Report;
import com.eby.sql.connector.MySQLQuery;
import com.mysql.jdbc.PreparedStatement;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.converter.LocalDateStringConverter;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRException;

/**
 * FXML Controller class
 *
 * @author eby
 */
public class PeminjamanController implements Initializable {

    @FXML
    private TextField txtNIS;
    @FXML
    private TextField txtIDBuku;
    @FXML
    private DatePicker datePinjam;
    @FXML
    private DatePicker dateKembali;
    @FXML
    private Button btCekNIS;
    @FXML
    private Button btAddPinjam;
    @FXML
    private TableView<Peminjaman> tablePinjam;

    private ObservableList<Peminjaman> listData;
    private PeminjamanTableModel tableModel;

    Config con;
    @FXML
    private Button btEdit;
    @FXML
    private Button btUpdate;

    private PeminjamanModel model;
    @FXML
    private TextField txtCari;
    @FXML
    private GridPane grid1;
    @FXML
    private FontAwesomeIconView grid2;
    @FXML
    private FontAwesomeIconView upIcon;
    @FXML
    private FontAwesomeIconView plusIcon;
    @FXML
    private FontAwesomeIconView editIcon;
    @FXML
    private FontAwesomeIconView iconClose;
    @FXML
    private Text txtHeader;
    @FXML
    private FontAwesomeIconView fontHeader;
    @FXML
    private FontAwesomeIconView searchIcon;
    @FXML
    private AnchorPane paneView;
    @FXML
    private TextField txtId;
    @FXML
    private Button btReport;
    @FXML
    private FontAwesomeIconView reportIcon;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initModel();
        initTable();
        con = new Config();
        fadeIn();
    }

    @FXML
    private void cekNISAction(ActionEvent event) {
        this.cekNIS();
    }

    @FXML
    private void AddPinjamAction(ActionEvent event) {
        this.addPinjam();
    }

    public void initTable() {
        tableModel = new PeminjamanTableModel();
        tablePinjam.setItems(tableModel.getItem());
        tablePinjam.getColumns().addAll(tableModel.getAllColumn());
        tableModel.getItem().addAll(model.list());
    }

    @FXML
    private void btEditAction(ActionEvent event) {
        int index = tablePinjam.getSelectionModel().getSelectedIndex();
        LocalDateStringConverter ld = new LocalDateStringConverter();
        if (index != -1) {
            Peminjaman p = tableModel.getItem().get(index);
            txtId.setText(String.valueOf(p.getId()));
            txtNIS.setText(String.valueOf(p.getAnggota().getId()));
            txtIDBuku.setText(String.valueOf(p.getBuku().getId()));
        } else {
            con.dialog(Alert.AlertType.WARNING, "Pilih data terlebih dahulu !", null);
        }

    }

    private void deleteAction(ActionEvent event) {
        int index = tablePinjam.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            Peminjaman p = tableModel.getItem().get(index);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Yakin ingin menghapus data?");
            alert.setHeaderText(null);
            alert.getButtonTypes().addAll(ButtonType.CANCEL);
            Optional<ButtonType> choose = alert.showAndWait();
            if (choose.get() == ButtonType.OK) {
                model.delete(p);
                loadData();
                con.dialog(Alert.AlertType.INFORMATION, "Data berhasil di hapus !", null);
            } else {

            }
        } else {
            con.dialog(Alert.AlertType.INFORMATION, "Data berhasil di hapus !", null);
        }
    }

    @FXML
    private void updateAction(ActionEvent event) {
        Peminjaman p = new Peminjaman();
        Anggota a = new Anggota();
        Buku b = new Buku();
        String NIS = txtNIS.getText();
        String idBuku = txtIDBuku.getText();

        if (NIS.equals("") || idBuku.equals("") || datePinjam == null || dateKembali == null) {
            con.dialog(Alert.AlertType.WARNING, "Isi data dengan lengakap !!!", null);
        } else {

            MySQLQuery query = new MySQLQuery();
            String sql = "select id"
                    + " from anggota where id ='" + NIS + "'";
            PreparedStatement pst = (PreparedStatement) query.prepareQuery(sql);
            ResultSet rs;
            try {
                rs = (ResultSet) query.DMLQuery(sql);
                if (rs.next()) {
                    p.setId(Integer.valueOf(txtId.getText()));
                    a.setId(Integer.valueOf(txtNIS.getText()));
                    p.setAnggota(a);
                    b.setId(Integer.valueOf(txtIDBuku.getText()));
                    p.setBuku(b);

                    p.setTglPinjam(java.sql.Date.valueOf(datePinjam.getValue()));
                    p.setTglKembali(java.sql.Date.valueOf(dateKembali.getValue()));
                    model.update(p);
                    con.dialog(Alert.AlertType.INFORMATION, "Data berhasil di update !", null);
                    clear();
                    loadData();
                } else {
                    con.dialog(Alert.AlertType.WARNING, "NIS " + NIS + " Tidak Tersedia, \n "
                            + "Silahkan melakukan cek NIS terlebih dahulu  !", null);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void initModel() {
        model = new PeminjamanModel();
        model.setController(this);
    }

    public void cekNIS() {
        String NIS = txtNIS.getText();

        MySQLQuery query = new MySQLQuery();
        String sql = "select id"
                + " from anggota where id ='" + NIS + "'";
        PreparedStatement pst = (PreparedStatement) query.prepareQuery(sql);
        ResultSet rs;
        try {
            rs = (ResultSet) query.DMLQuery(sql);
            if (rs.next()) {
                con.dialog(Alert.AlertType.INFORMATION, "NIS " + NIS + " Tersedia !", null);
            } else {
                con.dialog(Alert.AlertType.WARNING, "NIS " + NIS + " Belum Terdaftar sebagai anggota !", null);
            }
        } catch (Exception e) {
        }
    }

    public void addPinjam() {
        Peminjaman p = new Peminjaman();
        Anggota a = new Anggota();
        Buku b = new Buku();
        String NIS = txtNIS.getText();
        String idBuku = txtIDBuku.getText();
        LocalDate pinjam = datePinjam.getValue();
        LocalDate kembali = dateKembali.getValue();

        if (NIS.equals("") || idBuku.equals("") || datePinjam == null || dateKembali == null) {
            con.dialog(Alert.AlertType.WARNING, "Isi data dengan lengakap !!!", null);
        } else {

            MySQLQuery query = new MySQLQuery();
            String sql = "select id"
                    + " from anggota where id ='" + NIS + "'";
            PreparedStatement pst = (PreparedStatement) query.prepareQuery(sql);
            ResultSet rs;
            try {
                rs = (ResultSet) query.DMLQuery(sql);
                if (rs.next()) {

                    a.setId(Integer.valueOf(txtNIS.getText()));
                    p.setAnggota(a);
                    b.setId(Integer.valueOf(txtIDBuku.getText()));
                    p.setBuku(b);

                    p.setTglPinjam(java.sql.Date.valueOf(pinjam));
                    p.setTglKembali(java.sql.Date.valueOf(kembali));
                    model.save(p);
                    con.dialog(Alert.AlertType.INFORMATION, "Data berhasil di simpan !", null);
                    clear();
                    loadData();
                } else {
                    con.dialog(Alert.AlertType.WARNING, "NIS " + NIS + " Tidak Tersedia, \n "
                            + "Silahkan melakukan cek NIS terlebih dahulu  !", null);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void loadData() {
        tableModel.getItem().remove(0, tableModel.getItem().size());
        tablePinjam.getItems().addAll(model.list());
        tablePinjam.setItems(tableModel.getItem());
    }

    public void clear() {
        txtNIS.setText("");
        txtIDBuku.setText("");
        datePinjam.setValue(null);
        dateKembali.setValue(null);
    }

    @FXML
    private void cariAction(KeyEvent event) {
        String keyword = txtCari.getText();
        if (keyword.equals("")) {
            loadData();
        } else {
            tableModel.getItem().remove(0, tableModel.getItem().size());
            tablePinjam.getItems().addAll(model.getByID(Integer.valueOf(keyword)));
            tablePinjam.setItems(tableModel.getItem());

        }
    }

    @FXML
    private void closeAction(MouseEvent event) {
        fadeOut();
    }

    void fadeIn() {
        new FadeInLeftTransition(grid1).play();
        new FadeInLeftTransition(btUpdate).play();
        new FadeInLeftTransition(upIcon).play();
        new FadeInLeftTransition(btEdit).play();
        new FadeInLeftTransition(editIcon).play();
        new FadeInLeftTransition(btCekNIS).play();
        new FadeInLeftTransition(searchIcon).play();
        new FadeInLeftTransition(tablePinjam).play();
        new FadeInLeftTransition(txtHeader).play();
        new FadeInLeftTransition(fontHeader).play();
        new FadeInLeftTransition(btAddPinjam).play();
        new FadeInLeftTransition(plusIcon).play();
        new FadeInLeftTransition(iconClose).play();
        new FadeInLeftTransition(grid2).play();
        new FadeInLeftTransition(txtCari).play();
        new FadeInLeftTransition(btReport).play();
        new FadeInLeftTransition(reportIcon).play();
    }

    void fadeOut() {
        new FadeOutRightTransition(grid1).play();
        new FadeOutRightTransition(btUpdate).play();
        new FadeOutRightTransition(upIcon).play();
        new FadeOutRightTransition(btEdit).play();
        new FadeOutRightTransition(editIcon).play();
        new FadeOutRightTransition(btCekNIS).play();
        new FadeOutRightTransition(searchIcon).play();
        new FadeOutRightTransition(tablePinjam).play();
        new FadeOutRightTransition(txtHeader).play();
        new FadeOutRightTransition(fontHeader).play();
        new FadeOutRightTransition(btAddPinjam).play();
        new FadeOutRightTransition(plusIcon).play();
        new FadeOutRightTransition(grid2).play();
        new FadeOutRightTransition(iconClose).play();
        new FadeOutRightTransition(paneView).play();
        new FadeOutRightTransition(txtCari).play();
        new FadeOutRightTransition(btReport).play();
        new FadeOutRightTransition(reportIcon).play();
    }

    @FXML
    private void reportAction(ActionEvent event) throws FileNotFoundException, JRException, SQLException, ClassNotFoundException, DRException, IOException {
        Report rp = new Report();
        rp.report();
    }
}
