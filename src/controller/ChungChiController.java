package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.ThemChungChiMoiView;
import view.ChungChiView;
import view.DanhSachChungChiView;

public class ChungChiController {
	private ChungChiView view;
	private int iDHS;
	
	public ChungChiController(ChungChiView view, int iDHS) {
		this.view = view;
		this.iDHS = iDHS;
		view.getAddChungChi().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ThemChungChiMoiView frame = new ThemChungChiMoiView();
				frame.setVisible(true);
				new ThemChungChiMoiController(frame, iDHS);
			}
		});
		
		view.getThoat().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				view.dispose();
			}
		});
		
		view.getListChungChi().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DanhSachChungChiView frame = new DanhSachChungChiView();
				frame.setVisible(true);
				new DanhSachChungChiController(frame, iDHS);
			}
		});
	}

	public ChungChiView getView() {
		return view;
	}

	public void setView(ChungChiView view) {
		this.view = view;
	}

	public int getiDHS() {
		return iDHS;
	}

	public void setiDHS(int iDHS) {
		this.iDHS = iDHS;
	}
	
}
