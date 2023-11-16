package UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class PanelMain extends JPanel {

	/**
	 * Create the panel.
	 */
	public PanelMain() {
		
		
		setBounds(0, 0, 665, 550);
		setLayout(null);
		
		JLabel lbTitle = new JLabel("Seafront Hotel");
		lbTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lbTitle.setForeground(new Color(85, 140, 255));
		lbTitle.setFont(new Font("Monotype Corsiva", Font.BOLD, 42));
		lbTitle.setBounds(169, 11, 332, 89);
		add(lbTitle);
		
		Card card = new Card();
		card.setLayout(null);
		card.setBounds(52, 118, 200, 125);
		add(card);
		
		Card2 card2 = new Card2();
		card2.setLayout(null);
		card2.setBounds(393, 118, 200, 125);
		add(card2);
		
        JComboBox<String> cbbSort = new JComboBox<>();
        cbbSort.setBounds(10, 276, 129, 25);
        cbbSort.setModel(new DefaultComboBoxModel<>(new String[] {"Tất cả", "Phòng đơn", "Phòng Đôi", "Phòng VIP"}));

        // Tùy chỉnh giao diện
        cbbSort.setBackground(Color.WHITE);
        cbbSort.setForeground(Color.BLACK);
        cbbSort.setFont(new Font("Arial", Font.PLAIN, 12));
        cbbSort.setRenderer(new DefaultListCellRenderer() {
            public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component renderer = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (renderer instanceof JLabel && value instanceof String) {
                    ((JLabel) renderer).setIcon(new ImageIcon("icon.png")); // Đường dẫn đến biểu tượng
                }
                return renderer;
            }
        });
        cbbSort.setPreferredSize(new Dimension(200, 25));
		
        JTable table = new JTable(new DefaultTableModel(
            	new Object[][] {
            	},
            	new String[] {
            		"S\u1ED1 Th\u1EE9 T\u1EF1", "Kh\u00E1ch H\u00E0ng", "Lo\u1EA1i Ph\u00F2ng", "S\u1ED1 Ph\u00F2ng", "Tr\u1EA1ng Th\u00E1i"
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
            jsp.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		jsp.setBounds(10, 312, 645, 227);
		add(jsp);
		add(cbbSort);
	}
}
