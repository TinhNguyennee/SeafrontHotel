package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;

public class PanelCustomerChild extends JFrame {

	private JPanel contentPane;
	private JTextField txtHoVaTen;
	private JTextField txtSDT;
	private JTextField txtEmail;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PanelCustomerChild frame = new PanelCustomerChild();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			    try {
			    	  UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			    	} catch (Exception e) {
			    	  // handle exception
			    	}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PanelCustomerChild() {
    	addWindowListener(new WindowAdapter() {
    		@Override
    		public void windowClosing(WindowEvent e) {
    			dispose();
    		}
    	});
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setUndecorated(true);
		setLocationRelativeTo(contentPane);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbTitle = new JLabel("Khách Hàng");
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setForeground(new Color(85, 140, 255));
		lbTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
		lbTitle.setBounds(32, 0, 332, 44);
		contentPane.add(lbTitle);
		
		JLabel lbHoVaTen = new JLabel("Họ Và Tên");
		lbHoVaTen.setFont(new Font("Arial", Font.PLAIN, 16));
		lbHoVaTen.setBounds(10, 55, 92, 25);
		contentPane.add(lbHoVaTen);
		
		txtHoVaTen = new JTextField();
		txtHoVaTen.setColumns(10);
		txtHoVaTen.setBounds(10, 91, 156, 20);
		contentPane.add(txtHoVaTen);
		
		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(208, 91, 182, 20);
		contentPane.add(txtSDT);
		
		JLabel lbSDT = new JLabel("Số Điện Thoại");
		lbSDT.setFont(new Font("Arial", Font.PLAIN, 16));
		lbSDT.setBounds(205, 55, 112, 25);
		contentPane.add(lbSDT);
		
		JLabel lbEmail = new JLabel("Email");
		lbEmail.setFont(new Font("Arial", Font.PLAIN, 16));
		lbEmail.setBounds(10, 123, 92, 25);
		contentPane.add(lbEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(10, 160, 156, 20);
		contentPane.add(txtEmail);
		
		JLabel lbLoaiKhach = new JLabel("Loại Khách");
		lbLoaiKhach.setFont(new Font("Arial", Font.PLAIN, 16));
		lbLoaiKhach.setBounds(208, 123, 112, 25);
		contentPane.add(lbLoaiKhach);
		
		JComboBox cbbLoaiKhach = new JComboBox();
		cbbLoaiKhach.setBounds(208, 159, 182, 22);
		contentPane.add(cbbLoaiKhach);
		
		JButton btThemKhach = new JButton("Thêm");
		btThemKhach.setFont(new Font("Arial", Font.BOLD, 16));
		btThemKhach.setBounds(196, 225, 92, 31);
		contentPane.add(btThemKhach);
		
		JButton btThoat = new JButton("Thoát");
		btThoat.setFont(new Font("Arial", Font.BOLD, 16));
		btThoat.setBounds(298, 225, 92, 31);
		contentPane.add(btThoat);
		
		btThoat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		
	}
}
