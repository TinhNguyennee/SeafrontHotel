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

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Utils.ConnectDatabase;

public class PanelCustomer extends JPanel {
	private JTextField txtEmail;
	private JTextField txtSDT;
	private JTextField txtHoVaTen;
	private JTextField txtCCCD;
	private JTextField txtTimKhachHang;

	/**
	 * Create the panel.
	 */
	public PanelCustomer() {

		Utils.ConnectDatabase con = new ConnectDatabase();

		setBounds(0, 0, 665, 550);
		setLayout(null);

		JLabel lbTitle = new JLabel("Khách Hàng");
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setForeground(new Color(85, 140, 255));
		lbTitle.setFont(new Font("Segoe UI", Font.BOLD, 42));
		lbTitle.setBounds(169, 11, 332, 89);
		add(lbTitle);

		JLabel lbHoVaTen = new JLabel("Họ Và Tên");
		lbHoVaTen.setFont(new Font("Arial", Font.PLAIN, 16));
		lbHoVaTen.setBounds(10, 111, 92, 25);
		add(lbHoVaTen);

		JLabel lbCCCD = new JLabel("CCCD");
		lbCCCD.setFont(new Font("Arial", Font.PLAIN, 16));
		lbCCCD.setBounds(10, 147, 92, 25);
		add(lbCCCD);

		JLabel lbEmail = new JLabel("Email");
		lbEmail.setFont(new Font("Arial", Font.PLAIN, 16));
		lbEmail.setBounds(10, 183, 92, 25);
		add(lbEmail);

		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBounds(112, 187, 182, 20);
		add(txtEmail);

		JLabel lbSDT = new JLabel("Số Điện Thoại");
		lbSDT.setFont(new Font("Arial", Font.PLAIN, 16));
		lbSDT.setBounds(325, 111, 112, 25);
		add(lbSDT);

		JLabel lbQuocTich = new JLabel("Quốc Tịch");
		lbQuocTich.setFont(new Font("Arial", Font.PLAIN, 16));
		lbQuocTich.setBounds(325, 147, 92, 25);
		add(lbQuocTich);

		JLabel lbNgaySinh = new JLabel("Ngày Sinh");
		lbNgaySinh.setFont(new Font("Arial", Font.PLAIN, 16));
		lbNgaySinh.setBounds(325, 183, 112, 25);
		add(lbNgaySinh);

		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(435, 115, 182, 20);
		add(txtSDT);

		SpinnerDateModel dateModel = new SpinnerDateModel();
		JSpinner spinnerNgaySinh = new JSpinner(dateModel);
		spinnerNgaySinh.setEditor(new JSpinner.DateEditor(spinnerNgaySinh, "dd/MM/yyyy"));
		spinnerNgaySinh.setBounds(435, 187, 182, 20);
		add(spinnerNgaySinh);

		txtHoVaTen = new JTextField();
		txtHoVaTen.setColumns(10);
		txtHoVaTen.setBounds(112, 111, 182, 20);
		add(txtHoVaTen);

		txtCCCD = new JTextField();
		txtCCCD.setColumns(10);
		txtCCCD.setBounds(112, 147, 182, 20);
		add(txtCCCD);

		JButton btThemKhach = new JButton("Thêm");
		btThemKhach.setFont(new Font("Arial", Font.BOLD, 16));
		btThemKhach.setBounds(158, 260, 92, 31);
		add(btThemKhach);

		JButton btSuaKhach = new JButton("Sửa");
		btSuaKhach.setFont(new Font("Arial", Font.BOLD, 16));
		btSuaKhach.setBounds(283, 260, 92, 31);
		add(btSuaKhach);

		JLabel lbTimKhachHang = new JLabel("Mã Khách Hàng");
		lbTimKhachHang.setFont(new Font("Arial", Font.PLAIN, 16));
		lbTimKhachHang.setBounds(10, 297, 126, 25);
		add(lbTimKhachHang);

		txtTimKhachHang = new JTextField();
		txtTimKhachHang.setColumns(10);
		txtTimKhachHang.setBounds(146, 302, 419, 20);
		add(txtTimKhachHang);

		JButton btTim = new JButton("Tìm");
		btTim.setFont(new Font("Arial", Font.BOLD, 16));
		btTim.setBounds(575, 297, 80, 25);
		add(btTim);

		JTable table = new JTable(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Mã Khách Hàng","Họ Và Tên","Căn Cước Công Dân","Số Điện Thoại","Email","Giới Tính","Ngày Sinh","Quốc Tịch"
				}
				));
		table.setBorder(new LineBorder(new Color(0, 0, 0), 0));


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
		jsp.setLocation(10, 333);
		jsp.setSize(645, 206);
		jsp.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		add(jsp);

		JButton btXoaKhach = new JButton("Xóa");
		btXoaKhach.setFont(new Font("Arial", Font.BOLD, 16));
		btXoaKhach.setBounds(409, 260, 92, 31);
		add(btXoaKhach);

		JLabel lbGioiTinh = new JLabel("Giới Tính");
		lbGioiTinh.setFont(new Font("Arial", Font.PLAIN, 16));
		lbGioiTinh.setBounds(10, 219, 92, 25);
		add(lbGioiTinh);

		JComboBox cbbGioiTinh = new JComboBox();
		cbbGioiTinh.setModel(new DefaultComboBoxModel(new String[] {"Nam", "Nữ"}));
		cbbGioiTinh.setBounds(112, 222, 182, 22);
		add(cbbGioiTinh);

		JLabel lbLoaiKhach = new JLabel("Loại Khách");
		lbLoaiKhach.setFont(new Font("Arial", Font.PLAIN, 16));
		lbLoaiKhach.setBounds(325, 219, 112, 25);
		add(lbLoaiKhach);

		JComboBox cbbLoaiKhach = new JComboBox();
		cbbLoaiKhach.setBounds(435, 219, 182, 22);
		add(cbbLoaiKhach);

		JComboBox cbbQuocTich = new JComboBox();
		cbbQuocTich.setModel(new DefaultComboBoxModel(new String[] {"Việt Nam", "Ngoại Quốc"}));
		cbbQuocTich.setBounds(435, 150, 182, 22);
		add(cbbQuocTich);



		//fill loại khách hàng
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








		//fill table
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		try {

			//			Connection conn = con.getConnection();
			String query1 = "SELECT maKhachHang, tenKhachHang, CCCD, soDienThoai, email, gioiTinh, ngaySinh, quocTich FROM KhachHang";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query1);

			while (rs.next()) {
				int maKhachHang = rs.getInt("maKhachHang");
				String tenKhachHang = rs.getString("tenKhachHang");
				String CCCD = rs.getString("CCCD");
				String soDienThoai = rs.getString("soDienThoai");
				String email = rs.getString("email");
				boolean gioiTinh = rs.getBoolean("gioiTinh");
				Date ngaySinh = rs.getDate("ngaySinh");
				String quocTich = rs.getString("quocTich");


				Vector<Object> row = new Vector<>();
				row.add(maKhachHang);
				row.add(tenKhachHang);
				row.add(CCCD);
				row.add(soDienThoai);
				row.add(email);
				row.add(gioiTinh ? "Nam" : "Nữ");
				row.add(ngaySinh);
				row.add(quocTich);
				model.addRow(row);
			}




			//fill các text khi ấn vào row
			table.getSelectionModel().addListSelectionListener(e -> {
				// Kiểm tra xem có hàng nào được chọn không
				if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
					// Lấy chỉ số hàng được chọn
					int selectedRow = table.getSelectedRow();

					// Lấy dữ liệu từ hàng được chọn
					int maKhachHang = Integer.valueOf(table.getValueAt(selectedRow, 0).toString());
					
					
					
					
					String tenLoaiKhach = "";
					try {
						
						 String sql = "SELECT KhachHang.maKhachHang, LoaiKhachHang.chitiet " +
		                         "FROM KhachHang " +
		                         "JOIN LoaiKhachHang ON KhachHang.maLoai = LoaiKhachHang.maLoai " +
		                         "WHERE KhachHang.maKhachHang = ?";

		            PreparedStatement statement = conn.prepareStatement(sql);
		            statement.setInt(1, maKhachHang);

		            ResultSet resultSet = statement.executeQuery();
		            
		            if (resultSet.next()) {
		                tenLoaiKhach = resultSet.getString("chitiet");
		            }
						
					} catch (Exception e2) {
						// TODO: handle exception
					}
					
					
					
					
					
					
					
					Object hoTen = table.getValueAt(selectedRow, 1);
					Object canCuocCongDan = table.getValueAt(selectedRow, 2);
					Object soDienThoai = table.getValueAt(selectedRow, 3);
					Object email = table.getValueAt(selectedRow, 4);
					Object gioiTinh = table.getValueAt(selectedRow, 5);
					Object ngaySinh = table.getValueAt(selectedRow, 6);
					Object quocTich = table.getValueAt(selectedRow, 7);

					// Điền dữ liệu vào các trường văn bản
					txtHoVaTen.setText(hoTen.toString());
					txtCCCD.setText((String) canCuocCongDan);
					txtSDT.setText(soDienThoai.toString());
					txtEmail.setText(email.toString());
					cbbGioiTinh.setSelectedItem(gioiTinh.toString());
					if(ngaySinh!=null)dateModel.setValue(ngaySinh);
					if(quocTich!=null)cbbQuocTich.setSelectedItem(quocTich.toString());
					cbbLoaiKhach.setSelectedItem(tenLoaiKhach);
				}
			});





		} catch (Exception e) {
			// TODO: handle exception
		}





















		//ấn vào nút thêm
		btThemKhach.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Lấy dữ liệu từ các thành phần GUI
				String CCCD = txtCCCD.getText();
				String tenKhachHang = txtHoVaTen.getText();
				java.util.Date ngaySinh = (java.util.Date) spinnerNgaySinh.getValue();
				String soDienThoai = txtSDT.getText();
				boolean gioiTinh = cbbGioiTinh.getSelectedItem().equals("Nam");
				String email = txtEmail.getText();
				String quocTich = cbbQuocTich.getSelectedItem().toString();




				//tìm mã loại
				int maLoai = 0; // Ví dụ: index 0 tương ứng với maLoai 1

				try {
					String query = "SELECT maLoai FROM LoaiKhachHang where chitiet like N'"+cbbLoaiKhach.getSelectedItem().toString()+"'";
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);

					if (rs.next()) {
						maLoai = rs.getInt("maLoai");
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}



				//check lỗi
				if((Utils.Validate.isTxtEmpty(tenKhachHang))||((Utils.Validate.isTxtEmpty(soDienThoai))&&(Utils.Validate.isTxtEmpty(email)))) {
					JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin.", "Lỗi", JOptionPane.ERROR_MESSAGE);
					return;
				}else if(!Utils.Validate.isValidEmail(email)&&!Utils.Validate.isTxtEmpty(email)){
					JOptionPane.showMessageDialog(null, "Vui lòng điền đúng định dạng abc@gmail.com", "Lỗi", JOptionPane.ERROR_MESSAGE);
					return;		        	
				}else if(!Utils.Validate.isValidPhoneNumber(soDienThoai)&&!Utils.Validate.isTxtEmpty(soDienThoai)) {
					JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại đúng định dạng.", "Lỗi", JOptionPane.ERROR_MESSAGE);
					return;		        	
				}




				// Thực hiện thêm dữ liệu vào bảng KhachHang
				int maKhachHang = 0;
				try {
					String query = "INSERT INTO KhachHang (CCCD, tenKhachHang, ngaySinh, soDienThoai, gioiTinh, email, quocTich, maLoai) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
					pstmt.setString(1, CCCD);
					pstmt.setString(2, tenKhachHang);
					pstmt.setDate(3, new java.sql.Date(ngaySinh.getTime()));
					pstmt.setString(4, soDienThoai);
					pstmt.setBoolean(5, gioiTinh);
					pstmt.setString(6, email);
					pstmt.setString(7, quocTich);
					pstmt.setInt(8, maLoai);
					int rowsAffected = pstmt.executeUpdate();
					if (rowsAffected > 0) {
						ResultSet generatedKeys = pstmt.getGeneratedKeys();
						if (generatedKeys.next()) {
							maKhachHang = generatedKeys.getInt(1);
						}
						Utils.MsgBox.alert(null, "Thêm thành công");
					}


				} catch (SQLException ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Thêm khách hàng thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}



				if(maLoai==2) {
					try (PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO NhomKhachHang (maKhachHang) VALUES (?)")) {
						stmt2.setInt(1, maKhachHang);
						stmt2.executeUpdate();
					}catch (Exception e2) {
						// TODO: handle exception
					}
				}




			}
		});





		
		btSuaKhach.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	
		    	
		    	int selectedRow = table.getSelectedRow();
		    	if(selectedRow==-1) {
		    		Utils.MsgBox.alert(null, "Bạn chưa chọn khách hàng");
		    		return;
		    	}
		    	Object maKhachHang = table.getValueAt(selectedRow, 0);
		    	
		    	
		        // Lấy dữ liệu từ GUI
		        String tenKhachHang = txtHoVaTen.getText();
		        String soDienThoai = txtSDT.getText();
		        String cccd = txtCCCD.getText();
		        String quocTich = cbbQuocTich.getSelectedItem().toString();
		        String email = txtEmail.getText();
				java.util.Date ngaySinh = (java.util.Date) spinnerNgaySinh.getValue();
				boolean gioiTinh = cbbGioiTinh.getSelectedItem().equals("Nam");
				
		        int maLoai = cbbLoaiKhach.getSelectedIndex() + 1;

		        // Kiểm tra và xác thực dữ liệu
		        if (tenKhachHang.isEmpty() || (soDienThoai.isEmpty() && email.isEmpty())) {
		            // Hiển thị thông báo lỗi nếu các trường bắt buộc chưa được nhập
		            Utils.MsgBox.alert(null,"Vui lòng điền đầy đủ thông tin khách hàng.");
		            return; // Kết thúc xử lý sự kiện
		        }

		        if (!Utils.Validate.isValidEmail(email)&&!email.isEmpty()) {
		            // Hiển thị thông báo lỗi nếu email không hợp lệ
		            Utils.MsgBox.alert(null,"Email không hợp lệ.");
		            return; // Kết thúc xử lý sự kiện
		        }

		        if (!Utils.Validate.isValidPhoneNumber(soDienThoai)&&!soDienThoai.isEmpty()) {
		            // Hiển thị thông báo lỗi nếu số điện thoại không hợp lệ
		            Utils.MsgBox.alert(null,"Số điện thoại không hợp lệ.");
		            return; // Kết thúc xử lý sự kiện
		        }
		        
		        
		        
		        
		        
		        
		        
		      //tìm mã loại
		        int maLoai1 = 0; // Ví dụ: index 0 tương ứng với maLoai 1

				try {
					String query = "SELECT maLoai FROM KhachHang where makhachhang = "+Integer.valueOf(maKhachHang.toString());
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);

					if (rs.next()) {
						maLoai1 = rs.getInt("maLoai");
					}
				} catch (Exception e2) {
					// TODO: handle exception
				}
		        
				
				//tìm mã nhóm
				int maNhomKhachHang = 0;
				try {
					String selectQuery = "SELECT maNhom FROM NhomKhachHang WHERE maKhachHang = ?";
				    PreparedStatement statement = conn.prepareStatement(selectQuery);

				    // Đặt tham số
				    statement.setInt(1, Integer.valueOf(maKhachHang.toString()));

				    // Thực hiện truy vấn
				    ResultSet resultSet = statement.executeQuery();
				    if (resultSet.next()) {
				        // Lấy giá trị mã nhóm khách hàng từ kết quả truy vấn
				        maNhomKhachHang = resultSet.getInt("maNhom");
				    }
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
		        
		        
		        
		        
		        
		        

		        // Thực hiện cập nhật dữ liệu khách hàng trong cơ sở dữ liệu
		        try {
		            String updateQuery = "UPDATE KhachHang SET tenKhachHang = ?, soDienThoai = ?, cccd = ?, quoctich = ?, email = ?, ngaysinh = ?, gioitinh = ?, maLoai = ? WHERE maKhachHang = ?";
		            PreparedStatement statement = conn.prepareStatement(updateQuery);

		            // Đặt các tham số
		            statement.setString(1, tenKhachHang);
		            statement.setString(2, soDienThoai);
		            statement.setString(3, cccd);
		            statement.setString(4, quocTich);
		            statement.setString(5, email);
		            statement.setObject(6, ngaySinh);
		            statement.setBoolean(7, gioiTinh);
		            statement.setInt(8, maLoai);
		            statement.setInt(9, Integer.valueOf(maKhachHang.toString())); // Thay thế maKhachHang bằng ID của khách hàng cần sửa

		            // Thực hiện cập nhật
		            int rowsAffected = statement.executeUpdate();

		            if (rowsAffected > 0) {
		                // Hiển thị thông báo thành công
		                Utils.MsgBox.alert(null,"Cập nhật thông tin khách hàng thành công.");
		                
		                
		                
		                
		                //check xóa hoặc thêm nhóm khách
		                if(maLoai==maLoai1) {
		                	
		                }else if(maLoai==1) {
		                	
		                	String deleteQuery1 = "delete from thanhviennhom where manhom = "+maNhomKhachHang;
				            PreparedStatement statement1 = conn.prepareStatement(deleteQuery1);
				            statement1.executeUpdate();
				            
				            String deleteQuery2 = "delete from nhomkhachhang where manhom = "+maNhomKhachHang;
				            PreparedStatement statement2 = conn.prepareStatement(deleteQuery2);
				            statement2.executeUpdate();

		                }else if(maLoai==2) {
		                	try (PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO NhomKhachHang (maKhachHang) VALUES (?)")) {
								stmt2.setInt(1, Integer.valueOf(maKhachHang.toString()));
								stmt2.executeUpdate();
		                	}catch (Exception e2) {
								// TODO: handle exception
							}
		                }
		                
		                
		                
		                
		                
		                
		            } else {
		                // Hiển thị thông báo lỗi nếu không có hàng nào được cập nhật
		                Utils.MsgBox.alert(null,"Không tìm thấy khách hàng để cập nhật.");
		            }
		        } catch (SQLException ex) {
		            // Xử lý ngoại lệ SQL nếu có lỗi trong quá trình cập nhật
		            Utils.MsgBox.alert(null,"Lỗi cập nhật khách hàng: " + ex.getMessage());
		        }
		    }
		});







		
		
		
		
		//ấn nút xóa khách
		btXoaKhach.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				//lấy mã khách hàng
				int selectedRow = table.getSelectedRow();
				int maKhachHang = 0;
				if(selectedRow==-1) {
		    		Utils.MsgBox.alert(null, "Bạn chưa chọn khách hàng");
		    		return;
		    	}else {
		    		maKhachHang = (int) table.getValueAt(selectedRow, 0);
		    	}
				
				
				
				
				//check xem khách hàng này đã đặt phòng hay chưa
				String query = "select madatphong from datphong where makhachhang = "+maKhachHang;
				
				try {
					PreparedStatement statement = conn.prepareStatement(query);
					ResultSet resultSet = statement.executeQuery();
					
					if(resultSet.next()) {
			    		Utils.MsgBox.alert(null, "Không thể xóa, khách hàng này đã đặt phòng");
					}else {
						
	
						
						//tìm mã nhóm
						int maNhomKhachHang = 0;
						try {
							String selectQuery = "SELECT maNhom FROM NhomKhachHang WHERE maKhachHang = ?";
						    PreparedStatement statement1 = conn.prepareStatement(selectQuery);

						    // Đặt tham số
						    statement1.setInt(1, maKhachHang);

						    // Thực hiện truy vấn
						    ResultSet resultSet1 = statement1.executeQuery();
						    if (resultSet1.next()) {
						        // Lấy giá trị mã nhóm khách hàng từ kết quả truy vấn
						        maNhomKhachHang = resultSet1.getInt("maNhom");
						    }
						} catch (Exception e2) {
							// TODO: handle exception
						}

						
						
						
	                	String deleteQuery1 = "delete from thanhviennhom where manhom = "+maNhomKhachHang;
			            PreparedStatement statement2 = conn.prepareStatement(deleteQuery1);
			            statement2.executeUpdate();
			            
			            String deleteQuery2 = "delete from nhomkhachhang where manhom = "+maNhomKhachHang;
			            PreparedStatement statement3 = conn.prepareStatement(deleteQuery2);
			            statement3.executeUpdate();
			            
			            String deleteQuery3 = "delete from khachhang where makhachhang = "+maKhachHang;
			            PreparedStatement statement4 = conn.prepareStatement(deleteQuery3);
			            statement4.executeUpdate();
			            

			            
			            
						
			    		Utils.MsgBox.alert(null, "Xóa thành công");
						
					}
					
					
				} catch (Exception e2) {
					// TODO: handle exception
				}
				
				
				
				
				
			}
		});
		
		
		
		
		
		
		
		
		





	}
}
