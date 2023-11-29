package UI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import Utils.ConnectDatabase;

public class PanelMemberGroup extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StaffMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StaffMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StaffMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StaffMain.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					int maNhom = 0;
					PanelMemberGroup frame = new PanelMemberGroup(maNhom);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PanelMemberGroup(int maNhom) {
		addWindowListener(new WindowAdapter() {
    		@Override
    		public void windowClosing(WindowEvent e) {
    			dispose();
    		}
    	});
		Utils.ConnectDatabase con = new ConnectDatabase();
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		setUndecorated(true);
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setLocationRelativeTo(contentPane);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbTitle = new JLabel("Khách Hàng");
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setForeground(new Color(85, 140, 255));
		lbTitle.setFont(new Font("Segoe UI", Font.BOLD, 28));
		lbTitle.setBounds(32, 0, 332, 44);
		getContentPane().add(lbTitle);
		
		JLabel lbHoVaTen = new JLabel("Họ Và Tên");
		lbHoVaTen.setFont(new Font("Arial", Font.PLAIN, 16));
		lbHoVaTen.setBounds(10, 55, 92, 25);
		getContentPane().add(lbHoVaTen);
		
		JTextField txtHoVaTen = new JTextField();
		txtHoVaTen.setColumns(10);
		txtHoVaTen.setBounds(10, 91, 156, 20);
		getContentPane().add(txtHoVaTen);
		
		JLabel lbMoiQuanHe = new JLabel("Mối Quan Hệ");
		lbMoiQuanHe.setFont(new Font("Arial", Font.PLAIN, 16));
		lbMoiQuanHe.setBounds(10, 123, 126, 25);
		getContentPane().add(lbMoiQuanHe);
		
		JTextField txtMoiQuanHe = new JTextField();
		txtMoiQuanHe.setColumns(10);
		txtMoiQuanHe.setBounds(10, 160, 380, 20);
		getContentPane().add(txtMoiQuanHe);
		
		JLabel lbCCCD = new JLabel("Căn Cước Công Dân");
		lbCCCD.setFont(new Font("Arial", Font.PLAIN, 16));
		lbCCCD.setBounds(205, 55, 159, 25);
		getContentPane().add(lbCCCD);
		
		JTextField txtCCCD = new JTextField();
		txtCCCD.setColumns(10);
		txtCCCD.setBounds(208, 91, 182, 20);
		getContentPane().add(txtCCCD);
		
		JButton btThem = new JButton("Thêm");
		btThem.setFont(new Font("Arial", Font.BOLD, 16));
		btThem.setBounds(196, 225, 92, 31);
		getContentPane().add(btThem);
		
		JButton btThoat = new JButton("Thoát");
		btThoat.setFont(new Font("Arial", Font.BOLD, 16));
		btThoat.setBounds(298, 225, 92, 31);
		getContentPane().add(btThoat);
		
		btThoat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		
		btThem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				 String hoTen = txtHoVaTen.getText();
		            String moiQuanHe = txtMoiQuanHe.getText();
		            String cccd = txtCCCD.getText();
				
		            if(Utils.Validate.isTxtEmpty(hoTen)) {
		            	Utils.MsgBox.alert(null, "Họ tên không được để trống");
		            }else if(Utils.Validate.isTxtEmpty(cccd)) {
		            	Utils.MsgBox.alert(null, "Căn cước công dân không được để trống");
		            }else {
		            	
		            
		            
		            try {
						Connection conn = con.getConnection();
		            	String query = "INSERT INTO ThanhVienNhom (CCCD, hoTen, moiQuanHe, maNhom) VALUES (?, ?, ?, ?)";
		            	// Chuẩn bị câu lệnh
		            	PreparedStatement statement = conn.prepareStatement(query);
		            	statement.setString(1, cccd);
		            	statement.setString(2, hoTen);
		            	statement.setString(3, moiQuanHe);
		            	statement.setInt(4, maNhom);
		            	
		            	// Thực hiện chèn dữ liệu
		            	int rowsInserted = statement.executeUpdate();
		            	
		            	if (rowsInserted > 0) {
		                    Utils.MsgBox.alert(null, "Thêm thành công");
		                    dispose();
		                }
					} catch (Exception e2) {
						// TODO: handle exception
					}
		           }

			}
		});
	}

}
