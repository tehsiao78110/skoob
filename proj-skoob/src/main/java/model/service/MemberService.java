package model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.bean.MemberBean;

import model.dao.MemberDAO;

@Service
@Transactional
public class MemberService {

	@Autowired
	private MemberDAO memberDAO;

	// 新增會員
	public MemberBean insert(MemberBean bean) {
		MemberBean result = null;
		if (bean != null && bean.getAccount() != null) {
			result = memberDAO.insert(bean);
		}
		return result;
	}

	// 登入
	public MemberBean login(String username, String userpassword) {
		MemberBean bean = memberDAO.selectAccount(username);
		if (bean != null) {
			String account = bean.getAccount();
			String password = bean.getPassword();
			if (account.equals(username) && password.equals(userpassword)) {
				return bean;
			}
		}
		return null;
	}

	// 查詢帳號資料
	public MemberBean search(String username) {
		MemberBean bean = memberDAO.selectAccount(username);
		return bean;
	}

	// 確認帳號重複
	public boolean checkAccountExist(String bean) {
		MemberBean account = memberDAO.selectAccount(bean);
		if (account != null) {
			return true;
		} else {
			return false;
		}
	}

	// 確認email重複
	public boolean isRepeatEmail(MemberBean bean) {
		MemberBean email = memberDAO.selectEmail(bean.getEmail());
		if (email != null) {
			return true;
		} else {
			return false;
		}
	}

	// 編輯會員資料
	public MemberBean edit(MemberBean bean) {
		MemberBean member = null;
		if (bean != null && bean.getAccount() != null) {
			member = memberDAO.update(bean);
		}
		return member;
	}
}
