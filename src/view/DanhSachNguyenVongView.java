package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.DanhSachNguyenVongController;

import java.awt.*;

public class DanhSachNguyenVongView extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton buttonLen;
    private JButton buttonXuong;
    private JButton buttonXoa;
    private JButton buttonQuayLai;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                DanhSachNguyenVongView frame = new DanhSachNguyenVongView();
                frame.setVisible(true);
                new DanhSachNguyenVongController(frame);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public DanhSachNguyenVongView() {
        initializeUI();
    }

    private void initializeUI() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(DanhSachNguyenVongView.class.getResource("/view/icon_education.png")));
        setTitle("Hệ Thống Tuyển Sinh - Danh sách nguyện vọng");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 700, 500);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 240));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        createTitleLabels();
        createTable();
        createButtons();
    }

    private void createTitleLabels() {
        JLabel lblTitle = new JLabel(">> DANH SÁCH NGUYỆN VỌNG");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblTitle.setBounds(10, 5, 300, 25);
        contentPane.add(lblTitle);

        JLabel lblNote = new JLabel(">> Đây chính là thứ tự xét tuyển");
        lblNote.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNote.setForeground(new Color(70, 70, 70));
        lblNote.setBounds(20, 35, 300, 25);
        contentPane.add(lblNote);
    }

    private void createTable() {
        String[] columnNames = {"STT", "Tên Trường", "Mã Trường", "Tên Ngành", "Mã Ngành", "Phương Thức", "Trạng Thái"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getTableHeader().setReorderingAllowed(false);
        
        // Set custom row height
        table.setRowHeight(25);
        
        // Center align all columns
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 71, 661, 330);
        contentPane.add(scrollPane);
    }

    private void createButtons() {
        buttonLen = new JButton("LÊN");
        buttonLen.setIcon(new ImageIcon(DanhSachNguyenVongView.class.getResource("/view/up.png")));
        buttonLen.setFont(new Font("Tahoma", Font.BOLD, 11));
        buttonLen.setBackground(new Color(144, 238, 144));
        buttonLen.setBounds(535, 415, 122, 30);
        contentPane.add(buttonLen);

        buttonXuong = new JButton("XUỐNG");
        buttonXuong.setIcon(new ImageIcon(DanhSachNguyenVongView.class.getResource("/view/down.png")));
        buttonXuong.setFont(new Font("Tahoma", Font.BOLD, 11));
        buttonXuong.setBackground(new Color(255, 182, 193));
        buttonXuong.setBounds(370, 415, 122, 30);
        contentPane.add(buttonXuong);

        buttonXoa = new JButton("XÓA");
        buttonXoa.setIcon(new ImageIcon(DanhSachNguyenVongView.class.getResource("/view/delete.png")));
        buttonXoa.setFont(new Font("Tahoma", Font.BOLD, 11));
        buttonXoa.setBackground(new Color(211, 211, 211));
        buttonXoa.setBounds(190, 415, 122, 30);
        contentPane.add(buttonXoa);

        buttonQuayLai = new JButton("QUAY LẠI");
        buttonQuayLai.setIcon(new ImageIcon(DanhSachNguyenVongView.class.getResource("/view/back.png")));
        buttonQuayLai.setFont(new Font("Tahoma", Font.BOLD, 11));
        buttonQuayLai.setBackground(new Color(216, 191, 216));
        buttonQuayLai.setBounds(25, 415, 122, 30);
        contentPane.add(buttonQuayLai);
    }

    // Getter methods for controller
    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JButton getLenButton() {
        return buttonLen;
    }

    public JButton getXuongButton() {
        return buttonXuong;
    }

    public JButton getXoaButton() {
        return buttonXoa;
    }

    public JButton getQuayLaiButton() {
        return buttonQuayLai;
    }

    // Method to add a row to the table
    public void addRowToTable(Object[] rowData) {
        tableModel.addRow(rowData);
    }

    // Method to clear all rows from the table
    public void clearTable() {
        tableModel.setRowCount(0);
    }

    // Method to get selected row index
    public int getSelectedRowIndex() {
        return table.getSelectedRow();
    }

    // Method to get data from a specific row
    public Object getValueAt(int row, int column) {
        return tableModel.getValueAt(row, column);
    }
}