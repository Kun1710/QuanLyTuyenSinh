package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JSeparator;

public class TimKiemHocSinhView extends JFrame {

    private JPanel contentPane;
    private JTable tblResults;
    private JLabel lblTitle_1;
    private JLabel lblTnHcSinh;
    private JTextField textField;
    private JLabel lblCccd;
    private JTextField textField_1;
    private JSeparator separator;
    private JButton search;
    private JButton thoat;

//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    TimKiemHocSinhView frame = new TimKiemHocSinhView();
//                    frame.setVisible(true);
//                    new TimKiemHocSinhController(frame);
//                   // new SearchController(frame);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    public TimKiemHocSinhView() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(TimKiemHocSinhView.class.getResource("/view/icon_education.png")));
        setTitle("Hệ Thống Tuyển Sinh - Tìm Kiếm");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 410);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 239, 213));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 110, 560, 250);
        contentPane.add(scrollPane);
        
        tblResults = new JTable();
        tblResults.setModel(new DefaultTableModel(
            new Object[][] {},
            new String[] {"CCCD", "Họ Tên", "Ngày Sinh","Địa chỉ", "Giới Tính", "Dân Tộc"}
        ));
        scrollPane.setViewportView(tblResults);
        
        lblTitle_1 = new JLabel(">> TÌM KIẾM");
        lblTitle_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTitle_1.setBounds(5, 5, 150, 20);
        contentPane.add(lblTitle_1);
        
        lblTnHcSinh = new JLabel("Họ Tên Học Sinh");
        lblTnHcSinh.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblTnHcSinh.setBounds(10, 25, 150, 25);
        contentPane.add(lblTnHcSinh);
        
        textField = new JTextField();
        textField.setBounds(150, 25, 220, 25);
        contentPane.add(textField);
        
        lblCccd = new JLabel("CCCD");
        lblCccd.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblCccd.setBounds(10, 60, 150, 25);
        contentPane.add(lblCccd);
        
        textField_1 = new JTextField();
        textField_1.setBounds(150, 60, 220, 25);
        contentPane.add(textField_1);
        
        separator = new JSeparator();
        separator.setBounds(5, 100, 580, 2);
        contentPane.add(separator);
        
        search = new JButton("TÌM KIẾM");
        search.setFont(new Font("Tahoma", Font.BOLD, 11));
        search.setBackground(new Color(250, 250, 210));
        search.setBounds(400, 25, 122, 30);
        contentPane.add(search);
        
        thoat = new JButton("THOÁT");
        thoat.setFont(new Font("Tahoma", Font.BOLD, 11));
        thoat.setBackground(new Color(255, 99, 71));
        thoat.setBounds(400, 60, 122, 30);
        contentPane.add(thoat);
        
        setLocationRelativeTo(null);
    }

    // Getter methods



    public JTable getTblResults() {
        return tblResults;
    }
 // Thêm các phương thức getter và setter vào cuối class searchTruongView

    public String getHoTen() {
        return textField.getText().trim();
    }

    public String getCCCD() {
        return textField_1.getText().trim();
    }

    public void addSearchButtonListener(ActionListener listener) {
        search.addActionListener(listener);
    }

    public void addThoatButtonListener(ActionListener listener) {
        thoat.addActionListener(listener);
    }

    public JButton getSearchButton() {
        return search;
    }

    public JButton getThoatButton() {
        return thoat;
    }
}