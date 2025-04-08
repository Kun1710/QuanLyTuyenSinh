package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;

public class ChiTietHocSinhView extends JFrame {

    private JPanel contentPane;
    private JTextField hoTen;
    private JTextField cccd;
    private JTextField diachi;
    private JTextField dantoc;
    private JTextField gioitinh;
    private JComboBox<Integer> day;
    private JComboBox<Integer> month;
    private JComboBox<Integer> year;
    private JLabel lblThngTin;
    private JButton thoat;
    private JButton update;
    private JButton diemHB;
    private JButton diemTHPT;
    private JButton chungchi;

    public JTextField getHoTenField() {
        return hoTen;
    }

    public JTextField getCccdField() {
        return cccd;
    }

    public JTextField getDiaChiField() {
        return diachi;
    }

    public JTextField getDanTocField() {
        return dantoc;
    }

    public JTextField getGioiTinhField() {
        return gioitinh;
    }

    public JComboBox<Integer> getDayCombo() {
        return day;
    }

    public JComboBox<Integer> getMonthCombo() {
        return month;
    }

    public JComboBox<Integer> getYearCombo() {
        return year;
    }

    public JLabel getTitleLabel() {
        return lblThngTin;
    }

    public void addThoatButtonListener(ActionListener listener) {
        thoat.addActionListener(listener);
    }

    public void addUpdateButtonListener(ActionListener listener) {
        update.addActionListener(listener);
    }
    
    public JButton getDiemHB() {
		return diemHB;
	}

	public JButton getDiemTHPT() {
		return diemTHPT;
	}

	public JButton getChungchi() {
		return chungchi;
	}

	/**
     * Launch the application.
     */
//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    ChiTietHocSinhView frame = new ChiTietHocSinhView();
//                    frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    /**
     * Create the frame.
     */
    public ChiTietHocSinhView() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(ChiTietHocSinhView.class.getResource("/view/icon_education.png")));
        setTitle("Hệ Thống Tuyển Sinh - Thông Tin Chi Tiết");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 450);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(230, 230, 250));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        
        JLabel label = new JLabel("HỌ TÊN");
        label.setFont(new Font("Tahoma", Font.BOLD, 11));
        label.setBounds(10, 40, 110, 20);
        contentPane.add(label);
        
        JLabel lblCccd = new JLabel("CCCD");
        lblCccd.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblCccd.setBounds(10, 70, 110, 20);
        contentPane.add(lblCccd);
        
        JLabel lblNgySinh = new JLabel("Ngày Sinh");
        lblNgySinh.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNgySinh.setBounds(10, 100, 110, 20);
        contentPane.add(lblNgySinh);
        
        JLabel lblaCh = new JLabel("Địa Chỉ");
        lblaCh.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblaCh.setBounds(10, 135, 110, 20);
        contentPane.add(lblaCh);
        
        JLabel lblDnTc = new JLabel("Dân Tộc");
        lblDnTc.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblDnTc.setBounds(10, 170, 110, 20);
        contentPane.add(lblDnTc);
        
        JLabel lblGiiTnh = new JLabel("Giới Tính");
        lblGiiTnh.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblGiiTnh.setBounds(10, 200, 110, 20);
        contentPane.add(lblGiiTnh);
        
        JLabel lblimHcB = new JLabel("Điểm Học Bạ");
        lblimHcB.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblimHcB.setBounds(10, 240, 110, 20);
        contentPane.add(lblimHcB);
        
        JLabel lblimThiThpt = new JLabel("Điểm Thi THPT");
        lblimThiThpt.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblimThiThpt.setBounds(10, 275, 110, 20);
        contentPane.add(lblimThiThpt);
        
        JLabel lblChngCh = new JLabel("Chứng Chỉ");
        lblChngCh.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblChngCh.setBounds(10, 310, 110, 20);
        contentPane.add(lblChngCh);
        
        lblThngTin = new JLabel(">> Thông Tin Của ");
        lblThngTin.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblThngTin.setBounds(5, 5, 422, 20);
        contentPane.add(lblThngTin);
        
        hoTen = new JTextField();
        hoTen.setBounds(110, 40, 300, 25);
        contentPane.add(hoTen);
        
        cccd = new JTextField();
        cccd.setEditable(false); // Không cho phép chỉnh sửa CCCD
        cccd.setBounds(110, 70, 300, 25);
        contentPane.add(cccd);
        
        diachi = new JTextField();
        diachi.setBounds(110, 135, 300, 25);
        contentPane.add(diachi);
        
        dantoc = new JTextField();
        dantoc.setBounds(110, 170, 300, 25);
        contentPane.add(dantoc);
        
        gioitinh = new JTextField();
        gioitinh.setEditable(false);
        gioitinh.setBounds(110, 200, 300, 25);
        contentPane.add(gioitinh);
        
        diemHB = new JButton("XEM");
        diemHB.setIcon(new ImageIcon(ChiTietHocSinhView.class.getResource("/view/eye.png")));
        diemHB.setFont(new Font("Tahoma", Font.BOLD, 11));
        diemHB.setBackground(new Color(240, 255, 240));
        diemHB.setBounds(110, 240, 122, 30);
        contentPane.add(diemHB);
        
        diemTHPT = new JButton("XEM");
        diemTHPT.setIcon(new ImageIcon(ChiTietHocSinhView.class.getResource("/view/eye.png")));
        diemTHPT.setFont(new Font("Tahoma", Font.BOLD, 11));
        diemTHPT.setBackground(new Color(255, 240, 245));
        diemTHPT.setBounds(110, 270, 122, 30);
        contentPane.add(diemTHPT);
        
        chungchi = new JButton("XEM");
        chungchi.setIcon(new ImageIcon(ChiTietHocSinhView.class.getResource("/view/eye.png")));
        chungchi.setFont(new Font("Tahoma", Font.BOLD, 11));
        chungchi.setBackground(new Color(255, 228, 225));
        chungchi.setBounds(110, 300, 122, 30);
        contentPane.add(chungchi);
        
        day = new JComboBox<Integer>();
        day.setBackground(new Color(255, 250, 240));
        day.setBounds(110, 100, 60, 25);
        contentPane.add(day);
        
        month = new JComboBox<Integer>();
        month.setBackground(new Color(255, 250, 240));
        month.setBounds(180, 100, 60, 25);
        contentPane.add(month);
        
        year = new JComboBox<Integer>();
        year.setBackground(new Color(255, 250, 240));
        year.setBounds(250, 100, 60, 25);
        contentPane.add(year);
        
        thoat = new JButton("THOÁT");
        thoat.setIcon(new ImageIcon(ChiTietHocSinhView.class.getResource("/view/exit_1.png")));
        thoat.setFont(new Font("Tahoma", Font.BOLD, 11));
        thoat.setBackground(new Color(255, 127, 80));
        thoat.setBounds(39, 362, 122, 30);
        contentPane.add(thoat);
        
        update = new JButton("CẬP NHẬT");
        update.setIcon(new ImageIcon(ChiTietHocSinhView.class.getResource("/view/update.png")));
        update.setFont(new Font("Tahoma", Font.BOLD, 11));
        update.setBackground(new Color(127, 255, 0));
        update.setBounds(266, 362, 122, 30);
        contentPane.add(update);
    }
}