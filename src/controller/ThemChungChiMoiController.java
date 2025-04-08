package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import javax.swing.JOptionPane;
import dao.ChungChiDAO;
import model.ChungChi;
import view.ThemChungChiMoiView;

public class ThemChungChiMoiController {
    private ThemChungChiMoiView view;
    private ChungChiDAO ccDao;
    private int idHS;

    public ThemChungChiMoiController(ThemChungChiMoiView view, int idHS) {
        this.view = view;
        this.idHS = idHS;
        this.ccDao = new ChungChiDAO();

        this.view.getUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateChungChi();
            }
        });
        
        // Thêm sự kiện cho nút thoát
        this.view.getThoatButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	//System.out.println("ấn nút !");
                backToMenu();
            }
        });
    }

    private void updateChungChi() {
        // Lấy dữ liệu từ view
        String tenChungChi = view.getChungchi().getText();
        String linkChungMinh = view.getLinkchungminh().getText();
        
        // Lấy ngày từ JComboBox
        int day = (int) view.getDay().getSelectedItem();
        int month = (int) view.getMonth().getSelectedItem();
        int year = (int) view.getYear().getSelectedItem();
        
        // Chuyển đổi thành java.sql.Date
        Date ngayCap = new Date(year - 1900, month - 1, day);
        
        // Kiểm tra dữ liệu đầu vào
        if (tenChungChi.isEmpty() || linkChungMinh.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Tạo đối tượng chungChi
        ChungChi cc = new ChungChi();
        cc.setIdHS(idHS);
        cc.setTenChungChi(tenChungChi);
        cc.setNgayCap(ngayCap);
        cc.setLinkChungChi(linkChungMinh);
        
        // Kiểm tra xem học sinh đã có chứng chỉ chưa
        ChungChi existingCC = ccDao.getChungChiByHS(idHS);
        
        boolean success;
        if (existingCC != null) {
            // Nếu đã có thì cập nhật
            cc.setIdChungChi(existingCC.getIdChungChi());
            success = ccDao.updateChungChi(cc);
        } else {
            // Nếu chưa có thì thêm mới
            success = ccDao.addChungChi(cc);
        }
        
        if (success) {
            JOptionPane.showMessageDialog(view, "Cập nhật chứng chỉ thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            backToMenu();
        } else {
            JOptionPane.showMessageDialog(view, "Cập nhật chứng chỉ thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void backToMenu() {
//        MenuHocSinhView frame = new MenuHocSinhView();
//        frame.setVisible(true);
//        // Khởi tạo controller
//        new MenuHocSinhController(frame);
        view.dispose();
    }
}