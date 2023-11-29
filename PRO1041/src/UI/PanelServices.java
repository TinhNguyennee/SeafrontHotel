/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI;

import Swing.ScrollBar;
import Utils.MsgBox;
import Utils.XDate;
import Utils.XImage;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import DAO.DichVuDAO;
import DAO.LoaiPhongDAO;
import DAO.NhanVienDAO;
import Entity.DichVu;
import Entity.LoaiPhong;
import Entity.NhanVien;

/**
 *
 * @author NAMVAAU
 */
public class PanelServices extends javax.swing.JPanel {
	DefaultTableModel tblModel;
	List<DichVu> lstDichVu = new ArrayList<>();
	int index = 0;

	/**
	 * Creates new form Room
	 */
	public PanelServices() {
		initComponents();
		// add row table
		spTable.setVerticalScrollBar(new ScrollBar());
		spTable.getVerticalScrollBar().setBackground(Color.WHITE);
		spTable.getViewport().setBackground(Color.WHITE);
		JPanel p = new JPanel();
		p.setBackground(Color.WHITE);
		spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
		initTable();
		DBFillToList();
		setSelected(0);
		initIcon();
	}

	private void initIcon() {
		btnFirst.setIcon(XImage.read("button-round-previous-icon.png", 25, 25));
		btnLast.setIcon(XImage.read("button-round-next-icon.png", 25, 25));
		btnNext.setIcon(XImage.read("button-round-fast-forward-icon.png", 25, 25));
		btnPrevious.setIcon(XImage.read("button-round-fast-backward-icon.png", 25, 25));
	}

	void previous() {
		try {
			if (index == 0) {
				index = lstDichVu.size();
			}
			index--;
			setSelected(index);
		} catch (Exception e) {
		}
	}

	void next() {
		try {
			if (index == lstDichVu.size() - 1) {
				index = -1;
			}
			index++;
			setSelected(index);
		} catch (Exception e) {
		}
	}

	void first() {
		try {
			index = 0;
			setSelected(index);
		} catch (Exception e) {
		}
	}

	void last() {
		try {
			index = lstDichVu.size() - 1;
			setSelected(index);
		} catch (Exception e) {
		}
	}

	private void setSelected(int index) {
		try {
			table.setRowSelectionInterval(index, index);
			showDetail(index);
		} catch (Exception e) {

		}
	}

	private void showDetail(int index) {
		DichVu dv = lstDichVu.get(index);
		txtMaDichVu.setText(String.valueOf(dv.getMaDichVu()));
		txtTenDichVu.setText(dv.getTenDichVu());
		txtDonGia.setText(String.valueOf(dv.getDonGia()));
		txtMaDichVu.setEditable(false);
	}

	private void clearForm() {
		txtMaDichVu.setText("");
		txtTenDichVu.setText("");
		txtDonGia.setText("");
		txtMaDichVu.setEditable(true);
		txtMaDichVu.requestFocus();
	}

	private void initTable() {
		tblModel = new DefaultTableModel();
		tblModel.setColumnIdentifiers(new String[] { "Mã Dịch Vụ", "Tên Dịch Vụ", "Đơn Giá" });
		table.setModel(tblModel);
	}

	private void fillToTable(List<DichVu> lst) {
		tblModel.setRowCount(0);
		for (DichVu dv : lst) {
			tblModel.addRow(new Object[] { dv.getMaDichVu(), dv.getTenDichVu(), dv.getDonGia() });
		}
	}

	private void DBFillToList() {
		DichVuDAO dichVuDAO = new DichVuDAO();
		lstDichVu = dichVuDAO.selectAll();
		fillToTable(lstDichVu);
	}

	private DichVu readForm() {
		DichVu model = new DichVu();
		if (txtMaDichVu.getText().trim().length() == 0 || txtTenDichVu.getText().trim().length() == 0
				|| txtDonGia.getText().trim().length() == 0) {
			MsgBox.alert(this, "Thông tin không được để trống!");
			return null;
		}
		try {
			model.setMaDichVu(Integer.parseInt(txtMaDichVu.getText()));
		} catch (Exception e) {
			// TODO: handle exception
			MsgBox.alert(this, "Mã dịch vụ không đúng định dạng! (Chữ số)");
			return null;
		}
		try {
			model.setDonGia(Double.parseDouble(txtDonGia.getText()));
		} catch (Exception e) {
			// TODO: handle exception
			MsgBox.alert(this, "Đơn giá không đúng định dạng!");
			return null;
		}
		model.setTenDichVu(txtTenDichVu.getText());
		return model;
	}

	private void add() {
		DichVu model = readForm();
		DichVuDAO modelDAO = new DichVuDAO();
		if (model != null) {
			for (DichVu dv : lstDichVu) {
				if (dv.getMaDichVu() == model.getMaDichVu()) {
					MsgBox.alert(this, "Mã dịch vụ đã tồn tại!");
					return;
				}
			}
			lstDichVu.add(model);
			modelDAO.insert(model);
			DBFillToList();
			clearForm();
			MsgBox.alert(this, "Thêm dịch vụ thành công!");
		}
	}

