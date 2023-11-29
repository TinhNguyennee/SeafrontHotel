package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Entity.LoaiPhong;
import Entity.NhanVien;
import Utils.JDBCHelper;

public class LoaiPhongDAO extends HotelManagementDAO<LoaiPhong, Integer> {
	String INSERT_SQL = "INSERT INTO LoaiPhong VALUES (?, ?, ?, ?, ?)";
	String UPDATE_SQL = "UPDATE LoaiPhong SET TenLoaiPhong = ?, Gia = ?, SucChua = ?, MoTa = ?  WHERE maLoaiPhong = ?";
	String DELETE_SQL = "DELETE FROM LoaiPhong WHERE  maLoaiPhong = ?";
	String SELECT_ALL_SQL = "SELECT * FROM LoaiPhong";
	String SELECT_BY_ID_SQL = "SELECT * FROM LoaiPhong WHERE maLoaiPhong = ?";

	@Override
	public void insert(LoaiPhong entity) {
		// TODO Auto-generated method stub
		JDBCHelper.update(INSERT_SQL, entity.getMaLoaiPhong(), entity.getTenLoaiPhong(), entity.getDonGia(),
				entity.getSucChua(), entity.getMoTa());
	}

	@Override
	public void update(LoaiPhong entity) {
		// TODO Auto-generated method stub
		JDBCHelper.update(UPDATE_SQL, entity.getTenLoaiPhong(), entity.getDonGia(), entity.getSucChua(),
				entity.getMoTa(), entity.getMaLoaiPhong());
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		JDBCHelper.update(DELETE_SQL, id);
	}

	@Override
	public LoaiPhong selectByID(Integer id) {
		// TODO Auto-generated method stub
		List<LoaiPhong> lst = this.selectBySql(SELECT_BY_ID_SQL, id);
		if (lst.isEmpty()) {
			return null;
		}
		return lst.get(0);
	}

	@Override
	public List<LoaiPhong> selectAll() {
		// TODO Auto-generated method stub
		return this.selectBySql(SELECT_ALL_SQL);
	}

	@Override
	protected List<LoaiPhong> selectBySql(String sql, Object... args) {
		List<LoaiPhong> lstLoaiPhong = new ArrayList<>();
		try {
			ResultSet rs = null;
			try {
				rs = JDBCHelper.query(sql, args);
				while (rs.next()) {
					LoaiPhong cd = new LoaiPhong();
					cd.setMaLoaiPhong(rs.getInt(1));
					cd.setTenLoaiPhong(rs.getString(2));
					cd.setDonGia(rs.getDouble(3));
					cd.setSucChua(rs.getInt(4));
					cd.setMoTa(rs.getString(5));
					lstLoaiPhong.add(cd);
				}
			} finally {
				rs.getStatement().getConnection().close();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return lstLoaiPhong;
	}

}
