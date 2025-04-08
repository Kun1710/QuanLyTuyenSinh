package controller;

import view.DoiMatKhauView;
import model.TaiKhoan;
import dao.TaiKhoanDAO;
import util.SessionManager;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class DoiMatKhauController {
    private DoiMatKhauView view;
    private TaiKhoanDAO accountDao;

    public DoiMatKhauController(DoiMatKhauView view) {
        this.view = view;
        this.accountDao = new TaiKhoanDAO();
        initController();
    }

    private void initController() {
        // Xử lý nút Xác nhận
        view.getButtonXacNhan().addActionListener(e -> handleChangePassword());
        
        // Xử lý nút Thoát
        view.getButtonThoat().addActionListener(e -> view.dispose());
    }

    private void handleChangePassword() {
        try {
            // Lấy thông tin từ view
            String oldPass = new String(view.getMatKhauCu().getPassword());
            String newPass = new String(view.getMatKhauMoi().getPassword());
            String confirmPass = new String(view.getMatKhauMoi2().getPassword());

            // Kiểm tra các trường không được trống
            if (oldPass.isEmpty() || newPass.isEmpty() || confirmPass.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Kiểm tra mật khẩu mới và xác nhận khớp nhau
            if (!newPass.equals(confirmPass)) {
                JOptionPane.showMessageDialog(view, "Mật khẩu mới và xác nhận không khớp", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Kiểm tra mật khẩu mới khác mật khẩu cũ
            if (oldPass.equals(newPass)) {
                JOptionPane.showMessageDialog(view, "Mật khẩu mới phải khác mật khẩu cũ", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Lấy thông tin tài khoản từ session
            TaiKhoan currentAccount = SessionManager.getInstance().getCurrentAccount();
            if (currentAccount == null) {
                JOptionPane.showMessageDialog(view, "Phiên đăng nhập không hợp lệ", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Kiểm tra mật khẩu cũ đúng không
            TaiKhoan tempAccount = new TaiKhoan(currentAccount.getTaiKhoan(), oldPass);
            if (!accountDao.login(tempAccount)) {
                JOptionPane.showMessageDialog(view, "Mật khẩu cũ không đúng", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Thực hiện đổi mật khẩu
            if (accountDao.changePass(currentAccount, newPass)) {
                JOptionPane.showMessageDialog(view, "Đổi mật khẩu thành công", 
                    "Thành công", JOptionPane.INFORMATION_MESSAGE);
                view.dispose();
            } else {
                JOptionPane.showMessageDialog(view, "Đổi mật khẩu thất bại", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi kết nối cơ sở dữ liệu", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}