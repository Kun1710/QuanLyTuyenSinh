package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import dao.DatabaseConnection;
import model.NguyenVong;

public class NguyenVongDAO {
	
    /**
     * Thêm một nguyện vọng mới vào cơ sở dữ liệu
     * @param nv Đối tượng nguyenVong chứa thông tin nguyện vọng cần thêm
     * @return true nếu thêm thành công, false nếu thất bại
     */
    public boolean createNguyenVong(NguyenVong nv) {
        String sql = "INSERT INTO nguyenvong (idHoSo, thuTuNguyenVong, idTruong, idNganh, phuongThucXetTuyen, trangThai) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, nv.getIdHoSo());
            statement.setInt(2, nv.getThuTuNguyenVong());
            statement.setInt(3, nv.getIdTruong());
            statement.setInt(4, nv.getIdNganh());
            statement.setString(5, nv.getPhuongThucXetTuyen());
            statement.setString(6, "CHO_XET_TUYEN");
            
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Lấy danh sách tất cả nguyện vọng theo ID hồ sơ
     * @param idHoSo ID của hồ sơ cần lấy nguyện vọng
     * @return Danh sách các nguyện vọng của hồ sơ, sắp xếp theo thứ tự nguyện vọng
     */
    public List<NguyenVong> getAllByHoSoId(int idHoSo) {
        List<NguyenVong> list = new ArrayList<>();
        String sql = "SELECT * FROM nguyenvong WHERE idHoSo = ? ORDER BY thuTuNguyenVong";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idHoSo);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    NguyenVong nv = new NguyenVong();
                    nv.setIdNguyenVong(resultSet.getInt("idNguyenVong"));
                    nv.setIdHoSo(resultSet.getInt("idHoSo"));
                    nv.setThuTuNguyenVong(resultSet.getInt("thuTuNguyenVong"));
                    nv.setIdTruong(resultSet.getInt("idTruong"));
                    nv.setIdNganh(resultSet.getInt("idNganh"));
                    nv.setPhuongThucXetTuyen(resultSet.getString("phuongThucXetTuyen"));
                    nv.setTrangThai(resultSet.getString("trangThai"));
                    
                    list.add(nv);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return list;
    }
    
