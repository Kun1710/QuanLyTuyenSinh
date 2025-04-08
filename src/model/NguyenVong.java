package model;

public class NguyenVong {
    private int idNguyenVong;
    private int idHoSo;
    private int thuTuNguyenVong;
    private int idTruong;
    private int idNganh;
    private String phuongThucXetTuyen;
    private String trangThai;
    
    // Constructors
    public NguyenVong() {}
    
    public NguyenVong(int idNguyenVong, int idHoSo, int thuTuNguyenVong, int idTruong, 
                     int idNganh, String phuongThucXetTuyen, String trangThai) {
        this.idNguyenVong = idNguyenVong;
        this.idHoSo = idHoSo;
        this.thuTuNguyenVong = thuTuNguyenVong;
        this.idTruong = idTruong;
        this.idNganh = idNganh;
        this.phuongThucXetTuyen = phuongThucXetTuyen;
        this.trangThai = trangThai;
    }
    
    // Getters and Setters
    public int getIdNguyenVong() {
        return idNguyenVong;
    }
    
    public void setIdNguyenVong(int idNguyenVong) {
        this.idNguyenVong = idNguyenVong;
    }
    
    public int getIdHoSo() {
        return idHoSo;
    }
    
    public void setIdHoSo(int idHoSo) {
        this.idHoSo = idHoSo;
    }
    
    public int getThuTuNguyenVong() {
        return thuTuNguyenVong;
    }
    
    public void setThuTuNguyenVong(int thuTuNguyenVong) {
        this.thuTuNguyenVong = thuTuNguyenVong;
    }
    
    public int getIdTruong() {
        return idTruong;
    }
    
    public void setIdTruong(int idTruong) {
        this.idTruong = idTruong;
    }
    
    public int getIdNganh() {
        return idNganh;
    }
    
    public void setIdNganh(int idNganh) {
        this.idNganh = idNganh;
    }
    
    public String getPhuongThucXetTuyen() {
        return phuongThucXetTuyen;
    }
    
    public void setPhuongThucXetTuyen(String phuongThucXetTuyen) {
        this.phuongThucXetTuyen = phuongThucXetTuyen;
    }
    
    public String getTrangThai() {
        return trangThai;
    }
    
    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}