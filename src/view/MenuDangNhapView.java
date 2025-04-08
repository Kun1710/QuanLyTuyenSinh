package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;
import controller.MenuDangNhapController;

public class MenuDangNhapView extends JFrame {

    private JPanel contentPane;
    private JTextField field_taikhoan;
    private JPasswordField field_password;
    private MenuDangNhapController controller;
    
//    Hàm main để fix bug nếu cần:
    
//    public static void main(String[] args) {
//        EventQueue.invokeLater(() -> {
//            try {
//                MenuDangNhapView frame = new MenuDangNhapView();
//                frame.setVisible(true);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//    }

    public MenuDangNhapView() {
        controller = new MenuDangNhapController(this);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 450);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(253, 245, 230));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitle = new JLabel("HỆ THỐNG TUYỂN SINH");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblTitle.setForeground(new Color(0, 0, 255));
        lblTitle.setBounds(70, 25, 253, 60);
        contentPane.add(lblTitle);

        JLabel lblTaiKhoan = new JLabel("Tài Khoản");
        lblTaiKhoan.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblTaiKhoan.setBounds(30, 100, 85, 30);
        contentPane.add(lblTaiKhoan);

        field_taikhoan = new JTextField();
        field_taikhoan.setFont(new Font("Tahoma", Font.PLAIN, 15));
        field_taikhoan.setBounds(115, 100, 200, 26);
        contentPane.add(field_taikhoan);

        JLabel lblMatKhau = new JLabel("Mật Khẩu");
        lblMatKhau.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblMatKhau.setBounds(30, 140, 85, 30);
        contentPane.add(lblMatKhau);

        field_password = new JPasswordField();
        field_password.setFont(new Font("Tahoma", Font.PLAIN, 15));
        field_password.setBounds(115, 140, 200, 27);
        contentPane.add(field_password);

        JButton button_dangKy = new JButton("Đăng ký");
        button_dangKy.setBackground(new Color(0, 255, 127));
        button_dangKy.setForeground(new Color(255, 255, 255));
        button_dangKy.setBounds(15, 200, 100, 24);
        button_dangKy.addActionListener(e -> controller.moDangKy());
        contentPane.add(button_dangKy);

        JButton button_dangNhap = new JButton("Đăng nhập");
        button_dangNhap.setBackground(new Color(30, 144, 255));
        button_dangNhap.setForeground(new Color(211, 211, 211));
        button_dangNhap.setBounds(125, 200, 100, 24);
        button_dangNhap.addActionListener(e -> controller.xulyDangnhap());
        contentPane.add(button_dangNhap);

        JButton button_quenMK = new JButton("Quên mật khẩu?");
        button_quenMK.setBackground(new Color(250, 128, 114));
        button_quenMK.setBounds(235, 200, 135, 23);
        button_quenMK.addActionListener(e -> controller.moQuenMatKhau());
        contentPane.add(button_quenMK);

        URL urlicon = MenuDangNhapView.class.getResource("icon_education.png");
        if (urlicon != null) {
            Image img = Toolkit.getDefaultToolkit().createImage(urlicon);
            this.setIconImage(img);
        }

        setTitle("Hệ Thống Tuyển Sinh");
        setSize(400, 300);
        setLocationRelativeTo(null);
    }

    public String getTaiKhoan() {
        return field_taikhoan.getText().trim();
    }

    public String getMatKhau() {
        return new String(field_password.getPassword()).trim();
    }
}
