package model.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.bean.MemberBean;

@Repository
public class MemberDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	// 新增
	public MemberBean insert(MemberBean bean) {
		this.getSession().save(bean);
		return bean;
	}

	// 查詢帳號
	public MemberBean selectAccount(String account) {
		if (account != null) {
			Query<MemberBean> query = sessionFactory.getCurrentSession()
					.createQuery("FROM MemberBean WHERE account = :account", MemberBean.class);
			query.setParameter("account", account);
			MemberBean memberaccount = query.uniqueResult();
			return memberaccount;
		} else {
			return null;
		}
	}

	// 查詢email
	public MemberBean selectEmail(String email) {
		if (email != null) {
			Query<MemberBean> query = sessionFactory.getCurrentSession()
					.createQuery("FROM MemberBean WHERE email = :email", MemberBean.class);
			query.setParameter("email", email);
			MemberBean mail = query.uniqueResult();
			return mail;
		} else {
			return null;
		}
	}

	// 修改資料
	public MemberBean update(MemberBean member) {
		if (member != null) {
			this.getSession().merge(member);
			return member;
		}
		return null;
	}

	public MemberBean selectMemberId(Integer membeid) {
		if (membeid != null) {
			return this.getSession().get(MemberBean.class, membeid);
		}
		return null;
	}
}