    /**
     * Lấy thứ tự nguyện vọng tiếp theo cho một hồ sơ
     * @param idHoSo ID của hồ sơ cần kiểm tra
     * @return Thứ tự nguyện vọng tiếp theo (số nguyên)
     */
    public int getNextThuTuNguyenVong(int idHoSo) {
        String sql = "SELECT MAX(thuTuNguyenVong) FROM nguyenvong WHERE idHoSo = ?";
        int nextOrder = 1;
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idHoSo);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    nextOrder = resultSet.getInt(1) + 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return nextOrder;
    }
    
    /**
     * Hoán đổi thứ tự của hai nguyện vọng
     * @param idHoSo ID hồ sơ chứa các nguyện vọng
     * @param firstOrder Thứ tự nguyện vọng thứ nhất
     * @param secondOrder Thứ tự nguyện vọng thứ hai
     * @return true nếu hoán đổi thành công, false nếu thất bại
     */
    public boolean swapNguyenVongOrder(int idHoSo, int firstOrder, int secondOrder) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false);
            
            // Temporarily set first order to 0 to avoid unique constraint violation
            String tempUpdate = "UPDATE nguyenvong SET thuTuNguyenVong = 0 " +
                               "WHERE idHoSo = ? AND thuTuNguyenVong = ?";
            
            try (PreparedStatement statement = connection.prepareStatement(tempUpdate)) {
                statement.setInt(1, idHoSo);
                statement.setInt(2, firstOrder);
                statement.executeUpdate();
            }
            
            // Update second order to first order
            String updateFirst = "UPDATE nguyenvong SET thuTuNguyenVong = ? " +
                                "WHERE idHoSo = ? AND thuTuNguyenVong = ?";
            
            try (PreparedStatement statement = connection.prepareStatement(updateFirst)) {
                statement.setInt(1, firstOrder);
                statement.setInt(2, idHoSo);
                statement.setInt(3, secondOrder);
                statement.executeUpdate();
            }
            
            // Update first order (now 0) to second order
            String updateSecond = "UPDATE nguyenvong SET thuTuNguyenVong = ? " +
                                 "WHERE idHoSo = ? AND thuTuNguyenVong = 0";
            
            try (PreparedStatement statement = connection.prepareStatement(updateSecond)) {
                statement.setInt(1, secondOrder);
                statement.setInt(2, idHoSo);
                statement.executeUpdate();
            }
            
            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Xóa một nguyện vọng và cập nhật lại thứ tự các nguyện vọng còn lại
     * @param idHoSo ID hồ sơ chứa nguyện vọng cần xóa
     * @param thuTuNguyenVong Thứ tự nguyện vọng cần xóa
     * @return true nếu xóa thành công, false nếu thất bại
     */
    public boolean deleteNguyenVong(int idHoSo, int thuTuNguyenVong) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false);
            
            // First delete the nguyen vong
            String deleteSql = "DELETE FROM nguyenvong WHERE idHoSo = ? AND thuTuNguyenVong = ?";
            
            try (PreparedStatement statement = connection.prepareStatement(deleteSql)) {
                statement.setInt(1, idHoSo);
                statement.setInt(2, thuTuNguyenVong);
                int affectedRows = statement.executeUpdate();
                
                if (affectedRows == 0) {
                    connection.rollback();
                    return false;
                }
            }
            
            // Then update the orders of remaining nguyen vong
            String updateSql = "UPDATE nguyenvong SET thuTuNguyenVong = thuTuNguyenVong - 1 " +
                               "WHERE idHoSo = ? AND thuTuNguyenVong > ?";
            
            try (PreparedStatement statement = connection.prepareStatement(updateSql)) {
                statement.setInt(1, idHoSo);
                statement.setInt(2, thuTuNguyenVong);
                statement.executeUpdate();
            }
            
            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * Cập nhật trạng thái của một nguyện vọng
     * @param nv Đối tượng nguyenVong chứa thông tin cần cập nhật (chủ yếu là trạng thái)
     * @return true nếu cập nhật thành công, false nếu thất bại
     */
    public boolean updateNguyenVong(NguyenVong nv) {
        String sql = "UPDATE nguyenvong SET trangThai = ? WHERE idNguyenVong = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nv.getTrangThai());
            statement.setInt(2, nv.getIdNguyenVong());
            
            int affectedRows = statement.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public List<NguyenVong> getAllNguyenVongTheoPhuongThuc(String phuongThuc) throws SQLException {
        List<NguyenVong> list = new ArrayList<>();
        String sql = "SELECT * FROM nguyenvong WHERE phuongThucXetTuyen = ? AND trangThai = 'CHO_XET_TUYEN'";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, phuongThuc);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    NguyenVong nv = new NguyenVong();
                    nv.setIdNguyenVong(resultSet.getInt("idNguyenVong"));
                    nv.setIdHoSo(resultSet.getInt("idHoSo"));
                    nv.setThuTuNguyenVong(resultSet.getInt("thuTuNguyenVong"));
                    nv.setIdTruong(resultSet.getInt("idTruong"));
                    nv.setIdNganh(resultSet.getInt("idNganh"));
                    nv.setPhuongThucXetTuyen(resultSet.getString("phuongThucXetTuyen"));
                    nv.setTrangThai(resultSet.getString("trangThai"));
                    list.add(nv);
                }
            }
        }
        return list;
    }

    public List<NguyenVong> getAllByNganhAndPhuongThuc(int idNganh, String phuongThuc) throws SQLException {
        List<NguyenVong> list = new ArrayList<>();
        String sql = "SELECT * FROM nguyenvong WHERE idNganh = ? AND phuongThucXetTuyen = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idNganh);
            statement.setString(2, phuongThuc);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    NguyenVong nv = new NguyenVong();
                    nv.setIdNguyenVong(resultSet.getInt("idNguyenVong"));
                    nv.setIdHoSo(resultSet.getInt("idHoSo"));
                    nv.setThuTuNguyenVong(resultSet.getInt("thuTuNguyenVong"));
                    nv.setIdTruong(resultSet.getInt("idTruong"));
                    nv.setIdNganh(resultSet.getInt("idNganh"));
                    nv.setPhuongThucXetTuyen(resultSet.getString("phuongThucXetTuyen"));
                    nv.setTrangThai(resultSet.getString("trangThai"));
                    list.add(nv);
                }
            }
        }
        return list;
    }
    /**
     * Lấy danh sách nguyện vọng trúng tuyển của một ngành
     * @param idNganh ID của ngành
     * @return Danh sách nguyện vọng trúng tuyển
     * @throws SQLException
     */
    public List<NguyenVong> getNguyenVongTrungTuyenByNganh(int idNganh) throws SQLException {
        List<NguyenVong> list = new ArrayList<>();
        String sql = "SELECT * FROM nguyenvong WHERE idNganh = ? AND trangThai = 'TRUNG_TUYEN' ORDER BY phuongThucXetTuyen";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idNganh);
            
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    NguyenVong nv = new NguyenVong();
                    nv.setIdNguyenVong(resultSet.getInt("idNguyenVong"));
                    nv.setIdHoSo(resultSet.getInt("idHoSo"));
                    nv.setThuTuNguyenVong(resultSet.getInt("thuTuNguyenVong"));
                    nv.setIdTruong(resultSet.getInt("idTruong"));
                    nv.setIdNganh(resultSet.getInt("idNganh"));
                    nv.setPhuongThucXetTuyen(resultSet.getString("phuongThucXetTuyen"));
                    nv.setTrangThai(resultSet.getString("trangThai"));
                    list.add(nv);
                }
            }
        }
        return list;
    }
}