package UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Utils.ConnectDatabase;

public class PanelBill extends JPanel {
	private JTable table;

	/**
	 * Create the panel.
	 */
	public PanelBill() {
		Utils.ConnectDatabase con = new ConnectDatabase();
		setBounds(0, 0, 665, 550);
		setLayout(null);

		JLabel lbTitle = new JLabel("Hóa Đơn");
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setForeground(new Color(85, 140, 255));
		lbTitle.setFont(new Font("Segoe UI", Font.BOLD, 42));
		lbTitle.setBounds(169, 11, 332, 89);
		add(lbTitle);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Mã Hóa Đơn","Mã Đặt Phòng","Ngày Lập","Người Lập","Tổng Tiền"
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
		jsp.setSize(645, 415);
		jsp.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		jsp.setLocation(10, 125);
		add(jsp);









		//fill table
		try{


			Connection conn = con.getConnection();


			// Truy vấn để lấy dữ liệu từ bảng HoaDon
			String query = "SELECT HoaDon.maHoaDon, HoaDon.maDatPhong, HoaDon.ngayLapHoaDon, NhanVien.tenNhanVien, HoaDon.tongTien "
					+ "FROM HoaDon "
					+ "INNER JOIN NhanVien ON HoaDon.maNhanVien = NhanVien.maNhanVien";
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();

			// Tạo DefaultTableModel và định nghĩa các cột
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setRowCount(0);
			
		    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
		    table.setRowSorter(sorter);

			// Đổ dữ liệu từ ResultSet vào DefaultTableModel
			while (resultSet.next()) {
				int maHoaDon = resultSet.getInt("maHoaDon");
				int maDatPhong = resultSet.getInt("maDatPhong");
				Date ngayLap = resultSet.getDate("ngayLapHoaDon");
				String tenNhanVien = resultSet.getString("tenNhanVien");
				double tongTien = resultSet.getDouble("tongTien");

				// Thêm dòng dữ liệu vào DefaultTableModel
				model.addRow(new Object[]{maHoaDon, maDatPhong, ngayLap, tenNhanVien, tongTien});
			}

			// Tạo JTable và gán DefaultTableModel vào

			table.setModel(model);
//			System.out.println("hellow");

			// Hiển thị JTable trong JFrame hoặc JPanel theo nhu cầu của bạn
		} catch (SQLException e) {
			e.printStackTrace();
		}













	}
}
