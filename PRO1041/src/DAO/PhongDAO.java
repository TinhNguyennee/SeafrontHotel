package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Entity.LoaiPhong;
import Entity.Phong;
import Utils.JDBCHelper;

public class PhongDAO extends HotelManagementDAO<Phong, Integer>{
	String INSERT_SQL = "INSERT INTO Phong VALUES (?, ?, ?, ?, ?)";
    String UPDATE_SQL = "UPDATE Phong SET TenPhong = ?, TrangThaiPhong = ?, GiaPhong = ?, MaLoaiPhong = ? WHERE MaPhong = ?";
    String DELETE_SQL = "DELETE FROM Phong WHERE MaPhong = ?";
    String SELECT_ALL_SQL = "SELECT * FROM PHONG";
    String SELECT_DONGIA = "SELECT DONGIA FROM LOAIPHONG WHERE MALOAIPHONG = ?";
    
	@Override
	public void insert(Phong entity) {
		// TODO Auto-generated method stub
		JDBCHelper.update(INSERT_SQL, entity.getMaPhong(), entity.getTenPhong(), entity.getGiaPhong(), entity.getTrangThaiPhong(), entity.getMaLoaiPhong());
	}

	@Override
	public void update(Phong entity) {
		// TODO Auto-generated method stub
		JDBCHelper.update(UPDATE_SQL, entity.getTenPhong(), entity.getTrangThaiPhong(), entity.getGiaPhong(), entity.getMaLoaiPhong(), entity.getMaPhong());
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		JDBCHelper.update(DELETE_SQL, id);
	}

	@Override
	public Phong selectByID(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Phong> selectAll() {
		// TODO Auto-generated method stub
		return this.selectBySql(SELECT_ALL_SQL);
	}

	@Override
	protected List<Phong> selectBySql(String sql, Object... args) {
		// TODO Auto-generated method stub
		List<Phong> lstPhong = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.query(sql, args);
                while (rs.next()) {
                    Phong p = new Phong();
                    p.setMaPhong(rs.getInt(1));
                    p.setTenPhong(rs.getString(2));
                    p.setGiaPhong(rs.getDouble(3));
                    p.setTrangThaiPhong(rs.getString(4));
                    p.setMaLoaiPhong(rs.getInt(5));
                    lstPhong.add(p);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lstPhong;
	}

}
