package controller;

import dao.HocSinhDAO;
import model.TaiKhoan;
import model.HocSinh;
import util.SessionManager;
import view.MenuDangNhapView;
import view.MenuHocSinhView;
import view.ThongTinHocSinhView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ThongTinHocSinhController {
    private ThongTinHocSinhView view;
    private HocSinhDAO hocSinhDAO;
    private TaiKhoan currentAccount;

    public ThongTinHocSinhController(ThongTinHocSinhView view, HocSinhDAO hocSinhDAO) {
        this.view = view;
        this.hocSinhDAO = hocSinhDAO;
        this.currentAccount = SessionManager.getInstance().getCurrentAccount();
        
        initController();
        loadStudentData();
    }

    private void initController() {
        // Xử lý sự kiện nút cập nhật
        view.getUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStudentProfile();
            }
        });

        // Xử lý sự kiện nút thoát
    	view.getThoatButton().addActionListener(e -> {
    	    SwingUtilities.invokeLater(() -> {
//                MenuHocSinhView frame = new MenuHocSinhView();
//                frame.setVisible(true);
//                
//                // Khởi tạo controller
//                new MenuHocSinhController(frame);
    	    });
    	    view.dispose();
    	});
    }

    private void loadStudentData() {
        // Lấy thông tin học sinh từ database
        HocSinh student = hocSinhDAO.getHocSinhByTaiKhoan(currentAccount.getTaiKhoan());
        
        if (student != null) {
            // Hiển thị thông tin lên form
            view.getHoTen().setText(student.getHoVaTen());
            view.getDiaChi().setText(student.getDiaChi());
            view.getDanToc().setText(student.getDanToc());
            view.getCccd().setText(student.getCCCD());
            
            // Xử lý giới tính
            if (student.isGioiTinh()) {
                view.getGioitinh_nam().setSelected(true);
            } else {
                view.getGioitinh_nu().setSelected(true);
            }
            
            // Xử lý ngày sinh
            if (student.getNgaySinh() != null) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(student.getNgaySinh());
                
                view.getDayBird().setSelectedItem(cal.get(Calendar.DAY_OF_MONTH));
                view.getMonthBird().setSelectedItem(cal.get(Calendar.MONTH) + 1);
                view.getYearBrid().setSelectedItem(cal.get(Calendar.YEAR));
            }
        }
    }

    private void updateStudentProfile() {
        try {
            // Lấy dữ liệu từ form
            String hoTen = view.getHoTen().getText().trim();
            String diaChi = view.getDiaChi().getText().trim();
            String danToc = view.getDanToc().getText().trim();
            String cccd = view.getCccd().getText().trim();
            boolean gioiTinh = view.getGioitinh_nam().isSelected();
            
            // Lấy ngày sinh từ combobox
            int day = (int) view.getDayBird().getSelectedItem();
            int month = (int) view.getMonthBird().getSelectedItem();
            int year = (int) view.getYearBrid().getSelectedItem();
            
            Calendar cal = Calendar.getInstance();
            cal.set(year, month - 1, day);
            Date ngaySinh = new Date(cal.getTimeInMillis());
            
            // Tạo đối tượng học sinh
            HocSinh student = new HocSinh();
            student.setHoVaTen(hoTen);
            student.setNgaySinh(ngaySinh);
            student.setGioiTinh(gioiTinh);
            student.setDanToc(danToc);
            student.setCCCD(cccd);
            student.setDiaChi(diaChi);
            
            // Kiểm tra học sinh đã tồn tại chưa để quyết định insert hay update
            boolean success;
            if (hocSinhDAO.checkHocSinhExists(currentAccount.getTaiKhoan())) {
                success = hocSinhDAO.updateHocSinh(student, currentAccount.getTaiKhoan());
            } else {
                success = hocSinhDAO.insertHocSinh(student, currentAccount.getId());
            }
            
            if (success) {
                JOptionPane.showMessageDialog(view, "Cập nhật thông tin thành công!", 
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(view, "Cập nhật thông tin thất bại!", 
                    "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi cập nhật thông tin: " + e.getMessage(), 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}