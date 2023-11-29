package UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Utils.ConnectDatabase;

public class PanelService extends JPanel {
	private JTextField txtDonGia;
	private JTextField txtMaDatPhong;
	private JTextField txtTenPhong;
	private JTextField txtTimMaDichVu;
	private JTable table;
	private JTable table_1;
	private JTextField txtLoaiPhong;

	/**
	 * Create the panel.
	 */
	public PanelService() {

		Utils.ConnectDatabase con = new ConnectDatabase();

		setBounds(0, 0, 665, 550);
		setLayout(null);

		JLabel lbTitle = new JLabel("Dịch Vụ");
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setForeground(new Color(85, 140, 255));
		lbTitle.setFont(new Font("Segoe UI", Font.BOLD, 42));
		lbTitle.setBounds(169, 11, 332, 89);
		add(lbTitle);

		JLabel lbMaDatPhong = new JLabel("Mã Đặt Phòng");
		lbMaDatPhong.setFont(new Font("Arial", Font.PLAIN, 16));
		lbMaDatPhong.setBounds(5, 111, 111, 25);
		add(lbMaDatPhong);

		JLabel lbTenPhong = new JLabel("Tên Phòng");
		lbTenPhong.setFont(new Font("Arial", Font.PLAIN, 16));
		lbTenPhong.setBounds(10, 147, 92, 25);
		add(lbTenPhong);

		JLabel lbLoaiPhong = new JLabel("Loại Phòng");
		lbLoaiPhong.setFont(new Font("Arial", Font.PLAIN, 16));
		lbLoaiPhong.setBounds(10, 183, 92, 25);
		add(lbLoaiPhong);

		JLabel lbDichVu = new JLabel("Dịch Vụ");
		lbDichVu.setFont(new Font("Arial", Font.PLAIN, 16));
		lbDichVu.setBounds(325, 111, 112, 25);
		add(lbDichVu);

		JLabel lbSoLuong = new JLabel("Số Lượng");
		lbSoLuong.setFont(new Font("Arial", Font.PLAIN, 16));
		lbSoLuong.setBounds(325, 147, 92, 25);
		add(lbSoLuong);

		JLabel lbDonGia = new JLabel("Đơn Giá");
		lbDonGia.setFont(new Font("Arial", Font.PLAIN, 16));
		lbDonGia.setBounds(325, 183, 102, 25);
		add(lbDonGia);

		JComboBox<String> cbbSoLuong = new JComboBox<>();

		for (int i = 1; i <= 99; i++) {
			cbbSoLuong.addItem(String.valueOf(i));
		}
		cbbSoLuong.setBounds(435, 151, 182, 20);
		add(cbbSoLuong);

		txtDonGia = new JTextField();
		txtDonGia.setEditable(false);
		txtDonGia.setColumns(10);
		txtDonGia.setBounds(435, 187, 182, 20);
		add(txtDonGia);

		txtMaDatPhong = new JTextField();
		txtMaDatPhong.setColumns(10);
		txtMaDatPhong.setBounds(112, 111, 182, 20);
		add(txtMaDatPhong);

		txtTenPhong = new JTextField();
		txtTenPhong.setEditable(false);
		txtTenPhong.setColumns(10);
		txtTenPhong.setBounds(112, 147, 182, 20);
		add(txtTenPhong);

		JButton btThemDichVu = new JButton("Thêm");
		btThemDichVu.setFont(new Font("Arial", Font.BOLD, 16));
		btThemDichVu.setBounds(158, 240, 92, 31);
		add(btThemDichVu);

		JButton btSuaDichVu = new JButton("Sửa");
		btSuaDichVu.setFont(new Font("Arial", Font.BOLD, 16));
		btSuaDichVu.setBounds(283, 240, 92, 31);
		add(btSuaDichVu);

		JLabel lbTimMaDichVu = new JLabel("Mã Dịch Vụ");
		lbTimMaDichVu.setFont(new Font("Arial", Font.PLAIN, 16));
		lbTimMaDichVu.setBounds(10, 297, 111, 25);
		add(lbTimMaDichVu);

		txtTimMaDichVu = new JTextField();
		txtTimMaDichVu.setColumns(10);
		txtTimMaDichVu.setBounds(120, 302, 445, 20);
		add(txtTimMaDichVu);

		JButton btTim = new JButton("Tìm");
		btTim.setFont(new Font("Arial", Font.BOLD, 16));
		btTim.setBounds(575, 297, 80, 25);
		add(btTim);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 325, 645, 214);
		add(tabbedPane);

