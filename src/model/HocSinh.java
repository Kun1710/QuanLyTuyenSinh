package model;

import java.sql.Date;

public class HocSinh{
    private String CCCD;
    private String hoVaTen;
    private Date ngaySinh;
    private boolean gioiTinh;
    private String danToc;
    private String diaChi;
    private int idTaiKhoan;
    private int idHS;
    
    public HocSinh() {}

    public HocSinh(String CCCD, String hoVaTen, Date ngaySinh, boolean gioiTinh, 
                  String danToc, String diaChi) {
        this.CCCD = CCCD;
        this.hoVaTen = hoVaTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.danToc = danToc;
        this.diaChi = diaChi;
    }

    public HocSinh(String CCCD, int idTaiKhoan, String hoVaTen, Date ngaySinh, 
                  boolean gioiTinh, String danToc, String diaChi) {
        this.CCCD = CCCD;
        this.idTaiKhoan = idTaiKhoan;
        this.hoVaTen = hoVaTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.danToc = danToc;
        this.diaChi = diaChi;
    }

    // Getter và Setter
    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getHoVaTen() {
        return hoVaTen;
    }

    public void setHoVaTen(String hoVaTen) {
        this.hoVaTen = hoVaTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDanToc() {
        return danToc;
    }

    public void setDanToc(String danToc) {
        this.danToc = danToc;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getIdTaiKhoan() {
        return idTaiKhoan;
    }

    public void setIdTaiKhoan(int idTaiKhoan) {
        this.idTaiKhoan = idTaiKhoan;
    }
    public int getIdHS() {
    	return idHS;
    }
    public void setIdHS(int idHS) {
    	this.idHS = idHS;
    }
    @Override
    public String toString() {
        return "hocSinh{" +
                "CCCD='" + CCCD + '\'' +
                ", hoVaTen='" + hoVaTen + '\'' +
                ", ngaySinh=" + ngaySinh +
                ", gioiTinh=" + gioiTinh +
                ", danToc='" + danToc + '\'' +
                ", diaChi='" + diaChi + '\'' +
                ", idTaiKhoan=" + idTaiKhoan +
                '}';
    }
}