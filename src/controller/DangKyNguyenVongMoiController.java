package controller;

import view.DangKyNguyenVongView;
import view.HoSoXetTuyenView;
import model.TaiKhoan;
import model.Truong;
import model.HoSoDangKy;
import model.Nganh;
import dao.HoSoDangKyDAO;
import dao.HocSinhDAO;
import dao.NganhDAO;
import model.NguyenVong;
import util.SessionManager;
import util.TemporaryDataStorage;
import dao.NguyenVongDAO;
import dao.TruongDAO;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class DangKyNguyenVongMoiController {
//    private dangkyNguyenVongView view;
//    private HoSoDangKyDAO hoSoDAO;
//    private NguyenVongDAO nguyenVongDAO;
	private DangKyNguyenVongView view;
	private HoSoDangKyDAO hoSoDAO;
	private NguyenVongDAO nguyenVongDAO;
	private TruongDAO truongDAO;
	private NganhDAO nganhDAO;

	public DangKyNguyenVongMoiController(DangKyNguyenVongView view) {
		this.view = view;
		this.hoSoDAO = new HoSoDangKyDAO();
		this.nguyenVongDAO = new NguyenVongDAO();
		this.truongDAO = new TruongDAO();
		this.nganhDAO = new NganhDAO();

		loadTruongData();

		view.getTruongComboBox().addActionListener(e -> {
			Truong selectedTruong = (Truong) view.getTruongComboBox().getSelectedItem();
			if (selectedTruong != null) {
				loadNganhData(selectedTruong.getIdTruong());
			}
		});

		view.getNganhComboBox().addActionListener(e -> {
			Nganh selectedNganh = (Nganh) view.getNganhComboBox().getSelectedItem();
			if (selectedNganh != null) {
				updatePhuongThuc(selectedNganh.getPhuongThuc());
			}
		});

		// Initialize phuongThuc combo box
//		view.getPhuongThucComboBox().addItem("HOC_BA");
//		view.getPhuongThucComboBox().addItem("DIEM_THI_THPT");
//		view.getPhuongThucComboBox().addItem("TUYEN_THANG");

		// Add action listeners
		view.getDangKyButton().addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//            	System.out.println("Mã Trường Là: " + view.getMaTruong());
//            	System.out.println("Mã Ngành Là: " + view.getMaNganh());
//                truongDAO truongDAO = new truongDAO();
//                nganhDAO nganhDAO = new nganhDAO();
//                int idHS = getCurrentStudentId();
//                int idTruong = truongDAO.getIdTruongByMaTruong(view.getMaTruong());
//                int idNganh = nganhDAO.getIdNganhByMaNganh(view.getMaNganh());
//                System.out.println("id HS: " + idHS);
//                System.out.println("id Truong: " + idTruong);
//                System.out.println("id Nganh: " + idNganh );

				dangKyNguyenVong();
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

	private void loadTruongData() {
		List<Truong> dsTruong = truongDAO.getAllTruong();
		DefaultComboBoxModel<Truong> model = new DefaultComboBoxModel<>();
		model.addElement(null);
		for (Truong truong : dsTruong) {
			model.addElement(truong);
		}
		view.getTruongComboBox().setModel(model);
	}

	private void loadNganhData(int idTruong) {
		DefaultComboBoxModel<Nganh> model = new DefaultComboBoxModel<>();
		model.addElement(null);

		if (idTruong > 0) {
			List<Nganh> dsNganh = nganhDAO.getNganhByTruong(idTruong);
			for (Nganh nganh : dsNganh) {
				model.addElement(nganh);
			}
		}
		view.getNganhComboBox().setModel(model);
		view.getPhuongThucComboBox().removeAllItems();
	}

	private void updatePhuongThuc(String phuongThucStr) {
		view.getPhuongThucComboBox().removeAllItems();
		view.getPhuongThucComboBox().addItem(null);

		if (phuongThucStr != null && !phuongThucStr.isEmpty()) {
			String[] methods = phuongThucStr.split(",");
			for (String method : methods) {
				view.getPhuongThucComboBox().addItem(method.trim());
			}
		}
	}

	private void dangKyNguyenVong() {
//        try {
//            // Lấy thông tin người dùng hiện tại
//            int idHS = getCurrentStudentId();
//            if (idHS <= 0) {
//                JOptionPane.showMessageDialog(view, "Không tìm thấy thông tin học sinh", "Lỗi", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
//
//            // Lấy thông tin từ giao diện
//            String maTruong = view.getMaTruong();
//            String maNganh = view.getMaNganh();
//            String phuongThuc = view.getPhuongThuc();
//
//            // Kiểm tra dữ liệu đầu vào
//            if (maTruong.isEmpty() || maNganh.isEmpty() || phuongThuc == null) {
//                JOptionPane.showMessageDialog(view, "Vui lòng nhập đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
//                return;
//            }
		try {
			// Lấy thông tin người dùng hiện tại
			int idHS = getCurrentStudentId();
			if (idHS <= 0) {
				JOptionPane.showMessageDialog(view, "Không tìm thấy thông tin học sinh", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Lấy thông tin từ giao diện
			String maTruong = view.getMaTruong();
			String maNganh = view.getMaNganh();
			String phuongThuc = view.getPhuongThuc();

			// Kiểm tra dữ liệu đầu vào
			if (maTruong.isEmpty() || maNganh.isEmpty() || phuongThuc == null || phuongThuc.isEmpty()) {
				JOptionPane.showMessageDialog(view, "Vui lòng chọn đầy đủ thông tin", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}
			TemporaryDataStorage.getInstance().luuNguyenVongTamThoi(maTruong, maNganh, phuongThuc);
			// Chuyển đổi mã trường/mã ngành sang ID
			TruongDAO truongDAO = new TruongDAO();
			NganhDAO nganhDAO = new NganhDAO();

			int idTruong = truongDAO.getIdTruongByMaTruong(maTruong);
			int idNganh = nganhDAO.getIdNganhByMaNganh(maNganh);

			if (idTruong <= 0) {
				JOptionPane.showMessageDialog(view, "Mã trường không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (idNganh <= 0) {
				JOptionPane.showMessageDialog(view, "Mã ngành không hợp lệ", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}

			// Kiểm tra và tạo hồ sơ nếu chưa có
			HoSoDangKy hoSo = hoSoDAO.getHoSoByStudentId(idHS);

			if (hoSo == null) {
				// Tạo hồ sơ mới
				hoSo = new HoSoDangKy();
				hoSo.setIdHS(idHS);
				hoSo.setTrangThai("CHO_XET_TUYEN");
				hoSo.setDiemThi(0); // Sẽ được tính sau
				hoSo.setDiemHocBa(0); // Sẽ được tính sau

				int idHoSo = hoSoDAO.createHoSo(hoSo);
				if (idHoSo <= 0) {
					JOptionPane.showMessageDialog(view, "Không thể tạo hồ sơ", "Lỗi", JOptionPane.ERROR_MESSAGE);
					return;
				}
				hoSo.setIdHoSo(idHoSo);
			}

			// Tạo nguyện vọng mới
			NguyenVong nv = new NguyenVong();
			nv.setIdHoSo(hoSo.getIdHoSo());
			nv.setIdTruong(idTruong);
			nv.setIdNganh(idNganh);
			nv.setPhuongThucXetTuyen(phuongThuc);
			nv.setTrangThai("CHO_XET_TUYEN");

			// Lấy thứ tự nguyện vọng tiếp theo
			int thuTu = nguyenVongDAO.getNextThuTuNguyenVong(hoSo.getIdHoSo());
			nv.setThuTuNguyenVong(thuTu);

			// Lưu nguyện vọng
			boolean success = nguyenVongDAO.createNguyenVong(nv);

			if (success) {
				JOptionPane.showMessageDialog(view, "Đăng ký nguyện vọng thành công!", "Thành công",
						JOptionPane.INFORMATION_MESSAGE);

				// Cập nhật điểm thi và điểm học bạ
				hoSo.setDiemThi(hoSo.getDiemThi()); // Tính toán lại điểm thi
				hoSo.setDiemHocBa(hoSo.getDiemHocBa()); // Tính toán lại điểm học bạ
				hoSoDAO.updateHoSo(hoSo);

				// Đóng cửa sổ và quay về màn hình chính
				view.dispose();
				HoSoXetTuyenView mainView = new HoSoXetTuyenView();
				mainView.setVisible(true);
				new HoSoXetTuyenController(mainView);
			} else {
				JOptionPane.showMessageDialog(view, "Đăng ký nguyện vọng thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			JOptionPane.showMessageDialog(view, "Lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}

	// You need to implement this method to get current logged in student ID
	private int getCurrentStudentId() {
		// This should return the ID of the currently logged in student
		// For now, return a dummy value
		HocSinhDAO tempHSDAO = new HocSinhDAO();
		SessionManager session = SessionManager.getInstance();
		TaiKhoan currentUser = session.getCurrentAccount();
		return tempHSDAO.getIdHSByIdTaiKhoan(currentUser.getId());
		// return 3;
	}

	public String getMaTruong() {
		Truong selected = (Truong) view.getTruongComboBox().getSelectedItem();
		return selected != null ? selected.getMaTruong() : "";
	}

	public String getMaNganh() {
		Nganh selected = (Nganh) view.getNganhComboBox().getSelectedItem();
		return selected != null ? selected.getMaNganh() : "";
	}

}