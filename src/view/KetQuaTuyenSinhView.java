package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;

public class KetQuaTuyenSinhView extends JFrame {

	private JPanel contentPane;
	private JButton button_quayLai;
	private JTable table;
	private JLabel _laytenNganh;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KetQuaTuyenSinhView frame = new KetQuaTuyenSinhView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public KetQuaTuyenSinhView() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(KetQuaTuyenSinhView.class.getResource("/view/icon_education.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 780, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
        setLocationRelativeTo(null);

		
		button_quayLai = new JButton("QUAY LẠI");
		button_quayLai.setFont(new Font("Tahoma", Font.BOLD, 12));
		button_quayLai.setBackground(new Color(216, 191, 216));
		button_quayLai.setBounds(323, 367, 130, 30);
		contentPane.add(button_quayLai);
		
		table = new JTable();
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(6, 35, 750, 320);
		contentPane.add(scrollPane);

		
		_laytenNganh = new JLabel();
		_laytenNganh.setFont(new Font("Tahoma", Font.BOLD, 13));
		_laytenNganh.setBounds(5, 5, 747, 30);
		contentPane.add(_laytenNganh);
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public JButton getButton_quayLai() {
		return button_quayLai;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}



	public void set_laytenNganh(JLabel _laytenNganh) {
		this._laytenNganh = _laytenNganh;
	}

	public JLabel get_laytenNganh() {
		// TODO Auto-generated method stub
		return _laytenNganh;
	}
	
}
