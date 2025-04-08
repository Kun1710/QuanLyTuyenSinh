package view;

//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class ChungChiView extends JFrame {

	private JPanel contentPane;
	private JButton Thoat;
	private JButton AddChungChi;
	private JButton ListChungChi;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ChungChiView frame = new ChungChiView();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public ChungChiView() {
		setTitle("Hệ Thống Tuyển Sinh - Chứng Chỉ");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ChungChiView.class.getResource("/view/icon_education.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ListChungChi = new JButton("");
		ListChungChi.setIcon(new ImageIcon(ChungChiView.class.getResource("/view/list.png")));
		ListChungChi.setBackground(new Color(224, 255, 255));
		ListChungChi.setBounds(80, 50, 90, 90);
		contentPane.add(ListChungChi);
		
		AddChungChi = new JButton("");
		AddChungChi.setIcon(new ImageIcon(ChungChiView.class.getResource("/view/plus.png")));
		AddChungChi.setBackground(new Color(224, 255, 255));
		AddChungChi.setBounds(250, 50, 90, 90);
		contentPane.add(AddChungChi);
		
		Thoat = new JButton("THOÁT");
		Thoat.setFont(new Font("Tahoma", Font.BOLD, 11));
		Thoat.setBackground(new Color(250, 128, 114));
		Thoat.setBounds(135, 201, 122, 30);
		contentPane.add(Thoat);
		
		JLabel lblDanhSchChng = new JLabel("Danh Sách Chứng Chỉ");
		lblDanhSchChng.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblDanhSchChng.setBounds(65, 159, 134, 16);
		contentPane.add(lblDanhSchChng);
		
		JLabel lblThmChngCh = new JLabel("Thêm Chứng Chỉ Mới");
		lblThmChngCh.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblThmChngCh.setBounds(231, 159, 134, 16);
		contentPane.add(lblThmChngCh);
		setLocationRelativeTo(null);
	}

	public JButton getThoat() {
		return Thoat;
	}

	public JButton getAddChungChi() {
		return AddChungChi;
	}

	public JButton getListChungChi() {
		return ListChungChi;
	}
	
}
