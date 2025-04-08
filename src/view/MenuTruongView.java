package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import dao.HocSinhDAO;
import dao.TruongDAO;
import model.TaiKhoan;
import util.SessionManager;
import java.awt.Toolkit;
import java.sql.SQLException;

import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.awt.Font;

public class MenuTruongView extends JFrame {

    private JPanel contentPane;
    private JButton button_search;
    private JButton button_truong;
    private JButton button_listNganh;
    private JLabel lblxinCho;
	private JMenuItem thoat;
	private JMenuItem dangXuat;
	private JMenuItem doiMatKhau;

    /**
     * Launch the application.
     */
//    public static void main(String[] args) {
//        EventQueue.invokeLater(() -> {
//            try {
//                menuTruongView frame = new menuTruongView();
//                frame.setVisible(true);
//                new menuTruongController(frame);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        });
//    }

    /**
     * Create the frame.
     */
    public MenuTruongView() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(MenuTruongView.class.getResource("/view/icon_education.png")));
        setTitle("Hệ Thống Tuyển Sinh - Menu");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        
        initializeComponents();
        setLocationRelativeTo(null);
    }

    private void initializeComponents() {
    	JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu chucNang = new JMenu("Chức năng");
        chucNang.setFont(new Font("Segoe UI", Font.BOLD, 12));
        chucNang.setIcon(new ImageIcon(MenuHocSinhView.class.getResource("/view/chucnang.png")));
        menuBar.add(chucNang);
        
        dangXuat = new JMenuItem("Đăng Xuất");
        dangXuat.setIcon(new ImageIcon(MenuHocSinhView.class.getResource("/view/logout.png")));
        chucNang.add(dangXuat);
        
        doiMatKhau = new JMenuItem("Đổi Mật Khẩu");
        doiMatKhau.setIcon(new ImageIcon(MenuHocSinhView.class.getResource("/view/exchange.png")));
        chucNang.add(doiMatKhau);
        
        JSeparator separator = new JSeparator();
        chucNang.add(separator);
        
        thoat = new JMenuItem("Thoát");
        thoat.setIcon(new ImageIcon(MenuHocSinhView.class.getResource("/view/exit_1.png")));
        chucNang.add(thoat);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(224, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Tạo các nút
        button_truong = createButton("/view/university.png", 10, 45, 140, 147, new Color(250, 250, 210));
        button_search = createButton("/view/search-icon.png", 240, 45, 123, 147, new Color(250, 250, 210));
        button_listNganh = createButton("/view/menu.png", 450, 45, 123, 147, new Color(250, 250, 210));

        // Thêm các label
        addLabel("CẬP NHẬT THÔNG TIN", 20, 215, 130, 20);
        addLabel("TÌM KIẾM", 275, 215, 80, 20);
        addLabel("DANH SÁCH NGÀNH", 450, 215, 148, 20);

        // Label chào mừng
        lblxinCho = new JLabel("> Xin chào");
        lblxinCho.setFont(new Font("Tahoma", Font.PLAIN, 11));
        lblxinCho.setBounds(5, 5, 227, 20);
        contentPane.add(lblxinCho);

		TaiKhoan temp_account = SessionManager.getInstance().getCurrentAccount();
		TruongDAO temp_TruongDAO = new TruongDAO();
		String temp_ten;
		try {
			temp_ten = temp_TruongDAO.getTenTruongByIdTK(temp_account.getId());
			if(temp_ten != null) {
				lblxinCho.setText("> Xin chào " + temp_ten);
			}else {
				lblxinCho.setText("> Xin chào " + temp_account.getTaiKhoan());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    private JButton createButton(String iconPath, int x, int y, int width, int height, Color color) {
        JButton button = new JButton("");
        button.setBackground(color);
        button.setBounds(x, y, width, height);
        button.setIcon(new ImageIcon(MenuTruongView.class.getResource(iconPath)));
        contentPane.add(button);
        return button;
    }

    private void addLabel(String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        contentPane.add(label);
    }

    // Getter methods
    public JButton getButton_truong() {
        return button_truong;
    }

    public JButton getButton_search() {
        return button_search;
    }

    public JButton getButton_listNganh() {
        return button_listNganh;
    }

	public JMenuItem getThoat() {
		return thoat;
	}

	public JMenuItem getDangXuat() {
		return dangXuat;
	}

	public JMenuItem getDoiMatKhau() {
		return doiMatKhau;
	}
    
}