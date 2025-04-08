package dao;

import model.DiemHocBa;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DiemHocBaDAO {
    private static final Logger LOGGER = Logger.getLogger(DiemHocBaDAO.class.getName());
    
    // SQL queries
    private static final String INSERT_SQL = """
        INSERT INTO DiemHocBa (idHS, NamHoc, Toan, Ly, Hoa, Van, Su, Dia, GDCD, Anh, Sinh)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)""";
        
    private static final String UPDATE_SQL = """
        UPDATE DiemHocBa 
        SET Toan = ?, Ly = ?, Hoa = ?, Van = ?, Su = ?, Dia = ?, GDCD = ?, Anh = ?, Sinh = ?
        WHERE idDiemHocBa = ?""";
        
    private static final String GET_BY_HS_SQL = """
        SELECT * FROM DiemHocBa WHERE idHS = ?""";
        
    private static final String GET_BY_HS_NAMHOC_SQL = """
        SELECT * FROM DiemHocBa WHERE idHS = ? AND NamHoc = ?""";
        
    private static final String DELETE_SQL = """
        DELETE FROM DiemHocBa WHERE idDiemHocBa = ?""";

    public boolean insertDiemHocBa(DiemHocBa diem) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            
            setInsertParameters(ps, diem);
            int rows = ps.executeUpdate();
            
            if (rows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        diem.setidDiemHocBa(rs.getInt(1));
                    }
                }
              //  LOGGER.log(Level.INFO, "Thêm điểm học bạ thành công cho học sinh ID: {0}", diem.getIdHS());
                return true;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi thêm điểm học bạ: " + e.getMessage(), e);
        }
        return false;
    }

    public boolean updateDiemHocBa(DiemHocBa diem) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_SQL)) {
            
            setUpdateParameters(ps, diem);
            int rows = ps.executeUpdate();
            
            if (rows > 0) {
            //    LOGGER.log(Level.INFO, "Cập nhật điểm học bạ thành công ID: {0}", diem.getidDiemHocBa());
                return true;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi cập nhật điểm học bạ: " + e.getMessage(), e);
        }
        return false;
    }

    public List<DiemHocBa> getDiemHocBaByHS(int idHS) {
        List<DiemHocBa> list = new ArrayList<>();
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_BY_HS_SQL)) {
            
            ps.setInt(1, idHS);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    list.add(mapResultSetToDiemHocBa(rs));
                }
            }
            LOGGER.log(Level.INFO, "Lấy {0} bản ghi điểm học bạ cho học sinh ID: {1}", 
                    new Object[]{list.size(), idHS});
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy điểm học bạ: " + e.getMessage(), e);
        }
        return list;
    }

    public DiemHocBa getDiemHocBaByHSAndNamHoc(int idHS, String namHoc) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(GET_BY_HS_NAMHOC_SQL)) {
            
            ps.setInt(1, idHS);
            ps.setString(2, namHoc);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToDiemHocBa(rs);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi lấy điểm học bạ: " + e.getMessage(), e);
        }
        LOGGER.log(Level.WARNING, "Không tìm thấy điểm học bạ cho học sinh ID: {0}, năm học: {1}", 
                new Object[]{idHS, namHoc});
        return null;
    }

    public boolean deleteDiemHocBa(int idDiem) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
            
            ps.setInt(1, idDiem);
            int rows = ps.executeUpdate();
            
            if (rows > 0) {
                LOGGER.log(Level.INFO, "Xóa điểm học bạ thành công ID: {0}", idDiem);
                return true;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Lỗi khi xóa điểm học bạ: " + e.getMessage(), e);
        }
        return false;
    }

    private void setInsertParameters(PreparedStatement ps, DiemHocBa diem) throws SQLException {
        ps.setInt(1, diem.getIdHS());
        ps.setString(2, diem.getNamHoc());
        ps.setFloat(3, diem.getToan());
        ps.setFloat(4, diem.getLy());
        ps.setFloat(5, diem.getHoa());
        ps.setFloat(6, diem.getVan());
        ps.setFloat(7, diem.getSu());
        ps.setFloat(8, diem.getDia());
        ps.setFloat(9, diem.getGdcd());
        ps.setFloat(10, diem.getAnh());
        ps.setFloat(11, diem.getSinh());
    }

    private void setUpdateParameters(PreparedStatement ps, DiemHocBa diem) throws SQLException {
        ps.setFloat(1, diem.getToan());
        ps.setFloat(2, diem.getLy());
        ps.setFloat(3, diem.getHoa());
        ps.setFloat(4, diem.getVan());
        ps.setFloat(5, diem.getSu());
        ps.setFloat(6, diem.getDia());
        ps.setFloat(7, diem.getGdcd());
        ps.setFloat(8, diem.getAnh());
        ps.setFloat(9, diem.getSinh());
        ps.setInt(10, diem.getidDiemHocBa());
    }

    private DiemHocBa mapResultSetToDiemHocBa(ResultSet rs) throws SQLException {
        DiemHocBa diem = new DiemHocBa();
        diem.setidDiemHocBa(rs.getInt("idDiemHocBa"));
        diem.setIdHS(rs.getInt("idHS"));
        diem.setNamHoc(rs.getString("NamHoc"));
        diem.setToan(rs.getFloat("Toan"));
        diem.setLy(rs.getFloat("Ly"));
        diem.setHoa(rs.getFloat("Hoa"));
        diem.setVan(rs.getFloat("Van"));
        diem.setSu(rs.getFloat("Su"));
        diem.setDia(rs.getFloat("Dia"));
        diem.setGdcd(rs.getFloat("GDCD"));
        diem.setAnh(rs.getFloat("Anh"));
        diem.setSinh(rs.getFloat("Sinh"));
        return diem;
    }
}
