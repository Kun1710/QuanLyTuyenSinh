package controller;

import dao.HocSinhDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.HocSinh;
import view.MenuAdminView;
import view.DanhSachHocSinhView;
import view.ChiTietHocSinhView;

public class DanhSachHocSinhController {
    private DanhSachHocSinhView view;
    private HocSinhDAO hocSinhDAO;
    private DefaultTableModel tableModel;
    private int table_idHS_choose;
    
    public DanhSachHocSinhController(DanhSachHocSinhView view) {
        this.view = view;
        this.hocSinhDAO = new HocSinhDAO();
        
        initTableModel();
        loadAllHocSinh();
        
        // Thêm action listeners
        view.addThoatButtonListener(new ThoatButtonListener());
        view.addXoaButtonListener(new XoaButtonListener());
        view.addChiTietButtonListener(new ChiTietButtonListener());
        view.addTimKiemButtonListener(new TimKiemButtonListener());
    }
    
    private void initTableModel() {
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép chỉnh sửa trực tiếp trên bảng
            }
        };
        
        // Thêm các cột vào tableModel
        tableModel.addColumn("ID");
        tableModel.addColumn("Họ Tên");
        tableModel.addColumn("Ngày Sinh");
        tableModel.addColumn("Giới Tính");
        tableModel.addColumn("Dân Tộc");
        tableModel.addColumn("Địa Chỉ");
        tableModel.addColumn("CCCD");
        
        // Gán model cho JTable trong view
        view.getTable().setModel(tableModel);
    }
    
    private void loadAllHocSinh() {
        List<HocSinh> list = hocSinhDAO.getAllHocSinh();
        updateTableData(list);
    }
    
    private void updateTableData(List<HocSinh> list) {
        // Xóa dữ liệu cũ
        tableModel.setRowCount(0);
        
        // Thêm dữ liệu mới
        for (HocSinh hs : list) {
            Object[] row = {
                hs.getIdHS(),
                hs.getHoVaTen(),
                hs.getNgaySinh(),
                hs.isGioiTinh() ? "Nam" : "Nữ",
                hs.getDanToc(),
                hs.getDiaChi(),
                hs.getCCCD()
            };
            tableModel.addRow(row);
        }
    }
    
    class ThoatButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Đóng cửa sổ hiện tại
            view.dispose();
            
            // Mở lại cửa sổ admin
//            MenuAdminView adminView = new MenuAdminView();
//            adminView.setVisible(true);
//            new MenuAdminController(adminView);
        }
    }
    
    class XoaButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = view.getTable().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn học sinh cần xóa", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int idHS = (int) tableModel.getValueAt(selectedRow, 0);
            String tenHS = (String) tableModel.getValueAt(selectedRow, 1);
            
            int confirm = JOptionPane.showConfirmDialog(
                view, 
                "Bạn có chắc chắn muốn xóa học sinh " + tenHS + "?", 
                "Xác nhận xóa", 
                JOptionPane.YES_NO_OPTION
            );
            
            if (confirm == JOptionPane.YES_OPTION) {
                boolean success = hocSinhDAO.deleteHocSinh(idHS);
                if (success) {
                    JOptionPane.showMessageDialog(view, "Xóa học sinh thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    loadAllHocSinh();
                } else {
                    JOptionPane.showMessageDialog(view, "Xóa học sinh thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    class ChiTietButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = view.getTable().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn học sinh để xem chi tiết", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String cccd = (String) tableModel.getValueAt(selectedRow, 6);
            // Đóng cửa sổ hiện tại
            view.dispose();
            
            // Mở cửa sổ profile học sinh
            ChiTietHocSinhView profileView = new ChiTietHocSinhView();
            new ChiTietHocSinhController(profileView, cccd);
            profileView.setVisible(true);
        }
    }
    
    class TimKiemButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String tenCanTim = view.getTenTimKiem().trim();
            
            if (tenCanTim.isEmpty()) {
                loadAllHocSinh();
            } else {
                List<HocSinh> list = hocSinhDAO.searchHocSinhByName(tenCanTim);
                if (list.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Không tìm thấy học sinh phù hợp", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                updateTableData(list);
            }
        }
    }
}