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

public class HoSoXetTuyenView extends JFrame {

    private JPanel contentPane;
    private JButton dkNguyenVong;
    private JButton listNguyenVong;
    private JButton btnQuayLi;

    /**
     * Launch the application.
     */
//    public static void main(String[] args) {
//        EventQueue.invokeLater(() -> {
//            try {
//                HoSoXetTuyenView frame = new HoSoXetTuyenView();
//                frame.setVisible(true);
//                new controller.HoSoXetTuyenController(frame);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//    }

    /**
     * Create the frame.
     */
    public HoSoXetTuyenView() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(HoSoXetTuyenView.class.getResource("/view/icon_education.png")));
        setTitle("Hệ Thống Tuyển Sinh - Hồ Sơ Xét Tuyển");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        
        initializeComponents();
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 224));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Tạo các nút
        dkNguyenVong = createButton("/view/addnv.png", 69, 27, 82, 71, new Color(255, 228, 225));
        listNguyenVong = createButton("/view/listnv.png", 244, 27, 75, 71, new Color(175, 238, 238));
        btnQuayLi = createTextButton("QUAY LẠI", "/view/back.png", 133, 182, 122, 30, 
                                    new Color(255, 20, 147), new Font("Tahoma", Font.BOLD, 11));

        // Thêm các label
        addLabel("Đăng Ký Nguyện Vọng", 50, 120, 150, 25);
        addLabel("Danh Sách Nguyện Vọng", 230, 120, 150, 25);
    }

    private JButton createButton(String iconPath, int x, int y, int width, int height, Color color) {
        JButton button = new JButton("");
        button.setBackground(color);
        button.setBounds(x, y, width, height);
        button.setIcon(new ImageIcon(HoSoXetTuyenView.class.getResource(iconPath)));
        contentPane.add(button);
        return button;
    }

    private JButton createTextButton(String text, String iconPath, int x, int y, int width, int height, 
                                   Color color, Font font) {
        JButton button = new JButton(text);
        button.setIcon(new ImageIcon(HoSoXetTuyenView.class.getResource(iconPath)));
        button.setFont(font);
        button.setBackground(color);
        button.setBounds(x, y, width, height);
        contentPane.add(button);
        return button;
    }

    private void addLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Tahoma", Font.BOLD, 11));
        label.setBounds(x, y, width, height);
        contentPane.add(label);
    }

    // Getter methods
    public JButton getDkNguyenVongButton() {
        return dkNguyenVong;
    }

    public JButton getListNguyenVongButton() {
        return listNguyenVong;
    }

    public JButton getQuayLaiButton() {
        return btnQuayLi;
    }
}