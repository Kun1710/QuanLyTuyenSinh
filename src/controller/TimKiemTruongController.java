package controller;

import dao.NganhDAO;
import dao.TruongDAO;
import model.Nganh;
import model.Truong;
import view.TimKiemTruongView;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TimKiemTruongController {
    private TimKiemTruongView view;
    private TruongDAO truongDAO;
    private NganhDAO nganhDAO;

    public TimKiemTruongController(TimKiemTruongView view) {
        this.view = view;
        this.truongDAO = new TruongDAO();
        this.nganhDAO = new NganhDAO();
        
        // Thêm sự kiện cho nút tìm kiếm
        this.view.getSearchButton().addActionListener(e -> performSearch());
        
        // Thêm sự kiện cho nút thoát
        this.view.getExitButton().addActionListener(e -> view.dispose());
    }

    private void performSearch() {
        String maTruong = view.getMaTruong().getText().trim();
        String maNganh = view.getMaNganh().getText().trim();

        DefaultTableModel model = (DefaultTableModel) view.getTable().getModel();
        model.setRowCount(0);

        try {
            if (!maTruong.isEmpty() && !maNganh.isEmpty()) {

                searchCombined(maTruong, maNganh);
            } else if (!maTruong.isEmpty()) {

                searchByTruong(maTruong);
            } else if (!maNganh.isEmpty()) {
            //	System.out.println("co o day ");
          //  	System.out.println("ma ngang " + maNganh);
                searchByNganh(maNganh);
            } else {
                JOptionPane.showMessageDialog(view, "Vui lòng nhập mã trường hoặc mã ngành để tìm kiếm", 
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi tìm kiếm: " + e.getMessage(), 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void searchCombined(String maTruong, String maNganh) throws Exception {
        List<Truong> dsTruong = truongDAO.searchTruong(maTruong);
        DefaultTableModel model = (DefaultTableModel) view.getTable().getModel();

        for (Truong truong : dsTruong) {
            List<Nganh> dsNganh = nganhDAO.searchNganhByTruong(maNganh, truong.getIdTruong());
            
            for (Nganh ng : dsNganh) {
                model.addRow(new Object[]{
                    truong.getMaTruong(),
                    truong.getTenTruong(),
                    ng.getMaNganh(),
                    ng.getTenNganh(),
                    ng.getChiTieu(),
                    ng.getKhoiXetTuyen().toString()
                });
            }
        }

        if (model.getRowCount() == 0) {
            JOptionPane.showMessageDialog(view, "Không tìm thấy kết quả phù hợp", 
                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void searchByTruong(String keyword) throws Exception {
        List<Truong> dsTruong = truongDAO.searchTruong(keyword);
        DefaultTableModel model = (DefaultTableModel) view.getTable().getModel();

        if (dsTruong.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Không tìm thấy trường phù hợp", 
                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        for (Truong truong : dsTruong) {
            List<Nganh> dsNganh = nganhDAO.getNganhByTruong(truong.getIdTruong());
            
            if (dsNganh.isEmpty()) {
                // Nếu trường không có ngành nào, vẫn hiển thị thông tin trường
                model.addRow(new Object[]{
                    truong.getMaTruong(),
                    truong.getTenTruong(),
                    "",
                    "",
                    "",
                    ""
                });
            } else {
                // Hiển thị tất cả các ngành của trường
                for (Nganh ng : dsNganh) {
                    model.addRow(new Object[]{
                        truong.getMaTruong(),
                        truong.getTenTruong(),
                        ng.getMaNganh(),
                        ng.getTenNganh(),
                        ng.getChiTieu(),
                        ng.getKhoiXetTuyen().toString()
                    });
                }
            }
        }
    }

//    private void searchByNganh(String keyword) throws Exception {
//        List<nganh> dsNganh = nganhDAO.searchNganh(keyword);
//        DefaultTableModel model = (DefaultTableModel) view.getTable().getModel();
//
//        if (dsNganh.isEmpty()) {
//            JOptionPane.showMessageDialog(view, "Không tìm thấy ngành phù hợp", 
//                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
//            return;
//        }
//
//        for (nganh ng : dsNganh) {
//            truongDHorCD truong = truongDAO.getTruongById(ng.getIdTruong());
//            
//            if (truong != null) {
//                model.addRow(new Object[]{
//                    truong.getMaTruong(),
//                    truong.getTenTruong(),
//                    ng.getMaNganh(),
//                    ng.getTenNganh(),
//                    ng.getChiTieu(),
//                    ng.getKhoiXetTuyen().toString()
//                });
//            }
//        }
//    }
    
    private void searchByNganh(String keyword) throws Exception {
        // 1. Lấy danh sách mã ngành phù hợp
        List<String> dsMaNganh = nganhDAO.searchMaNganh(keyword);
        List<Truong> dsTruong = truongDAO.getTruongByListMaNganh(dsMaNganh);
        DefaultTableModel model = (DefaultTableModel) view.getTable().getModel();

        if (dsTruong.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Không tìm thấy trường phù hợp", 
                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        for (Truong truong : dsTruong) {
            List<Nganh> dsNganh = nganhDAO.getNganhByTruong(truong.getIdTruong());
            
            if (dsNganh.isEmpty()) {
                // Nếu trường không có ngành nào, vẫn hiển thị thông tin trường
                model.addRow(new Object[]{
                    truong.getMaTruong(),
                    truong.getTenTruong(),
                    "",
                    "",
                    "",
                    ""
                });
            } else {
                // Hiển thị tất cả các ngành của trường
                for (Nganh ng : dsNganh) {
                    model.addRow(new Object[]{
                        truong.getMaTruong(),
                        truong.getTenTruong(),
                        ng.getMaNganh(),
                        ng.getTenNganh(),
                        ng.getChiTieu(),
                        ng.getKhoiXetTuyen().toString()
                    });
                }
            }
        }
    }
}