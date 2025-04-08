package model;

import java.sql.SQLException;

import dao.DiemHocBaDAO;
import dao.DiemThiTHPTDAO;
import dao.HocSinhDAO;
import dao.NganhDAO;
import util.SessionManager;
import util.TemporaryDataStorage;


public class HoSoDangKy {
    private int idHoSo;
    private int idHS;
    private String trangThai;
    private float diemThi;
    private float diemHocBa;
    
    // Constructors
    public HoSoDangKy() {}
    
    public HoSoDangKy(int idHoSo, int idHS, String trangThai, float diemThi, float diemHocBa) {
        this.idHoSo = idHoSo;
        this.idHS = idHS;
        this.trangThai = trangThai;
        this.diemThi = diemThi;
        this.diemHocBa = diemHocBa;
    }
    
    // Getters and Setters
    public int getIdHoSo() {
        return idHoSo;
    }
    
    public void setIdHoSo(int idHoSo) {
        this.idHoSo = idHoSo;
    }
    
    public int getIdHS() {
        return idHS;
    }
    
    public void setIdHS(int idHS) {
        this.idHS = idHS;
    }
    
    public String getTrangThai() {
        return trangThai;
    }
    
    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
    
//    public float getDiemThi(String maNganh) {
//        try {
//
//            SessionManager session = SessionManager.getInstance();
//            TaiKhoan currentUser = session.getCurrentAccount();
//            if (currentUser == null) {
//                throw new IllegalStateException("Không tìm thấy thông tin người dùng");
//            }
//
//            if (maNganh == null || maNganh.isEmpty()) {
//                throw new IllegalArgumentException("Mã ngành không hợp lệ");
//            }
//            
//            NganhDAO nganhDao = new NganhDAO();
//            KhoiXetTuyen khoi = nganhDao.getKhoiXetTuyenByMaNganh(maNganh);
//            if (khoi == null) {
//                throw new IllegalStateException("Không tìm thấy khối xét tuyển cho ngành " + maNganh);
//            }
//            HocSinhDAO hsDao = new HocSinhDAO();
//            int idHS = hsDao.getIdHSByIdTaiKhoan(currentUser.getId());
//            if (idHS <= 0) {
//                throw new IllegalStateException("Không tìm thấy học sinh tương ứng");
//            }
//
//            DiemThiTHPTDAO diemThiDao = new DiemThiTHPTDAO();
//            DiemThiTHPT diemThi = diemThiDao.getByStudentId(idHS);
//            if (diemThi == null) {
//                throw new IllegalStateException("Không tìm thấy điểm thi THPT");
//            }
//
//            switch (khoi) {
//                case A: return diemThi.getTongDiemKhoiA();
//                case B: return diemThi.getTongDiemKhoiB();
//                case C: return diemThi.getTongDiemKhoiC();
//                case D: return diemThi.getTongDiemKhoiD();
//                default: return 0f;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Lỗi truy vấn cơ sở dữ liệu", e);
//        }catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException(
//                "Lỗi khi lấy điểm thi: " 
//                + e.getClass().getName() 
//                + " - " 
//                + e.getMessage(),       
//                e
//            );
//        }
//    }
    public float getDiemThi() {
        try {

            SessionManager session = SessionManager.getInstance();
            TaiKhoan currentUser = session.getCurrentAccount();
            if (currentUser == null) {
                throw new IllegalStateException("Không tìm thấy thông tin người dùng");
            }

            NguyenVongTamThoi nguyenVongTam = TemporaryDataStorage.getInstance().layNguyenVongTamThoi();
                String maNganh = nguyenVongTam.getMaNganh();

            if (maNganh == null || maNganh.isEmpty()) {
            	System.out.println("O day: " + maNganh);
                throw new IllegalArgumentException("Mã ngành không hợp lệ0001");
            }
            NganhDAO nganhDao = new NganhDAO();
            KhoiXetTuyen khoi = nganhDao.getKhoiXetTuyenByMaNganh(maNganh);
            if (khoi == null) {
                throw new IllegalStateException("Không tìm thấy khối xét tuyển cho ngành " + maNganh);
            }
            HocSinhDAO hsDao = new HocSinhDAO();
            int idHS = hsDao.getIdHSByIdTaiKhoan(currentUser.getId());
            if (idHS <= 0) {
                throw new IllegalStateException("Không tìm thấy học sinh tương ứng");
            }

            DiemThiTHPTDAO diemThiDao = new DiemThiTHPTDAO();
            DiemThiTHPT diemThi = diemThiDao.getByStudentId(idHS);
            if (diemThi == null) {
                throw new IllegalStateException("Không tìm thấy điểm thi THPT");
            }

            switch (khoi) {
                case A: return diemThi.getTongDiemKhoiA();
                case B: return diemThi.getTongDiemKhoiB();
                case C: return diemThi.getTongDiemKhoiC();
                case D: return diemThi.getTongDiemKhoiD();
                default: return 0f;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Lỗi truy vấn cơ sở dữ liệu", e);
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(
                "Lỗi khi lấy điểm thi-- o dáy: " 
                + e.getClass().getName()
                + " - " 
                + e.getMessage(),        
                e
            );
        }
    }
    public void setDiemThi(float diemThi) {
        this.diemThi = diemThi;
    }
    
    public float getDiemHocBa() {
  //      try {
            // Lấy thông tin người dùng hiện tại
            SessionManager session = SessionManager.getInstance();
            TaiKhoan currentUser = session.getCurrentAccount();
            if (currentUser == null) {
                throw new IllegalStateException("Không tìm thấy thông tin người dùng");
            }

            // Lấy mã ngành từ view (nên truyền vào từ bên ngoài thay vì tạo mới)
            NguyenVongTamThoi nguyenVongTam = TemporaryDataStorage.getInstance().layNguyenVongTamThoi();
            //   String maTruong = nguyenVongTam.getMaTruong();
               String maNganh = nguyenVongTam.getMaNganh();
            if (maNganh == null || maNganh.isEmpty()) {
                throw new IllegalArgumentException("Mã ngành không hợp lệ");
            }

            // Lấy khối xét tuyển của ngành
            NganhDAO nganhDao = new NganhDAO();
            KhoiXetTuyen khoi = nganhDao.getKhoiXetTuyenByMaNganh(maNganh);
            if (khoi == null) {
                throw new IllegalStateException("Không tìm thấy khối xét tuyển cho ngành " + maNganh);
            }

            // Lấy thông tin học sinh
            HocSinhDAO hsDao = new HocSinhDAO();
            int idHS = hsDao.getIdHSByIdTaiKhoan(currentUser.getId());
            if (idHS <= 0) {
                throw new IllegalStateException("Không tìm thấy học sinh tương ứng");
            }

            // Lấy điểm học bạ các năm
            DiemHocBaDAO hocBaDao = new DiemHocBaDAO();
            DiemHocBa diem10 = hocBaDao.getDiemHocBaByHSAndNamHoc(idHS, "10");
            DiemHocBa diem11 = hocBaDao.getDiemHocBaByHSAndNamHoc(idHS, "11");
            DiemHocBa diem12 = hocBaDao.getDiemHocBaByHSAndNamHoc(idHS, "12");

            // Tính điểm trung bình học bạ theo khối
            switch (khoi) {
                case A:
                    return calculateDiemHocBaKhoiA(diem10, diem11, diem12);
                case B:
                    return calculateDiemHocBaKhoiB(diem10, diem11, diem12);
                case C:
                    return calculateDiemHocBaKhoiC(diem10, diem11, diem12);
                case D:
                    return calculateDiemHocBaKhoiD(diem10, diem11, diem12);
                default:
                    return 0f;
            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new RuntimeException("Lỗi truy vấn cơ sở dữ liệu", e);
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw new RuntimeException("Lỗi khi lấy điểm học bạ", e);
//        }
    }

    // Các phương thức hỗ trợ tính điểm theo khối
    private float calculateDiemHocBaKhoiA(DiemHocBa diem10, DiemHocBa diem11, DiemHocBa diem12) {
//        float toan = calculateTrungBinhMon(diem10.getToan(), diem11.getToan(), diem12.getToan());
//        float ly = calculateTrungBinhMon(diem10.getLy(), diem11.getLy(), diem12.getLy());
//        float hoa = calculateTrungBinhMon(diem10.getHoa(), diem11.getHoa(), diem12.getHoa());
//        return (toan + ly + hoa) / 3;
    	return diem10.getToan() + diem10.getLy()+ diem10.getHoa() +
    		   diem11.getToan() + diem11.getLy()+ diem11.getHoa() +
    		   diem12.getToan() + diem12.getLy()+ diem12.getHoa();
    			 
    }

    private float calculateDiemHocBaKhoiB(DiemHocBa diem10, DiemHocBa diem11, DiemHocBa diem12) {
//        float toan = calculateTrungBinhMon(diem10.getToan(), diem11.getToan(), diem12.getToan());
//        float hoa = calculateTrungBinhMon(diem10.getHoa(), diem11.getHoa(), diem12.getHoa());
//        float sinh = calculateTrungBinhMon(diem10.getSinh(), diem11.getSinh(), diem12.getSinh());
//        return (toan + hoa + sinh) / 3;
    	return diem10.getToan() + diem10.getSinh()+ diem10.getHoa() +
     		   diem11.getToan() + diem11.getSinh()+ diem11.getHoa() +
     		   diem12.getToan() + diem12.getSinh()+ diem12.getHoa();
    }

    private float calculateDiemHocBaKhoiC(DiemHocBa diem10, DiemHocBa diem11, DiemHocBa diem12) {
//        float van = calculateTrungBinhMon(diem10.getVan(), diem11.getVan(), diem12.getVan());
//        float su = calculateTrungBinhMon(diem10.getSu(), diem11.getSu(), diem12.getSu());
//        float dia = calculateTrungBinhMon(diem10.getDia(), diem11.getDia(), diem12.getDia());
//        return (van + su + dia) / 3;
    	return diem10.getVan() + diem10.getSu()+ diem10.getDia() +
     		   diem11.getVan() + diem11.getSu()+ diem11.getDia() +
     		   diem12.getVan() + diem12.getSu()+ diem12.getDia();
    }

    private float calculateDiemHocBaKhoiD(DiemHocBa diem10, DiemHocBa diem11, DiemHocBa diem12) {
//        float toan = calculateTrungBinhMon(diem10.getToan(), diem11.getToan(), diem12.getToan());
//        float van = calculateTrungBinhMon(diem10.getVan(), diem11.getVan(), diem12.getVan());
//        float anh = calculateTrungBinhMon(diem10.getAnh(), diem11.getAnh(), diem12.getAnh());
//        return (toan + van + anh) / 3;
    	return diem10.getToan() + diem10.getVan()+ diem10.getAnh() +
     		   diem11.getToan() + diem11.getVan()+ diem11.getAnh() +
     		   diem12.getToan() + diem12.getVan()+ diem12.getAnh();
    }

//    private float calculateTrungBinhMon(Float diem10, Float diem11, Float diem12) {
//        float sum = 0;
//        int count = 0;
//        
//        if (diem10 != null && diem10 > 0) {
//            sum += diem10;
//            count++;
//        }
//        if (diem11 != null && diem11 > 0) {
//            sum += diem11;
//            count++;
//        }
//        if (diem12 != null && diem12 > 0) {
//            sum += diem12;
//            count++;
//        }
//        
//        return count > 0 ? sum / count : 0;
//    }
    
    
    public void setDiemHocBa(float diemHocBa) {
        this.diemHocBa = diemHocBa;
    }
}