package util;

import org.mindrot.jbcrypt.BCrypt;

public class MaHoaPass {

    /**
     * Mã hóa mật khẩu bằng thuật toán BCrypt
     *
     * @param password Mật khẩu gốc
     * @return Mật khẩu đã được băm
     */
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt(12)); // Số vòng lặp là 12 (đủ an toàn)
    }

    /**
     * Kiểm tra mật khẩu với chuỗi đã băm
     *
     * @param plainPassword  Mật khẩu người dùng nhập vào
     * @param hashedPassword Mật khẩu đã băm từ database
     * @return true nếu khớp, false nếu không khớp
     */
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}