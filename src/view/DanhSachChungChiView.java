package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;

public class DanhSachChungChiView extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JButton buttonQuayLai;
    private JButton buttonXoa;
//
//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    DanhSachChungChiView frame = new DanhSachChungChiView();
//                    frame.setVisible(true);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    public DanhSachChungChiView() {
        setTitle("Hệ Thống Tuyển Sinh - Danh Sách Chứng Chỉ");
        setIconImage(Toolkit.getDefaultToolkit().getImage(DanhSachChungChiView.class.getResource("/view/icon_education.png")));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        // Khởi tạo table với model mặc định
        table = new JTable();
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setRowHeight(25);
        
        // Tạo scroll pane và thêm table vào
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 10, 770, 350); // Điều chỉnh kích thước
        contentPane.add(scrollPane);
        
        buttonQuayLai = new JButton("QUAY LẠI");
        buttonQuayLai.setIcon(new ImageIcon(DanhSachChungChiView.class.getResource("/view/back.png")));
        buttonQuayLai.setFont(new Font("Tahoma", Font.BOLD, 11));
        buttonQuayLai.setBackground(new Color(216, 191, 216));
        buttonQuayLai.setBounds(20, 380, 120, 30);
        contentPane.add(buttonQuayLai);
        
        buttonXoa = new JButton("XÓA");
        buttonXoa.setIcon(new ImageIcon(DanhSachChungChiView.class.getResource("/view/delete.png")));
        buttonXoa.setFont(new Font("Tahoma", Font.BOLD, 11));
        buttonXoa.setBackground(new Color(211, 211, 211));
        buttonXoa.setBounds(650, 380, 120, 30);
        contentPane.add(buttonXoa);
    }

    // Các phương thức getter
    public JTable getTable() {
        return table;
    }

    public JButton getButtonQuayLai() {
        return buttonQuayLai;
    }

    public JButton getButtonXoa() {
        return buttonXoa;
    }
}