		JPanel panelDichVu = new JPanel();
		tabbedPane.addTab("Dịch Vụ", null, panelDichVu, null);
		panelDichVu.setLayout(null);

		table = new JTable();
		table.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Mã Đặt Dịch Vụ","Tên Dịch Vụ","Số Lượng","Đơn Giá","Thành Tiền"
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
		jsp.setBounds(0, 0, 640, 186);
		panelDichVu.add(jsp);

		JPanel panel = new JPanel();
		tabbedPane.addTab("Phòng Đặt", null, panel, null);
		panel.setLayout(null);

		table_1 = new JTable();
		table_1.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		table_1.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Mã Đặt Phòng","Phòng","Loại Phòng","Người Lập","Ngày Ở","Ngày Trả","Trạng Thái"
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
		renderer1.setHorizontalAlignment(JLabel.CENTER);

		// Áp dụng Renderer cho tất cả các ô trong bảng
		for (int i = 0; i < table_1.getColumnCount(); i++) {
			table_1.getColumnModel().getColumn(i).setCellRenderer(renderer);
		}

		JScrollPane jsp1 = new JScrollPane(table_1);
		jsp1.setLocation(0, 0);
		jsp1.setSize(640, 186);
		panel.add(jsp1);

		JButton btXoaDichVu = new JButton("Xóa");
		btXoaDichVu.setFont(new Font("Arial", Font.BOLD, 16));
		btXoaDichVu.setBounds(409, 240, 92, 31);
		add(btXoaDichVu);

		JComboBox cbbDichVu = new JComboBox();
		cbbDichVu.setBounds(435, 114, 182, 22);
		add(cbbDichVu);

		txtLoaiPhong = new JTextField();
		txtLoaiPhong.setEditable(false);
		txtLoaiPhong.setColumns(10);
		txtLoaiPhong.setBounds(112, 183, 182, 20);
		add(txtLoaiPhong);

