/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.book.category;

import com.eby.animations.FadeInLeftTransition;
import com.eby.animations.FadeOutUpTransition;
import com.eby.frameworkConfig.Config;
import com.eby.main.ControlledScreen;
import com.eby.main.ScreensController;
import com.eby.orm.entity.Category;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
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
public class CategoryController implements Initializable, ControlledScreen {

    @FXML
    private TextField txtNama;
    @FXML
    private ListView<String> listParent;
    @FXML
    private Button btAdd;
    @FXML
    private TableView<Category> tableCategory;
    @FXML
    private Button btEdit;
    @FXML
    private Button btDelete;

    private CategoryTableModel tableModel;
    @FXML
    private Button btUpdate;
    @FXML
    private TextField txtId;

    private CategoryModel model;
    private CategoryListModel listModel;
    private Config con;
    private ComboBox<String> cbJenisCari;
    @FXML
    private TextField txtCari;
    private ScreensController screensController;
    @FXML
    private FontAwesomeIconView iconClose;
    @FXML
    private GridPane grid1;
    private GridPane grid2;
    @FXML
    private FontAwesomeIconView upIcon;
    @FXML
    private FontAwesomeIconView plusIcon;
    @FXML
    private FontAwesomeIconView editIcon;
    @FXML
    private FontAwesomeIconView deleteIcon;
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
        initListView();
        fadeIn();
    }

    @FXML
    private void AddAction(ActionEvent event) {

        String id = txtId.getText();
        String nama = txtNama.getText();

        if (nama.equals("")) {
            con.dialog(Alert.AlertType.WARNING, "isi data dengan lengkap", null);
        } else {
            Category cat = new Category();
            cat.setNama(nama);

            int index = listParent.getSelectionModel().getSelectedIndex();
            if (index != -1) {
                Category parent = listModel.get(index);
                cat.setCategory(parent);
            }

            model.save(cat);

            clear();
            con.dialog(Alert.AlertType.INFORMATION, "Data berhasil disimpan", null);
            loadData();
            initListView();
        }
    }

    @FXML
    private void editAction(ActionEvent event) {
        int index = tableCategory.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            Category cat = tableModel.getItem().get(index);
            txtId.setText(String.valueOf(cat.getId()));
            txtNama.setText(cat.getNama());
        } else {
            con.dialog(Alert.AlertType.WARNING, "Pilih data terlebih dahulu !", null);
        }

    }

    @FXML
    private void deleteAction(ActionEvent event) {
        int index = tableCategory.getSelectionModel().getSelectedIndex();
        if (index != -1) {
            Category category = tableModel.getItem().get(index);
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Yakin ingin menghapus data?");
            alert.setHeaderText(null);
            alert.getButtonTypes().addAll(ButtonType.CANCEL);
            Optional<ButtonType> choose = alert.showAndWait();
            if (choose.get() == ButtonType.OK) {
                model.delete(category);
                con.dialog(Alert.AlertType.INFORMATION, "Data berhasil di hapus !", null);
                loadData();
                initListView();
            } else {

            }

        } else {
            con.dialog(Alert.AlertType.WARNING, "Pilih data terlebih dahulu !", null);
        }
    }

    public void initTable() {
        tableModel = new CategoryTableModel();
        tableCategory.setItems(tableModel.getItem());
        tableCategory.getColumns().addAll(tableModel.getAllColumn());
        tableModel.getItem().addAll(model.list());
    }

    @FXML
    private void updateAction(ActionEvent event) {
        String id = txtId.getText();
        String nama = txtNama.getText();

        if (nama.equals("")) {
            con.dialog(Alert.AlertType.WARNING, "isi data dengan lengkap", null);
        } else {
            Category cat = new Category();
            cat.setId(Integer.valueOf(txtId.getText()));
            cat.setNama(nama);
            int index = listParent.getSelectionModel().getSelectedIndex();
            if (index != -1) {
                Category parent = listModel.get(index);
                cat.setCategory(parent);
            }

            model.update(cat);

            clear();
            con.dialog(Alert.AlertType.INFORMATION, "Update data berhasil !", null);
            loadData();
            initListView();
        }

    }

    public void initListView() {
        listModel = new CategoryListModel();
        listModel.addList(model.list());
        listParent.setItems(listModel.getItems());
    }

    public void initModel() {
        model = new CategoryModel();
        model.setController(this);
    }

    public void clear() {
        txtId.setText("");
        txtNama.setText("");
        listParent.getSelectionModel().clearSelection();
    }

    public void loadData() {
        tableModel.getItem().remove(0, tableModel.getItem().size());
        tableModel.getItem().addAll(model.list());
        tableCategory.setItems(tableModel.getItem());

    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        screensController = screenPage;
    }

    @FXML
    private void cariAction(KeyEvent event) {
        String keyword = txtCari.getText();
        if (keyword.equals("")) {
            loadData();
        } else {
            tableModel.getItem().remove(0, tableModel.getItem().size());
            tableModel.getItem().addAll(model.findByNama(keyword));
            tableCategory.setItems(tableModel.getItem());
        }
    }

    @FXML
    private void closeAction(MouseEvent event) {
        fadeOut();
    }
    
    public void fadeIn(){
        new FadeInLeftTransition(btAdd).play();
        new FadeInLeftTransition(grid1).play();
        new FadeInLeftTransition(cariIcon).play();
        new FadeInLeftTransition(iconClose).play();
        new FadeInLeftTransition(tableCategory).play();
        new FadeInLeftTransition(btDelete).play();
        new FadeInLeftTransition(btEdit).play();
        new FadeInLeftTransition(btUpdate).play();
        new FadeInLeftTransition(upIcon).play();
        new FadeInLeftTransition(editIcon).play();
        new FadeInLeftTransition(deleteIcon).play();
        new FadeInLeftTransition(plusIcon).play();
        new FadeInLeftTransition(txtCari).play();
        new FadeInLeftTransition(cariIcon).play();
    }
    public void fadeOut(){
        new FadeOutUpTransition(btAdd).play();
        new FadeOutUpTransition(grid1).play();
        new FadeOutUpTransition(cariIcon).play();
        new FadeOutUpTransition(iconClose).play();
        new FadeOutUpTransition(tableCategory).play();
        new FadeOutUpTransition(btDelete).play();
        new FadeOutUpTransition(btEdit).play();
        new FadeOutUpTransition(btUpdate).play();
        new FadeOutUpTransition(upIcon).play();
        new FadeOutUpTransition(editIcon).play();
        new FadeOutUpTransition(deleteIcon).play();
        new FadeOutUpTransition(plusIcon).play();
        new FadeOutUpTransition(txtCari).play();
        new FadeOutUpTransition(paneView).play();
        new FadeOutUpTransition(cariIcon).play();
    }
    
}
