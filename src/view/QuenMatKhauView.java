package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class QuenMatKhauView extends JFrame {
    private JPanel contentPane;
    private JTextField taiKhoan;
    private JTextField gmail;
    private JButton button_thoat;
    private JButton button_guiMK;

    public QuenMatKhauView() {
        setTitle("Hệ Thống Tuyển Sinh - Quên Mật Khẩu");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(192, 192, 192));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        initializeComponents();
        setIcon();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initializeComponents() {
        JLabel lblTiKhon = new JLabel("Tài khoản");
        lblTiKhon.setBounds(17, 50, 70, 30);
        lblTiKhon.setForeground(Color.BLACK);
        lblTiKhon.setFont(new Font("Tahoma", Font.BOLD, 12));
        contentPane.add(lblTiKhon);
        
        JLabel lblGmai = new JLabel("Gmail");
        lblGmai.setForeground(Color.BLACK);
        lblGmai.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblGmai.setBounds(17, 100, 70, 30);
        contentPane.add(lblGmai);
        
        button_thoat = new JButton("THOÁT");
        button_thoat.setIcon(new ImageIcon(QuenMatKhauView.class.getResource("/view/exit_1.png")));
        button_thoat.setForeground(Color.BLACK);
        button_thoat.setFont(new Font("Tahoma", Font.BOLD, 11));
        button_thoat.setBackground(Color.RED);
        button_thoat.setBounds(33, 173, 90, 27);
        contentPane.add(button_thoat);
        
        button_guiMK = new JButton("GỬI MẬT KHẨU MỚI");
        button_guiMK.setForeground(new Color(255, 182, 193));
        button_guiMK.setFont(new Font("Tahoma", Font.BOLD, 11));
        button_guiMK.setBackground(new Color(0, 191, 255));
        button_guiMK.setBounds(158, 173, 200, 27);
        contentPane.add(button_guiMK);
        
        taiKhoan = new JTextField();
        taiKhoan.setFont(new Font("Tahoma", Font.PLAIN, 15));
        taiKhoan.setColumns(10);
        taiKhoan.setBounds(110, 50, 220, 25);
        contentPane.add(taiKhoan);
        
        gmail = new JTextField();
        gmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
        gmail.setColumns(10);
        gmail.setBounds(110, 100, 220, 25);
        contentPane.add(gmail);
    }

    private void setIcon() {
        URL urliconeducation = MenuDangNhapView.class.getResource("icon_education.png");
        Image img = Toolkit.getDefaultToolkit().createImage(urliconeducation);
        this.setIconImage(img);
    }

    public JButton getButton_thoat() {
        return button_thoat;
    }

    public JButton getButton_guiMK() {
        return button_guiMK;
    }

    public JTextField getTaiKhoan() {
        return taiKhoan;
    }

    public JTextField getGmail() {
        return gmail;
    }
}