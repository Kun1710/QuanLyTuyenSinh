package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.ThemChungChiMoiController;
import controller.DiemHocBaController;
import dao.HocSinhDAO;
import model.TaiKhoan;
import util.SessionManager;

import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class ThemChungChiMoiView extends JFrame {

	private JPanel contentPane;
	private JTextField chungchi;
	private JTextField linkchungminh;
	private JComboBox<Integer> day;
	private JComboBox<Integer> month;
	private JComboBox<Integer> year;
	private JButton thoat;
	private JButton update;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddChungChiView frame = new AddChungChiView();
//					frame.setVisible(true);
//					hocSinhDAO hocSinhDAO = new hocSinhDAO();
//					account temp_account = SessionManager.getInstance().getCurrentAccount();
//					new AddChungChiController(frame, hocSinhDAO.getIdHSByIdTaiKhoan(temp_account.getId()));
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public ThemChungChiMoiView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(ThemChungChiMoiView.class.getResource("/view/icon_education.png")));
		setTitle("Hệ Thống Tuyển Sinh - Chứng Chỉ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 235, 205));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCpNht_1 = new JLabel(">> CHỨNG CHỈ MỚI");
		lblCpNht_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCpNht_1.setBounds(5, 5, 230, 20);
		contentPane.add(lblCpNht_1);
		
		JLabel lblChngCh = new JLabel("CHỨNG CHỈ");
		lblChngCh.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblChngCh.setBounds(10, 50, 110, 20);
		contentPane.add(lblChngCh);
		
		JLabel lblNgyCp = new JLabel("Ngày Cấp");
		lblNgyCp.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNgyCp.setBounds(10, 95, 110, 20);
		contentPane.add(lblNgyCp);
		
		day = new JComboBox<Integer>();
		day.setBackground(new Color(255, 250, 240));
		day.setBounds(135, 95, 60, 25);
		contentPane.add(day);
		
		month = new JComboBox<Integer>();
		month.setBackground(new Color(255, 250, 240));
		month.setBounds(205, 95, 60, 25);
		contentPane.add(month);
		
		year = new JComboBox<Integer>();
		year.setBackground(new Color(255, 250, 240));
		year.setBounds(275, 95, 80, 25);
		contentPane.add(year);
		
		chungchi = new JTextField();
		chungchi.setBounds(135, 50, 220, 25);
		contentPane.add(chungchi);
		
		JLabel lblLinkChngMinh = new JLabel("LINK CHỨNG MINH");
		lblLinkChngMinh.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLinkChngMinh.setBounds(10, 145, 110, 20);
		contentPane.add(lblLinkChngMinh);
		
		linkchungminh = new JTextField();
		linkchungminh.setBounds(135, 145, 220, 25);
		contentPane.add(linkchungminh);
		
		thoat = new JButton("THOÁT");
		thoat.setIcon(new ImageIcon(ThemChungChiMoiView.class.getResource("/view/exit_1.png")));
		thoat.setFont(new Font("Tahoma", Font.BOLD, 11));
		thoat.setBackground(new Color(255, 99, 71));
		thoat.setBounds(25, 200, 122, 30);
		contentPane.add(thoat);
		
		update = new JButton("CẬP NHẬT");
		update.setIcon(new ImageIcon(ThemChungChiMoiView.class.getResource("/view/update.png")));
		update.setFont(new Font("Tahoma", Font.BOLD, 11));
		update.setBackground(new Color(127, 255, 0));
		update.setBounds(230, 200, 122, 30);
		contentPane.add(update);
		
        for (int i = 1; i <= 31; i++) day.addItem(i);
        for (int i = 1; i <= 12; i++) month.addItem(i);
        for (int i = 2020; i <= 2025; i++) year.addItem(i);
        setLocationRelativeTo(null);

	}
    // Các getter methods để controller có thể truy cập các thành phần
    public JTextField getChungchi() {
        return chungchi;
    }

    public JTextField getLinkchungminh() {
        return linkchungminh;
    }

    public JComboBox<Integer> getDay() {
        return day;
    }

    public JComboBox<Integer> getMonth() {
        return month;
    }

    public JComboBox<Integer> getYear() {
        return year;
    }

    public JButton getThoatButton() {
        return thoat;
    }

    public JButton getUpdateButton() {
        return update;
    }
}
