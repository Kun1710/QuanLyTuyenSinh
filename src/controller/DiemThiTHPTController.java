package controller;

import java.awt.event.ActionEvent;
import java.sql.SQLException;
import dao.DiemThiTHPTDAO;
import model.DiemThiTHPT;
import view.DiemThiTHPView;

public class DiemThiTHPTController {
    private DiemThiTHPTDAO diemThiDAO;
    private DiemThiTHPView view;
    private int currentStudentId;


    public DiemThiTHPTController(DiemThiTHPView view, int idHS) {
		this.diemThiDAO = new DiemThiTHPTDAO();
        this.view = view;
        this.currentStudentId = idHS;
        initController();
        loadDiemThi();
    }

    private void initController() {
        view.addUpdateButtonListener(this::handleUpdate);
        view.addExitButtonListener(this::handleExit);
    }

    private void loadDiemThi() {
        try {
            DiemThiTHPT diemThi = diemThiDAO.getByStudentId(currentStudentId);
            if (diemThi != null) {
                populateFields(diemThi);
            }
            // Nếu không có điểm, giữ nguyên các trường trống
        } catch (SQLException ex) {
            handleError("Lỗi khi tải dữ liệu điểm thi", ex);
        }
    }

    private void populateFields(DiemThiTHPT diemThi) {
        view.getToanField().setText(String.valueOf(diemThi.getToan()));
        view.getVanField().setText(String.valueOf(diemThi.getVan()));
        view.getNgoainguField().setText(String.valueOf(diemThi.getAnh()));
        
        if ("A".equals(diemThi.getKhoiThi())) {
            view.getLyField().setText(String.valueOf(diemThi.getLy()));
            view.getHoaField().setText(String.valueOf(diemThi.getHoa()));
            view.getSinhField().setText(String.valueOf(diemThi.getSinh()));
            clearBlockCFields();
        } else {
            view.getSuField().setText(String.valueOf(diemThi.getSu()));
            view.getDiaField().setText(String.valueOf(diemThi.getDia()));
            view.getGdcdField().setText(String.valueOf(diemThi.getGdcd()));
            clearBlockAFields();
        }
    }

    private void handleUpdate(ActionEvent e) {
        try {
            DiemThiTHPT diemThi = createDiemThiFromInput();
            
            if (validateDiemThi(diemThi)) {
                boolean success = diemThiDAO.saveOrUpdate(diemThi);
                //System.out.println("YES" + success);
                if (success) {
                    view.showSuccessMessage("Cập nhật điểm thi thành công!");
                    view.setVisible(false); // Đóng view sau khi cập nhật thành công
                } else {
                    view.showErrorMessage("Cập nhật điểm thi thất bại");
                }
            }
        } catch (NumberFormatException ex) {
            view.showErrorMessage("Vui lòng nhập điểm là số hợp lệ (0-10)");
        }
    }

    private DiemThiTHPT createDiemThiFromInput() {
        DiemThiTHPT diemThi = new DiemThiTHPT();
        diemThi.setIdHS(currentStudentId);
        
        // Môn học chung
        diemThi.setToan(parseScore(view.getToanField().getText()));
        diemThi.setVan(parseScore(view.getVanField().getText()));
        diemThi.setAnh(parseScore(view.getNgoainguField().getText()));
        
        // Xác định khối
        boolean isBlockA = isBlockASelected();
        boolean isBlockC = isBlockCSelected();
        
        if (isBlockA && isBlockC) {
            throw new IllegalArgumentException("Chỉ được chọn một khối thi");
        }
        
        if (isBlockA) {
            diemThi.setKhoiThi("A");
            diemThi.setLy(parseScore(view.getLyField().getText()));
            diemThi.setHoa(parseScore(view.getHoaField().getText()));
            diemThi.setSinh(parseScore(view.getSinhField().getText()));
            // Đặt điểm khối C về 0
            diemThi.setSu(0);
            diemThi.setDia(0);
            diemThi.setGdcd(0);
        } else {
            diemThi.setKhoiThi("C");
            diemThi.setSu(parseScore(view.getSuField().getText()));
            diemThi.setDia(parseScore(view.getDiaField().getText()));
            diemThi.setGdcd(parseScore(view.getGdcdField().getText()));
            // Đặt điểm khối A về 0
            diemThi.setLy(0);
            diemThi.setHoa(0);
            diemThi.setSinh(0);
        }
        
        return diemThi;
    }

    private boolean validateDiemThi(DiemThiTHPT diemThi) {
        // Kiểm tra điểm trong khoảng 0-10
        if (!isValidScore(diemThi.getToan()) || !isValidScore(diemThi.getVan()) || 
            !isValidScore(diemThi.getAnh()) || !isValidScore(diemThi.getLy()) || 
            !isValidScore(diemThi.getHoa()) || !isValidScore(diemThi.getSinh()) || 
            !isValidScore(diemThi.getSu()) || !isValidScore(diemThi.getDia()) || 
            !isValidScore(diemThi.getGdcd())) {
            view.showErrorMessage("Điểm phải trong khoảng 0-10");
            return false;
        }
        
        // Kiểm tra ít nhất một khối được chọn
        if (!isBlockASelected() && !isBlockCSelected()) {
            view.showErrorMessage("Vui lòng nhập điểm cho ít nhất một khối (A hoặc C)");
            return false;
        }
        
        return true;
    }

    private boolean isBlockASelected() {
        return !view.getLyField().getText().isEmpty() || 
               !view.getHoaField().getText().isEmpty() || 
               !view.getSinhField().getText().isEmpty();
    }

    private boolean isBlockCSelected() {
        return !view.getSuField().getText().isEmpty() || 
               !view.getDiaField().getText().isEmpty() || 
               !view.getGdcdField().getText().isEmpty();
    }

    private float parseScore(String text) {
        if (text == null || text.trim().isEmpty()) {
            return 0;
        }
        return Float.parseFloat(text);
    }

    private boolean isValidScore(float score) {
        return score >= 0 && score <= 10;
    }

    private void handleExit(ActionEvent e) {
        view.setVisible(false);
    }

//    private void clearFields() {
//        clearCommonFields();
//        clearBlockAFields();
//        clearBlockCFields();
//    }
//
//    private void clearCommonFields() {
//        view.getToanField().setText("");
//        view.getVanField().setText("");
//        view.getNgoainguField().setText("");
//    }

    private void clearBlockAFields() {
        view.getLyField().setText("");
        view.getHoaField().setText("");
        view.getSinhField().setText("");
    }

    private void clearBlockCFields() {
        view.getSuField().setText("");
        view.getDiaField().setText("");
        view.getGdcdField().setText("");
    }

    private void handleError(String message, Exception ex) {
        ex.printStackTrace();
        view.showErrorMessage(message);
    }
}