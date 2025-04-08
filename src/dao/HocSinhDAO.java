package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.HocSinh;

public class HocSinhDAO {
    private static final Logger LOGGER = Logger.getLogger(HocSinhDAO.class.getName());

    // SQL queries (giữ nguyên)
    private static final String INSERT_SQL = """
            INSERT INTO hocSinh (cccd, idTaiKhoan, hoTen, ngaySinh, gioiTinh, danToc, diaChi)
            VALUES (?, ?, ?, ?, ?, ?, ?)""";

    private static final String UPDATE_SQL = """
            UPDATE hocSinh
            SET hoTen = ?, ngaySinh = ?, gioiTinh = ?, danToc = ?, diaChi = ?, cccd = ?
            WHERE idTaiKhoan = (SELECT id FROM Account WHERE taiKhoan = ?)""";

    private static final String CHECK_EXISTS_SQL = """
            SELECT COUNT(*) FROM hocSinh hs
            JOIN account acc ON hs.idTaiKhoan = acc.id
            WHERE acc.taiKhoan = ?""";

    private static final String GET_BY_TAIKHOAN_SQL = """
            SELECT hs.* FROM hocSinh hs
            JOIN account acc ON hs.idTaiKhoan = acc.id
            WHERE acc.taiKhoan = ?""";
    private static final String GET_BY_ID_SQL = "SELECT * FROM hocSinh WHERE idHS = ?";
    private static final String GET_BY_CCCD_SQL = "SELECT * FROM hocSinh WHERE cccd = ?";
    private static final String UPDATE_BY_CCCD_SQL = """
            UPDATE hocSinh
            SET hoTen = ?, ngaySinh = ?, gioiTinh = ?, danToc = ?, diaChi = ?
            WHERE cccd = ?""";

