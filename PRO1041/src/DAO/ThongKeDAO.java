package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Utils.JDBCHelper;

public class ThongKeDAO {
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
	
	public List<Object[]> getDoanhThuThang(int year){
        String sql = "{CALL doanhThuThang (?)}";
        String[] cols = {"Thang", "TongTien"};
        return this.getListOfArray(sql, cols, year);
    }
	
	public long tongDoanhThuNam(int year) {
		String sql = "SELECT SUM(TongTien) AS TongDoanhThu FROM HoaDon WHERE YEAR(NgayLapHoaDon) = " + String.valueOf(year);
		long tongDoanhThu = 0;
		try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.query(sql);
                while(rs.next()){
                    tongDoanhThu = rs.getLong(1);
                }
            } 
            finally{
                rs.getStatement().getConnection().close();
            }
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return tongDoanhThu;
	}
	
	public List<Object[]> getThongKeKhachHang(int year){
        String sql = "{CALL ThongKeKhachHang (?)}";
        String[] cols = {"MaKhachHang", "TenKhachHang", "NgaySinh", "CCCD", "SDT", "Email", "NgayDatPhong"};
        return this.getListOfArray(sql, cols, year);
    }
	
	public int tongKhachHang(int year) {
		String sql = "SELECT COUNT(*) AS TongSoKhachHang\r\n"
				+ "FROM KhachHang\r\n"
				+ "INNER JOIN DatPhong DP ON DP.maKhachHang = KhachHang.maKhachHang\r\n"
				+ "WHERE YEAR(DP.ngayDatPhong) = " + year;
		int tongKhachHang = 0;
		try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.query(sql);
                while(rs.next()){
                	tongKhachHang = rs.getInt(1);
                }
            } 
            finally{
                rs.getStatement().getConnection().close();
            }
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return tongKhachHang;
	}
	
	public List<Object[]> getThongKeDichVu(int year){
        String sql = "{CALL ThongKeDichVu (?)}";
        String[] cols = {"MADICHVU", "TENDICHVU", "SOLANSUDUNG"};
        return this.getListOfArray(sql, cols, year);
    }
	
	public int tongDichVu() {
		String sql = "SELECT COUNT(MADICHVU) FROM DICHVU";
		int tongDichVu = 0;
		try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.query(sql);
                while(rs.next()){
                	tongDichVu = rs.getInt(1);
                }
            } 
            finally{
                rs.getStatement().getConnection().close();
            }
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return tongDichVu;
	}
	
	public List<Integer> selectYears(){
        String sql = "SELECT DISTINCT YEAR(ngayDatPhong) YEAR FROM DatPhong ORDER BY YEAR DESC";
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JDBCHelper.query(sql);
                while(rs.next()){
                    list.add(rs.getInt(1));
                }
            } 
            finally{
                rs.getStatement().getConnection().close();
            }
        } 
        catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        return list;
    }
}
