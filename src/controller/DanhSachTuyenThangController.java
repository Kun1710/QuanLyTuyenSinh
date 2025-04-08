package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.DatabaseConnection;
import dao.ChungChiDAO;
import dao.HoSoDangKyDAO;
import dao.NguyenVongDAO;
import dao.TruongDAO;
import dao.HocSinhDAO;
import dao.NganhDAO;
import model.ChungChi;
import model.HoSoDangKy;
import model.NguyenVong;
import model.HocSinh;
import model.Nganh;
import model.Truong;
import view.DuyetTuyenThangView;
import view.DanhSachTuyenThangView;

public class DanhSachTuyenThangController {
    private DanhSachTuyenThangView view;
    private HoSoDangKyDAO hoSoDAO;
    private NguyenVongDAO nguyenVongDAO;
    private HocSinhDAO hocSinhDAO;
    private TruongDAO truongDAO;
    
    public DanhSachTuyenThangController(DanhSachTuyenThangView view) {
        this.view = view;
        this.hoSoDAO = new HoSoDangKyDAO();
        this.nguyenVongDAO = new NguyenVongDAO();
        this.hocSinhDAO = new HocSinhDAO();
        this.truongDAO = new TruongDAO();
        initController();
        loadEligibleStudents();
    }
    
    private void initController() {
        view.getThoatButton().addActionListener(e -> view.dispose());
        
        view.getChonButton().addActionListener(e -> {
            int selectedRow = view.getTable().getSelectedRow();
            if (selectedRow >= 0) {
                try {
                    int idHoSo = Integer.parseInt(view.getTable().getValueAt(selectedRow, 0).toString());
                    //System.out.println("được chạy không");
                    openDuyetTuyenThang(idHoSo);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, "Lỗi khi mở hồ sơ: day hả " + ex.getMessage(), 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(view, "Vui lòng chọn một hồ sơ", 
                    "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        });
    }
 // Thêm cột trạng thái vào model bảng (trong phương thức loadEligibleStudents)
    private void loadEligibleStudents() {
        try {
            List<HoSoDangKy> eligibleStudents = getEligibleStudents();
            
            DefaultTableModel model = (DefaultTableModel) view.getTable().getModel();
            model.setColumnIdentifiers(new Object[]{"ID Hồ sơ", "Tên học sinh", "Trường nguyện vọng", "Trạng thái"}); // Thêm cột trạng thái
            model.setRowCount(0);
            
            for (HoSoDangKy hoSo : eligibleStudents) {
                try {
                    HocSinh hs = hocSinhDAO.getHocSinhById(hoSo.getIdHS());
                    String tenHS = hs != null ? hs.getHoVaTen() : "Không xác định";
                    
                    List<NguyenVong> nguyenVongs = nguyenVongDAO.getAllByHoSoId(hoSo.getIdHoSo());
                    String tenTruong = "";
                    
                    if (!nguyenVongs.isEmpty()) {
                        NguyenVong nv = nguyenVongs.get(0);
                        Truong tr = truongDAO.getTruongById(nv.getIdTruong());
                        if (tr != null) {
                            tenTruong = tr.getTenTruong();
                        }
                    }
                    
                    // Thêm trạng thái vào dòng
                    model.addRow(new Object[]{
                        hoSo.getIdHoSo(),
                        tenHS,
                        tenTruong,
                        hoSo.getTrangThai() 
                    });
                } catch (Exception e) {
                    System.err.println("Lỗi khi thêm học sinh ID " + hoSo.getIdHS() + ": " + e.getMessage());
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, "Lỗi khi tải danh sách học sinh: " + e.getMessage(), 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Cập nhật phương thức getEligibleStudents để chỉ lấy hồ sơ chưa xử lý
    private List<HoSoDangKy> getEligibleStudents() throws SQLException {
        List<HoSoDangKy> eligibleStudents = new ArrayList<>();
        
        String sql = "SELECT h.* FROM hosodangky h " +
                     "JOIN nguyenvong n ON h.idHoSo = n.idHoSo " +
                     "WHERE n.phuongThucXetTuyen = 'Tuyển Thẳng' " +
                     "AND n.trangThai = 'CHO_XET_TUYEN'";
//        String sql = "SELECT h.* FROM hosodangky h " +
//                "JOIN nguyenvong n ON h.idHoSo = n.idHoSo " +
//                "WHERE n.phuongThucXetTuyen = 'TUYEN_THANG' " +
//                "AND h.trangThai = 'CHO_XET_TUYEN'";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                HoSoDangKy hoSo = new HoSoDangKy();
                hoSo.setIdHoSo(rs.getInt("idHoSo"));
                hoSo.setIdHS(rs.getInt("idHS"));
                hoSo.setTrangThai(rs.getString("trangThai"));
                hoSo.setDiemThi(rs.getFloat("diemThi"));
                hoSo.setDiemHocBa(rs.getFloat("diemHocBa"));
                eligibleStudents.add(hoSo);
            }
        }
        
        return eligibleStudents;
    }

    // Thêm phương thức refresh lại dữ liệu
    public void refreshData() {
        loadEligibleStudents();
    }
    
//    private void loadEligibleStudents() {
//        try {
//            List<hoSoDangKy> eligibleStudents = getEligibleStudents();
//            
//            DefaultTableModel model = (DefaultTableModel) view.getTable().getModel();
//            model.setRowCount(0);
//            
//            for (hoSoDangKy hoSo : eligibleStudents) {
//                try {
//                    // Get student info
//                    hocSinh hs = hocSinhDAO.getHocSinhById(hoSo.getIdHS());
//                    String tenHS = hs != null ? hs.getHoVaTen() : "Không xác định";
//                    
//                    // Get first choice school info
//                    List<nguyenVong> nguyenVongs = nguyenVongDAO.getAllByHoSoId(hoSo.getIdHoSo());
//                    String tenTruong = "";
//                    
//                    if (!nguyenVongs.isEmpty()) {
//                        nguyenVong nv = nguyenVongs.get(0);
//                        truong tr = truongDAO.getTruongById(nv.getIdTruong());
//                        if (tr != null) {
//                            tenTruong = tr.getTenTruong();
//                        }
//                    }
//                    
//                    model.addRow(new Object[]{
//                        hoSo.getIdHoSo(),
//                        tenHS,
//                        tenTruong,
//                    });
//                } catch (Exception e) {
//                    System.err.println("Lỗi khi thêm học sinh ID " + hoSo.getIdHS() + ": " + e.getMessage());
//                    // Continue with next student
//                }
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(view, "Lỗi khi tải danh sách học sinh: " + e.getMessage(), 
//                "Lỗi", JOptionPane.ERROR_MESSAGE);
//        }
//    }
//    
//    private List<hoSoDangKy> getEligibleStudents() throws SQLException {
//        List<hoSoDangKy> eligibleStudents = new ArrayList<>();
//        
//        String sql = "SELECT h.* FROM hosodangky h " +
//                     "JOIN nguyenvong n ON h.idHoSo = n.idHoSo " +
//                     "WHERE n.phuongThucXetTuyen = 'TUYEN_THANG' " +
//                     "AND h.trangThai = 'CHO_XET_TUYEN'";
//        
//        try (Connection conn = DatabaseConnection.getConnection();
//             PreparedStatement ps = conn.prepareStatement(sql);
//             ResultSet rs = ps.executeQuery()) {
//            
//            while (rs.next()) {
//                hoSoDangKy hoSo = new hoSoDangKy();
//                hoSo.setIdHoSo(rs.getInt("idHoSo"));
//                hoSo.setIdHS(rs.getInt("idHS"));
//                hoSo.setTrangThai(rs.getString("trangThai"));
//                hoSo.setDiemThi(rs.getFloat("diemThi"));
//                hoSo.setDiemHocBa(rs.getFloat("diemHocBa"));
//                eligibleStudents.add(hoSo);
//            }
//        }
//        
//        return eligibleStudents;
//    }
    private void openDuyetTuyenThang(int idHoSo) throws SQLException {
        // Lấy thông tin hồ sơ đăng ký
    	int idHs = hocSinhDAO.getIDHSFromHoSo(idHoSo);
        HoSoDangKy hoSo = hoSoDAO.getHoSoByStudentId(idHs);
        if (hoSo == null) {
            throw new SQLException("Không tìm thấy hồ sơ với ID: lỗi hồ sơ null" + idHoSo);
        }
        
        // Lấy thông tin học sinh từ idHS
        
       // System.out.println("id học sinh là: " + idHs);
        HocSinh hs = hocSinhDAO.getHocSinhById(idHs);
        if (hs == null) {
            throw new SQLException("Không tìm thấy thông tin học sinh với ID: lỗi đây nữa hả" + idHs);
        }
        
        // Lấy danh sách nguyện vọng của học sinh
        List<NguyenVong> nguyenVongs = nguyenVongDAO.getAllByHoSoId(idHoSo);
        if (nguyenVongs.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Hồ sơ không có nguyện vọng nào", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Lấy nguyện vọng đầu tiên (vì đây là xét tuyển thẳng)
        NguyenVong nv = nguyenVongs.get(0);
        
        // Lấy thông tin trường và ngành từ nguyện vọng
        Truong truong = truongDAO.getTruongById(nv.getIdTruong());
        
     // Lấy thông tin ngành từ nguyện vọng
        NganhDAO nganhDAO = new NganhDAO();  // Khởi tạo đối tượng
        Nganh nganh = nganhDAO.getNganhById(nv.getIdNganh());

        // Lấy danh sách chứng chỉ của học sinh
        ChungChiDAO chungChiDAO = new ChungChiDAO();  // Khởi tạo đối tượng
   //     chungChi chungChi = chungChiDAO.getChungChiByHS(idHoSo);
        List<ChungChi> listCc = chungChiDAO.getListChungChiByiDHS(idHs);
        // Tạo và hiển thị form duyệt tuyển thẳng
        DuyetTuyenThangView duyetView = new DuyetTuyenThangView();
        new DuyetTuyenThangController(duyetView, hoSo, hs, nv, truong, nganh, listCc);
        duyetView.setVisible(true);
        
    }
}