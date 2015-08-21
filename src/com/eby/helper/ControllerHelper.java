/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eby.helper;

import com.eby.book.add.BukuAddController;
import com.eby.admin.register.RegisterForAdminController;
import com.eby.admin.view.ListAdminController;
import com.eby.admin.anggota.view.ListAnggotaAdminController;
import com.eby.admin.book.edit.BukuEditAdminController;
import com.eby.admin.book.view.ListBukuController;
import com.eby.admin.edit.admin.EditAdminController;
import com.eby.main.ScreensController;
import com.eby.main.Main;
import com.eby.admin.mainmenu.MainMenuAdminController;
import com.eby.admin.mainmenu.NewMainMenuAdminController;
import com.eby.login.LoginController;
import com.eby.operator.anggota.edit.AnggotaOpEditController;
import com.eby.operator.anggota.view.ListAnggotaOperatorController;
import com.eby.operator.book.edit.BukuEditOperatorController;
import com.eby.operator.book.view.ListBukuOperatorController;

/**
 *
 * @author eby
 */
public class ControllerHelper {

    //Both Controller Helper
    public static BukuAddController getBukuAddController(ScreensController screensController) {
        return (BukuAddController) screensController.getControllerScreen(Main.bukuAddId);
    }

    //Admin Controller Helper
    public static RegisterForAdminController getRegAdminController(ScreensController screensController) {
        return (RegisterForAdminController) screensController.getControllerScreen(Main.RegAdminId);
    }

    public static EditAdminController getEditAdminController(ScreensController screensController) {
        return (EditAdminController) screensController.getControllerScreen(Main.editAdminId);
    }

    public static ListAdminController getListAdminController(ScreensController screensController) {
        return (ListAdminController) screensController.getControllerScreen(Main.listAdminID);
    }

    public static NewMainMenuAdminController getMainMenuAdmin(ScreensController screensController) {
        return (NewMainMenuAdminController) screensController.getControllerScreen(Main.MenuUtamaAdminID);
    }

    public static ListBukuController getListBukuController(ScreensController screensController) {
        return (ListBukuController) screensController.getControllerScreen(Main.listBukuAdminID);
    }

    public static BukuEditAdminController getBukuEditAdmin(ScreensController screensController) {
        return (BukuEditAdminController) screensController.getControllerScreen(Main.bukuEditAdminID);
    }

    //Operator Controller Helper
    public static BukuEditOperatorController getBukuEditOperator(ScreensController screensController) {
        return (BukuEditOperatorController) screensController.getControllerScreen(Main.bukuEditOperatorID);
    }

    public static ListBukuOperatorController getListBukuOpCon(ScreensController screensController) {
        return (ListBukuOperatorController) screensController.getControllerScreen(Main.listBukuOperatorID);
    }

    public static ListAnggotaAdminController getListAnggotaController(ScreensController screensController) {
        return (ListAnggotaAdminController) screensController.getControllerScreen(Main.listAnggotaAdminID);
    }

    public static AnggotaOpEditController getEditAnggotaOpCon(ScreensController screensController) {
        return (AnggotaOpEditController) screensController.getControllerScreen(Main.anggotaEditOpID);
    }

    public static ListAnggotaOperatorController getListAnggotaOp(ScreensController screenController) {
        return (ListAnggotaOperatorController) screenController.getControllerScreen(Main.listAnggotaOpID);
    }

    public static LoginController getLoginController(ScreensController screensController) {
        return (LoginController) screensController.getControllerScreen(Main.loginID);
    }

}
