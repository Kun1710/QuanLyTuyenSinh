package Main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import dao.DiemHocBaDAO;
import dao.DiemThiTHPTDAO;
import dao.HocSinhDAO;
import dao.TaiKhoanDAO;
import model.HocSinh;
import model.DiemHocBa;
import model.DiemThiTHPT;
import model.TaiKhoan;
import model.TaiKhoan.LoaiTaiKhoan;
import util.SessionManager;

public class DataCreator {
    private final TaiKhoanDAO taiKhoanDAO = new TaiKhoanDAO();
    private final HocSinhDAO hocSinhDAO = new HocSinhDAO();
    private final DiemHocBaDAO diemHocBaDAO = new DiemHocBaDAO();
    private final DiemThiTHPTDAO diemThiTHPTDAO = new DiemThiTHPTDAO();
    private final SessionManager sessionManager = SessionManager.getInstance();

    public void createTestData(int numberOfStudents) {
        try (FileWriter writer = new FileWriter("student_accounts.txt")) {
            writer.write("DANH SÁCH TÀI KHOẢN HỌC SINH (Tài khoản - Mật khẩu)\n");
            writer.write("=====================================================\n");

            for (int i = 0; i < numberOfStudents; i++) {
                try {

                //    System.out.println("\n=== Tạo tài khoản mới ===");
                    TaiKhoan account = DataGenerator.generateRandomAccount(LoaiTaiKhoan.HS);
                    boolean accountCreated = taiKhoanDAO.dangKy(account);
                    String accountInfo = String.format("%-15s | %s\n", 
                            account.getTaiKhoan(), 
                            account.getMatKhau());
                        
                       // System.out.print("Đã tạo thành công: " + accountInfo);
                        writer.write(accountInfo);
                      
                    if (accountCreated) {

                    //    System.out.println("Đang đăng nhập...");
                        boolean loginSuccess = taiKhoanDAO.login(account);
                        
                        if (loginSuccess) {

                            TaiKhoan loggedInAccount = taiKhoanDAO.getFullAccount(account.getTaiKhoan());
                            sessionManager.setCurrentAccount(loggedInAccount);
                            System.out.println("Đã đăng nhập và lưu session: " + loggedInAccount.getTaiKhoan());
                            

                            processStudentProfile(loggedInAccount, writer);
                            

                            sessionManager.setCurrentAccount(null);
                            
                         //   System.out.println("Đã đăng xuất tài khoản: " + loggedInAccount.getTaiKhoan());
                        }
                    }
                } catch (Exception e) {
                    System.err.println("Lỗi khi tạo dữ liệu test: " + e.getMessage());
                    e.printStackTrace();
                }
            }
            
            System.out.println("\nĐã lưu danh sách tài khoản vào file: student_accounts.txt");
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi file: " + e.getMessage());
        }
    }

    private void processStudentProfile(TaiKhoan account, FileWriter writer) throws Exception {
        // 1. Tạo hồ sơ học sinh
        HocSinh student = DataGenerator.generateRandomStudent(account.getId());
        boolean studentCreated = hocSinhDAO.insertHocSinh(student, account.getId());
        
        if (studentCreated) {
            // 2. Lấy ID học sinh vừa tạo
            HocSinh createdStudent = hocSinhDAO.getHocSinhByTaiKhoan(account.getTaiKhoan());
            
            if (createdStudent != null) {
                // 3. Thêm điểm học bạ
                List<DiemHocBa> dsDiemHocBa = DataGenerator.generateFullDiemHocBa(createdStudent.getIdHS());
                for (DiemHocBa diem : dsDiemHocBa) {
                    diemHocBaDAO.insertDiemHocBa(diem);
                }
                
                // 4. Thêm điểm thi THPT
                DiemThiTHPT diemThiTHPT = DataGenerator.generateRandomDiemThiTHPT(createdStudent.getIdHS());
                diemThiTHPTDAO.saveOrUpdate(diemThiTHPT);
                
                // 5. Ghi thông tin tài khoản
//                String accountInfo = String.format("%-15s | %s\n", 
//                    account.getTaiKhoan(), 
//                    account.getMatKhau());
//                
//                System.out.print("Đã tạo thành công: " + accountInfo);
//                writer.write(accountInfo);
            }
        }
    }

    public static void main(String[] args) {
        DataCreator creator = new DataCreator();
        creator.createTestData(100);
        System.out.println("Đã hoàn thành tạo dữ liệu test!");
    }
}