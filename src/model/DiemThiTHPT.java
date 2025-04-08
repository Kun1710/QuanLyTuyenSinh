package model;

import dao.HocSinhDAO;
import util.SessionManager;

public class DiemThiTHPT {
    private int idDiemthiDH;
    private int idHS;
    private String khoiThi;
    private float toan;
    private float ly;
    private float hoa;
    private float sinh;
    private float anh;
    private float van;
    private float su;
    private float dia;
    private float gdcd;

    public DiemThiTHPT() {
    }

    public DiemThiTHPT(int idDiemthiDH, int idHS, String khoiThi, float toan, float ly, float hoa, 
                      float sinh, float anh, float van, float su, float dia, float gdcd) {
        this.idDiemthiDH = idDiemthiDH;
        this.idHS = idHS;
        this.khoiThi = khoiThi;
        this.toan = toan;
        this.ly = ly;
        this.hoa = hoa;
        this.sinh = sinh;
        this.anh = anh;
        this.van = van;
        this.su = su;
        this.dia = dia;
        this.gdcd = gdcd;
    }

    // Getters and Setters
    public int getIdDiemthiDH() {
        return idDiemthiDH;
    }

    public void setIdDiemthiDH(int idDiemthiDH) {
        this.idDiemthiDH = idDiemthiDH;
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

    public String getKhoiThi() {
        return khoiThi;
    }

    public void setKhoiThi(String khoiThi) {
        this.khoiThi = khoiThi;
    }

    public float getToan() {
        return toan;
    }

    public void setToan(float toan) {
        this.toan = toan;
    }

    public float getLy() {
        return ly;
    }

    public void setLy(float ly) {
        this.ly = ly;
    }

    public float getHoa() {
        return hoa;
    }

    public void setHoa(float hoa) {
        this.hoa = hoa;
    }

    public float getSinh() {
        return sinh;
    }

    public void setSinh(float sinh) {
        this.sinh = sinh;
    }

    public float getAnh() {
        return anh;
    }

    public void setAnh(float anh) {
        this.anh = anh;
    }

    public float getVan() {
        return van;
    }

    public void setVan(float van) {
        this.van = van;
    }

    public float getSu() {
        return su;
    }

    public void setSu(float su) {
        this.su = su;
    }

    public float getDia() {
        return dia;
    }

    public void setDia(float dia) {
        this.dia = dia;
    }

    public float getGdcd() {
        return gdcd;
    }

    public void setGdcd(float gdcd) {
        this.gdcd = gdcd;
    }
    public float getTongDiemKhoiA() {
    	return this.toan + this.ly + this.hoa;
    }
    public float getTongDiemKhoiB() {
    	return this.toan + this.sinh + this.hoa;
    }
    public float getTongDiemKhoiC() {
    	return this.van + this.su + this.dia;
    }
    public float getTongDiemKhoiD() {
    	return this.toan + this.van + this.anh;
    }
}
