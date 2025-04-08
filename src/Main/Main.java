package Main;

import java.awt.EventQueue;

import view.MenuDangNhapView;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

      EventQueue.invokeLater(() -> {
          try {
              MenuDangNhapView frame = new MenuDangNhapView();
              frame.setVisible(true);
          } catch (Exception e) {
              e.printStackTrace();
          }
      });

	}

}
