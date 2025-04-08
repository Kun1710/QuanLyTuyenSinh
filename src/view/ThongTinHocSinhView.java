package view;

import javax.swing.*;

import controller.DangKyTaiKhoanMoiController;
import controller.ThongTinHocSinhController;
import dao.HocSinhDAO;

import java.awt.*;
import model.HocSinh;
import util.SessionManager;

public class ThongTinHocSinhView extends JFrame {
    private JPanel contentPane;
    private JComboBox<Integer> dayBird, monthBird, yearBrid;
    private JTextField hoTen;
    private JRadioButton gioitinh_nam;
    private JRadioButton gioitinh_nu;
    private JTextField diaChi;
    private JTextField danToc;
    private JTextField cccd;
    private JButton update;
    private JButton thoat;

    public ThongTinHocSinhView() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(ThongTinHocSinhView.class.getResource("/view/icon_education.png")));
        setTitle("Hệ Thống Tuyển Sinh - Thông Tin Cá Nhân");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 350);
        
        initializeComponents();
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
        contentPane = new JPanel();
        contentPane.setBackground(new Color(250, 250, 210));
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Labels
        addLabel("HỌ TÊN", 20, 44, 110, 20);
        addLabel("NGÀY SINH", 20, 77, 110, 20);
        addLabel("ĐỊA CHỈ", 20, 111, 110, 20);
        addLabel("GIỚI TÍNH", 20, 153, 110, 20);
        addLabel("DÂN TỘC", 20, 196, 110, 20);
        addLabel("CCCD", 20, 239, 110, 20);
        addLabel(">> CẬP NHẬT THÔNG TIN CÁ NHÂN", 5, 5, 227, 20);

        // Text fields
        hoTen = createTextField(150, 42, 220, 25);
        diaChi = createTextField(150, 111, 220, 25);
        danToc = createTextField(150, 196, 220, 25);
        cccd = createTextField(150, 234, 220, 25);

        // Date combo boxes
        dayBird = createComboBox(150, 75, 60, 25);
        monthBird = createComboBox(220, 75, 60, 25);
        yearBrid = createComboBox(290, 75, 80, 25);
        
        // Gender radio buttons
        gioitinh_nam = createRadioButton("Nam", 150, 153, 100, 23);
        gioitinh_nu = createRadioButton("Nữ", 270, 152, 100, 23);
        
        ButtonGroup btn_gioiTinh = new ButtonGroup();
        btn_gioiTinh.add(gioitinh_nu);
        btn_gioiTinh.add(gioitinh_nam);

        // Buttons
        update = createButton("CẬP NHẬT", "/view/update.png", 271, 270, 122, 30, new Color(127, 255, 0));
        thoat = createButton("THOÁT", "/view/exit_1.png", 38, 270, 122, 30, new Color(255, 99, 71));
        initializeDateFields();
    }

    private void addLabel(String text, int x, int y, int w, int h) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Tahoma", Font.BOLD, 11));
        label.setBounds(x, y, w, h);
        contentPane.add(label);
    }

    private JTextField createTextField(int x, int y, int w, int h) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, w, h);
        contentPane.add(textField);
        return textField;
    }

    private JComboBox<Integer> createComboBox(int x, int y, int w, int h) {
        JComboBox<Integer> comboBox = new JComboBox<>();
        comboBox.setBackground(new Color(255, 250, 240));
        comboBox.setBounds(x, y, w, h);
        contentPane.add(comboBox);
        return comboBox;
    }

    private JRadioButton createRadioButton(String text, int x, int y, int w, int h) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setFont(new Font("Tahoma", Font.BOLD, 11));
        radioButton.setBounds(x, y, w, h);
        contentPane.add(radioButton);
        return radioButton;
    }

    private JButton createButton(String text, String iconPath, int x, int y, int w, int h, Color bg) {
        JButton button = new JButton(text);
        button.setBackground(bg);
        button.setIcon(new ImageIcon(ThongTinHocSinhView.class.getResource(iconPath)));
        button.setFont(new Font("Tahoma", Font.BOLD, 11));
        button.setBounds(x, y, w, h);
        contentPane.add(button);
        return button;
    }

    // Getters for components
    public JComboBox<Integer> getDayBird() { return dayBird; }
    public JComboBox<Integer> getMonthBird() { return monthBird; }
    public JComboBox<Integer> getYearBrid() { return yearBrid; }
    public JTextField getHoTen() { return hoTen; }
    public JRadioButton getGioitinh_nam() { return gioitinh_nam; }
    public JRadioButton getGioitinh_nu() { return gioitinh_nu; }
    public JTextField getDiaChi() { return diaChi; }
    public JTextField getDanToc() { return danToc; }
    public JTextField getCccd() { return cccd; }
    public JButton getUpdateButton() { return update; }
    public JButton getThoatButton() { return thoat; }

    // Methods to populate data
    public void populateDateFields(int day, int month, int year) {
        dayBird.addItem(day);
        monthBird.addItem(month);
        yearBrid.addItem(year);
    }

    public void initializeDateFields() {
        for (int i = 1; i <= 31; i++) dayBird.addItem(i);
        for (int i = 1; i <= 12; i++) monthBird.addItem(i);
        for (int i = 2000; i <= 2010; i++) yearBrid.addItem(i);
    }

}