	private void delete() {
		try {
			for (DichVu dv : lstDichVu) {
				if (Integer.parseInt(txtMaDichVu.getText()) == dv.getMaDichVu()) {
					boolean choose = MsgBox.comfirm(this,
							"Bạn có chắc chắn muốn xóa dịch vụ '" + dv.getTenDichVu() + "' không?");
					if (choose) {
						new DichVuDAO().delete(dv.getMaDichVu());
						lstDichVu.remove(dv);
						DBFillToList();
						index = 0;
						setSelected(0);
						MsgBox.alert(this, "Xóa thông tin dịch vụ thành công!");
						if (lstDichVu.isEmpty()) {
							clearForm();
						}
					} else {
						return;
					}
				}
			}
		} catch (Exception e) {

		}
	}

	private void update() {
		for (DichVu dv : lstDichVu) {
			if (Integer.parseInt(txtMaDichVu.getText()) == dv.getMaDichVu()) {
				DichVu lp = readForm();
				new DichVuDAO().update(lp);
				DBFillToList();
				MsgBox.alert(this, "Cập nhật thông tin dịch vụ thành công");
			}
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		tabNV = new javax.swing.JTabbedPane();
		tabCapnhat = new javax.swing.JPanel();
		jPanel4 = new javax.swing.JPanel();
		txtMaDichVu = new javax.swing.JTextField();
		lblThoiLuong = new javax.swing.JLabel();
		txtTenDichVu = new javax.swing.JTextField();
		lblMaChuyenDe = new javax.swing.JLabel();
		lblChuyenDe = new javax.swing.JLabel();
		txtDonGia = new javax.swing.JTextField();
		lblHocPhi1 = new javax.swing.JLabel();
		jScrollPane2 = new javax.swing.JScrollPane();
		btnFirst = new javax.swing.JButton();
		btnPrevious = new javax.swing.JButton();
		btnNext = new javax.swing.JButton();
		btnLast = new javax.swing.JButton();
		btnAdd = new javax.swing.JButton();
		btnClear = new javax.swing.JButton();
		btnUpdate = new javax.swing.JButton();
		btnDelete = new javax.swing.JButton();
		tabDanhsach = new javax.swing.JPanel();
		spTable = new javax.swing.JScrollPane();
		table = new Swing.Table();

		setBackground(new java.awt.Color(242, 242, 242));

		jPanel1.setBackground(new java.awt.Color(255, 255, 255));

		tabNV.setBackground(new java.awt.Color(255, 255, 255));

		tabCapnhat.setBackground(new java.awt.Color(255, 255, 255));

		jPanel4.setBackground(new java.awt.Color(255, 255, 255));
		jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "THÔNG TIN",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 1, 14), new java.awt.Color(32, 136, 203))); // NOI18N

//        txtMaDichVu.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
		txtMaDichVu.setPreferredSize(new java.awt.Dimension(64, 25));
		txtMaDichVu.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				txtMaDichVuActionPerformed(evt);
			}
		});

		lblThoiLuong.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		lblThoiLuong.setForeground(new java.awt.Color(102, 102, 102));
		lblThoiLuong.setText("Đơn Giá");

//        txtTenDichVu.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
		txtTenDichVu.setPreferredSize(new java.awt.Dimension(64, 25));

		lblMaChuyenDe.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		lblMaChuyenDe.setForeground(new java.awt.Color(102, 102, 102));
		lblMaChuyenDe.setText("Mã Dịch Vụ");

		lblChuyenDe.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		lblChuyenDe.setForeground(new java.awt.Color(102, 102, 102));
		lblChuyenDe.setText("Tên Dịch Vụ");

