package UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.Comparator;

public class test {
    public static void main(String[] args) {
        // Tạo JTable và DefaultTableModel
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);

        // Thêm dữ liệu vào JTable
        model.addColumn("Column 1");
        model.addColumn("Column 2");
        model.addColumn("Column 3");
        model.addRow(new Object[]{5, 3, 8});
        model.addRow(new Object[]{2, 9, 1});
        model.addRow(new Object[]{7, 6, 4});

        // Tạo JFrame và hiển thị JTable
        JFrame frame = new JFrame();
        frame.setLayout(new BorderLayout());
        frame.add(new JScrollPane(table), BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 200);
        frame.setVisible(true);

        // Sắp xếp cột đầu theo thứ tự từ lớn đến bé
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(model);
        table.setRowSorter(sorter);
        sorter.setComparator(0, Comparator.reverseOrder());
        sorter.sort();
    }
}