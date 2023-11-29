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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Utils.ConnectDatabase;

public class PanelPay extends JFrame {

	private JPanel contentPane;
	private int maPhong = 0;

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
			java.util.logging.Logger.getLogger(PanelPay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(PanelPay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(PanelPay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(PanelPay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					int maDatPhong = 0;
					PanelPay frame = new PanelPay(maDatPhong);
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
	public PanelPay(int maDatPhong) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		Utils.ConnectDatabase con = new ConnectDatabase();
		//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 600);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setLocationRelativeTo(contentPane);

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblThanhToan = new JLabel("Thanh Toán");
		lblThanhToan.setHorizontalAlignment(SwingConstants.CENTER);
		lblThanhToan.setForeground(new Color(85, 140, 255));
		lblThanhToan.setFont(new Font("Segoe UI", Font.BOLD, 36));
		lblThanhToan.setBounds(134, 0, 224, 63);
		contentPane.add(lblThanhToan);

		JLabel lbMaDatPhong = new JLabel("Mã đặt phòng: ");
		lbMaDatPhong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbMaDatPhong.setBounds(10, 111, 209, 27);
		contentPane.add(lbMaDatPhong);

		JLabel lbPhong = new JLabel("Phòng: ");
		lbPhong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbPhong.setBounds(10, 149, 209, 27);
		contentPane.add(lbPhong);

		JLabel lbLoaiPhong = new JLabel("Loại phòng: ");
		lbLoaiPhong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbLoaiPhong.setBounds(10, 187, 209, 27);
		contentPane.add(lbLoaiPhong);

		JLabel lbDonGia = new JLabel("Đơn giá: ");
		lbDonGia.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbDonGia.setBounds(10, 223, 209, 27);
		contentPane.add(lbDonGia);

		JLabel lbNgayO = new JLabel("Ngày đến:");
		lbNgayO.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbNgayO.setBounds(229, 111, 209, 27);
		contentPane.add(lbNgayO);

		JLabel lbNgayDi = new JLabel("Ngày đi:");
		lbNgayDi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbNgayDi.setBounds(229, 149, 209, 27);
		contentPane.add(lbNgayDi);

		JLabel lbSoNgay = new JLabel("Số ngày: ");
		lbSoNgay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbSoNgay.setBounds(229, 187, 209, 27);
		contentPane.add(lbSoNgay);

		JLabel lbThanhTien = new JLabel("Thành tiền: ");
		lbThanhTien.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbThanhTien.setBounds(229, 223, 209, 27);
		contentPane.add(lbThanhTien);

		JLabel lbDichVu = new JLabel("Dịch vụ:");
		lbDichVu.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbDichVu.setBounds(120, 280, 209, 27);
		contentPane.add(lbDichVu);

		JLabel lbTongTien = new JLabel("Tổng Tiền:");
		lbTongTien.setForeground(new Color(255, 128, 128));
		lbTongTien.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lbTongTien.setBounds(120, 318, 95, 27);
		contentPane.add(lbTongTien);

		JLabel lbSoTongTien = new JLabel("0 Đ");
		lbSoTongTien.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbSoTongTien.setBounds(225, 319, 125, 27);
		contentPane.add(lbSoTongTien);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 356, 480, 198);
		getContentPane().add(tabbedPane);

		JPanel panelKhachHang = new JPanel();
		tabbedPane.addTab("Khách Hàng", null, panelKhachHang, null);
		panelKhachHang.setLayout(null);

		JTable table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Mã Khách Hàng","Họ Và Tên","Căn Cước Công Dân","Số Điện Thoại","Email","Quốc Tịch"
				}
				));
		table.setBounds(0, 0, 640, 186);

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

		JScrollPane jsp = new JScrollPane(table);
		jsp.setBounds(0, 0, 475, 170);
		panelKhachHang.add(jsp);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Dịch Vụ", null, panel, null);
		panel.setLayout(null);

		JTable table_1 = new JTable();
		table_1.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		table_1.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Mã Dịch Vụ","Tên Dịch Vụ","Số Lượng","Đơn Giá","Thành Tiền"
				}
				));
		table_1.setBounds(0, 0, 640, 186);

		// Tùy chỉnh giao diện
		table_1.setShowGrid(false);
		table_1.setIntercellSpacing(new Dimension(0, 0));
		table_1.setShowHorizontalLines(true);
		table_1.setRowHeight(30);
		table_1.getTableHeader().setReorderingAllowed(false);
		table_1.getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
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
		DefaultTableCellRenderer renderer1 = new DefaultTableCellRenderer();
		renderer1.setBackground(new Color(240,240,240)); // Đặt màu nền cho Renderer
		renderer.setHorizontalAlignment(JLabel.CENTER);

		// Áp dụng Renderer cho tất cả các ô trong bảng
		for (int i = 0; i < table_1.getColumnCount(); i++) {
			table_1.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}

		JScrollPane jsp1 = new JScrollPane(table_1);
		jsp1.setLocation(0, 0);
		jsp1.setSize(475, 170);
		panel.add(jsp1);

		JButton btThanhToan = new JButton("Thanh Toán");
		btThanhToan.setBounds(385, 566, 105, 23);
		contentPane.add(btThanhToan);

		JButton btThanhToanVoiIn = new JButton("Thanh Toán và In Hóa Đơn");
		btThanhToanVoiIn.setBounds(178, 566, 197, 23);
		contentPane.add(btThanhToanVoiIn);

		JButton btThoat = new JButton("Thoát");
		btThoat.setBounds(79, 566, 89, 23);
		contentPane.add(btThoat);







		//fill ở trên
		double thanhTien=0;
