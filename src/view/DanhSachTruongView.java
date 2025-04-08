package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.DanhSachTruongController;

import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;

public class DanhSachTruongView extends JFrame {

    private JPanel contentPane;
    private JTextField tenTruong;
    private JTable table;
    private JButton thoat;
    private JButton xoa;
    private JButton chitiet;
    private JButton search;

    public JTable getTable() {
        return table;
    }
    
    public String getTenTruong() {
        return tenTruong.getText();
    }
    
    public void addThoatButtonListener(ActionListener listener) {
        thoat.addActionListener(listener);
    }
    
    public void addXoaButtonListener(ActionListener listener) {
        xoa.addActionListener(listener);
    }
    
    public void addChiTietButtonListener(ActionListener listener) {
        chitiet.addActionListener(listener);
    }
    
    public void addTimKiemButtonListener(ActionListener listener) {
        search.addActionListener(listener);
    }

    /**
     * Launch the application.
     */
//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    DanhSachTruongView frame = new DanhSachTruongView();
//                    new DanhSachTruongController(frame); // Thêm controller
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
    public DanhSachTruongView() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(DanhSachTruongView.class.getResource("/view/icon_education.png")));
        setTitle("Hệ Thống Tuyển Sinh - Danh Sách Trường");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 475);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        JLabel lblDanhSchHc = new JLabel("Danh Sách Trường");
        lblDanhSchHc.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblDanhSchHc.setBounds(10, 11, 150, 20);
        contentPane.add(lblDanhSchHc);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(0, 340, 580, 2);
        contentPane.add(separator);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(5, 40, 570, 300);
        contentPane.add(scrollPane);
        
        table = new JTable();
        scrollPane.setViewportView(table);
        
        JLabel lblTn = new JLabel("Tên Trường");
        lblTn.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblTn.setBounds(0, 400, 150, 25);
        contentPane.add(lblTn);
        
        tenTruong = new JTextField();
        tenTruong.setBounds(90, 400, 320, 25);
        contentPane.add(tenTruong);
        
        search = new JButton("TÌM KIẾM");
        search.setIcon(new ImageIcon(DanhSachTruongView.class.getResource("/view/search1.png")));
        search.setFont(new Font("Tahoma", Font.BOLD, 11));
        search.setBackground(new Color(250, 250, 210));
        search.setBounds(420, 400, 122, 30);
        contentPane.add(search);
        
        thoat = new JButton("THOÁT");
        thoat.setIcon(new ImageIcon(DanhSachTruongView.class.getResource("/view/exit_1.png")));
        thoat.setFont(new Font("Tahoma", Font.BOLD, 11));
        thoat.setBackground(new Color(255, 99, 71));
        thoat.setBounds(10, 350, 122, 25);
        contentPane.add(thoat);
        
        xoa = new JButton("XÓA");
        xoa.setIcon(new ImageIcon(DanhSachTruongView.class.getResource("/view/delete.png")));
        xoa.setFont(new Font("Tahoma", Font.BOLD, 11));
        xoa.setBackground(new Color(192, 192, 192));
        xoa.setBounds(220, 350, 122, 25);
        contentPane.add(xoa);
        
        chitiet = new JButton("CHI TIẾT");
        chitiet.setIcon(new ImageIcon(DanhSachTruongView.class.getResource("/view/info.png")));
        chitiet.setFont(new Font("Tahoma", Font.BOLD, 11));
        chitiet.setBackground(new Color(222, 184, 135));
        chitiet.setBounds(425, 350, 122, 25);
        contentPane.add(chitiet);
    }
}