package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;

import controller.MenuAdminController;

import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;

public class MenuAdminView extends JFrame {

    private JPanel contentPane;
    public JButton listHocSinh;
    public JButton listTruong;
    public JButton xetTuyen;
    public JMenuItem dangXuat;
    public JMenuItem doiMatKhau;
    public JMenuItem thoat;

//    public static void main(String[] args) {
//        EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                try {
//                    MenuAdminView frame = new MenuAdminView();
//                    frame.setVisible(true);
//                    new MenuAdminController(frame);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    public MenuAdminView() {
        setTitle("Hệ Thống Tuyển Sinh - Menu");
        setIconImage(Toolkit.getDefaultToolkit().getImage(MenuAdminView.class.getResource("/view/icon_education.png")));
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(224, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu chucNang = new JMenu("Chức năng");
        chucNang.setFont(new Font("Segoe UI", Font.BOLD, 12));
        chucNang.setIcon(new ImageIcon(MenuAdminView.class.getResource("/view/chucnang.png")));
        menuBar.add(chucNang);
        
        dangXuat = new JMenuItem("Đăng Xuất");
        dangXuat.setIcon(new ImageIcon(MenuAdminView.class.getResource("/view/logout.png")));
        chucNang.add(dangXuat);
        
        doiMatKhau = new JMenuItem("Đổi Mật Khẩu");
        doiMatKhau.setIcon(new ImageIcon(MenuAdminView.class.getResource("/view/exchange.png")));
        chucNang.add(doiMatKhau);
        
        JSeparator separator = new JSeparator();
        chucNang.add(separator);
        
        thoat = new JMenuItem("Thoát");
        thoat.setIcon(new ImageIcon(MenuAdminView.class.getResource("/view/exit_1.png")));
        chucNang.add(thoat);
        
        listHocSinh = new JButton("");
        listHocSinh.setBackground(new Color(250, 235, 215));
        listHocSinh.setIcon(new ImageIcon(MenuAdminView.class.getResource("/view/list.png")));
        listHocSinh.setBounds(27, 50, 100, 100);
        contentPane.add(listHocSinh);
        
        listTruong = new JButton("");
        listTruong.setBackground(new Color(245, 245, 245));
        listTruong.setIcon(new ImageIcon(MenuAdminView.class.getResource("/view/list-text.png")));
        listTruong.setBounds(160, 50, 100, 100);
        contentPane.add(listTruong);
        
        xetTuyen = new JButton("");
        xetTuyen.setBackground(new Color(255, 192, 203));
        xetTuyen.setIcon(new ImageIcon(MenuAdminView.class.getResource("/view/seo.png")));
        xetTuyen.setBounds(300, 50, 100, 100);
        contentPane.add(xetTuyen);
        
        JLabel lblNewLabel = new JLabel("Danh Sách Học Sinh");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblNewLabel.setBounds(20, 180, 150, 20);
        contentPane.add(lblNewLabel);
        
        JLabel lblDanhSchTrng = new JLabel("Danh Sách Trường");
        lblDanhSchTrng.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblDanhSchTrng.setBounds(160, 180, 150, 20);
        contentPane.add(lblDanhSchTrng);
        
        JLabel lblXtTuyn = new JLabel("Xét Tuyển");
        lblXtTuyn.setFont(new Font("Tahoma", Font.BOLD, 11));
        lblXtTuyn.setBounds(320, 181, 150, 20);
        contentPane.add(lblXtTuyn);
        setLocationRelativeTo(null);
    }

	public JButton getListHocSinh() {
		return listHocSinh;
	}

	public void setListHocSinh(JButton listHocSinh) {
		this.listHocSinh = listHocSinh;
	}

	public JButton getListTruong() {
		return listTruong;
	}

	public void setListTruong(JButton listTruong) {
		this.listTruong = listTruong;
	}

	public JButton getXetTuyen() {
		return xetTuyen;
	}

	public void setXetTuyen(JButton xetTuyen) {
		this.xetTuyen = xetTuyen;
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

	public JMenuItem getThoat() {
		return thoat;
	}

	public void setThoat(JMenuItem thoat) {
		this.thoat = thoat;
	}
    
}