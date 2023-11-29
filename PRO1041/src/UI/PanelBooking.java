package UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Utils.ConnectDatabase;

public class PanelBooking extends JPanel {
	private JTextField txtLoaiPhong;
	private JTextField txtNgayO;
	private JTextField txtNgayTra;
	private JTable table;
	private JTable table_1;
	private JTextField txtTimMaDatPhong;

	private static int nguoiLap;
	private static int phong;
	private static Date ngayO;
	private static Date ngayTra;
	public static int getNguoiLap() {
		return nguoiLap;
	}
	public void setNguoiLap(int nguoiLap) {
		this.nguoiLap = nguoiLap;
	}
	public static int getPhong() {
		return phong;
	}
	public void setPhong(int phong) {
		this.phong = phong;
	}
	public static Date getNgayO() {
		return ngayO;
	}
	public void setNgayO(Date ngayO) {
		this.ngayO = ngayO;
	}
	public static Date getNgayTra() {
		return ngayTra;
	}
	public void setNgayTra(Date ngayTra) {
		this.ngayTra = ngayTra;
	}





	/**
	 * Create the panel.
	 */
	public PanelBooking(StaffMain main) {

		Utils.ConnectDatabase con = new ConnectDatabase();

		setBounds(0, 0, 665, 550);
		setLayout(null);

		JLabel lbTitle = new JLabel("Đặt Phòng");
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setForeground(new Color(85, 140, 255));
		lbTitle.setFont(new Font("Segoe UI", Font.BOLD, 42));
		lbTitle.setBounds(169, 11, 332, 89);
		add(lbTitle);

		JLabel lbPhong = new JLabel("Phòng");
		lbPhong.setFont(new Font("Arial", Font.PLAIN, 16));
		lbPhong.setBounds(10, 136, 92, 25);
		add(lbPhong);

		JLabel lbLoaiPhong = new JLabel("Loại Phòng");
		lbLoaiPhong.setFont(new Font("Arial", Font.PLAIN, 16));
		lbLoaiPhong.setBounds(10, 172, 92, 25);
		add(lbLoaiPhong);

		JLabel lbNguoiLap = new JLabel("Người Lập");
		lbNguoiLap.setFont(new Font("Arial", Font.PLAIN, 16));
		lbNguoiLap.setBounds(10, 100, 92, 25);
		add(lbNguoiLap);

		JComboBox cbbNguoiLap = new JComboBox();
		cbbNguoiLap.setBounds(112, 103, 182, 22);
		add(cbbNguoiLap);

		txtLoaiPhong = new JTextField();
		txtLoaiPhong.setEditable(false);
		txtLoaiPhong.setBounds(112, 176, 182, 20);
		add(txtLoaiPhong);
		txtLoaiPhong.setColumns(10);

		JComboBox cbbPhong = new JComboBox();
		cbbPhong.setBounds(112, 139, 182, 22);
		add(cbbPhong);

		JLabel lbNgayO = new JLabel("Ngày Ở");
		lbNgayO.setFont(new Font("Arial", Font.PLAIN, 16));
		lbNgayO.setBounds(325, 100, 92, 25);
		add(lbNgayO);

		SpinnerDateModel dateModel = new SpinnerDateModel();
		JSpinner spinnerNgayO = new JSpinner(dateModel);
		spinnerNgayO.setEditor(new JSpinner.DateEditor(spinnerNgayO, "dd/MM/yyyy"));
		spinnerNgayO.setBounds(435, 104, 182, 20);
		add(spinnerNgayO);

		JLabel lbNgayTra = new JLabel("Ngày Trả");
		lbNgayTra.setFont(new Font("Arial", Font.PLAIN, 16));
		lbNgayTra.setBounds(325, 136, 92, 25);
		add(lbNgayTra);

		SpinnerDateModel dateModel2 = new SpinnerDateModel();
		JSpinner spinnerNgayTra = new JSpinner(dateModel2);
		spinnerNgayTra.setEditor(new JSpinner.DateEditor(spinnerNgayTra, "dd/MM/yyyy"));
		spinnerNgayTra.setEnabled(false); // Không cho phép chỉnh sửa
		spinnerNgayTra.setBounds(435, 140, 182, 20);
		add(spinnerNgayTra);

		JLabel lbSoNgay = new JLabel("Số Ngày");
		lbSoNgay.setFont(new Font("Arial", Font.PLAIN, 16));
		lbSoNgay.setBounds(325, 172, 92, 25);
		add(lbSoNgay);

		JComboBox<String> cbbSoNgay = new JComboBox<>();

		for (int i = 1; i <= 99; i++) {
			cbbSoNgay.addItem(String.valueOf(i));
		}
		cbbSoNgay.setBounds(435, 176, 182, 20);
		add(cbbSoNgay);






		int soNgay = Integer.parseInt(cbbSoNgay.getSelectedItem().toString());
		java.util.Date ngayO = (java.util.Date) spinnerNgayO.getValue();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(ngayO);
		calendar.add(Calendar.DATE, soNgay);

		java.util.Date ngayTra = calendar.getTime();
		Date ngayTraSQL = new Date(ngayTra.getTime()); // Chuyển đổi sang java.sql.Date

		spinnerNgayTra.setValue(ngayTraSQL);





		cbbSoNgay.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int soNgay = Integer.parseInt(cbbSoNgay.getSelectedItem().toString());
				java.util.Date ngayO = (java.util.Date) spinnerNgayO.getValue();

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(ngayO);
				calendar.add(Calendar.DATE, soNgay);

				java.util.Date ngayTra = calendar.getTime();
				Date ngayTraSQL = new Date(ngayTra.getTime()); // Chuyển đổi sang java.sql.Date

				spinnerNgayTra.setValue(ngayTraSQL);
			}
		});



		spinnerNgayO.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {


				int soNgay = Integer.parseInt(cbbSoNgay.getSelectedItem().toString());
				java.util.Date ngayO = (java.util.Date) spinnerNgayO.getValue();

				Calendar calendar = Calendar.getInstance();
				calendar.setTime(ngayO);
				calendar.add(Calendar.DATE, soNgay);

				java.util.Date ngayTra = calendar.getTime();
				Date ngayTraSQL = new Date(ngayTra.getTime()); // Chuyển đổi sang java.sql.Date

				spinnerNgayTra.setValue(ngayTraSQL);



			}
		});




		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 325, 645, 214);
		add(tabbedPane);

		JPanel panelKhachHang = new JPanel();
		tabbedPane.addTab("Khách Hàng", null, panelKhachHang, null);
		panelKhachHang.setLayout(null);

		table = new JTable();
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
		jsp.setBounds(0, 0, 640, 186);
		panelKhachHang.add(jsp);

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

		JButton btThemPhong = new JButton("Thêm");
		btThemPhong.setFont(new Font("Arial", Font.BOLD, 16));
		btThemPhong.setBounds(10, 229, 92, 31);
		add(btThemPhong);

		JButton btSuaPhong = new JButton("Sửa");
		btSuaPhong.setFont(new Font("Arial", Font.BOLD, 16));
		btSuaPhong.setBounds(112, 229, 92, 31);
		add(btSuaPhong);

		JLabel lbTimMaDatPhong = new JLabel("Mã Đặt Phòng");
		lbTimMaDatPhong.setFont(new Font("Arial", Font.PLAIN, 16));
		lbTimMaDatPhong.setBounds(10, 290, 111, 25);
		add(lbTimMaDatPhong);

		txtTimMaDatPhong = new JTextField();
		txtTimMaDatPhong.setBounds(124, 294, 441, 20);
		add(txtTimMaDatPhong);
		txtTimMaDatPhong.setColumns(10);
		
		JButton btHuyPhong = new JButton("Hủy");
		btHuyPhong.setFont(new Font("Arial", Font.BOLD, 16));
		btHuyPhong.setBounds(214, 229, 92, 31);
		add(btHuyPhong);

		JButton btIn = new JButton("In");
		btIn.setFont(new Font("Arial", Font.BOLD, 16));
		btIn.setBounds(316, 229, 92, 31);
		add(btIn);

		JButton btThanhToan = new JButton("Thanh Toán");
		btThanhToan.setForeground(new Color(255, 128, 128));
		btThanhToan.setFont(new Font("Arial", Font.BOLD, 16));
		btThanhToan.setBounds(448, 229, 155, 31);
		add(btThanhToan);

		JButton btTim = new JButton("Tìm");
		btTim.setFont(new Font("Arial", Font.BOLD, 16));
		btTim.setBounds(575, 290, 80, 25);
		add(btTim);

		btThemPhong.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {






				// Lấy ngày ở từ spinner
				java.util.Date ngayO = (java.util.Date) spinnerNgayO.getValue();

				// Lấy ngày hiện tại
				java.util.Date ngayHienTai = new java.util.Date();
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(ngayHienTai);
				calendar.add(Calendar.DAY_OF_YEAR, -1);

				java.util.Date ngayTruocDo = calendar.getTime();


				;


				String trangThaiPhong = null;
				try {
					Connection conn = con.getConnection();

					// Chuẩn bị câu truy vấn PreparedStatement
					String query = "SELECT trangThaiPhong FROM Phong WHERE tenPhong = ?";
					PreparedStatement pstmt = conn.prepareStatement(query);
					pstmt.setNString(1, String.valueOf(cbbPhong.getSelectedItem()));

					// Thực thi câu truy vấn
					ResultSet rs = pstmt.executeQuery();

					rs.next();
					trangThaiPhong = rs.getString("trangThaiPhong");


					rs.close();
					pstmt.close();
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}


				// Kiểm tra ngày ở
				if(!trangThaiPhong.equalsIgnoreCase("Còn trống")) {
					Utils.MsgBox.alert(null, "Phòng này đã được đặt trước");
				}else if (ngayO.before(ngayTruocDo)) {
					JOptionPane.showMessageDialog(null, "Ngày ở không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				} else {






					if(Utils.MsgBox.comfirm(null, "Bạn đã có khách hàng?")) {

						PanelCustomerSelect panelcustomerselect = new PanelCustomerSelect();
						panelcustomerselect.setVisible(true);
						
						Connection conn = con.getConnection();
						try {
							Statement stm = conn.createStatement();
							String query = "SELECT manhanvien from nhanvien where tennhanvien like N'"+(String) cbbNguoiLap.getSelectedItem()+"'";
							ResultSet resultSet = stm.executeQuery(query);
							resultSet.next();
							setNguoiLap(resultSet.getInt("manhanvien"));
						}catch (SQLException e1) {
							e1.printStackTrace();
						}


						try {
							Statement stm = conn.createStatement();
							String query = "SELECT maphong from phong where tenphong like N'"+(String) cbbPhong.getSelectedItem()+"'";
							ResultSet resultSet = stm.executeQuery(query);
							resultSet.next();
							setPhong(resultSet.getInt("maphong"));
						}catch (SQLException e1) {
							e1.printStackTrace();
						}


						java.util.Date utilDate = (java.util.Date) spinnerNgayO.getValue();
						java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

						setNgayO(sqlDate);

						java.util.Date utilDate2 = (java.util.Date) spinnerNgayTra.getValue();
						java.sql.Date sqlDate2 = new java.sql.Date(utilDate2.getTime());

						setNgayTra(sqlDate2);


					}else {







						// Ngày ở hợp lệ, thực hiện hành động khác ở đây
						main.getPanelcustomerchild().setVisible(true);






						Connection conn = con.getConnection();
						try {
							Statement stm = conn.createStatement();
							String query = "SELECT manhanvien from nhanvien where tennhanvien like N'"+(String) cbbNguoiLap.getSelectedItem()+"'";
							ResultSet resultSet = stm.executeQuery(query);
							resultSet.next();
							setNguoiLap(resultSet.getInt("manhanvien"));
						}catch (SQLException e1) {
							e1.printStackTrace();
						}


						try {
							Statement stm = conn.createStatement();
							String query = "SELECT maphong from phong where tenphong like N'"+(String) cbbPhong.getSelectedItem()+"'";
							ResultSet resultSet = stm.executeQuery(query);
							resultSet.next();
							setPhong(resultSet.getInt("maphong"));
						}catch (SQLException e1) {
							e1.printStackTrace();
						}

						java.util.Date utilDate = (java.util.Date) spinnerNgayO.getValue();
						java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

						setNgayO(sqlDate);

						java.util.Date utilDate2 = (java.util.Date) spinnerNgayTra.getValue();
						java.sql.Date sqlDate2 = new java.sql.Date(utilDate2.getTime());

						setNgayTra(sqlDate2);




					}


				}
			}
		});
		
		
		
		
		
		//ấn nút in
		btIn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				//lấy mã đặt phòng
				int selectedRow = table_1.getSelectedRow();
		        int maDatPhong = 0;
		        java.util.Date ngayO;
		        if (selectedRow == -1) {
		            Utils.MsgBox.alert(null, "Bạn chưa chọn đặt phòng để in");
		        } else {
		            maDatPhong = (int) table_1.getValueAt(selectedRow, 0);
		            ngayO = (java.util.Date) table_1.getValueAt(selectedRow, 4);


		            
		         // Kiểm tra ngày hiện tại
		            java.util.Date ngayHienTai = new java.util.Date();
		            if (ngayHienTai.before(ngayO)) {
		                Utils.MsgBox.alert(null, "Đặt trước không thể in hóa đơn");
		            } else {
		                PanelPrintBill panelprintbill = new PanelPrintBill(maDatPhong);
		                panelprintbill.setVisible(true);
		            }
		            
		            
		        }
				
				

			}
		});
		
		
		
		
		
		
		//ấn vào nút hủy
		btHuyPhong.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        // TODO Auto-generated method stub
				Connection conn = con.getConnection();

		        // Lấy mã đặt phòng
		        int selectedRow = table_1.getSelectedRow();
		        int maDatPhong = 0;
		        java.util.Date ngayO;
		        if (selectedRow == -1) {
		            Utils.MsgBox.alert(null, "Bạn chưa chọn đặt phòng để hủy");
		        } else {
		            maDatPhong = (int) table_1.getValueAt(selectedRow, 0);
		            ngayO = (java.util.Date) table_1.getValueAt(selectedRow, 4);

		            // Kiểm tra ngày hiện tại
		            java.util.Date currentDate = new Date(System.currentTimeMillis());

		            
		            if (currentDate.compareTo(ngayO) >= 1) {
		                // Ngày hiện tại lớn hơn hoặc bằng ngày ở
//		            	System.out.println(currentDate.compareTo(ngayO));
//		            	System.out.println(ngayO);
//		            	System.out.println(currentDate);
		                Utils.MsgBox.alert(null, "Không thể hủy đặt phòng này vì phòng đang trong sử dụng hoặc đã hoàn tất thanh toán");
		            } else {
		                // Tiến hành hủy phòng
		                // TODO: Thực hiện hủy phòng ở đây
//		            	System.out.println(currentDate.compareTo(ngayO));
		            	
		            	
			        	//tìm mã phòng
			        	int maPhong = 0;
			        	String query = "SELECT maPhong FROM datphong WHERE madatphong = ?";
			        	try {
			        	    PreparedStatement statement = conn.prepareStatement(query);
			        	    statement.setInt(1, maDatPhong);

			        	    ResultSet resultSet = statement.executeQuery();
			        	    if (resultSet.next()) {
			        	        maPhong = resultSet.getInt("maPhong");
			        	    }

			        	    resultSet.close();
			        	    statement.close();
			        	} catch (SQLException e1) {
			        	    e1.printStackTrace();
			        	}
			        	
			        	
			        	
			        	//chuyển trạng thái của phòng
			        	try {
       	                 // Chuẩn bị câu truy vấn PreparedStatement
    	                    String query1 = "UPDATE Phong SET trangThaiPhong = ? WHERE maPhong = ?";
    	                    PreparedStatement pstmt = conn.prepareStatement(query1);
    	                    pstmt.setNString(1, "Còn trống");
    	                    pstmt.setInt(2, maPhong);
    	                    pstmt.executeUpdate();

    	                    pstmt.close();
			        	} catch (SQLException e1) {
			        	    e1.printStackTrace();
			        	}
		            	
		            	
		            	
		            	
		            	try {
		                	String deleteQuery = "delete from datphong where madatphong = "+maDatPhong;
				            PreparedStatement statement = conn.prepareStatement(deleteQuery);
				            statement.executeUpdate();
				            Utils.MsgBox.alert(null, "Đã hủy thành công");
			        	    statement.close();
						} catch (Exception e2) {
							// TODO: handle exception
						}

		            	
		            	
		            }
		        }
		    }
		});
		
		
		
		
		
		
		
		// ấn nút thanh toán
		btThanhToan.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				
				//lấy mã đặt phòng
				int selectedRow = table_1.getSelectedRow();
				int maDatPhong = 0;
				java.util.Date ngayO;
				if(selectedRow==-1) {
		    		Utils.MsgBox.alert(null, "Bạn chưa chọn đặt phòng để thanh toán");
		    	}else {
		    		maDatPhong = Integer.valueOf(table_1.getValueAt(selectedRow, 0).toString());
		    		ngayO = (java.util.Date) table_1.getValueAt(selectedRow, 4);
		    		String trangThai = table_1.getValueAt(selectedRow, 6).toString();
		    		
		    		
		    		if(trangThai.equalsIgnoreCase("Đã hoàn tất")) {
			    		Utils.MsgBox.alert(null, "Phòng này đã được thanh toán");
			    		return;
		    		}
		    		

		    		// Kiểm tra ngày hiện tại
		            java.util.Date ngayHienTai = new java.util.Date();
		            if (ngayHienTai.before(ngayO)) {
		                Utils.MsgBox.alert(null, "Đặt trước không thể thanh toán");
		            } else {
		                PanelPay panelpay = new PanelPay(maDatPhong);
		                panelpay.setVisible(true);
		            }

		    	}
				
				

				
				
				
			}
		});

		DefaultComboBoxModel<String> cbbModel = new DefaultComboBoxModel<>();
		List<String> tenNhanVienList = getTenNhanVienList();
		for (String tenNhanVien : tenNhanVienList) {
			cbbModel.addElement(tenNhanVien);
		}
		cbbNguoiLap.setModel(cbbModel);


		DefaultComboBoxModel<String> cbbPhongModel = new DefaultComboBoxModel<>();
		List<String> tenPhongList = getTenPhongList();
		for (String tenPhong : tenPhongList) {
			cbbPhongModel.addElement(tenPhong);
		}
		cbbPhong.setModel(cbbPhongModel);
		


		String selectedPhong = cbbPhong.getSelectedItem().toString();
		fillTenLoaiPhong(selectedPhong);

		cbbPhong.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String selectedPhong = cbbPhong.getSelectedItem().toString();
					fillTenLoaiPhong(selectedPhong);
				}
			}
		});







		// Truy vấn dữ liệu từ các bảng
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






		// Lắng nghe sự kiện chọn hàng trong JTable
		table_1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) { // Đảm bảo chỉ xử lý khi kết thúc quá trình chọn hàng
					int selectedRow = table_1.getSelectedRow();
					if (selectedRow != -1) { // Kiểm tra xem đã chọn hàng nào chưa
						// Lấy dữ liệu từ hàng được chọn
						int maDatPhong = (int) table_1.getValueAt(selectedRow, 0);
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










							String tenPhong = resultSet.getString("tenPhong");
							String tenLoaiPhong = resultSet.getString("tenLoaiPhong");
							String tenNhanVien = resultSet.getString("tenNhanVien");
							Date ngayO1 = resultSet.getDate("ngayBatDau");
							Date ngayTra1 = resultSet.getDate("ngayKetThuc");
							// Chuyển đổi từ java.util.Date sang java.time.LocalDate
							LocalDate ngayO1LocalDate = ngayO1.toLocalDate();
							LocalDate ngayTra1LocalDate = ngayTra1.toLocalDate();

							// Tính số ngày giữa hai ngày
							String soNgay = String.valueOf(ChronoUnit.DAYS.between(ngayO1LocalDate, ngayTra1LocalDate));



							cbbNguoiLap.setSelectedItem(tenNhanVien);
							cbbPhong.setSelectedItem(tenPhong);
							txtLoaiPhong.setText(tenLoaiPhong);
							dateModel.setValue(ngayO1);
							dateModel2.setValue(ngayTra1);
							cbbSoNgay.setSelectedItem(soNgay);




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



		// Tạo một menu danh sách
		JPopupMenu popupMenu = new JPopupMenu();
		JMenuItem menuItem1 = new JMenuItem("Thanh toán");
		JMenuItem menuItem2 = new JMenuItem("Cài dịch vụ");
		JMenuItem menuItem3 = new JMenuItem("Thêm thành viên");
		menuItem1.setIcon(new ImageIcon(PanelBooking.class.getResource("/Icon/cash.png")));
		menuItem2.setIcon(new ImageIcon(PanelBooking.class.getResource("/Icon/servicebell.png")));
		menuItem3.setIcon(new ImageIcon(PanelBooking.class.getResource("/Icon/userplus.png")));
		popupMenu.add(menuItem1);
		popupMenu.add(menuItem2);
		popupMenu.add(menuItem3);



		// Xử lý sự kiện nhấp chuột phải trên hàng
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					int row = table_1.rowAtPoint(e.getPoint());
					if (row >= 0 && row < table_1.getRowCount()) {
						table_1.setRowSelectionInterval(row, row);
						popupMenu.show(e.getComponent(), e.getX(), e.getY());
					}
				}
			}
		});


		menuItem3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table_1.getSelectedRow();
				if (row != -1) {
					int maDatPhong = (int) table_1.getValueAt(row, 0);

					Connection conn = con.getConnection();

					int maKhachHang = 0;
					int maLoai = 0;
					// Chuẩn bị câu truy vấn
					try {
						String query = "SELECT DatPhong.maKhachHang, KhachHang.maLoai " +
								"FROM DatPhong " +
								"JOIN KhachHang ON DatPhong.maKhachHang = KhachHang.maKhachHang " +
								"JOIN LoaiKhachHang ON KhachHang.maLoai = LoaiKhachHang.maLoai " +
								"WHERE maDatPhong = ?";
						PreparedStatement statement = conn.prepareStatement(query);
						statement.setInt(1, maDatPhong);
						ResultSet resultSet = statement.executeQuery();
						resultSet.next();
						maKhachHang = resultSet.getInt("maKhachHang");
						maLoai = resultSet.getInt("maLoai");
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

					if(maLoai==1) {
						Utils.MsgBox.alert(null, "Phòng này được đặt bởi khách hàng cá nhân");
					}else if(maLoai==2) {


						int maNhom = 0;
						try {
							String query = "SELECT maNhom FROM NhomKhachHang WHERE maKhachHang = ?";
							PreparedStatement statement = conn.prepareStatement(query);
							statement.setInt(1, maKhachHang);
							ResultSet resultSet = statement.executeQuery();
							resultSet.next();
							maNhom = resultSet.getInt("maNhom");
						} catch (SQLException e1) {
							e1.printStackTrace();
						}

						PanelMemberGroup panelmembergroup = new PanelMemberGroup(maNhom);
						panelmembergroup.setVisible(true);


					}


				}
			}
		});




	}
	private void fillTenLoaiPhong(String selectedPhong) {
		try {
			Utils.ConnectDatabase con = new ConnectDatabase();
			Connection conn = con.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT LoaiPhong.tenLoaiPhong FROM Phong INNER JOIN LoaiPhong ON Phong.maLoaiPhong = LoaiPhong.maLoaiPhong WHERE Phong.tenPhong = ?");
			stmt.setString(1, selectedPhong);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				String tenLoaiPhong = rs.getString("tenLoaiPhong");
				txtLoaiPhong.setText(tenLoaiPhong);
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private List<String> getTenPhongList() {
		Utils.ConnectDatabase con = new ConnectDatabase();
		List<String> tenPhongList = new ArrayList<>();

		try {
			Connection conn = con.getConnection();
			Statement stm = conn.createStatement();

			String query = "SELECT tenPhong FROM Phong";
			ResultSet rs = stm.executeQuery(query);

			while (rs.next()) {
				String tenPhong = rs.getString("tenPhong");
				tenPhongList.add(tenPhong);
			}

			rs.close();
			stm.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tenPhongList;
	}


	private List<String> getTenNhanVienList() {
		List<String> tenNhanVienList = new ArrayList<>();
		Utils.ConnectDatabase con = new ConnectDatabase();

		try {
			Connection conn = con.getConnection(); // Sử dụng phương thức getConnection() đã có sẵn
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT tenNhanVien FROM NhanVien");

			while (rs.next()) {
				String tenNhanVien = rs.getString("tenNhanVien");
				tenNhanVienList.add(tenNhanVien);
			}

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return tenNhanVienList;
	}
}
