package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import controller.MenuHocSinhController;
import dao.HocSinhDAO;
import model.TaiKhoan;
import model.HocSinh;
import util.SessionManager;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class MenuHocSinhView extends JFrame {
	private JPanel contentPane;
	private JButton button_updateprofile;
	private JButton button_updateGPA;
	private JButton button_updateGPA_THPT;
	private JButton button_updateCe;
	private JButton button_search;
	private JButton button_register;
	private JLabel lblxinCho;
	private JMenuItem thoat;
	private JMenuItem dangXuat;
	private JMenuItem doiMatKhau;

//	public static void main(String[] args) {
//		EventQueue.invokeLater(() -> {
//			try {
//				MenuHocSinhView frame = new MenuHocSinhView();
//				frame.setVisible(true);
//
//				// Khởi tạo controller
//				new MenuHocSinhController(frame);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		});
//	}

	public MenuHocSinhView() {
		setTitle("Hệ Thống Tuyển Sinh - Menu");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 450);

		initializeComponents();
		setIcon();
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

		button_updateprofile = createButton("/view/icon_updateprofile1.png", 30, 30, 120, 130,
				new Color(250, 240, 230));
		button_updateGPA = createButton("/view/gpa1.png", 230, 30, 120, 130, new Color(250, 250, 210));
		button_updateGPA_THPT = createButton("/view/gpa2.png", 410, 30, 120, 130, new Color(250, 250, 210));
		button_updateCe = createButton("/view/chungchi.png", 30, 210, 120, 130, new Color(250, 250, 210));
		button_search = createButton("/view/search-icon.png", 230, 210, 120, 130, new Color(250, 250, 210));
		button_register = createButton("/view/register1.png", 410, 210, 120, 130, new Color(250, 250, 210));

		addLabel("CẬP NHẬT PROFILE", 40, 171, 110, 20);
		addLabel("CẬP NHẬT ĐIỂM HỌC BẠ", 217, 171, 153, 20);
		addLabel("CẬP NHẬT ĐIỂM THI THPT", 400, 171, 170, 20);
		addLabel("CHỨNG CHỈ", 55, 350, 140, 20);
		addLabel("TÌM KIẾM", 252, 351, 80, 20);
		addLabel("ĐĂNG KÝ XÉT TUYỂN", 415, 350, 130, 20);

		lblxinCho = new JLabel("> Xin chào");
		lblxinCho.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblxinCho.setBounds(5, 5, 227, 20);
		contentPane.add(lblxinCho);

		TaiKhoan temp_account = SessionManager.getInstance().getCurrentAccount();
		HocSinhDAO temp_HSDAO = new HocSinhDAO();
		String temp_ten = temp_HSDAO.getHoTenByIdTaiKhoan(temp_account.getId());
		if(temp_ten != null) {
			lblxinCho.setText("> Xin chào " + temp_ten);
		}else {
			lblxinCho.setText("> Xin chào " + temp_account.getTaiKhoan());
		}
	}

	private JButton createButton(String iconPath, int x, int y, int width, int height, Color color) {
		JButton button = new JButton("");
		button.setBackground(color);
		button.setBounds(x, y, width, height);
		button.setIcon(new ImageIcon(MenuHocSinhView.class.getResource(iconPath)));
		contentPane.add(button);
		return button;
	}

	private void addLabel(String text, int x, int y, int width, int height) {
		JLabel label = new JLabel(text);
		label.setBounds(x, y, width, height);
		contentPane.add(label);
	}

	private void setIcon() {
		URL urliconeducation = MenuDangNhapView.class.getResource("icon_education.png");
		Image img = Toolkit.getDefaultToolkit().createImage(urliconeducation);
		this.setIconImage(img);
	}

	// Getters for buttons
	public JButton getButton_updateprofile() {
		return button_updateprofile;
	}

	public JButton getButton_updateGPA() {
		return button_updateGPA;
	}

	public JButton getButton_updateGPA_THPT() {
		return button_updateGPA_THPT;
	}

	public JButton getButton_updateCe() {
		return button_updateCe;
	}

	public JButton getButton_search() {
		return button_search;
	}

	public JButton getButton_register() {
		return button_register;
	}

	public JMenuItem getThoat() {
		return thoat;
	}

	public void setThoat(JMenuItem thoat) {
		this.thoat = thoat;
	}

	public JMenuItem getDangXuat() {
		return dangXuat;
	}

	public void setDangXuat(JMenuItem dangXuat) {
		this.dangXuat = dangXuat;
	}

	public JMenuItem getDoiMatKhau() {
		return doiMatKhau;
	}

	public void setDoiMatKhau(JMenuItem doiMatKhau) {
		this.doiMatKhau = doiMatKhau;
	}

}