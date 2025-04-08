package model;

import java.sql.Date;

import dao.HocSinhDAO;
import util.SessionManager;

public class ChungChi {
    private int idChungChi;
    private int idHS;
    private String tenChungChi;
    private Date ngayCap;
    private String linkChungChi;

    public ChungChi() {
    }
    public ChungChi(String tenChungChi, Date ngayCap, String linkChungChi) {
        this.tenChungChi = tenChungChi;
        this.ngayCap = ngayCap;
        this.linkChungChi = linkChungChi;
    }
    public ChungChi(int idChungChi, int idHS, String tenChungChi, Date ngayCap, String linkChungChi) {
        this.idChungChi = idChungChi;
        this.idHS = idHS;
        this.tenChungChi = tenChungChi;
        this.ngayCap = ngayCap;
        this.linkChungChi = linkChungChi;
    }

    // Getters and Setters
    public int getIdChungChi() {
        return idChungChi;
    }

    public void setIdChungChi(int idChungChi) {
        this.idChungChi = idChungChi;
    }

    public int getIdHS() {
    	HocSinhDAO tempHSDAO = new HocSinhDAO();
    	SessionManager session = SessionManager.getInstance();
    	TaiKhoan currentUser = session.getCurrentAccount();
        return tempHSDAO.getIdHSByIdTaiKhoan(currentUser.getId());
    }

    public void setIdHS(int idHS) {
        this.idHS = idHS;
    }

    public String getTenChungChi() {
        return tenChungChi;
    }

    public void setTenChungChi(String tenChungChi) {
        this.tenChungChi = tenChungChi;
    }

    public Date getNgayCap() {
        return ngayCap;
    }

    public void setNgayCap(Date ngayCap) {
        this.ngayCap = ngayCap;
    }

    public String getLinkChungChi() {
        return linkChungChi;
    }

    public void setLinkChungChi(String linkChungChi) {
        this.linkChungChi = linkChungChi;
    }
}