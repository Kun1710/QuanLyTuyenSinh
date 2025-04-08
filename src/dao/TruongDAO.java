package dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Truong;


public class TruongDAO {


	// Thêm trường mới
	public boolean addTruong(Truong truong) {
		String sql = "INSERT INTO truongdhorcd (idTaiKhoan, maTruong, tenTruong, diaChi, website) VALUES (?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, truong.getIdTaiKhoan());
			ps.setString(2, truong.getMaTruong());
			ps.setString(3, truong.getTenTruong());
			ps.setString(4, truong.getDiaChi());
			ps.setString(5, truong.getWebsite());

			int result = ps.executeUpdate();

			if (result > 0) {
				try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						truong.setIdTruong(generatedKeys.getInt(1));
					}
				}
				return true;
			}
			return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Lấy id trường dựa vào id tài khoản
	public int getIdTruongByIdTaiKhoan(int idTaiKhoan) {
		String sql = "SELECT idTruong FROM truongdhorcd WHERE idTaiKhoan = ?";
		try {
			PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
			ps.setInt(1, idTaiKhoan);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt("idTruong");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	/**
	 * Lấy id tài khoản dựa vào id trường
	 * @param idTruong ID của trường cần tìm
	 * @return id tài khoản nếu tìm thấy, -1 nếu không tìm thấy
	 */
	public int getIdTaiKhoanByIdTruong(int idTruong) {
	    String sql = "SELECT idTaiKhoan FROM truongdhorcd WHERE idTruong = ?";
	    try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
	        ps.setInt(1, idTruong);
	        ResultSet rs = ps.executeQuery();
	        
	        if (rs.next()) {
	            return rs.getInt("idTaiKhoan");
	        }
	    } catch (SQLException e) {
	        System.err.println("Lỗi khi lấy id tài khoản từ id trường: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return -1;
	}
	// Cập nhật thông tin trường
	public boolean updateTruong(Truong truong) {
		String sql = "UPDATE truongdhorcd SET maTruong = ?, tenTruong = ?, diaChi = ?, website = ? WHERE idTruong = ?";
		try {
			PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
			ps.setString(1, truong.getMaTruong());
			ps.setString(2, truong.getTenTruong());
			ps.setString(3, truong.getDiaChi());
			ps.setString(4, truong.getWebsite());
			ps.setInt(5, truong.getIdTruong());

			int result = ps.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Lấy ID trường dựa vào mã trường
	 * 
	 * @param maTruong Mã trường cần tìm
	 * @return ID trường nếu tìm thấy, -1 nếu không tìm thấy
	 */
	public int getIdTruongByMaTruong(String maTruong) {
		String sql = "SELECT idTruong FROM truongdhorcd WHERE maTruong = ?";
		try {
			PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
			ps.setString(1, maTruong);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return rs.getInt("idTruong");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public String getTenTruongById(int idTruong) throws SQLException {
		String sql = "SELECT tenTruong FROM truongdhorcd WHERE idTruong = ?";
		try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
			ps.setInt(1, idTruong);
			ResultSet rs = ps.executeQuery();
			return rs.next() ? rs.getString("tenTruong") : "Không xác định";
		}
	}
	public String getTenTruongByIdTK(int idTK) throws SQLException {
		String sql = "SELECT tenTruong FROM truongdhorcd WHERE idTaiKhoan = ?";
		try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
			ps.setInt(1, idTK);
			ResultSet rs = ps.executeQuery();
			return rs.next() ? rs.getString("tenTruong") : "Không xác định";
		}
	}
	// Lấy thông tin trường theo ID tài khoản
	public Truong getTruongByTaiKhoan(int idTaiKhoan) {
		String sql = "SELECT * FROM truongdhorcd WHERE idTaiKhoan = ?";
		try {
			PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
			ps.setInt(1, idTaiKhoan);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Truong truong = new Truong();
				truong.setIdTruong(rs.getInt("idTruong"));
				truong.setIdTaiKhoan(rs.getInt("idTaiKhoan"));
				truong.setMaTruong(rs.getString("maTruong"));
				truong.setTenTruong(rs.getString("tenTruong"));
				truong.setDiaChi(rs.getString("diaChi"));
				truong.setWebsite(rs.getString("website"));
				return truong;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	// Thêm các phương thức này vào class truongDHorCDDAO

	public List<Truong> searchTruong(String keyword) {
	    List<Truong> dsTruong = new ArrayList<>();
	    String sql = "SELECT * FROM truongdhorcd WHERE maTruong LIKE ? OR tenTruong LIKE ?";
	    
	    try {
	        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
	        ps.setString(1, "%" + keyword + "%");
	        ps.setString(2, "%" + keyword + "%");
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()) {
	            Truong truong = new Truong();
	            truong.setIdTruong(rs.getInt("idTruong"));
	            truong.setIdTaiKhoan(rs.getInt("idTaiKhoan"));
	            truong.setMaTruong(rs.getString("maTruong"));
	            truong.setTenTruong(rs.getString("tenTruong"));
	            truong.setDiaChi(rs.getString("diaChi"));
	            truong.setWebsite(rs.getString("website"));
	            dsTruong.add(truong);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return dsTruong;
	}

	public Truong getTruongById(int idTruong) {
	    String sql = "SELECT * FROM truongdhorcd WHERE idTruong = ?";
	    try {
	        PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
	        ps.setInt(1, idTruong);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            Truong truong = new Truong();
	            truong.setIdTruong(rs.getInt("idTruong"));
	            truong.setIdTaiKhoan(rs.getInt("idTaiKhoan"));
	            truong.setMaTruong(rs.getString("maTruong"));
	            truong.setTenTruong(rs.getString("tenTruong"));
	            truong.setDiaChi(rs.getString("diaChi"));
	            truong.setWebsite(rs.getString("website"));
	            return truong;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return null;
	}
	/**
	 * Lấy danh sách các trường có chứa ít nhất một trong các mã ngành được cung cấp
	 * @param dsMaNganh Danh sách mã ngành cần tìm
	 * @return Danh sách các trường có ngành thuộc dsMaNganh
	 */
	public List<Truong> getTruongByListMaNganh(List<String> dsMaNganh) {
	    List<Truong> dsTruong = new ArrayList<>();
	    
	    if (dsMaNganh == null || dsMaNganh.isEmpty()) {
	        return dsTruong;
	    }
	    
	    // Tạo chuỗi placeholders (?,?,...)
	    String placeholders = String.join(",", Collections.nCopies(dsMaNganh.size(), "?"));
	    
	    String sql = "SELECT DISTINCT t.* FROM truongdhorcd t " +
	                 "JOIN nganh n ON t.idTruong = n.idTruong " +
	                 "WHERE n.maNganh IN (" + placeholders + ")";
	    
	    try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
	        // Thiết lập các tham số cho các placeholders
	        for (int i = 0; i < dsMaNganh.size(); i++) {
	            ps.setString(i + 1, dsMaNganh.get(i));
	        }
	        
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                Truong truong = new Truong();
	                truong.setIdTruong(rs.getInt("idTruong"));
	                truong.setIdTaiKhoan(rs.getInt("idTaiKhoan"));
	                truong.setMaTruong(rs.getString("maTruong"));
	                truong.setTenTruong(rs.getString("tenTruong"));
	                truong.setDiaChi(rs.getString("diaChi"));
	                truong.setWebsite(rs.getString("website"));
	                dsTruong.add(truong);
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Lỗi khi lấy danh sách trường theo mã ngành: " + e.getMessage());
	        e.printStackTrace();
	    }
	    
	    return dsTruong;
	}

	public List<Truong> getAllTruong() {
	    List<Truong> list = new ArrayList<>();
	    String sql = "SELECT * FROM truongdhorcd";
	    
	    try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {
	        
	        while (rs.next()) {
	            Truong t = new Truong();
	            t.setIdTruong(rs.getInt("idTruong"));
	            t.setIdTaiKhoan(rs.getInt("idTaiKhoan"));
	            t.setMaTruong(rs.getString("maTruong"));
	            t.setTenTruong(rs.getString("tenTruong"));
	            t.setDiaChi(rs.getString("diaChi"));
	            t.setWebsite(rs.getString("website"));
	            list.add(t);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return list;
	}

	public boolean deleteTruong(int idTruong) {
	    String sql = "DELETE FROM truongdhorcd WHERE idTruong = ?";
	    
	    try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
	        ps.setInt(1, idTruong);
	        int rows = ps.executeUpdate();
	        return rows > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}