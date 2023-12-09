package UI;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Components.BlankPlotChart;
import Components.Chart;
import Components.ModelChart;
import Components.Model_Card;
import Components.StatusType;
import DAO.PhongDAO;
import DAO.ThongKeDAO;
import Entity.Phong;
import Swing.CellStatus;
import Swing.ScrollBar;
import Utils.XImage;

public class Form_Home extends javax.swing.JPanel {
	List<Phong> lstPhong = new ArrayList<>();

	public Form_Home() {
		initComponents();
		initData();
		// add row table
//		spTable.setVerticalScrollBar(new ScrollBar());
//		spTable.getVerticalScrollBar().setBackground(Color.WHITE);
//		spTable.getViewport().setBackground(Color.WHITE);
//		JPanel p = new JPanel();
//		p.setBackground(Color.WHITE);
//		spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
		chart.addLegend("Doanh Thu", new Color(142, 142, 250));
		String[] months = {
			    "Jan", "Feb", "Mar", "Apr", "May", "Jun",
			    "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
			};
		List<Object[]> lst = new ThongKeDAO().getDoanhThuThang(2023);

		for (Object[] objects : lst) {
			double[] arr = {Double.valueOf(String.valueOf(objects[1])), 2, 4};
			chart.addData(new ModelChart(months[(int)objects[0] - 1], arr));
		}
        
		fillToTable();
	}

	private void initData() {
		ThongKeDAO modelDAO = new ThongKeDAO();
		try {
			long tongDoanhThu2023 = modelDAO.tongDoanhThuNam(2023);
			long tongDoanhThu2022 = modelDAO.tongDoanhThuNam(2022);
			DecimalFormat decimalFormat = new DecimalFormat("#.##");
			float doanhThuTang = (((float) tongDoanhThu2023 - (float) tongDoanhThu2022) / (float) tongDoanhThu2023)
					* 100;

			card1.setData(new Model_Card(
					new ImageIcon(new ImageIcon(getClass().getResource("/Icon/profit.png")).getImage()
							.getScaledInstance(25, 25, 0)),
					"Doanh Thu", String.valueOf(tongDoanhThu2023) + " VNĐ",
					"Increased by " + decimalFormat.format(doanhThuTang) + "%"));

			int khachHang2023 = modelDAO.tongKhachHang(2023);
			int khachHang2022 = modelDAO.tongKhachHang(2022);
			float khachHangTang = (((float) khachHang2023 - (float) khachHang2022) / (float) khachHang2023) * 100;
			card2.setData(new Model_Card(
					new ImageIcon(new ImageIcon(getClass().getResource("/Icon/8.png")).getImage().getScaledInstance(25,
							25, 0)),
					"Khách Hàng", String.valueOf(khachHang2023),
					"Increased by " + decimalFormat.format(khachHangTang) + "%"));

			card3.setData(
					new Model_Card(
							new ImageIcon(new ImageIcon(getClass().getResource("/Icon/stock.png")).getImage()
									.getScaledInstance(25, 25, 0)),
							"Dịch Vụ", String.valueOf(modelDAO.tongDichVu()), ""));
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void fillToTable() {
		DefaultTableModel tblModel = (DefaultTableModel) table.getModel();
		tblModel.setRowCount(0);
		lstPhong = new PhongDAO().selectAll();
		StatusType st = null;
		for (Phong p : lstPhong) {
			if (p.getTrangThaiPhong().equals("Còn trống")) {
				st = StatusType.VACANCY;
			} else {
				st = StatusType.RESERVATED;
			}
			tblModel.addRow(new Object[] { p.getMaPhong(), p.getTenPhong(), st });
		}
	}

	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		panel = new javax.swing.JLayeredPane();
		card1 = new Components.Card();
		card2 = new Components.Card();
		card3 = new Components.Card();
		panelBorder1 = new Swing.PanelBorder();
		jLabel1 = new javax.swing.JLabel();
		spTable = new JPanel();
		table = new Swing.Table();
		chart = new Chart();
		blankPlotChart = new BlankPlotChart();
        panelLegend = new javax.swing.JPanel();

		setBackground(new java.awt.Color(242, 242, 242));

		panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

		card1.setColor1(new java.awt.Color(142, 142, 250));
		card1.setColor2(new java.awt.Color(123, 123, 245));
		card1.setPreferredSize(new java.awt.Dimension(300, 150));
		panel.add(card1);
		

		card2.setColor1(new java.awt.Color(186, 123, 247));
		card2.setColor2(new java.awt.Color(167, 94, 236));
		card2.setPreferredSize(new java.awt.Dimension(300, 150));
		panel.add(card2);
		
		card3.setColor1(new java.awt.Color(241, 208, 62));
		card3.setColor2(new java.awt.Color(211, 184, 61));
		card3.setPreferredSize(new java.awt.Dimension(300, 150));
		panel.add(card3);

		panelLegend.setOpaque(false);
        panelLegend.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
                .addGap(20, 20, 20))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(50, 50, 50)
                    .addComponent(panelLegend, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
                    .addGap(50, 50, 50)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap(403, Short.MAX_VALUE)
                    .addComponent(panelLegend, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(62, 62, 62)))
        );
		
		
	}// </editor-fold>//GEN-END:initComponents

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private Components.Card card1;
	private Components.Card card2;
	private Components.Card card3;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLayeredPane panel;
	private Swing.PanelBorder panelBorder1;
	private JPanel spTable;
	private Swing.Table table;
	private Chart chart;
	private BlankPlotChart blankPlotChart;
    private javax.swing.JPanel panelLegend;
	// End of variables declaration//GEN-END:variables
}
