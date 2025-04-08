package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JOptionPane;

import dao.NganhDAO;
import model.KhoiXetTuyen;
import model.Nganh;
import view.ThemNganhMoiView;

public class ThemNganhMoiController {
    private ThemNganhMoiView view;
    private NganhDAO dao;
    private boolean isEditMode;

    // Constructor cho chế độ thêm mới
    public ThemNganhMoiController(ThemNganhMoiView view, int idTruong) {
        this.view = view;
        this.dao = new NganhDAO();
        this.isEditMode = false;
        
        // Khởi tạo dữ liệu
        initData();
        
        // Thiết lập sự kiện
        setupListeners();
    }

    // Constructor cho chế độ chỉnh sửa
    public ThemNganhMoiController(ThemNganhMoiView view, int idTruong, Nganh nganh) {
        this.view = view;
        this.dao = new NganhDAO();
        this.isEditMode = true;
        
        // Khởi tạo dữ liệu
        initData();
        
        // Thiết lập sự kiện
        setupListeners();
    }

    private void initData() {
        // Load danh sách khối xét tuyển vào combobox
        view.getKhoiComboBox().removeAllItems();
        Arrays.stream(KhoiXetTuyen.values()).forEach(view.getKhoiComboBox()::addItem);
        
        // Nếu là chế độ sửa, không cho phép chỉnh sửa mã ngành
        if (isEditMode) {
            view.setMaNganhEditable(false);
        }
    }

    private void setupListeners() {
        // Xử lý sự kiện nút Thêm/Cập nhật
        view.getBtnThem().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isEditMode) {
                    updateNganh();
                } else {
                    addNganh();
                }
            }
        });

        // Xử lý sự kiện nút Quay lại
        view.getBtnQuayLai().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
    }

    private void addNganh() {
        try {
            // Lấy dữ liệu từ view
            String maNganh = view.getMaNganh().getText().trim();
            String tenNganh = view.getTenNganh().getText().trim();
            int chiTieu = Integer.parseInt(view.getSoLuong().getText().trim());
            String uuTien = view.getUuTien().getText().trim();
            String phuongThuc = view.getSelectedPhuongThuc();
            KhoiXetTuyen khoi = (KhoiXetTuyen) view.getKhoiComboBox().getSelectedItem();
            
            // Validate dữ liệu
            if (maNganh.isEmpty() || tenNganh.isEmpty() || uuTien.isEmpty() || phuongThuc.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (chiTieu <= 0) {
                JOptionPane.showMessageDialog(view, "Số lượng chỉ tiêu phải lớn hơn 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Kiểm tra mã ngành đã tồn tại chưa
            if (dao.isMaNganhExists(maNganh)) {
                JOptionPane.showMessageDialog(view, "Mã ngành đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Tạo đối tượng ngành mới
            Nganh newNganh = new Nganh();
            newNganh.setMaNganh(maNganh);
            newNganh.setTenNganh(tenNganh);
            newNganh.setChiTieu(chiTieu);
            newNganh.setPhuongThuc(phuongThuc);
            newNganh.setKhoiXetTuyen(khoi);
            newNganh.setUuTien(uuTien);
            
            // Thêm vào database
            if (dao.addNganh(newNganh)) {
                JOptionPane.showMessageDialog(view, "Thêm ngành thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                view.dispose();
            } else {
                JOptionPane.showMessageDialog(view, "Thêm ngành thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Số lượng chỉ tiêu phải là số nguyên dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(view, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateNganh() {
        try {
            // Lấy dữ liệu từ view
            String maNganh = view.getMaNganh().getText().trim();
            String tenNganh = view.getTenNganh().getText().trim();
            int chiTieu = Integer.parseInt(view.getSoLuong().getText().trim());
            String uuTien = view.getUuTien().getText().trim();
            String phuongThuc = view.getSelectedPhuongThuc();
            KhoiXetTuyen khoi = (KhoiXetTuyen) view.getKhoiComboBox().getSelectedItem();
            
            // Validate dữ liệu
            if (maNganh.isEmpty() || tenNganh.isEmpty() || uuTien.isEmpty() || phuongThuc.isEmpty()) {
                JOptionPane.showMessageDialog(view, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (chiTieu <= 0) {
                JOptionPane.showMessageDialog(view, "Số lượng chỉ tiêu phải lớn hơn 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Lấy thông tin ngành hiện tại
            Nganh currentNganh = view.getCurrentNganh();
            if (currentNganh == null) {
                JOptionPane.showMessageDialog(view, "Không tìm thấy thông tin ngành cần cập nhật!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Cập nhật thông tin
            currentNganh.setTenNganh(tenNganh);
            currentNganh.setChiTieu(chiTieu);
            currentNganh.setPhuongThuc(phuongThuc);
            currentNganh.setKhoiXetTuyen(khoi);
            currentNganh.setUuTien(uuTien);
//            System.out.println("Các thông số của ngành: ");
//            System.out.println(currentNganh.getIdTruong());
//            System.out.println(currentNganh.getMaNganh());
//            System.out.println(currentNganh.getTenNganh());
//            System.out.println(currentNganh.getChiTieu());
//            System.out.println(currentNganh.getPhuongThuc());
//            System.out.println(currentNganh.getKhoiXetTuyen());
//            System.out.println(currentNganh.getUuTien());
            // Cập nhật vào database
            if (dao.updateNganh(currentNganh)) {
                JOptionPane.showMessageDialog(view, "Cập nhật ngành thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
                view.dispose();
            } else {
                JOptionPane.showMessageDialog(view, "Cập nhật ngành thất bại!  o day saoooo", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Số lượng chỉ tiêu phải là số nguyên dương!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(view, "Đã xảy ra lỗi: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}