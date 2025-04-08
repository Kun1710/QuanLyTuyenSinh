package model;

public class TaiKhoan {
    public enum LoaiTaiKhoan {
        HS, TRUONG, ADMIN
    }

    private int id;
    private String taiKhoan;
    private String matKhau;
    private String email;
    private LoaiTaiKhoan loaiTaiKhoan;

    // Constructor
    public TaiKhoan() {}

    public TaiKhoan(String taiKhoan, String matKhau, String email, LoaiTaiKhoan loaiTaiKhoan) {
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.email = email;
        this.loaiTaiKhoan = loaiTaiKhoan;
    }
    public TaiKhoan(String taiKhoan, String matKhau) {
    	this.taiKhoan = taiKhoan;
    	this.matKhau = matKhau;
    }
    
    public TaiKhoan(int id, String taiKhoan, String matKhau, String email, LoaiTaiKhoan loaiTaiKhoan) {
        this.id = id;
        this.taiKhoan = taiKhoan;
        this.matKhau = matKhau;
        this.email = email;
        this.loaiTaiKhoan = loaiTaiKhoan;
    }

    // ================= GETTER / SETTER =================

    public String getTaiKhoan() {
        return taiKhoan;
    }

    public void setTaiKhoan(String taiKhoan) {
        this.taiKhoan = taiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LoaiTaiKhoan getLoaiTaiKhoan() {
        return loaiTaiKhoan;
    }

    public void setLoaiTaiKhoan(LoaiTaiKhoan loaiTaiKhoan) {
        this.loaiTaiKhoan = loaiTaiKhoan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
