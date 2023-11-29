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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import Utils.ConnectDatabase;
import javax.swing.JButton;

public class PanelMain extends JPanel {
	/**
	 * Create the panel.
	 */
	public PanelMain() {
		
		Utils.ConnectDatabase con = new ConnectDatabase();
		
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
        cbbSort.setModel(new DefaultComboBoxModel<>(new String[] {"Tất cả", "Phòng đơn", "Phòng đôi", "Phòng VIP"}));

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
            		"Số thứ tự", "Số phòng", "Loại phòng", "Trạng thái"
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
            jsp.setBorder(new LineBorder(new Color(0, 0, 0), 0));
		jsp.setBounds(10, 312, 645, 227);
		add(jsp);
		add(cbbSort);
		

		int countConTrong = 0; // Biến đếm số phòng "Còn trống"
		int countDaDat = 0; // Biến đếm số phòng "Đã đặt"
		

        DefaultTableModel model = (DefaultTableModel) table.getModel();
		
		try {
            Connection conn = con.getConnection();
            Statement stmt = conn.createStatement();
            
            String query = "SELECT ROW_NUMBER() OVER (ORDER BY p.maPhong) AS STT, p.tenPhong, lp.tenLoaiPhong, p.trangThaiPhong " +
                           "FROM Phong p " +
                           "JOIN LoaiPhong lp ON p.maLoaiPhong = lp.maLoaiPhong";
            ResultSet rs = stmt.executeQuery(query);
            
            while (rs.next()) {
                int stt = rs.getInt("STT");
                String tenPhong = rs.getString("tenPhong");
                String tenLoaiPhong = rs.getString("tenLoaiPhong");
                String trangThaiPhong = rs.getString("trangThaiPhong");
                
                

                model.addRow(new Object[] {stt, tenPhong, tenLoaiPhong, trangThaiPhong});
                
                if(trangThaiPhong.equalsIgnoreCase("Còn trống"))countConTrong++;
                else if(trangThaiPhong.equalsIgnoreCase("Đã đặt"))countDaDat++;
            
                
                
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		
		
		
		
		
		
		card.getLbSoPhongTrong().setText(String.valueOf(countConTrong));
		card2.getLbSoPhongDangThue().setText(String.valueOf(countDaDat));
		

		
		   card.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	        		boolean isClicked = false;
	                isClicked = !isClicked;
	                sortTable(table, model, isClicked);
	            }
	        });
		   
		   card2.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	        		boolean isClicked = false;
	                isClicked = !isClicked;
	                sortTable2(table, model, isClicked);
	            }
	        });
		   
		   ItemListener itemListener = new ItemListener() {
			    public void itemStateChanged(ItemEvent itemEvent) {
			        if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
			            // Thực hiện sắp xếp bảng dựa trên giá trị đã chọn
			            sortTableByLoaiPhong(cbbSort.getSelectedItem().toString(),table,model);
			        }
			    }
			};
			
			cbbSort.addItemListener(itemListener);
			

		
	}
	private void sortTableByLoaiPhong(String loaiPhong, JTable table, DefaultTableModel model) {
	    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
	    table.setRowSorter(sorter);
	    
	    List<RowFilter<Object, Object>> filters = new ArrayList<>();
	    
	    if (!loaiPhong.equals("Tất cả")) {
	        RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
	            public boolean include(Entry<? extends Object, ? extends Object> entry) {
	                String value = entry.getStringValue(2); // Vị trí cột "Loại Phòng" là 2
//	                System.out.println(entry.getStringValue(2));
	                return value.equalsIgnoreCase(loaiPhong);
	            }
	        };
	        filters.add(filter);
	    }
	    
	    sorter.setRowFilter(RowFilter.andFilter(filters));
	}
	
	private void sortTable(JTable table, DefaultTableModel model, boolean isClicked) {
	    TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
	    table.setRowSorter(sorter);
	    
	    List<RowFilter<Object, Object>> filters = new ArrayList<>();
	    
	    if (isClicked) {
	        RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
	            public boolean include(Entry<? extends Object, ? extends Object> entry) {
	                String status = entry.getStringValue(3); // Vị trí cột "Trạng Thái" là 3
	                return status.equalsIgnoreCase("Còn trống");
	            }
	        };
	        filters.add(filter);
	    }
	    
	    sorter.setRowFilter(RowFilter.andFilter(filters));
	}
	private void sortTable2(JTable table, DefaultTableModel model, boolean isClicked) {
		TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
		table.setRowSorter(sorter);
		
		List<RowFilter<Object, Object>> filters = new ArrayList<>();
		
		if (isClicked) {
			RowFilter<Object, Object> filter = new RowFilter<Object, Object>() {
				public boolean include(Entry<? extends Object, ? extends Object> entry) {
					String status = entry.getStringValue(3); // Vị trí cột "Trạng Thái" là 3
					return status.equalsIgnoreCase("Đã đặt");
				}
			};
			filters.add(filter);
		}
		
		sorter.setRowFilter(RowFilter.andFilter(filters));
	}
}
