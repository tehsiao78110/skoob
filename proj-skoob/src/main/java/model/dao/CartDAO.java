package model.dao;

import java.io.Serializable;
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

	public MemberBean selectMember(Integer membeid) {
		if (membeid != null) {
			return this.getSession().get(MemberBean.class, membeid);
		}
		return null;
	}

	public ProductBean selectProduct(Integer productid) {
		System.out.println("DAO = " + productid);
		if (productid != null) {
			return this.getSession().get(ProductBean.class, productid);
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

	public CartBean select(MemberBean member, ProductBean product) {
		if (member != null && product != null) {
			return this.getSession().get(CartBean.class, new CartId(member.getMemberid(), product.getProductid()));
		}

		return null;
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

	public CartBean update(CartBean cart) {
		CartBean isExist = this.getSession().get(CartBean.class, new CartId(cart.getMemberid(), cart.getProductid()));
		if (isExist != null) {
			return (CartBean) this.getSession().merge(cart);
		}

		return null;
	}

	public CartBean insert(CartBean cart) {
		return (CartBean) this.getSession().merge(cart);
	}

	// ===========================================================================================
	// 沒有使用到的語法
	// ===========================================================================================
	public List<CartBean> selectAllHql(Integer membeid) {
		return this.getSession().createQuery("FROM CartBean WHERE memberId = :id", CartBean.class)
				.setParameter("id", membeid).list();
	}

	public List<CartBean> selectAllSql(Integer membeid) {
		return this.getSession().createNativeQuery("SELECT * FROM  cart WHERE memberid = ?", CartBean.class)
				.setParameter(1, membeid).list();
	}
}
