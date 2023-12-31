/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import DAO.LoaiPhongDAO;
import DAO.PhongDAO;
import Entity.LoaiPhong;
import Entity.Phong;
import Swing.ScrollBar;
import Utils.MsgBox;
import Utils.XImage;
import javax.swing.ImageIcon;

/**
 *
 * @author NAMVAAU
 */
public class PanelRoom extends javax.swing.JPanel {
	DefaultTableModel tblModel;
	DefaultComboBoxModel cbxModel;
	List<Phong> lstPhong = new ArrayList<>();
	int index = 0;
	StringBuilder phong = new StringBuilder();;

	/**
	 * Creates new form Room
	 */
	public PanelRoom() {
		initComponents();
		txtDonGia.setEditable(false);
		txtTenPhong.setEditable(false);
		// add row table
		spTable.setVerticalScrollBar(new ScrollBar());
		spTable.getVerticalScrollBar().setBackground(Color.WHITE);
		spTable.getViewport().setBackground(Color.WHITE);
		JPanel p = new JPanel();
		p.setBackground(Color.WHITE);
		spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
		initComboBox();
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
				index = lstPhong.size();
			}
			index--;
			setSelected(index);
		} catch (Exception e) {
		}
	}

	void next() {
		try {
			if (index == lstPhong.size() - 1) {
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
			index = lstPhong.size() - 1;
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
		cbxModel = new DefaultComboBoxModel();
		cbxModel.removeAllElements();
		List<LoaiPhong> lst = new LoaiPhongDAO().selectAll();
		for (LoaiPhong loaiPhong : lst) {
			cbxModel.addElement(loaiPhong);
		}
		cbxLoaiPhong.setModel(cbxModel);
	}

	private void initTable() {
		tblModel = new DefaultTableModel();
		tblModel.setColumnIdentifiers(new String[] { "Mã Phòng", "Tên Phòng", "Trạng Thái Phòng", "Đơn Giá" });
		table.setModel(tblModel);
	}

	private void showDetail(int index) {
		Phong model = lstPhong.get(index);
		LoaiPhong loaiPhong = new LoaiPhongDAO().selectByID(model.getMaLoaiPhong());
		txtMaPhong.setText(String.valueOf(model.getMaPhong()));
		txtTenPhong.setText(model.getTenPhong());
		txtDonGia.setText(String.valueOf(model.getGiaPhong()));
		cbxLoaiPhong.setSelectedItem(loaiPhong);
		cbxLoaiPhong.setEnabled(false);
		txtMaPhong.setEditable(false);
	}

	private void fillToTable(List<Phong> lst) {
		tblModel.setRowCount(0);
		for (Phong p : lst) {
			tblModel.addRow(new Object[] { p.getMaPhong(), p.getTenPhong(), p.getTrangThaiPhong(), p.getGiaPhong() });
		}
	}

	private void DBFillToList() {
		PhongDAO phongDAO = new PhongDAO();
		lstPhong = phongDAO.selectAll();
		fillToTable(lstPhong);
	}

	private void clearForm() {
		txtMaPhong.setText("");
		txtTenPhong.setText("");
		txtDonGia.setText("");
		cbxLoaiPhong.setSelectedIndex(0);
		txtMaPhong.requestFocus();
		cbxLoaiPhong.setEnabled(true);
		txtMaPhong.setEditable(true);
	}

	private Phong readForm() {
		Phong model = new Phong();
		LoaiPhong loaiPhong = (LoaiPhong) cbxLoaiPhong.getSelectedItem();
		if (txtMaPhong.getText().trim().length() == 0) {
			MsgBox.alert(this, "Thông tin không được để trống!");
			return null;
		}
		try {
			model.setMaPhong(Integer.parseInt(txtMaPhong.getText()));
		} catch (Exception e) {
			// TODO: handle exception
			MsgBox.alert(this, "Mã phòng không đúng định dạng (Chữ Số)!");
			return null;
		}
		model.setTenPhong(txtTenPhong.getText());
		model.setGiaPhong(loaiPhong.getDonGia());
		model.setMaLoaiPhong(loaiPhong.getMaLoaiPhong());
		model.setTrangThaiPhong("Còn trống");
		return model;
	}

	private void add() {
		Phong model = readForm();
		PhongDAO modelDAO = new PhongDAO();
		if (model != null) {
			for (Phong p : lstPhong) {
				if (p.getMaPhong() == model.getMaPhong()) {
					MsgBox.alert(this, "Mã phòng đã tồn tại!");
					return;
				}
			}
			lstPhong.add(model);
			modelDAO.insert(model);
			DBFillToList();
			clearForm();
			MsgBox.alert(this, "Thêm phòng thành công!");
		}
	}

	private void delete() {
		try {
			for (Phong p : lstPhong) {
				if (Integer.parseInt(txtMaPhong.getText()) == p.getMaPhong()) {
					boolean choose = MsgBox.comfirm(this,
							"Bạn có chắc chắn muốn xóa phòng '" + p.getTenPhong() + "' không?");
					if (choose) {
						new PhongDAO().delete(p.getMaPhong());
						lstPhong.remove(p);
						DBFillToList();
						index = 0;
						setSelected(0);
						MsgBox.alert(this, "Xóa thông tin phòng thành công!");
						if (lstPhong.isEmpty()) {
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
		for (Phong phong : lstPhong) {
			if (Integer.parseInt(txtMaPhong.getText()) == phong.getMaPhong()) {
				Phong p = readForm();
				if (p != null) {
					new PhongDAO().update(p);
					DBFillToList();
					MsgBox.alert(this, "Cập nhật thông tin phòng thành công");
				}
			}
		}
	}

	private void updateDisplay() {
		String text = txtMaPhong.getText();
		txtTenPhong.setText("Phòng " + text);
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
		txtMaPhong = new javax.swing.JTextField();
		lblThoiLuong = new javax.swing.JLabel();
		txtTenPhong = new javax.swing.JTextField();
		lblMaChuyenDe = new javax.swing.JLabel();
		lblChuyenDe = new javax.swing.JLabel();
		txtDonGia = new javax.swing.JTextField();
		lblHocPhi1 = new javax.swing.JLabel();
		jScrollPane2 = new javax.swing.JScrollPane();
		lblMaChuyenDe1 = new javax.swing.JLabel();
		cbxLoaiPhong = new javax.swing.JComboBox<>();
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

//        txtMaPhong.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
		txtMaPhong.setPreferredSize(new java.awt.Dimension(64, 25));
		txtMaPhong.addKeyListener((KeyListener) new KeyListener() {
			@Override
			public void keyTyped(KeyEvent event) {

			}

			@Override
			public void keyReleased(KeyEvent event) {
				updateDisplay();
				if (txtMaPhong.getText().isEmpty()) {
					txtTenPhong.setText("");
				}

			}

			@Override
			public void keyPressed(KeyEvent event) {
//                System.out.println("key pressed");
			}
		});

		lblThoiLuong.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		lblThoiLuong.setForeground(new java.awt.Color(102, 102, 102));
		lblThoiLuong.setText("Đơn Giá");

//        txtTenPhong.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
		txtTenPhong.setPreferredSize(new java.awt.Dimension(64, 25));

		lblMaChuyenDe.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		lblMaChuyenDe.setForeground(new java.awt.Color(102, 102, 102));
		lblMaChuyenDe.setText("Mã Phòng");

		lblChuyenDe.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		lblChuyenDe.setForeground(new java.awt.Color(102, 102, 102));
		lblChuyenDe.setText("Tên Phòng");

//        txtDonGia.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
		txtDonGia.setPreferredSize(new java.awt.Dimension(64, 25));

		lblMaChuyenDe1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		lblMaChuyenDe1.setForeground(new java.awt.Color(102, 102, 102));
		lblMaChuyenDe1.setText("Loại Phòng");

		cbxLoaiPhong.setModel(
				new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
		cbxLoaiPhong.setMinimumSize(new java.awt.Dimension(72, 25));
//        cbxLoaiPhong.setFont(new java.awt.Font("Segoe UI", 0, 12));

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel4Layout.createSequentialGroup().addContainerGap().addGroup(jPanel4Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(txtTenPhong, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
								.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(txtMaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 305,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(lblMaChuyenDe))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46,
										Short.MAX_VALUE)
								.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(lblMaChuyenDe1).addComponent(cbxLoaiPhong,
												javax.swing.GroupLayout.PREFERRED_SIZE, 288,
												javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addComponent(txtDonGia, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(jPanel4Layout.createSequentialGroup()
								.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(lblChuyenDe).addComponent(lblThoiLuong))
								.addGap(0, 0, Short.MAX_VALUE)))
						.addContainerGap()));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel4Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(lblMaChuyenDe).addComponent(lblMaChuyenDe1))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addComponent(cbxLoaiPhong, javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(txtMaPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
						.addGap(18, 18, 18).addComponent(lblChuyenDe)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(txtTenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
								javax.swing.GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18).addComponent(lblThoiLuong)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED).addComponent(txtDonGia,
								javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

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
				if (txtMaPhong.getText().trim().length() != 0) {
					update();
				}
			}
		});

		btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		btnDelete.setForeground(new java.awt.Color(32, 136, 203));
		btnDelete.setText("XÓA");
		btnDelete.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if (txtMaPhong.getText().trim().length() != 0) {
					delete();
				}
			}
		});

		cbxLoaiPhong.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				LoaiPhong loaiPhong = (LoaiPhong) cbxLoaiPhong.getSelectedItem();
				txtDonGia.setText(String.valueOf(loaiPhong.getDonGia()));
			}
		});

		javax.swing.GroupLayout tabCapnhatLayout = new javax.swing.GroupLayout(tabCapnhat);
		tabCapnhat.setLayout(tabCapnhatLayout);
		tabCapnhatLayout
				.setHorizontalGroup(tabCapnhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(tabCapnhatLayout.createSequentialGroup().addContainerGap()
								.addGroup(tabCapnhatLayout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
												tabCapnhatLayout.createSequentialGroup()
														.addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE,
																51, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(8, 8, 8)
														.addComponent(btnPrevious,
																javax.swing.GroupLayout.PREFERRED_SIZE, 51,
																javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(8, 8, 8)
														.addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE,
																51, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(8, 8, 8)
														.addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE,
																51, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(
																javax.swing.LayoutStyle.ComponentPlacement.RELATED,
																javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(btnAdd).addGap(18, 18, 18).addComponent(btnDelete)
														.addGap(18, 18, 18).addComponent(btnUpdate).addGap(18, 18, 18)
														.addComponent(btnClear))
										.addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

		}, new String[] { "Mã Phòng", "Tên Phòng", "Đơn Giá", "Trạng Thái" }) {
			boolean[] canEdit = new boolean[] { false, false, false, false };

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

	private void txtMaPhongActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtMaPhongActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_txtMaPhongActionPerformed

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
	private javax.swing.JComboBox<String> cbxLoaiPhong;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JScrollPane jScrollPane2;
	private javax.swing.JLabel lblChuyenDe;
	private javax.swing.JLabel lblHocPhi1;
	private javax.swing.JLabel lblMaChuyenDe;
	private javax.swing.JLabel lblMaChuyenDe1;
	private javax.swing.JLabel lblThoiLuong;
	private javax.swing.JScrollPane spTable;
	private javax.swing.JPanel tabCapnhat;
	private javax.swing.JPanel tabDanhsach;
	private javax.swing.JTabbedPane tabNV;
	private Swing.Table table;
	private javax.swing.JTextField txtDonGia;
	private javax.swing.JTextField txtMaPhong;
	private javax.swing.JTextField txtTenPhong;
	// End of variables declaration//GEN-END:variables
}
