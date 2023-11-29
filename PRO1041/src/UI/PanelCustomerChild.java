package UI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import Utils.ConnectDatabase;

public class PanelCustomerChild extends JFrame {

	private JPanel contentPane;
	private JTextField txtHoVaTen;
	private JTextField txtSDT;
	private JTextField txtEmail;

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
					PanelCustomerChild frame = new PanelCustomerChild();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
//			    try {
//			    	  UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
//			    	} catch (Exception e) {
//			    	  // handle exception
//			    	}
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
    	
		Utils.ConnectDatabase con = new ConnectDatabase();
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
		
	
		
		
	Connection conn = con.getConnection();
	String query = "SELECT chitiet FROM LoaiKhachHang";
	try (Statement stmt = conn.createStatement();
	     ResultSet rs = stmt.executeQuery(query)) {
	    while (rs.next()) {
	        String chitiet = rs.getString("chitiet");
	        cbbLoaiKhach.addItem(chitiet);
	    }
	} catch (SQLException ex) {
	    ex.printStackTrace();
	}
	
	
	
	btThemKhach.addActionListener(new ActionListener() {
	    @Override
	    public void actionPerformed(ActionEvent e) {
	        String hoVaTen = txtHoVaTen.getText().trim();
	        String sdt = txtSDT.getText().trim();
	        String email = txtEmail.getText().trim();
	        String loaiKhach = cbbLoaiKhach.getSelectedItem().toString();

	        Connection conn = con.getConnection();

	        if((Utils.Validate.isTxtEmpty(hoVaTen))||((Utils.Validate.isTxtEmpty(sdt))&&(Utils.Validate.isTxtEmpty(email)))) {
	        	 JOptionPane.showMessageDialog(contentPane, "Vui lòng điền đầy đủ thông tin.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        }else if(!Utils.Validate.isValidEmail(email)&&!Utils.Validate.isTxtEmpty(email)){
	        	JOptionPane.showMessageDialog(contentPane, "Vui lòng điền đúng định dạng abc@gmail.com", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        }else if(!Utils.Validate.isValidPhoneNumber(sdt)&&!Utils.Validate.isTxtEmpty(sdt)) {
	        	JOptionPane.showMessageDialog(contentPane, "Vui lòng nhập số điện thoại đúng định dạng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
	        }else if (loaiKhach.equalsIgnoreCase("Khách hàng cá nhân")) {
	        	
	        	try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO KhachHang (tenKhachHang, soDienThoai, email, maLoai) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
	        	    stmt.setString(1, hoVaTen);
	        	    stmt.setString(2, sdt);
	        	    stmt.setString(3, email);
	        	    stmt.setInt(4, 1);
	        	    int rowsAffected = stmt.executeUpdate();
	        	    if (rowsAffected > 0) {
	        	        ResultSet generatedKeys = stmt.getGeneratedKeys();
	        	        generatedKeys.next();
	        	            int maKhachHang = generatedKeys.getInt(1);
	        	            
	        	            // Thực hiện các thao tác khác với giá trị maKhachHang ở đây
	        	            // Ví dụ: thêm dữ liệu vào bảng NhomKhachHang
	        	            


	        	                java.time.LocalDate ngayHienTai = java.time.LocalDate.now();
	        	                try (PreparedStatement stmt3 = conn.prepareStatement("INSERT INTO DatPhong (ngayDatPhong, ngayBatDau, ngayKetThuc, tienDatCoc, maKhachHang, maPhong, maNhanVien, trangThai) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
	        	                    stmt3.setObject(1, ngayHienTai);
	        	                    stmt3.setObject(2, PanelBooking.getNgayO());
	        	                    stmt3.setObject(3, PanelBooking.getNgayTra());
	        	                    stmt3.setInt(4, 0);
	        	                    stmt3.setInt(5, maKhachHang);
	        	                    stmt3.setInt(6, PanelBooking.getPhong());
	        	                    stmt3.setInt(7, PanelBooking.getNguoiLap());
	        	                    stmt3.setNString(8, "Chưa thanh toán");
	        	                    stmt3.executeUpdate();
	        	                    Utils.MsgBox.alert(contentPane, "Thêm thành công");
	        	                    
	        	                 // Chuẩn bị câu truy vấn PreparedStatement
	        	                    String query = "UPDATE Phong SET trangThaiPhong = ? WHERE maPhong = ?";
	        	                    PreparedStatement pstmt = conn.prepareStatement(query);
	        	                    pstmt.setNString(1, "Đã đặt");
	        	                    pstmt.setInt(2, PanelBooking.getPhong());
	        	                    pstmt.executeUpdate();
	        	                    
	        	                    dispose();
	        	                } catch (SQLException ex) {
	        	                    ex.printStackTrace();
	        	                }
	        	            
	        	                
	        	        
	        	    } else {
	        	        Utils.MsgBox.alert(contentPane, "Thêm thất bại");
	        	    }
	        	} catch (SQLException ex) {
	        	    ex.printStackTrace();
	        	}
	            
	        }else if(loaiKhach.equalsIgnoreCase("Khách hàng theo nhóm")) {
	        	
	        	try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO KhachHang (tenKhachHang, soDienThoai, email, maLoai) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
	        	    stmt.setString(1, hoVaTen);
	        	    stmt.setString(2, sdt);
	        	    stmt.setString(3, email);
	        	    stmt.setInt(4, 2);
	        	    int rowsAffected = stmt.executeUpdate();
	        	    if (rowsAffected > 0) {
	        	        ResultSet generatedKeys = stmt.getGeneratedKeys();
	        	        generatedKeys.next();
	        	            int maKhachHang = generatedKeys.getInt(1);
	        	            
	        	            // Thực hiện các thao tác khác với giá trị maKhachHang ở đây
	        	            // Ví dụ: thêm dữ liệu vào bảng NhomKhachHang
	        	            
	        	            try (PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO NhomKhachHang (maKhachHang) VALUES (?)")) {
	        	                stmt2.setInt(1, maKhachHang);
	        	                stmt2.executeUpdate();

	        	                java.time.LocalDate ngayHienTai = java.time.LocalDate.now();
	        	                try (PreparedStatement stmt3 = conn.prepareStatement("INSERT INTO DatPhong (ngayDatPhong, ngayBatDau, ngayKetThuc, tienDatCoc, maKhachHang, maPhong, maNhanVien, trangThai) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
	        	                    stmt3.setObject(1, ngayHienTai);
	        	                    stmt3.setObject(2, PanelBooking.getNgayO());
	        	                    stmt3.setObject(3, PanelBooking.getNgayTra());
	        	                    stmt3.setInt(4, 0);
	        	                    stmt3.setInt(5, maKhachHang);
	        	                    stmt3.setInt(6, PanelBooking.getPhong());
	        	                    stmt3.setInt(7, PanelBooking.getNguoiLap());
	        	                    stmt3.setNString(8, "Chưa thanh toán");
	        	                    stmt3.executeUpdate();
	        	                    Utils.MsgBox.alert(contentPane, "Thêm thành công");
	        	                    
		        	                 // Chuẩn bị câu truy vấn PreparedStatement
	        	                    String query = "UPDATE Phong SET trangThaiPhong = ? WHERE maPhong = ?";
	        	                    PreparedStatement pstmt = conn.prepareStatement(query);
	        	                    pstmt.setNString(1, "Đã đặt");
	        	                    pstmt.setInt(2, PanelBooking.getPhong());
	        	                    pstmt.executeUpdate();
	        	                    
	        	                    dispose();
	        	                } catch (SQLException ex) {
	        	                    ex.printStackTrace();
	        	                }
	        	            } catch (SQLException ex) {
	        	                ex.printStackTrace();
	        	            }
	        	        
	        	        dispose();
	        	    } else {
	        	        Utils.MsgBox.alert(contentPane, "Thêm thất bại");
	        	    }
	        	} catch (SQLException ex) {
	        	    ex.printStackTrace();
	        	}
	        	
	        }
	        
	        
	    }
	});
	
	
	
	
	
	
	
	
	}	
}
