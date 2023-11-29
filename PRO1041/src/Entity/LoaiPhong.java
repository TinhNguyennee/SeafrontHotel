package Entity;

public class LoaiPhong {
	private int MaLoaiPhong;
	private String TenLoaiPhong;
	private double DonGia;
	private int SucChua;
	private String MoTa;
	
	public String toString() {
		return this.TenLoaiPhong;
	}
	
	public LoaiPhong() {
		super();
	}

	public int getMaLoaiPhong() {
		return MaLoaiPhong;
	}

	public void setMaLoaiPhong(int maLoaiPhong) {
		MaLoaiPhong = maLoaiPhong;
	}

	public String getTenLoaiPhong() {
		return TenLoaiPhong;
	}

	public void setTenLoaiPhong(String tenLoaiPhong) {
		TenLoaiPhong = tenLoaiPhong;
	}

	public double getDonGia() {
		return DonGia;
	}

	public void setDonGia(double donGia) {
		DonGia = donGia;
	}

	public int getSucChua() {
		return SucChua;
	}

	public void setSucChua(int sucChua) {
		SucChua = sucChua;
	}

	public String getMoTa() {
		return MoTa;
	}

	public void setMoTa(String moTa) {
		MoTa = moTa;
	}
	
	
}
