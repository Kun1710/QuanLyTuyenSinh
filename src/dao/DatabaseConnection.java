package dao;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DatabaseConnection {
//    private static Connection connection = null;
//    
//    // Thông tin kết nối database - nên lưu trong file cấu hình
//    private static final String DB_URL = "jdbc:mysql://localhost:3306/quanly_tuyensinh?serverTimezone=UTC";
//    private static final String DB_USER = "root";
//    private static final String DB_PASSWORD = "1710";
//    
//    // Private constructor để ngăn chặn khởi tạo từ bên ngoài
//    private DatabaseConnection() {}
//    
//    /**
//     * Phương thức tạo kết nối đến database
//     * @return Connection object
//     * @throws SQLException nếu có lỗi kết nối
//     */
//    public static Connection getConnection() throws SQLException {
//        if (connection == null || connection.isClosed()) {
//            try {
//                // Đăng ký driver MySQL
//                Class.forName("com.mysql.cj.jdbc.Driver");
//                
//                // Tạo kết nối
//                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//            } catch (ClassNotFoundException e) {
//                throw new SQLException("MySQL JDBC Driver không tìm thấy", e);
//            } catch (SQLException e) {
//                throw new SQLException("Lỗi kết nối database", e);
//            }
//        }
//        return connection;
//    }
//    
//    /**
//     * Đóng kết nối database
//     */
//    public static void closeConnection() {
//        if (connection != null) {
//            try {
//                connection.close();
//                System.out.println("Đã đóng kết nối database");
//            } catch (SQLException e) {
//                System.err.println("Lỗi khi đóng kết nối database: " + e.getMessage());
//            }
//        }
//    }
//    
//    /**
//     * Kiểm tra kết nối database
//     * @return true nếu kết nối hợp lệ, false nếu không
//     */
//    public static boolean isConnectionValid() {
//        if (connection != null) {
//            try {
//                return connection.isValid(5); // Kiểm tra trong 5 giây
//            } catch (SQLException e) {
//                return false;
//            }
//        }
//        return false;
//    }
//}
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/quanly_tuyensinh?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "1710";
    
    static {
        try {
            DriverManager.setLoginTimeout(5); // 5 giây timeout
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        if (conn.isClosed()) {
            throw new SQLException("Connection is closed immediately after creation");
        }
        return conn;
    }
}