package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import dao.TaiKhoanDAO;
import model.TaiKhoan;
import view.QuenMatKhauView;
import view.MenuDangNhapView;

public class QuenMatKhauController {
    private QuenMatKhauView view;
    private TaiKhoanDAO accountDAO;

    public QuenMatKhauController(QuenMatKhauView view) {
        this.view = view;
        this.accountDAO = new TaiKhoanDAO();
        initController();
    }

    private void initController() {
        view.getButton_thoat().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuDangNhapView().setVisible(true);
                view.dispose();
            }
        });

        view.getButton_guiMK().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleForgotPassword();
            }
        });
    }

    private void handleForgotPassword() {
        String tK = view.getTaiKhoan().getText();
        String email = view.getGmail().getText();

        if (tK.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ tài khoản và email!", 
                "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        TaiKhoan acc = new TaiKhoan(tK, "", email, null);
        try {
            String newPass = accountDAO.quenMatKhau(acc);
            if (newPass != null) {
                JOptionPane.showMessageDialog(null, "Mật khẩu mới của bạn: " + newPass, 
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Tài khoản hoặc email không chính xác!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi kết nối CSDL!", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}