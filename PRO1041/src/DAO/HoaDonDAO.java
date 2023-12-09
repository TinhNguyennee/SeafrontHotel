package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Utils.JDBCHelper;

public class HoaDonDAO {
	private List<Object[]> getListOfArray(String sql, String[] cols, Object...args){
        try {
            List<Object[]> list=new ArrayList<>();
            ResultSet rs = JDBCHelper.query(sql, args);
            while(rs.next()){
                Object[] vals = new Object[cols.length];
                for(int i=0; i<cols.length; i++){
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
	
	public List<Object[]> getThongTinHoaDon(){
        String sql = "{CALL ThongTinHoaDon}";
        String[] cols = {"MaHoaDon", "NgayLapHoaDon", "TenKhachHang", "SoDienThoaiKhachHang", "TenPhong", "DichVuDaDung", "TongTien"};
        return this.getListOfArray(sql, cols);
    }
	public List<Object[]> getThongTinHoaDonTheoMa(String id){
        String sql = "{CALL TimThongTinHoaDon(?)}";
        String[] cols = {"MaHoaDon", "NgayLapHoaDon", "TenKhachHang", "SoDienThoaiKhachHang", "TenPhong", "DichVuDaDung", "TongTien"};
        return this.getListOfArray(sql, cols, id);
    }
	
	public List<Object[]> getThongTinDatPhong(){
        String sql = "{CALL ThongTinDatPhong}";
        String[] cols = {"maDatPhong", "tenPhong", "tenLoaiPhong", "tenNhanVien", "ngayBatDau", "ngayKetThuc", "trangThai"};
        return this.getListOfArray(sql, cols);
    }
	public List<Object[]> getThongTinDatPhongTheoMa(String id){
        String sql = "{CALL TimThongTinDatPhong(?)}";
        String[] cols = {"maDatPhong", "tenPhong", "tenLoaiPhong", "tenNhanVien", "ngayBatDau", "ngayKetThuc", "trangThai"};
        return this.getListOfArray(sql, cols, id);
    }
	
	public List<Object[]> getThongTinKhachHang(){
        String sql = "{CALL ThongTinKhachHang}";
        String[] cols = {"maKhachHang", "tenKhachHang", "CCCD", "soDienThoai", "email", "gioiTinh", "ngaySinh", "quocTich"};
        return this.getListOfArray(sql, cols);
    }
	
	public List<Object[]> getThongTinKhachHangTheoTen(String ten){
        String sql = "{CALL TimThongTinKhachHang(?)}";
        String[] cols = {"maKhachHang", "tenKhachHang", "CCCD", "soDienThoai", "email", "gioiTinh", "ngaySinh", "quocTich"};
        return this.getListOfArray(sql, cols, ten);
    }
	
}
