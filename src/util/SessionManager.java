package util;

import model.TaiKhoan;
import java.util.Stack;

public class SessionManager {
	private static SessionManager instance;
	private TaiKhoan currentAccount;
	private Stack<TaiKhoan> accountStack;

	private SessionManager() {
		this.accountStack = new Stack<>();
	}

	public static SessionManager getInstance() {
		if (instance == null) {
			instance = new SessionManager();
		}
		return instance;
	}

	public void setCurrentAccount(TaiKhoan acc) {
		this.currentAccount = acc;
	}

	public TaiKhoan getCurrentAccount() {
		return currentAccount;
	}

	/**
	 * Lưu tài khoản hiện tại vào stack và thiết lập tài khoản mới
	 * 
	 * @param newAccount Tài khoản mới sẽ được thiết lập
	 */
	public void pushAccount(TaiKhoan newAccount) {
		if (currentAccount != null) {
			accountStack.push(currentAccount);
		}
		this.currentAccount = newAccount;
	}

	/**
	 * Khôi phục tài khoản trước đó từ stack
	 * 
	 * @return Tài khoản được khôi phục, hoặc null nếu stack rỗng
	 */
	public TaiKhoan popAccount() {
		if (!accountStack.isEmpty()) {
			this.currentAccount = accountStack.pop();
			return this.currentAccount;
		}
		return null;
	}

	/**
	 * Xem tài khoản trước đó mà không xóa khỏi stack
	 * 
	 * @return Tài khoản trước đó, hoặc null nếu stack rỗng
	 */
	public TaiKhoan peekPreviousAccount() {
		if (!accountStack.isEmpty()) {
			return accountStack.peek();
		}
		return null;
	}

	/**
	 * Kiểm tra có tài khoản nào được lưu trong stack không
	 * 
	 * @return true nếu có tài khoản được lưu
	 */
	public boolean hasPreviousAccount() {
		return !accountStack.isEmpty();
	}

	/**
	 * Xóa tất cả tài khoản đã lưu trong stack
	 */
	public void clearAccountStack() {
		accountStack.clear();
	}
}