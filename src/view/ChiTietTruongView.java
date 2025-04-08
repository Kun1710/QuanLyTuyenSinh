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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JSeparator;

public class ChiTietTruongView extends JFrame {

    private JPanel contentPane;
    private JTextField tenTruong;
    private JTextField maTruong;
    private JTextField diachi;
    private JTextField website;
    private JLabel lblThngTin;
    private JButton thoat;
    private JButton update;
    private JButton button_xoa;
    private JButton button_sua;
    private JButton button_them;
    private JTable table;

    
    public JTable getTable() {
        return table;
    }

    
    public JTextField getTenTruongField() {
        return tenTruong;
    }

    public JTextField getMaTruongField() {
        return maTruong;
    }

    public JTextField getDiaChiField() {
        return diachi;
    }

    public JTextField getWebsiteField() {
        return website;
    }

    public JLabel getTitleLabel() {
        return lblThngTin;
    }

    // Các method thêm action listener
    public void addThoatButtonListener(ActionListener listener) {
        thoat.addActionListener(listener);
    }

    public void addUpdateButtonListener(ActionListener listener) {
        update.addActionListener(listener);
    }
    
    public void addXoaButtonListener(ActionListener listener) {
        button_xoa.addActionListener(listener);
    }

    public void addSuaButtonListener(ActionListener listener) {
        button_sua.addActionListener(listener);
    }

    public void addThemButtonListener(ActionListener listener) {
        button_them.addActionListener(listener);
    }

    /**
     * Launch the application.
     */
//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    ChiTietTruongView frame = new ChiTietTruongView();
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
    public ChiTietTruongView() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(ChiTietTruongView.class.getResource("/view/icon_education.png")));
        setTitle("Hệ Thống Tuyển Sinh - Thông Tin Chi Tiết");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 600);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(238, 232, 170));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        
        // Khởi tạo JTable và JScrollPane trước
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(5, 185, 575, 251);
        contentPane.add(scrollPane);
        
        // Các thành phần khác
        JLabel lblTnTrng = new JLabel("Tên Trường");
        lblTnTrng.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblTnTrng.setBounds(10, 40, 110, 20);
        contentPane.add(lblTnTrng);
        
        JLabel lblCccd = new JLabel("Mã Trường");
        lblCccd.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblCccd.setBounds(10, 70, 110, 20);
        contentPane.add(lblCccd);
        
        JLabel lblaCh = new JLabel("Địa Chỉ");
        lblaCh.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblaCh.setBounds(10, 102, 110, 20);
        contentPane.add(lblaCh);
        
        JLabel lblDnTc = new JLabel("Website");
        lblDnTc.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblDnTc.setBounds(10, 134, 110, 20);
        contentPane.add(lblDnTc);
        
        lblThngTin = new JLabel(">> Thông Tin Của ");
        lblThngTin.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblThngTin.setBounds(5, 5, 422, 20);
        contentPane.add(lblThngTin);
        
        tenTruong = new JTextField();
        tenTruong.setBounds(110, 40, 468, 25);
        contentPane.add(tenTruong);
        
        maTruong = new JTextField();
        maTruong.setBounds(110, 72, 468, 25);
        contentPane.add(maTruong);
        
        diachi = new JTextField();
        diachi.setBounds(110, 102, 468, 25);
        contentPane.add(diachi);
        
        website = new JTextField();
        website.setBounds(110, 134, 468, 25);
        contentPane.add(website);
        
        thoat = new JButton("THOÁT");
        thoat.setIcon(new ImageIcon(ChiTietTruongView.class.getResource("/view/exit_1.png")));
        thoat.setFont(new Font("Tahoma", Font.BOLD, 11));
        thoat.setBackground(new Color(255, 127, 80));
        thoat.setBounds(20, 510, 122, 30);
        contentPane.add(thoat);
        
        update = new JButton("CẬP NHẬT");
        update.setIcon(new ImageIcon(ChiTietTruongView.class.getResource("/view/update.png")));
        update.setFont(new Font("Tahoma", Font.BOLD, 11));
        update.setBackground(new Color(127, 255, 0));
        update.setBounds(440, 510, 122, 30);
        contentPane.add(update);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(0, 170, 580, 2);
        contentPane.add(separator);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(5, 490, 580, 2);
        contentPane.add(separator_1);
        
        button_xoa = new JButton("XÓA");
        button_xoa.setIcon(new ImageIcon(ChiTietTruongView.class.getResource("/view/delete.png")));
        button_xoa.setFont(new Font("Tahoma", Font.BOLD, 12));
        button_xoa.setBackground(Color.LIGHT_GRAY);
        button_xoa.setBounds(5, 450, 150, 30);
        contentPane.add(button_xoa);
        
        button_sua = new JButton("SỬA");
        button_sua.setIcon(new ImageIcon(ChiTietTruongView.class.getResource("/view/wrench.png")));
        button_sua.setFont(new Font("Tahoma", Font.BOLD, 12));
        button_sua.setBackground(new Color(119, 136, 153));
        button_sua.setBounds(225, 450, 150, 30);
        contentPane.add(button_sua);
        
        button_them = new JButton("THÊM");
        button_them.setIcon(new ImageIcon(ChiTietTruongView.class.getResource("/view/add.png")));
        button_them.setFont(new Font("Tahoma", Font.BOLD, 12));
        button_them.setBackground(Color.CYAN);
        button_them.setBounds(430, 450, 150, 30);
        contentPane.add(button_them);
    }
}