package controller;

import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import dao.TaiKhoanDAO;
import model.TaiKhoan;
import model.TaiKhoan.LoaiTaiKhoan;
import view.DangKyTaiKhoanMoiView;
import view.MenuDangNhapView;

public class DangKyTaiKhoanMoiController {
    private DangKyTaiKhoanMoiView view;

    public DangKyTaiKhoanMoiController(DangKyTaiKhoanMoiView view) {
        this.view = view;
        initController();
    }

    private void initController() {
    	view.getThoat().addActionListener(e -> {
    	    SwingUtilities.invokeLater(() -> {
    	        MenuDangNhapView loginView = new MenuDangNhapView();
    	        new MenuDangNhapController(loginView);
    	        loginView.setVisible(true);
    	    });
    	    view.dispose();
    	});

        view.getDangky().addActionListener(e -> {
            try {
            	String tK = view.getTaiKhoan().getText().trim();
                String mK = new String(view.getMatKhau().getPassword()).trim();
                String xN = new String(view.getXacNhanMK().getPassword()).trim();
                String mail = view.getGmail().getText().trim();

                if (tK.isEmpty() || mK.isEmpty() || xN.isEmpty() || mail.isEmpty()) {
                    showErrorMessage("Vui lòng điền đầy đủ thông tin!", "Lỗi nhập liệu");
                    return;
                }

                if (!mK.equals(xN)) {
                    showErrorMessage("Mật khẩu xác nhận không trùng khớp!", "Lỗi xác nhận mật khẩu");
                    return;
                }

                LoaiTaiKhoan loai = null;
                if (view.getHocSinh().isSelected()) {
                    loai = LoaiTaiKhoan.HS;
                } else if (view.getTruong().isSelected()) {
                    loai = LoaiTaiKhoan.TRUONG;
                } else {
                    showWarningMessage("Vui lòng chọn loại tài khoản (Học sinh / Trường)!", "Thiếu thông tin");
                    return;
                }

                model.TaiKhoan accNew = new TaiKhoan(tK, mK, mail, loai);
                TaiKhoanDAO accDAO = new TaiKhoanDAO();
                
                try {
                    boolean insertSuccess = accDAO.dangKy(accNew);
                    if (insertSuccess) {
                        showInfoMessage("Đăng ký thành công!", "Thông báo");
                        new MenuDangNhapView().setVisible(true);
                        view.dispose();
                    } else {
                        showErrorMessage("Đăng ký thất bại! (Tài khoản đã tồn tại hoặc lỗi DB)", "Thông báo");
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    showErrorMessage("Lỗi kết nối cơ sở dữ liệu!", "Lỗi");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(view, 
                    "Lỗi hệ thống: " + ex.getMessage(), 
                    "Lỗi", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void showErrorMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.ERROR_MESSAGE);
    }

    private void showWarningMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
    }

    private void showInfoMessage(String message, String title) {
        JOptionPane.showMessageDialog(null, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
}