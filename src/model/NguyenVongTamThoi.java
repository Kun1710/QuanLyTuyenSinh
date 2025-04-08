package model;

public class NguyenVongTamThoi {
    private String maTruong;
    private String maNganh;
    private String phuongThuc;
    
    public NguyenVongTamThoi(String maTruong, String maNganh, String phuongThuc) {
        this.maTruong = maTruong;
        this.maNganh = maNganh;
        this.phuongThuc = phuongThuc;
    }
    
    // Getters
    public String getMaTruong() {
        return maTruong;
    }
    
    public String getMaNganh() {
        return maNganh;
    }
    
    public String getPhuongThuc() {
        return phuongThuc;
    }
    
    // Setters
    public void setMaTruong(String maTruong) {
        this.maTruong = maTruong;
    }
    
    public void setMaNganh(String maNganh) {
        this.maNganh = maNganh;
    }
    
    public void setPhuongThuc(String phuongThuc) {
        this.phuongThuc = phuongThuc;
    }
}