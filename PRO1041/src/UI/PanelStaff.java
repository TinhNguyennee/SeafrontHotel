package UI;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import DAO.NhanVienDAO;
import Entity.NhanVien;
import Swing.ScrollBar;
import Utils.MsgBox;
import Utils.Validate;
import Utils.XDate;
import Utils.XImage;

public class PanelStaff extends javax.swing.JPanel {
	DefaultTableModel tblModel;
	List<NhanVien> lstNhanVien = new ArrayList<>();
	int index = 0;

	/**
	 * Creates new form Room
	 */
	public PanelStaff() {
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
				index = lstNhanVien.size();
			}
			index--;
			setSelected(index);
		} catch (Exception e) {
		}
	}

	void next() {
		try {
			if (index == lstNhanVien.size() - 1) {
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
			index = lstNhanVien.size() - 1;
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
		NhanVien nv = lstNhanVien.get(index);
		txtMaNhanVien.setText(String.valueOf(nv.getMaNhanVien()));
		txtCCCD.setText(nv.getCCCD());
		txtDiaChi.setText(nv.getDiaChi());
		txtNgaySinh.setText(XDate.toString(nv.getNgaySinh(), "dd-MM-yyyy"));
		txtSoDienThoai.setText(nv.getSoDienThoai());
		txtTenNhanVien.setText(nv.getTenNhanVien());
		txtEmail.setText(nv.getEmail());
		if (!nv.getHinh().equals("")) {
			lblHinh.setIcon(XImage.read(nv.getHinh(), 110, 123));
			lblHinh.setToolTipText(nv.getHinh());
		}

		if (nv.isGioiTinh()) {
			rdbNam.setSelected(true);
		} else {
			rdbNu.setSelected(true);
		}

		if (nv.getChucVu() == 2) {
			rdbNhanVien.setSelected(true);
		} else {
			rdbQuanLy.setSelected(true);
		}
		txtMaNhanVien.setEditable(false);
	}

	private void clearForm() {
		txtMaNhanVien.setText("");
		txtCCCD.setText("");
		txtDiaChi.setText("");
		txtNgaySinh.setText("");
		txtSoDienThoai.setText("");
		txtTenNhanVien.setText("");
		rdbNam.setSelected(true);
		rdbNhanVien.setSelected(true);
		txtEmail.setText("");
		int maNhanVien = (int) table.getValueAt(lstNhanVien.size() - 1, 0);
		txtMaNhanVien.setText(String.valueOf(maNhanVien + 1));
	}

	private void initTable() {
		tblModel = new DefaultTableModel();
		tblModel.setColumnIdentifiers(
				new String[] { "Mã Nhân Viên", "Tên Nhân Viên", "CCCD", "Ngày Sinh", "Giới Tính", "SĐT", "Chức Vụ" });
		table.setModel(tblModel);
	}

	private void fillToTable(List<NhanVien> lst) {
		tblModel.setRowCount(0);
		for (NhanVien nv : lst) {
			tblModel.addRow(new Object[] { nv.getMaNhanVien(), nv.getTenNhanVien(), nv.getCCCD(),
					XDate.toString(nv.getNgaySinh(), "dd-MM-yyyy"), nv.isGioiTinh() ? "Nam" : "Nữ", nv.getSoDienThoai(),
					nv.getChucVu() == 1 ? "Quản Lý" : "Nhân Viên" });
		}
	}

	private void DBFillToList() {
		NhanVienDAO nhanVienDAO = new NhanVienDAO();
		lstNhanVien = nhanVienDAO.selectAll();
		fillToTable(lstNhanVien);
	}

	public static boolean isValidDate(String inputDate, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setLenient(false); // Không cho phép chấp nhận các giá trị ngày tháng không hợp lệ

		try {
			sdf.parse(inputDate);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	private NhanVien readForm() {
		NhanVien model = new NhanVien();
		if (txtMaNhanVien.getText().trim().length() == 0 || txtCCCD.getText().trim().length() == 0
				|| txtDiaChi.getText().trim().length() == 0 || txtEmail.getText().trim().length() == 0
				|| txtNgaySinh.getText().trim().length() == 0 || txtSoDienThoai.getText().trim().length() == 0
				|| txtTenNhanVien.getText().trim().length() == 0) {
			MsgBox.alert(this, "Thông tin không được để trống!");
			return null;
		}
		if (!Validate.isValidEmail(txtEmail.getText())) {
			MsgBox.alert(this, "Email không đúng định dạng!");
			return null;
		}

		if (!Validate.isValidPhoneNumber(txtSoDienThoai.getText())) {
			MsgBox.alert(this, "Số điện thoại không đúng định dạng!");
			return null;
		}

		boolean isValid = isValidDate(txtNgaySinh.getText(), "dd-MM-yyyy");

		if (!isValid) {
			MsgBox.alert(this, "Ngày sinh không hợp lệ!");
			return null;
		}
		model.setMaNhanVien(Integer.parseInt(txtMaNhanVien.getText()));
		model.setTenNhanVien(txtTenNhanVien.getText());
		model.setCCCD(txtCCCD.getText());
		model.setEmail(txtEmail.getText());
		model.setDiaChi(txtDiaChi.getText());
		model.setSoDienThoai(txtSoDienThoai.getText());
		model.setNgaySinh(XDate.toDate(txtNgaySinh.getText(), "dd-MM-yyyy"));
		model.setChucVu(rdbNhanVien.isSelected() ? 2 : 1);
		model.setGioiTinh(rdbNam.isSelected());
		model.setHinh(lblHinh.getToolTipText());
		return model;
	}

	private void add() {
		NhanVien model = readForm();
		NhanVienDAO modelDAO = new NhanVienDAO();
		if (model != null) {
			for (NhanVien nv : lstNhanVien) {
				if (nv.getMaNhanVien() == model.getMaNhanVien()) {
					MsgBox.alert(this, "Mã nhân viên đã tồn tại!");
					return;
				}
			}
			lstNhanVien.add(model);
			modelDAO.insert(model);
			DBFillToList();
			clearForm();
			MsgBox.alert(this, "Thêm nhân viên thành công!");
		}
	}

	private void delete() {
		try {
			for (NhanVien nv : lstNhanVien) {
				if (Integer.parseInt(txtMaNhanVien.getText()) == nv.getMaNhanVien()) {
					boolean choose = MsgBox.comfirm(this,
							"Bạn có chắc chắn muốn xóa nhân viên '" + nv.getTenNhanVien() + "' không?");
					if (choose) {
						new NhanVienDAO().delete(nv.getMaNhanVien());
						lstNhanVien.remove(nv);
						DBFillToList();
						index = 0;
						setSelected(0);
						MsgBox.alert(this, "Xóa thông tin nhân viên thành công!");
						if (lstNhanVien.isEmpty()) {
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
		for (NhanVien nv : lstNhanVien) {
			if (Integer.parseInt(txtMaNhanVien.getText()) == nv.getMaNhanVien()) {
				NhanVien p = readForm();
				if (p != null) {
					new NhanVienDAO().update(p);
					DBFillToList();
					MsgBox.alert(this, "Cập nhật thông tin nhân viên thành công");
				}
			}
		}
	}

	private void initComponents() {

		buttonGroup1 = new javax.swing.ButtonGroup();
		buttonGroup2 = new javax.swing.ButtonGroup();
		jPanel1 = new javax.swing.JPanel();
		tabNV = new javax.swing.JTabbedPane();
		tabCapnhat = new javax.swing.JPanel();
		jPanel4 = new javax.swing.JPanel();
		txtMaNhanVien = new javax.swing.JTextField();
		lblEmail = new javax.swing.JLabel();
		lblHoTen = new javax.swing.JLabel();
		lblMaNhanVien = new javax.swing.JLabel();
		lblMatKhau = new javax.swing.JLabel();
		txtSoDienThoai = new javax.swing.JTextField();
		txtTenNhanVien = new javax.swing.JTextField();
		txtCCCD = new javax.swing.JTextField();
		rdbNam = new javax.swing.JRadioButton();
		lblGioiTinh = new javax.swing.JLabel();
		rdbNu = new javax.swing.JRadioButton();
		lblEmail1 = new javax.swing.JLabel();
		txtNgaySinh = new javax.swing.JTextField();
		lblEmail2 = new javax.swing.JLabel();
		jPanel3 = new javax.swing.JPanel();
		lblHinh = new javax.swing.JLabel();
		lblGioiTinh1 = new javax.swing.JLabel();
		rdbNhanVien = new javax.swing.JRadioButton();
		rdbQuanLy = new javax.swing.JRadioButton();
		lblHoTen1 = new javax.swing.JLabel();
		txtEmail = new javax.swing.JTextField();
		jScrollPane1 = new javax.swing.JScrollPane();
		txtDiaChi = new javax.swing.JTextArea();
		btnFirst = new javax.swing.JButton();
		btnPrevious = new javax.swing.JButton();
		btnNext = new javax.swing.JButton();
		btnLast = new javax.swing.JButton();
		btnAdd = new javax.swing.JButton();
		btnDelete = new javax.swing.JButton();
		btnUpdate = new javax.swing.JButton();
		btnClear = new javax.swing.JButton();
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

//		txtMaNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

		lblEmail.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		lblEmail.setForeground(new java.awt.Color(102, 102, 102));
		lblEmail.setText("Số CCCD/CMND");

		lblHoTen.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		lblHoTen.setForeground(new java.awt.Color(102, 102, 102));
		lblHoTen.setText("Số Điện Thoại");

		lblMaNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		lblMaNhanVien.setForeground(new java.awt.Color(102, 102, 102));
		lblMaNhanVien.setText("Mã Nhân Viên");

		lblMatKhau.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		lblMatKhau.setForeground(new java.awt.Color(102, 102, 102));
		lblMatKhau.setText("Tên Nhân Viên");

//		txtSoDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
//
//		txtTenNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

//		txtCCCD.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

		rdbNam.setBackground(new java.awt.Color(255, 255, 255));
		buttonGroup1.add(rdbNam);
		rdbNam.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		rdbNam.setForeground(new java.awt.Color(32, 136, 203));
		rdbNam.setSelected(true);
		rdbNam.setText("Nam");

		lblGioiTinh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		lblGioiTinh.setForeground(new java.awt.Color(102, 102, 102));
		lblGioiTinh.setText("Giới Tính");

		rdbNu.setBackground(new java.awt.Color(255, 255, 255));
		buttonGroup1.add(rdbNu);
		rdbNu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		rdbNu.setForeground(new java.awt.Color(32, 136, 203));
		rdbNu.setText("Nữ");

		lblEmail1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		lblEmail1.setForeground(new java.awt.Color(102, 102, 102));
		lblEmail1.setText("Ngày Sinh");

//		txtNgaySinh.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

		lblEmail2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		lblEmail2.setForeground(new java.awt.Color(102, 102, 102));
		lblEmail2.setText("Địa Chỉ");

		jPanel3.setBackground(new java.awt.Color(255, 255, 255));
		jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ảnh",
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
				new java.awt.Font("Segoe UI", 1, 12), new java.awt.Color(32, 136, 203))); // NOI18N

		javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
		jPanel3.setLayout(jPanel3Layout);
		jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(lblHinh, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE));
		jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(lblHinh, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE,
						123, Short.MAX_VALUE));

		lblGioiTinh1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		lblGioiTinh1.setForeground(new java.awt.Color(102, 102, 102));
		lblGioiTinh1.setText("Vai Trò");

		rdbNhanVien.setBackground(new java.awt.Color(255, 255, 255));
		buttonGroup2.add(rdbNhanVien);
		rdbNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		rdbNhanVien.setForeground(new java.awt.Color(32, 136, 203));
		rdbNhanVien.setSelected(true);
		rdbNhanVien.setText("Nhân Viên");

		rdbQuanLy.setBackground(new java.awt.Color(255, 255, 255));
		buttonGroup2.add(rdbQuanLy);
		rdbQuanLy.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		rdbQuanLy.setForeground(new java.awt.Color(32, 136, 203));
		rdbQuanLy.setText("Quản Lý");

		lblHoTen1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		lblHoTen1.setForeground(new java.awt.Color(102, 102, 102));
		lblHoTen1.setText("Email");

//		txtEmail.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N

		txtDiaChi.setColumns(20);
		txtDiaChi.setRows(5);
//		txtDiaChi.setFont(new java.awt.Font("Segoe UI", 1, 12));
		jScrollPane1.setViewportView(txtDiaChi);

		lblHinh.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				JFileChooser fileChooser = new JFileChooser();
				int res = fileChooser.showOpenDialog(fileChooser);
				if (res == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					XImage.save(selectedFile);
					ImageIcon icon = XImage.read(selectedFile.getName(), lblHinh.getWidth(), lblHinh.getHeight());
					lblHinh.setIcon(icon);
					lblHinh.setToolTipText(selectedFile.getName());
				}
			}
		});

		javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel4Layout.createSequentialGroup().addContainerGap().addGroup(jPanel4Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false).addComponent(txtEmail)
						.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(jPanel4Layout
										.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(lblMaNhanVien).addComponent(lblMatKhau).addComponent(lblEmail)
										.addComponent(lblEmail1)
										.addComponent(txtMaNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 270,
												Short.MAX_VALUE)
										.addComponent(txtTenNhanVien).addComponent(txtCCCD).addComponent(txtNgaySinh)
										.addComponent(lblHoTen).addComponent(txtSoDienThoai))
								.addComponent(lblHoTen1)))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 78, Short.MAX_VALUE)
						.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
										jPanel4Layout.createSequentialGroup()
												.addGroup(
														jPanel4Layout
																.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																.addComponent(lblGioiTinh).addComponent(lblGioiTinh1))
												.addGap(18, 18, 18)
												.addGroup(jPanel4Layout
														.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(jPanel4Layout.createSequentialGroup()
																.addGap(12, 12, 12).addComponent(jPanel3,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
														.addGroup(jPanel4Layout.createSequentialGroup()
																.addGroup(jPanel4Layout.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(rdbNhanVien).addComponent(rdbNam))
																.addGap(18, 18, 18)
																.addGroup(jPanel4Layout.createParallelGroup(
																		javax.swing.GroupLayout.Alignment.LEADING)
																		.addComponent(rdbNu).addComponent(rdbQuanLy))))
												.addGap(54, 54, 54))
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
										jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(lblEmail2).addComponent(jScrollPane1,
														javax.swing.GroupLayout.PREFERRED_SIZE, 291,
														javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(jPanel4Layout.createSequentialGroup().addGroup(jPanel4Layout
						.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel4Layout.createSequentialGroup()
								.addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(17, 17, 17)
								.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(rdbNam).addComponent(rdbNu).addComponent(lblGioiTinh))
								.addGap(18, 18, 18)
								.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(lblGioiTinh1).addComponent(rdbNhanVien).addComponent(rdbQuanLy)))
						.addGroup(jPanel4Layout.createSequentialGroup().addContainerGap().addComponent(lblMaNhanVien)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(txtMaNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(lblMatKhau)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(txtTenNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(lblEmail)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(lblEmail1).addGap(8, 8, 8).addComponent(txtNgaySinh,
										javax.swing.GroupLayout.PREFERRED_SIZE, 31,
										javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(lblHoTen).addComponent(lblEmail2))
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
								.addGroup(jPanel4Layout.createSequentialGroup()
										.addComponent(txtSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(lblHoTen1).addGap(7, 7, 7).addComponent(txtEmail,
												javax.swing.GroupLayout.PREFERRED_SIZE, 31,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addComponent(jScrollPane1))
						.addContainerGap(23, Short.MAX_VALUE)));

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

		btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		btnDelete.setForeground(new java.awt.Color(32, 136, 203));
		btnDelete.setText("XÓA");
		btnDelete.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				delete();
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

		btnClear.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
		btnClear.setForeground(new java.awt.Color(32, 136, 203));
		btnClear.setText("MỚI");
		btnClear.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				clearForm();
			}
		});

		javax.swing.GroupLayout tabCapnhatLayout = new javax.swing.GroupLayout(tabCapnhat);
		tabCapnhat.setLayout(tabCapnhatLayout);
		tabCapnhatLayout.setHorizontalGroup(tabCapnhatLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(tabCapnhatLayout.createSequentialGroup().addContainerGap()
						.addGroup(tabCapnhatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addGroup(tabCapnhatLayout.createSequentialGroup()
										.addComponent(btnFirst, javax.swing.GroupLayout.PREFERRED_SIZE, 51,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(8, 8, 8)
										.addComponent(btnPrevious, javax.swing.GroupLayout.PREFERRED_SIZE, 51,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(8, 8, 8)
										.addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 51,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 51,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 111,
												Short.MAX_VALUE)
										.addComponent(btnAdd)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(btnDelete)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(btnUpdate)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
										.addComponent(btnClear)))
						.addContainerGap()));
		tabCapnhatLayout.setVerticalGroup(tabCapnhatLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tabCapnhatLayout.createSequentialGroup()
						.addContainerGap()
						.addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGap(18, 18, 18)
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
												.addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
														javax.swing.GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));

		tabNV.addTab("CẬP NHẬT", tabCapnhat);

		spTable.setBorder(null);

		table.setModel(new javax.swing.table.DefaultTableModel(new Object[][] {

		}, new String[] { "Mã Nhân Viên", "Tên Nhân Viên", "Giới Tính", "Ngày Sinh", "Email", "Số Điện Thoại" }) {
			boolean[] canEdit = new boolean[] { false, false, false, false, false, false };

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
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btnAdd;
	private javax.swing.JButton btnClear;
	private javax.swing.JButton btnDelete;
	private javax.swing.JButton btnFirst;
	private javax.swing.JButton btnLast;
	private javax.swing.JButton btnNext;
	private javax.swing.JButton btnPrevious;
	private javax.swing.JButton btnUpdate;
	private javax.swing.ButtonGroup buttonGroup1;
	private javax.swing.ButtonGroup buttonGroup2;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel3;
	private javax.swing.JPanel jPanel4;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JLabel lblEmail;
	private javax.swing.JLabel lblEmail1;
	private javax.swing.JLabel lblEmail2;
	private javax.swing.JLabel lblGioiTinh;
	private javax.swing.JLabel lblGioiTinh1;
	private javax.swing.JLabel lblHinh;
	private javax.swing.JLabel lblHoTen;
	private javax.swing.JLabel lblHoTen1;
	private javax.swing.JLabel lblMaNhanVien;
	private javax.swing.JLabel lblMatKhau;
	private javax.swing.JRadioButton rdbNam;
	private javax.swing.JRadioButton rdbNhanVien;
	private javax.swing.JRadioButton rdbNu;
	private javax.swing.JRadioButton rdbQuanLy;
	private javax.swing.JScrollPane spTable;
	private javax.swing.JPanel tabCapnhat;
	private javax.swing.JPanel tabDanhsach;
	private javax.swing.JTabbedPane tabNV;
	private Swing.Table table;
	private javax.swing.JTextField txtCCCD;
	private javax.swing.JTextArea txtDiaChi;
	private javax.swing.JTextField txtEmail;
	private javax.swing.JTextField txtMaNhanVien;
	private javax.swing.JTextField txtNgaySinh;
	private javax.swing.JTextField txtSoDienThoai;
	private javax.swing.JTextField txtTenNhanVien;
	// End of variables declaration//GEN-END:variables
}
