package UI;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;

public class Card2 extends JPanel {

	/**
	 * Create the panel.
	 */
	public Card2() {
		setBounds(0, 0, 200, 125);
		setLayout(null);
		
		JLabel lbIconPhongDangThue = new JLabel("");
		lbIconPhongDangThue.setIcon(new ImageIcon(Card2.class.getResource("/Icon/bed.png")));
		lbIconPhongDangThue.setBounds(15, 15, 46, 36);
		add(lbIconPhongDangThue);
		
		JLabel lbPhongDangThue = new JLabel("Số phòng đang thuê");
		lbPhongDangThue.setForeground(Color.WHITE);
		lbPhongDangThue.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbPhongDangThue.setBounds(15, 62, 180, 17);
		add(lbPhongDangThue);
		
		JLabel lbSoPhongDangThue = new JLabel("0");
		lbSoPhongDangThue.setForeground(Color.WHITE);
		lbSoPhongDangThue.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbSoPhongDangThue.setBounds(15, 90, 62, 24);
		add(lbSoPhongDangThue);

	}
	 @Override
	    protected void paintComponent(Graphics g) {
	        Graphics2D g2d = (Graphics2D) g.create();

	        // Tạo đối tượng GradientPaint
	        GradientPaint gradient = new GradientPaint(
	                0, 0, Color.decode("#00c6ff"), getWidth(), getHeight(), Color.decode("#0072ff"));

	        // Áp dụng màu loang vào nền của panel
	        g2d.setPaint(gradient);
	        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
//	        g2d.fillRect(0, 0, getWidth(), getHeight());
	        g2d.setColor(new Color(255,255,255,50));
	        g2d.fillOval(getWidth()-(getHeight()/2),10,getHeight(),getHeight());
	        g2d.fillOval(getWidth()-(getHeight()/2)-20,getHeight()/2+20,getHeight(),getHeight());
	        g2d.dispose();
	    }
}
