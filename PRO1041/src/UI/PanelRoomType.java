/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI;

import Swing.ScrollBar;
import Utils.MsgBox;
import Utils.SetIcon;
import Utils.XImage;
import Entity.LoaiPhong;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import DAO.LoaiPhongDAO;

/**
 *
 * @author NAMVAAU
 */
public class PanelRoomType extends javax.swing.JPanel {
	DefaultTableModel tblModel;
	DefaultComboBoxModel cbxModel;
	List<LoaiPhong> lstLoaiPhong = new ArrayList<>();
	int index = 0;

	/**
	 * Creates new form Room
	 */
	public PanelRoomType() {
		initComponents();
		// add row table
		spTable.setVerticalScrollBar(new ScrollBar());
		spTable.getVerticalScrollBar().setBackground(Color.WHITE);
		spTable.getViewport().setBackground(Color.WHITE);
		JPanel p = new JPanel();
		p.setBackground(Color.WHITE);
		spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
		initTable();
		initIcon();
		initComboBox();
		DBFillToList();
		setSelected(0);
	}

	void previous() {
		try {
			if (index == 0) {
				index = lstLoaiPhong.size();
			}
			index--;
			setSelected(index);
		} catch (Exception e) {
		}
	}

	void next() {
		try {
			if (index == lstLoaiPhong.size() - 1) {
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
			index = lstLoaiPhong.size() - 1;
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

	private void initComboBox() {
		cbxModel = new DefaultComboBoxModel<String>(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" });
		cbxSucChua.setModel(cbxModel);
	}

	private void initTable() {
		tblModel = new DefaultTableModel();
		tblModel.setColumnIdentifiers(
				new String[] { "Mã Loại Phòng", "Tên Loại Phòng", "Đơn Giá", "Sức Chứa", "Mô Tả" });
		table.setModel(tblModel);
	}
	
	private void initIcon() {
		btnFirst.setIcon(XImage.read("button-round-previous-icon.png", 25, 25));
		btnLast.setIcon(XImage.read("button-round-next-icon.png", 25, 25));
		btnNext.setIcon(XImage.read("button-round-fast-forward-icon.png", 25, 25));
		btnPrevious.setIcon(XImage.read("button-round-fast-backward-icon.png", 25, 25));
	}

	private void showDetail(int index) {
		LoaiPhong model = lstLoaiPhong.get(index);
		txtMaLoaiPhong.setText(String.valueOf(model.getMaLoaiPhong()));
		txtTenLoaiPhong.setText(model.getTenLoaiPhong());
		txtDonGia.setText(String.valueOf(model.getDonGia()));
		cbxSucChua.setSelectedItem(String.valueOf(model.getSucChua()));
		txtMoTa.setText(model.getMoTa());
		txtMaLoaiPhong.setEditable(false);
	}

	private void fillToTable(List<LoaiPhong> lst) {
		tblModel.setRowCount(0);
		for (LoaiPhong lp : lst) {
			tblModel.addRow(new Object[] { lp.getMaLoaiPhong(), lp.getTenLoaiPhong(), lp.getDonGia(), lp.getSucChua(),
					lp.getMoTa() });
		}
	}

	private void DBFillToList() {
		LoaiPhongDAO loaiPhongDAO = new LoaiPhongDAO();
		lstLoaiPhong = loaiPhongDAO.selectAll();
		fillToTable(lstLoaiPhong);
	}

	private void clearForm() {
		txtMaLoaiPhong.setText("");
		txtTenLoaiPhong.setText("");
		txtDonGia.setText("");
		txtMoTa.setText("");
		cbxSucChua.setSelectedIndex(0);
		txtMaLoaiPhong.requestFocus();
		txtMaLoaiPhong.setEditable(true);
	}

	private LoaiPhong readForm() {
		LoaiPhong model = new LoaiPhong();
		if (txtMaLoaiPhong.getText().trim().length() == 0 || txtTenLoaiPhong.getText().trim().length() == 0 || txtDonGia.getText().trim().length() == 0) {
			MsgBox.alert(this, "Thông tin không được để trống!");
			return null;
		}
		try {
			model.setMaLoaiPhong(Integer.parseInt(txtMaLoaiPhong.getText()));
		} catch (Exception e) {
			// TODO: handle exception
			MsgBox.alert(this, "Mã phòng không đúng định dạng (Chữ Số)!");
			return null;
		}
		try {
			model.setDonGia(Float.parseFloat(txtDonGia.getText()));
		} catch (Exception e) {
			// TODO: handle exception
			MsgBox.alert(this, "Đơn giá không đúng định dạng (Chữ Số)!");
			return null;
		}
		model.setTenLoaiPhong(txtTenLoaiPhong.getText());
		model.setSucChua(Integer.parseInt((String) cbxModel.getSelectedItem()));
		model.setMoTa(txtMoTa.getText());
		return model;
	}

	private void add() {
		LoaiPhong model = readForm();
		LoaiPhongDAO modelDAO = new LoaiPhongDAO();
		if (model != null) {
			for (LoaiPhong lp : lstLoaiPhong) {
				if (lp.getMaLoaiPhong() == model.getMaLoaiPhong()) {
					MsgBox.alert(this, "Mã loại phòng đã tồn tại!");
					return;
				}
			}
			lstLoaiPhong.add(model);
			modelDAO.insert(model);
			DBFillToList();
			clearForm();
			MsgBox.alert(this, "Thêm loại phòng thành công!");
		}
	}

	private void delete() {
		try {
			for (LoaiPhong lp : lstLoaiPhong) {
				if (Integer.parseInt(txtMaLoaiPhong.getText()) == lp.getMaLoaiPhong()) {
					boolean choose = MsgBox.comfirm(this,
							"Bạn có chắc chắn muốn xóa loại phòng '" + lp.getTenLoaiPhong() + "' không?");
					if (choose) {
						new LoaiPhongDAO().delete(lp.getMaLoaiPhong());
						lstLoaiPhong.remove(lp);
						DBFillToList();
						index = 0;
						setSelected(0);
						MsgBox.alert(this, "Xóa thông tin loại phòng thành công!");
						if (lstLoaiPhong.isEmpty()) {
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
		for (LoaiPhong loaiPhong : lstLoaiPhong) {
			if (Integer.parseInt(txtMaLoaiPhong.getText()) == loaiPhong.getMaLoaiPhong()) {
				LoaiPhong lp = readForm();
				if (lp != null) {
					new LoaiPhongDAO().update(lp);
					DBFillToList();
					MsgBox.alert(this, "Cập nhật thông tin loại phòng thành công");
				}
			}
		}
	}

	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		tabNV = new javax.swing.JTabbedPane();
		tabCapnhat = new javax.swing.JPanel();
		jPanel4 = new javax.swing.JPanel();
		txtMaLoaiPhong = new javax.swing.JTextField();
		lblThoiLuong = new javax.swing.JLabel();
		txtTenLoaiPhong = new javax.swing.JTextField();
		lblMaChuyenDe = new javax.swing.JLabel();
		lblChuyenDe = new javax.swing.JLabel();
		txtDonGia = new javax.swing.JTextField();
		lblMaChuyenDe1 = new javax.swing.JLabel();
		cbxSucChua = new javax.swing.JComboBox<>();
		lblThoiLuong1 = new javax.swing.JLabel();
		jScrollPane1 = new javax.swing.JScrollPane();
		txtMoTa = new javax.swing.JTextArea();
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

//		txtMaLoaiPhong.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

		lblThoiLuong.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		lblThoiLuong.setForeground(new java.awt.Color(102, 102, 102));
		lblThoiLuong.setText("Đơn Giá");

//		txtTenLoaiPhong.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

		lblMaChuyenDe.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		lblMaChuyenDe.setForeground(new java.awt.Color(102, 102, 102));
		lblMaChuyenDe.setText("Mã Loại Phòng");

		lblChuyenDe.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		lblChuyenDe.setForeground(new java.awt.Color(102, 102, 102));
		lblChuyenDe.setText("Tên Loại Phòng");

//		txtDonGia.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N

		lblMaChuyenDe1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		lblMaChuyenDe1.setForeground(new java.awt.Color(102, 102, 102));
		lblMaChuyenDe1.setText("Sức Chứa Tối Đa");

		cbxSucChua.setModel(
				new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
//		cbxSucChua.setFont(new java.awt.Font("Segoe UI", 0, 12));
		
		lblThoiLuong1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		lblThoiLuong1.setForeground(new java.awt.Color(102, 102, 102));
		lblThoiLuong1.setText("Mô Tả");

		txtMoTa.setColumns(20);
		txtMoTa.setRows(5);
//		txtMoTa.setFont(new java.awt.Font("Segoe UI", 0, 11));
		jScrollPane1.setViewportView(txtMoTa);

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel4Layout.createSequentialGroup().addContainerGap().addGroup(jPanel4Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(txtTenLoaiPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 639, Short.MAX_VALUE)
						.addComponent(txtDonGia)
						.addGroup(jPanel4Layout.createSequentialGroup()
								.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(lblMaChuyenDe).addComponent(txtMaLoaiPhong,
												javax.swing.GroupLayout.PREFERRED_SIZE, 308,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18, 18)
								.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(jPanel4Layout.createSequentialGroup().addComponent(lblMaChuyenDe1)
												.addGap(0, 0, Short.MAX_VALUE))
										.addComponent(cbxSucChua, 0, javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)))
						.addComponent(jScrollPane1)
						.addGroup(jPanel4Layout.createSequentialGroup()
								.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(lblChuyenDe).addComponent(lblThoiLuong)
										.addComponent(lblThoiLuong1))
								.addGap(0, 0, Short.MAX_VALUE)))
						.addContainerGap()));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel4Layout.createSequentialGroup().addContainerGap().addGroup(jPanel4Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
						.addGroup(jPanel4Layout.createSequentialGroup().addComponent(lblMaChuyenDe)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(txtMaLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(cbxSucChua, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addGroup(
								jPanel4Layout.createSequentialGroup().addComponent(lblMaChuyenDe1).addGap(37, 37, 37)))
						.addGap(18, 18, 18).addComponent(lblChuyenDe)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(txtTenLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18).addComponent(lblThoiLuong)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(txtDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18).addComponent(lblThoiLuong1)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(35, Short.MAX_VALUE)));

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
				if (txtMaLoaiPhong.getText().trim().length() != 0) {
                	update();
                }
			}
		});

		btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		btnDelete.setForeground(new java.awt.Color(32, 136, 203));
		btnDelete.setText("XÓA");
		btnDelete.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if (txtMaLoaiPhong.getText().trim().length() != 0) {
                	delete();
                }
			}
		});

		javax.swing.GroupLayout tabCapnhatLayout = new javax.swing.GroupLayout(tabCapnhat);
		tabCapnhat.setLayout(tabCapnhatLayout);
		tabCapnhatLayout
				.setHorizontalGroup(tabCapnhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(tabCapnhatLayout.createSequentialGroup().addContainerGap().addGroup(tabCapnhatLayout
								.createParallelGroup(
										javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(tabCapnhatLayout.createSequentialGroup()
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
								.addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addContainerGap()));
		tabCapnhatLayout.setVerticalGroup(tabCapnhatLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabCapnhatLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
						.addGroup(tabCapnhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGroup(
										tabCapnhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
														javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));

		tabNV.addTab("CẬP NHẬT", tabCapnhat);

		spTable.setBorder(null);
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

	private void txtMaLoaiPhongActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtMaLoaiPhongActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_txtMaLoaiPhongActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnAdd;
	private javax.swing.JButton btnClear;
	private javax.swing.JButton btnDelete;
	private javax.swing.JButton btnFirst;
	private javax.swing.JButton btnLast;
	private javax.swing.JButton btnNext;
	private javax.swing.JButton btnPrevious;
	private javax.swing.JButton btnUpdate;
	private javax.swing.JComboBox<String> cbxSucChua;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JLabel lblChuyenDe;
	private javax.swing.JLabel lblMaChuyenDe;
	private javax.swing.JLabel lblMaChuyenDe1;
	private javax.swing.JLabel lblThoiLuong;
	private javax.swing.JLabel lblThoiLuong1;
	private javax.swing.JScrollPane spTable;
	private javax.swing.JPanel tabCapnhat;
	private javax.swing.JPanel tabDanhsach;
	private javax.swing.JTabbedPane tabNV;
	private Swing.Table table;
	private javax.swing.JTextField txtDonGia;
	private javax.swing.JTextField txtMaLoaiPhong;
	private javax.swing.JTextArea txtMoTa;
	private javax.swing.JTextField txtTenLoaiPhong;
	// End of variables declaration//GEN-END:variables
}
