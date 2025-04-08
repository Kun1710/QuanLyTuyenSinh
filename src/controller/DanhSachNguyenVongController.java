package controller;

import view.HoSoXetTuyenView;
import view.DanhSachNguyenVongView;
import dao.HoSoDangKyDAO;
import dao.HocSinhDAO;
import dao.NganhDAO;
import dao.NguyenVongDAO;
import dao.TruongDAO;
import model.TaiKhoan;
import model.HoSoDangKy;
import model.NguyenVong;
import util.SessionManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DanhSachNguyenVongController {
	private DanhSachNguyenVongView view;
	private NguyenVongDAO nguyenVongDAO;
	private HoSoDangKyDAO hoSoDAO;

	public DanhSachNguyenVongController(DanhSachNguyenVongView view) {
		this.view = view;
		this.nguyenVongDAO = new NguyenVongDAO();
		this.hoSoDAO = new HoSoDangKyDAO();

		// Load data
		loadNguyenVongData();

		// Add action listeners
		view.getLenButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveUp();
			}
		});

		view.getXuongButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				moveDown();
			}
		});

		view.getXoaButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteSelected();
			}
		});

		view.getQuayLaiButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.dispose();
//				HoSoXetTuyenView mainView = new HoSoXetTuyenView();
//				mainView.setVisible(true);
//				new HoSoXetTuyenController(mainView);
			}
		});
	}

	private void loadNguyenVongData() {
	//	try {
			// Lấy ID học sinh hiện tại
			int idHS = getCurrentStudentId();
			if (idHS <= 0) {
				throw new IllegalStateException("Không tìm thấy học sinh");
			}

			// Lấy hồ sơ đăng ký của học sinh
			HoSoDangKy hoSo = hoSoDAO.getHoSoByStudentId(idHS);
			if (hoSo == null) {
				JOptionPane.showMessageDialog(view, "Học sinh chưa có hồ sơ đăng ký", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			// Lấy danh sách nguyện vọng
			List<NguyenVong> list = nguyenVongDAO.getAllByHoSoId(hoSo.getIdHoSo());
			if (list == null || list.isEmpty()) {
				JOptionPane.showMessageDialog(view, "Chưa có nguyện vọng nào được đăng ký", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			// Khởi tạo DAO
			TruongDAO truongDAO = new TruongDAO();
			NganhDAO nganhDAO = new NganhDAO();

			// Xóa dữ liệu cũ trong bảng
			DefaultTableModel model = view.getTableModel();
			model.setRowCount(0);

			// Thêm dữ liệu mới
			for (NguyenVong nv : list) {
				// Lấy thông tin trường
				String tenTruong = "Không xác định";
				try {
					tenTruong = truongDAO.getTenTruongById(nv.getIdTruong());
				} catch (Exception e) {
					System.err.println("Lỗi khi lấy tên trường: " + e.getMessage());
				}

				// Lấy thông tin ngành
				String tenNganh = "Không xác định";
				try {
					tenNganh = nganhDAO.getTenNganhById(nv.getIdNganh());
				} catch (Exception e) {
					System.err.println("Lỗi khi lấy tên ngành: " + e.getMessage());
				}

				// Thêm dòng vào bảng
				model.addRow(
						new Object[] { nv.getThuTuNguyenVong(), tenTruong, nv.getIdTruong(), tenNganh, nv.getIdNganh(),
								convertPhuongThuc(nv.getPhuongThucXetTuyen()), convertTrangThai(nv.getTrangThai()) });
			}
		}
//	catch (SQLException e) {
//			JOptionPane.showMessageDialog(view, "Lỗi truy vấn cơ sở dữ liệu: " + e.getMessage(), "Lỗi",
//					JOptionPane.ERROR_MESSAGE);
//			e.printStackTrace();
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(view, "Lỗi khi tải dữ liệu: " + e.getMessage(), "Lỗi",
//					JOptionPane.ERROR_MESSAGE);
//			e.printStackTrace();
//		}
//	}

	// Chuyển đổi phương thức xét tuyển sang tiếng Việt
	private String convertPhuongThuc(String phuongThuc) {
		switch (phuongThuc) {
		case "HOC_BA":
			return "Xét học bạ";
		case "DIEM_THI_THPT":
			return "Điểm thi THPT";
		case "TUYEN_THANG":
			return "Tuyển thẳng";
		default:
			return phuongThuc;
		}
	}

	// Chuyển đổi trạng thái sang tiếng Việt
	private String convertTrangThai(String trangThai) {
		switch (trangThai) {
		case "CHO_XET_TUYEN":
			return "Chờ xét tuyển";
		case "TRUNG_TUYEN":
			return "Trúng tuyển";
		case "TRUOT":
			return "Trượt";
		default:
			return trangThai;
		}
	}

	private void moveUp() {
		int selectedRow = view.getTable().getSelectedRow();
		if (selectedRow > 0) {
			// Get current student ID and profile
			int idHS = getCurrentStudentId();
			HoSoDangKy hoSo = hoSoDAO.getHoSoByStudentId(idHS);

			if (hoSo != null) {
				// Get the two nguyen vong to swap
				int firstOrder = selectedRow + 1; // +1 because table rows start at 0
				int secondOrder = selectedRow; // the row above

				boolean success = nguyenVongDAO.swapNguyenVongOrder(hoSo.getIdHoSo(), firstOrder, secondOrder);

				if (success) {
					loadNguyenVongData();
					view.getTable().setRowSelectionInterval(selectedRow - 1, selectedRow - 1);
				} else {
					JOptionPane.showMessageDialog(view, "Không thể thay đổi thứ tự", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	private void moveDown() {
		int selectedRow = view.getTable().getSelectedRow();
		DefaultTableModel model = view.getTableModel();

		if (selectedRow >= 0 && selectedRow < model.getRowCount() - 1) {
			// Get current student ID and profile
			int idHS = getCurrentStudentId();
			HoSoDangKy hoSo = hoSoDAO.getHoSoByStudentId(idHS);

			if (hoSo != null) {
				// Get the two nguyen vong to swap
				int firstOrder = selectedRow + 1; // +1 because table rows start at 0
				int secondOrder = selectedRow + 2; // the row below

				boolean success = nguyenVongDAO.swapNguyenVongOrder(hoSo.getIdHoSo(), firstOrder, secondOrder);

				if (success) {
					loadNguyenVongData();
					view.getTable().setRowSelectionInterval(selectedRow + 1, selectedRow + 1);
				} else {
					JOptionPane.showMessageDialog(view, "Không thể thay đổi thứ tự", "Lỗi", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}

	private void deleteSelected() {
		int selectedRow = view.getTable().getSelectedRow();
		if (selectedRow >= 0) {
			int confirm = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn xóa nguyện vọng này?",
					"Xác nhận xóa", JOptionPane.YES_NO_OPTION);

			if (confirm == JOptionPane.YES_OPTION) {
				// Get current student ID and profile
				int idHS = getCurrentStudentId();
				HoSoDangKy hoSo = hoSoDAO.getHoSoByStudentId(idHS);

				if (hoSo != null) {
					int thuTu = selectedRow + 1; // +1 because table rows start at 0
					boolean success = nguyenVongDAO.deleteNguyenVong(hoSo.getIdHoSo(), thuTu);

					if (success) {
						loadNguyenVongData();
					} else {
						JOptionPane.showMessageDialog(view, "Không thể xóa nguyện vọng", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		} else {
			JOptionPane.showMessageDialog(view, "Vui lòng chọn một nguyện vọng để xóa", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
		}
	}

	// You need to implement this method to get current logged in student ID
	private int getCurrentStudentId() {
		// This should return the ID of the currently logged in student
		// For now, return a dummy value
		// return 1;
		HocSinhDAO tempHSDAO = new HocSinhDAO();
		SessionManager session = SessionManager.getInstance();
		TaiKhoan currentUser = session.getCurrentAccount();
		return tempHSDAO.getIdHSByIdTaiKhoan(currentUser.getId());
	}
}