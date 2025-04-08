package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class DiemThiTHPView extends JFrame {
    private JPanel contentPane;
    private JTextField toanField;
    private JTextField vanField;
    private JTextField ngoainguField;
    private JTextField lyField;
    private JTextField hoaField;
    private JTextField sinhField;
    private JTextField suField;
    private JTextField diaField;
    private JTextField gdcdField;
    private JButton thoatButton;
    private JButton updateButton;

    public DiemThiTHPView() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Hệ Thống Tuyển Sinh - Điểm Thi THPT");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/view/icon_education.png")));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(230, 230, 250));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        createTitleLabel();
        createCommonSubjectFields();
        createBlockAFields();
        createBlockCFields();
        createActionButtons();
        setLocationRelativeTo(null);
    }

    private void createTitleLabel() {
        JLabel titleLabel = new JLabel(">> CẬP NHẬT ĐIỂM THI THPT");
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        titleLabel.setBounds(5, 5, 230, 20);
        contentPane.add(titleLabel);
    }

    private void createCommonSubjectFields() {
        // Toán
        addLabelAndField("Toán", 10, 40, toanField = new JTextField(), 50, 40);
        
        // Văn
        addLabelAndField("Văn", 145, 40, vanField = new JTextField(), 180, 40);
        
        // Ngoại ngữ
        addLabelAndField("Ngoại ngữ", 270, 40, ngoainguField = new JTextField(), 350, 40);
    }

    private void createBlockAFields() {
        // Khối A label
        JLabel blockALabel = new JLabel("Khối Tự Nhiên");
        blockALabel.setIcon(new ImageIcon(getClass().getResource("/view/A.png")));
        blockALabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        blockALabel.setBounds(5, 67, 150, 20);
        contentPane.add(blockALabel);
        
        // Lý
        addLabelAndField("Lý", 10, 100, lyField = new JTextField(), 50, 100);
        
        // Hóa
        addLabelAndField("Hóa", 145, 100, hoaField = new JTextField(), 180, 100);
        
        // Sinh
        addLabelAndField("Sinh", 270, 100, sinhField = new JTextField(), 350, 98);
    }

    private void createBlockCFields() {
        // Khối C label
        JLabel blockCLabel = new JLabel("Khối Xã Hội");
        blockCLabel.setIcon(new ImageIcon(getClass().getResource("/view/C.png")));
        blockCLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        blockCLabel.setBounds(5, 144, 150, 20);
        contentPane.add(blockCLabel);
        
        // Sử
        addLabelAndField("Sử", 10, 170, suField = new JTextField(), 50, 170);
        
        // Địa
        addLabelAndField("Địa", 145, 170, diaField = new JTextField(), 180, 170);
        
        // GDCD
        addLabelAndField("GDCD", 270, 170, gdcdField = new JTextField(), 350, 170);
    }

    private void addLabelAndField(String labelText, int labelX, int labelY, 
                                JTextField textField, int fieldX, int fieldY) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Tahoma", Font.PLAIN, 13));
        label.setBounds(labelX, labelY, labelText.equals("Ngoại ngữ") ? 70 : 40, 20);
        contentPane.add(label);
        
        textField.setColumns(10);
        textField.setBounds(fieldX, fieldY, 50, 20);
        contentPane.add(textField);
    }

    private void createActionButtons() {
        // Thoát button
        thoatButton = new JButton("THOÁT");
        thoatButton.setIcon(new ImageIcon(getClass().getResource("/view/exit_1.png")));
        thoatButton.setFont(new Font("Tahoma", Font.BOLD, 11));
        thoatButton.setBackground(new Color(255, 99, 71));
        thoatButton.setBounds(38, 220, 122, 30);
        contentPane.add(thoatButton);
        
        // Cập nhật button
        updateButton = new JButton("CẬP NHẬT");
        updateButton.setIcon(new ImageIcon(getClass().getResource("/view/update.png")));
        updateButton.setFont(new Font("Tahoma", Font.BOLD, 11));
        updateButton.setBackground(new Color(127, 255, 0));
        updateButton.setBounds(249, 224, 122, 30);
        contentPane.add(updateButton);
    }

    // Getters for controller
    public JButton getUpdateButton() {
        return updateButton;
    }

    public JButton getThoatButton() {
        return thoatButton;
    }

    public JTextField getToanField() {
        return toanField;
    }

    public JTextField getVanField() {
        return vanField;
    }

    public JTextField getNgoainguField() {
        return ngoainguField;
    }

    public JTextField getLyField() {
        return lyField;
    }

    public JTextField getHoaField() {
        return hoaField;
    }

    public JTextField getSinhField() {
        return sinhField;
    }

    public JTextField getSuField() {
        return suField;
    }

    public JTextField getDiaField() {
        return diaField;
    }

    public JTextField getGdcdField() {
        return gdcdField;
    }

    public void addUpdateButtonListener(ActionListener listener) {
        updateButton.addActionListener(listener);
    }

    public void addExitButtonListener(ActionListener listener) {
        thoatButton.addActionListener(listener);
    }

    public void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Lỗi", JOptionPane.ERROR_MESSAGE);
    }

    public void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Thành công", JOptionPane.INFORMATION_MESSAGE);
    }
//    public static void main(String[] args) {
//        EventQueue.invokeLater(() -> {
//            try {
//                diemThiTHPTView frame = new diemThiTHPTView();
//                frame.setVisible(true);
//               // diemThiTHPTController();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//    }
}