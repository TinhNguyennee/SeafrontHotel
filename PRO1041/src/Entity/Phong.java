package Entity;

public class Phong {
	private int MaPhong;
	private String TenPhong;
	private String TrangThaiPhong;
	private double GiaPhong; 
	private int MaLoaiPhong;
	
	
	public Phong() {
		super();
	}
	public int getMaPhong() {
		return MaPhong;
	}
	public void setMaPhong(int maPhong) {
		MaPhong = maPhong;
	}
	public String getTenPhong() {
		return TenPhong;
	}
	public void setTenPhong(String tenPhong) {
		TenPhong = tenPhong;
	}
	public String getTrangThaiPhong() {
		return TrangThaiPhong;
	}
	public void setTrangThaiPhong(String trangThaiPhong) {
		TrangThaiPhong = trangThaiPhong;
	}
	
	public double getGiaPhong() {
		return GiaPhong;
	}
	public void setGiaPhong(double giaPhong) {
		GiaPhong = giaPhong;
	}
	public int getMaLoaiPhong() {
		return MaLoaiPhong;
	}
	public void setMaLoaiPhong(int maLoaiPhong) {
		MaLoaiPhong = maLoaiPhong;
	}
	
	
}
