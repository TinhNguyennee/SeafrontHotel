/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 *
 * @author MY LINH
 */
public class MsgBox {

    public static void alert(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message,
                "Hotel Management", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean comfirm(Component parent, String message) {
        int result = JOptionPane.showConfirmDialog(parent, message,
                "Hotel Management",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }

    public static String prompt(Component parent, String message) {
        return JOptionPane.showInputDialog(parent, message,
                "Hotel Management", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void warning(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message,
                "Hotel Management", JOptionPane.WARNING_MESSAGE);
    }

    public static void error(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message,
                "HotelMangement", JOptionPane.ERROR_MESSAGE);
    }
}
