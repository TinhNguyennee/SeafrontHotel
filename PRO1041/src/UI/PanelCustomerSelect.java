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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

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

public class PanelCustomerSelect extends JFrame {

	private JPanel contentPane;
	private JTable table;

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
					PanelCustomerSelect frame = new PanelCustomerSelect();
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
	public PanelCustomerSelect() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
		Utils.ConnectDatabase con = new ConnectDatabase();
		//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 400);
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
		lbTitle.setBounds(195, 6, 159, 38);
		contentPane.add(lbTitle);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Mã Khách Hàng","Họ Và Tên","Căn Cước Công Dân","Số Điện Thoại","Email"
				}
				));
		table.setBounds(10, 55, 530, 300);
		contentPane.add(table);


		// Tùy chỉnh giao diện table
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
		jsp.setLocation(10, 80);
		jsp.setSize(530, 275);
		jsp.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		getContentPane().add(jsp);


		JButton btThoat = new JButton("Thoát");
		btThoat.setBounds(451, 366, 89, 23);
		contentPane.add(btThoat);

		JButton btOK = new JButton("OK");
		btOK.setBounds(352, 366, 89, 23);
		contentPane.add(btOK);





		//fill table
		try {
			Connection conn = con.getConnection();
			String sql = "SELECT maKhachHang, tenKhachHang, CCCD, soDienThoai, email " +
					"FROM KhachHang " +
					"WHERE maKhachHang NOT IN (SELECT maKhachHang FROM DatPhong)";

			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);

			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);

			while(resultSet.next()) {
				int maKhachHang = resultSet.getInt("maKhachHang");
				String tenKhachHang = resultSet.getString("tenKhachHang");
				String CCCD = resultSet.getString("CCCD");
				String soDienThoai = resultSet.getString("soDienThoai");
				String email = resultSet.getString("email");

				Vector<Object> row = new Vector<>();
				row.add(maKhachHang);
				row.add(tenKhachHang);
				row.add(CCCD);
				row.add(soDienThoai);
				row.add(email);
				model.addRow(row);
			}




			resultSet.close();
			statement.close();
		} catch (Exception e2) {
			// TODO: handle exception
		}







		//ấn vào nút thoát
		btThoat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});









		//ấn vào nút OK
		btOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Connection conn = con.getConnection();
				int selectedRow = table.getSelectedRow();

				//lấy mã khách hàng
				if (selectedRow != -1) {
					int maKhachHang = (int) table.getValueAt(selectedRow, 0);
					// Sử dụng mã khách hàng ở đây để thực hiện các hành động khác









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
				Utils.MsgBox.alert(null, "Bạn chưa chọn khách hàng");
			}
		}
	});

















}
}
