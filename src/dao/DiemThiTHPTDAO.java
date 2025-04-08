package dao;

import java.sql.*;
import model.DiemThiTHPT;

public class DiemThiTHPTDAO {
	public boolean saveOrUpdate(DiemThiTHPT diemThi) {
	    Connection connection = null;
	    PreparedStatement ps = null;
	    
	    try {
	        connection = DatabaseConnection.getConnection();
	        // Tắt auto-commit để kiểm soát transaction
	        connection.setAutoCommit(false);
	        
	        String sql = "INSERT INTO diemthidh (idHS, KhoiThi, Toan, Van, Anh, Ly, Hoa, Sinh, Su, Dia, GDCD) "
	                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) "
	                   + "ON DUPLICATE KEY UPDATE "
	                   + "KhoiThi = VALUES(KhoiThi), "
	                   + "Toan = VALUES(Toan), Van = VALUES(Van), Anh = VALUES(Anh), "
	                   + "Ly = VALUES(Ly), Hoa = VALUES(Hoa), Sinh = VALUES(Sinh), "
	                   + "Su = VALUES(Su), Dia = VALUES(Dia), GDCD = VALUES(GDCD)";
	        
	        ps = connection.prepareStatement(sql);
	        
	        // Thiết lập các tham số
	        ps.setInt(1, diemThi.getIdHS());
	        ps.setString(2, diemThi.getKhoiThi());
	        ps.setFloat(3, diemThi.getToan());
	        ps.setFloat(4, diemThi.getVan());
	        ps.setFloat(5, diemThi.getAnh());
	        ps.setFloat(6, diemThi.getLy());
	        ps.setFloat(7, diemThi.getHoa());
	        ps.setFloat(8, diemThi.getSinh());
	        ps.setFloat(9, diemThi.getSu());
	        ps.setFloat(10, diemThi.getDia());
	        ps.setFloat(11, diemThi.getGdcd());
	        
	        int affectedRows = ps.executeUpdate();
	        connection.commit(); // Xác nhận transaction
	        
	        return affectedRows > 0;
	        
	    } catch (SQLException e) {
	        try {
	            if (connection != null) connection.rollback();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        System.err.println("SQL Error details:");
	       // System.err.println("SQL: " + sql);
	        System.err.println("Params: " + diemThi.toString());
	        e.printStackTrace();
	        return false;
	    } finally {
	        try {
	            if (ps != null) ps.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}


    // Get exam scores by student ID
    public DiemThiTHPT getByStudentId(int idHS) throws SQLException {
        String sql = "SELECT * FROM diemthidh WHERE idHS = ?";
        DiemThiTHPT diemThi = null;
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {
            
            stmt.setInt(1, idHS);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                diemThi = new DiemThiTHPT();
                diemThi.setIdDiemthiDH(rs.getInt("idDiemthiDH"));
                diemThi.setIdHS(rs.getInt("idHS"));
                diemThi.setKhoiThi(rs.getString("KhoiThi"));
                diemThi.setToan(rs.getFloat("Toan"));
                diemThi.setLy(rs.getFloat("Ly"));
                diemThi.setHoa(rs.getFloat("Hoa"));
                diemThi.setSinh(rs.getFloat("Sinh"));
                diemThi.setAnh(rs.getFloat("Anh"));
                diemThi.setVan(rs.getFloat("Van"));
                diemThi.setSu(rs.getFloat("Su"));
                diemThi.setDia(rs.getFloat("Dia"));
                diemThi.setGdcd(rs.getFloat("GDCD"));
            }
        }
        
        return diemThi;
    }
}