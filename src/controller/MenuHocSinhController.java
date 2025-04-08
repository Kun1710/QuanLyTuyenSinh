package controller;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

import javax.swing.JOptionPane;

import dao.DatabaseConnection;
import dao.HocSinhDAO;
import model.TaiKhoan;
import util.SessionManager;
import view.DoiMatKhauView;
import view.HoSoXetTuyenView;
import view.MenuDangNhapView;
import view.MenuHocSinhView;
import view.TimKiemTruongView;
import view.ThemChungChiMoiView;
import view.ChungChiView;
import view.DiemThiTHPView;
import view.DiemHocBaView;
import view.ThongTinHocSinhView;

public class MenuHocSinhController {
	private MenuHocSinhView view;

	public MenuHocSinhController(MenuHocSinhView view) {
		this.view = view;
		// System.out.println("Trước khi gọi hàm init");
		initController();
	}

	private void initController() {

//    	view.getButton_updateprofile().addActionListener(e -> {
//    	    updateprofileView updateView = new updateprofileView();
//    	    hocSinhDAO hocSinhDAO = new hocSinhDAO();
//    	    new updateprofileController(updateView, hocSinhDAO);
//    	    updateView.setVisible(true);
//    	});
		// System.out.println("Trước khi ấn nút");
		view.getButton_updateprofile().addActionListener(e -> {
			// System.out.println("Nút updateprofile được nhấn"); // Debug log

			try {
				ThongTinHocSinhView updateView = new ThongTinHocSinhView();
				HocSinhDAO hocSinhDAO = new HocSinhDAO();
				new ThongTinHocSinhController(updateView, hocSinhDAO);
				updateView.setVisible(true);
				// System.out.println("UpdateprofileView đã được mở"); // Debug log
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(view, "Lỗi khi mở trang cập nhật: " + ex.getMessage(), "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			}
		});
		// Add similar action listeners for other buttons
		view.getButton_updateGPA().addActionListener(e -> handleUpdateGPA());
		view.getButton_updateGPA_THPT().addActionListener(e -> handleUpdateGPA_THPT());
		view.getButton_updateCe().addActionListener(e -> handleChungChi());
		view.getButton_search().addActionListener(e -> handleSearch());
		view.getButton_register().addActionListener(e -> handleRegister());
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

	private void handleUpdateGPA() {
		// Implement GPA update functionality
		// Example: new updateGPAView().setVisible(true);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiemHocBaView frame = new DiemHocBaView();
					frame.setVisible(true);
					HocSinhDAO hocSinhDAO = new HocSinhDAO();
					TaiKhoan temp_account = SessionManager.getInstance().getCurrentAccount();
					new DiemHocBaController(frame, hocSinhDAO.getIdHSByIdTaiKhoan(temp_account.getId()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void handleUpdateGPA_THPT() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiemThiTHPView frame = new DiemThiTHPView();
					frame.setVisible(true);
					HocSinhDAO hocSinhDAO = new HocSinhDAO();
					TaiKhoan temp_account = SessionManager.getInstance().getCurrentAccount();
					new DiemThiTHPTController(frame, hocSinhDAO.getIdHSByIdTaiKhoan(temp_account.getId()));
					//new diemThiTHPTController(connection, frame, view);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void handleChungChi() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//AddChungChiView frame = new AddChungChiView();
					ChungChiView frame = new ChungChiView();
					frame.setVisible(true);
					HocSinhDAO hocSinhDAO = new HocSinhDAO();
					TaiKhoan temp_account = SessionManager.getInstance().getCurrentAccount();
					new ChungChiController(frame, hocSinhDAO.getIdHSByIdTaiKhoan(temp_account.getId()));
					//new AddChungChiController(frame, hocSinhDAO.getIdHSByIdTaiKhoan(temp_account.getId()));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void handleSearch() {
		// Implement search functionality

		// Implement registration functionality
		try {
			TimKiemTruongView frame = new TimKiemTruongView();
			frame.setVisible(true);

			// Khởi tạo controller
			new TimKiemTruongController(frame);

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(view, "Lỗi khi mở trang danh sách ngành: " + e.getMessage(), "Lỗi",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private void handleRegister() {
		// Implement registration functionality
		try {
			HoSoXetTuyenView frame = new HoSoXetTuyenView();
			frame.setVisible(true);
			new HoSoXetTuyenController(frame);
			view.dispose(); // Đóng cửa sổ hiện tại

		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(view, "Lỗi khi mở trang danh sách ngành: " + e.getMessage(), "Lỗi",
					JOptionPane.ERROR_MESSAGE);
		}
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
				view.dispose();
			});
		}
	}
}