package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import view.HoSoXetTuyenView;
import view.DangKyNguyenVongView;
import view.DanhSachNguyenVongView;
import view.MenuTruongView;
import view.MenuHocSinhView;

public class HoSoXetTuyenController {
    private HoSoXetTuyenView view;

    public HoSoXetTuyenController(HoSoXetTuyenView view) {
        this.view = view;
        initController();
    }

    private void initController() {
        // Nút đăng ký nguyện vọng
        view.getDkNguyenVongButton().addActionListener(e -> handleDangKyNguyenVong());
        
        // Nút danh sách nguyện vọng
        view.getListNguyenVongButton().addActionListener(e -> handleListNguyenVong());
        
        // Nút quay lại
        view.getQuayLaiButton().addActionListener(e -> handleQuayLai());
    }

    private void handleDangKyNguyenVong() {
        EventQueue.invokeLater(() -> {
            try {
                DangKyNguyenVongView frame = new DangKyNguyenVongView();
                frame.setVisible(true);
               new DangKyNguyenVongMoiController(frame);
                //view.dispose(); // Đóng cửa sổ hiện tại
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(view, "Lỗi khi mở trang đăng ký nguyện vọng: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void handleListNguyenVong() {
        EventQueue.invokeLater(() -> {
            try {
                DanhSachNguyenVongView frame = new DanhSachNguyenVongView();
                frame.setVisible(true);
                new DanhSachNguyenVongController(frame);
                //view.dispose(); // Đóng cửa sổ hiện tại
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(view, "Lỗi khi mở trang danh sách nguyện vọng: " + e.getMessage(), 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private void handleQuayLai() {
        MenuHocSinhView frame = new MenuHocSinhView();
        frame.setVisible(true);
        
        // Khởi tạo controller
        new MenuHocSinhController(frame);
        view.dispose();
        
    }
}