    /**
     * Thêm mới học sinh vào database
     */
    public boolean insertHocSinh(HocSinh hs, int idTaiKhoan) {
        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {

            setInsertParameters(ps, hs, idTaiKhoan);
            int rows = ps.executeUpdate();

            if (rows > 0) {
          //      LOGGER.log(Level.INFO, "Thêm học sinh thành công: {0}", hs.getCCCD());
                return true;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi thêm học sinh: " + e.getMessage(), e);
            if (e.getMessage().contains("Duplicate entry")) {
                LOGGER.log(Level.WARNING, "CCCD hoặc tài khoản đã tồn tại");
            }
        }
        return false;
    }

    /**
     * Cập nhật thông tin học sinh
     */
    public boolean updateHocSinh(HocSinh hs, String taiKhoan) {
        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {

            setUpdateParameters(ps, hs, taiKhoan);
            int rows = ps.executeUpdate();

            if (rows > 0) {
             //   LOGGER.log(Level.INFO, "Cập nhật học sinh thành công: {0}", taiKhoan);
                return true;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi cập nhật học sinh: " + e.getMessage(), e);
        }
        return false;
    }

    /**
     * Kiểm tra học sinh đã tồn tại chưa
     */
    public boolean checkHocSinhExists(String taiKhoan) {
        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(CHECK_EXISTS_SQL)) {

            ps.setString(1, taiKhoan);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    boolean exists = rs.getInt(1) > 0;
            //        LOGGER.log(Level.INFO, "Kiểm tra tồn tại học sinh {0}: {1}", new Object[] { taiKhoan, exists });
                    return exists;
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi kiểm tra học sinh: " + e.getMessage(), e);
        }
        return false;
    }

    /**
     * Lấy thông tin học sinh bằng tài khoản
     */
    public HocSinh getHocSinhByTaiKhoan(String taiKhoan) {
        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(GET_BY_TAIKHOAN_SQL)) {

            ps.setString(1, taiKhoan);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    HocSinh hs = mapResultSetToHocSinh(rs);
                    return hs;
                }
            }
        } catch (SQLException e) {
   //         LOGGER.log(Level.SEVERE, "Lỗi khi lấy học sinh: " + e.getMessage(), e);
        }
  //      LOGGER.log(Level.WARNING, "Không tìm thấy học sinh: {0}", taiKhoan);
        return null;
    }

    // Helper methods (giữ nguyên)
    private void setInsertParameters(PreparedStatement ps, HocSinh hs, int idTaiKhoan) throws SQLException {
        ps.setString(1, hs.getCCCD());
        ps.setInt(2, idTaiKhoan);
        ps.setString(3, hs.getHoVaTen());
        ps.setDate(4, hs.getNgaySinh());
        ps.setBoolean(5, hs.isGioiTinh());
        ps.setString(6, hs.getDanToc());
        ps.setString(7, hs.getDiaChi());
    }

    private void setUpdateParameters(PreparedStatement ps, HocSinh hs, String taiKhoan) throws SQLException {
        ps.setString(1, hs.getHoVaTen());
        ps.setDate(2, hs.getNgaySinh());
        ps.setBoolean(3, hs.isGioiTinh());
        ps.setString(4, hs.getDanToc());
        ps.setString(5, hs.getDiaChi());
        ps.setString(6, hs.getCCCD());
        ps.setString(7, taiKhoan);
    }

    private HocSinh mapResultSetToHocSinh(ResultSet rs) throws SQLException {
        HocSinh hs = new HocSinh(rs.getString("cccd"), rs.getString("hoTen"), rs.getDate("ngaySinh"),
                rs.getBoolean("gioiTinh"), rs.getString("danToc"), rs.getString("diaChi"));
        hs.setIdHS(rs.getInt("idHS"));
        hs.setIdTaiKhoan(rs.getInt("idTaiKhoan"));
        return hs;
    }

    public int getIdHSByIdTaiKhoan(int idTaiKhoan) {
        String sql = "SELECT idHS FROM hocSinh WHERE idTaiKhoan = ?";
        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idTaiKhoan);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("idHS");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Lấy danh sách tất cả học sinh
     */
    public List<HocSinh> getAllHocSinh() {
        List<HocSinh> list = new ArrayList<>();
        String sql = "SELECT * FROM hocSinh";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                HocSinh hs = mapResultSetToHocSinh(rs);
                list.add(hs);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy danh sách học sinh: " + e.getMessage(), e);
        }
        return list;
    }

    /**
     * Tìm kiếm học sinh theo tên (tương đối)
     */
    public List<HocSinh> searchHocSinhByName(String name) {
        List<HocSinh> list = new ArrayList<>();
        String sql = "SELECT * FROM hocSinh WHERE hoTen LIKE ?";

        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, "%" + name + "%");

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    HocSinh hs = mapResultSetToHocSinh(rs);
                    list.add(hs);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi tìm kiếm học sinh: " + e.getMessage(), e);
        }
        return list;
    }

    /**
     * Xóa học sinh theo ID
     */
    public boolean deleteHocSinh(int idHS) {
        String sql = "DELETE FROM hocSinh WHERE idHS = ?";

        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idHS);
            int rows = ps.executeUpdate();

            if (rows > 0) {
            //    LOGGER.log(Level.INFO, "Xóa học sinh thành công: {0}", idHS);
                return true;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi xóa học sinh: " + e.getMessage(), e);
        }
        return false;
    }

    /**
     * Lấy học sinh bằng CCCD
     */
    public HocSinh getHocSinhByCCCD(String cccd) {
        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(GET_BY_CCCD_SQL)) {

            ps.setString(1, cccd);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToHocSinh(rs);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy học sinh bằng CCCD: " + e.getMessage(), e);
        }
        return null;
    }

    /**
     * Cập nhật học sinh bằng CCCD
     */
    public boolean updateHocSinhByCCCD(HocSinh hs) {
        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(UPDATE_BY_CCCD_SQL)) {

            ps.setString(1, hs.getHoVaTen());
            ps.setDate(2, hs.getNgaySinh());
            ps.setBoolean(3, hs.isGioiTinh());
            ps.setString(4, hs.getDanToc());
            ps.setString(5, hs.getDiaChi());
            ps.setString(6, hs.getCCCD());

            int rows = ps.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi cập nhật học sinh bằng CCCD: " + e.getMessage(), e);
        }
        return false;
    }

    /**
     * Lấy idHS từ số CCCD
     */
    public int getIdHSByCCCD(String cccd) {
        String sql = "SELECT idHS FROM hocSinh WHERE cccd = ?";

        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cccd);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int idHS = rs.getInt("idHS");
                    return idHS;
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy idHS từ CCCD: " + e.getMessage(), e);
        }

        LOGGER.log(Level.WARNING, "Không tìm thấy idHS cho CCCD: {0}", cccd);
        return -1;
    }

    /**
     * Lấy thông tin học sinh bằng ID
     */
    public HocSinh getHocSinhById(int idHS) {
        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement ps = conn.prepareStatement(GET_BY_ID_SQL)) {
            ps.setInt(1, idHS);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToHocSinh(rs);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy học sinh bằng ID: " + e.getMessage(), e);
        }
        return null;
    }
    /**
     * Lấy idHS từ idHoSo trong bảng hosodangky
     * @param idHoSo ID của hồ sơ đăng ký
     * @return idHS nếu tìm thấy, -1 nếu không tìm thấy hoặc có lỗi
     */
    public int getIDHSFromHoSo(int idHoSo) {
        String sql = "SELECT idHS FROM hosodangky WHERE idHoSo = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idHoSo);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("idHS");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Lỗi khi lấy idHS từ idHoSo: " + e.getMessage());
        }
        
        return -1; // Trả về -1 nếu không tìm thấy hoặc có lỗi
    }
    /**
     * Lấy tên học sinh bằng id tài khoản
     * @param idTaiKhoan ID của tài khoản (có thể null)
     * @return Tên học sinh nếu tìm thấy và không rỗng, null nếu không tìm thấy, idTaiKhoan null hoặc tên rỗng
     */
    public String getHoTenByIdTaiKhoan(Integer idTaiKhoan) {
        // Kiểm tra idTaiKhoan null
        if (idTaiKhoan == null) {
            LOGGER.log(Level.INFO, "ID tài khoản null, trả về null");
            return null;
        }

        String sql = "SELECT hoTen FROM hocSinh WHERE idTaiKhoan = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, idTaiKhoan);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String hoTen = rs.getString("hoTen");
                    
                    // Kiểm tra nếu tên null hoặc rỗng
                    if (hoTen == null || hoTen.trim().isEmpty()) {
                     //   LOGGER.log(Level.INFO, "Tìm thấy học sinh nhưng tên rỗng hoặc null với idTaiKhoan: {0}", idTaiKhoan);
                        return null;
                    }
                    
                //    LOGGER.log(Level.INFO, "Tìm thấy học sinh với idTaiKhoan {0}: {1}", 
                //        new Object[]{idTaiKhoan, hoTen});
                    return hoTen;
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy tên học sinh bằng id tài khoản: " + e.getMessage(), e);
        }
        
     //   LOGGER.log(Level.INFO, "Không tìm thấy học sinh với idTaiKhoan: {0}", idTaiKhoan);
        return null;
    }
}