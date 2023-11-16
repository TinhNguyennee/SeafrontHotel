package UI;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;

public class Card extends JPanel {

	/**
	 * Create the panel.
	 */
	public Card() {
		setBounds(0, 0, 200, 125);
		setLayout(null);
		
		JLabel lbIconPhongTrong = new JLabel("");
		lbIconPhongTrong.setIcon(new ImageIcon(Card.class.getResource("/Icon/bed2.png")));
		lbIconPhongTrong.setBounds(15, 15, 46, 36);
		add(lbIconPhongTrong);
		
		JLabel lbPhongTrong = new JLabel("Số phòng trống");
		lbPhongTrong.setForeground(Color.WHITE);
		lbPhongTrong.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbPhongTrong.setBounds(15, 62, 132, 17);
		add(lbPhongTrong);
		
		JLabel lbSoPhongTrong = new JLabel("0");
		lbSoPhongTrong.setForeground(Color.WHITE);
		lbSoPhongTrong.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbSoPhongTrong.setBounds(15, 90, 62, 24);
		add(lbSoPhongTrong);
	}
	 @Override
	    protected void paintComponent(Graphics g) {
	        Graphics2D g2d = (Graphics2D) g.create();

	        // Tạo đối tượng GradientPaint
	        GradientPaint gradient = new GradientPaint(
	                0, 0, Color.decode("#6A82FB"), getWidth(), getHeight(), Color.decode("#FC5C7D"));

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
