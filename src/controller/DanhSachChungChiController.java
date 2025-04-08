package controller;

//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import dao.ChungChiDAO;
import model.ChungChi;
import view.DanhSachChungChiView;

public class DanhSachChungChiController {
    private DanhSachChungChiView view;
    private int idHS;
    private ChungChiDAO chungChiDao;
    private DefaultTableModel tableModel;

    public DanhSachChungChiController(DanhSachChungChiView view, int idHS) {
        this.view = view;
        this.idHS = idHS;
        this.chungChiDao = new ChungChiDAO();
        
        initializeTable();
        loadChungChiData();
        addEventListeners();
    }

    private void initializeTable() {
        // Tạo model với các cột
        String[] columnNames = {"ID Chứng chỉ", "ID Học sinh", "Tên chứng chỉ", "Ngày cấp", "Link chứng chỉ"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        // Gán model cho table
        view.getTable().setModel(tableModel);
        
        // Thiết lập độ rộng cột
        view.getTable().getColumnModel().getColumn(0).setPreferredWidth(80);
        view.getTable().getColumnModel().getColumn(1).setPreferredWidth(80);
        view.getTable().getColumnModel().getColumn(2).setPreferredWidth(200);
        view.getTable().getColumnModel().getColumn(3).setPreferredWidth(100);
        view.getTable().getColumnModel().getColumn(4).setPreferredWidth(250);
    }

    private void loadChungChiData() {
        List<ChungChi> dsChungChi = chungChiDao.getListChungChiByiDHS(idHS);
        tableModel.setRowCount(0); // Xóa dữ liệu cũ
        
        if (dsChungChi.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Không có chứng chỉ nào cho học sinh này", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        for (ChungChi cc : dsChungChi) {
            Object[] rowData = {
                cc.getIdChungChi(),
                cc.getIdHS(),
                cc.getTenChungChi(),
                cc.getNgayCap(),
                cc.getLinkChungChi()
            };
            tableModel.addRow(rowData);
        }
    }

    private void addEventListeners() {
        view.getButtonQuayLai().addActionListener(e -> view.dispose());
        
        view.getButtonXoa().addActionListener(e -> {
            int selectedRow = view.getTable().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn chứng chỉ cần xóa", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            int confirm = JOptionPane.showConfirmDialog(
                view, 
                "Bạn có chắc chắn muốn xóa chứng chỉ này?", 
                "Xác nhận xóa", 
                JOptionPane.YES_NO_OPTION
            );
            
            if (confirm == JOptionPane.YES_OPTION) {
                int idChungChi = (int) tableModel.getValueAt(selectedRow, 0);
                boolean result = chungChiDao.deleteChungChi(idChungChi);
                
                if (result) {
                    JOptionPane.showMessageDialog(view, "Xóa chứng chỉ thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    loadChungChiData(); // Tải lại dữ liệu
                } else {
                    JOptionPane.showMessageDialog(view, "Xóa chứng chỉ thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}