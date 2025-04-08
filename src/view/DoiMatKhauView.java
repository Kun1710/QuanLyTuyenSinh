package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.DoiMatKhauController;

import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;

public class DoiMatKhauView extends JFrame {

    private JPanel contentPane;
    private JPasswordField matKhauCu;
    private JPasswordField matKhauMoi;
    private JPasswordField matKhauMoi_2;
    private JButton button_thoat;
    private JButton button_xacNhan;

//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    doiMatKhauView frame = new doiMatKhauView();
//                    frame.setVisible(true);
//                    new doiMatKhauController(frame);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    public DoiMatKhauView() {
        setTitle("Hệ Thống Tuyển Sinh - Đổi Mật Khẩu");
        setIconImage(Toolkit.getDefaultToolkit().getImage(DoiMatKhauView.class.getResource("/view/icon_education.png")));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(211, 211, 211));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        
        JLabel lblMtKhuC = new JLabel("Mật Khẩu Cũ");
        lblMtKhuC.setForeground(Color.BLACK);
        lblMtKhuC.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblMtKhuC.setBounds(10, 30, 120, 30);
        contentPane.add(lblMtKhuC);
        
        JLabel lblMtKhuMi = new JLabel("Mật Khẩu Mới");
        lblMtKhuMi.setForeground(Color.BLACK);
        lblMtKhuMi.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblMtKhuMi.setBounds(10, 70, 120, 30);
        contentPane.add(lblMtKhuMi);
        
        JLabel lblXcNhnMt = new JLabel("Xác Nhận Mật Khẩu Mới");
        lblXcNhnMt.setForeground(Color.BLACK);
        lblXcNhnMt.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblXcNhnMt.setBounds(10, 110, 160, 30);
        contentPane.add(lblXcNhnMt);
        
        button_thoat = new JButton("THOÁT");
        button_thoat.setIcon(new ImageIcon(DoiMatKhauView.class.getResource("/view/exit_1.png")));
        button_thoat.setForeground(Color.BLACK);
        button_thoat.setFont(new Font("Tahoma", Font.BOLD, 11));
        button_thoat.setBackground(Color.RED);
        button_thoat.setBounds(50, 190, 90, 27);
        contentPane.add(button_thoat);
        
        button_xacNhan = new JButton("XÁC NHẬN");
        button_xacNhan.setIcon(new ImageIcon(DoiMatKhauView.class.getResource("/view/exchange.png")));
        button_xacNhan.setForeground(Color.BLACK);
        button_xacNhan.setFont(new Font("Tahoma", Font.BOLD, 11));
        button_xacNhan.setBackground(new Color(0, 255, 0));
        button_xacNhan.setBounds(250, 190, 120, 27);
        contentPane.add(button_xacNhan);
        
        matKhauCu = new JPasswordField();
        matKhauCu.setFont(new Font("Tahoma", Font.PLAIN, 15));
        matKhauCu.setBounds(190, 30, 220, 25);
        contentPane.add(matKhauCu);
        
        matKhauMoi = new JPasswordField();
        matKhauMoi.setFont(new Font("Tahoma", Font.PLAIN, 15));
        matKhauMoi.setBounds(190, 70, 220, 25);
        contentPane.add(matKhauMoi);
        
        matKhauMoi_2 = new JPasswordField();
        matKhauMoi_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
        matKhauMoi_2.setBounds(190, 110, 220, 25);
        contentPane.add(matKhauMoi_2);
    }

    // Các getter methods cho controller
    public JPasswordField getMatKhauCu() {
        return matKhauCu;
    }

    public JPasswordField getMatKhauMoi() {
        return matKhauMoi;
    }

    public JPasswordField getMatKhauMoi2() {
        return matKhauMoi_2;
    }

    public JButton getButtonThoat() {
        return button_thoat;
    }

    public JButton getButtonXacNhan() {
        return button_xacNhan;
    }
}