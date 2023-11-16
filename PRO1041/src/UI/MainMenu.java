package UI;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.SwingConstants;

public class MainMenu extends JPanel {
	private Main main;
	/**
	 * Create the panel.
	 */
	public MainMenu(Main main) {
//		this.main = main;
		setOpaque(false);
		setBounds(0, 0, 235, 550);
		setLayout(null);
		
		JLabel lbLogo = new JLabel("");
		lbLogo.setIcon(new ImageIcon(MainMenu.class.getResource("/Icon/logoresize.png")));
		lbLogo.setBounds(10, 10, 205, 140);
		add(lbLogo);
		
		JLabel lbbtTrangChu = new JLabel(" Trang Chủ");
		lbbtTrangChu.setIcon(new ImageIcon(MainMenu.class.getResource("/Icon/home.png")));
		lbbtTrangChu.setHorizontalAlignment(SwingConstants.LEFT);
		lbbtTrangChu.setForeground(Color.WHITE);
		lbbtTrangChu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbbtTrangChu.setBounds(10, 187, 215, 38);
		lbbtTrangChu.setOpaque(true);
        lbbtTrangChu.setBackground(new Color(88,206,249));
		add(lbbtTrangChu);



		
		JLabel lbbtPhong = new JLabel(" Đặt Phòng");
		lbbtPhong.setIcon(new ImageIcon(MainMenu.class.getResource("/Icon/door.png")));
		lbbtPhong.setHorizontalAlignment(SwingConstants.LEFT);
		lbbtPhong.setForeground(Color.WHITE);
		lbbtPhong.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbbtPhong.setBounds(10, 236, 215, 38);
		add(lbbtPhong);

		
		
		JLabel lbbtDichVu = new JLabel(" Dịch Vụ");
		lbbtDichVu.setIcon(new ImageIcon(MainMenu.class.getResource("/Icon/service.png")));
		lbbtDichVu.setHorizontalAlignment(SwingConstants.LEFT);
		lbbtDichVu.setForeground(Color.WHITE);
		lbbtDichVu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbbtDichVu.setBounds(10, 285, 215, 38);
		add(lbbtDichVu);
		
		JLabel lbbtKhachHang = new JLabel(" Khách Hàng");
		lbbtKhachHang.setIcon(new ImageIcon(MainMenu.class.getResource("/Icon/customer.png")));
		lbbtKhachHang.setHorizontalAlignment(SwingConstants.LEFT);
		lbbtKhachHang.setForeground(Color.WHITE);
		lbbtKhachHang.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbbtKhachHang.setBounds(10, 334, 215, 38);
		add(lbbtKhachHang);
		
		JLabel lbbtDoiMatKhau = new JLabel(" Đổi Mật Khẩu");
		lbbtDoiMatKhau.setIcon(new ImageIcon(MainMenu.class.getResource("/Icon/recycle.png")));
		lbbtDoiMatKhau.setHorizontalAlignment(SwingConstants.LEFT);
		lbbtDoiMatKhau.setForeground(Color.WHITE);
		lbbtDoiMatKhau.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbbtDoiMatKhau.setBounds(10, 432, 215, 38);
		add(lbbtDoiMatKhau);
		JLabel lbbtDangXuat = new JLabel(" Đăng Xuất");
		lbbtDangXuat.setIcon(new ImageIcon(MainMenu.class.getResource("/Icon/logout.png")));
		lbbtDangXuat.setHorizontalAlignment(SwingConstants.LEFT);
		lbbtDangXuat.setForeground(Color.WHITE);
		lbbtDangXuat.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbbtDangXuat.setBounds(10, 481, 215, 38);
		add(lbbtDangXuat);
		
		JLabel lbbtHoaDon = new JLabel(" Hóa Đơn");
		lbbtHoaDon.setIcon(new ImageIcon(MainMenu.class.getResource("/Icon/bill.png")));
		lbbtHoaDon.setHorizontalAlignment(SwingConstants.LEFT);
		lbbtHoaDon.setForeground(Color.WHITE);
		lbbtHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbbtHoaDon.setBounds(10, 383, 215, 38);
		add(lbbtHoaDon);
        lbbtTrangChu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
        		lbbtTrangChu.setOpaque(true);
                lbbtTrangChu.setBackground(new Color(88,206,249)); // Đổi màu nền thành màu đỏ
                lbbtPhong.setOpaque(false);
                lbbtPhong.setBackground(null);
                lbbtDichVu.setOpaque(false);
                lbbtDichVu.setBackground(null);
                lbbtKhachHang.setOpaque(false);
                lbbtKhachHang.setBackground(null);
        		lbbtHoaDon.setOpaque(false);
        		lbbtHoaDon.setBackground(null);
                lbbtDoiMatKhau.setOpaque(false);
                lbbtDoiMatKhau.setBackground(null);      
                
                main.getPanelmain().setVisible(true);
                main.getPanelbooking().setVisible(false);
                main.getPanelcustomer().setVisible(false);
                main.getPanelbill().setVisible(false);
                main.getPanelservice().setVisible(false);
            }
        });
        lbbtPhong.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		lbbtTrangChu.setOpaque(false);
        		lbbtTrangChu.setBackground(null); // Đổi màu nền thành màu đỏ
        		lbbtPhong.setOpaque(true);
        		lbbtPhong.setBackground(new Color(88,206,249));
        		lbbtDichVu.setOpaque(false);
        		lbbtDichVu.setBackground(null);
        		lbbtKhachHang.setOpaque(false);
        		lbbtKhachHang.setBackground(null);
        		lbbtHoaDon.setOpaque(false);
        		lbbtHoaDon.setBackground(null);
        		lbbtDoiMatKhau.setOpaque(false);
        		lbbtDoiMatKhau.setBackground(null);
        		
                main.getPanelmain().setVisible(false);
                main.getPanelbooking().setVisible(true);
                main.getPanelcustomer().setVisible(false);
                main.getPanelbill().setVisible(false);
                main.getPanelservice().setVisible(false);
        	}
        });
        lbbtDichVu.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		lbbtTrangChu.setOpaque(false);
        		lbbtTrangChu.setBackground(null); // Đổi màu nền thành màu đỏ
        		lbbtPhong.setOpaque(false);
        		lbbtPhong.setBackground(null);
        		lbbtDichVu.setOpaque(true);
        		lbbtDichVu.setBackground(new Color(88,206,249));
        		lbbtKhachHang.setOpaque(false);
        		lbbtKhachHang.setBackground(null);
        		lbbtHoaDon.setOpaque(false);
        		lbbtHoaDon.setBackground(null);
        		lbbtDoiMatKhau.setOpaque(false);
        		lbbtDoiMatKhau.setBackground(null);
        		
                main.getPanelmain().setVisible(false);
                main.getPanelbooking().setVisible(false);
                main.getPanelcustomer().setVisible(false);
                main.getPanelbill().setVisible(false);
                main.getPanelservice().setVisible(true);
        	}
        });
        lbbtKhachHang.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		lbbtTrangChu.setOpaque(false);
        		lbbtTrangChu.setBackground(null); // Đổi màu nền thành màu đỏ
        		lbbtPhong.setOpaque(false);
        		lbbtPhong.setBackground(null);
        		lbbtDichVu.setOpaque(false);
        		lbbtDichVu.setBackground(null);
        		lbbtKhachHang.setOpaque(true);
        		lbbtKhachHang.setBackground(new Color(88,206,249));
        		lbbtHoaDon.setOpaque(false);
        		lbbtHoaDon.setBackground(null);
        		lbbtDoiMatKhau.setOpaque(false);
        		lbbtDoiMatKhau.setBackground(null);	
        		
                main.getPanelmain().setVisible(false);
                main.getPanelbooking().setVisible(false);
                main.getPanelcustomer().setVisible(true);
                main.getPanelbill().setVisible(false);
                main.getPanelservice().setVisible(false);
        	}
        });
        lbbtHoaDon.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		lbbtTrangChu.setOpaque(false);
        		lbbtTrangChu.setBackground(null); // Đổi màu nền thành màu đỏ
        		lbbtPhong.setOpaque(false);
        		lbbtPhong.setBackground(null);
        		lbbtDichVu.setOpaque(false);
        		lbbtDichVu.setBackground(null);
        		lbbtKhachHang.setOpaque(false);
        		lbbtKhachHang.setBackground(null);
        		lbbtHoaDon.setOpaque(true);
        		lbbtHoaDon.setBackground(new Color(88,206,249));
        		lbbtDoiMatKhau.setOpaque(false);
        		lbbtDoiMatKhau.setBackground(null);
        		
                main.getPanelmain().setVisible(false);
                main.getPanelbooking().setVisible(false);
                main.getPanelcustomer().setVisible(false);
                main.getPanelbill().setVisible(true);
                main.getPanelservice().setVisible(false);
        	}
        });
        lbbtDoiMatKhau.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		lbbtTrangChu.setOpaque(false);
        		lbbtTrangChu.setBackground(null); // Đổi màu nền thành màu đỏ
        		lbbtPhong.setOpaque(false);
        		lbbtPhong.setBackground(null);
        		lbbtDichVu.setOpaque(false);
        		lbbtDichVu.setBackground(null);
        		lbbtKhachHang.setOpaque(false);
        		lbbtKhachHang.setBackground(null);
        		lbbtHoaDon.setOpaque(false);
        		lbbtHoaDon.setBackground(null);
        		lbbtDoiMatKhau.setOpaque(true);
        		lbbtDoiMatKhau.setBackground(new Color(88,206,249));
        		
        		main.getChangepassword().setVisible(true);
        	}
        });
		
	}
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Tạo đối tượng GradientPaint
        GradientPaint gradient = new GradientPaint(
                0, 0, Color.decode("#36D1DC"), getWidth(), getHeight(), Color.decode("#5B86E5"));

        // Áp dụng màu loang vào nền của panel
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.dispose();
    }
}
