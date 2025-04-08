package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

import controller.DanhSachTuyenThangController;

public class DanhSachTuyenThangView extends JFrame {
    private JPanel contentPane;
    private JTable table;
    private JButton thoat;
    private JButton chon;

//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    DanhSachTuyenThangView frame = new DanhSachTuyenThangView();
//                    frame.setVisible(true);
//                    new DanhSachTuyenThangController(frame);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    public DanhSachTuyenThangView() {
        setTitle("Hệ Thống Tuyển Sinh - Danh Sách Tuyển Thẳng");
        setIconImage(Toolkit.getDefaultToolkit().getImage(DanhSachTuyenThangView.class.getResource("/view/icon_education.png")));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 450); // Increased width to accommodate more columns
        contentPane = new JPanel();
        contentPane.setBackground(new Color(175, 238, 238));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        // Table setup with only required columns
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        model.addColumn("ID Hồ sơ");
        model.addColumn("Tên Học Sinh");
        model.addColumn("Tên Trường");
        //model.addColumn("Trạng thái");
        
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(5, 5, 775, 330);
        contentPane.add(scrollPane);
        setLocationRelativeTo(null);
        
        thoat = new JButton("THOÁT");
        thoat.setIcon(new ImageIcon(DanhSachTuyenThangView.class.getResource("/view/exit_1.png")));
        thoat.setFont(new Font("Tahoma", Font.BOLD, 11));
        thoat.setBackground(new Color(255, 127, 80));
        thoat.setBounds(23, 360, 122, 30);
        contentPane.add(thoat);
        
        chon = new JButton("CHỌN");
        chon.setIcon(new ImageIcon(DanhSachTuyenThangView.class.getResource("/view/selection.png")));
        chon.setFont(new Font("Tahoma", Font.BOLD, 11));
        chon.setBackground(new Color(255, 250, 240));
        chon.setBounds(643, 360, 122, 30);
        contentPane.add(chon);
    }

    public JTable getTable() {
        return table;
    }

    public JButton getThoatButton() {
        return thoat;
    }

    public JButton getChonButton() {
        return chon;
    }
}