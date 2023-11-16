package UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class PanelService extends JPanel {
	private JTextField txtDonGia;
	private JTextField txtMaDatPhong;
	private JTextField txtTenPhong;
	private JTextField txtTimMaDichVu;
	private JTable table;
	private JTable table_1;

	/**
	 * Create the panel.
	 */
	public PanelService() {
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
				"Mã Dịch Vụ","Tên Dịch Vụ","Số Lượng","Đơn Giá","Thành Tiền","Mã Đặt Phòng"
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
				"Mã Đặt Phòng","Phòng","Loại Phòng","Người Lập","Trạng Thái"
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
		
		JComboBox cbbLoaiPhong = new JComboBox();
		cbbLoaiPhong.setBounds(112, 186, 182, 22);
		add(cbbLoaiPhong);
		
		JComboBox cbbDichVu = new JComboBox();
		cbbDichVu.setBounds(435, 114, 182, 22);
		add(cbbDichVu);
	}
}
