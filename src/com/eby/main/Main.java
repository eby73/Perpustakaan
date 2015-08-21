/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2013 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License"). You
 * may not use this file except in compliance with the License. You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package com.eby.main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Angie
 */
public class Main extends Application {

    public static String prefix = "/com/eby";

    // Admin Menu
    public static String mainMenuAdmin = "MainMenuAdmin";
    public static String mainMenuAdminFile = prefix + "/admin/mainmenu/MainMenuAdmin.fxml";

    public static String listBukuAdminID = "ListBukuAdmin";
    public static String listBukuAdminFile = prefix + "/admin/book/view/ListBuku.fxml";

    public static String bukuEditAdminID = "BukuEdit";
    public static String bukuEdiAdmintFile = prefix + "/admin/book/edit/BukuEditAdmin.fxml";

    public static String RegAdminId = "RegisterForAdmin";
    public static String RegAdminFile = prefix + "/admin/register/RegisterForAdmin.fxml";

    public static String listAdminID = "ListAdmin";
    public static String listAdminFile = prefix + "/admin/view/ListAdmin.fxml";

    public static String editAdminId = "EditAdmin";
    public static String editAdminFile = prefix + "/admin/edit/admin/EditAdmin.fxml";

    public static String listAnggotaAdminID = "ListAnggotaAdmin";
    public static String listAnggotaAdminFile = prefix + "/admin/anggota/view/ListAnggotaAdmin.fxml";

    // Operator Menu
    public static String mainMenuOperator = "MainMenuOperator";
    public static String mainMenuOperatorFile = prefix + "/operator/mainmenu/MainMenuOperator.fxml";

    public static String listBukuOperatorID = "ListBuku";
    public static String listBukuOperatorFile = prefix + "/operator/book/view/ListBukuOperator.fxml";

    public static String bukuEditOperatorID = "BukuEditOperator";
    public static String bukuEditOperatorFile = prefix + "/operator/book/edit/BukuEditOperator.fxml";

    public static String anggotaEditOpID = "AnggotaEdit";
    public static String anggotaEditOpFile = prefix + "/operator/anggota/edit/AnggotaOpEdit.fxml";

    public static String listAnggotaOpID = "ListAnggota";
    public static String listAnggotaOpFile = prefix + "/operator/anggota/view/ListAnggotaOperator.fxml";

    // Both Menu
    public static String loginID = "main";
    public static String loginFile = prefix + "/login/Login.fxml";

    public static String categoryID = "Category";
    public static String categoryFile = prefix + "/book/category/Category.fxml";

    public static String bukuAddId = "BukuAdd";
    public static String bukuAddFile = prefix + "/book/add/BukuAdd.fxml";
    
    
    public static String MenuCategoryID = "MenuCategory";
    public static String MenuCategoryFile = prefix + "/menu/category/CategoryMenu.fxml";
    
    public static String MenuUtamaAdminID = "MenuUtamaAdmin";
    public static String MenuUtamaAdminFile = prefix + "/admin/mainmenu/NewMainMenuAdmin.fxml";
    
    public static String MenuBukuID = "MenuBukuAdmin";
    public static String MenuBukuFile = prefix + "/menu/buku/NewBookMenu.fxml";
    
    public static String menuAnggotaID = "MenuAnggotaAdmin";
    public static String menuAnggotaFile = prefix + "/menu/anggota/AnggotaMenu.fxml";
    
    public static String menuAdminID = "MenuAdmin";
    public static String menuAdminFile = prefix + "/menu/admin/AdminMenu.fxml";
    
    public static String menuUtamaOperatorID = "MenuUtamaOperator";
    public static String menuUtamaOperatorFile = prefix + "/operator/mainmenu/NewMainMenuOperator.fxml";
    
    public static String menuBukuOpID = "MenuBukuOperator";
    public static String menuBukuOpFile = prefix + "/menu/buku/op/MenuBukuOp.fxml";
    
    public static String menuAnggotaOpID = "MenuAnggotaOperator";
    public static String menuAnggotaOpFile = prefix + "/menu/anggota/op/MenuAnggotaOp.fxml";

    @Override
    public void start(Stage primaryStage) {

        ScreensController mainContainer = new ScreensController();

        mainContainer.loadScreen(Main.loginID, Main.loginFile);
        mainContainer.loadScreen(Main.mainMenuAdmin, Main.mainMenuAdminFile);
        mainContainer.loadScreen(Main.mainMenuOperator, Main.mainMenuOperatorFile);
        mainContainer.loadScreen(Main.bukuAddId, Main.bukuAddFile);
        mainContainer.loadScreen(Main.RegAdminId, Main.RegAdminFile);
        mainContainer.loadScreen(Main.listAdminID, Main.listAdminFile);
        mainContainer.loadScreen(Main.editAdminId, Main.editAdminFile);
        mainContainer.loadScreen(Main.listAnggotaAdminID, Main.listAnggotaAdminFile);
        mainContainer.loadScreen(Main.listBukuAdminID, Main.listBukuAdminFile);
        mainContainer.loadScreen(Main.bukuEditAdminID, Main.bukuEdiAdmintFile);
        mainContainer.loadScreen(Main.categoryID, Main.categoryFile);
        mainContainer.loadScreen(Main.listBukuOperatorID, Main.listBukuOperatorFile);
        mainContainer.loadScreen(Main.bukuEditOperatorID, Main.bukuEditOperatorFile);
        mainContainer.loadScreen(Main.anggotaEditOpID, Main.anggotaEditOpFile);
        mainContainer.loadScreen(Main.listAnggotaOpID, Main.listAnggotaOpFile);
        mainContainer.loadScreen(Main.MenuCategoryID, Main.MenuCategoryFile);
        mainContainer.loadScreen(Main.MenuUtamaAdminID, Main.MenuUtamaAdminFile);
        mainContainer.loadScreen(Main.MenuBukuID, Main.MenuBukuFile);
        mainContainer.loadScreen(Main.menuAnggotaID, Main.menuAnggotaFile);
        mainContainer.loadScreen(Main.menuAdminID, Main.menuAdminFile);
        mainContainer.loadScreen(Main.menuUtamaOperatorID, Main.menuUtamaOperatorFile);
        mainContainer.loadScreen(Main.menuBukuOpID, Main.menuBukuOpFile);
        mainContainer.loadScreen(Main.menuAnggotaOpID, Main.menuAnggotaOpFile);

        mainContainer.setScreen(Main.loginID);

        Group root = new Group();
        root.getChildren().addAll(mainContainer);
        Scene scene = new Scene(root);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