//        txtDonGia.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
		txtDonGia.setPreferredSize(new java.awt.Dimension(64, 25));

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel4Layout.createSequentialGroup().addContainerGap().addGroup(jPanel4Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(txtMaDichVu, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txtTenDichVu, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(txtDonGia, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
						.addGroup(jPanel4Layout.createSequentialGroup()
								.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(lblMaChuyenDe).addComponent(lblChuyenDe)
										.addComponent(lblThoiLuong).addComponent(lblHocPhi1))
								.addGap(0, 0, Short.MAX_VALUE)))
						.addContainerGap()));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel4Layout.createSequentialGroup().addContainerGap().addComponent(lblMaChuyenDe)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(txtMaDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18).addComponent(lblChuyenDe)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(txtTenDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18).addComponent(lblThoiLuong)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18).addComponent(lblHocPhi1)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(31, Short.MAX_VALUE)));

		btnFirst.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				first();
			}
		});

		btnPrevious.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				previous();
			}
		});

		btnNext.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				next();
			}
		});

		btnLast.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				last();
			}
		});

		btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		btnAdd.setForeground(new java.awt.Color(32, 136, 203));
		btnAdd.setText("THÊM");
		btnAdd.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				add();
			}
		});

		btnClear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		btnClear.setForeground(new java.awt.Color(32, 136, 203));
		btnClear.setText("MỚI");
		btnClear.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				clearForm();
			}
		});

		btnUpdate.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		btnUpdate.setForeground(new java.awt.Color(32, 136, 203));
		btnUpdate.setText("SỬA");
		btnUpdate.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				update();
			}
		});

		btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		btnDelete.setForeground(new java.awt.Color(32, 136, 203));
		btnDelete.setText("XÓA");
		btnDelete.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				delete();
			}
		});

		javax.swing.GroupLayout tabCapnhatLayout = new javax.swing.GroupLayout(tabCapnhat);
		tabCapnhat.setLayout(tabCapnhatLayout);
		tabCapnhatLayout
				.setHorizontalGroup(tabCapnhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(tabCapnhatLayout.createSequentialGroup().addContainerGap().addGroup(tabCapnhatLayout
								.createParallelGroup(
										javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
										tabCapnhatLayout.createSequentialGroup()
												.addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 51,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(8, 8, 8)
												.addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 51,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(8, 8, 8)
												.addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 51,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addGap(8, 8, 8)
												.addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 51,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91,
														Short.MAX_VALUE)
												.addComponent(btnAdd).addGap(18, 18, 18).addComponent(btnDelete)
												.addGap(18, 18, 18).addComponent(btnUpdate).addGap(18, 18, 18)
												.addComponent(btnClear))
								.addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE))
								.addContainerGap()));
		tabCapnhatLayout
				.setVerticalGroup(tabCapnhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
								tabCapnhatLayout.createSequentialGroup().addContainerGap()
										.addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGap(40, 40, 40)
										.addGroup(tabCapnhatLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(tabCapnhatLayout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE,
																25, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE,
																25, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE,
																25, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE,
																25, javax.swing.GroupLayout.PREFERRED_SIZE))
												.addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
														javax.swing.GroupLayout.PREFERRED_SIZE))
										.addContainerGap()));

		tabNV.addTab("CẬP NHẬT", tabCapnhat);

		spTable.setBorder(null);

		table.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "Mã Dịch Vụ", "Tên Dịch Vụ", "Đơn Vị Tính", "Đơn Giá", "Ngày Tạo" }) {
			boolean[] canEdit = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		});
		spTable.setViewportView(table);

		javax.swing.GroupLayout tabDanhsachLayout = new javax.swing.GroupLayout(tabDanhsach);
		tabDanhsach.setLayout(tabDanhsachLayout);
		tabDanhsachLayout.setHorizontalGroup(tabDanhsachLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 673, Short.MAX_VALUE)
				.addGroup(tabDanhsachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)));
		tabDanhsachLayout.setVerticalGroup(tabDanhsachLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 462, Short.MAX_VALUE)
				.addGroup(tabDanhsachLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 462, Short.MAX_VALUE)));

		tabNV.addTab("DANH SÁCH", tabDanhsach);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(tabNV));
		jPanel1Layout.setVerticalGroup(
				jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(tabNV));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
		this.setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
	}// </editor-fold>//GEN-END:initComponents

	private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnDeleteActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_btnDeleteActionPerformed

	private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnUpdateActionPerformed
		// TODO add your handling code here:

	}// GEN-LAST:event_btnUpdateActionPerformed

	private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnClearActionPerformed

	}// GEN-LAST:event_btnClearActionPerformed

	private void txtMaDichVuActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtMaDichVuActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_txtMaDichVuActionPerformed

	private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnFirstActionPerformed

	}// GEN-LAST:event_btnFirstActionPerformed

	private void btnPreviousActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnPreviousActionPerformed

	}// GEN-LAST:event_btnPreviousActionPerformed

	private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnNextActionPerformed

	}// GEN-LAST:event_btnNextActionPerformed

	private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnLastActionPerformed

	}// GEN-LAST:event_btnLastActionPerformed

	private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAddActionPerformed

	}// GEN-LAST:event_btnAddActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnAdd;
	private javax.swing.JButton btnClear;
	private javax.swing.JButton btnDelete;
	private javax.swing.JButton btnFirst;
	private javax.swing.JButton btnLast;
	private javax.swing.JButton btnNext;
	private javax.swing.JButton btnPrevious;
	private javax.swing.JButton btnUpdate;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JLabel lblChuyenDe;
	private javax.swing.JLabel lblHocPhi1;
	private javax.swing.JLabel lblMaChuyenDe;
	private javax.swing.JLabel lblThoiLuong;
	private javax.swing.JScrollPane spTable;
	private javax.swing.JPanel tabCapnhat;
	private javax.swing.JPanel tabDanhsach;
	private javax.swing.JTabbedPane tabNV;
	private Swing.Table table;
	private javax.swing.JTextField txtDonGia;
	private javax.swing.JTextField txtMaDichVu;
	private javax.swing.JTextField txtTenDichVu;
	// End of variables declaration//GEN-END:variables
}
