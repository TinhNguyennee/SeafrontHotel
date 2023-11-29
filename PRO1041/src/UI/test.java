package UI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class test {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Table Right Click Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Tạo một bảng với dữ liệu mẫu
        String[] columnNames = {"Column 1", "Column 2", "Column 3"};
        Object[][] data = {
                {"Data 1", "Data 2", "Data 3"},
                {"Data 4", "Data 5", "Data 6"},
                {"Data 7", "Data 8", "Data 9"}
        };
        JTable table = new JTable(data, columnNames);

        // Tạo một menu danh sách
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem menuItem = new JMenuItem("Box List");
        popupMenu.add(menuItem);

        // Xử lý sự kiện nhấp chuột phải trên hàng
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    int row = table.rowAtPoint(e.getPoint());
                    if (row >= 0 && row < table.getRowCount()) {
                        table.setRowSelectionInterval(row, row);
                        popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
            }
        });
        frame.getContentPane().setLayout(null);

        // Đặt bảng vào trong một thanh cuộn
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, 386, 263);
        frame.getContentPane().add(scrollPane);
        
        
        SpinnerDateModel dateModel = new SpinnerDateModel();
        JSpinner spinner = new JSpinner(dateModel);
        spinner.setEditor(new JSpinner.DateEditor(spinner, "dd/MM/yyyy"));
        spinner.setEnabled(false); // Không cho phép chỉnh sửa
        spinner.setBounds(100, 100, 100, 35);

        frame.setSize(400, 300);
        frame.setVisible(true);
    }
}