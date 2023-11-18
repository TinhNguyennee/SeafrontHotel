
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.NhanVien;
import Utils.JDBCHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO extends HotelManagementDAO<NhanVien, String> {

    String INSERT_SQL = "INSERT INTO NhanVien (CCCD, tenNhanVien, ngaySinh, soDienThoai, diaChi, gioiTinh, Email, hinh, chucVu) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?   )";
    String UPDATE_SQL = "UPDATE NhanVien SET CCCD = ?, tenNhanVien = ?, ngaySinh = ?, soDienThoai = ?, diaChi = ?, gioiTinh = ?, Email = ?, hinh = ?, chucVu = ?  WHERE maNhanVien = ?";
    String DELETE_SQL = "DELETE FROM NhanVien WHERE  maNhanVien = ?";
    String SELECT_ALL_SQL = "SELECT * FROM NhanVien";
    String SELECT_BY_ID_SQL = "SELECT * FROM NhanVien WHERE maNhanVien = ?";

    @Override
    public void insert(NhanVien entity) {
        JDBCHelper.update(INSERT_SQL, entity.getCCCD(), entity.getTenNhanVien(), entity.getNgaySinh(), 
                entity.getSoDienThoai(), entity.getDiaChi(), entity.isGioiTinh(), entity.getEmail(), entity.getHinh(), entity.getChucVu());
    }

    @Override
    public void update(NhanVien entity) {
        JDBCHelper.update(UPDATE_SQL, entity.getCCCD(), entity.getTenNhanVien(), entity.getNgaySinh(), entity.getSoDienThoai()
        , entity.getDiaChi(), entity.isGioiTinh(), entity.getEmail(), entity.getHinh(), entity.getChucVu());

    }

    @Override
    public void delete(String id) {
        JDBCHelper.update(DELETE_SQL, id);
    }

    @Override
    public NhanVien selectByID(String id) {
        List<NhanVien> lst = this.selectBySql(SELECT_BY_ID_SQL, id);
        if (lst.isEmpty()) {
            return null;
        }
        return lst.get(0);
    }

    @Override
    public List<NhanVien> selectAll() {
        return this.selectBySql(SELECT_ALL_SQL);
    }

    @Override
    protected List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> lst = new ArrayList<NhanVien>();
        try {
            ResultSet rs = JDBCHelper.query(sql, args);
            while (rs.next()) {
                NhanVien entity = new NhanVien();
                entity.setMaNhanVien(rs.getInt("maNhanVien"));
                entity.setCCCD(rs.getString("CCCD"));
                entity.setTenNhanVien(rs.getString("tenNhanVien"));
                entity.setNgaySinh(rs.getDate("ngaySinh"));
                entity.setSoDienThoai(rs.getString("soDienThoai"));
                entity.setDiaChi(rs.getString("diaChi"));
                entity.setGioiTinh(true);
                entity.setEmail(rs.getString("email"));
                entity.setHinh(rs.getString("hinh"));
                entity.setChucVu(rs.getString("chucVu"));
                lst.add(entity);
            }
            rs.getStatement().getConnection().close();
            return lst;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
