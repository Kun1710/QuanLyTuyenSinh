package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import java.sql.Date;
import model.ChungChi;
//import dao.DatabaseConnection;

public class ChungChiDAO {

    public ChungChiDAO() {
        // Constructor không cần khởi tạo connection nữa
    }

    /**
     * Thêm một chứng chỉ mới vào cơ sở dữ liệu
     * @param cc Đối tượng chungChi chứa thông tin chứng chỉ cần thêm
     * @return true nếu thêm thành công, false nếu thất bại
     */
    public boolean addChungChi(ChungChi cc) {
        String sql = "INSERT INTO chungchi (idHS, tenChungChi, NgayCap, linkChungChi) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            
            ps.setInt(1, cc.getIdHS());
            ps.setString(2, cc.getTenChungChi());
            ps.setDate(3, cc.getNgayCap());
            ps.setString(4, cc.getLinkChungChi());
            
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Cập nhật thông tin chứng chỉ
     * @param cc Đối tượng chungChi chứa thông tin cần cập nhật
     * @return true nếu cập nhật thành công, false nếu thất bại
     */
    public boolean updateChungChi(ChungChi cc) {
        String sql = "UPDATE chungchi SET tenChungChi = ?, NgayCap = ?, linkChungChi = ? WHERE idChungChi = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            
            ps.setString(1, cc.getTenChungChi());
            ps.setDate(2, cc.getNgayCap());
            ps.setString(3, cc.getLinkChungChi());
            ps.setInt(4, cc.getIdChungChi());
            
            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Lấy thông tin chứng chỉ theo ID học sinh
     * @param idHS ID của học sinh cần lấy chứng chỉ
     * @return Đối tượng chungChi nếu tìm thấy, null nếu không tìm thấy
     */
    public ChungChi getChungChiByHS(int idHS) {
        String sql = "SELECT * FROM chungchi WHERE idHS = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            
            ps.setInt(1, idHS);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ChungChi cc = new ChungChi();
                    cc.setIdChungChi(rs.getInt("idChungChi"));
                    cc.setIdHS(rs.getInt("idHS"));
                    cc.setTenChungChi(rs.getString("tenChungChi"));
                    cc.setNgayCap(rs.getDate("NgayCap"));
                    cc.setLinkChungChi(rs.getString("linkChungChi"));
                    return cc;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * Lấy danh sách chứng chỉ của học sinh dựa vào idHS
     * @param idHS ID của học sinh
     * @return Danh sách chứng chỉ (List<chungChi>), trả về danh sách rỗng nếu không có hoặc lỗi
     */
    public List<ChungChi> getListChungChiByiDHS(int idHS) {
        List<ChungChi> dsChungChi = new ArrayList<>();
        String sql = "SELECT * FROM chungchi WHERE idHS = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idHS);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ChungChi cc = new ChungChi();
                    cc.setIdChungChi(rs.getInt("idChungChi"));
                    cc.setIdHS(rs.getInt("idHS"));
                    cc.setTenChungChi(rs.getString("tenChungChi"));
                    cc.setNgayCap(rs.getDate("NgayCap"));
                    cc.setLinkChungChi(rs.getString("linkChungChi"));
                    dsChungChi.add(cc);
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy danh sách chứng chỉ: " + e.getMessage());
            e.printStackTrace();
        }

        return dsChungChi;
    }
    public boolean deleteChungChi(int idChungChi) {
        // Thêm phương thức xóa chứng chỉ vào DAO nếu chưa có
        String sql = "DELETE FROM chungchi WHERE idChungChi = ?";
        
        try (java.sql.Connection connection = dao.DatabaseConnection.getConnection();
             java.sql.PreparedStatement ps = connection.prepareStatement(sql)) {
            
            ps.setInt(1, idChungChi);
            int result = ps.executeUpdate();
            return result > 0;
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}