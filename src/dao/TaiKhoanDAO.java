package dao;

import model.TaiKhoan;
import model.TaiKhoan.LoaiTaiKhoan;
import util.MaHoaPass;

import java.sql.*;
import java.util.Random;

public class TaiKhoanDAO {

    /**
     * Kết nối đến cơ sở dữ liệu MySQL
     */
//    public static Connection getConnection() throws SQLException {
//        String url = "jdbc:mysql://localhost:3306/quanly_tuyensinh?serverTimezone=UTC";
//        String user = "root";
//        String password = "1710";
//        return DriverManager.getConnection(url, user, password);
//    }

    /**
     * Kiểm tra thông tin đăng nhập
     */
    public boolean login(TaiKhoan account) throws SQLException {
        String query = "SELECT id, taiKhoan, matKhau, email, loaiTaiKhoan FROM account WHERE taiKhoan = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, account.getTaiKhoan());
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                String hashedPassword = rs.getString("matKhau");
                if (MaHoaPass.checkPassword(account.getMatKhau(), hashedPassword)) {
                    // Cập nhật thông tin account từ database
                    account.setId(rs.getInt("id"));
                    account.setEmail(rs.getString("email"));
                    account.setLoaiTaiKhoan(LoaiTaiKhoan.valueOf(rs.getString("loaiTaiKhoan")));
                    return true;
                }
            }
            return false;
        }
    }
    public TaiKhoan getFullAccount(String taiKhoan) throws SQLException {
        String query = "SELECT id, taiKhoan, email, loaiTaiKhoan FROM account WHERE taiKhoan = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, taiKhoan);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new TaiKhoan(
                    rs.getInt("id"),
                    rs.getString("taiKhoan"),
                    null,
                    rs.getString("email"),
                    LoaiTaiKhoan.valueOf(rs.getString("loaiTaiKhoan"))
                );
            }
        }
        return null;
    }

  // Hàm lấy về loại Tài Khoản đó là: HS, TRUONG, ADMIN, kiểu dữ liệu là EMUN
    public LoaiTaiKhoan getLoaiTK(TaiKhoan acc) {
        String query = "SELECT loaiTaiKhoan FROM account WHERE taiKhoan = ?";
        try (Connection conn = DatabaseConnection.getConnection(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, acc.getTaiKhoan());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String loaiTK = rs.getString("loaiTaiKhoan");
                return LoaiTaiKhoan.valueOf(loaiTK);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Kiểm tra tài khoản hoặc email đã tồn tại chưa
     */
    private boolean kiemTraTonTai(TaiKhoan account) throws SQLException {
        String query = "SELECT * FROM account WHERE taiKhoan = ? OR email = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, account.getTaiKhoan());
            stmt.setString(2, account.getEmail());
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    /**
     * Đăng ký tài khoản mới
     */
    public boolean dangKy(TaiKhoan account) throws SQLException {
        if (kiemTraTonTai(account)) {
            return false;
        }

        String query = "INSERT INTO account (taiKhoan, matKhau, email, loaiTaiKhoan) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, account.getTaiKhoan());
            stmt.setString(2, MaHoaPass.hashPassword(account.getMatKhau()));
            stmt.setString(3, account.getEmail());
            stmt.setString(4, account.getLoaiTaiKhoan().name());
            return stmt.executeUpdate() > 0;
        }catch (SQLException e) {
            e.printStackTrace();
            return false; 
        }
    }

    /**
     * Quên mật khẩu: tạo mật khẩu mới và cập nhật
     */
    public String quenMatKhau(TaiKhoan account) throws SQLException {
        String newPass = taoMatKhauNgauNhien();
        String hashedPass = MaHoaPass.hashPassword(newPass);

        String query = "UPDATE account SET matKhau = ? WHERE taiKhoan = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, hashedPass);
            stmt.setString(2, account.getTaiKhoan());
            if (stmt.executeUpdate() > 0) {
            	return newPass;
            }
        }
        return null;
    }

    /**
     * Đổi mật khẩu
     */
    public boolean changePass(TaiKhoan account, String newPass) throws SQLException {
        String hashedPass = MaHoaPass.hashPassword(newPass);

        String query = "UPDATE account SET matKhau = ? WHERE taiKhoan = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, hashedPass);
            stmt.setString(2, account.getTaiKhoan());
            return stmt.executeUpdate() > 0;
        }
    }

    /**
     * Tạo mật khẩu ngẫu nhiên 8 ký tự
     */
    private String taoMatKhauNgauNhien() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder newPass = new StringBuilder();
        Random rnd = new Random();
        for (int i = 0; i < 8; i++) {
            newPass.append(characters.charAt(rnd.nextInt(characters.length())));
        }
        return newPass.toString();
    }
    /**
     * Lấy thông tin account dựa vào id tài khoản
     * @param idTaiKhoan ID của tài khoản cần tìm
     * @return Đối tượng account nếu tìm thấy, null nếu không tìm thấy
     * @throws SQLException
     */
    public TaiKhoan getAccountByIdTaiKhoan(int idTaiKhoan) throws SQLException {
        String query = "SELECT id, taiKhoan, email, loaiTaiKhoan FROM account WHERE id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setInt(1, idTaiKhoan);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new TaiKhoan(
                    rs.getInt("id"),
                    rs.getString("taiKhoan"),
                    null, // Không lấy mật khẩu vì lý do bảo mật
                    rs.getString("email"),
                    LoaiTaiKhoan.valueOf(rs.getString("loaiTaiKhoan"))
                );
            }
        }
        return null;
    }
}