package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

import dao.HoSoDangKyDAO;
import dao.NganhDAO;
import dao.NguyenVongDAO;
import model.HoSoDangKy;
import model.Nganh;
import model.NguyenVong;
import view.DanhSachTuyenThangView;
import view.KetQuaTuyenSinhView;
import view.XetTuyenView;

public class XetTuyenController {
    private XetTuyenView view;
    private NguyenVongDAO nguyenVongDAO;
    private NganhDAO nganhDAO;
    private HoSoDangKyDAO hoSoDangKyDAO;

    public XetTuyenController(XetTuyenView view) {
        this.view = view;
        this.nguyenVongDAO = new NguyenVongDAO();
        this.nganhDAO = new NganhDAO();
        this.hoSoDangKyDAO = new HoSoDangKyDAO();
        initController();
    }

    private void initController() {
        // Xử lý sự kiện cho nút Hồ Sơ Tuyển Thẳng
        view.getHoSoTuyenThangButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openTuyenThangView();
            }
        });

        // Xử lý sự kiện cho nút Xét Tuyển Tự Động
        view.getxetTuyenTuDongButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (kiemTraConNguyenVongTuyenThang()) {
                        JOptionPane.showMessageDialog(view, 
                            "Vui lòng xét tuyển thẳng trước khi xét tuyển tự động", 
                            "Thông báo", JOptionPane.WARNING_MESSAGE);
                    } else {
                        xetTuyenTuDong();
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(view, 
                        "Lỗi khi xử lý dữ liệu: " + ex.getMessage(), 
                        "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        view.getthoatButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
    }

    private boolean kiemTraConNguyenVongTuyenThang() throws SQLException {
    	//System.out.println("lỗi đây ");
        List<NguyenVong> dsTuyenThang = nguyenVongDAO.getAllNguyenVongTheoPhuongThuc("Tuyển thẳng");
        return !dsTuyenThang.isEmpty();
    }

    private void openTuyenThangView() {
        DanhSachTuyenThangView tuyenThangView = new DanhSachTuyenThangView();
        tuyenThangView.setVisible(true);
        new DanhSachTuyenThangController(tuyenThangView);
    }

    private void xetTuyenTuDong() throws SQLException {
        // Lấy tất cả hồ sơ đăng ký
        List<HoSoDangKy> dsHoSo = hoSoDangKyDAO.getAllHoSo();
        
        // Xét tuyển từng hồ sơ
        for (HoSoDangKy hoSo : dsHoSo) {
            xetTuyenMotHoSo(hoSo);
        }
        
        // Hiển thị kết quả xét tuyển
        JOptionPane.showMessageDialog(view, 
            "Đã hoàn thành xét tuyển tự động cho tất cả hồ sơ", 
            "Thông báo", JOptionPane.INFORMATION_MESSAGE);
    }

    private void xetTuyenMotHoSo(HoSoDangKy hoSo) throws SQLException {
        // Lấy danh sách nguyện vọng của hồ sơ, sắp xếp theo thứ tự nguyện vọng
        List<NguyenVong> dsNguyenVong = nguyenVongDAO.getAllByHoSoId(hoSo.getIdHoSo())
            .stream()
            .sorted((nv1, nv2) -> Integer.compare(nv1.getThuTuNguyenVong(), nv2.getThuTuNguyenVong()))
            .collect(Collectors.toList());
        
        // Xét từng nguyện vọng theo thứ tự
        for (int i = 0; i < dsNguyenVong.size(); i++) {
            NguyenVong nv = dsNguyenVong.get(i);
            
            // Lấy thông tin ngành
            Nganh nganh = nganhDAO.getNganhById(nv.getIdNganh());
            if (nganh == null) {
                nv.setTrangThai("TRUOT");
                nguyenVongDAO.updateNguyenVong(nv);
                continue;
            }
            
            // Xét tuyển theo phương thức
            boolean trungTuyen = false;
            switch (nv.getPhuongThucXetTuyen()) {
                case "Học bạ":
                    trungTuyen = xetHocBa(hoSo, nganh);
                    break;
                case "Điểm thi THPT":
                    trungTuyen = xetDiemThiTHPT(hoSo, nganh);
                    break;
                default:
                    nv.setTrangThai("TRUOT");
                    nguyenVongDAO.updateNguyenVong(nv);
                    continue;
            }
            
            // Nếu trúng tuyển
            if (trungTuyen) {
                // Đánh dấu tất cả nguyện vọng sau là TRUOT
                for (int j = i + 1; j < dsNguyenVong.size(); j++) {
                    NguyenVong nvSau = dsNguyenVong.get(j);
                    nvSau.setTrangThai("TRUOT");
                    nguyenVongDAO.updateNguyenVong(nvSau);
                }
                
                // Kiểm tra lại các nguyện vọng trước đó
                for (int k = 0; k < i; k++) {
                    NguyenVong nvTruoc = dsNguyenVong.get(k);
                    if ("TRUNG_TUYEN".equals(nvTruoc.getTrangThai())) {
                        // Nếu có nguyện vọng trước đã trúng tuyển,
                        // thì đánh dấu nguyện vọng hiện tại là TRUOT
                        nv.setTrangThai("TRUOT");
                        nguyenVongDAO.updateNguyenVong(nv);
                        // Và đánh dấu lại các nguyện vọng sau nvTruoc là TRUOT
                        for (int m = k + 1; m < dsNguyenVong.size(); m++) {
                            NguyenVong nvSauTruoc = dsNguyenVong.get(m);
                            if (nvSauTruoc.getThuTuNguyenVong() > nvTruoc.getThuTuNguyenVong()) {
                                nvSauTruoc.setTrangThai("TRUOT");
                                nguyenVongDAO.updateNguyenVong(nvSauTruoc);
                            }
                        }
                        break;
                    }
                }
                
                // Nếu không có nguyện vọng trước nào trúng tuyển
                if (!"TRUOT".equals(nv.getTrangThai())) {
                    nv.setTrangThai("TRUNG_TUYEN");
                    nguyenVongDAO.updateNguyenVong(nv);
                }
                
                // Thoát vòng lặp vì đã có nguyện vọng trúng tuyển
                break;
            } else {
                nv.setTrangThai("TRUOT");
                nguyenVongDAO.updateNguyenVong(nv);
            }
        }
    }

    private boolean daTrungTuyenNguyenVongTruocDo(int idHoSo, int thuTuHienTai) throws SQLException {
        List<NguyenVong> dsNguyenVong = nguyenVongDAO.getAllByHoSoId(idHoSo);
        return dsNguyenVong.stream()
            .anyMatch(nv -> nv.getThuTuNguyenVong() < thuTuHienTai && "TRUNG_TUYEN".equals(nv.getTrangThai()));
    }

    private boolean xetHocBa(HoSoDangKy hoSo, Nganh nganh) throws SQLException {
        // Lấy danh sách nguyện vọng cùng ngành cùng phương thức
        List<NguyenVong> dsNguyenVong = nguyenVongDAO.getAllByNganhAndPhuongThuc(nganh.getIdNganh(), "Học bạ");
        
        // Tính chỉ tiêu học bạ (30% tổng chỉ tiêu)
        int chiTieuHocBa = (int) Math.round(nganh.getChiTieu() * 0.3);
        
        // Sắp xếp theo điểm học bạ giảm dần
        dsNguyenVong.sort((nv1, nv2) -> {
            HoSoDangKy hs1 = hoSoDangKyDAO.getHoSoByStudentId(nv1.getIdHoSo());
			HoSoDangKy hs2 = hoSoDangKyDAO.getHoSoByStudentId(nv2.getIdHoSo());
			return Float.compare(hs2.getDiemHocBa(), hs1.getDiemHocBa());
        });
        
        // Lấy top điểm cao nhất
        List<NguyenVong> dsTrungTuyen = new ArrayList<>();
        for (NguyenVong nv : dsNguyenVong) {
            if (dsTrungTuyen.size() >= chiTieuHocBa) break;
            if ("TRUNG_TUYEN".equals(nv.getTrangThai())) continue;
            
            dsTrungTuyen.add(nv);
        }
        
        // Kiểm tra xem hồ sơ hiện tại có trong danh sách trúng tuyển không
        return dsTrungTuyen.stream()
            .anyMatch(nv -> nv.getIdHoSo() == hoSo.getIdHoSo());
    }

    private boolean xetDiemThiTHPT(HoSoDangKy hoSo, Nganh nganh) throws SQLException {
        // Lấy danh sách nguyện vọng cùng ngành cùng phương thức
        List<NguyenVong> dsNguyenVong = nguyenVongDAO.getAllByNganhAndPhuongThuc(nganh.getIdNganh(), "Điểm thi THPT");
        
        // Tính chỉ tiêu thi THPT (70% tổng chỉ tiêu nếu có cả học bạ, 100% nếu chỉ có thi THPT)
        int chiTieuThiTHPT;
        if (nganh.getPhuongThuc().contains("Học bạ")) {
            chiTieuThiTHPT = nganh.getChiTieu() - (int) Math.round(nganh.getChiTieu() * 0.3);
        } else {
            chiTieuThiTHPT = nganh.getChiTieu();
        }
        
//        // Sắp xếp theo điểm thi THPT giảm dần
//        if (dsNguyenVong != null && dsNguyenVong.size() > 1) {
//            dsNguyenVong.sort((nv1, nv2) -> {
//                HoSoDangKy hs1 = hoSoDangKyDAO.getHoSoByStudentId(nv1.getIdHoSo());
//                HoSoDangKy hs2 = hoSoDangKyDAO.getHoSoByStudentId(nv2.getIdHoSo());
//                return Float.compare(hs2.getDiemThi(), hs1.getDiemThi());
//            });
//        }
        
        // Lấy top điểm cao nhất
        List<NguyenVong> dsTrungTuyen = new ArrayList<>();
        for (NguyenVong nv : dsNguyenVong) {
            if (dsTrungTuyen.size() >= chiTieuThiTHPT) break;
            if ("TRUNG_TUYEN".equals(nv.getTrangThai())) continue;
            
            dsTrungTuyen.add(nv);
        }
        
        // Kiểm tra xem hồ sơ hiện tại có trong danh sách trúng tuyển không
        return dsTrungTuyen.stream()
            .anyMatch(nv -> nv.getIdHoSo() == hoSo.getIdHoSo());
    }
}