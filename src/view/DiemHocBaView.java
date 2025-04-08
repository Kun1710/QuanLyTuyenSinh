package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.DiemHocBaController;
import dao.HocSinhDAO;
import model.TaiKhoan;
import util.SessionManager;

import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class DiemHocBaView extends JFrame {

	private JPanel contentPane;
	private JTextField toan10;
	private JTextField ly10;
	private JTextField hoa10;
	private JTextField sinh10;
	private JTextField van10;
	private JTextField su10;
	private JTextField dia10;
	private JTextField gdcd10;
	private JTextField nn10;
	private JTextField toan11;
	private JTextField van11;
	private JTextField nn11;
	private JTextField ly11;
	private JTextField su11;
	private JTextField hoa11;
	private JTextField dia11;
	private JTextField sinh11;
	private JTextField gdcd11;
	private JTextField toan12;
	private JTextField van12;
	private JTextField nn12;
	private JTextField ly12;
	private JTextField su12;
	private JTextField hoa12;
	private JTextField dia12;
	private JTextField gdcd12;
	private JTextField sinh12;
	private JButton thoat;
	private JButton update;
	
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					DiemHocBaView frame = new DiemHocBaView();
//					frame.setVisible(true);
//					hocSinhDAO hocSinhDAO = new hocSinhDAO();
//					account temp_account = SessionManager.getInstance().getCurrentAccount();
//					new DiemHocBaController(frame, hocSinhDAO.getIdHSByIdTaiKhoan(temp_account.getId()));
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public DiemHocBaView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(DiemHocBaView.class.getResource("/view/icon_education.png")));
		setTitle("Hệ Thống Tuyển Sinh - Điểm Học Bạ");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 550);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(230, 230, 250));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Khối 10");
		lblNewLabel.setIcon(new ImageIcon(DiemHocBaView.class.getResource("/view/number-10.png")));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(20, 34, 70, 20);
		contentPane.add(lblNewLabel);
		
		JLabel lblLp = new JLabel("Khối 11");
		lblLp.setIcon(new ImageIcon(DiemHocBaView.class.getResource("/view/number-11.png")));
		lblLp.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLp.setBounds(20, 170, 70, 20);
		contentPane.add(lblLp);
		
		JLabel lblLp_1 = new JLabel("Khối 12");
		lblLp_1.setIcon(new ImageIcon(DiemHocBaView.class.getResource("/view/number-12.png")));
		lblLp_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblLp_1.setBounds(20, 314, 70, 20);
		contentPane.add(lblLp_1);
		
		JLabel lblTon = new JLabel("Toán");
		lblTon.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTon.setBounds(20, 65, 33, 20);
		contentPane.add(lblTon);
		
		JLabel lblNewLabel_1_1 = new JLabel("Lý");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1.setBounds(140, 65, 30, 20);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Hóa");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_2.setBounds(270, 65, 30, 20);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Sinh");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_3.setBounds(400, 65, 30, 20);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Văn");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_4.setBounds(20, 100, 40, 20);
		contentPane.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("Sử");
		lblNewLabel_1_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_5.setBounds(140, 100, 40, 20);
		contentPane.add(lblNewLabel_1_5);
		
		JLabel lblNewLabel_1_6 = new JLabel("Địa");
		lblNewLabel_1_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_6.setBounds(270, 100, 40, 20);
		contentPane.add(lblNewLabel_1_6);
		
		JLabel lblNewLabel_1_7 = new JLabel("GDCD");
		lblNewLabel_1_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_7.setBounds(400, 100, 40, 20);
		contentPane.add(lblNewLabel_1_7);
		
		JLabel lblNewLabel_1 = new JLabel("Ngoại ngữ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(20, 140, 70, 20);
		contentPane.add(lblNewLabel_1);
		
		toan10 = new JTextField();
		toan10.setBounds(70, 65, 50, 20);
		contentPane.add(toan10);
		toan10.setColumns(10);
		
		ly10 = new JTextField();
		ly10.setColumns(10);
		ly10.setBounds(175, 65, 50, 20);
		contentPane.add(ly10);
		
		hoa10 = new JTextField();
		hoa10.setColumns(10);
		hoa10.setBounds(310, 65, 50, 20);
		contentPane.add(hoa10);
		
		sinh10 = new JTextField();
		sinh10.setColumns(10);
		sinh10.setBounds(440, 64, 50, 20);
		contentPane.add(sinh10);
		
		van10 = new JTextField();
		van10.setColumns(10);
		van10.setBounds(70, 100, 50, 20);
		contentPane.add(van10);
		
		su10 = new JTextField();
		su10.setColumns(10);
		su10.setBounds(175, 100, 50, 20);
		contentPane.add(su10);
		
		dia10 = new JTextField();
		dia10.setColumns(10);
		dia10.setBounds(310, 100, 50, 20);
		contentPane.add(dia10);
		
		gdcd10 = new JTextField();
		gdcd10.setColumns(10);
		gdcd10.setBounds(440, 100, 50, 20);
		contentPane.add(gdcd10);
		
		nn10 = new JTextField();
		nn10.setColumns(10);
		nn10.setBounds(100, 140, 50, 20);
		contentPane.add(nn10);
		
		JLabel lblTon_1 = new JLabel("Toán");
		lblTon_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTon_1.setBounds(20, 205, 33, 20);
		contentPane.add(lblTon_1);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Văn");
		lblNewLabel_1_4_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_4_1.setBounds(20, 240, 40, 20);
		contentPane.add(lblNewLabel_1_4_1);
		
		JLabel lblNewLabel_1_8 = new JLabel("Ngoại ngữ");
		lblNewLabel_1_8.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_8.setBounds(20, 280, 70, 20);
		contentPane.add(lblNewLabel_1_8);
		
		toan11 = new JTextField();
		toan11.setColumns(10);
		toan11.setBounds(70, 205, 50, 20);
		contentPane.add(toan11);
		
		van11 = new JTextField();
		van11.setColumns(10);
		van11.setBounds(70, 240, 50, 20);
		contentPane.add(van11);
		
		nn11 = new JTextField();
		nn11.setColumns(10);
		nn11.setBounds(100, 280, 50, 20);
		contentPane.add(nn11);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Lý");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1_1.setBounds(140, 205, 30, 20);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_5_1 = new JLabel("Sử");
		lblNewLabel_1_5_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_5_1.setBounds(140, 240, 40, 20);
		contentPane.add(lblNewLabel_1_5_1);
		
		ly11 = new JTextField();
		ly11.setColumns(10);
		ly11.setBounds(175, 205, 50, 20);
		contentPane.add(ly11);
		
		su11 = new JTextField();
		su11.setColumns(10);
		su11.setBounds(175, 240, 50, 20);
		contentPane.add(su11);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Hóa");
		lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1_2.setBounds(270, 205, 30, 20);
		contentPane.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_5_2 = new JLabel("Địa");
		lblNewLabel_1_5_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_5_2.setBounds(270, 240, 40, 20);
		contentPane.add(lblNewLabel_1_5_2);
		
		hoa11 = new JTextField();
		hoa11.setColumns(10);
		hoa11.setBounds(305, 205, 50, 20);
		contentPane.add(hoa11);
		
		dia11 = new JTextField();
		dia11.setColumns(10);
		dia11.setBounds(305, 240, 50, 20);
		contentPane.add(dia11);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Sinh");
		lblNewLabel_1_3_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_3_1.setBounds(400, 202, 30, 20);
		contentPane.add(lblNewLabel_1_3_1);
		
		JLabel lblNewLabel_1_7_1 = new JLabel("GDCD");
		lblNewLabel_1_7_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_7_1.setBounds(400, 237, 40, 20);
		contentPane.add(lblNewLabel_1_7_1);
		
		sinh11 = new JTextField();
		sinh11.setColumns(10);
		sinh11.setBounds(440, 201, 50, 20);
		contentPane.add(sinh11);
		
		gdcd11 = new JTextField();
		gdcd11.setColumns(10);
		gdcd11.setBounds(440, 237, 50, 20);
		contentPane.add(gdcd11);
		
		JLabel lblTon_2 = new JLabel("Toán");
		lblTon_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTon_2.setBounds(20, 345, 33, 20);
		contentPane.add(lblTon_2);
		
		JLabel lblNewLabel_1_4_2 = new JLabel("Văn");
		lblNewLabel_1_4_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_4_2.setBounds(20, 380, 40, 20);
		contentPane.add(lblNewLabel_1_4_2);
		
		toan12 = new JTextField();
		toan12.setColumns(10);
		toan12.setBounds(70, 345, 50, 20);
		contentPane.add(toan12);
		
		van12 = new JTextField();
		van12.setColumns(10);
		van12.setBounds(70, 380, 50, 20);
		contentPane.add(van12);
		
		JLabel lblNewLabel_1_9 = new JLabel("Ngoại ngữ");
		lblNewLabel_1_9.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_9.setBounds(20, 422, 70, 20);
		contentPane.add(lblNewLabel_1_9);
		
		nn12 = new JTextField();
		nn12.setColumns(10);
		nn12.setBounds(100, 422, 50, 20);
		contentPane.add(nn12);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("Lý");
		lblNewLabel_1_1_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_1_3.setBounds(140, 345, 30, 20);
		contentPane.add(lblNewLabel_1_1_3);
		
		JLabel lblNewLabel_1_5_3 = new JLabel("Sử");
		lblNewLabel_1_5_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_5_3.setBounds(140, 380, 40, 20);
		contentPane.add(lblNewLabel_1_5_3);
		
		ly12 = new JTextField();
		ly12.setColumns(10);
		ly12.setBounds(175, 345, 50, 20);
		contentPane.add(ly12);
		
		su12 = new JTextField();
		su12.setColumns(10);
		su12.setBounds(175, 380, 50, 20);
		contentPane.add(su12);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Hóa");
		lblNewLabel_1_2_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_2_1.setBounds(270, 345, 30, 20);
		contentPane.add(lblNewLabel_1_2_1);
		
		JLabel lblNewLabel_1_6_1 = new JLabel("Địa");
		lblNewLabel_1_6_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_6_1.setBounds(270, 380, 40, 20);
		contentPane.add(lblNewLabel_1_6_1);
		
		hoa12 = new JTextField();
		hoa12.setColumns(10);
		hoa12.setBounds(310, 345, 50, 20);
		contentPane.add(hoa12);
		
		dia12 = new JTextField();
		dia12.setColumns(10);
		dia12.setBounds(310, 380, 50, 20);
		contentPane.add(dia12);
		
		JLabel lblNewLabel_1_3_2 = new JLabel("Sinh");
		lblNewLabel_1_3_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_3_2.setBounds(400, 345, 30, 20);
		contentPane.add(lblNewLabel_1_3_2);
		
		JLabel lblNewLabel_1_7_2 = new JLabel("GDCD");
		lblNewLabel_1_7_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1_7_2.setBounds(400, 380, 40, 20);
		contentPane.add(lblNewLabel_1_7_2);
		
		gdcd12 = new JTextField();
		gdcd12.setColumns(10);
		gdcd12.setBounds(440, 380, 50, 20);
		contentPane.add(gdcd12);
		
		sinh12 = new JTextField();
		sinh12.setColumns(10);
		sinh12.setBounds(440, 344, 50, 20);
		contentPane.add(sinh12);
		
		thoat = new JButton("THOÁT");
		thoat.setIcon(new ImageIcon(DiemHocBaView.class.getResource("/view/exit_1.png")));
		thoat.setFont(new Font("Tahoma", Font.BOLD, 11));
		thoat.setBackground(new Color(255, 99, 71));
		thoat.setBounds(41, 470, 122, 30);
		contentPane.add(thoat);
		
		update = new JButton("CẬP NHẬT");
		update.setIcon(new ImageIcon(DiemHocBaView.class.getResource("/view/update.png")));
		update.setFont(new Font("Tahoma", Font.BOLD, 11));
		update.setBackground(new Color(127, 255, 0));
		update.setBounds(308, 474, 122, 30);
		contentPane.add(update);
		
		JLabel lblCpNht = new JLabel(">> CẬP NHẬT ĐIỂM HỌC BẠ");
		lblCpNht.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCpNht.setBounds(5, 5, 230, 20);
		contentPane.add(lblCpNht);
		setLocationRelativeTo(null);
		
	}
	// Getters for all text fields
    public JTextField getToan10() { return toan10; }
    public JTextField getLy10() { return ly10; }
    public JTextField getHoa10() { return hoa10; }
    public JTextField getSinh10() { return sinh10; }
    public JTextField getVan10() { return van10; }
    public JTextField getSu10() { return su10; }
    public JTextField getDia10() { return dia10; }
    public JTextField getGdcd10() { return gdcd10; }
    public JTextField getNn10() { return nn10; }
    
    public JTextField getToan11() { return toan11; }
    public JTextField getVan11() { return van11; }
    public JTextField getNn11() { return nn11; }
    public JTextField getLy11() { return ly11; }
    public JTextField getSu11() { return su11; }
    public JTextField getHoa11() { return hoa11; }
    public JTextField getDia11() { return dia11; }
    public JTextField getSinh11() { return sinh11; }
    public JTextField getGdcd11() { return gdcd11; }
    
    public JTextField getToan12() { return toan12; }
    public JTextField getVan12() { return van12; }
    public JTextField getNn12() { return nn12; }
    public JTextField getLy12() { return ly12; }
    public JTextField getSu12() { return su12; }
    public JTextField getHoa12() { return hoa12; }
    public JTextField getDia12() { return dia12; }
    public JTextField getGdcd12() { return gdcd12; }
    public JTextField getSinh12() { return sinh12; }
    
    // Getters for buttons
    public JButton getThoatButton() { return thoat; }
    public JButton getUpdateButton() { return update; }

}
