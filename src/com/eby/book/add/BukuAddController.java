/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.book.add;

import com.eby.admin.book.view.ListBukuController;
import com.eby.animations.FadeInLeftTransition;
import com.eby.animations.FadeOutUpTransition;
import com.eby.autocomplete.ComboBoxAutoComplete;
import com.eby.frameworkConfig.Config;
import com.eby.orm.entity.Buku;
import com.eby.orm.entity.Category;
import com.eby.orm.entity.Penerbit;
import com.eby.main.ControlledScreen;
import com.eby.main.Main;
import com.eby.main.ScreensController;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author eby
 */
public class BukuAddController implements Initializable, ControlledScreen {

    @FXML
    private TextField txtJudul;
    @FXML
    private ComboBox<String> cbPenerbit;
    @FXML
    private TextField txtThnTerbit;
    @FXML
    private TextField txtJmlhHal;
    @FXML
    private TextArea txtSynopsis;
    @FXML
    private TextField txtPengarang;
    @FXML
    private ListView<String> listCategory;
    @FXML
    private TextField txtIdBuku;
    @FXML
    private Button btAdd;
    private BukuAddModel model;
    private CategoryListModel listModel;
    private PenerbitComboBoxModel penComboModel;

    private Config con;
    private ListBukuController listBuku;

    private ScreensController screenController;
    @FXML
    private GridPane gridPane1;
    @FXML
    private GridPane gridPane2;
    @FXML
    private Text Header;
    @FXML
    private FontAwesomeIconView iconPlus;
    @FXML
    private FontAwesomeIconView closeIcon;
    @FXML
    private AnchorPane paneView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initModel();
        initCategoryListModel();
        initPenerbitComboModel();
        fadeIn();
    }

    @Override
    public void setScreenParent(ScreensController screenPage) {
        screenController = screenPage;
    }

    public void initModel() {
        model = new BukuAddModel();
        model.setController(this);

    }

    public void initCategoryListModel() {
        listModel = new CategoryListModel();
        listModel.addList(model.lisCat());
        listCategory.setItems(listModel.getItems());
        listCategory.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void initPenerbitComboModel() {
        penComboModel = new PenerbitComboBoxModel();
        penComboModel.add(model.listPen());
        cbPenerbit.setItems(penComboModel.getItems());
    }

    public void save() {

        Buku buku = new Buku();
        String id = txtIdBuku.getText();
        String judul = txtJudul.getText();
        int catId = listCategory.getSelectionModel().getSelectedIndex();
        int penId = cbPenerbit.getSelectionModel().getSelectedIndex();
        String pengarang = txtPengarang.getText();
        String tahun = txtThnTerbit.getText();
        String jumlah = txtJmlhHal.getText();
        String synopsis = txtSynopsis.getText();

        if (id != null && judul != null && catId != -1 && penId != -1 && pengarang != null) {

            buku.setId(Integer.valueOf(id));
            buku.setJudul(judul);

            ObservableList<Integer> indices = listCategory.getSelectionModel().getSelectedIndices();
            for (int i = 0; i < indices.size(); i++) {
                Category cat = listModel.get(indices.get(i));
                buku.setCategory(cat);
            }

            int index = cbPenerbit.getSelectionModel().getSelectedIndex();
            if (index != -1) {
                Penerbit pen = penComboModel.get(index);
                buku.setPenerbit(pen);
            }

            buku.setPengarang(pengarang);
            buku.setTahunTerbit(tahun);
            buku.setJumlahHalaman(jumlah);
            buku.setSynopsis(synopsis);
            model.save(buku);
            clear();

            con.dialog(Alert.AlertType.INFORMATION, "Data berhasil di simpan !", null);
        } else {
            con.dialog(Alert.AlertType.WARNING, "Isi data dengan lengkap !", null);
        }
    }

    public void update() {

        Buku buku = new Buku();
        String id = txtIdBuku.getText();
        String judul = txtJudul.getText();
        int catId = listCategory.getSelectionModel().getSelectedIndex();
        int penId = cbPenerbit.getSelectionModel().getSelectedIndex();
        String pengarang = txtPengarang.getText();
        String tahun = txtThnTerbit.getText();
        String jumlah = txtJmlhHal.getText();
        String synopsis = txtSynopsis.getText();

        if (id != null && judul != null && catId != -1 && penId != -1 && pengarang != null) {

            buku.setId(Integer.valueOf(id));
            buku.setJudul(judul);

            ObservableList<Integer> indices = listCategory.getSelectionModel().getSelectedIndices();
            for (int i = 0; i < indices.size(); i++) {
                Category cat = listModel.get(indices.get(i));
                buku.setCategory(cat);
            }

            int index = cbPenerbit.getSelectionModel().getSelectedIndex();
            if (index != -1) {
                Penerbit pen = penComboModel.get(index);
                buku.setPenerbit(pen);
            }

            buku.setPengarang(pengarang);
            buku.setTahunTerbit(tahun);
            buku.setJumlahHalaman(jumlah);
            buku.setSynopsis(synopsis);
            model.update(buku);
            clear();

            con.dialog(Alert.AlertType.INFORMATION, "Data berhasil di update !", null);
        } else {
            con.dialog(Alert.AlertType.WARNING, "idi sata dengan lengkap !", null);
        }
    }

    private void clear() {
        txtIdBuku.setText("");
        txtJudul.setText("");
        txtPengarang.setText("");
        txtJmlhHal.setText("");
        txtSynopsis.setText("");
        txtThnTerbit.setText("");
        listCategory.getSelectionModel().select(-1);
        cbPenerbit.getSelectionModel().select(-1);
    }

    @FXML
    private void addAction(ActionEvent event) {
        this.save();
    }

    public void test() {
        System.out.println("lolossssssssssss !!!!!!");
    }

    public void addControlScreen() {
        screenController.addControllerScreen("BukuController", (ControlledScreen) screenController.getScreen(Main.bukuAddId));
    }

    @FXML
    private void closeAction(MouseEvent event) {
        fadeOut();
    }

    private void fadeIn() {
        new FadeInLeftTransition(gridPane1).play();
        new FadeInLeftTransition(gridPane2).play();
        new FadeInLeftTransition(btAdd).play();
        new FadeInLeftTransition(iconPlus).play();
        new FadeInLeftTransition(closeIcon).play();
        new FadeInLeftTransition(Header).play();
    }

    private void fadeOut() {
        new FadeOutUpTransition(gridPane1).play();
        new FadeOutUpTransition(gridPane2).play();
        new FadeOutUpTransition(btAdd).play();
        new FadeOutUpTransition(iconPlus).play();
        new FadeOutUpTransition(closeIcon).play();
        new FadeOutUpTransition(Header).play();
        new FadeOutUpTransition(paneView).play();
    }

    @FXML
    private void comboReleased(KeyEvent event) {
        new ComboBoxAutoComplete<>(cbPenerbit);
    }

}
