package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.XetTuyenController;

import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;

public class XetTuyenView extends JFrame {

	private JPanel contentPane;
	private JButton hoSoTuyenThang;
	private JButton xetTuyenTuDong;
	private JButton thoat;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					XetTuyenView frame = new XetTuyenView();
					frame.setVisible(true);
					new XetTuyenController(frame);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public XetTuyenView() {
		setTitle("Hệ Thống Tuyển Sinh - Xét Tuyển");
		setIconImage(Toolkit.getDefaultToolkit().getImage(XetTuyenView.class.getResource("/view/icon_education.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(176, 224, 230));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		hoSoTuyenThang = new JButton("");
		hoSoTuyenThang.setBackground(new Color(224, 255, 255));
		hoSoTuyenThang.setIcon(new ImageIcon(XetTuyenView.class.getResource("/view/folder.png")));
		hoSoTuyenThang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		hoSoTuyenThang.setBounds(63, 70, 90, 90);
		contentPane.add(hoSoTuyenThang);
		
		xetTuyenTuDong = new JButton("");
		xetTuyenTuDong.setBackground(new Color(255, 235, 205));
		xetTuyenTuDong.setIcon(new ImageIcon(XetTuyenView.class.getResource("/view/auto.png")));
		xetTuyenTuDong.setBounds(251, 70, 90, 90);
		contentPane.add(xetTuyenTuDong);
		
		JLabel lblNewLabel = new JLabel("Hồ Sơ Tuyển Thẳng");
		lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		lblNewLabel.setBounds(58, 173, 120, 16);
		contentPane.add(lblNewLabel);
		
		JLabel text = new JLabel("Xét Tuyển Tự Động");
		text.setFont(new Font("SansSerif", Font.BOLD, 12));
		text.setBounds(242, 173, 109, 16);
		contentPane.add(text);
		
		thoat = new JButton("THOÁT");
		thoat.setIcon(new ImageIcon(XetTuyenView.class.getResource("/view/close.png")));
		thoat.setFont(new Font("Tahoma", Font.BOLD, 11));
		thoat.setBackground(new Color(250, 128, 114));
		thoat.setBounds(150, 210, 122, 30);
		contentPane.add(thoat);
		
	}
    public JButton getHoSoTuyenThangButton() {
        return hoSoTuyenThang;
    }
    public JButton getxetTuyenTuDongButton() {
        return xetTuyenTuDong;
    }
    public JButton getthoatButton() {
    	return thoat;
    }
}