//		int maPhong=0;
		try {



			Connection conn = con.getConnection();
			String query = "SELECT dp.maPhong, dp.ngayBatDau, p.giaPhong, lp.tenLoaiPhong, GETDATE(), " +
					"CASE WHEN DATEDIFF(DAY, dp.ngayBatDau, GETDATE()) = 0 THEN 1 ELSE DATEDIFF(DAY, dp.ngayBatDau, GETDATE()) END AS soNgay, " +
					"(p.giaPhong * CASE WHEN DATEDIFF(DAY, dp.ngayBatDau, GETDATE()) = 0 THEN 1 ELSE DATEDIFF(DAY, dp.ngayBatDau, GETDATE()) END) AS thanhTien " +
					"FROM DatPhong dp " +
					"INNER JOIN Phong p ON dp.maPhong = p.maPhong " +
					"INNER JOIN LoaiPhong lp ON p.maLoaiPhong = lp.maLoaiPhong " +
					"WHERE dp.maDatPhong = ?";

			//            int maDatPhong = 1; // Thay đổi giá trị này thành mã đặt phòng cần truy vấn

			PreparedStatement statement = conn.prepareStatement(query);
			statement.setInt(1, maDatPhong);

			ResultSet resultSet = statement.executeQuery();




			if (resultSet.next()) {
				maPhong = resultSet.getInt(1);
				Date ngayDen = resultSet.getDate(2);
				double giaPhong = resultSet.getDouble(3);
				String tenLoaiPhong = resultSet.getString(4);
				Date ngayHienTai = resultSet.getDate(5);
				int soNgay = resultSet.getInt(6);
				thanhTien = resultSet.getDouble(7);



				lbMaDatPhong.setText("Mã đặt phòng: "+maDatPhong);
				lbPhong.setText("Phòng: "+maPhong);
				lbLoaiPhong.setText("Loại phòng: "+tenLoaiPhong);
				lbDonGia.setText("Đơn giá: "+giaPhong+" đ");
				lbNgayO.setText("Ngày đến: "+ngayDen);
				lbNgayDi.setText("Ngày đi: "+ngayHienTai);
				lbSoNgay.setText("Số ngày: "+soNgay);
				lbThanhTien.setText("Thành tiền: "+thanhTien+" đ");


			}

			resultSet.close();
			statement.close();



		} catch (Exception e) {
			// TODO: handle exception
		}






		//fill dịch vụ
		double tongTien=0;
		try {
			Connection conn = con.getConnection();
			// Truy vấn để lấy tổng tiền các thành tiền
			String sumQuery = "SELECT SUM(dv.donGia * ctdv.soLuong) AS tongTien " +
					"FROM ChiTietDichVu ctdv " +
					"INNER JOIN DichVu dv ON ctdv.maDichVu = dv.maDichVu " +
					"WHERE ctdv.maDatPhong = ?";
			PreparedStatement sumStatement = conn.prepareStatement(sumQuery);
			sumStatement.setInt(1, maDatPhong);
			ResultSet sumResultSet = sumStatement.executeQuery();

			// In ra tổng tiền các thành tiền
			if (sumResultSet.next()) {
				tongTien = sumResultSet.getDouble("tongTien");
				lbDichVu.setText("Dịch vụ: "+tongTien+" Đ");
				//                System.out.println("Tổng tiền các thành tiền: " + tongTien);
			}

			sumResultSet.close();
			sumStatement.close();

		} catch (Exception e) {
			// TODO: handle exception
		}


		lbSoTongTien.setText((thanhTien+tongTien)+" Đ");


		//fill table
		try {
			Connection conn = con.getConnection();
			PreparedStatement statement = conn.prepareStatement(
					"SELECT kh.maKhachHang, kh.tenKhachHang, kh.CCCD, kh.soDienThoai, kh.email, kh.quocTich, p.tenPhong, lp.tenLoaiPhong, nv.tenNhanVien, dp.ngayBatDau, dp.ngayKetThuc " +
							"FROM KhachHang kh " +
							"JOIN DatPhong dp ON kh.maKhachHang = dp.maKhachHang " +
							"JOIN Phong p ON dp.maPhong = p.maPhong " +
							"JOIN LoaiPhong lp ON p.maLoaiPhong = lp.maLoaiPhong " +
							"JOIN NhanVien nv ON dp.maNhanVien = nv.maNhanVien " +
							"WHERE dp.maDatPhong = ?"
					);


			statement.setInt(1, maDatPhong);
			ResultSet resultSet = statement.executeQuery();

			// In kết quả truy vấn
			resultSet.next();
			int maKhachHang = resultSet.getInt("maKhachHang");
			String tenKhachHang = resultSet.getString("tenKhachHang");
			String cccd = resultSet.getString("CCCD");
			String soDienThoai = resultSet.getString("soDienThoai");
			String email = resultSet.getString("email");
			String quocTich = resultSet.getString("quocTich");
			// Tạo mô hình bảng
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			Vector<Object> row = new Vector<>();
			row.add(maKhachHang);
			row.add(tenKhachHang);
			row.add(cccd);
			row.add(soDienThoai);
			row.add(email);
			row.add(quocTich);
			model.addRow(row);








			int maNhom = 0;
			// Chuẩn bị câu truy vấn
			try {
				String query = "SELECT maNhom FROM NhomKhachHang WHERE maKhachHang = ?";
				PreparedStatement statement1 = conn.prepareStatement(query);
				statement1.setInt(1, maKhachHang);
				ResultSet resultSet1 = statement1.executeQuery();
				if (resultSet1.next()) {
					maNhom = resultSet1.getInt("maNhom");
					// Thực hiện các xử lý khác liên quan đến maNhom ở đây
				}

				resultSet1.close();
				statement1.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}





			try {
				// Tạo câu truy vấn SELECT
				String query = "SELECT hoTen, CCCD FROM ThanhVienNhom WHERE maNhom = ?";

				// Chuẩn bị câu truy vấn
				PreparedStatement statement2 = conn.prepareStatement(query);
				statement2.setInt(1, maNhom);

				// Thực hiện truy vấn
				ResultSet resultSet2 = statement2.executeQuery();

				// Lấy giá trị CCCD từ kết quả truy vấn
				while(resultSet2.next()) {


					String hoTen2 = resultSet2.getString("hoTen");
					String cccd2 = resultSet2.getString("CCCD");

					Vector<Object> row2 = new Vector<>();
					row2.add("");
					row2.add(hoTen2);
					row2.add(cccd2);
					row2.add("");
					row2.add("");
					row2.add("");
					model.addRow(row2);
				}

				resultSet2.close();
				statement2.close();

			} catch (Exception e2) {
				// TODO: handle exception
			}







			// Đóng ResultSet và PreparedStatement
			resultSet.close();
			statement.close();

			// Đóng kết nối
			conn.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}





		//fill table_1
		try {
			Connection conn = con.getConnection();
			
			PreparedStatement statement = conn.prepareStatement(
					"SELECT cd.maChiTietDichVu AS machitietdichvu, dv.tenDichVu AS tendichvu, cd.soLuong, dv.donGia AS dongia, (cd.soLuong * dv.donGia) AS [thanhTien] " +
							"FROM ChiTietDichVu cd " +
							"JOIN DichVu dv ON cd.maDichVu = dv.maDichVu " +
							"WHERE cd.maDatPhong = ?"
					);


			statement.setInt(1, maDatPhong);
			ResultSet resultSet = statement.executeQuery();
			DefaultTableModel model = (DefaultTableModel) table_1.getModel();
			model.setRowCount(0);
			// In kết quả truy vấn
			while(resultSet.next()) {


				int maChiTietDichVu = resultSet.getInt("maChiTietDichVu");
				String tenDichVu = resultSet.getString("tenDichVu");
				int soLuong = resultSet.getInt("soLuong");
				int donGia = resultSet.getInt("donGia");
				int thanhTien1 = resultSet.getInt("thanhTien");

				// Tạo mô hình bảng

				Vector<Object> row = new Vector<>();
				row.add(maChiTietDichVu);
				row.add(tenDichVu);
				row.add(soLuong);
				row.add(donGia);
				row.add(thanhTien1);
				model.addRow(row);

			}
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		
		
		
		
		
		
		
		
		
		






		btThoat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		//ấn nút thanh toán
		btThanhToan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Connection conn = con.getConnection();
				
//				System.out.println(maPhong);
				try {
					
					 String query = "UPDATE Phong SET trangThaiPhong = ? WHERE maPhong = ?";
	                 PreparedStatement pstmt = conn.prepareStatement(query);
	                 pstmt.setNString(1, "Còn trống");
	                 pstmt.setInt(2, maPhong);
	                 pstmt.executeUpdate();
					
				} catch (Exception e2) {
					// TODO: handle exception
					System.out.println("hellow");
				}
				
				try {
				    String query = "UPDATE DatPhong SET trangThai = ?, ngayKetThuc = ? WHERE maDatPhong = ?";
				    PreparedStatement pstmt = conn.prepareStatement(query);
				    pstmt.setString(1, "Đã hoàn tất");
				    pstmt.setDate(2, new java.sql.Date(System.currentTimeMillis()));
				    pstmt.setInt(3, maDatPhong);
				    pstmt.executeUpdate();
				} catch (Exception e2) {
				    // Xử lý ngoại lệ
					System.out.println("hellow");
				}
				
				dispose();

				
				
			}
		});
		
		
		
		
		
		
		
		
		

	}
}
