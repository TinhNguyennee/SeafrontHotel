/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Component;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author DELL
 */
public class Button extends JButton {

    public Button() {
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setContentAreaFilled(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR) {
        });
        setText("Loggg");
        setFont(new Font("Arial", Font.PLAIN, 12));
    }
    
    
    @Override
    protected void paintComponent(Graphics grphs) {
       
        Graphics2D g2 = (Graphics2D) grphs.create(); // Ép kiểu Graphics thành Graphics2D
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        int width = getWidth() - 1;
        int height = getHeight() - 1;
        g2.fillRoundRect(0, 0, width, height, height, height);
        g2.dispose();
        super.paintComponent(grphs); // Gọi phương thức paintComponent của lớp cha trước khi vẽ
    }

}
