/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.NhanVien;
import Entity.TaiKhoan;
import Utils.JDBCHelper;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL
 */
public class TaiKhoanDAO extends HotelManagementDAO<TaiKhoan, String> {

    String INSERT_SQL = "INSERT INTO TaiKhoan (tenTaiKhoan, matKhau, ngayTao, maChucVu, maNhanVien) VALUES (?, ?, ?, ?, ?)";
    String UPDATE_SQL = "UPDATE TaiKhoan SET matKhau = ?, ngayTao = ?, maChucVu  = ?, maNhanVien = ? WHERE tenTaiKhoan = ? ";
    String DELETE_SQL = "DELETE FROM TaiKhoan WHERE  maTaiKhoan = ?";
    String SELECT_ALL_SQL = "SELECT * FROM TaiKhoan";
    String SELECT_BY_ID_SQL = "SELECT * FROM TaiKhoan WHERE tenTaiKhoan = ?";
    String SELECT_BY_EMAIL = "SELECT tk.matKhau  \n"
            + "FROM  TaiKhoan tk\n"
            + "JOIN NhanVien nv\n"
            + "ON tk.maNhanVien = nv.maNhanVien\n"
            + "WHERE nv.Email =  ? ";
 
    String SELECT_CHECK_PASS = "SELECT nv.maNhanVien,\n"
            + "nv.CCCD,\n"
            + "nv.tenNhanVien,\n"
            + "nv.ngaySinh,\n"
            + "nv.soDienThoai,\n"
            + "nv.diaChi,\n"
            + "nv.gioiTinh\n"
            + "FROM  TaiKhoan tk\n"
            + "JOIN NhanVien nv\n"
            + "ON tk.maNhanVien = nv.maNhanVien\n"
            + "WHERE nv.Email = ? ";

    @Override

    public void insert(TaiKhoan entity) {
        JDBCHelper.update(INSERT_SQL,
                entity.getTenTaiKhoan(),
                entity.getMatKhau(),
                entity.getNgayTao(),
                entity.getMaChucVu(),
                entity.getMaNhanVien());
    }

    @Override
    public void update(TaiKhoan entity) {
        JDBCHelper.update(UPDATE_SQL,
                entity.getMatKhau(),
                entity.getNgayTao(),
                entity.getMaChucVu(),
                entity.getMaNhanVien(),
                entity.getTenTaiKhoan());
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

    public String getPass(String email) {
        try {
            ResultSet rs = JDBCHelper.query(SELECT_BY_EMAIL, email);
            if (rs.next()) {
                String matKhau = rs.getString("matKhau");
                rs.getStatement().getConnection().close();
                return matKhau;
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public String selectByMail(String email) {
       
        try {
                String sql = "select tk.maNhanVien,"
                  + " tk.tenTaiKhoan,"
                  + " tk.MatKhau, "
                  + " nv.Email from TaiKhoan tk "
                  + "join NhanVien nv "
                  + "on  tk.maNhanVien = nv.maNhanVien "
                        + "where nv.Email = ?";
             ResultSet rs  = JDBCHelper.query(sql, email);
             if(rs.next()) {
//                 TaiKhoan  taikhoan = new TaiKhoan();
                 
                   String matKhau = rs.getString("matKhau");
                     rs.getStatement().getConnection().close();
                   return matKhau;
             }
         rs.getStatement().getConnection().close();
        } catch (Exception e) {
         e.printStackTrace();
        }
        return null;
    }

    public TaiKhoan selectByMail1(String email) {
    try {
        String sql = "SELECT tk.maNhanVien, tk.tenTaiKhoan, tk.MatKhau, nv.Email "
                   + "FROM TaiKhoan tk "
                   + "JOIN NhanVien nv ON tk.maNhanVien = nv.maNhanVien "
                   + "WHERE nv.Email = ?";
        
        ResultSet rs = JDBCHelper.query(sql, email);
        
        if (rs.next()) {
            TaiKhoan taiKhoan = new TaiKhoan();
      
            taiKhoan.setTenTaiKhoan(rs.getString("tenTaiKhoan"));
            taiKhoan.setMatKhau(rs.getString("MatKhau"));
         
            
            rs.getStatement().getConnection().close();
            
            return taiKhoan;
        }
        
        rs.getStatement().getConnection().close();
    } catch (Exception e) {
 e.printStackTrace();
    }
    return null;
}
}
