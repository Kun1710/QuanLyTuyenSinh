package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

//import controller.duyetTuyenThangController;

import java.awt.Toolkit;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;

public class DuyetTuyenThangView extends JFrame {

	private JPanel contentPane;
	private JTextField hoTen;
	private JTextField tenTruong;
	private JTextField tenNganh;
	private JTable tableChungChi;
	private JButton thoat;
	private JButton dau;
	private JButton rot;
	
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					DuyetTuyenThangView frame = new DuyetTuyenThangView();
//					frame.setVisible(true);
//				//	new duyetTuyenThangController(frame, null, null);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public DuyetTuyenThangView() {
		setTitle("Hệ Thống Tuyển Sinh - Tuyển Thẳng");
		setIconImage(Toolkit.getDefaultToolkit().getImage(DuyetTuyenThangView.class.getResource("/view/icon_education.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 500);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 228, 196));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JLabel lblTuynThng = new JLabel(">> Tuyển Thẳng");
		lblTuynThng.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTuynThng.setBounds(6, 6, 422, 20);
		contentPane.add(lblTuynThng);
		
		JLabel label = new JLabel("HỌ TÊN");
		label.setFont(new Font("Tahoma", Font.BOLD, 11));
		label.setBounds(10, 40, 110, 20);
		contentPane.add(label);
		
		thoat = new JButton("THOÁT");
		thoat.setIcon(new ImageIcon(DuyetTuyenThangView.class.getResource("/view/exit_1.png")));
		thoat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		thoat.setFont(new Font("Tahoma", Font.BOLD, 11));
		thoat.setBackground(new Color(255, 99, 71));
		thoat.setBounds(6, 405, 122, 30);
		contentPane.add(thoat);
		
		hoTen = new JTextField();
		hoTen.setBounds(120, 40, 300, 25);
		contentPane.add(hoTen);
		
		JLabel lblTnTrng = new JLabel("Tên Trường");
		lblTnTrng.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTnTrng.setBounds(10, 70, 186, 25);
		contentPane.add(lblTnTrng);
		
		JLabel a = new JLabel("Tên Ngành");
		a.setFont(new Font("Tahoma", Font.BOLD, 11));
		a.setBounds(10, 100, 186, 25);
		contentPane.add(a);
		
		dau = new JButton("ĐẬU");
		dau.setIcon(new ImageIcon(DuyetTuyenThangView.class.getResource("/view/check.png")));
		dau.setFont(new Font("Tahoma", Font.BOLD, 11));
		dau.setBackground(new Color(152, 251, 152));
		dau.setBounds(295, 405, 122, 30);
		contentPane.add(dau);
		
		rot = new JButton("RỚT");
		rot.setForeground(new Color(0, 0, 0));
		rot.setIcon(new ImageIcon(DuyetTuyenThangView.class.getResource("/view/close.png")));
		rot.setFont(new Font("Tahoma", Font.BOLD, 11));
		rot.setBackground(new Color(255, 235, 205));
		rot.setBounds(150, 405, 122, 30);
		contentPane.add(rot);
		
		tenTruong = new JTextField();
		tenTruong.setBounds(120, 70, 300, 25);
		contentPane.add(tenTruong);
		
		tenNganh = new JTextField();
		tenNganh.setBounds(120, 100, 300, 25);
		contentPane.add(tenNganh);
		
        tableChungChi = new JTable();
        JScrollPane scrollPane = new JScrollPane(tableChungChi);
        scrollPane.setBounds(5, 140, 425, 250);
        contentPane.add(scrollPane);
        
		JScrollPane listChungChi = new JScrollPane((Component) null);
		listChungChi.setBounds(5, 140, 425, 250);
		contentPane.add(listChungChi);
	}
	public JTextField getHoTenField() {
	    return hoTen;
	}

	public JTextField getTruongField() {
	    return tenTruong;
	}

	public JTextField getNganhField() {
	    return tenNganh;
	}

	public JButton getThoatButton() {
	    return thoat;
	}

	public JButton getDauButton() {
	    return dau;
	}

	public JButton getRotButton() {
	    return rot;
	}
    public JTable getTableChungChi() {
        return tableChungChi;
    }
}
