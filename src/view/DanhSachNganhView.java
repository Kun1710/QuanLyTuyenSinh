package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controller.DanhSachNganhController;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DanhSachNganhView extends JFrame {

    private JPanel contentPane;
    private JTable table;
    private JLabel lblTitle;
    private DefaultTableModel tableModel;
    private JButton button_back;
    private JButton button_xoa;
    private JButton button_sua;
    private JButton button_them;
    private JButton button_ketqua;
//    public static void main(String[] args) {
//        EventQueue.invokeLater(() -> {
//            try {
//                DanhSachNganhView frame = new DanhSachNganhView();
//                frame.setVisible(true);
//                new DanhSachNganhController(frame);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//    }

    public DanhSachNganhView() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(DanhSachNganhView.class.getResource("/view/icon_education.png")));
        setTitle("Hệ Thống Tuyển Sinh - Danh Sách Ngành");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 800, 500);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(245, 245, 220));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);
        setLocationRelativeTo(null);
        lblTitle = new JLabel(">> DANH SÁCH NGÀNH HỌC CỦA TRƯỜNG");
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblTitle.setBounds(10, 5, 400, 30);
        contentPane.add(lblTitle);

        // Tạo bảng
        String[] columnNames = {"ID", "Tên Ngành", "Mã Ngành", "Chỉ Tiêu", "Phương Thức", "Khối Xét Tuyển", "Ưu Tiên"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Chống chỉnh sửa dữ liệu
            }
        };

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 50, 760, 350);
        contentPane.add(scrollPane);

        // Nút Quay lại
        button_back = createButton("QUAY LẠI", "/view/back.png", new Color(216, 191, 216), 10, 420);
        contentPane.add(button_back);
        
        button_xoa = new JButton("XÓA");
        button_xoa.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        button_xoa.setIcon(new ImageIcon(DanhSachNganhView.class.getResource("/view/delete.png")));
        button_xoa.setFont(new Font("Tahoma", Font.BOLD, 12));
        button_xoa.setBackground(new Color(192, 192, 192));
        button_xoa.setBounds(170, 420, 110, 30);
        contentPane.add(button_xoa);
        
        button_sua = new JButton("SỬA");
        button_sua.setIcon(new ImageIcon(DanhSachNganhView.class.getResource("/view/wrench.png")));
        button_sua.setFont(new Font("Tahoma", Font.BOLD, 12));
        button_sua.setBackground(new Color(119, 136, 153));
        button_sua.setBounds(340, 420, 110, 30);
        contentPane.add(button_sua);
        
        button_them = new JButton("THÊM");
        button_them.setIcon(new ImageIcon(DanhSachNganhView.class.getResource("/view/add.png")));
        button_them.setFont(new Font("Tahoma", Font.BOLD, 12));
        button_them.setBackground(new Color(0, 255, 255));
        button_them.setBounds(500, 420, 110, 30);
        contentPane.add(button_them);
        
        button_ketqua = new JButton("Kết Quả");
        button_ketqua.setIcon(new ImageIcon(DanhSachNganhView.class.getResource("/view/info.png")));
        button_ketqua.setFont(new Font("Tahoma", Font.BOLD, 12));
        button_ketqua.setBackground(new Color(255, 239, 213));
        button_ketqua.setBounds(650, 420, 110, 30);
        contentPane.add(button_ketqua);

        // Dữ liệu mẫu
      //  loadSampleData();
    }

    private JButton createButton(String text, String iconPath, Color bgColor, int x, int y) {
        JButton button_quayLai = new JButton(text);
        button_quayLai.setIcon(new ImageIcon(DanhSachNganhView.class.getResource(iconPath)));
        button_quayLai.setFont(new Font("Tahoma", Font.BOLD, 12));
        button_quayLai.setBackground(bgColor);
        button_quayLai.setBounds(10, 420, 130, 30);
        return button_quayLai;
    }
 // Thêm các getter methods vào class listNganhView

    public JTable getTable() {
        return table;
    }

    public JButton getBtnBack() {
        return button_back;
    }

    public JButton getBtnThem() {
        return button_them;
    }

    public JButton getBtnSua() {
        return button_sua;
    }

    public JButton getBtnXoa() {
        return button_xoa;
    }

	public JLabel getLblTitle() {
		return lblTitle;
	}

	public void setLblTitle(JLabel lblTitle) {
		this.lblTitle = lblTitle;
	}

	public void setButton_ketqua(JButton button_ketqua) {
		this.button_ketqua = button_ketqua;
	}

	public AbstractButton getButton_ketqua() {
		// TODO Auto-generated method stub
		return button_ketqua;
	}
	
}