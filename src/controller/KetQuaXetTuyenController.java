package controller;

import dao.*;
import model.*;
import view.KetQuaTuyenSinhView;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.*;

public class KetQuaXetTuyenController {
    private KetQuaTuyenSinhView view;
    private DefaultTableModel model;
    private NganhDAO nganhDAO;
    private NguyenVongDAO nguyenVongDAO;
    private HocSinhDAO hocSinhDAO;
    private HoSoDangKyDAO hoSoDangKyDAO;

    public KetQuaXetTuyenController(KetQuaTuyenSinhView view) {
        this.view = view;
        this.nganhDAO = new NganhDAO();
        this.nguyenVongDAO = new NguyenVongDAO();
        this.hocSinhDAO = new HocSinhDAO();
        this.hoSoDangKyDAO = new HoSoDangKyDAO();
        initTableModel();
        view.getButton_quayLai().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
    }

    public void hienThiKetQuaNganh(int idNganh) {
        try {
            Nganh nganh = nganhDAO.getNganhById(idNganh);
            if (nganh == null) {
                JOptionPane.showMessageDialog(view, "Không tìm thấy thông tin ngành");
                return;
            }

            List<NguyenVong> dsTrungTuyen = nguyenVongDAO.getNguyenVongTrungTuyenByNganh(idNganh);
            List<HocSinh> dsHocSinhTrungTuyen = new ArrayList<>();
            
            for (NguyenVong nv : dsTrungTuyen) {
                HoSoDangKy hoSo = hoSoDangKyDAO.getHoSoByIdHS(nv.getIdHoSo());
                if (hoSo == null) {
                    System.err.println("Không tìm thấy hồ sơ cho nguyện vọng ID: " + nv.getIdNguyenVong());
                    continue;
                }
                
                HocSinh hs = hocSinhDAO.getHocSinhById(hoSo.getIdHS());
                if (hs == null) {
                    System.err.println("Không tìm thấy học sinh cho hồ sơ ID: " + hoSo.getIdHoSo());
                    continue;
                }
                
                dsHocSinhTrungTuyen.add(hs);
            }

            hienThiDanhSachTrungTuyen(dsHocSinhTrungTuyen, nganh);
            
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(view, "Lỗi khi tải dữ liệu: " + e.getMessage());
        }
    }
    private void initTableModel() {
    	 model = new DefaultTableModel() {
             @Override
             public boolean isCellEditable(int row, int column) {
                 return false;
             }
         };
         
         model.addColumn("STT");
         model.addColumn("Họ tên");
         model.addColumn("CCCD");
         model.addColumn("Ngày sinh");
         model.addColumn("Giới tính");
         model.addColumn("Dân tộc");
         model.addColumn("Địa chỉ");
         view.getTable().setModel(model);
        
    }
    private void hienThiDanhSachTrungTuyen(List<HocSinh> dsHocSinh, Nganh nganh) throws SQLException {
        for (int i = 0; i < dsHocSinh.size(); i++) {
            HocSinh hs = dsHocSinh.get(i);
            if (hs == null) {
                System.err.println("Học sinh null tại vị trí: " + i);
                continue;
            }
            
            model.addRow(new Object[]{
                i + 1,
                hs.getHoVaTen(),
                hs.getCCCD(),
                hs.getNgaySinh(),
                hs.isGioiTinh() ? "Nam" : "Nữ",
                hs.getDanToc(),
                hs.getDiaChi()
            });
        }
        
      view.get_laytenNganh().setText(">> Danh Sách Trúng Tuyển Ngành: " + nganh.getTenNganh() 
      + " - Chỉ tiêu: " + nganh.getChiTieu());
    }
}