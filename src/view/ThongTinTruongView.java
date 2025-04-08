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
import javax.swing.ImageIcon;

public class ThongTinTruongView extends JFrame {

    private JPanel contentPane;
    private JTextField tenTruong;
    private JTextField maTruong;
    private JTextField DiaChi;
    private JTextField website;
    private JButton thoat;
    private JButton update;

    /**
     * Launch the application.
     */
//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    ThongTinTruongView frame = new ThongTinTruongView(1);
//                    frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    /**
     * Create the frame with default constructor.
     */
    public ThongTinTruongView() {
        initComponents();
    }

    /**
     * Create the frame with idTaiKhoan parameter.
     */
    public ThongTinTruongView(int idTaiKhoan) {
        initComponents();
        // Khởi tạo controller với idTaiKhoan
        new controller.ThongTinTruongController(this, idTaiKhoan);
    }

    /**
     * Initialize all UI components.
     */
    private void initComponents() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(ThongTinTruongView.class.getResource("/view/icon_education.png")));
        setTitle("Hệ Thống Tuyển Sinh - Thông Tin Của Trường");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Thay đổi từ EXIT_ON_CLOSE để không đóng cả ứng dụng
        setBounds(100, 100, 400, 350);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(220, 220, 220));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Label tiêu đề
        JLabel lblCpNhtThng = new JLabel(">> CẬP NHẬT THÔNG TIN TRƯỜNG");
        lblCpNhtThng.setBounds(5, 5, 250, 20); // Tăng width để hiển thị đủ text
        lblCpNhtThng.setFont(new Font("Tahoma", Font.BOLD, 11));
        contentPane.add(lblCpNhtThng);

        // Các label và text field
        addLabelAndTextField("TÊN TRƯỜNG", 15, 50, tenTruong = new JTextField(), 113, 50);
        addLabelAndTextField("MÃ TRƯỜNG", 15, 100, maTruong = new JTextField(), 113, 100);
        addLabelAndTextField("ĐỊA CHỈ", 15, 150, DiaChi = new JTextField(), 113, 150);
        addLabelAndTextField("Website", 15, 200, website = new JTextField(), 113, 200);

        // Nút thoát
        thoat = new JButton("THOÁT");
        thoat.setIcon(new ImageIcon(ThongTinTruongView.class.getResource("/view/exit_1.png")));
        thoat.setFont(new Font("Tahoma", Font.BOLD, 11));
        thoat.setBackground(new Color(255, 99, 71));
        thoat.setBounds(15, 257, 122, 30);
        contentPane.add(thoat);

        // Nút cập nhật
        update = new JButton("CẬP NHẬT");
        update.setIcon(new ImageIcon(ThongTinTruongView.class.getResource("/view/update.png")));
        update.setFont(new Font("Tahoma", Font.BOLD, 11));
        update.setBackground(new Color(127, 255, 0));
        update.setBounds(207, 257, 122, 30);
        contentPane.add(update);

        setLocationRelativeTo(null);
    }

    /**
     * Helper method to add label and text field
     */
    private void addLabelAndTextField(String labelText, int xLabel, int yLabel, 
                                    JTextField textField, int xField, int yField) {
        JLabel label = new JLabel(labelText);
        label.setBounds(xLabel, yLabel, 110, 20);
        label.setFont(new Font("Tahoma", Font.BOLD, 11));
        contentPane.add(label);

        textField.setBounds(xField, yField, 220, 25);
        contentPane.add(textField);
    }

    // ========== GETTER METHODS ==========
    public JTextField getTenTruong() {
        return tenTruong;
    }

    public JTextField getMaTruong() {
        return maTruong;
    }

    public JTextField getDiaChi() {
        return DiaChi;
    }

    public JTextField getWebsite() {
        return website;
    }

    public JButton getThoatButton() {
        return thoat;
    }

    public JButton getUpdateButton() {
        return update;
    }
}