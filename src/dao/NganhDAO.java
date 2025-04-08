package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.KhoiXetTuyen;
import model.Nganh;

public class NganhDAO {


	public boolean addNganh(Nganh ng) {
        String sql = "INSERT INTO nganh (idTruong, maNganh, tenNganh, chiTieu, phuongThuc, khoiXetTuyen, uuTien) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
        // Sử dụng try-with-resources để tự động đóng kết nối
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
            
            ps.setInt(1, ng.getIdTruong());
            ps.setString(2, ng.getMaNganh());
            ps.setString(3, ng.getTenNganh());
            ps.setInt(4, ng.getChiTieu());
            ps.setString(5, ng.getPhuongThuc());
            ps.setString(6, ng.getKhoiXetTuyen().toString());
            ps.setString(7, ng.getUuTien());

            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

	public List<Nganh> getNganhByTruong(int idTruong) {
		List<Nganh> dsNganh = new ArrayList<>();
		String sql = "SELECT * FROM nganh WHERE idTruong = ?";

		try {
			PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
			ps.setInt(1, idTruong);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Nganh ng = new Nganh();
				ng.setIdNganh(rs.getInt("idNganh"));
				ng.setIdTruong(rs.getInt("idTruong"));
				ng.setMaNganh(rs.getString("maNganh"));
				ng.setTenNganh(rs.getString("tenNganh"));
				ng.setChiTieu(rs.getInt("chiTieu"));
				ng.setPhuongThuc(rs.getString("phuongThuc"));
				ng.setKhoiXetTuyen(KhoiXetTuyen.fromString(rs.getString("khoiXetTuyen")));
				ng.setUuTien(rs.getString("uuTien"));

				dsNganh.add(ng);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsNganh;
	}

	public boolean deleteNganh(int idNganh) {
		String sql = "DELETE FROM nganh WHERE idNganh = ?";
		try {
			PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
			ps.setInt(1, idNganh);
			int result = ps.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	public Nganh getNganhByMaNganh(String maNganh) {
		String sql = "SELECT * FROM nganh WHERE maNganh = ?";
		try {
			PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
			ps.setString(1, maNganh);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Nganh ng = new Nganh();
				ng.setIdNganh(rs.getInt("idNganh"));
				ng.setIdTruong(rs.getInt("idTruong"));
				ng.setMaNganh(rs.getString("maNganh"));
				ng.setTenNganh(rs.getString("tenNganh"));
				ng.setChiTieu(rs.getInt("chiTieu"));
				ng.setPhuongThuc(rs.getString("phuongThuc"));
				ng.setKhoiXetTuyen(KhoiXetTuyen.fromString(rs.getString("khoiXetTuyen")));
				ng.setUuTien(rs.getString("uuTien"));
				return ng;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean updateNganh(Nganh ng) {
	    String sql = "UPDATE nganh SET maNganh=?, tenNganh=?, chiTieu=?, phuongThuc=?, khoiXetTuyen=?, uuTien=? WHERE idNganh=?";
	    
	    try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {
	        
	        // Đặt các tham số theo ĐÚNG THỨ TỰ trong câu SQL
	        ps.setString(1, ng.getMaNganh());        // maNganh
	        ps.setString(2, ng.getTenNganh());       // tenNganh
	        ps.setInt(3, ng.getChiTieu());           // chiTieu (INT)
	        ps.setString(4, ng.getPhuongThuc());     // phuongThuc
	        ps.setString(5, ng.getKhoiXetTuyen().toString()); // khoiXetTuyen (ENUM)
	        ps.setString(6, ng.getUuTien());         // uuTien
	        ps.setInt(7, ng.getIdNganh());           // idNganh (điều kiện WHERE)

	        int rowsAffected = ps.executeUpdate();
	        return rowsAffected > 0;
	        
	    } catch (SQLException e) {
	        System.err.println("Lỗi khi cập nhật ngành: " + e.getMessage());
	        e.printStackTrace();
	        return false;
	    }
	}

	public int getIdTruongByTaiKhoan(int idTaiKhoan) {
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
	 * Xóa ngành học dựa trên mã ngành (maNganh)
	 * 
	 * @param maNganh Mã ngành cần xóa
	 * @return true nếu xóa thành công, false nếu thất bại
	 */
	public boolean deleteNganh(String maNganh) {
		String sql = "DELETE FROM nganh WHERE maNganh = ?";

		try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
			ps.setString(1, maNganh);
			int affectedRows = ps.executeUpdate();
			return affectedRows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Lỗi khi xóa ngành học: " + e.getMessage());
			return false;
		}
	}

//	public boolean isMaNganhExists(String maNganh) {
//		String sql = "SELECT 1 FROM nganh WHERE maNganh = ?";
//		try {
//			PreparedStatement ps = connection.prepareStatement(sql);
//			ps.setString(1, maNganh);
//			ResultSet rs = ps.executeQuery();
//			return rs.next();
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return false;
//		}
//	}

	/**
	 * Lấy thông tin khối xét tuyển dựa vào mã ngành
	 * 
	 * @param maNganh Mã ngành cần tìm
	 * @return Đối tượng KhoiXetTuyen nếu tìm thấy, null nếu không tìm thấy
	 */
	public KhoiXetTuyen getKhoiXetTuyenByMaNganh(String maNganh) {
		String sql = "SELECT khoiXetTuyen FROM nganh WHERE maNganh = ?";
		try {
			PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
			ps.setString(1, maNganh);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				return KhoiXetTuyen.fromString(rs.getString("khoiXetTuyen"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getIdNganhByMaNganh(String maNganh) {
		String sql = "SELECT idNganh FROM nganh WHERE maNganh = ?";
		try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
			ps.setString(1, maNganh);
			ResultSet rs = ps.executeQuery();
			return rs.next() ? rs.getInt("idNganh") : -1;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	public String getTenNganhById(int idNganh) throws SQLException {
		String sql = "SELECT tenNganh FROM nganh WHERE idNganh = ?";
		try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
			ps.setInt(1, idNganh);
			ResultSet rs = ps.executeQuery();
			return rs.next() ? rs.getString("tenNganh") : "Không xác định";
		}
	}

	public Nganh getNganhById(int idNganh) {
		String sql = "SELECT * FROM nganh WHERE idNganh = ?";
		try {
			PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
			ps.setInt(1, idNganh);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				Nganh ng = new Nganh();
				ng.setIdNganh(rs.getInt("idNganh"));
				ng.setIdTruong(rs.getInt("idTruong"));
				ng.setMaNganh(rs.getString("maNganh"));
				ng.setTenNganh(rs.getString("tenNganh"));
				ng.setChiTieu(rs.getInt("chiTieu"));
				ng.setPhuongThuc(rs.getString("phuongThuc"));
				ng.setKhoiXetTuyen(KhoiXetTuyen.fromString(rs.getString("khoiXetTuyen")));
				ng.setUuTien(rs.getString("uuTien"));
				return ng;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	// Thêm phương thức này vào class nganhDAO

//    public List<nganh> searchNganh(String keyword) {
//        List<nganh> dsNganh = new ArrayList<>();
//        String sql = "SELECT * FROM nganh WHERE maNganh LIKE ? OR tenNganh LIKE ?";
//        
//        try {
//            PreparedStatement ps = connection.prepareStatement(sql);
//            ps.setString(1, "%" + keyword + "%");
//            ps.setString(2, "%" + keyword + "%");
//            ResultSet rs = ps.executeQuery();
//            
//            while (rs.next()) {
//                nganh ng = new nganh();
//                ng.setIdNganh(rs.getInt("idNganh"));
//                ng.setIdTruong(rs.getInt("idTruong"));
//                ng.setMaNganh(rs.getString("maNganh"));
//                ng.setTenNganh(rs.getString("tenNganh"));
//                ng.setChiTieu(rs.getInt("chiTieu"));
//                ng.setPhuongThuc(rs.getString("phuongThuc"));
//                ng.setKhoiXetTuyen(KhoiXetTuyen.fromString(rs.getString("khoiXetTuyen")));
//                ng.setUuTien(rs.getString("uuTien"));
//                dsNganh.add(ng);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return dsNganh;
//    }
	public List<String> searchMaNganh(String keyword) {
	    List<String> dsMaNganh = new ArrayList<>();
	    if (keyword == null || keyword.trim().isEmpty()) {
	        return dsMaNganh;
	    }
	    
	    String sql = "SELECT maNganh FROM nganh WHERE maNganh LIKE ? OR tenNganh LIKE ?";
	    
	    try (PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql)) {
	        String searchPattern = "%" + keyword + "%";
	        ps.setString(1, searchPattern);
	        ps.setString(2, searchPattern);
	        
	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                dsMaNganh.add(rs.getString("maNganh"));
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("Lỗi khi tìm kiếm mã ngành: " + e.getMessage());
	        e.printStackTrace();
	    }
//	    // In danh sách mã ngành tìm được trước khi return
//	    System.out.println("\nDANH SÁCH MÃ NGÀNH TÌM ĐƯỢC:");
//	    System.out.println("----------------------------------------");
//	    if (dsMaNganh.isEmpty()) {
//	        System.out.println("Không tìm thấy mã ngành nào phù hợp");
//	    } else {
//	        System.out.println("Tổng số: " + dsMaNganh.size());
//	        for (int i = 0; i < dsMaNganh.size(); i++) {
//	            System.out.printf("%-3d. %s%n", (i + 1), dsMaNganh.get(i));
//	        }
//	    }
//	    System.out.println("----------------------------------------\n");
	    return dsMaNganh;
	}
	public List<Nganh> searchNganhByTruong(String keyword, int idTruong) {
		List<Nganh> dsNganh = new ArrayList<>();
		String sql = "SELECT * FROM nganh WHERE (maNganh LIKE ? OR tenNganh LIKE ?) AND idTruong = ?";

		try {
			PreparedStatement ps = DatabaseConnection.getConnection().prepareStatement(sql);
			ps.setString(1, "%" + keyword + "%");
			ps.setString(2, "%" + keyword + "%");
			ps.setInt(3, idTruong);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Nganh ng = new Nganh();
				ng.setIdNganh(rs.getInt("idNganh"));
				ng.setIdTruong(rs.getInt("idTruong"));
				ng.setMaNganh(rs.getString("maNganh"));
				ng.setTenNganh(rs.getString("tenNganh"));
				ng.setChiTieu(rs.getInt("chiTieu"));
				ng.setPhuongThuc(rs.getString("phuongThuc"));
				ng.setKhoiXetTuyen(KhoiXetTuyen.fromString(rs.getString("khoiXetTuyen")));
				ng.setUuTien(rs.getString("uuTien"));
				dsNganh.add(ng);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dsNganh;
	}
	
	public boolean isMaNganhExists(String maNganh) {
        String sql = "SELECT COUNT(*) FROM nganh WHERE maNganh = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, maNganh);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}