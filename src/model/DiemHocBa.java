package model;

//import java.sql.Date;

import dao.HocSinhDAO;
import util.SessionManager;

public class DiemHocBa {
    private int idDiemHocBa;
    private int idHS;
    private String namHoc;
    private float toan;
    private float ly;
    private float hoa;
    private float van;
    private float su;
    private float dia;
    private float gdcd;
    private float anh;
    private float sinh;

    public DiemHocBa() {}

    public DiemHocBa(int idHS, String namHoc, float toan, float ly, float hoa, 
                    float van, float su, float dia, float gdcd, float anh, float sinh) {
        this.idHS = idHS;
        this.namHoc = namHoc;
        this.toan = toan;
        this.ly = ly;
        this.hoa = hoa;
        this.van = van;
        this.su = su;
        this.dia = dia;
        this.gdcd = gdcd;
        this.anh = anh;
        this.sinh = sinh;
    }

    // Getters and Setters
    public int getidDiemHocBa() {
        return idDiemHocBa;
    }

    public void setidDiemHocBa(int idDiemHocBa) {
        this.idDiemHocBa = idDiemHocBa;
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

    public String getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(String namHoc) {
        this.namHoc = namHoc;
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

    public float getAnh() {
        return anh;
    }

    public void setAnh(float anh) {
        this.anh = anh;
    }

    public float getSinh() {
        return sinh;
    }

    public void setSinh(float sinh) {
        this.sinh = sinh;
    }

    @Override
    public String toString() {
        return "diemHocBa{" +
                "idDiemHocBa=" + idDiemHocBa +
                ", idHS=" + idHS +
                ", namHoc='" + namHoc + '\'' +
                ", toan=" + toan +
                ", ly=" + ly +
                ", hoa=" + hoa +
                ", van=" + van +
                ", su=" + su +
                ", dia=" + dia +
                ", gdcd=" + gdcd +
                ", anh=" + anh +
                ", sinh=" + sinh +
                '}';
    }
}