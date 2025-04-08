package controller;

import dao.HocSinhDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.HocSinh;
import view.MenuTruongView;
import view.TimKiemHocSinhView;

public class TimKiemHocSinhController {
    private TimKiemHocSinhView view;
    private HocSinhDAO hocSinhDao;

    public TimKiemHocSinhController(TimKiemHocSinhView view) {
        this.view = view;
        this.hocSinhDao = new HocSinhDAO();
        
        // Thêm action listener cho các nút
        this.view.addSearchButtonListener(new SearchButtonListener());
        this.view.addThoatButtonListener(new ThoatButtonListener());
    }

    class SearchButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            searchHocSinh();
        }
    }

    class ThoatButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose();
            MenuTruongView frame = new MenuTruongView();
            frame.setVisible(true);
            new MenuTruongController(frame);
        }
    }

    private void searchHocSinh() {
        String hoTen = view.getHoTen();
        String cccd = view.getCCCD();
        
        // Kiểm tra nếu cả hai trường đều trống
        if ((hoTen == null || hoTen.trim().isEmpty()) && 
            (cccd == null || cccd.trim().isEmpty())) {
            JOptionPane.showMessageDialog(view, 
                "Vui lòng nhập ít nhất một thông tin để tìm kiếm", 
                "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        List<HocSinh> hocSinhList;
        
        if (cccd != null && !cccd.trim().isEmpty()) {
            // Tìm kiếm theo CCCD (chính xác)
            HocSinh hs = hocSinhDao.getHocSinhByCCCD(cccd);
            if (hs != null) {
                displaySingleResult(hs);
            } else {
                JOptionPane.showMessageDialog(view, 
                    "Không tìm thấy học sinh với CCCD: " + cccd, 
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                clearTable();
            }
        } else {
            // Tìm kiếm theo tên (tương đối)
            hocSinhList = hocSinhDao.searchHocSinhByName(hoTen);
            if (!hocSinhList.isEmpty()) {
                displayMultipleResults(hocSinhList);
            } else {
                JOptionPane.showMessageDialog(view, 
                    "Không tìm thấy học sinh với tên: " + hoTen, 
                    "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                clearTable();
            }
        }
    }

    private void displaySingleResult(HocSinh hs) {
        DefaultTableModel model = (DefaultTableModel) view.getTblResults().getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ
        
        Object[] rowData = {
            hs.getCCCD(),
            hs.getHoVaTen(),
            hs.getNgaySinh(),
            hs.getDiaChi(),
            hs.isGioiTinh() ? "Nam" : "Nữ",
            hs.getDanToc()
        };
        model.addRow(rowData);
    }

    private void displayMultipleResults(List<HocSinh> hocSinhList) {
        DefaultTableModel model = (DefaultTableModel) view.getTblResults().getModel();
        model.setRowCount(0); // Xóa dữ liệu cũ
        
        for (HocSinh hs : hocSinhList) {
            Object[] rowData = {
                hs.getCCCD(),
                hs.getHoVaTen(),
                hs.getNgaySinh(),
                hs.getDiaChi(),
                hs.isGioiTinh() ? "Nam" : "Nữ",
                hs.getDanToc()
            };
            model.addRow(rowData);
        }
    }

    private void clearTable() {
        DefaultTableModel model = (DefaultTableModel) view.getTblResults().getModel();
        model.setRowCount(0);
    }
}