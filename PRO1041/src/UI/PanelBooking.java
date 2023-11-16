package UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class PanelBooking extends JPanel {
	private JTextField txtLoaiPhong;
	private JTextField txtNgayO;
	private JTextField txtNgayTra;
	private JTable table;
	private JTable table_1;
	private JTextField txtTimMaDatPhong;

	/**
	 * Create the panel.
	 */
	public PanelBooking(Main main) {
		
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
		
		txtNgayO = new JTextField();
		txtNgayO.setColumns(10);
		txtNgayO.setBounds(435, 104, 182, 20);
		add(txtNgayO);
		
		JLabel lbNgayTra = new JLabel("Ngày Trả");
		lbNgayTra.setFont(new Font("Arial", Font.PLAIN, 16));
		lbNgayTra.setBounds(325, 136, 92, 25);
		add(lbNgayTra);
		
		txtNgayTra = new JTextField();
		txtNgayTra.setEditable(false);
		txtNgayTra.setColumns(10);
		txtNgayTra.setBounds(435, 140, 182, 20);
		add(txtNgayTra);
		
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
		
		JButton btThemPhong = new JButton("Thêm");
		btThemPhong.setFont(new Font("Arial", Font.BOLD, 16));
		btThemPhong.setBounds(10, 229, 92, 31);
		add(btThemPhong);
		
		JButton btSuaPhong = new JButton("Sửa");
		btSuaPhong.setFont(new Font("Arial", Font.BOLD, 16));
		btSuaPhong.setBounds(134, 229, 92, 31);
		add(btSuaPhong);
		
		JLabel lbTimMaDatPhong = new JLabel("Mã Đặt Phòng");
		lbTimMaDatPhong.setFont(new Font("Arial", Font.PLAIN, 16));
		lbTimMaDatPhong.setBounds(10, 290, 111, 25);
		add(lbTimMaDatPhong);
		
		txtTimMaDatPhong = new JTextField();
		txtTimMaDatPhong.setBounds(124, 294, 441, 20);
		add(txtTimMaDatPhong);
		txtTimMaDatPhong.setColumns(10);
		
		JButton btIn = new JButton("In");
		btIn.setFont(new Font("Arial", Font.BOLD, 16));
		btIn.setBounds(262, 229, 92, 31);
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
				// TODO Auto-generated method stub
				main.getPanelcustomerchild().setVisible(true);
			}
		});
		
		btIn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				main.getPanelprintbill().setVisible(true);
			}
		});
		
		btThanhToan.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				main.getPanelpay().setVisible(true);
			}
		});
		
		
	}
}
