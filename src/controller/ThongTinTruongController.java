package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import dao.TruongDAO;
import model.Truong;
import view.MenuTruongView;
import view.ThongTinTruongView;

public class ThongTinTruongController {
    private ThongTinTruongView view;
    private TruongDAO truongDAO;
    private int idTaiKhoan;

    public ThongTinTruongController(ThongTinTruongView view, int idTaiKhoan) {
        this.view = view;
        this.idTaiKhoan = idTaiKhoan;
        this.truongDAO = new TruongDAO();
        
        // Load thông tin trường nếu đã có
        loadTruongInfo();
        
        // Thêm sự kiện cho nút cập nhật
        this.view.getUpdateButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTruong();
            }
        });
        
        // Thêm sự kiện cho nút thoát
        this.view.getThoatButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backToTruongDHCDView();
            }
        });
    }

    private void loadTruongInfo() {
        Truong truong = truongDAO.getTruongByTaiKhoan(idTaiKhoan);
        if (truong != null) {
            view.getTenTruong().setText(truong.getTenTruong());
            view.getMaTruong().setText(truong.getMaTruong());
            view.getDiaChi().setText(truong.getDiaChi());
            view.getWebsite().setText(truong.getWebsite());
        }
    }

    private void updateTruong() {
        // Lấy dữ liệu từ view
        String tenTruong = view.getTenTruong().getText().trim();
        String maTruong = view.getMaTruong().getText().trim();
        String diaChi = view.getDiaChi().getText().trim();
        String website = view.getWebsite().getText().trim();
        
        // Kiểm tra dữ liệu đầu vào
        if (tenTruong.isEmpty() || maTruong.isEmpty() || diaChi.isEmpty() || website.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Tạo đối tượng trường
        Truong truong = new Truong();
        truong.setIdTaiKhoan(idTaiKhoan);
        truong.setTenTruong(tenTruong);
        truong.setMaTruong(maTruong);
        truong.setDiaChi(diaChi);
        truong.setWebsite(website);
        
        // Kiểm tra xem trường đã tồn tại chưa
        Truong existingTruong = truongDAO.getTruongByTaiKhoan(idTaiKhoan);
        
        boolean success;
        if (existingTruong != null) {
            // Nếu đã có thì cập nhật
            truong.setIdTruong(existingTruong.getIdTruong());
            success = truongDAO.updateTruong(truong);
        } else {
            // Nếu chưa có thì thêm mới
            success = truongDAO.addTruong(truong);
        }
        
        if (success) {
            JOptionPane.showMessageDialog(view, "Cập nhật thông tin trường thành công!", "Thành công", JOptionPane.INFORMATION_MESSAGE);
            backToTruongDHCDView();
        } else {
            JOptionPane.showMessageDialog(view, "Cập nhật thông tin trường thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        view.dispose();
    }

    private void backToTruongDHCDView() {
        MenuTruongView frame = new MenuTruongView();
        frame.setVisible(true);
        new MenuTruongController(frame);
        view.dispose();
    }
}