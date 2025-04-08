package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.ChungChiDAO;
import dao.NguyenVongDAO;
import model.ChungChi;
import model.HoSoDangKy;
import model.HocSinh;
import model.NguyenVong;
import model.Truong;
import model.Nganh;
import view.DuyetTuyenThangView;

public class DuyetTuyenThangController {
    private DuyetTuyenThangView view;
    private HoSoDangKy hoSo;
    private HocSinh hs;
    private NguyenVong nv;
    private Truong truong;
    private Nganh nganh;
    private ChungChiDAO chungChiDAO;
    private NguyenVongDAO nguyenVongDAO;

    public DuyetTuyenThangController(DuyetTuyenThangView view, HoSoDangKy hoSo, HocSinh hs, 
            NguyenVong nv, Truong truong, Nganh nganh, List<ChungChi> dsChungChi)  {
        this.view = view;
        this.hoSo = hoSo;
        this.hs = hs;
        this.nv = nv;
        this.truong = truong;
        this.nganh = nganh;
        this.chungChiDAO = new ChungChiDAO();
        this.nguyenVongDAO = new NguyenVongDAO();
        
        initData(dsChungChi);
        initEvents();
    }

    private void initData(List<ChungChi> dsChungChi) {
        // Hiển thị thông tin cơ bản
        view.getHoTenField().setText(hs.getHoVaTen());
        view.getTruongField().setText(truong != null ? truong.getTenTruong() : "Không xác định");
        view.getNganhField().setText(nganh != null ? nganh.getTenNganh() : "Không xác định");
        
        // Load danh sách chứng chỉ
        loadChungChi(dsChungChi);
    }

    private void loadChungChi(List<ChungChi> dsChungChi) {
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        model.addColumn("STT");
        model.addColumn("Tên Chứng Chỉ");
        model.addColumn("Ngày Cấp");
        model.addColumn("Link Chứng Chỉ");
        
        if (dsChungChi != null && !dsChungChi.isEmpty()) {
            int stt = 1;
            for (ChungChi cc : dsChungChi) {
                model.addRow(new Object[]{
                    stt++,
                    cc.getTenChungChi(),
                    cc.getNgayCap(),
                    cc.getLinkChungChi()
                });
            }
        } else {
            model.addRow(new Object[]{"", "Không có chứng chỉ nào", "", ""});
        }
        
        view.getTableChungChi().setModel(model);
    }

    private void initEvents() {
        // Xử lý sự kiện nút Đậu
        view.getDauButton().addActionListener(e -> xuLyDau());
        
        // Xử lý sự kiện nút Rớt
        view.getRotButton().addActionListener(e -> xuLyRot());
        
        // Xử lý sự kiện nút Thoát
        view.getThoatButton().addActionListener(e -> view.dispose());
    }

    private void xuLyDau() {
        if (capNhatTrangThaiNguyenVong("TRUNG_TUYEN")) {
            JOptionPane.showMessageDialog(view, "Đã duyệt tuyển thẳng thành công!", 
                "Thành công", JOptionPane.INFORMATION_MESSAGE);
            view.dispose();
        } else {
            JOptionPane.showMessageDialog(view, "Có lỗi xảy ra khi duyệt!", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void xuLyRot() {
        if (capNhatTrangThaiNguyenVong("TRUOT")) {
            JOptionPane.showMessageDialog(view, "Đã từ chối tuyển thẳng!", 
                "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            view.dispose();
        } else {
            JOptionPane.showMessageDialog(view, "Có lỗi xảy ra khi từ chối!", 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean capNhatTrangThaiNguyenVong(String trangThai) {
        if (nv != null) {
            nv.setTrangThai(trangThai);
            return nguyenVongDAO.updateNguyenVong(nv);
        }
        return false;
    }
}