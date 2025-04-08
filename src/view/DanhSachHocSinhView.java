package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.DanhSachHocSinhController;

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

public class DanhSachHocSinhView extends JFrame {

    private JPanel contentPane;
    private JTextField ten;
    private JTable table;
    private JButton xoa;
    private JButton thoat;
    private JButton chitiet;
    private JButton search;
    
    public JTable getTable() {
        return table;
    }
    
    public String getTenTimKiem() {
        return ten.getText();
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
//                    DanhSachHocSinhView frame = new DanhSachHocSinhView();
//                    frame.setVisible(true);
//                    new DanhSachHocSinhController(frame);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    /**
     * Create the frame.
     */
    public DanhSachHocSinhView() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(DanhSachHocSinhView.class.getResource("/view/icon_education.png")));
        setTitle("Hệ Thống Tuyển Sinh - Danh Sách Học Sinh");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 475);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblDanhSchHc = new JLabel("Danh Sách Học Sinh");
        lblDanhSchHc.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblDanhSchHc.setBounds(10, 11, 150, 20);
        contentPane.add(lblDanhSchHc);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(0, 340, 780, 2);
        contentPane.add(separator);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(5, 40, 770, 300);
        contentPane.add(scrollPane);
        
        table = new JTable();
        scrollPane.setViewportView(table);
        
        JLabel lblTn = new JLabel("Họ Tên");
        lblTn.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblTn.setBounds(10, 400, 50, 25);
        contentPane.add(lblTn);
        
        ten = new JTextField();
        ten.setBounds(65, 400, 431, 25);
        contentPane.add(ten);
        ten.setColumns(10);
        
        search = new JButton("TÌM KIẾM");
        search.setIcon(new ImageIcon(DanhSachHocSinhView.class.getResource("/view/search1.png")));
        search.setFont(new Font("Tahoma", Font.BOLD, 11));
        search.setBackground(new Color(250, 250, 210));
        search.setBounds(545, 397, 122, 30);
        contentPane.add(search);
        
        thoat = new JButton("THOÁT");
        thoat.setIcon(new ImageIcon(DanhSachHocSinhView.class.getResource("/view/exit_1.png")));
        thoat.setFont(new Font("Tahoma", Font.BOLD, 11));
        thoat.setBackground(new Color(255, 99, 71));
        thoat.setBounds(10, 350, 122, 25);
        contentPane.add(thoat);
        
        xoa = new JButton("XÓA");
        xoa.setIcon(new ImageIcon(DanhSachHocSinhView.class.getResource("/view/delete.png")));
        xoa.setFont(new Font("Tahoma", Font.BOLD, 11));
        xoa.setBackground(new Color(192, 192, 192));
        xoa.setBounds(339, 350, 122, 25);
        contentPane.add(xoa);
        
        chitiet = new JButton("CHI TIẾT");
        chitiet.setIcon(new ImageIcon(DanhSachHocSinhView.class.getResource("/view/info.png")));
        chitiet.setFont(new Font("Tahoma", Font.BOLD, 11));
        chitiet.setBackground(new Color(222, 184, 135));
        chitiet.setBounds(653, 350, 122, 25);
        contentPane.add(chitiet);
    }
}