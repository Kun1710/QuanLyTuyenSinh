package util;

import model.NguyenVongTamThoi;

public class TemporaryDataStorage {
    private static TemporaryDataStorage instance;
    private NguyenVongTamThoi nguyenVongTamThoi;
    
    private TemporaryDataStorage() {}
    
    public static synchronized TemporaryDataStorage getInstance() {
        if (instance == null) {
            instance = new TemporaryDataStorage();
        }
        return instance;
    }
    
    public void luuNguyenVongTamThoi(String maTruong, String maNganh, String phuongThuc) {
        this.nguyenVongTamThoi = new NguyenVongTamThoi(maTruong, maNganh, phuongThuc);
    }
    
    public NguyenVongTamThoi layNguyenVongTamThoi() {
        return this.nguyenVongTamThoi;
    }
    
    public void xoaNguyenVongTamThoi() {
        this.nguyenVongTamThoi = null;
    }
}