/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import Entity.TaiKhoan;

/**
 *
 * @author DELL
 */
public class Auth {

    public static TaiKhoan user = null;

    public static boolean isLogin() {
        return Auth.user != null;
    }

    public static void clear() {
        Auth.user = null;
    }

    public static boolean isManager() {
        if (Auth.isLogin()) {
            if (Auth.user.getMaChucVu() == 1) {
                return true;
            }
        }
        return false;
    }
}
