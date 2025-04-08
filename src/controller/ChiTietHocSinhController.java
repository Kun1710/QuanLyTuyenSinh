package controller;

import dao.HocSinhDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.TaiKhoan;
import model.HocSinh;
import util.SessionManager;
import view.DanhSachHocSinhView;
import view.DiemHocBaView;
import view.DiemThiTHPView;
import view.ChiTietHocSinhView;
import view.DanhSachChungChiView;

public class ChiTietHocSinhController {
    private ChiTietHocSinhView view;
    private HocSinhDAO hocSinhDAO;
    private String currentCCCD;

    public ChiTietHocSinhController(ChiTietHocSinhView view, String cccd) {
        this.view = view;
        this.hocSinhDAO = new HocSinhDAO();
        this.currentCCCD = cccd;
        
        initView();
        loadHocSinhData();
        
        // Thêm action listeners
        view.addThoatButtonListener(new ThoatButtonListener());
        view.addUpdateButtonListener(new UpdateButtonListener());
        view.getDiemHB().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//System.out.println("truong dep trai");
				DiemHocBaView frame = new DiemHocBaView();
				frame.setVisible(true);
				HocSinhDAO temp = new HocSinhDAO();
				new DiemHocBaController(frame, temp.getIdHSByCCCD(cccd));
				
			}
		});
        view.getDiemTHPT().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DiemThiTHPView frame = new DiemThiTHPView();
				frame.setVisible(true);
				HocSinhDAO temp = new HocSinhDAO();
				new DiemThiTHPTController(frame, temp.getIdHSByCCCD(cccd));
				
			}
		});
        view.getChungchi().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DanhSachChungChiView frame = new DanhSachChungChiView();
				frame.setVisible(true);
				HocSinhDAO temp = new HocSinhDAO();
				new DanhSachChungChiController(frame, temp.getIdHSByCCCD(cccd));
				
			}
		});
        
    }

    private void initView() {
        // Đặt tiêu đề cửa sổ với tên học sinh
        view.setTitle("Hệ Thống Tuyển Sinh - Thông Tin Học Sinh");
        
        // Khởi tạo các combobox ngày tháng năm
        initDateComboBoxes();
    }

    private void initDateComboBoxes() {
        JComboBox<Integer> dayCombo = view.getDayCombo();
        for (int i = 1; i <= 31; i++) {
            dayCombo.addItem(i);
        }
        
        JComboBox<Integer> monthCombo = view.getMonthCombo();
        for (int i = 1; i <= 12; i++) {
            monthCombo.addItem(i);
        }
       
        JComboBox<Integer> yearCombo = view.getYearCombo();
        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        for (int i = currentYear; i >= 1900; i--) {
            yearCombo.addItem(i);
        }
    }

    private void loadHocSinhData() {
        HocSinh hs = hocSinhDAO.getHocSinhByCCCD(currentCCCD);
        
        if (hs != null) {
            view.getHoTenField().setText(hs.getHoVaTen());
            view.getCccdField().setText(hs.getCCCD());
            view.getDiaChiField().setText(hs.getDiaChi());
            view.getDanTocField().setText(hs.getDanToc());
            view.getGioiTinhField().setText(hs.isGioiTinh() ? "Nam" : "Nữ");
            
            if (hs.getNgaySinh() != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String[] dateParts = sdf.format(hs.getNgaySinh()).split("-");
                
                view.getYearCombo().setSelectedItem(Integer.parseInt(dateParts[0]));
                view.getMonthCombo().setSelectedItem(Integer.parseInt(dateParts[1]));
                view.getDayCombo().setSelectedItem(Integer.parseInt(dateParts[2]));
            }
            
            view.getTitleLabel().setText(">> Thông Tin Của " + hs.getHoVaTen());
        } else {
            JOptionPane.showMessageDialog(view, "Không tìm thấy thông tin học sinh", "Lỗi", JOptionPane.ERROR_MESSAGE);
            view.dispose();
        }
    }

    class ThoatButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose();
            
            DanhSachHocSinhView dsView = new DanhSachHocSinhView();
            new DanhSachHocSinhController(dsView);
            dsView.setVisible(true);
        }
    }

    class UpdateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String hoTen = view.getHoTenField().getText().trim();
            String diaChi = view.getDiaChiField().getText().trim();
            String danToc = view.getDanTocField().getText().trim();
            
            // Tạo đối tượng học sinh với dữ liệu mới
            HocSinh hs = new HocSinh(
                currentCCCD,
                hoTen,
                getSelectedDate(),
                view.getGioiTinhField().getText().equalsIgnoreCase("Nam"),
                danToc,
                diaChi
            );
            
            // Cập nhật vào database
            boolean success = hocSinhDAO.updateHocSinhByCCCD(hs);
            
            if (success) {
                JOptionPane.showMessageDialog(view, "Cập nhật thông tin thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadHocSinhData();
            } else {
                JOptionPane.showMessageDialog(view, "Cập nhật thông tin thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        private Date getSelectedDate() {
            int year = (int) view.getYearCombo().getSelectedItem();
            int month = (int) view.getMonthCombo().getSelectedItem();
            int day = (int) view.getDayCombo().getSelectedItem();
            
            // Lưu ý: Tháng trong java.sql.Date bắt đầu từ 0 (0-11)
            return new Date(year - 1900, month - 1, day);
        }
    }
}