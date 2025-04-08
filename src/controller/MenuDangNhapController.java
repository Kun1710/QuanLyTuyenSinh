package controller;

import javax.swing.*;
import model.TaiKhoan;
import model.TaiKhoan.LoaiTaiKhoan;
import util.SessionManager;
import dao.TaiKhoanDAO;
import view.MenuDangNhapView;
import view.MenuAdminView;
import view.DangKyTaiKhoanMoiView;
import view.QuenMatKhauView;
import view.MenuHocSinhView;
import view.MenuTruongView;

public class MenuDangNhapController {
    private MenuDangNhapView view;

    public MenuDangNhapController(MenuDangNhapView view) {
        this.view = view;
    }

    public void xulyDangnhap() {
        String taiKhoan = view.getTaiKhoan();
        String matKhau = view.getMatKhau();

        if (taiKhoan.isEmpty() || matKhau.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ tài khoản và mật khẩu!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        LoaiTaiKhoan loaiTaiKhoan = null;
        
        TaiKhoan accnew = new TaiKhoan(taiKhoan, matKhau);
        TaiKhoanDAO DAOnew = new TaiKhoanDAO();
        loaiTaiKhoan = DAOnew.getLoaiTK(accnew);
        try {
            boolean validLogin = DAOnew.login(accnew);
            if (!validLogin) {
                JOptionPane.showMessageDialog(view, "Sai tài khoản hoặc mật khẩu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            LoaiTaiKhoan LoaiTK = DAOnew.getLoaiTK(accnew);
            if (LoaiTK == null || !LoaiTK.equals(loaiTaiKhoan)) {
                JOptionPane.showMessageDialog(view, "Loại tài khoản không khớp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            SessionManager.getInstance().setCurrentAccount(accnew);
            JOptionPane.showMessageDialog(view, "Đăng nhập thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);

            if (loaiTaiKhoan.equals(LoaiTaiKhoan.HS)) {
                MenuHocSinhView frame = new MenuHocSinhView();
                frame.setVisible(true);
                
                // Khởi tạo controller
                new MenuHocSinhController(frame);
            } else if (loaiTaiKhoan.equals(LoaiTaiKhoan.TRUONG)) {
                MenuTruongView frame = new MenuTruongView();
                frame.setVisible(true);
                new MenuTruongController(frame);
            }else {
                MenuAdminView frame = new MenuAdminView();
                frame.setVisible(true);
                new MenuAdminController(frame);
            }
            view.dispose();
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi kết nối cơ sở dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void moDangKy() {
        SwingUtilities.invokeLater(() -> {
            DangKyTaiKhoanMoiView dangKyView = new DangKyTaiKhoanMoiView();
            new DangKyTaiKhoanMoiController(dangKyView);
            dangKyView.setVisible(true);
        });
        view.dispose();
    }
    public void moQuenMatKhau() {
        SwingUtilities.invokeLater(() -> {
            QuenMatKhauView quenMKView = new QuenMatKhauView();
            new QuenMatKhauController(quenMKView);
            quenMKView.setVisible(true);
        });
        view.dispose();
    }

}
