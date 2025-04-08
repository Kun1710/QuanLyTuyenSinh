package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import dao.DatabaseConnection;
import model.HoSoDangKy;

public class HoSoDangKyDAO {
    
    public HoSoDangKyDAO() {}
    
    public HoSoDangKy getHoSoByStudentId(int idHS) {
        HoSoDangKy hoSo = null;
        String sql = "SELECT * FROM hosodangky WHERE idHS = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, idHS);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    hoSo = new HoSoDangKy();
                    hoSo.setIdHoSo(resultSet.getInt("idHoSo"));
                    hoSo.setIdHS(resultSet.getInt("idHS"));
                    hoSo.setTrangThai(resultSet.getString("trangThai"));
                    hoSo.setDiemThi(resultSet.getFloat("diemThi"));
                    hoSo.setDiemHocBa(resultSet.getFloat("diemHocBa"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return hoSo;
    }
    public HoSoDangKy getHoSoByIdHS(int idHoSo) {
        HoSoDangKy hoSo = null;
        String sql = "SELECT * FROM hosodangky WHERE idHoSo = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            statement.setInt(1, idHoSo);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    hoSo = new HoSoDangKy();
                    hoSo.setIdHoSo(resultSet.getInt("idHoSo"));
                    hoSo.setIdHS(resultSet.getInt("idHS"));
                    hoSo.setTrangThai(resultSet.getString("trangThai"));
                    hoSo.setDiemThi(resultSet.getFloat("diemThi"));
                    hoSo.setDiemHocBa(resultSet.getFloat("diemHocBa"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return hoSo;
    }
    
    
    public int createHoSo(HoSoDangKy hoSo) {
        String sql = "INSERT INTO hosodangky (idHS, trangThai, diemThi, diemHocBa) VALUES (?, ?, ?, ?)";
        int generatedId = -1;
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            statement.setInt(1, hoSo.getIdHS());
            statement.setString(2, hoSo.getTrangThai());
            statement.setFloat(3, hoSo.getDiemThi());
            statement.setFloat(4, hoSo.getDiemHocBa());
            
            int affectedRows = statement.executeUpdate();
            
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getInt(1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return generatedId;
    }
    
    public boolean updateHoSo(HoSoDangKy hoSo) {
        String sql = "UPDATE hosodangky SET "
                   + "trangThai = ?, "
                   + "diemThi = ?, "
                   + "diemHocBa = ? "
                   + "WHERE idHoSo = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, hoSo.getTrangThai());
            ps.setFloat(2, hoSo.getDiemThi());
            ps.setFloat(3, hoSo.getDiemHocBa());
            ps.setInt(4, hoSo.getIdHoSo());
            
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Lỗi khi cập nhật hồ sơ: " + e.getMessage());
            return false;
        }
    }
    public List<HoSoDangKy> getAllHoSo() throws SQLException {
        List<HoSoDangKy> dsHoSo = new ArrayList<>();
        String sql = "SELECT * FROM hosodangky";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    HoSoDangKy hoSo = new HoSoDangKy();
                    hoSo.setIdHoSo(resultSet.getInt("idHoSo"));
                    hoSo.setIdHS(resultSet.getInt("idHS"));
                    hoSo.setTrangThai(resultSet.getString("trangThai"));
                    hoSo.setDiemThi(resultSet.getFloat("diemThi"));
                    hoSo.setDiemHocBa(resultSet.getFloat("diemHocBa"));
                    dsHoSo.add(hoSo);
                }
            }
        }
        return dsHoSo;
    }
}