package controller;

import dao.TaiKhoanDAO;
import dao.NganhDAO;
import dao.TruongDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.TaiKhoan;
import model.Nganh;
import model.Truong;
import util.SessionManager;
import view.ThemNganhMoiView;
import view.DanhSachTruongView;
import view.ChiTietTruongView;

public class ChiTietTruongController implements NganhParentController  {
    private ChiTietTruongView view;
    private TruongDAO truongDAO;
    private NganhDAO nganhDAO;
    private DefaultTableModel tableModel;
    private int currentIdTruong;
    
    public ChiTietTruongController(ChiTietTruongView view, int idTruong) {
        this.view = view;
        this.truongDAO = new TruongDAO();
        this.nganhDAO = new NganhDAO();
        this.currentIdTruong = idTruong;
        
        initTableModel();
        loadTruongData();
        loadNganhData();
        
        // Thêm action listeners
        view.addThoatButtonListener(new ThoatButtonListener());
        view.addUpdateButtonListener(new UpdateButtonListener());
        view.addXoaButtonListener(new XoaButtonListener());
        view.addSuaButtonListener(new SuaButtonListener());
        view.addThemButtonListener(new ThemButtonListener());
    }
    
    private void initTableModel() {
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        tableModel.addColumn("Mã Ngành");
        tableModel.addColumn("Tên Ngành");
        tableModel.addColumn("Chỉ Tiêu");
        tableModel.addColumn("Phương Thức");
        tableModel.addColumn("Khối Xét Tuyển");
        tableModel.addColumn("Ưu Tiên");
        
        view.getTable().setModel(tableModel);
    }
    
    private void loadTruongData() {
        Truong truong = truongDAO.getTruongById(currentIdTruong);
        if (truong != null) {
            view.getTenTruongField().setText(truong.getTenTruong());
            view.getMaTruongField().setText(truong.getMaTruong());
            view.getDiaChiField().setText(truong.getDiaChi());
            view.getWebsiteField().setText(truong.getWebsite());
            view.getTitleLabel().setText(">> Thông Tin Của " + truong.getTenTruong());
        }
    }
    
    private void loadNganhData() {
        List<Nganh> list = nganhDAO.getNganhByTruong(currentIdTruong);
        updateTableData(list);
    }
    
    private void updateTableData(List<Nganh> list) {
        tableModel.setRowCount(0);
        
        for (Nganh n : list) {
            Object[] row = {
                n.getMaNganh(),
                n.getTenNganh(),
                n.getChiTieu(),
                n.getPhuongThuc(),
                n.getKhoiXetTuyen(),
                n.getUuTien()
            };
            tableModel.addRow(row);
        }
    }
    
    class ThoatButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.dispose();
            DanhSachTruongView dsView = new DanhSachTruongView();
            new DanhSachTruongController(dsView);
            dsView.setVisible(true);
        }
    }
    
    class UpdateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Truong truong = new Truong();
            truong.setIdTruong(currentIdTruong);
            truong.setTenTruong(view.getTenTruongField().getText());
            truong.setMaTruong(view.getMaTruongField().getText());
            truong.setDiaChi(view.getDiaChiField().getText());
            truong.setWebsite(view.getWebsiteField().getText());
            
            boolean success = truongDAO.updateTruong(truong);
            
            if (success) {
                JOptionPane.showMessageDialog(view, 
                    "Cập nhật thông tin trường thành công", 
                    "Thông báo", 
                    JOptionPane.INFORMATION_MESSAGE);
                loadTruongData();
            } else {
                JOptionPane.showMessageDialog(view, 
                    "Cập nhật thông tin trường thất bại", 
                    "Lỗi", 
                    JOptionPane.ERROR_MESSAGE);
            }
            view.dispose();
        }
    }
    
    class XoaButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = view.getTable().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(view, 
                    "Vui lòng chọn ngành cần xóa", 
                    "Thông báo", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String maNganh = (String) tableModel.getValueAt(selectedRow, 0);
            String tenNganh = (String) tableModel.getValueAt(selectedRow, 1);
            
            int confirm = JOptionPane.showConfirmDialog(
                view, 
                "Bạn có chắc chắn muốn xóa ngành " + tenNganh + "?", 
                "Xác nhận xóa", 
                JOptionPane.YES_NO_OPTION
            );
            
            if (confirm == JOptionPane.YES_OPTION) {
                boolean success = nganhDAO.deleteNganh(maNganh);
                if (success) {
                    JOptionPane.showMessageDialog(view, 
                        "Xóa ngành thành công", 
                        "Thông báo", 
                        JOptionPane.INFORMATION_MESSAGE);
                    loadNganhData();
                } else {
                    JOptionPane.showMessageDialog(view, 
                        "Xóa ngành thất bại", 
                        "Lỗi", 
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
    
//    class SuaButtonListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            int selectedRow = view.getTable().getSelectedRow();
//            if (selectedRow == -1) {
//                JOptionPane.showMessageDialog(view, 
//                    "Vui lòng chọn ngành cần sửa", 
//                    "Thông báo", 
//                    JOptionPane.WARNING_MESSAGE);
//                return;
//            }
//            
//            String maNganh = (String) tableModel.getValueAt(selectedRow, 0);
//            nganh nganh = nganhDAO.getNganhByMaNganh(maNganh);
//            
//            if (nganh != null) {
//                view.setVisible(false);
//                addNganhView addView = new addNganhView(currentIdTruong, nganh);
//                new addNganhController(addView, currentIdTruong, nganh);
//                //new addNganhController(addView, profileTruongController.this);
//                addView.setVisible(true);
//            }
//        }
//    }
    class SuaButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedRow = view.getTable().getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(view, 
                    "Vui lòng chọn ngành cần sửa", 
                    "Thông báo", 
                    JOptionPane.WARNING_MESSAGE);
                return;
            }
            
            String maNganh = (String) tableModel.getValueAt(selectedRow, 0);
            Nganh nganh = nganhDAO.getNganhByMaNganh(maNganh);
            
            if (nganh != null) {
                view.setVisible(false);
                
                try {
                    // Lấy tài khoản tạm thời
                    int idTaiKhoan = new TruongDAO().getIdTaiKhoanByIdTruong(currentIdTruong);
                    TaiKhoan accTemp = new TaiKhoanDAO().getAccountByIdTaiKhoan(idTaiKhoan);
                    
                    // Debug trước khi push
                    System.out.println("Pushing temp account for edit: " + accTemp.getTaiKhoan());
                    
                    // Push tài khoản vào session
                    SessionManager.getInstance().pushAccount(accTemp);
                    System.out.println("ID tài khoản tạm thời: " + accTemp.getId());
                    
                    // Tạo và hiển thị cửa sổ chỉnh sửa
                    ThemNganhMoiView addView = new ThemNganhMoiView(currentIdTruong, nganh);
                    new ThemNganhMoiController(addView, currentIdTruong, nganh);
                    
                    // Xử lý khi cửa sổ đóng
                    addView.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent e) {
                            // Debug trước khi pop
                            System.out.println("Popping temp account after edit");
                            
                            // Khôi phục tài khoản trước đó
                            SessionManager.getInstance().popAccount();
                            
                            // Debug sau khi pop
                            System.out.println("Current account after pop: " + 
                                SessionManager.getInstance().getCurrentAccount().getId());
                            
                            // Hiển thị lại view chính
                            view.setVisible(true);
                        }
                    });
                    
                    // Cấu hình đóng cửa sổ
                    addView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    addView.setVisible(true);
                    
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(view, 
                        "Lỗi khi lấy thông tin tài khoản", 
                        "Lỗi", 
                        JOptionPane.ERROR_MESSAGE);
                    view.setVisible(true); // Hiển thị lại view nếu có lỗi
                }
            }
        }
    }

