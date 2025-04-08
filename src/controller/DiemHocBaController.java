package controller;

import dao.DiemHocBaDAO;
import model.DiemHocBa;
import view.DiemHocBaView;
import javax.swing.*;

public class DiemHocBaController {
    private DiemHocBaView view;
    private DiemHocBaDAO diemHocBaDAO;
    private int currentHSId;

    public DiemHocBaController(DiemHocBaView view, int idHS) {
        this.view = view;
        this.diemHocBaDAO = new DiemHocBaDAO();
        this.currentHSId = idHS;
        
        initController();
        loadData();
    }

    private void initController() {
        // Xử lý sự kiện nút cập nhật
        view.getUpdateButton().addActionListener(e -> updateDiemHocBa());
        
        // Xử lý sự kiện nút thoát
        view.getThoatButton().addActionListener(e -> view.dispose());
    }

    private void loadData() {
        // Load data for grade 10
        DiemHocBa diem10 = diemHocBaDAO.getDiemHocBaByHSAndNamHoc(currentHSId, "10");
        if (diem10 != null) {
            view.getToan10().setText(String.valueOf(diem10.getToan()));
            view.getLy10().setText(String.valueOf(diem10.getLy()));
            view.getHoa10().setText(String.valueOf(diem10.getHoa()));
            view.getSinh10().setText(String.valueOf(diem10.getSinh()));
            view.getVan10().setText(String.valueOf(diem10.getVan()));
            view.getSu10().setText(String.valueOf(diem10.getSu()));
            view.getDia10().setText(String.valueOf(diem10.getDia()));
            view.getGdcd10().setText(String.valueOf(diem10.getGdcd()));
            view.getNn10().setText(String.valueOf(diem10.getAnh()));
        }

        // Load data for grade 11
        DiemHocBa diem11 = diemHocBaDAO.getDiemHocBaByHSAndNamHoc(currentHSId, "11");
        if (diem11 != null) {
            view.getToan11().setText(String.valueOf(diem11.getToan()));
            view.getLy11().setText(String.valueOf(diem11.getLy()));
            view.getHoa11().setText(String.valueOf(diem11.getHoa()));
            view.getSinh11().setText(String.valueOf(diem11.getSinh()));
            view.getVan11().setText(String.valueOf(diem11.getVan()));
            view.getSu11().setText(String.valueOf(diem11.getSu()));
            view.getDia11().setText(String.valueOf(diem11.getDia()));
            view.getGdcd11().setText(String.valueOf(diem11.getGdcd()));
            view.getNn11().setText(String.valueOf(diem11.getAnh()));
        }

        // Load data for grade 12
        DiemHocBa diem12 = diemHocBaDAO.getDiemHocBaByHSAndNamHoc(currentHSId, "12");
        if (diem12 != null) {
            view.getToan12().setText(String.valueOf(diem12.getToan()));
            view.getLy12().setText(String.valueOf(diem12.getLy()));
            view.getHoa12().setText(String.valueOf(diem12.getHoa()));
            view.getSinh12().setText(String.valueOf(diem12.getSinh()));
            view.getVan12().setText(String.valueOf(diem12.getVan()));
            view.getSu12().setText(String.valueOf(diem12.getSu()));
            view.getDia12().setText(String.valueOf(diem12.getDia()));
            view.getGdcd12().setText(String.valueOf(diem12.getGdcd()));
            view.getNn12().setText(String.valueOf(diem12.getAnh()));
        }
    }

    private void updateDiemHocBa() {
        try {
            // Process grade 10 data
            processGradeData("10", 
                view.getToan10(), view.getLy10(), view.getHoa10(), view.getSinh10(),
                view.getVan10(), view.getSu10(), view.getDia10(), view.getGdcd10(), view.getNn10());

            // Process grade 11 data
            processGradeData("11", 
                view.getToan11(), view.getLy11(), view.getHoa11(), view.getSinh11(),
                view.getVan11(), view.getSu11(), view.getDia11(), view.getGdcd11(), view.getNn11());

            // Process grade 12 data
            processGradeData("12", 
                view.getToan12(), view.getLy12(), view.getHoa12(), view.getSinh12(),
                view.getVan12(), view.getSu12(), view.getDia12(), view.getGdcd12(), view.getNn12());

            JOptionPane.showMessageDialog(view, "Cập nhật điểm học bạ thành công!", 
                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(view, "Vui lòng nhập điểm hợp lệ (số thực)!", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi cập nhật điểm: " + e.getMessage(), 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void processGradeData(String namHoc, JTextField toan, JTextField ly, JTextField hoa, 
                                JTextField sinh, JTextField van, JTextField su, 
                                JTextField dia, JTextField gdcd, JTextField anh) {
        // Check if any field has data
        if (!toan.getText().isEmpty() || !ly.getText().isEmpty() || !hoa.getText().isEmpty() ||
            !sinh.getText().isEmpty() || !van.getText().isEmpty() || !su.getText().isEmpty() ||
            !dia.getText().isEmpty() || !gdcd.getText().isEmpty() || !anh.getText().isEmpty()) {
            
            DiemHocBa diem = new DiemHocBa();
            diem.setIdHS(currentHSId);
            diem.setNamHoc(namHoc);
            diem.setToan(parseFloat(toan.getText()));
            diem.setLy(parseFloat(ly.getText()));
            diem.setHoa(parseFloat(hoa.getText()));
            diem.setSinh(parseFloat(sinh.getText()));
            diem.setVan(parseFloat(van.getText()));
            diem.setSu(parseFloat(su.getText()));
            diem.setDia(parseFloat(dia.getText()));
            diem.setGdcd(parseFloat(gdcd.getText()));
            diem.setAnh(parseFloat(anh.getText()));

            // Check if record exists
            DiemHocBa existing = diemHocBaDAO.getDiemHocBaByHSAndNamHoc(currentHSId, namHoc);
            if (existing != null) {
                diem.setidDiemHocBa(existing.getidDiemHocBa());
                diemHocBaDAO.updateDiemHocBa(diem);
            } else {
                diemHocBaDAO.insertDiemHocBa(diem);
            }
        }
    }

    private float parseFloat(String text) {
        return text.isEmpty() ? 0 : Float.parseFloat(text);
    }
}