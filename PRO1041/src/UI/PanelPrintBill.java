package UI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class PanelPrintBill extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PanelPrintBill frame = new PanelPrintBill();
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
	public PanelPrintBill() {
    	addWindowListener(new WindowAdapter() {
    		@Override
    		public void windowClosing(WindowEvent e) {
    			dispose();
    		}
    	});
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 625);
		setLocationRelativeTo(contentPane);
		setUndecorated(true);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lbTitle = new JLabel("Hóa Đơn");
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setForeground(new Color(85, 140, 255));
		lbTitle.setFont(new Font("Segoe UI", Font.BOLD, 36));
		lbTitle.setBounds(0, 0, 187, 63);
		contentPane.add(lbTitle);
		
		JLabel lbName = new JLabel("Seafront Hotel");
		lbName.setForeground(new Color(255, 128, 128));
		lbName.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lbName.setBounds(25, 74, 134, 27);
		contentPane.add(lbName);
		
		JLabel lbContent1 = new JLabel("Người ta gọi đây là view triệu đô");
		lbContent1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbContent1.setBounds(35, 100, 247, 27);
		contentPane.add(lbContent1);
		
		JLabel lbContent2 = new JLabel("Vậy thì ai mới là người dư dã");
		lbContent2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbContent2.setBounds(35, 128, 214, 27);
		contentPane.add(lbContent2);
		
		JLabel lbMHD = new JLabel("Mã:");
		lbMHD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbMHD.setBounds(295, 36, 145, 27);
		contentPane.add(lbMHD);
		
		JLabel lbNgay = new JLabel("Ngày:");
		lbNgay.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbNgay.setBounds(295, 74, 145, 27);
		contentPane.add(lbNgay);
		
		JTable table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Mục","Số Lượng/Số Ngày","Đơn Giá","Thành Tiền"
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

        // Áp dụng Renderer cho tất cả các ô trong bảng
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(renderer);
        }
		
		table.setBounds(10, 125, 645, 415);
		JScrollPane jsp = new JScrollPane(table);
		jsp.setSize(430, 242);
        jsp.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		jsp.setLocation(10, 166);
		contentPane.add(jsp);
		
		JLabel lbPay = new JLabel("Thông tin thanh toán");
		lbPay.setForeground(new Color(255, 128, 128));
		lbPay.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lbPay.setBounds(10, 450, 187, 27);
		contentPane.add(lbPay);
		
		JLabel lbContent3 = new JLabel("Ngân Hàng MB");
		lbContent3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbContent3.setBounds(25, 478, 110, 27);
		contentPane.add(lbContent3);
		
		JLabel lbContent4 = new JLabel("Tên tài khoản: NGUYEN THANH TINH");
		lbContent4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbContent4.setBounds(25, 505, 234, 27);
		contentPane.add(lbContent4);
		
		JLabel lblSTiKhon = new JLabel("Số tài khoản: 0550767799967");
		lblSTiKhon.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSTiKhon.setBounds(25, 533, 197, 27);
		contentPane.add(lblSTiKhon);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(PanelPrintBill.class.getResource("/Icon/QRResize.jpg")));
		lblNewLabel.setBounds(295, 458, 125, 125);
		contentPane.add(lblNewLabel);
		
		JButton btThoat = new JButton("Thoát");
		btThoat.setBounds(245, 591, 89, 23);
		contentPane.add(btThoat);
		
		JButton btIn = new JButton("In");
		btIn.setBounds(351, 591, 89, 23);
		contentPane.add(btIn);
		
		JLabel lbTongTien = new JLabel("Tổng Tiền:");
		lbTongTien.setForeground(new Color(255, 128, 128));
		lbTongTien.setFont(new Font("Segoe UI", Font.BOLD, 18));
		lbTongTien.setBounds(212, 419, 95, 27);
		contentPane.add(lbTongTien);
		
		JLabel lbSoTongTien = new JLabel("0"+" Đ");
		lbSoTongTien.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbSoTongTien.setBounds(317, 420, 125, 27);
		contentPane.add(lbSoTongTien);
		
		btThoat.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dispose();
			}
		});
	}
}
