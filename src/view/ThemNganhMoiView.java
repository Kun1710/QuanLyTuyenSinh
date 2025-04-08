package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import model.KhoiXetTuyen;
import model.Nganh;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

public class ThemNganhMoiView extends JFrame {
    private JPanel contentPane;
    private JLabel lblNgnhMi;
    private JTextField tenNganh;
    private JTextField maNganh;
    private JTextField soLuong;
    private JTextField uuTien;
    private JButton btnThm;
    private JButton btnQuayLai;
    private JComboBox<KhoiXetTuyen> khoi;
    private JRadioButton hocBa;
    private JRadioButton diemThiTHPT;
    private JRadioButton tuyenThang;
	/**
	 * Launch the application.
	 */
    private Nganh currentNganh;
    private int idTruong;

    // Constructor mới
    public ThemNganhMoiView(int idTruong, Nganh nganh) {
        this();
        this.idTruong = idTruong;
        this.currentNganh = nganh;
        
        // Nếu là chế độ sửa
        if (nganh != null) {
            lblNgnhMi.setText(">> CHỈNH SỬA NGÀNH");
            setEditMode(true);
            populateData(nganh);
        }
    }

    // Phương thức điền dữ liệu vào form khi sửa
    private void populateData(Nganh nganh) {
        tenNganh.setText(nganh.getTenNganh());
        maNganh.setText(nganh.getMaNganh());
        soLuong.setText(String.valueOf(nganh.getChiTieu()));
        uuTien.setText(nganh.getUuTien());
        
        // Phương thức xét tuyển
        String phuongThuc = nganh.getPhuongThuc();
        if (phuongThuc.contains("Học Bạ")) hocBa.setSelected(true);
        if (phuongThuc.contains("Điểm Thi THPT")) diemThiTHPT.setSelected(true);
        if (phuongThuc.contains("Tuyển Thẳng")) tuyenThang.setSelected(true);
        
        // Khối xét tuyển
        for (int i = 0; i < khoi.getItemCount(); i++) {
            if (khoi.getItemAt(i).toString().equals(nganh.getKhoiXetTuyen())) {
                khoi.setSelectedIndex(i);
                break;
            }
        }
    }

    // Thêm getter cho idTruong và currentNganh
    public int getIdTruong() {
        return idTruong;
    }

