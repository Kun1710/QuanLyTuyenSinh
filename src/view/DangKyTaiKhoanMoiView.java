package view;

import java.awt.*;
import javax.swing.*;
import java.net.URL;
import javax.swing.border.EmptyBorder;

public class DangKyTaiKhoanMoiView extends JFrame {
//	public static void main(String[] args) {
//	    EventQueue.invokeLater(() -> {
//	        try {
//	            dangKyView view = new dangKyView();
//	            new dangkyController(view); 
//	            view.setVisible(true);
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//	    });
//	}
    private JPanel contentPane;
    private JTextField taiKhoan;
    private JTextField gmail;
    private JPasswordField matKhau;
    private JPasswordField xacNhanMK;
    private JRadioButton hocSinh;
    private JRadioButton truong;
    private JButton dangky;
    private JButton thoat;

    public DangKyTaiKhoanMoiView() {
        setTitle("Hệ Thống Tuyển Sinh - Đăng Ký");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(238, 232, 170));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        initComponents();
        setIcon();
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        JLabel lblNewLabel_2 = new JLabel("Mật khẩu");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNewLabel_2.setBounds(17, 50, 80, 30);
        contentPane.add(lblNewLabel_2);
        
        JLabel lblTiKhon = new JLabel("Tài khoản");
        lblTiKhon.setForeground(Color.BLACK);
        lblTiKhon.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTiKhon.setBounds(17, 15, 80, 30);
        contentPane.add(lblTiKhon);
        
        dangky = new JButton("ĐĂNG KÝ");
        dangky.setIcon(new ImageIcon(DangKyTaiKhoanMoiView.class.getResource("/view/update.png")));
        dangky.setForeground(new Color(255, 255, 0));
        dangky.setBackground(new Color(0, 255, 0));
        dangky.setFont(new Font("Tahoma", Font.BOLD, 11));
        dangky.setBounds(250, 225, 110, 27);
        contentPane.add(dangky);
        
        thoat = new JButton("THOÁT");
        thoat.setIcon(new ImageIcon(DangKyTaiKhoanMoiView.class.getResource("/view/exit_1.png")));
        thoat.setForeground(new Color(0, 0, 0));
        thoat.setBackground(new Color(250, 128, 114));
        thoat.setFont(new Font("Tahoma", Font.BOLD, 11));
        thoat.setBounds(40, 225, 90, 27);
        contentPane.add(thoat);
        
        JLabel lblNhpLiMt = new JLabel("Nhập lại mật khẩu");
        lblNhpLiMt.setForeground(Color.BLACK);
        lblNhpLiMt.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblNhpLiMt.setBounds(17, 90, 130, 30);
        contentPane.add(lblNhpLiMt);
        
        JLabel lblGmailXcNhn = new JLabel("Gmail xác nhận");
        lblGmailXcNhn.setForeground(Color.BLACK);
        lblGmailXcNhn.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblGmailXcNhn.setBounds(17, 130, 110, 30);
        contentPane.add(lblGmailXcNhn);
        
        taiKhoan = new JTextField();
        taiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 15));
        taiKhoan.setColumns(10);
        taiKhoan.setBounds(150, 15, 220, 25);
        contentPane.add(taiKhoan);
        
        gmail = new JTextField();
        gmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
        gmail.setColumns(10);
        gmail.setBounds(150, 130, 220, 25);
        contentPane.add(gmail);
        
        matKhau = new JPasswordField();
        matKhau.setFont(new Font("Tahoma", Font.PLAIN, 15));
        matKhau.setBounds(150, 50, 220, 25);
        contentPane.add(matKhau);
        
        xacNhanMK = new JPasswordField();
        xacNhanMK.setFont(new Font("Tahoma", Font.PLAIN, 15));
        xacNhanMK.setBounds(150, 90, 220, 25);
        contentPane.add(xacNhanMK);
        
        JLabel lblLoi = new JLabel("Tài khoản");
        lblLoi.setForeground(Color.BLACK);
        lblLoi.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblLoi.setBounds(17, 170, 110, 30);
        contentPane.add(lblLoi);
        
        hocSinh = new JRadioButton("Học Sinh");
        hocSinh.setFont(new Font("Tahoma", Font.BOLD, 11));
        hocSinh.setBounds(150, 170, 100, 23);
        contentPane.add(hocSinh);
        
        truong = new JRadioButton("Trường");
        truong.setFont(new Font("Tahoma", Font.BOLD, 11));
        truong.setBounds(270, 170, 100, 23);
        contentPane.add(truong);
        
        ButtonGroup btn_loai = new ButtonGroup();
        btn_loai.add(hocSinh);
        btn_loai.add(truong);
    }

    private void setIcon() {
        URL urliconeducation = getClass().getResource("/view/icon_education.png");
        Image img = Toolkit.getDefaultToolkit().createImage(urliconeducation);
        this.setIconImage(img);
    }

    // Các phương thức getter và setter
    public JTextField getTaiKhoan() {
        return taiKhoan;
    }

    public JTextField getGmail() {
        return gmail;
    }

    public JPasswordField getMatKhau() {
        return matKhau;
    }

    public JPasswordField getXacNhanMK() {
        return xacNhanMK;
    }

    public JRadioButton getHocSinh() {
        return hocSinh;
    }

    public JRadioButton getTruong() {
        return truong;
    }

    public JButton getDangky() {
        return dangky;
    }

    public JButton getThoat() {
        return thoat;
    }
}