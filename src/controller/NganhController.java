package controller;

import java.awt.EventQueue;
import javax.swing.JOptionPane;

import dao.NganhDAO;
import model.TaiKhoan;
import util.SessionManager;
import view.NganhView;
import view.ThemNganhMoiView;
import view.DanhSachNganhView;
import view.MenuTruongView;

public class NganhController implements NganhParentController{
    private NganhView view;

    public NganhController(NganhView view) {
        this.view = view;
        initController();
    }

    private void initController() {
        // Nút thêm ngành mới
        view.getBtnAddNganh().addActionListener(e -> handleAddNganh());
        
        // Nút danh sách ngành học
        view.getBtnListNganh().addActionListener(e -> handleListNganh());
        
        // Nút quay lại
        view.getBtnQuayLai().addActionListener(e -> handleQuayLai());
    }

    private void handleAddNganh() {
        EventQueue.invokeLater(() -> {
            try {
                TaiKhoan currentAccount = SessionManager.getInstance().getCurrentAccount();
                int idTruong = new NganhDAO().getIdTruongByTaiKhoan(currentAccount.getId());
                
                ThemNganhMoiView frame = new ThemNganhMoiView(idTruong, null);
                frame.setVisible(true);
                new ThemNganhMoiController(frame, idTruong); // Pass this controller as parent
                //view.dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(view, 
                    "Lỗi khi mở trang đăng ký ngành mới: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void handleListNganh() {
        EventQueue.invokeLater(() -> {
            try {
                DanhSachNganhView frame = new DanhSachNganhView();
                frame.setVisible(true);
                new DanhSachNganhController(frame);
               // new listNganhController(frame, this); // Pass this controller as parent
                //view.dispose();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(view, 
                    "Lỗi khi mở trang danh sách ngành học: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    @Override
    public void refreshNganhData() {
        // Có thể để trống nếu không cần
    }
    
    @Override
    public void showNganhView() {
        EventQueue.invokeLater(() -> {
            NganhView frame = new NganhView();
            frame.setVisible(true);
            new NganhController(frame);
        });
    }
    private void handleQuayLai() {
        MenuTruongView frame = new MenuTruongView();
        frame.setVisible(true);
        new MenuTruongController(frame);
        view.dispose();
    }
}