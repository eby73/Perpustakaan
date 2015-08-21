/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.book.penerbit;

import com.eby.animations.FadeInLeftTransition;
import com.eby.animations.FadeOutUpTransition;
import com.eby.frameworkConfig.Config;
import com.eby.orm.entity.Penerbit;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author eby
 */
public class PenerbitController implements Initializable {

    @FXML
    private TextField txtNama;
    @FXML
    private TextArea txtAlamat;
    @FXML
    private TextField txtId;
    @FXML
    private TableView<Penerbit> tablePenerbit;
    @FXML
    private Button btUpdate;
    @FXML
    private Button btAdd;
    @FXML
    private Button btEdit;
    @FXML
    private Button btDelete;
    private PenerbitTableModel tableModel;
    private PenerbitModel model;
    private Config con;
    @FXML
    private TextField txtCari;
    @FXML
    private GridPane grid1;
    @FXML
    private FontAwesomeIconView upIcon;
    @FXML
    private FontAwesomeIconView plusIcon;
    @FXML
    private FontAwesomeIconView edIcon;
    @FXML
    private FontAwesomeIconView delIcon;
    @FXML
    private FontAwesomeIconView closeIcon;
    @FXML
    private AnchorPane paneView;
    @FXML
    private FontAwesomeIconView cariIcon;

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
    private void UpdateAction(ActionEvent event) {
        this.update();
    }

    @FXML
    private void addAction(ActionEvent event) {
        this.save();
    }

    @FXML
    private void editAction(ActionEvent event) {
        int index = tablePenerbit.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            Penerbit pen = tableModel.getItem().get(index);
            txtId.setText(String.valueOf(pen.getId()));
            txtNama.setText(pen.getNama());
            txtAlamat.setText(pen.getAlamat());
        } else {
            con.dialog(Alert.AlertType.WARNING, "Pilih data terlebih dahulu !", null);
        }

    }

    @FXML
    private void deleteAction(ActionEvent event) {
        this.delete();
    }

    public void initTable() {
        tableModel = new PenerbitTableModel();
        tablePenerbit.setItems(tableModel.getItem());
        tablePenerbit.getColumns().addAll(tableModel.getAllColumn());
        tableModel.getItem().addAll(model.list());
    }

    public void initModel() {
        model = new PenerbitModel();
        model.setController(this);
    }

    public void save() {
        Penerbit pen = new Penerbit();
        String nama = txtNama.getText();
        String alamat = txtAlamat.getText();

        if (nama.equals("") || alamat.equals("")) {
            con.dialog(Alert.AlertType.WARNING, "Isi data dengan lengkap !", null);
        } else {

            pen.setNama(txtNama.getText());
            pen.setAlamat(txtAlamat.getText());
            model.save(pen);
            reset();
            con.dialog(Alert.AlertType.INFORMATION, "Data berhasil di simpan", null);
            loadData();
        }
    }

    public void update() {
        Penerbit pen = new Penerbit();
        String nama = txtNama.getText();
        String alamat = txtAlamat.getText();

        if (nama.equals("") || alamat.equals("")) {
            con.dialog(Alert.AlertType.WARNING, "Isi data dengan lengkap !", null);
        } else {
            
            pen.setId(Integer.valueOf(txtId.getText()));
            pen.setNama(txtNama.getText());
            pen.setAlamat(txtAlamat.getText());
            model.update(pen);
            reset();
            con.dialog(Alert.AlertType.INFORMATION, "Update data berhasil !", null);
            loadData();
        }
    }

    public void reset() {
        txtId.setText("");
        txtNama.setText("");
        txtAlamat.setText("");
    }

    public void delete() {
        int index = tablePenerbit.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            Penerbit p = tableModel.getItem().get(index);
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
            con.dialog(Alert.AlertType.WARNING, "Pilih data terlebih dahulu !", null);
        }
    }

    public void loadData() {
        tableModel.getItem().remove(0, tableModel.getItem().size());
        tableModel.getItem().addAll(model.list());
        tablePenerbit.setItems(tableModel.getItem());
    }

    @FXML
    private void cariAction(KeyEvent event) {
        String keyword = txtCari.getText();
        if (keyword.equals("")) {
            loadData();
        } else {
            tableModel.getItem().remove(0, tableModel.getItem().size());
            tableModel.getItem().addAll(model.findData(keyword));
            tablePenerbit.setItems(tableModel.getItem());
        }
    }
    
    public void fadeIn(){
        new FadeInLeftTransition(grid1).play();
        new FadeInLeftTransition(btUpdate).play();
        new FadeInLeftTransition(upIcon).play();
        new FadeInLeftTransition(btAdd).play();
        new FadeInLeftTransition(plusIcon).play();
        new FadeInLeftTransition(tablePenerbit).play();
        new FadeInLeftTransition(btEdit).play();
        new FadeInLeftTransition(edIcon).play();
        new FadeInLeftTransition(btDelete).play();
        new FadeInLeftTransition(delIcon).play();
        new FadeInLeftTransition(txtCari).play();
        new FadeInLeftTransition(cariIcon).play();
        new FadeInLeftTransition(closeIcon).play();
    }
    
    void fadeOut(){
        new FadeOutUpTransition(grid1).play();
        new FadeOutUpTransition(btUpdate).play();
        new FadeOutUpTransition(upIcon).play();
        new FadeOutUpTransition(btAdd).play();
        new FadeOutUpTransition(plusIcon).play();
        new FadeOutUpTransition(tablePenerbit).play();
        new FadeOutUpTransition(btEdit).play();
        new FadeOutUpTransition(edIcon).play();
        new FadeOutUpTransition(btDelete).play();
        new FadeOutUpTransition(delIcon).play();
        new FadeOutUpTransition(txtCari).play();
        new FadeOutUpTransition(closeIcon).play();
        new FadeOutUpTransition(paneView).play();
        new FadeOutUpTransition(cariIcon).play();
    }

    @FXML
    private void closeAction(MouseEvent event) {
        fadeOut();
    }

}
