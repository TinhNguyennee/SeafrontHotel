package UI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class PanelPay extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PanelPay frame = new PanelPay();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			    try {
			    	  UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
			    	} catch (Exception e) {
			    	  // handle exception
			    	}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PanelPay() {
    	addWindowListener(new WindowAdapter() {
    		@Override
    		public void windowClosing(WindowEvent e) {
    			dispose();
    		}
    	});
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
		
		JLabel lblContent1 = new JLabel("Mã đặt phòng: ");
		lblContent1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblContent1.setBounds(10, 111, 209, 27);
		contentPane.add(lblContent1);
		
		JLabel lbContent2 = new JLabel("Phòng: ");
		lbContent2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbContent2.setBounds(10, 149, 209, 27);
		contentPane.add(lbContent2);
		
		JLabel lbContent3 = new JLabel("Loại phòng: ");
		lbContent3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbContent3.setBounds(10, 187, 209, 27);
		contentPane.add(lbContent3);
		
		JLabel lbContent4 = new JLabel("Đơn giá: ");
		lbContent4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbContent4.setBounds(10, 223, 209, 27);
		contentPane.add(lbContent4);
		
		JLabel lbContent5 = new JLabel("Ngày đến:");
		lbContent5.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbContent5.setBounds(229, 111, 209, 27);
		contentPane.add(lbContent5);
		
		JLabel lbContent6 = new JLabel("Ngày đi:");
		lbContent6.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbContent6.setBounds(229, 149, 209, 27);
		contentPane.add(lbContent6);
		
		JLabel lbContent7 = new JLabel("Số ngày: ");
		lbContent7.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbContent7.setBounds(229, 187, 209, 27);
		contentPane.add(lbContent7);
		
		JLabel lbContent8 = new JLabel("Thành tiền: ");
		lbContent8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbContent8.setBounds(229, 223, 209, 27);
		contentPane.add(lbContent8);
		
		JLabel lbContent9 = new JLabel("Dịch vụ:");
		lbContent9.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbContent9.setBounds(120, 280, 209, 27);
		contentPane.add(lbContent9);
		
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
		
		btThoat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
		
	}
}
