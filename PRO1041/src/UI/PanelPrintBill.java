package UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Utils.ConnectDatabase;

public class PanelPrintBill extends JFrame {

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
            java.util.logging.Logger.getLogger(PanelPrintBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PanelPrintBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PanelPrintBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PanelPrintBill.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					int maDatPhong = 0;
					PanelPrintBill frame = new PanelPrintBill(maDatPhong);
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
	public PanelPrintBill(int maDatPhong) {
    	addWindowListener(new WindowAdapter() {
    		@Override
    		public void windowClosing(WindowEvent e) {
    			dispose();
    		}
    	});
		Utils.ConnectDatabase con = new ConnectDatabase();
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 625);
		setLocationRelativeTo(contentPane);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbTitle = new JLabel("Hóa Đơn");
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setForeground(new Color(85, 140, 255));
		lbTitle.setFont(new Font("Segoe UI", Font.BOLD, 36));
		lbTitle.setBounds(0, 0, 187, 63);
		contentPane.add(lbTitle);
		
		JLabel lbName = new JLabel("Seafront Hotel");
		lbName.setForeground(new Color(255, 128, 128));
		lbName.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lbName.setBounds(25, 74, 134, 27);
		contentPane.add(lbName);
		
		JLabel lbContent1 = new JLabel("Người ta gọi đây là view triệu đô");
		lbContent1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbContent1.setBounds(35, 100, 247, 27);
		contentPane.add(lbContent1);
		
		JLabel lbContent2 = new JLabel("Vậy thì ai mới là người dư dã");
		lbContent2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbContent2.setBounds(35, 128, 214, 27);
		contentPane.add(lbContent2);
		
		JLabel lbMHD = new JLabel("Mã:");
		lbMHD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbMHD.setBounds(295, 36, 145, 27);
		contentPane.add(lbMHD);
		
		JLabel lbNgay = new JLabel("Ngày:");
		lbNgay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbNgay.setBounds(295, 74, 145, 27);
		contentPane.add(lbNgay);
		
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mục","Số Lượng/Số Ngày","Đơn Giá","Thành Tiền"
			}
		));
		
		// Tùy chỉnh giao diện
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setShowHorizontalLines(true);
        table.setRowHeight(30);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
        	@Override
        	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
        			boolean hasFocus, int row, int column) {
        		JLabel header = new JLabel(value + "");
        			header.setOpaque(true);
        			header.setBackground(new Color(240,240,240));
        			header.setFont(new Font("sansserif",1,12));
        			header.setForeground(new Color(102,102,102));
        			header.setBorder(new EmptyBorder(10,5,10,5));
        			header.setHorizontalAlignment(JLabel.CENTER);
        		return header;
        	}
        });
     // Tạo Renderer tùy chỉnh
        DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
        renderer.setBackground(new Color(240,240,240)); // Đặt màu nền cho Renderer
		renderer.setHorizontalAlignment(JLabel.CENTER);

        // Áp dụng Renderer cho tất cả các ô trong bảng
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
		
		table.setBounds(10, 125, 645, 415);
		JScrollPane jsp = new JScrollPane(table);
		jsp.setSize(430, 242);
        jsp.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		jsp.setLocation(10, 166);
		contentPane.add(jsp);
		
		JLabel lbPay = new JLabel("Thông tin thanh toán");
		lbPay.setForeground(new Color(255, 128, 128));
		lbPay.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lbPay.setBounds(10, 450, 187, 27);
		contentPane.add(lbPay);
		
		JLabel lbContent3 = new JLabel("Ngân Hàng MB");
		lbContent3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbContent3.setBounds(25, 478, 110, 27);
		contentPane.add(lbContent3);
		
		JLabel lbContent4 = new JLabel("Tên tài khoản: NGUYEN THANH TINH");
		lbContent4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbContent4.setBounds(25, 505, 234, 27);
		contentPane.add(lbContent4);
		
		JLabel lblSTiKhon = new JLabel("Số tài khoản: 0550767799967");
		lblSTiKhon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSTiKhon.setBounds(25, 533, 197, 27);
		contentPane.add(lblSTiKhon);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PanelPrintBill.class.getResource("/Icon/QRResize.jpg")));
		lblNewLabel.setBounds(295, 458, 125, 125);
		contentPane.add(lblNewLabel);
		
		JButton btThoat = new JButton("Thoát");
		btThoat.setBounds(245, 591, 89, 23);
		contentPane.add(btThoat);
		
		JButton btIn = new JButton("In");
		btIn.setBounds(351, 591, 89, 23);
		contentPane.add(btIn);
		
		JLabel lbTongTien = new JLabel("Tổng Tiền:");
		lbTongTien.setForeground(new Color(255, 128, 128));
		lbTongTien.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lbTongTien.setBounds(212, 419, 95, 27);
		contentPane.add(lbTongTien);
		
		JLabel lbSoTongTien = new JLabel("0"+" Đ");
		lbSoTongTien.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbSoTongTien.setBounds(317, 420, 125, 27);
		contentPane.add(lbSoTongTien);
		
		
		
		
		
		//lấy thông tin từ mã đặt phòng
		int maNhanVien = 0;
		int maPhong = 0;
		String trangThai = "";
		Date ngayBatDau = null;
		Date ngayKetThuc = null;
		 try {
	            // Kết nối tới CSDL
				Connection conn = con.getConnection();

	            // Truy vấn thông tin từ bảng DatPhong
	            String query = "SELECT ngayBatDau, ngayKetThuc, maKhachHang, maPhong, maNhanVien, trangThai FROM DatPhong WHERE maDatPhong = ?";
	            PreparedStatement statement = conn.prepareStatement(query);
	            statement.setInt(1, maDatPhong);
	            ResultSet result = statement.executeQuery();

	            if (result.next()) {
	                // In ra thông tin từ kết quả truy vấn
	                ngayBatDau = result.getDate("ngayBatDau");
	                ngayKetThuc = result.getDate("ngayKetThuc");
	                int maKhachHang = result.getInt("maKhachHang");
	                maPhong = result.getInt("maPhong");
	                maNhanVien = result.getInt("maNhanVien");
	                trangThai = result.getString("trangThai");
	            } else {
	                System.out.println("Không tìm thấy mã đặt phòng.");
	            }

	            // Đóng kết nối CSDL
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		


		 
//		 ChronoUnit.DAYS.between(NgayHienTaiLocalDate, ngayBatDauLocalDate);
		 
		 //lấy số ngày
		 int soNgay = 0;
		 if(trangThai.equalsIgnoreCase("Chưa thanh toán")) {
			 LocalDate ngayBatDauLocalDate = ngayBatDau.toLocalDate();
			 LocalDate NgayHienTaiLocalDate = new Date(System.currentTimeMillis()).toLocalDate();
			 soNgay = Integer.valueOf((int) ChronoUnit.DAYS.between(ngayBatDauLocalDate, NgayHienTaiLocalDate));

			 if(soNgay==0) {
				 soNgay=1;
			 }
			 

			 
//			 System.out.println(new Date(System.currentTimeMillis()));
//			 System.out.println(new Date(System.currentTimeMillis()).compareTo(ngayBatDau));
//			 System.out.println(ngayBatDau);
			 
		 }else if(trangThai.equalsIgnoreCase("Đã hoàn tất")) {
			 LocalDate ngayBatDauLocalDate = ngayBatDau.toLocalDate();
			 LocalDate NgayKetThucLocalDate = ngayKetThuc.toLocalDate();
			 soNgay = Integer.valueOf((int) ChronoUnit.DAYS.between(ngayBatDauLocalDate, NgayKetThucLocalDate));
			 
			 if(soNgay==0) {
				 soNgay=1;
			 }
		 }
		 
		 
		 
		 
		 //lấy đơn giá phòng
		 double giaPhong=0;
		 try {
	            // Kết nối tới CSDL
			 Connection conn = con.getConnection();

	            // Truy vấn thông tin từ bảng Phong
	            String query = "SELECT giaPhong FROM Phong where maPhong = "+maPhong;
	            Statement statement = conn.createStatement();
	            ResultSet result = statement.executeQuery(query);

	            if (result.next()) {
	                // Lấy thông tin từ kết quả truy vấn

	                giaPhong = result.getDouble("giaPhong");

	            }

	            // Đóng kết nối CSDL
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		 
		 
		 
		 int thanhTien = (int) (giaPhong*soNgay);
		 
		 
		 
		 
		 
		 //fill hóa đơn phòng cho table
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			
			Vector<Object> row = new Vector<>();
			
			row.add("Phòng "+maPhong);
			row.add(soNgay);
			row.add(giaPhong);
			row.add(thanhTien);
			model.addRow(row);
			
		 
		 
			

		 
		 
		 
		 
		//fill hóa đơn dịch vụ cho table
			 try {
		            // Kết nối tới CSDL
				 Connection conn = con.getConnection();

		            // Truy vấn thông tin từ bảng ChiTietDichVu và DichVu
		            String query = "SELECT cdv.maDatPhong, dv.tenDichVu, cdv.soLuong, dv.donGia, (cdv.soLuong * dv.donGia) AS thanhTien " +
		                    "FROM ChiTietDichVu cdv " +
		                    "INNER JOIN DichVu dv ON cdv.maDichVu = dv.maDichVu " +
		                    "WHERE maDatPhong = ?";
		            PreparedStatement statement = conn.prepareStatement(query);
		            statement.setInt(1, maDatPhong);
		            ResultSet result = statement.executeQuery();

		            while (result.next()) {
		                // Lấy thông tin từ kết quả truy vấn
		                String tenDichVu = result.getString("tenDichVu");
		                int soLuong = result.getInt("soLuong");
		                double donGia = result.getDouble("donGia");
		                double thanhTien1 = result.getDouble("thanhTien");
		                
		    			Vector<Object> row1 = new Vector<>();
		    			
		    			row1.add(tenDichVu);
		    			row1.add(soLuong);
		    			row1.add(donGia);
		    			row1.add(thanhTien1);
		    			model.addRow(row1);

		            }

		            // Đóng kết nối CSDL
		            conn.close();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
			
		 
		 
			 
			 
		 
		 
				// Lấy số lượng hàng trong JTable
		        int rowCount = model.getRowCount();
		        double tongTien = 0;

		        // Lấy tất cả giá trị trong cột 1
		        for (int row1 = 0; row1 < rowCount; row1++) {
		            tongTien = tongTien + Double.valueOf(model.getValueAt(row1, 3).toString()) ; 
		        }
				
		        lbSoTongTien.setText(tongTien+" Đ");
		
			 
			 
			 
			 
			 
			 
			 
			 
		
		
		
		
		 //tạo hóa hơn 
		 try {
	            // Kết nối tới CSDL
				Connection conn = con.getConnection();

	            // Kiểm tra xem mã đặt phòng đã có hóa đơn hay chưa
	            String checkQuery = "SELECT COUNT(*) FROM HoaDon WHERE maDatPhong = ?";
	            PreparedStatement checkStatement = conn.prepareStatement(checkQuery);
	            checkStatement.setInt(1, maDatPhong);
	            ResultSet checkResult = checkStatement.executeQuery();
	            checkResult.next();
	            int count = checkResult.getInt(1);

	            if (count == 0) {
	            	String createQuery = "INSERT INTO HoaDon (ngayLapHoaDon, tongTien, maDatPhong, maNhanVien) VALUES (?, ?, ?, ?)";
	            	PreparedStatement createStatement = conn.prepareStatement(createQuery, Statement.RETURN_GENERATED_KEYS);
	            	createStatement.setDate(1, new Date(System.currentTimeMillis()));
	            	createStatement.setDouble(2, tongTien);
	            	createStatement.setInt(3, maDatPhong);
	            	createStatement.setInt(4, maNhanVien);
	            	createStatement.executeUpdate();

	            	ResultSet generatedKeys = createStatement.getGeneratedKeys();
	            	if (generatedKeys.next()) {
	            	    int maHoaDon = generatedKeys.getInt(1);
	            	    lbMHD.setText("Mã: "+maHoaDon);
	            	    lbNgay.setText("Ngày: "+new Date(System.currentTimeMillis()));
	            	    
	            	}
//	                System.out.println("Đã tạo hóa đơn mới.");
	            } else {
//	                System.out.println("Mã đặt phòng đã có hóa đơn.");
	            	
	            	
	            	String updateQuery = "UPDATE HoaDon SET ngayLapHoaDon = ?, tongTien = ?, maNhanVien = ? WHERE maDatPhong = ?";
	            	PreparedStatement updateStatement = conn.prepareStatement(updateQuery);
	            	updateStatement.setDate(1, new Date(System.currentTimeMillis()));
	            	updateStatement.setDouble(2, tongTien);
	            	updateStatement.setInt(3, maNhanVien);
	            	updateStatement.setInt(4, maDatPhong);
	            	updateStatement.executeUpdate();
	            	
	            	
	            	
	            	String selectQuery = "SELECT maHoaDon FROM HoaDon WHERE maDatPhong = ?";
	            	PreparedStatement selectStatement = conn.prepareStatement(selectQuery);
	            	selectStatement.setInt(1, maDatPhong);

	            	ResultSet resultSet = selectStatement.executeQuery();
	            	if (resultSet.next()) {
	            	    int maHoaDon = resultSet.getInt("maHoaDon");
	            	    lbMHD.setText("Mã: "+maHoaDon);
	            	    lbNgay.setText("Ngày: "+new Date(System.currentTimeMillis()));
	            	}
	            	
	            	
	            	
	            }
	            
	            
	            

	            // Đóng kết nối CSDL
	            conn.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
		
		 
		 
		 
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		btThoat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
	}
}
