package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Entity.DichVu;
import Entity.LoaiPhong;
import Utils.JDBCHelper;

public class DichVuDAO extends HotelManagementDAO<DichVu, Integer>{
	String INSERT_SQL = "INSERT INTO DichVu VALUES (?, ?, ?)";
    String UPDATE_SQL = "UPDATE DichVu SET TenDichVu = ?, DonGia = ? WHERE maDichVu = ?";
    String DELETE_SQL = "DELETE FROM DichVu WHERE maDichVu = ?";
    String SELECT_ALL_SQL = "SELECT * FROM DichVu";
    String SELECT_BY_ID_SQL = "SELECT * FROM DichVu WHERE maDichVu = ?";
	
	@Override
	public void insert(DichVu entity) {
		// TODO Auto-generated method stub
		JDBCHelper.update(INSERT_SQL, entity.getMaDichVu(), entity.getTenDichVu(), entity.getDonGia());
	}

	@Override
	public void update(DichVu entity) {
		// TODO Auto-generated method stub
		JDBCHelper.update(UPDATE_SQL, entity.getTenDichVu(), entity.getDonGia(), entity.getMaDichVu());
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		JDBCHelper.update(DELETE_SQL, id);
	}

	@Override
	public DichVu selectByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DichVu> selectAll() {
		// TODO Auto-generated method stub
		return this.selectBySql(SELECT_ALL_SQL);
	}

	@Override
	protected List<DichVu> selectBySql(String sql, Object... args) {
		// TODO Auto-generated method stub
		List<DichVu> lstDichVu = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.query(sql, args);
                while (rs.next()) {
                    DichVu dv = new DichVu();
                    dv.setMaDichVu(rs.getInt(1));
                    dv.setTenDichVu(rs.getString(2));
                    dv.setDonGia(rs.getDouble(3));
                    lstDichVu.add(dv);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lstDichVu;
	}

}
