package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;

public class NganhView extends JFrame {

    private JPanel contentPane;
    private JButton btnAddNganh;
    private JButton btnListNganh;
    private JButton btnQuayLai;

    /**
     * Launch the application.
     */
//    public static void main(String[] args) {
//        EventQueue.invokeLater(() -> {
//            try {
//                nganhView frame = new nganhView();
//                frame.setVisible(true);
//                new controller.nganhController(frame);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//    }

    /**
     * Create the frame.
     */
    public NganhView() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(NganhView.class.getResource("/view/icon_education.png")));
        setTitle("Hệ Thống Tuyển Sinh - Quản Lý Ngành Học");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        
        initializeComponents();
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        contentPane = new JPanel();
        contentPane.setBackground(new Color(240, 248, 255)); // Màu nền xanh nhạt
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Tạo các nút
        btnAddNganh = createButton("/view/addnv.png", 69, 27, 82, 71, new Color(144, 238, 144)); // Xanh lá nhạt
        btnListNganh = createButton("/view/listnv.png", 244, 27, 75, 71, new Color(135, 206, 250)); // Xanh da trời nhạt
        btnQuayLai = createTextButton("QUAY LẠI", "/view/back.png", 133, 182, 122, 30, 
                                    new Color(255, 105, 180), new Font("Tahoma", Font.BOLD, 11)); // Màu hồng

        // Thêm các label
        addLabel("Đăng Ký Ngành Mới", 50, 120, 150, 25);
        addLabel("Danh Sách Ngành Học", 230, 120, 150, 25);
    }

    private JButton createButton(String iconPath, int x, int y, int width, int height, Color color) {
        JButton button = new JButton("");
        button.setBackground(color);
        button.setBounds(x, y, width, height);
        button.setIcon(new ImageIcon(NganhView.class.getResource(iconPath)));
        contentPane.add(button);
        return button;
    }

    private JButton createTextButton(String text, String iconPath, int x, int y, int width, int height, 
                                   Color color, Font font) {
        JButton button = new JButton(text);
        button.setIcon(new ImageIcon(NganhView.class.getResource(iconPath)));
        button.setFont(font);
        button.setBackground(color);
        button.setBounds(x, y, width, height);
        contentPane.add(button);
        return button;
    }

    private void addLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Tahoma", Font.BOLD, 11));
        label.setForeground(new Color(25, 25, 112)); // Màu chữ xanh đậm
        label.setBounds(x, y, width, height);
        contentPane.add(label);
    }

    // Getter methods
    public JButton getBtnAddNganh() {
        return btnAddNganh;
    }

    public JButton getBtnListNganh() {
        return btnListNganh;
    }

    public JButton getBtnQuayLai() {
        return btnQuayLai;
    }
}