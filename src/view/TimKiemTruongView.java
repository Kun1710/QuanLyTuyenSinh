package view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
//
//import controller.searchController;
//
//import java.awt.event.*;

public class TimKiemTruongView extends JFrame {

    private JPanel contentPane;
    private JTextField maTruong, maNganh;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton search, thoat;

//    public static void main(String[] args) {
//        EventQueue.invokeLater(() -> {
//            try {
//                TimKiemTruongView frame = new TimKiemTruongView();
//                frame.setVisible(true);
//                
//                // Khởi tạo controller
//                new searchController(frame);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//    }

    public TimKiemTruongView() {
        setTitle("Hệ Thống Tuyển Sinh - Tìm Kiếm");
        setIconImage(Toolkit.getDefaultToolkit().getImage(TimKiemTruongView.class.getResource("/view/icon_education.png")));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 450);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(250, 240, 230));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        JLabel lblTitle = new JLabel(">> TÌM KIẾM");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTitle.setBounds(10, 5, 150, 20);
        contentPane.add(lblTitle);

        JLabel lblMaTruong = new JLabel("Mã Trường/Tên Trường");
        lblMaTruong.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblMaTruong.setBounds(10, 30, 150, 25);
        contentPane.add(lblMaTruong);

        maTruong = new JTextField();
        maTruong.setBounds(170, 30, 220, 25);
        contentPane.add(maTruong);

        JLabel lblMaNganh = new JLabel("Mã Ngành/Tên Ngành");
        lblMaNganh.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblMaNganh.setBounds(10, 60, 150, 25);
        contentPane.add(lblMaNganh);

        maNganh = new JTextField();
        maNganh.setBounds(170, 60, 220, 25);
        contentPane.add(maNganh);

        search = new JButton("TÌM KIẾM");
        search.setIcon(new ImageIcon(TimKiemTruongView.class.getResource("/view/search1.png")));
        search.setFont(new Font("Tahoma", Font.BOLD, 11));
        search.setBackground(new Color(250, 250, 210));
        search.setBounds(440, 30, 122, 30);
        contentPane.add(search);

        thoat = new JButton("THOÁT");
        thoat.setIcon(new ImageIcon(TimKiemTruongView.class.getResource("/view/exit_1.png")));
        thoat.setFont(new Font("Tahoma", Font.BOLD, 11));
        thoat.setBackground(new Color(255, 99, 71));
        thoat.setBounds(440, 60, 122, 30);
        contentPane.add(thoat);

        JSeparator separator = new JSeparator();
        separator.setBounds(5, 95, 580, 2);
        contentPane.add(separator);

        // Tạo bảng với 6 cột (Không cho phép chỉnh sửa)
        tableModel = new DefaultTableModel(new Object[]{"Mã Trường", "Tên Trường", "Mã Ngành", "Tên Ngành", "Chỉ Tiêu", "Khối TS"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(5, 110, 570, 300);
        contentPane.add(scrollPane);
        setLocationRelativeTo(null);
    }

    // Các phương thức getter để controller có thể truy cập các thành phần
    public JTextField getMaTruong() {
        return maTruong;
    }

    public JTextField getMaNganh() {
        return maNganh;
    }

    public JTable getTable() {
        return table;
    }

    public JButton getSearchButton() {
        return search;
    }

    public JButton getExitButton() {
        return thoat;
    }
}