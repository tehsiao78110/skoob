package model.dao;

import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.bean.MemberBean;
import model.bean.MyFavBean;
import model.bean.ProductBean;
import model.compkey.FavId;

@Repository
public class MyfavDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public Set<MyFavBean> selectAll(Integer id) {
		MemberBean isExist = this.getSession().get(MemberBean.class, id);
		if (isExist != null) {
			return isExist.getMyFavs();
		}
		return null;
	}

	public MyFavBean insert(MyFavBean bean) {
		this.getSession().save(bean);
		return bean;
	}

	// 透過 MemberBean 拿到 Set<MyFavBean>
	public Set<MyFavBean> selectMyFavs(Integer id) {
		MemberBean memberExist = this.getSession().get(MemberBean.class, id);
		if (memberExist != null) {
			return memberExist.getMyFavs();
		}
		return null;
	}

	public MyFavBean select(Integer memberId, Integer productId) {
		if (memberId != null && productId != null) {
			return this.getSession().get(MyFavBean.class, new FavId(memberId, productId));
		} else {
			return null;
		}
	}

	// 刪除一筆收藏
	public boolean delete(Integer memberId, Integer productId) {
		if (memberId != null && productId != null) {
			String hql = "delete from MyFavBean where memberId=?0 and productId=?1";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setParameter(0, memberId);
			query.setParameter(1, productId);
			query.executeUpdate();
			return true;
		} else {
			return false;
		}
	}
}