		//fill cbbDichVu
		try {
			Connection conn = con.getConnection();
			String query = "SELECT tenDichVu FROM DichVu";
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				String tenDichVu = resultSet.getString("tenDichVu");
				cbbDichVu.addItem(tenDichVu);
			}
			// Đóng kết nối và giải phóng tài nguyên
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}


		//fill đơn giá
		try {
			String tenDichVu = (String) cbbDichVu.getSelectedItem();
			Connection conn = con.getConnection();
			String query = "SELECT donGia FROM DichVu where tenDichVu like N'"+tenDichVu+"'";
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next()) {
				int donGia = resultSet.getInt("donGia");
				txtDonGia.setText(String.valueOf(donGia));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}




		// Bắt sự kiện khi chọn mục trong cbbDichVu
		cbbDichVu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String tenDichVu = (String) cbbDichVu.getSelectedItem();
					Connection conn = con.getConnection();
					String query = "SELECT donGia FROM DichVu where tenDichVu like N'"+tenDichVu+"'";
					Statement statement = conn.createStatement();
					ResultSet resultSet = statement.executeQuery(query);
					if(resultSet.next()) {
						int donGia = resultSet.getInt("donGia");
						txtDonGia.setText(String.valueOf(donGia));
					}

				} catch (Exception e1) {
					// TODO: handle exception
				}
			}
		});









		// fill table_1
		try {
			Connection conn = con.getConnection();
			Statement statement = conn.createStatement();
			String query = "SELECT dp.maDatPhong, p.tenPhong, lp.tenLoaiPhong, nv.tenNhanVien, dp.ngayBatDau, dp.ngayKetThuc, dp.trangThai " +
					"FROM DatPhong dp " +
					"JOIN Phong p ON dp.maPhong = p.maPhong " +
					"JOIN LoaiPhong lp ON p.maLoaiPhong = lp.maLoaiPhong " +
					"JOIN NhanVien nv ON dp.maNhanVien = nv.maNhanVien";
			ResultSet resultSet = statement.executeQuery(query);

			// Tạo mô hình bảng
			DefaultTableModel model = (DefaultTableModel) table_1.getModel();
			model.setRowCount(0);

			// Đổ dữ liệu từ ResultSet vào mô hình bảng
			while (resultSet.next()) {
				int maDatPhong = resultSet.getInt("maDatPhong");
				String tenPhong = resultSet.getString("tenPhong");
				String tenLoaiPhong = resultSet.getString("tenLoaiPhong");
				String tenNhanVien = resultSet.getString("tenNhanVien");
				Date ngayO1 = resultSet.getDate("ngayBatDau");
				Date ngayTra1 = resultSet.getDate("ngayKetThuc");
				String trangThai = resultSet.getString("trangThai");

				Vector<Object> row = new Vector<>();
				row.add(maDatPhong);
				row.add(tenPhong);
				row.add(tenLoaiPhong);
				row.add(tenNhanVien);
				row.add(ngayO1);
				row.add(ngayTra1);
				row.add(trangThai);
				model.addRow(row);
			}

			// Đóng ResultSet và Statement
			resultSet.close();
			statement.close();

			// Đóng kết nối
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}







		// chọn row trong table_1
		table_1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) { // Đảm bảo chỉ xử lý khi kết thúc quá trình chọn hàng
					int selectedRow = table_1.getSelectedRow();
					if (selectedRow != -1) { // Kiểm tra xem đã chọn hàng nào chưa
						// Lấy dữ liệu từ hàng được chọn
						int maDatPhong = (int) table_1.getValueAt(selectedRow, 0);

						//fill table
						Connection conn = con.getConnection();
						try {
							PreparedStatement statement = conn.prepareStatement(
									"SELECT cd.maChiTietDichVu AS machitietdichvu, dv.tenDichVu AS tendichvu, cd.soLuong, dv.donGia AS dongia, (cd.soLuong * dv.donGia) AS [thanhTien] " +
											"FROM ChiTietDichVu cd " +
											"JOIN DichVu dv ON cd.maDichVu = dv.maDichVu " +
											"WHERE cd.maDatPhong = ?"
									);


							statement.setInt(1, maDatPhong);
							ResultSet resultSet = statement.executeQuery();
							DefaultTableModel model = (DefaultTableModel) table.getModel();
							model.setRowCount(0);
							// In kết quả truy vấn
							while(resultSet.next()) {


								int maKChiTietDichVu = resultSet.getInt("maChiTietDichVu");
								String tenDichVu = resultSet.getString("tenDichVu");
								int soLuong = resultSet.getInt("soLuong");
								int donGia = resultSet.getInt("donGia");
								int thanhTien = resultSet.getInt("thanhTien");

								// Tạo mô hình bảng

								Vector<Object> row = new Vector<>();
								row.add(maKChiTietDichVu);
								row.add(tenDichVu);
								row.add(soLuong);
								row.add(donGia);
								row.add(thanhTien);
								model.addRow(row);

							}







							try {
								String query = "SELECT p.tenPhong, lp.tenLoaiPhong " +
										"FROM DatPhong dp " +
										"JOIN Phong p ON dp.maPhong = p.maPhong " +
										"JOIN LoaiPhong lp ON p.maLoaiPhong = lp.maLoaiPhong " +
										"WHERE dp.maDatPhong = ?";
								PreparedStatement statement1 = conn.prepareStatement(query);
								statement1.setInt(1, maDatPhong);

								// Thực hiện truy vấn
								ResultSet resultSet1 = statement1.executeQuery();

								// Kiểm tra xem có kết quả trả về hay không
								if (resultSet1.next()) {
									String tenPhong = resultSet1.getString("tenPhong");
									String tenLoaiPhong = resultSet1.getString("tenLoaiPhong");

									// In ra tên phòng và loại phòng
									txtTenPhong.setText(tenPhong);
									txtLoaiPhong.setText(tenLoaiPhong);
								}

								// Đóng kết nối và giải phóng tài nguyên
								resultSet1.close();
								statement1.close();
							} catch (Exception e2) {
								// TODO: handle exception
							}



							txtMaDatPhong.setText(String.valueOf(maDatPhong));




							// Đóng ResultSet và PreparedStatement
							resultSet.close();
							statement.close();

							// Đóng kết nối
							conn.close();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}

						// Thực hiện các hành động khác tùy theo nhu cầu của bạn
					}
				}
			}
		});


		
		
		
		
		
		//gõ chữ vào mã đặt phòng tự fill tên phòng và loại phòng
		txtMaDatPhong.getDocument().addDocumentListener(new DocumentListener() {
		    @Override
		    public void insertUpdate(DocumentEvent e) {
		        valueChanged();
		    }

		    @Override
		    public void removeUpdate(DocumentEvent e) {
		        valueChanged();
		    }

		    @Override
		    public void changedUpdate(DocumentEvent e) {
		        valueChanged();
		    }

		    private void valueChanged() {
		        String maDatPhong = txtMaDatPhong.getText();
				Connection conn = con.getConnection();
				try {
					String query = "SELECT p.tenPhong, lp.tenLoaiPhong " +
							"FROM DatPhong dp " +
							"JOIN Phong p ON dp.maPhong = p.maPhong " +
							"JOIN LoaiPhong lp ON p.maLoaiPhong = lp.maLoaiPhong " +
							"WHERE dp.maDatPhong = ?";
					PreparedStatement statement1 = conn.prepareStatement(query);
					statement1.setInt(1, Integer.valueOf(maDatPhong));

					// Thực hiện truy vấn
					ResultSet resultSet1 = statement1.executeQuery();

					// Kiểm tra xem có kết quả trả về hay không
					if (resultSet1.next()) {
						String tenPhong = resultSet1.getString("tenPhong");
						String tenLoaiPhong = resultSet1.getString("tenLoaiPhong");

						// In ra tên phòng và loại phòng
						txtTenPhong.setText(tenPhong);
						txtLoaiPhong.setText(tenLoaiPhong);
					}

					// Đóng kết nối và giải phóng tài nguyên
					resultSet1.close();
					statement1.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
		        

		    }
		});




		//ấn nút thêm
		btThemDichVu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				Connection conn = con.getConnection();
				String maDatPhong = txtMaDatPhong.getText();

				// Kiểm tra xem maDatPhong không chứa ký tự chữ
				if (!maDatPhong.matches(".*[a-zA-Z].*")) {



				// Tạo câu truy vấn SQL để kiểm tra mã đặt phòng có tồn tại hay không
				String query = "SELECT trangThai FROM DatPhong WHERE maDatPhong = ?";
				try {
				    PreparedStatement statement = conn.prepareStatement(query);
				    statement.setString(1, maDatPhong);

				    ResultSet resultSet = statement.executeQuery();
				    if (resultSet.next()) {
				        if(resultSet.getString("trangThai").equalsIgnoreCase("Chưa thanh toán")) {
				        	
				        	
				        	Date ngayBatDau = null;
				        	try {
				        		String query1 = "SELECT ngayBatDau, ngayKetThuc FROM DatPhong WHERE maDatPhong = ?";
				                PreparedStatement statement1 = conn.prepareStatement(query1);
				                statement1.setInt(1, Integer.valueOf(maDatPhong));

				                ResultSet resultSet1 = statement1.executeQuery();
				                if (resultSet1.next()) {
				                    ngayBatDau = resultSet1.getDate("ngayBatDau");
				                }
							} catch (Exception e2) {
								// TODO: handle exception
							}
				        	
				        	if(new Date(System.currentTimeMillis()).compareTo(ngayBatDau) < 1) {
				        		Utils.MsgBox.alert(null, "Phòng đặt trước, chưa thể thêm dịch vụ");
				        		return;
				        	}
				        	
				        	
				        	//tìm mã dịch vụ
				        	int maDichVu = 0;
				        	String query1 = "SELECT maDichVu FROM DichVu WHERE tenDichVu = ?";
				        	try {
				        	    PreparedStatement statement1 = conn.prepareStatement(query1);
				        	    statement1.setString(1, (String) cbbDichVu.getSelectedItem());

				        	    ResultSet resultSet1 = statement1.executeQuery();
				        	    if (resultSet1.next()) {
				        	        maDichVu = resultSet1.getInt("maDichVu");
				        	    }

				        	    resultSet1.close();
				        	    statement1.close();
				        	} catch (SQLException e1) {
				        	    e1.printStackTrace();
				        	}
				        	
				        	
				        	
				        	
				        	
				        	
				        	
				        	
				        	
				        	
				        	
				        	
				        	
				        	//thực hiện lệnh thêm chi tiết dịch vụ
				        	int soLuong = Integer.valueOf((String) cbbSoLuong.getSelectedItem()) ;
				        	
				        	String query2 = "INSERT INTO ChiTietDichVu (soLuong, maDatPhong, maDichVu) VALUES (?, ?, ?)";

				        	try {
				        	    PreparedStatement statement2 = conn.prepareStatement(query2);
				        	    statement2.setInt(1, soLuong);
				        	    statement2.setInt(2, Integer.valueOf(maDatPhong));
				        	    statement2.setInt(3, maDichVu);

				        	    // Thực hiện truy vấn INSERT
				        	    int rowsInserted = statement2.executeUpdate();
				        	    if (rowsInserted > 0) {
							    	Utils.MsgBox.alert(null, "Thêm thành công");
				        	    }

				        	    statement2.close();
				        	} catch (SQLException e1) {
				        	    e1.printStackTrace();
				        	}
				        	
				        	
				        	
				        	
				        	
				        	
				        	
				        	
				        	
				        	
				        }else {
					    	Utils.MsgBox.alert(null, "Mã đặt phòng này đã được thanh toán");
				        }
				    }else {
				    	Utils.MsgBox.alert(null, "Mã đặt phòng không tồn tại");
				    }

				    resultSet.close();
				    statement.close();
				} catch (SQLException e1) {
				    e1.printStackTrace();
				}
				
				
				} else {
			    	Utils.MsgBox.alert(null, "Mã đặt phòng không đúng định dạng");
				}
				
				
				
				
				
				
			}
		});
		
		
		
		
		
		
		
		
		
		//ấn vào nút xóa dịch vụ
		btXoaDichVu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Connection conn = con.getConnection();
				
				
				
				//lấy mã chi tiết dịch vụ
				int selectedRow = table.getSelectedRow();
				int maChiTietDichVu = 0;
				if(selectedRow==-1) {
		    		Utils.MsgBox.alert(null, "Bạn chưa chọn dịch vụ");
		    		return;
		    	}else {
		    		maChiTietDichVu = (int) table.getValueAt(selectedRow, 0);
		    	}
				
				//lấy trạng thái
				int selectedRow1 = table_1.getSelectedRow();
				String trangThai=null;
				if(selectedRow1==-1) {
		    	}else {
		    		trangThai = table_1.getValueAt(selectedRow, 6).toString();
		    	}
				
				
				if(trangThai.equalsIgnoreCase("Đã hoàn tất")) {
			    	Utils.MsgBox.alert(null, "Phòng này đã hoàn tất, không thể thêm dịch vụ");
			    	return;
				}
				
				
				//tiến hành xóa dịch vụ
				String query = "delete from chitietdichvu where machitietdichvu = "+maChiTietDichVu;
				
				try {
					PreparedStatement statement = conn.prepareStatement(query);
	        	    int rowsInserted = statement.executeUpdate();
	        	    if (rowsInserted > 0) {
				    	Utils.MsgBox.alert(null, "Xóa thành công");
	        	    }else {
	        	    	Utils.MsgBox.alert(null, "Dịch vụ này đã được xóa, vui lòng tải lại trang");    	
	        	    }
				} catch (Exception e2) {
					// TODO: handle exception
				}

        	    
        	    
        	    
			}
		});
		
		
		
		
		
		
		
		
		
		



	}
}
