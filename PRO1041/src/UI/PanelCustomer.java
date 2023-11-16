package UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class PanelCustomer extends JPanel {
	private JTextField txtEmail;
	private JTextField txtSDT;
	private JTextField txtNgaySinh;
	private JTextField txtHoVaTen;
	private JTextField txtCCCD;
	private JTextField txtTimKhachHang;

	/**
	 * Create the panel.
	 */
	public PanelCustomer() {
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
		
		txtNgaySinh = new JTextField();
		txtNgaySinh.setColumns(10);
		txtNgaySinh.setBounds(435, 187, 182, 20);
		add(txtNgaySinh);
		
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
            			"Mã Khách Hàng","Họ Và Tên","Căn Cước Công Dân","Số Điện Thoại","Email","Quốc Tịch","Mã Đặt Phòng"
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
		cbbGioiTinh.setModel(new DefaultComboBoxModel(new String[] {"Nam", "Nữ", "Khác"}));
		cbbGioiTinh.setBounds(112, 222, 182, 22);
		add(cbbGioiTinh);
		
		JLabel lbLoaiKhach = new JLabel("Loại Khách");
		lbLoaiKhach.setFont(new Font("Arial", Font.PLAIN, 16));
		lbLoaiKhach.setBounds(325, 219, 112, 25);
		add(lbLoaiKhach);
		
		JComboBox cbbLoaiKhach = new JComboBox();
		cbbLoaiKhach.setBounds(435, 219, 182, 22);
		add(cbbLoaiKhach);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Việt Nam", "Ngoại Quốc"}));
		comboBox.setBounds(435, 150, 182, 22);
		add(comboBox);
		
	}
}