//    class ThemButtonListener implements ActionListener {
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            view.setVisible(false);
////            nganh newNganh = new nganh();
////            newNganh.setIdTruong(currentIdTruong); // Set trực tiếp
////            addNganhView addView = new addNganhView(currentIdTruong, null);
////            new addNganhController(addView, currentIdTruong);
////            addView.setVisible(true);
//            // System.out.println("Mã Trường Là: " + currentIdTruong);
//            int idTaiKhoan = new truongDAO().getIdTaiKhoanByIdTruong(currentIdTruong);
//            try {
//				account accTemp = new accountDAO().getAccountByIdTaiKhoan(idTaiKhoan);
//				SessionManager.getInstance().pushAccount(accTemp);
//				try {
//					System.out.println(currentIdTruong);
//					
//		            addNganhView addView = new addNganhView(currentIdTruong, null);
//		            
//		            new addNganhController(addView, currentIdTruong);
//		            addView.setVisible(true);
//				} finally {
//				    SessionManager.getInstance().popAccount();
//				}
//			} catch (SQLException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//
//        }
//    }

    class ThemButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            view.setVisible(false);
            int idTaiKhoan = new TruongDAO().getIdTaiKhoanByIdTruong(currentIdTruong);
            TaiKhoanDAO DAOnew = new TaiKhoanDAO();
            try {
                TaiKhoan accTemp = DAOnew.getAccountByIdTaiKhoan(idTaiKhoan);
                
                // Debug trước khi push
                System.out.println("Pushing temp account: " + accTemp.getTaiKhoan());
                
                SessionManager.getInstance().pushAccount(accTemp);
                System.out.println("ID tai khoan la: " + accTemp.getId());
                
                // Debug sau khi push
                System.out.println("Current account after push: " + 
                    SessionManager.getInstance().getCurrentAccount().getId());
                
                ThemNganhMoiView addView = new ThemNganhMoiView(currentIdTruong, null);
                new ThemNganhMoiController(addView, currentIdTruong);
                
                // Thêm WindowListener để xử lý khi cửa sổ đóng
                WindowListener listener = new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        // Xử lý khi đóng
                        System.out.println("Popping temp account");
                        SessionManager.getInstance().popAccount();
                        
                        // Debug sau khi pop
                        System.out.println("Current account after pop: " + 
                            SessionManager.getInstance().getCurrentAccount().getId());
                    }
                };
                
                // Đảm bảo cửa sổ sẽ kích hoạt sự kiện windowClosed khi đóng
                addView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                addView.setVisible(true);
                
            } catch (SQLException e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(view, "Lỗi khi lấy thông tin tài khoản", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    @Override
    public void refreshNganhData() {
        loadNganhData();
    }
    
    @Override
    public void showNganhView() {
        // Implement nếu cần
    }
}