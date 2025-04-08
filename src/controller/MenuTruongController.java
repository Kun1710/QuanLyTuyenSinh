package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import model.TaiKhoan;
import util.SessionManager;
import view.MenuTruongView;
import view.NganhView;
import view.ThongTinTruongView;
import view.TimKiemHocSinhView;
import view.DoiMatKhauView;
import view.HoSoXetTuyenView;
import view.MenuDangNhapView;

public class MenuTruongController {
	private MenuTruongView view;

	public MenuTruongController(MenuTruongView view) {
		this.view = view;
		// System.out.println("Trước khi gọi hàm init");
		initController();
	}

	private void initController() {
		// Nút cập nhật thông tin trường
		view.getButton_truong().addActionListener(e -> handleUpdateTruong());

		// Nút tìm kiếm
		view.getButton_search().addActionListener(e -> handleSearch());

		// Nút danh sách ngành học
		view.getButton_listNganh().addActionListener(e -> handleListNganh());
		view.getDangXuat().addActionListener(e -> handleLogout());

		// Xử lý menu Đổi mật khẩu
		view.getDoiMatKhau().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//JOptionPane.showMessageDialog(view, "Mở form đổi mật khẩu");
				// Thêm logic xử lý tại đây
                DoiMatKhauView frame = new DoiMatKhauView();
                frame.setVisible(true);
                new DoiMatKhauController(frame);
			}
		});

		// Xử lý menu Thoát
		view.getThoat().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int confirm = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn thoát ứng dụng?",
						"Xác nhận thoát", JOptionPane.YES_NO_OPTION);

				if (confirm == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
	}

	private void handleUpdateTruong() {
		EventQueue.invokeLater(() -> {
			// System.out.println("Nút updateprofile được nhấn"); // Debug log
			try {
				TaiKhoan currentAccount = SessionManager.getInstance().getCurrentAccount();
				ThongTinTruongView frame = new ThongTinTruongView(currentAccount.getId());
				frame.setVisible(true);
				view.dispose();
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(view, "Lỗi khi mở trang cập nhật: " + e.getMessage(), "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}
		});
	}

	private void handleSearch() {
		EventQueue.invokeLater(() -> {
			try {
				TimKiemHocSinhView frame = new TimKiemHocSinhView();
				frame.setVisible(true);
				new TimKiemHocSinhController(frame);
				view.dispose();
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(view, "Lỗi khi mở trang tìm kiếm: " + e.getMessage(), "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}
		});
	}

	private void handleListNganh() {
		EventQueue.invokeLater(() -> {
			try {
				NganhView frame = new NganhView();
				frame.setVisible(true);
				new controller.NganhController(frame);
				view.dispose(); // Đóng cửa sổ hiện tại
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(view, "Lỗi khi mở trang danh sách ngành: " + e.getMessage(), "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}
		});
	}
	private void handleLogout() {
		int confirm = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn đăng xuất?", "Xác nhận đăng xuất",
				JOptionPane.YES_NO_OPTION);

		if (confirm == JOptionPane.YES_OPTION) {
			// Xóa thông tin account trong SessionManager
			SessionManager.getInstance().setCurrentAccount(null);

			// Đóng cửa sổ hiện tại
			view.dispose();

			// Mở lại màn hình đăng nhập
			EventQueue.invokeLater(() -> {
				MenuDangNhapView loginView = new MenuDangNhapView();
				new MenuDangNhapController(loginView);
				loginView.setVisible(true);
			});
		}
	}
}