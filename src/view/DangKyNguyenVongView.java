package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.DangKyNguyenVongMoiController;
import model.Nganh;
import model.Truong;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;

public class DangKyNguyenVongView extends JFrame {

	private JPanel contentPane;
    private JComboBox<Truong> cbTruong; 
    private JComboBox<Nganh> cbNganh;   
    private JComboBox<String> phuongThuc;
    private JButton btnDangKy;
    private JButton btnQuayLai;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DangKyNguyenVongView frame = new DangKyNguyenVongView();
                    frame.setVisible(true);
                    new DangKyNguyenVongMoiController(frame);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public DangKyNguyenVongView() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(DangKyNguyenVongView.class.getResource("/view/icon_education.png")));
        setTitle("Hệ Thống Tuyển Sinh - Đăng Ký Nguyện Vọng");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 255, 250));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblTitle = new JLabel(">> ĐĂNG KÝ NGUYỆN VỌNG");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 12));
        lblTitle.setBounds(5, 5, 186, 20);
        contentPane.add(lblTitle);
        
        JLabel lblMaTruong = new JLabel("Trường ");
        lblMaTruong.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblMaTruong.setBounds(5, 35, 77, 25);
        contentPane.add(lblMaTruong);
        
        
        JLabel lblMaNganh = new JLabel("Ngành ");
        lblMaNganh.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblMaNganh.setBounds(5, 80, 70, 25);
        contentPane.add(lblMaNganh);
//        
//        maTruong = new JTextField();
//        maTruong.setBounds(219, 38, 186, 25);
//        contentPane.add(maTruong);
//        maTruong.setColumns(10);
        cbTruong = new JComboBox<>();
        cbTruong.setBounds(219, 38, 186, 25);
        cbTruong.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value == null) {
                    setText("");
                }
                return this;
            }
        });
        contentPane.add(cbTruong);
        
//        maNganh = new JTextField();
//        maNganh.setBounds(219, 81, 186, 25);
//        contentPane.add(maNganh);
//        maNganh.setColumns(10);
        
//        cbNganh = new JComboBox<>();
//        cbNganh.setBounds(150, 80, 250, 25);
//        contentPane.add(cbNganh);
        cbNganh = new JComboBox<>();
        cbNganh.setBounds(219, 81, 186, 25);
        cbNganh.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value == null) {
                    setText("");
                }
                return this;
            }
        });
        contentPane.add(cbNganh);
        
        JLabel lblPhuongThuc = new JLabel("Phương Thức Xét Tuyển");
        lblPhuongThuc.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblPhuongThuc.setBounds(5, 135, 186, 25);
        contentPane.add(lblPhuongThuc);
        
//        phuongThuc = new JComboBox<>();
//        phuongThuc.setBounds(219, 135, 186, 22);
//        contentPane.add(phuongThuc);
        phuongThuc = new JComboBox<>();
        phuongThuc.setBounds(219, 136, 186, 22);
        phuongThuc.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value == null) {
                    setText("");
                }
                return this;
            }
        });
        contentPane.add(phuongThuc);
        
        btnDangKy = new JButton("ĐĂNG KÝ");
        btnDangKy.setIcon(new ImageIcon(DangKyNguyenVongView.class.getResource("/view/dangky.png")));
        btnDangKy.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnDangKy.setBackground(new Color(127, 255, 0));
        btnDangKy.setBounds(232, 199, 122, 30);
        contentPane.add(btnDangKy);
        
        btnQuayLai = new JButton("QUAY LẠI");
        btnQuayLai.setIcon(new ImageIcon(DangKyNguyenVongView.class.getResource("/view/back.png")));
        btnQuayLai.setFont(new Font("Tahoma", Font.BOLD, 11));
        btnQuayLai.setBackground(new Color(255, 105, 180));
        btnQuayLai.setBounds(46, 203, 122, 30);
        contentPane.add(btnQuayLai);
        setLocationRelativeTo(null);
    }

    // Getters
//    public String getMaTruong() {
//        return maTruong.getText();
//    }
//
//    public String getMaNganh() {
//        return maNganh.getText();
//    }
    public String getMaTruong() {
        Truong selected = (Truong) cbTruong.getSelectedItem();
        return selected != null ? selected.getMaTruong() : "";
    }

    public String getMaNganh() {
        Nganh selected = (Nganh) cbNganh.getSelectedItem();
        return selected != null ? selected.getMaNganh() : "";
    }

    public JComboBox<Truong> getTruongComboBox() {
        return cbTruong;
    }

    public JComboBox<Nganh> getNganhComboBox() {
        return cbNganh;
    }
    
    public String getPhuongThuc() {
        return (String) phuongThuc.getSelectedItem();
    }

    public JButton getDangKyButton() {
        return btnDangKy;
    }

    public JButton getQuayLaiButton() {
        return btnQuayLai;
    }

    public JComboBox<String> getPhuongThucComboBox() {
        return phuongThuc;
    }
}