    public Nganh getCurrentNganh() {
        return currentNganh;
    }
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ThemNganhMoiView frame = new ThemNganhMoiView();
//					frame.setVisible(true);
//					new ThemNganhMoiView();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public ThemNganhMoiView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ThemNganhMoiView.class.getResource("/view/icon_education.png")));
		setTitle("Hệ Thống Tuyển Sinh - Ngành");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 350);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(245, 255, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTnNgnh = new JLabel("TÊN NGÀNH");
		lblTnNgnh.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTnNgnh.setBounds(10, 30, 110, 20);
		contentPane.add(lblTnNgnh);
		
		JLabel lblMNgnh = new JLabel("MÃ NGÀNH");
		lblMNgnh.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMNgnh.setBounds(10, 65, 110, 20);
		contentPane.add(lblMNgnh);
		
		JLabel lblSLng = new JLabel("SỐ LƯỢNG");
		lblSLng.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSLng.setBounds(10, 100, 110, 20);
		contentPane.add(lblSLng);
		
		JLabel lblPhngThc = new JLabel("PHƯƠNG THỨC");
		lblPhngThc.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPhngThc.setBounds(10, 135, 110, 20);
		contentPane.add(lblPhngThc);
		
		JLabel lblKhi = new JLabel("KHỐI");
		lblKhi.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblKhi.setBounds(10, 170, 110, 20);
		contentPane.add(lblKhi);
		
		JLabel aaaa = new JLabel("ƯU TIÊN");
		aaaa.setFont(new Font("Tahoma", Font.BOLD, 11));
		aaaa.setBounds(10, 205, 110, 20);
		contentPane.add(aaaa);
		
		tenNganh = new JTextField();
		tenNganh.setBounds(100, 30, 330, 25);
		contentPane.add(tenNganh);
		
		maNganh = new JTextField();
		maNganh.setBounds(100, 65, 330, 25);
		contentPane.add(maNganh);
		
		soLuong = new JTextField();
		soLuong.setBounds(100, 100, 330, 25);
		contentPane.add(soLuong);
		
		uuTien = new JTextField();
		uuTien.setBounds(100, 205, 330, 25);
		contentPane.add(uuTien);
		
		lblNgnhMi = new JLabel(">> NGÀNH MỚI");
		lblNgnhMi.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNgnhMi.setBounds(0, 0, 227, 20);
		contentPane.add(lblNgnhMi);
		
		btnThm = new JButton("THÊM");
        btnThm.setIcon(new ImageIcon(ThemNganhMoiView.class.getResource("/view/add.png")));
        btnThm.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnThm.setBackground(new Color(127, 255, 0));
        btnThm.setBounds(240, 250, 150, 30);
        contentPane.add(btnThm);
        
        btnQuayLai = new JButton("QUAY LẠI");
        btnQuayLai.setIcon(new ImageIcon(ThemNganhMoiView.class.getResource("/view/back.png")));
        btnQuayLai.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnQuayLai.setBackground(new Color(216, 191, 216));
        btnQuayLai.setBounds(40, 250, 150, 30);
        contentPane.add(btnQuayLai);
        
        khoi = new JComboBox<>();
        khoi.setBounds(100, 170, 330, 25);
        contentPane.add(khoi);
        
        hocBa = new JRadioButton("Học Bạ");
        hocBa.setBounds(100, 134, 109, 23);
        contentPane.add(hocBa);
        
        diemThiTHPT = new JRadioButton("Điểm Thi THPT");
        diemThiTHPT.setBounds(211, 134, 109, 23);
        contentPane.add(diemThiTHPT);
        
        tuyenThang = new JRadioButton("Tuyển Thẳng");
        tuyenThang.setBounds(321, 134, 109, 23);
        contentPane.add(tuyenThang);
//        ButtonGroup phuongThucGroup = new ButtonGroup();
//        phuongThucGroup.add(hocBa);
//        phuongThucGroup.add(diemThiTHPT);
//        phuongThucGroup.add(tuyenThang);

		setLocationRelativeTo(null);
	}
    // Thêm các getter methods
	public String getSelectedPhuongThuc() {
	    StringBuilder phuongThuc = new StringBuilder();
	    
	    if (hocBa.isSelected()) {
	        phuongThuc.append("Học bạ");
	    }
	    if (diemThiTHPT.isSelected()) {
	        if (phuongThuc.length() > 0) phuongThuc.append(", ");
	        phuongThuc.append("Điểm thi THPT");
	    }
	    if (tuyenThang.isSelected()) {
	        if (phuongThuc.length() > 0) phuongThuc.append(", ");
	        phuongThuc.append("Tuyển thẳng");
	    }
	    
	    return phuongThuc.toString();
	}
    public JTextField getTenNganh() {
        return tenNganh;
    }

    public JTextField getMaNganh() {
        return maNganh;
    }

    public JTextField getSoLuong() {
        return soLuong;
    }

    public JTextField getUuTien() {
        return uuTien;
    }

    public JButton getBtnThem() {
        return btnThm;
    }

    public JButton getBtnQuayLai() {
        return btnQuayLai;
    }

    public JComboBox<KhoiXetTuyen> getKhoiComboBox() {
        return khoi;
    }

    public JRadioButton getHocBaRadio() {
        return hocBa;
    }

    public JRadioButton getDiemThiTHPTRadio() {
        return diemThiTHPT;
    }

    public JRadioButton getTuyenThangRadio() {
        return tuyenThang;
    }
 // Thêm phương thức để set editable cho mã ngành
    public void setMaNganhEditable(boolean editable) {
        maNganh.setEditable(editable);
    }

    // Thay đổi text nút khi ở chế độ sửa
    public void setEditMode(boolean isEdit) {
        if (isEdit) {
            btnThm.setText("CẬP NHẬT");
        } else {
            btnThm.setText("THÊM");
        }
    }
 // Thêm các phương thức getter/setter cần thiết
    public JLabel getLblNgnhMi() {
        return lblNgnhMi;
    }

    public void setLblNgnhMiText(String text) {
        lblNgnhMi.setText(text);
    }
}
