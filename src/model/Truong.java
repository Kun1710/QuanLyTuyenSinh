package model;

import dao.HocSinhDAO;
import util.SessionManager;

public class Truong {
    private int idTruong;
    private int idTaiKhoan;
    private String maTruong;
    private String tenTruong;
    private String diaChi;
    private String website;

    public Truong() {
    }

    public Truong(int idTruong, int idTaiKhoan, String maTruong, String tenTruong, String diaChi, String website) {
        this.idTruong = idTruong;
        this.idTaiKhoan = idTaiKhoan;
        this.maTruong = maTruong;
        this.tenTruong = tenTruong;
        this.diaChi = diaChi;
        this.website = website;
    }
    public Truong(String maTruong, String tenTruong, String diaChi, String website) {
        this.maTruong = maTruong;
        this.tenTruong = tenTruong;
        this.diaChi = diaChi;
        this.website = website;
    }
    // Getters and Setters
    public int getIdTruong() {
        return idTruong;
    }

    public void setIdTruong(int idTruong) {
        this.idTruong = idTruong;
    }

    public int getIdTaiKhoan() {
    	SessionManager session = SessionManager.getInstance();
    	TaiKhoan currentUser = session.getCurrentAccount();
        return currentUser.getId();
    }

    public void setIdTaiKhoan(int idTaiKhoan) {
        this.idTaiKhoan = idTaiKhoan;
    }

    public String getMaTruong() {
        return maTruong;
    }

    public void setMaTruong(String maTruong) {
        this.maTruong = maTruong;
    }

    public String getTenTruong() {
        return tenTruong;
    }

    public void setTenTruong(String tenTruong) {
        this.tenTruong = tenTruong;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
    @Override
    public String toString() {
        return maTruong + " - " + tenTruong;
    }
}