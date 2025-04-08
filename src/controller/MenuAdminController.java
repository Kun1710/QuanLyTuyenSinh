package controller;

import view.MenuAdminView;
import view.DanhSachHocSinhView;
import view.DanhSachTruongView;
import view.DoiMatKhauView;
import view.MenuDangNhapView;
import view.XetTuyenView;

import javax.swing.*;

import util.SessionManager;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuAdminController {
    private MenuAdminView view;

    public MenuAdminController(MenuAdminView view) {
        this.view = view;
        initController();
    }

    private void initController() {
        // Xử lý nút Danh sách học sinh
        view.listHocSinh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // JOptionPane.showMessageDialog(view, "Mở danh sách học sinh");
                // Thêm logic xử lý tại đây
				DanhSachHocSinhView frame = new DanhSachHocSinhView();
				frame.setVisible(true);
				new DanhSachHocSinhController(frame);
				//view.dispose();
            }
        });

        // Xử lý nút Danh sách trường
        view.listTruong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // JOptionPane.showMessageDialog(view, "Mở danh sách trường");
                // Thêm logic xử lý tại đây
				DanhSachTruongView frame = new DanhSachTruongView();
				frame.setVisible(true);
				new DanhSachTruongController(frame);
				//view.dispose();
            }
        });

        // Xử lý nút Xét tuyển
        view.xetTuyen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // JOptionPane.showMessageDialog(view, "Mở chức năng xét tuyển");
                // Thêm logic xử lý tại đây
				XetTuyenView frame = new XetTuyenView();
				frame.setVisible(true);
				new XetTuyenController(frame);
				//view.dispose();
            }
        });

        // Xử lý menu Đăng xuất
        view.getDangXuat().addActionListener(e -> handleLogout());

        // Xử lý menu Đổi mật khẩu
        view.doiMatKhau.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              //  JOptionPane.showMessageDialog(view, "Mở form đổi mật khẩu");
                // Thêm logic xử lý tại đây
                DoiMatKhauView frame = new DoiMatKhauView();
                frame.setVisible(true);
                new DoiMatKhauController(frame);
            }
        });

        // Xử lý menu Thoát
        view.thoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(view, 
                    "Bạn có chắc chắn muốn thoát ứng dụng?", 
                    "Xác nhận thoát", 
                    JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }
	private void handleLogout() {
		int confirm = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn đăng xuất?", "Xác nhận đăng xuất",
				JOptionPane.YES_NO_OPTION);

		if (confirm == JOptionPane.YES_OPTION) {
			// Xóa thông tin account trong SessionManager
			SessionManager.getInstance().setCurrentAccount(null);



			// Mở lại màn hình đăng nhập
			EventQueue.invokeLater(() -> {
				MenuDangNhapView loginView = new MenuDangNhapView();
				new MenuDangNhapController(loginView);
				loginView.setVisible(true);
				// Đóng cửa sổ hiện tại
				view.dispose();
			});
		}
	}
}