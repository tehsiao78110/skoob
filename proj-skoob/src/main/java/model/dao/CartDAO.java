package model.dao;

import java.util.List;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.bean.CartBean;
import model.bean.MemberBean;
import model.bean.ProductBean;
import model.compkey.CartId;

@Repository
public class CartDAO {
	@Autowired
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public CartBean select(MemberBean member, ProductBean product) {
		if (member != null && product != null) {
			return this.getSession().get(CartBean.class, new CartId(member.getMemberid(), product.getProductid()));
		}

		return null;
	}

	public Set<CartBean> selectAll(Integer membeid) {
		MemberBean isExist = this.getSession().get(MemberBean.class, membeid);
		if (isExist != null) {
			return isExist.getCarts();
		}
		return null;
	}

	public CartBean insert(CartBean cart) {
		return (CartBean) this.getSession().merge(cart);
	}

	public boolean delete(Integer membeid, Integer productid) {
		if (membeid != null && productid != null) {
			CartBean cart = this.getSession().get(CartBean.class, new CartId(membeid, productid));
			if (cart != null) {
				this.getSession().delete(cart);
			}
			return true;
		}
		return false;
	}

	public boolean deleteMulti(Integer membeid, List<Integer> checkid) {
		if (membeid != null && checkid != null && !checkid.isEmpty()) {
			String hql = "DELETE FROM CartBean WHERE memberid = :id AND productid IN (:list)";
			int success = this.getSession().createQuery(hql).setParameter("list", checkid).setParameter("id", membeid)
					.executeUpdate();
			if (success > 0) {
				return true;
			}
		}
		return false;
	}

	public CartBean update(CartBean cart) {
		CartBean isExist = this.getSession().get(CartBean.class, new CartId(cart.getMemberid(), cart.getProductid()));
		if (isExist != null) {
			return (CartBean) this.getSession().merge(cart);
		}

		return null;
	}

}
