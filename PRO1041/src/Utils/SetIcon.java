/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import java.awt.Component;
import java.awt.Image;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author DELL
 */
public class SetIcon {

    public static void icon(Component com, String file, int width, int height) {
        try {
            Image image = ImageIO.read(new File(file));
            Image resizedImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            if (com instanceof JLabel) {
                JLabel label = (JLabel) com;
                label.setIcon(new ImageIcon(resizedImage));
            } else if (com instanceof JButton) {
                JButton button = (JButton) com;
                button.setIcon(new ImageIcon(resizedImage));
            }
        } catch (Exception e) {
        }
    }
}
