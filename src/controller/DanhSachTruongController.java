package controller;

import dao.TruongDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Truong;
import view.MenuAdminView;
import view.DanhSachTruongView;
import view.ChiTietTruongView;

public class DanhSachTruongController {
    private DanhSachTruongView view;
    private TruongDAO truongDAO;
    private DefaultTableModel tableModel;
    
    public DanhSachTruongController(DanhSachTruongView view) {
        this.view = view;
        this.truongDAO = new TruongDAO();
        
        initTableModel();
        loadAllTruong();
        
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
        tableModel.addColumn("Mã Trường");
        tableModel.addColumn("Tên Trường");
        tableModel.addColumn("Địa Chỉ");
        tableModel.addColumn("Website");
        
        // Gán model cho JTable trong view
        view.getTable().setModel(tableModel);
    }
    
    private void loadAllTruong() {
        List<Truong> list = truongDAO.getAllTruong();
        updateTableData(list);
    }
    
    private void updateTableData(List<Truong> list) {
        // Xóa dữ liệu cũ
        tableModel.setRowCount(0);
        
        // Thêm dữ liệu mới
        for (Truong t : list) {
            Object[] row = {
                t.getIdTruong(),
                t.getMaTruong(),
                t.getTenTruong(),
                t.getDiaChi(),
                t.getWebsite()
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
                JOptionPane.showMessageDialog(view, "Vui lòng chọn trường cần xóa", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int idTruong = (int) tableModel.getValueAt(selectedRow, 0);
            String tenTruong = (String) tableModel.getValueAt(selectedRow, 2);
            
            int confirm = JOptionPane.showConfirmDialog(
                view, 
                "Bạn có chắc chắn muốn xóa trường " + tenTruong + "?", 
                "Xác nhận xóa", 
                JOptionPane.YES_NO_OPTION
            );
            
            if (confirm == JOptionPane.YES_OPTION) {
                boolean success = truongDAO.deleteTruong(idTruong);
                if (success) {
                    JOptionPane.showMessageDialog(view, "Xóa trường thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    loadAllTruong(); // Tải lại danh sách
                } else {
                    JOptionPane.showMessageDialog(view, "Xóa trường thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
    class ChiTietButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = view.getTable().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn trường để xem chi tiết", "Thông báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int idTruong = (int) tableModel.getValueAt(selectedRow, 0);
            
            // Đóng cửa sổ hiện tại
            view.dispose();
            
            // Mở cửa sổ profile trường
            ChiTietTruongView profileView = new ChiTietTruongView();
            new ChiTietTruongController(profileView, idTruong);
            profileView.setVisible(true);
        }
    }
    
    class TimKiemButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String tenCanTim = view.getTenTruong().trim();
            
            if (tenCanTim.isEmpty()) {
                loadAllTruong();
            } else {
                List<Truong> list = truongDAO.searchTruong(tenCanTim);
                if (list.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Không tìm thấy trường phù hợp", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
                updateTableData(list);
            }
        }
    }
}