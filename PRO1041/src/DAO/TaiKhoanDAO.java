/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.NhanVien;
import Entity.TaiKhoan;
import Utils.JDBCHelper;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class TaiKhoanDAO extends HotelManagementDAO<TaiKhoan, String> {

    String INSERT_SQL = "INSERT INTO TaiKhoan (tenTaiKhoan, matKhau, ngayTao, maChucVu, maNhanVien) VALUES (?, ?, ?, ?, ?)";
    String UPDATE_SQL = "UPDATE TaiKhoan SET tenTaiKhoan, matKhau, ngayTao, maChucVu  WHERE maNhanVien = ? ";
    String DELETE_SQL = "DELETE FROM TaiKhoan WHERE  maNhanVien = ?";
    String SELECT_ALL_SQL = "SELECT * FROM TaiKhoan";
    String SELECT_BY_ID_SQL = "SELECT * FROM TaiKhoan WHERE tenTaiKhoan = ?";

    @Override
    public void insert(TaiKhoan entity) {
        JDBCHelper.update(INSERT_SQL, entity.getTenTaiKhoan(), 
                                                entity.getMatKhau(), 
                                                entity.getNgayTao(),
                                                entity.getMaChucVu(),
                                                entity.getMaNhanVien());
                                    }

    @Override
    public void update(TaiKhoan entity) {
        JDBCHelper.update(UPDATE_SQL, entity.getTenTaiKhoan(),
                entity.getMatKhau(),
                entity.getNgayTao(),
                entity.getMaChucVu());
    }

    @Override
    public void delete(String id) {
         JDBCHelper.update(DELETE_SQL, id);
    }

    @Override
    public TaiKhoan selectByID(String id) {
        List<TaiKhoan> lst = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (lst.isEmpty()) {
            return null;
        }
        return lst.get(0);

    }

    @Override
    public List<TaiKhoan> selectAll() {
           return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<TaiKhoan> selectBySql(String sql, Object... args) {
         List<TaiKhoan> lst = new ArrayList<TaiKhoan>();
         try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                TaiKhoan entity = new TaiKhoan();
                entity.setTenTaiKhoan(rs.getString("tenTaiKhoan"));
                entity.setMatKhau(rs.getString("matKhau"));
                entity.setNgayTao(rs.getDate("ngayTao"));
                entity.setMaChucVu(rs.getInt("maChucVu"));
                entity.setMaNhanVien(rs.getInt("maNhanVien"));            
                lst.add(entity);
            }
            rs.getStatement().getConnection().close();
            return lst;
        } catch (Exception e) {
         throw new RuntimeException();
        }
    }

}
