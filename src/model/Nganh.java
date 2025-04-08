package model;

import dao.TruongDAO;
import model.TaiKhoan.LoaiTaiKhoan;
import util.SessionManager;

public class Nganh {
    private int idNganh;
    private int idTruong;
    private String maNganh;
    private String tenNganh;
    private int chiTieu;
    private String phuongThuc;
    private KhoiXetTuyen khoiXetTuyen;
    private String uuTien;

    public Nganh() {
    }

    public Nganh(int idTruong, String maNganh, String tenNganh, int chiTieu, 
               String phuongThuc, KhoiXetTuyen khoiXetTuyen, String uuTien) {
        this.idTruong = idTruong;
        this.maNganh = maNganh;
        this.tenNganh = tenNganh;
        this.chiTieu = chiTieu;
        this.phuongThuc = phuongThuc;
        this.khoiXetTuyen = khoiXetTuyen;
        this.uuTien = uuTien;
    }

    // Getters and Setters
    public int getIdNganh() {
        return idNganh;
    }

    public void setIdNganh(int idNganh) {
        this.idNganh = idNganh;
    }

    public int getIdTruong() {
    	SessionManager session = SessionManager.getInstance();
    	TaiKhoan currentUser = session.getCurrentAccount();
    	TruongDAO temp = new TruongDAO();
    	return temp.getIdTruongByIdTaiKhoan(currentUser.getId());
    }


    public void setIdTruong(int idTruong) {
        this.idTruong = idTruong;
    }

    public String getMaNganh() {
        return maNganh;
    }

    public void setMaNganh(String maNganh) {
        this.maNganh = maNganh;
    }

    public String getTenNganh() {
        return tenNganh;
    }

    public void setTenNganh(String tenNganh) {
        this.tenNganh = tenNganh;
    }

    public int getChiTieu() {
        return chiTieu;
    }

    public void setChiTieu(int chiTieu) {
        this.chiTieu = chiTieu;
    }

    public String getPhuongThuc() {
        return phuongThuc;
    }

    public void setPhuongThuc(String phuongThuc) {
        this.phuongThuc = phuongThuc;
    }

    public KhoiXetTuyen getKhoiXetTuyen() {
        return khoiXetTuyen;
    }

    public void setKhoiXetTuyen(KhoiXetTuyen khoiXetTuyen) {
        this.khoiXetTuyen = khoiXetTuyen;
    }

    public String getUuTien() {
        return uuTien;
    }

    public void setUuTien(String uuTien) {
        this.uuTien = uuTien;
    }
    @Override
    public String toString() {
        return maNganh + " - " + tenNganh;
    }
}