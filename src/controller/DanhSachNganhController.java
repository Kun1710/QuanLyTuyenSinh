package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import dao.NganhDAO;
import model.TaiKhoan;
import model.Nganh;
import util.SessionManager;
import view.ThemNganhMoiView;
import view.DanhSachNganhView;
import view.KetQuaTuyenSinhView;
import view.NganhView;

public class DanhSachNganhController {
	private DanhSachNganhView view;
	private NganhDAO nganhDao;
	private DefaultTableModel tableModel;
	private NganhController parentController;

	public DanhSachNganhController(DanhSachNganhView view) {
		this.view = view;
		this.nganhDao = new NganhDAO();
		this.tableModel = (DefaultTableModel) view.getTable().getModel();
		initController();
		loadNganhData();
	}

	public DanhSachNganhController(DanhSachNganhView view, NganhController parentController) {
		this.view = view;
		this.parentController = parentController;
		this.nganhDao = new NganhDAO();
		this.tableModel = (DefaultTableModel) view.getTable().getModel();

		initController();
		loadNganhData();

	}

	private void initController() {
//		if (view.getBtnBack() != null) {
//			view.getBtnBack().addActionListener(e -> backToNganhView());
//		} else {
//			System.err.println("Lỗi: Nút quay lại không được khởi tạo");
//		}
		// Nút quay lại
		view.getBtnBack().addActionListener(e -> backToNganhView());

		// Nút thêm
		view.getBtnThem().addActionListener(e -> themNganh());

		// Nút sửa
		view.getBtnSua().addActionListener(e -> suaNganh());

		// Nút xóa
		view.getBtnXoa().addActionListener(e -> xoaNganh());
		// Nút kết quả tuyển sinh
		view.getButton_ketqua().addActionListener(e -> xemKetQuaTuyenSinh());
	}

	private void loadNganhData() {
		// Xóa dữ liệu cũ trong bảng
		tableModel.setRowCount(0);

		TaiKhoan currentAccount = SessionManager.getInstance().getCurrentAccount();
		int idTruong = nganhDao.getIdTruongByTaiKhoan(currentAccount.getId());
		if (idTruong == -1) {
			JOptionPane.showMessageDialog(view, "Không tìm thấy thông tin trường!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}

		// Lấy danh sách ngành từ database
		List<Nganh> dsNganh = nganhDao.getNganhByTruong(idTruong);
		// fix bug
		for (Nganh ng : dsNganh) {
			Object[] rowData = { ng.getIdNganh(), ng.getTenNganh(), ng.getMaNganh(), ng.getChiTieu(),
					ng.getPhuongThuc(), ng.getKhoiXetTuyen(), ng.getUuTien() };
			tableModel.addRow(rowData);
		}
	}
	private void xemKetQuaTuyenSinh() {
        int selectedRow = view.getTable().getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(view, "Vui lòng chọn ngành cần xem kết quả!", 
                "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Lấy ID ngành từ dòng được chọn
        int idNganh = (int) tableModel.getValueAt(selectedRow, 0);
        
        // Mở view kết quả tuyển sinh
        KetQuaTuyenSinhView ketQuaView = new KetQuaTuyenSinhView();
        KetQuaXetTuyenController ketQuaController = new KetQuaXetTuyenController(ketQuaView);
        ketQuaController.hienThiKetQuaNganh(idNganh);
        ketQuaView.setVisible(true);
    }

	private void backToNganhView() {
		view.dispose();
		if (parentController != null) {
			parentController.showNganhView();
		}
//		} else {
//			new nganhView().setVisible(true);
//		}
	}

	private void themNganh() {
		TaiKhoan currentAccount = SessionManager.getInstance().getCurrentAccount();
		int idTruong = nganhDao.getIdTruongByTaiKhoan(currentAccount.getId());

		ThemNganhMoiView frame = new ThemNganhMoiView(idTruong, null);
		frame.setVisible(true);
		new ThemNganhMoiController(frame, idTruong);
		view.dispose();
	}

	private void suaNganh() {
		int selectedRow = view.getTable().getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(view, "Vui lòng chọn ngành cần sửa!", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		String maNganh = (String) tableModel.getValueAt(selectedRow, 2);
		Nganh ng = nganhDao.getNganhByMaNganh(maNganh);
		TaiKhoan currentAccount = SessionManager.getInstance().getCurrentAccount();
		int idTruong = nganhDao.getIdTruongByTaiKhoan(currentAccount.getId());
	//	System.out.println("Mã Ngành là: "+ maNganh);

		if (ng == null) {
			JOptionPane.showMessageDialog(view, "Không tìm thấy thông tin ngành!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}

		ThemNganhMoiView editView = new ThemNganhMoiView(ng.getIdTruong(), ng);
		new ThemNganhMoiController(editView, idTruong, ng);
		editView.setVisible(true);
		//view.dispose();
	}

	private void xoaNganh() {
		int selectedRow = view.getTable().getSelectedRow();
		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(view, "Vui lòng chọn ngành cần xóa!", "Thông báo",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		String maNganh = (String) tableModel.getValueAt(selectedRow, 2);
		int confirm = JOptionPane.showConfirmDialog(view, "Bạn có chắc chắn muốn xóa ngành này?", "Xác nhận xóa",
				JOptionPane.YES_NO_OPTION);

		if (confirm == JOptionPane.YES_OPTION) {
			if (nganhDao.deleteNganh(maNganh)) {
				JOptionPane.showMessageDialog(view, "Xóa ngành thành công!", "Thành công",
						JOptionPane.INFORMATION_MESSAGE);
				loadNganhData(); // Tải lại dữ liệu
			} else {
				JOptionPane.showMessageDialog(view, "Xóa ngành thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}