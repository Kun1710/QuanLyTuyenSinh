package Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.sql.Date;
import model.HocSinh;
import model.DiemHocBa;
import model.DiemThiTHPT;
import model.TaiKhoan;
import model.TaiKhoan.LoaiTaiKhoan;

public class DataGenerator {
    private static final String[] FIRST_NAMES = {
    		"Nguyễn", "Trần", "Lê", "Phạm", "Hoàng", "Huỳnh", "Phan", "Vũ", "Võ", "Đặng", "Cao", "Giang", "Đoàn", "Đậu"
    		};
    private static final String[] MIDDLE_NAMES = {
    	    "Văn", "Thị", "Hồng", "Thanh", "Minh", "Đức", "Phương", "Anh", "Ngọc", "Quốc",
    	    "Phạm Hoàng", "Thị Kim"
    	};

    	private static final String[] LAST_NAMES = {
    	    "An", "Bình", "Châu", "Dũng", "Đạt", "Hà", "Hưng", "Khoa", "Khánh", "Linh",
    	    "Long", "Nam", "Nhân", "Phong", "Quân", "Quang", "Sơn", "Tài", "Thành", "Thắng",
    	    "Trang", "Triều", "Trúc", "Tuấn", "Việt", "Vy", "Vũ", "Yến"
    	};

    private static final String[] DAN_TOC = {
    		"Kinh", "Kinh", "Kinh",  "Kinh",  "Kinh",  "Kinh", "Tày", "Thái", "Mường", "Khmer", "Hoa", "Nùng", "H'Mông", "Dao", "Ê Đê"
    		};
    private static final String[] DIA_CHI = {
    		"Hà Nội", "TP.HCM", "Đà Nẵng", "Hải Phòng", "Cần Thơ", "Huế", "Nha Trang", "Đà Lạt", "Vũng Tàu", "Buôn Ma Thuột",
    		"Đắk Nông", "Đắk Lắk", "Bến Tre", "Đồng Tháp", "Nam Định", "Hà Nội", "Hà Nội", "Hà Nội", "TP.HCM", "TP.HCM", "TP.HCM"
    		};
    private static final String[] NAM_HOC = {"10", "11", "12"};
    private static final String[] KHOI_THI = {"A","C",};

    private static final Random random = new Random();
    private static boolean lastStudentWasGood = false;

    private static float generateDiemHocBa(String namHoc) {  // Thêm tham số namHoc
        float bonus = lastStudentWasGood ? 0.5f : 0f;
        float baseScore = 5 + (float)(Math.pow(random.nextFloat(), 1.5) * 5);
        
        // Thêm phần tính bonus theo năm học
        float bonusProgress = 0.2f * (Integer.parseInt(namHoc) - 9); // Năm 10: +0.2, năm 11: +0.4, năm 12: +0.6
        baseScore = Math.min(baseScore + bonus + bonusProgress, 10f);
        
        baseScore = Math.round(baseScore * 10) / 10f;
        lastStudentWasGood = baseScore >= 8.0f;
        return baseScore;
    }

    private static float generateDiemThiTHPT() {
        float randomValue = random.nextFloat();
        float score;
        
        if (randomValue < 0.1f) {
            score = random.nextFloat() * 5f;
        } else if (randomValue < 0.95f) {
            score = 5f + random.nextFloat() * 3f;
        } else {
            score = 8f + random.nextFloat() * 2f;
        }
      
        score = Math.round(score * 10) / 10f;
        return score;
    }


    public static List<DiemHocBa> generateFullDiemHocBa(int idHS) {
        List<DiemHocBa> dsDiemHocBa = new ArrayList<>();
        

        for (String namHoc : NAM_HOC) {
        	
            DiemHocBa diem = new DiemHocBa(
                idHS,
                namHoc,
                generateDiemHocBa(namHoc), // 
                generateDiemHocBa(namHoc), // 
                generateDiemHocBa(namHoc), // 
                generateDiemHocBa(namHoc), // 
                generateDiemHocBa(namHoc), // 
                generateDiemHocBa(namHoc), // 
                generateDiemHocBa(namHoc), // 
                generateDiemHocBa(namHoc), // 
                generateDiemHocBa(namHoc)  // 
            );
            dsDiemHocBa.add(diem);
        }
        
        return dsDiemHocBa;
    }

    public static DiemThiTHPT generateRandomDiemThiTHPT(int idHS) {
    	String khoiThi = KHOI_THI[random.nextInt(KHOI_THI.length)];;
        
        DiemThiTHPT diemThi = new DiemThiTHPT();
        diemThi.setIdHS(idHS);
        diemThi.setKhoiThi(khoiThi);
        
        diemThi.setToan(generateDiemThiTHPT());
        diemThi.setVan(generateDiemThiTHPT());
        diemThi.setAnh(generateDiemThiTHPT());

        switch (khoiThi) {
            case "A":
                diemThi.setLy(generateDiemThiTHPT());
                diemThi.setHoa(generateDiemThiTHPT());
                diemThi.setSinh(generateDiemThiTHPT());
                diemThi.setSu(0f);
                diemThi.setDia(0f);
                diemThi.setGdcd(0f);
                break;
                
            case "C":
                diemThi.setLy(0f);
                diemThi.setHoa(0f);
                diemThi.setSinh(0f);
                diemThi.setSu(generateDiemThiTHPT());
                diemThi.setDia(generateDiemThiTHPT());
                diemThi.setGdcd(generateDiemThiTHPT());
                break;
                
        }
        
        return diemThi;
    }

    public static TaiKhoan generateRandomAccount(LoaiTaiKhoan loaiTK) {
        String username = "HS" + random.nextInt(1001);
//       String password = "123";
        String password = "MK@" + random.nextInt(101);
        String email = username + "@example.com";
        return new TaiKhoan(username, password, email, loaiTK);
    }

    public static HocSinh generateRandomStudent(int idTaiKhoan) {
        String cccd = generateRandomCCCD();
        String hoTen = FIRST_NAMES[random.nextInt(FIRST_NAMES.length)] + " " +
        			  MIDDLE_NAMES[random.nextInt(MIDDLE_NAMES.length)] + " " +
                     LAST_NAMES[random.nextInt(LAST_NAMES.length)];
        Date ngaySinh = generateRandomDate();
        boolean gioiTinh = random.nextBoolean();
        String danToc = DAN_TOC[random.nextInt(DAN_TOC.length)];
        String diaChi = DIA_CHI[random.nextInt(DIA_CHI.length)];
        
        return new HocSinh(cccd, hoTen, ngaySinh, gioiTinh, danToc, diaChi);
    }

    private static String generateRandomCCCD() {
        return String.valueOf(1000000000L + Math.abs(random.nextInt(900000000)));
    }

    private static Date generateRandomDate() {
        long minDay = Date.valueOf("2000-01-01").getTime();
        long maxDay = Date.valueOf("2005-12-31").getTime();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return new Date(randomDay);
    }
}
